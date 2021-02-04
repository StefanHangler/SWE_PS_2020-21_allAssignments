public class Account implements Ident{
    private static int AccountCounts=0;
    private String name;
    private double money;
    private Manager manager;

    public Account(String name){
        this.name = name;
        AccountCounts++;
    }

    @Override
    public long getID() {
        return name.hashCode();
    }

    public void deposit(double value){
        this.money += value;
    }

    public void withDraw(double value){
        this.money -= value;
    }

    public String getName() {
        return name;
    }

    //Additional Code for testing
    public double displayBalance(){
        return this.money;
    }
}
