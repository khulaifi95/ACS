package queue;

import java.util.ArrayList;
import java.util.List;

public class MainBQ1
{
	public static final int NUM_ENQUEUERS             = 1;
	public static final int NUM_DEQUEUERS             = 5;
	public static final int NUM_MESSAGES_PER_ENQUEUER = 10;
	public static final int NUM_MESSAGES_PER_DEQUEUER = 3;

	public static void main(String[] args)
	{

		ArrayList<Thread> threads = new ArrayList<Thread>(NUM_DEQUEUERS + NUM_ENQUEUERS);
		Queue<String> queue = new BadQueue1<String>();
		int nextThreadId = 0;

		for (int i = 0; i < NUM_ENQUEUERS; i++)
		{
			Runnable enq = new Enqueuer(nextThreadId, queue, NUM_MESSAGES_PER_ENQUEUER);
			Thread t = new Thread(enq);
			threads.add(nextThreadId, t);
			nextThreadId++ ;
		}

		for (int i = 0; i < NUM_DEQUEUERS; i++)
		{
			Runnable deq = new Dequeuer(nextThreadId, queue, NUM_MESSAGES_PER_DEQUEUER);
			Thread t = new Thread(deq);
			threads.add(nextThreadId, t);
			nextThreadId++ ;
		}
		
		// start all the threads
		try
		{
			Thread.sleep(10);
		}
		catch (InterruptedException e1)
		{
			e1.printStackTrace();
		}
		for (Thread t : threads)
		{
			t.start();
		}
		
		for (Thread t : threads)
		{
			try
			{
				t.join();
			}
			catch (InterruptedException e)
			{
				// if sleep is interrupted, just ignore it and continue
			}
		}
		System.out.println("Finished");
	}
}

class Enqueuer implements Runnable
{
	private int           threadId;
	private Queue<String> queue;
	private int           num_messages;

	public Enqueuer(int threadId, Queue<String> queue, int num_messages)
	{
		this.threadId = threadId;
		this.queue = queue;
		this.num_messages = num_messages;
	}

	public void run()
	{
		System.out.println("Enqueuer " + threadId);
		for (int i = 0; i < num_messages; i++)
		{
			String m = String.format("Message %d from enqueuer %d", i, threadId);
			System.out.println("Adding: " + m);
			queue.enq(m);
		}
	}
}

class Dequeuer implements Runnable
{
	private int           threadId;
	private Queue<String> queue;
	private int           num_messages;

	public Dequeuer(int threadId, Queue<String> queue, int num_messages)
	{
		this.threadId = threadId;
		this.queue = queue;
		this.num_messages = num_messages;
	}

	public void run()
	{
		System.out.println("Dequeuer " + threadId);

		for (int i = 0; i < num_messages; i++)
		{
			String m = queue.deq();
			System.out.format("Dequeuer %d processed %s\n", threadId, m);
			try
			{
				Thread.sleep(10);
			}
			catch (InterruptedException e)
			{
				// if sleep is interrupted, just ignore it and continue
			}
		}
	}
}

interface Queue<T>
{
	public abstract void enq(T x);

	public abstract T deq();
}

class BadQueue1<T> implements Queue<T>
{
	public static final int QSIZE = 10;
	private int             head  = 0;
	private int             tail  = 0;

	List<T>                 items;
	
	public BadQueue1()
	{
		items = new ArrayList<T>(QSIZE);
		// We need to make sure that the list not just has the capacity for QSIZE elements, but that is actually
		// HAS QSIZE elements, since we are simulating an array with it
		for (int i = 0; i < QSIZE; i++)
			items.add(null);
	}

	public void enq(T x)
	{
		items.set((tail++) % QSIZE, x);
	}

	public T deq()
	{
		return items.get((head++) % QSIZE);
	}

}
