import java.util.concurrent.Semaphore;

public class ReaderWriter {

    static Semaphore mutex = new Semaphore(1);
    static Semaphore wrt = new Semaphore(1);
    static String message = "Good Morning";
    static int count = 0;


    // Reader
    static class Reader implements Runnable
    {
        public void run()
        {
            try
            {
                mutex.acquire();
                count++;
                if(count == 1)
                {
                    wrt.acquire();   
                }
                mutex.release();

                System.out.println(Thread.currentThread().getName()+ " is Reading: "+ message);
                Thread.sleep(1500);
                System.out.println(Thread.currentThread().getName()+ " Reading Completed: "+ message);

                mutex.acquire();
                count--;
                if(count == 0)
                {
                    wrt.release();   
                }
                mutex.release();
            }
            catch( InterruptedException e)
            {
                System.out.println("Error Occured");
            }

        }//run()

    }// Reader class

    //Writer
    static class Writer implements Runnable
    {
        public void run()
        {
            try
            {
                wrt.acquire();
                message = "Good Evening";
                System.out.println(Thread.currentThread().getName() + " is WRITING");
                Thread.sleep(1500);
                System.out.println(Thread.currentThread().getName() + " finished WRITING");
                wrt.release();
            }
            catch( InterruptedException e)
            {
                System.out.println("Error Occured");
            }
        }//run()
    }// Writer class

    public static void main(String[] args)
    {
        Reader read = new Reader();
        Writer write = new Writer();

        Thread r1 = new Thread(read);
        r1.setName("read 1");
        Thread r2 = new Thread(read);
        r2.setName("read 2");
        Thread r3 = new Thread(read);
        r3.setName("read 3");

        Thread w1 = new Thread(write);
        w1.setName("write 1");
        Thread w2 = new Thread(write);
        w2.setName("write 2");
        Thread w3 = new Thread(write);
        w3.setName("write 3");

        r1.start();
        w1.start();
        r2.start();
        w2.start();
        r3.start();        
        w3.start();

    }
}// main class
