package runable;

import java.io.*;
import java.util.Scanner;

class Daemon  implements Runnable
{
    @Override
    public void run(){
        System.out.println("进入守护线程");
        try{
            writeToFile();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        System.out.println("退出守护线程");
    }


    private void writeToFile() throws Exception{
            File filename=new File("C:\\Users\\lenovo\\Desktop\\test.txt");
            OutputStream os=new FileOutputStream(filename,true);
            int count=0;
            while(count<9){
                os.write(("\r\nword"+count).getBytes());
                System.out.println("守护线程"+Thread.currentThread().getName()+"向文件中写入word"+count++);
                //Thread.sleep(1000);
            }
    }
}

public class DaemonThreadDemo
{
    public static void main(String args[]){
        System.out.println("进入主线程"+Thread.currentThread().getName());
        Daemon daemonThread=new Daemon();
        Thread thread =new Thread(daemonThread);
        thread.setDaemon(true);
        thread.start();

        Scanner sc=new Scanner(System.in);
        sc.next();

        System.out.println("退出主线程"+Thread.currentThread().getName());

        
    }
}