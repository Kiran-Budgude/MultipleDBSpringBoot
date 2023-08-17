/*
package com.example.two.db.demo.batchconfig;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;

public class MyStepExecutionListener extends StepExecutionListenerSupport {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("Step " + stepExecution.getStepName() + " is about to start execution.");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("Step " + stepExecution.getStepName() + " has completed execution.");
        return stepExecution.getExitStatus();
    }
}

*/
