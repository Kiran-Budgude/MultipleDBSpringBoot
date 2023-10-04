package com.example.two.db.demo.mysql.repository.db1;

import com.example.two.db.demo.entity.db1.NotificationData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationDataRepository extends JpaRepository<NotificationData,Long> {


    NotificationData findByTemplateId(String templateId);

    @Query("select n from NotificationData n where n.module='employee' and n.subject='email'")
    NotificationData findTemplateId();


}
