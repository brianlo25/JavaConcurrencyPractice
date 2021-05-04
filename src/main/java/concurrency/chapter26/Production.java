package concurrency.chapter26;

public class Production extends InstructionBook{
    private final int productID;

    public Production(int productID) {
        this.productID = productID;
    }

    @Override
    protected void firstProcess() {
        System.out.println("execute the " + productID + " first process.");
    }

    @Override
    protected void secondProcess() {
        System.out.println("execute the " + productID + " second process.");
    }
}
