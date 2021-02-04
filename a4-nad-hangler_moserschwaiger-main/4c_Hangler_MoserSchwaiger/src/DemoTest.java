public class DemoTest {
    public static void main(String[] args) {
        Manager m1 = null; //new Manager(); geht nur wenn der public is
        StudentAccount stefan = new StudentAccount("Stefan");
        StudentAccount marie = new StudentAccount("Marie");
        StudentAccount stefan2 = new StudentAccount("Stefan2");
        StudentAccount marie2 = new StudentAccount("Marie2");

        m1.addAccount(stefan);
        m1.addAccount(marie);
        m1.addAccount(stefan2);
        m1.addAccount(marie2);

        stefan.deposit(100);
        System.out.println("ID von Stefan: " + stefan.getID());
        System.out.println("ID von Marie: " + marie.getID());
        System.out.println("ID von Stefan2: " + stefan2.getID());
        System.out.println("ID von Marie2: " + marie2.getID());
        stefan.withDraw(30);

        System.out.println("Balance: 100-30 = " + stefan.displayBalance());
    }
}
