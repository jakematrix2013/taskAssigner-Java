public class Task {
    private String name;
    private int importance;
    private int difficulty;
    private int interest;
    private double weight;

    public Task() {
        this.name = "";
        this.importance = 0;
        this.difficulty = 0;
        this.interest = 0;
        this.weight = 0;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImportance() {
        return importance;
    }

}