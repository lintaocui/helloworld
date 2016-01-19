package unittest;

import gg.MyTimer;
import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.*;

/**
 * Created by on 1/18/16.
 */
public class MyTimerTest {

    @Test
    public void testSchedule() throws Exception {
        MyTimer timer = new MyTimer();
        System.out.println(LocalTime.now());
        MyTimer.Task task2 = new MyTimer.Task(2, "task2");
        MyTimer.Task task5 = new MyTimer.Task(5, "task5");
        timer.schedule(task2);
        timer.schedule(task5);
        Thread.sleep(1000 * 5);
        timer.schedule(new MyTimer.Task(2, "task6"));
        Thread.sleep(1000 * 5);
        System.out.println("Stop timer");
        timer.stop();
        Thread.sleep(1000 * 5);
    }
}