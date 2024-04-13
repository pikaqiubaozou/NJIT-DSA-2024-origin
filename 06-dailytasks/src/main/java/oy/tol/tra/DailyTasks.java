package oy.tol.tra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A class showing your daily schedule using a timer.
 */
public class DailyTasks {

   private QueueInterface<String> dailyTaskQueue = null;
   private Timer timer = null;
   private static final int TASK_DELAY_IN_SECONDS = 1 * 1000;

   private DailyTasks() {
   }

   /**
    * Execute from the command line:  <code>java -jar target/04-queue-1.0-SNAPSHOT-jar-with-dependencies.jar</code>
    * @param args Not used.
    */
   public static void main(String[] args) {
      DailyTasks tasks = new DailyTasks();
      tasks.run();
   }

   private void run() {
      try {
         // Create a queue (to the member variable!) for daily tasks, which are strings.
         dailyTaskQueue = new QueueImplementation<String>();
         // Read the tasks for today by calling readTasks() -- implementing missing parts of it!
         readTasks();
         // Create Java Timer object (to member variable) to schedule your daily tasks. (Already given to you.)
         timer = new Timer();
         // Schedule the timer at fixed rate with a new TimerTask,
         // using the delay constant values in the class member variable. (Already given to you.)
         timer.scheduleAtFixedRate(new TimerTask() {
            // In the timer task run:
            @Override
            public void run() {
               // Check if there are tasks in the queue:
               if (!dailyTaskQueue.isEmpty()) {
                  // If yes, print the task from the queue, dequeueing it.
                  String task = dailyTaskQueue.dequeue();
                  System.out.println("Task for today: " + task);
               } else {
                  // If not, cancel the timer.
                  timer.cancel();
               }
            }
         }, TASK_DELAY_IN_SECONDS, TASK_DELAY_IN_SECONDS);
      } catch (IOException e) {
         System.out.println("Something went wrong :( " + e.getLocalizedMessage());
      }
   }

   private void readTasks() throws IOException {
      InputStream inputStream = getClass().getClassLoader().getResourceAsStream("DailyTasks.txt");
      if (inputStream != null) {
         BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
         String line;
         while ((line = reader.readLine()) != null) {
            // Enqueue the task to your Queue implementation:
            dailyTaskQueue.enqueue(line);
         }
         reader.close();
         // Print out to the console the number of tasks in the queue:
         System.out.println("Number of tasks in the queue: " + dailyTaskQueue.size());
      } else {
         throw new IOException("Failed to open DailyTasks.txt");
      }
   }
}