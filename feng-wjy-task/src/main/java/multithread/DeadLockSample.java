package multithread;

public class DeadLockSample {
	public final Object lock1 = new Object();    
    public final Object lock2 = new Object();    
    
    public void methodOne(){    
       synchronized(lock1){    
    	   System.out.println("do something while lock1 in methodOne");
          synchronized(lock2){
        	  System.out.println("do something while lock2 in methodOne");
          }    
       }    
    }        
    public void methodTwo(){    
       synchronized(lock2){    
    	   System.out.println("do something while lock2 in methodTwo");
          synchronized(lock1){
        	  System.out.println("do something while lock1 in methodTwo");
          }    
       }    
    }    
}
