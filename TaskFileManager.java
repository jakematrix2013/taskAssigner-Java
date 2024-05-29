import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class TaskFileManager {

    public static void saveTasksToFile(List<Task> tasks, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Task task : tasks) {
                writer.write(task.getName() + "," + task.getImportance() + "," + task.getDifficulty()
                        + "," + task.getInterest() + "," + task.getWeight() + "\n");
            }
            System.out.println("Tasks saved successfully to " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to " + filename);
            e.printStackTrace();
        }
    }

    public static List<Task> loadTasksFromFile(String filename) {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0];
                    int importance = Integer.parseInt(parts[1]);
                    int difficulty = Integer.parseInt(parts[2]);
                    int interest = Integer.parseInt(parts[3]);
                    double weight = Double.parseDouble(parts[4]);

                    Task task = new Task();
                    task.setName(name);
                    task.setImportance(importance);
                    task.setDifficulty(difficulty);
                    task.setInterest(interest);
                    task.setWeight(weight);
                    tasks.add(task);
                }
            }
            System.out.println("Tasks loaded successfully from " + filename);
        } catch (FileNotFoundException e) {
            System.out.println("The file " + filename + " was not found.");
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks from " + filename);
            e.printStackTrace();
        }
        return tasks;
    }

}