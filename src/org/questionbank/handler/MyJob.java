package org.questionbank.handler;

import org.quartz.JobExecutionContext;  
import org.quartz.JobExecutionException;  
import org.questionbank.tasks.MyTask;
import org.springframework.scheduling.quartz.QuartzJobBean;  
  
public class MyJob extends QuartzJobBean {  
  
 private MyTask myTask;  
  
 public void setMyTask(MyTask myTask) {  
  this.myTask = myTask;  
 }  
  
 @Override  
 protected void executeInternal(JobExecutionContext arg0)  
   throws JobExecutionException {  
  // TODO Auto-generated method stub  
  myTask.printCurrentTime();  
  
 }  
  
}  