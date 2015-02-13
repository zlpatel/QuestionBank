package org.questionbank.tasks;

import java.util.Calendar;  
import org.questionbank.serviceImpl.SendMailTLS;
 
public class MyTask {  
  
 public void printCurrentTime() {  
  // printing current system time  
//  System.out.println("Current Time : " + Calendar.getInstance().getTime()); 
  SendMailTLS mailService=new SendMailTLS();
  mailService.sendEmail("zlpatel@hotmail.com", ""+Calendar.getInstance().getTime() ,"Reminder");
 }  
  
}  