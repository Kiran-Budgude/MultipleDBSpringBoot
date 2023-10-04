package com.example.two.db.demo.notificationFCM;

import com.example.two.db.demo.entity.db1.NotificationData;
import com.example.two.db.demo.mysql.repository.db1.NotificationDataRepository;
import com.example.two.db.demo.service.EmailService;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;

@Controller
@RequestMapping(value = "/v1/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @Autowired
    EmailService emailService;

    @Autowired
    NotificationDataRepository notificationDataRepository;


    @RequestMapping(value = "/notifySingle", method = RequestMethod.POST)
    ResponseEntity<?> pushNotification(@RequestBody PushNotificationDto pushNotificationDto) {

        ApiResponse apiResponse = new ApiResponse();
        try {
            PushDto pushDto = notificationService.execute(pushNotificationDto);
            apiResponse.setResponse(pushDto);
            apiResponse.setStatus(HttpStatus.OK);
            apiResponse.setCode(200);

        } catch (Exception e) {
            apiResponse.setErrros(e);
            apiResponse.setCode(500);
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @RequestMapping(value = "send/mail", method = RequestMethod.POST)
    ResponseEntity<?> sendMailSendGrid(@RequestBody String manager) throws JSONException {

        ApiResponse apiResponse = new ApiResponse();
        try {
            NotificationData notificationData = notificationDataRepository.findTemplateId();
            String response = emailService.snedEmailWithSendGrid("to", manager, notificationData.getTemplateId(), Collections.singletonList("bcc"));
            apiResponse.setCode(200);
            apiResponse.setStatus(HttpStatus.OK);
            apiResponse.setResponse(response);
        } catch (Exception e) {
            apiResponse.setErrros(e);
            apiResponse.setCode(500);
            apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);


    }


}
