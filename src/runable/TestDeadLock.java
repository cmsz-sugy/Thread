package runable;

public class TestDeadLock implements Runnable {
	 public int flag = 1;
	 static Object o1 = new Object(), o2 = new Object();
	 public void run() {
	        System.out.println("flag=" + flag);
	        //锁住o1，同时锁住了o2
	  if(flag == 1) {
	   //synchronized 锁方法
	   synchronized(o1) {
	    try {
	     Thread.sleep(500);
	    } catch (Exception e) {
	     e.printStackTrace();
	    }
	    synchronized(o2) {
	     System.out.println("1"); 
	    }
	   }
	  }
	  //锁住o2，同时锁住了o1
	  if(flag == 0) {
	   synchronized(o2) {
	    try {
	     Thread.sleep(500);
	    } catch (Exception e) {
	     e.printStackTrace();
	    }
	    synchronized(o1) {
	     System.out.println("0");
	    }
	   }
	  }
	 }
	 public static void main(String[] args) {
		  TestDeadLock td1 = new TestDeadLock();
		  TestDeadLock td2 = new TestDeadLock();
		  td1.flag = 1;
		  td2.flag = 0;
		  Thread t1 = new Thread(td1);
		  Thread t2 = new Thread(td2);
		  t1.start();
		  t2.start();
		  
		 }
		}

