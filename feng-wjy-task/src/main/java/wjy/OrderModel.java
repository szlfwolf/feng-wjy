package wjy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OrderModel {
	public static final Map<Integer,OrderDao> Orders = new ConcurrentHashMap<Integer,OrderDao>();

}
