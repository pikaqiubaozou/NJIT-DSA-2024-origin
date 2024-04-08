package oy.tol.tra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

public class DailyTasks {

   private QueueInterface<String> dailyTaskQueue = null;
   private Timer timer = null;
   private static final int TASK_DELAY_IN_SECONDS = 1 * 1000;

   private DailyTasks() {
   }

   public static void main(String[] args) {
      DailyTasks tasks = new DailyTasks();
      tasks.run();
   }

   private void run() {
      try {
         dailyTaskQueue = QueueFactory.createStringQueue();
         readTasks();
         timer = new Timer();
         timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
               if (!dailyTaskQueue.isEmpty()) {
                  String task = dailyTaskQueue.dequeue();
                  System.out.println(task);
               } else {
                  timer.cancel();
               }
            }
         }, TASK_DELAY_IN_SECONDS, TASK_DELAY_IN_SECONDS);
      } catch (IOException e) {
         System.out.println("Error reading tasks: " + e.getMessage());
      } finally {
         if (timer != null) {
            timer.cancel();
         }
      }
   }

   private void readTasks() throws IOException {
      InputStream inputStream = null;
      try {
         inputStream = getClass().getClassLoader().getResourceAsStream("DailyTasks.txt");
         if (inputStream != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String task;
            while ((task = reader.readLine()) != null) {
               dailyTaskQueue.enqueue(task);
            }
            System.out.println("Number of tasks: " + dailyTaskQueue.size());
         } else {
            throw new IOException("File not found: DailyTasks.txt");
         }
      } finally {
         if (inputStream != null) {
            inputStream.close();
         }
      }
   }
}