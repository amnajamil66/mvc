import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskManagerApp {

    // Model: Task class with attributes and methods
    static class Task {
        private int id;
        private String title;
        private String description;
        private boolean isCompleted;

        // Constructor
        public Task(int id, String title, String description) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.isCompleted = false; // Default to not completed
        }

        // Getters and Setters
        public int getId() { return id; }
        public String getTitle() { return title; }
        public String getDescription() { return description; }
        public boolean isCompleted() { return isCompleted; }
        public void setCompleted(boolean completed) { isCompleted = completed; }
    }

    // View: TaskView class to display tasks and messages
    static class TaskView {
        public void displayTasks(List<Task> tasks) {
            if (tasks.isEmpty()) {
                System.out.println("No tasks available.");
            } else {
                for (Task task : tasks) {
                    System.out.println("ID: " + task.getId() + ", Title: " + task.getTitle() +
                                       ", Description: " + task.getDescription() +
                                       ", Completed: " + (task.isCompleted() ? "Yes" : "No"));
                }
            }
        }

        public void displayMessage(String message) {
            System.out.println(message);
        }
    }

    // Controller: TaskController class to handle task logic
    static class TaskController {
        private List<Task> tasks = new ArrayList<>();
        private TaskView view = new TaskView();
        private int nextId = 1;

        public void addTask(String title, String description) {
            Task task = new Task(nextId++, title, description);
            tasks.add(task);
            view.displayMessage("Task added successfully.");
        }

        public void displayTasks() {
            view.displayTasks(tasks);
        }

        public void markTaskAsCompleted(int id) {
            for (Task task : tasks) {
                if (task.getId() == id) {
                    task.setCompleted(true);
                    view.displayMessage("Task marked as completed.");
                    return;
                }
            }
            view.displayMessage("Task not found.");
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        TaskController controller = new TaskController();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Task");
            System.out.println("2. Display Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    controller.addTask(title, description);
                    break;
                case 2:
                    controller.displayTasks();
                    break;
                case 3:
                    System.out.print("Enter task ID to mark as completed: ");
                    int id = scanner.nextInt();
                    controller.markTaskAsCompleted(id);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}

