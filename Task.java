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
        this.weight = 0.0;

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

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getInterest() {
        return interest;
    }

    public void setInterest(int interest) {
        this.interest = interest;
    }

}