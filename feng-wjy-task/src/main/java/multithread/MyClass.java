package multithread;

public class MyClass {

	// 某个类的范围，防止多个线程同时访问这个类中的synchronized static 方法。它可以对类的所有对象实例起作用。
	//（注：这个可以认为是对Class对象起作用） 
	public synchronized static void StaticSyncMethod() {
	}

	// 某个对象实例内，可以防止多个线程同时访问这个对象的synchronized方法
	//（如果一个对象有多个synchronized方法，只要一个线程访问了其中的一个synchronized方法，其它线程不能同时访问这个对象中任何一个synchronized方法）。
	// 这时，不同的对象实例的synchronized方法是不相干扰的。也就是说，其它线程照样可以同时访问相同类的另一个对象实例中的synchronized方法； 
	public synchronized void SyncMethod() {
		// do something
	}

	public void OtherFunction() {
		//用于方法中的某个区块中，表示只对这个区块的资源实行互斥访问
		synchronized (this) {

		}
	}
}