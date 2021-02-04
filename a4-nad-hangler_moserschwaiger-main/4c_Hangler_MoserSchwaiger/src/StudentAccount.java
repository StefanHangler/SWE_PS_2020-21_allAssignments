public class StudentAccount extends Account{

    public StudentAccount(String name) {
        super(name);
        University u = new University();
    }

    @Override
    public void withDraw(double value) {
        super.withDraw(value);
    }
}
