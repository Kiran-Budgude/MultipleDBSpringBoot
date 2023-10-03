package com.example.two.db.demo.notificationFCM;

import com.example.two.db.demo.entity.db2.GenericNotification;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationService {

    RestTemplate restTemplate;

    public NotificationService() {
    }

    @Autowired
    public NotificationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${fcm.api.key}")
    private String apiKey;

    @Value("${fcm.api.url}")
    private String apiUrl;


    public PushDto execute(GenericNotification genericNotification) {

        PushNotificationDto pushNotificationDto = null;

        if (genericNotification instanceof PushNotificationDto) {
            pushNotificationDto = (PushNotificationDto) genericNotification;
        }
        if (pushNotificationDto != null) {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", "Bearer " + apiKey);

            Map<String, Object> data = new HashMap<>();
            data.put("to", pushNotificationDto.getTo());

            Map<String, String> notification = new HashMap<>();
            notification.put("title", pushNotificationDto.getSubject());
            notification.put("body", pushNotificationDto.getContent());
            data.put("notification", notification);

            if (pushNotificationDto.getModel() != null) {
                data.put("data", pushNotificationDto.getModel());
            }

            HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(data, headers);
            try {
                ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, httpEntity, String.class);
                System.out.println("Response : " + response);
                JSONObject jsonObject = new JSONObject(response.getBody());
                PushDto pushDto = new PushDto();
                pushDto.setMulticastId(jsonObject.getString("multicast_id"));
                pushDto.setSuccess(Long.parseLong(jsonObject.getString("success")));
                pushDto.setFailure(Long.parseLong(jsonObject.getString("failure")));
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                JSONObject results = jsonArray.getJSONObject(0);
                pushDto.setError(results.getString("error"));
                pushDto.setResponseMsg("Successfully Send Notification");
                if (pushDto.getFailure() == 1) {
                    pushDto.setResponseMsg("Fail To Send Notification");
                }

                return pushDto;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return null;
    }

}
