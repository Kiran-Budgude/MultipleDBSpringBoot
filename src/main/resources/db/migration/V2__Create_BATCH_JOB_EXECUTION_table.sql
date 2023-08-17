                        CREATE TABLE BATCH_JOB_EXECUTION (
                            JOB_EXECUTION_ID BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                            VERSION BIGINT,
                            JOB_INSTANCE_ID BIGINT NOT NULL,
                            CREATE_TIME DATETIME NOT NULL,
                            START_TIME DATETIME,
                            END_TIME DATETIME,
                            STATUS VARCHAR(10),
                            EXIT_CODE VARCHAR(2500),
                            EXIT_MESSAGE VARCHAR(2500),
                            LAST_UPDATED DATETIME,
                            JOB_CONFIGURATION_LOCATION VARCHAR(2500),
                            FOREIGN KEY (JOB_INSTANCE_ID) REFERENCES BATCH_JOB_INSTANCE(JOB_INSTANCE_ID)
                        );