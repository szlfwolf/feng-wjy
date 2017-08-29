package wjy;


import java.io.IOException;

import org.joda.time.LocalTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import hello.Greeter;
import multithread.ThreadLocalDemo;

@SpringBootApplication
@EnableScheduling
@EnableAsync  
public class WjyTaskApplication  {

	private static final Logger logger =  LoggerFactory.getLogger(WjyTaskApplication.class);
	public static void main(String[] args) {
		

		
		SpringApplication.run(WjyTaskApplication.class, args);
		
		LocalTime currentTime = new LocalTime();
		logger.info("The current local time is: " + currentTime);
		
		Greeter greeter = new Greeter();
		logger.info(greeter.sayHello());
		
		ThreadLocalDemo td = new ThreadLocalDemo();
		Thread t1 = new Thread(td, "a");
		Thread t2 = new Thread(td, "b");
		t1.start();
		t2.start();
		
		OrderLoaderTask loadtask = new OrderLoaderTask();
		Thread loadTask = new Thread(loadtask,"OrderLoaderTask");
		loadTask.run();
		

		
		/*
		 try {
			System.in.read();			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("read error",e);
		}finally{		
		}
		*/
		logger.info("Application exit!");
	}
}
