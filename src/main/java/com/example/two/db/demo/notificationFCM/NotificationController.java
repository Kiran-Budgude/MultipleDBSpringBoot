package com.example.two.db.demo.notificationFCM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/v1/notification")
public class NotificationController {

   @Autowired
   NotificationService notificationService;


   @RequestMapping(value = "/notifySingle",method = RequestMethod.POST)
    ResponseEntity<?> pushNotification(@RequestBody PushNotificationDto pushNotificationDto){

         PushDto pushDto = notificationService.execute(pushNotificationDto);

     return new ResponseEntity<>(pushDto, HttpStatus.OK);



   }



}
