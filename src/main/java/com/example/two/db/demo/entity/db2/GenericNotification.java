package com.example.two.db.demo.entity.db2;

import java.util.HashMap;
import java.util.Map;

public class GenericNotification {

    private String to;

    private String content;

    private String title;

    private String notificationType;

    private String subject;

    private Map<String, Object> model;

    public Map<String, Object> getModel() {
        if (model == null) {
            model = new HashMap<>();
        }
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
