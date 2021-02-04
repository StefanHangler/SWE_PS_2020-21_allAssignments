package Hangler_MoserSchwaiger;

/**
 * Main-class, which call the Transaction-methode with the file-name
 */
public class Assignment2_BankAccount {
    public static void main(String[] args) throws Exception {
        Assignment2 a2 = new Assignment2();

        System.out.println("Numbers of Accounts with negative balance: " +
                a2.performTransactions("/Users/stefanhangler/Documents/Uni/3_Semester/Software Engineering/PS/Git_Repos_Hangler_MoserSchwaiger/a2-nad-hangler_moserschwaiger/src/main/Accounts_and_Transactions.txt"));
    }
}
