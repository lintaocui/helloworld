package gg;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by lintaocui on 1/13/16.
 * Implement a thread-safe timer class/delayed task scheduler
 * Design: need a thread to execute the scheduled tasks, that are stored in a priority queue.
 * Use monitor wait/notifyAll to wake up the executing thread (push)
 */
public class MyTimer {
    private PriorityQueue<Task> queue = new PriorityQueue<>();
    private TimerThread timerThread = new TimerThread(queue);

    public MyTimer() {
        timerThread.setDaemon(false);
        timerThread.start();
    }

    public void schedule(Task task) {
        synchronized (queue) {
            if(timerThread.stopped)
                throw new IllegalStateException("Timer is already cancelled");
            queue.add(task);
            if (queue.peek() == task)
                queue.notify();
            System.out.println("schedule task: " + task.name + " to run at " + task.scheduledTime);
        }
    }

    public void stop() {
        synchronized (queue)
        {
            timerThread.stopped = true;
            queue.clear();
            queue.notify();
        }
    }

    public static class Task implements Runnable, Comparable<Task> {
        LocalTime scheduledTime;
        String name;

        public Task(long delayInSeconds, String name) {
            scheduledTime = LocalTime.now().plusSeconds(delayInSeconds);
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("Task " + name + " is scheduled at" + LocalTime.now());
        }

        @Override
        public int compareTo(Task o) {
            return this.scheduledTime.compareTo(o.scheduledTime);
        }
    }

    class TimerThread extends Thread {

        private PriorityQueue<Task> queue;

        volatile boolean stopped = false;

        TimerThread(PriorityQueue<Task> queue) {
            this.queue = queue;
        }

        @Override
        // This is a long-running thread, that schedules tasks
        public void run() {
            Task task;
            System.out.println("Timer started.");
            while(!stopped) {
                try{
                    synchronized (queue) {
                        while(queue.isEmpty() && !stopped) {
                            queue.wait();
                            System.out.println("Timer wake up at " + LocalTime.now());
                        }

                        if(stopped) break;

                        int delay = queue.peek().scheduledTime.getSecond() - LocalTime.now().getSecond();
                        if(delay <= 0) {
                            task = queue.poll();
                        }
                        else {
                            task = null;
                            queue.wait(delay * 1000);
                        }
                    }

                    if(task == null) continue;

                    task.run();
                } catch (InterruptedException e) {
                }
            }

            System.out.println("Timer exited!");
        }
    }
}
