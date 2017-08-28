package multithread;

import java.util.Random;
import org.joda.time.LocalTime;

//ThreadLocal为每一个线程都提供了变量的副本，使得每个线程在某一时间访问到的并不是同一个对象，这样就隔离了多个线程对数据的数据共享。
//Synchronized用于线程间的数据共享，而ThreadLocal则用于线程间的数据隔离。两者处于不同的问题域
//ThreadLocal通过一个Map来为每个线程都持有一个变量副本，用ThreadLocal对象以键值对的方式来维护这些线程独立变量 。
public class ThreadLocalDemo implements Runnable {
	private final static ThreadLocal studentLocal = new ThreadLocal();
	Student student = new Student();

	// 这个类有一个Student的私有变量,在run方法中，它随机产生一个整数。然后设置到student变量中,从student中读取设置后的值。然后睡眠5秒钟，最后再次读student的age值。
	public void run() {
		// TODO Auto-generated method stub
		accessStudent1();
		//accessStudent2();
		//accessStudent3();
	}

	static void println(String msg) {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println(new LocalTime() + ": thread " + currentThreadName + " : " + msg);
	}

	// 0、多线程访问时将出现并发问题,线程中设定的age值被其他线程修改.
	public void accessStudent() {		
		println(" is running!");
		Random random = new Random();
		int age = random.nextInt(100);
		println(" set age to:" + age);
		student.setAge(age);
		println(" first  read age is:" + student.getAge());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		println(" second read age is:" + student.getAge());

	}

	// 1、使用ThreadLocal处理多线程访问的并发问题.
	public void accessStudent1() {
		println(" is running!");
		Random random = new Random();
		int age = random.nextInt(100);
		println(" set age to:" + age);
		Student student = getStudent(); // 每个线程都独立维护一个Student变量
		student.setAge(age);
		println(" first  read age is:" + student.getAge());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		println(" second read age is:" + student.getAge());

	}

	// 2、使用synchronized给方法加锁，处理多线程访问的并发问题，将导致性能大幅下降.
	public synchronized void accessStudent2() {
		println(" is running!");
		Random random = new Random();
		int age = random.nextInt(100);
		println(" set age to:" + age);
		this.student.setAge(age);
		println(" first  read age is:" + student.getAge());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		println(" second  read age is:" + student.getAge());
	}

	// 3、使用synchronized只给student加锁，处理多线程访问的并发问题，性能提升.
	public void accessStudent3() {
		println(" is running!");
		synchronized (this) {
			Random random = new Random();
			int age = random.nextInt(100);
			println(" set age to:" + age);			
			this.student.setAge(age);
			println(" first  read age is:" + student.getAge());

			try {
				Thread.sleep(3000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			println(" second  read age is:" + student.getAge());
		}
	}

	protected Student getStudent() {
		Student student = (Student) studentLocal.get(); // 从ThreadLocal对象中取
		if (student == null) {
			student = new Student();
			studentLocal.set(student); // 如果没有就创建一个
		}
		return student;
	}

	protected void setStudent(Student student) {
		studentLocal.set(student); // 放入ThreadLocal对象中
	}

	/*
	public static void main(String[] agrs) {
		ThreadLocalDemo td = new ThreadLocalDemo();
		Thread t1 = new Thread(td, "a");
		Thread t2 = new Thread(td, "b");
		t1.start();
		t2.start();
	}
	*/

}
