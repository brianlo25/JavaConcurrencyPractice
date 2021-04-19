package concurrency.chapter16;

public class TablewarePair {

    private final Tableware lefTool;

    private final Tableware rightTool;

    public TablewarePair(Tableware lefTool, Tableware rightTool) {
        this.lefTool = lefTool;
        this.rightTool = rightTool;
    }

    public Tableware getLefTool() {
        return lefTool;
    }

    public Tableware getRightTool() {
        return rightTool;
    }
}
