import java.util.Scanner;
import java.util.List;
//import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        // List<Task> tasks = new ArrayList<>();

        TaskAssigner taskAssigner = new TaskAssigner();

        System.out.print("Do you wanna load up the task list? 1 - yes, 0 - no");
        int loadChoice = scanner.nextInt();

        if (loadChoice == 1) {

            List<Task> loadedTasks = TaskFileManager.loadTasksFromFile("tasks.txt");
            if (loadedTasks.isEmpty()) {
                System.out.println("No tasks were loaded. Exiting the program.");
                scanner.close();
                return; // Exit the program if no tasks were loaded
            }
            for (Task task : loadedTasks) {
                taskAssigner.addTask(task);
            }

        }

        int choice = -1;
        do {
            try {
                // Display menu
                System.out.println("\nMenu:");
                System.out.println("0. Quit");
                System.out.println("1. Add a task");
                System.out.println("2. List tasks");
                System.out.println("3. Choose a random task");
                System.out.println("4. Choose a task based on its weight");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character?

                switch (choice) {

                    case 0:
                        // Asking the user if they want to save the tasks to a file
                        System.out.print("Do you want to save the current task list? 1 - yes, 0 - no: ");
                        int saveChoice = scanner.nextInt();
                        if (saveChoice == 1) {
                            TaskFileManager.saveTasksToFile(taskAssigner.getTasks(), "tasks.txt");
                        }
                        System.out.println("Exiting.");
                        break;

                    case 1:
                        System.out.print("Enter task name: ");
                        String taskName = scanner.nextLine();
                        int importance = 0;
                        int difficulty = 0;
                        int interest = 0;

                        while (taskAssigner.taskNameTaken(taskName)) {
                            System.out.println("Task name taken, re-enter");
                            System.out.print("Enter task name: ");
                            taskName = scanner.nextLine();
                        }

                        // Accept difficulty, importance, and interest values
                        while (importance < 1 || importance > 3) {
                            System.out.print("Enter importance (1-3): ");
                            importance = scanner.nextInt();
                            if (importance < 1 || importance > 3) {
                                System.out.println("Invalid input. Please enter a value between 1 and 3.");
                            }
                        }

                        while (difficulty < 1 || difficulty > 3) {
                            System.out.print("Enter difficulty (1-3): ");
                            difficulty = scanner.nextInt();
                            if (difficulty < 1 || difficulty > 3) {
                                System.out.println("Invalid input. Please enter a value between 1 and 3.");
                            }
                        }

                        while (interest < 1 || interest > 3) {
                            System.out.print("Enter interest (1-3): ");
                            interest = scanner.nextInt();
                            if (interest < 1 || interest > 3) {
                                System.out.println("Invalid input. Please enter a value between 1 and 3.");
                            }
                        }

                        scanner.nextLine(); // Consume newline character after reading integers

                        Task newTask = new Task();
                        newTask.setName(taskName);
                        newTask.setDifficulty(difficulty);
                        newTask.setImportance(importance);
                        newTask.setInterest(interest);
                        taskAssigner.calculateWeight(newTask);
                        taskAssigner.addTask(newTask);

                        System.out.println("Task '" + taskName + "' added successfully!");

                        break;

                    case 2:
                        System.out.println("List of tasks:");
                        List<Task> currentTasks = taskAssigner.getTasks();
                        for (Task task : currentTasks) {
                            System.out.println(task.getName() + " (Weight: " + task.getWeight() + ")");
                        }
                        break;

                    case 3:
                        List<Task> availableTasks = taskAssigner.getTasks();
                        if (availableTasks.isEmpty()) {
                            System.out.println("No tasks available.");
                        } else {
                            Task randomTask = availableTasks.get(random.nextInt(availableTasks.size()));
                            System.out.println("Randomly chosen task: " + randomTask.getName());
                        }
                        break;

                    case 4:
                        List<Task> tasks = taskAssigner.getTasks();
                        if (tasks.isEmpty()) {
                            System.out.println("No tasks available.");
                        } else {
                            Task chosenTask = taskAssigner.chooseWeightedTask();
                            System.out.println("Algorithmically chosen task: " + chosenTask.getName());
                        }
                        break;

                    default:
                        System.out.println("Invalid, read instructions bro wtf");
                        break;

                }

            } catch (Exception e) {
                System.out.println("Error: Invalid input. Please enter a valid integer option.");
                scanner.nextLine(); // Consume the invalid input
                choice = -1; // Reset choice to ensure the loop continues
            }

        } while (choice != 0);

        scanner.close();

    }
}