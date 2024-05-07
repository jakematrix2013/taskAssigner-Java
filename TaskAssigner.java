
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

    public void addTask(Task task) {
        tasks.add(task);
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

}
