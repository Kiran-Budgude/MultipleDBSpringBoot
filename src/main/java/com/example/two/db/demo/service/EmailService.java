package com.example.two.db.demo.service;

import com.example.two.db.demo.entity.db1.NotificationData;
import com.example.two.db.demo.entity.db2.ManagerData;
import com.example.two.db.demo.mysql.repository.db1.NotificationDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailService {


    SendGrid sendGrid;

    NotificationDataRepository notificationDataRepository;

    @Autowired
    public EmailService(SendGrid sendGrid, NotificationDataRepository notificationDataRepository) {
        this.sendGrid = sendGrid;
        this.notificationDataRepository = notificationDataRepository;
    }

    public String snedEmailWithSendGrid(String email, String jsonObject, String templateId, List<String> bccEmails) {

        Request request = new Request();
        Response response = null;

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(SendDynamicTemplateData(email, jsonObject, templateId, bccEmails).build());
            response = sendGrid.api(request);

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
        return "Mail send successfully";
    }

    public Mail SendDynamicTemplateData(String email, String jsonObject, String templateId, List<String> bccEmails) throws JSONException, IOException {

        Mail mail = new Mail();
        Email fromMail = new Email();
        fromMail.setName("Hr Consultancy");
        Personalization personalization = new Personalization();

        NotificationData notificationData = notificationDataRepository.findByTemplateId(templateId);
        if (notificationData.getModule().equalsIgnoreCase("employee")) {
            fromMail.setEmail("from");
            ObjectMapper objectMapper = new ObjectMapper();
            ManagerData managerData = objectMapper.readValue(jsonObject,ManagerData.class);

            personalization.addDynamicTemplateData("managerName",managerData.getManagerName());
            personalization.addDynamicTemplateData("salaries", managerData.getSalaries());
        }
        mail.setFrom(fromMail);
        mail.setTemplateId(templateId);
        personalization.addTo(new Email(email));

        if (bccEmails != null && !bccEmails.isEmpty()) {
            for (String bcc : bccEmails
            ) {
                personalization.addBcc(new Email(bcc));
            }
        }
        mail.addPersonalization(personalization);
        return mail;

    }

}
