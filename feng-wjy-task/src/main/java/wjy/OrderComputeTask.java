package wjy;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderComputeTask implements Runnable {
	
	private static final Logger logger =  LoggerFactory.getLogger(OrderComputeTask.class);

	public void run() {
		// TODO Auto-generated method stub
	}
	
	public final static long ONE_Second =  1000;
	public final static long ONE_Minute =  60 * 1000;

    @Scheduled(fixedDelay=ONE_Second)
    public void fixedDelayJob(){
    	logger.info(" >>fixedDelay执行....");
        
    }

    @Scheduled(fixedRate=ONE_Minute)
    public void fixedRateJob() throws InterruptedException{
    	logger.info(" >>fixedRate执行....start");     
    	Thread.sleep(ONE_Second*2);
    	logger.info(" >>fixedRate执行....end");     
    }

    @Scheduled(cron="0 15 3 * * ?")
    public void cronJob(){
    	logger.info(" >>cron执行....");
    }

}
