package assignment2_int;
/*
* Processes transactions as specified in file with name fileName
* Returns the number of accounts with negative balance after execution of the transactions in the file
*
*/

public interface TransactionPerformer {
   public int performTransactions(String fileName) throws Exception;
}
