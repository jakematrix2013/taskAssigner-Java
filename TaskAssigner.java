
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TaskAssigner {
    private Random rand;
    private List<Task> tasks;

    public TaskAssigner() {
        rand = new Random();
        tasks = new ArrayList<>();
    }

    public boolean addTask(Task task) {
        if (!taskNameTaken(task.getName())) {
            tasks.add(task);
            return true;
        }
        return false;
    }

    public boolean taskNameTaken(String taskName) {
        for (Task task : tasks) {
            if (task.getName().equalsIgnoreCase(taskName))
                return true;
        }
        return false;
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Task getRandomTask() {
        return tasks.get(rand.nextInt(tasks.size()));
    }

    public void calculateWeight(Task task) {
        double importanceWeight = task.getImportance() * 0.6;
        double difficultyWeight = task.getDifficulty() * 0.3;
        double interestWeight = task.getInterest() * 0.1;

        // Base weight calculation
        double baseWeight = (importanceWeight + difficultyWeight + interestWeight) * (100.0 / 3.0);

        // Add some randomness to the weight (+/- 5%)
        double randomFactor = (rand.nextDouble() * 0.1) - 0.05;
        double finalWeight = baseWeight * (1 + randomFactor);

        // Ensure the weight is within the 0-100 range
        finalWeight = Math.max(0, Math.min(100, finalWeight));

        task.setWeight(finalWeight);

    }

    public Task chooseWeightedTask() {
        if (tasks.isEmpty()) {
            return null;
        }

        Task highestWeightTask = tasks.get(0);
        double highestWeight = highestWeightTask.getWeight();

        for (Task task : tasks) {
            if (task.getWeight() > highestWeight) {
                highestWeightTask = task;
                highestWeight = task.getWeight();
            }
        }

        return highestWeightTask;
    }

}
