package runable;
class MyThread2 implements Runnable
{
    private int ticketsCont=5; //一共有5张火车票
    
    @Override
    public void run(){    
            while(true){
                 synchronized(this){
                    if(ticketsCont<=0){
                        break;
                    }
                    ticketsCont--; //如果还有票，就卖掉一张票
                    
                    System.out.println(Thread.currentThread().getName()+"卖掉了1张票，剩余票数为:"+ticketsCont);
                    
                    try{
                        Thread.sleep(2000);  //通过阻塞程序来查看效果
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }

                }
            }
        
    }



//    @Override  //不加同步锁
//    public void run(){
//        while(ticketsCont>0){
//            ticketsCont--; //如果还有票，就卖掉一张票
//            System.out.println(Thread.currentThread().getName()+"卖掉了1张票，剩余票数为:"+ticketsCont);
//            try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        }
//    }
}

public class TicketsRunnable
{
    public static void main(String args[]){
        
        MyThread2 mt=new MyThread2();
        //创建三个线程来模拟三个售票窗口
        Thread th1=new Thread(mt,"窗口1");
        Thread th2=new Thread(mt,"窗口2");
        Thread th3=new Thread(mt,"窗口3");

        //启动三个线程，也即是三个窗口，开始卖票
        th1.start();
        th2.start();
        th3.start();
        

    }
}