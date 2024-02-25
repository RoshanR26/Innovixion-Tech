import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;

class Task {
    private String name;
    private Date dueDate;
    private int priority;

    public Task(String name, Date dueDate, int priority) {
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "Task: " + name + ", Due Date: " + sdf.format(dueDate) + ", Priority: " + priority;
    }
}

class TaskScheduler {
    private Timer timer;
    private TimerTask notificationTask;

    public TaskScheduler() {
        this.timer = new Timer(true);
    }

    public void scheduleTaskWithNotification(Task task) {
        System.out.println("Task scheduled: " + task);
        timer.schedule(new NotificationTask(task), task.getDueDate());
    }

    public void cancelTasks() {
        timer.cancel();
    }

    private class NotificationTask extends TimerTask {
        private Task task;

        public NotificationTask(Task task) {
            this.task = task;
        }

        @Override
        public void run() {
            System.out.println("Notification: Task '" + task.getName() + "' is due!");
        }
    }
}

public class TaskSchedulerWithNotification {
    public static void main(String[] args) {
        TaskScheduler taskScheduler = new TaskScheduler();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Schedule Task\n2. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter task name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter due date (yyyy-MM-dd HH:mm:ss): ");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date dueDate;
                    try {
                        dueDate = sdf.parse(scanner.nextLine());
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd HH:mm:ss.");
                        continue;
                    }
                    System.out.print("Enter priority: ");
                    int priority = scanner.nextInt();

                    Task newTask = new Task(name, dueDate, priority);
                    taskScheduler.scheduleTaskWithNotification(newTask);
                    break;

                case 2:
                    System.out.println("Exiting the Task Scheduler.");
                    taskScheduler.cancelTasks();
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }
}