package com.example.two.db.demo.entity.db1;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity
@Table(name = "notificationData")
@Primary
public class NotificationData {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    private String module;

    private String subModule;

    private String templateId;

    private String subject;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getSubModule() {
        return subModule;
    }

    public void setSubModule(String subModule) {
        this.subModule = subModule;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
