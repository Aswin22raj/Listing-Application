import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ToDoList {
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.println("Enter a command (add/view/remove/complete/exit):");
            command = scanner.nextLine().trim().toLowerCase();

            if (command.equals("add")) {
                addTask(scanner);
            } else if (command.equals("view")) {
                viewTasks();
            } else if (command.equals("remove")) {
                removeTask(scanner);
            } else if (command.equals("complete")) {
                completeTask(scanner);
            } else if (command.equals("exit")) {
                break;
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }

        scanner.close();
    }

    private static void addTask(Scanner scanner) {
        System.out.println("Enter the task description:");
        String description = scanner.nextLine();
        tasks.add(new Task(description));
        System.out.println("Task added.");
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private static void removeTask(Scanner scanner) {
        viewTasks();
        System.out.println("Enter the task number to remove:");
        int taskNumber = getValidIntegerInput(scanner);

        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            System.out.println("Invalid task number.");
        } else {
            tasks.remove(taskNumber - 1);
            System.out.println("Task removed.");
        }
    }

    private static void completeTask(Scanner scanner) {
        viewTasks();
        System.out.println("Enter the task number to mark as completed:");
        int taskNumber = getValidIntegerInput(scanner);

        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            System.out.println("Invalid task number.");
        } else {
            tasks.get(taskNumber - 1).markAsCompleted();
            System.out.println("Task marked as completed.");
        }
    }

    private static int getValidIntegerInput(Scanner scanner) {
        int input = -1;
        boolean valid = false;

        while (!valid) {
            try {
                input = scanner.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number:");
                scanner.next();
            }
        }

        scanner.nextLine();
        return input;
    }
}
