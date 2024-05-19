
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

    }

}
