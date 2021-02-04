# A2_WS2020

------------------------------
Assignment 2: Bank manager
------------------------------

Implement a bank manager that processes transfers between accounts specified as transactions in a text file. The input file contains two parts: accounts and transactions. 

The account description starts with the number of accounts on the first line, followed by account details: one line for each account consisting of name of account holder (one string, no spaces) and account balance (integer).

The transactions section start immediately after the accounts section with one line containing the number of transactions. Each following line describes one transaction as follows:

```
<receiverAccountHolder> <senderAccountHolder> <amount> <timestamp> [<One line description text>]
```

The description is optional. 

For example the file ```Accounts_and_Transactions.txt``` has the following content: 

```
2
Ben 50
Cathy 378
2
Cathy Ben 35 1506997700 Lorem Ipsum is simply a dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.
Cathy Ben 12 1506997759
```

Your bank manager shall read such a file and execute all transactions in (increasing) timestamp-order. You are asked to:

A. Create another file that has the account description with updated account balances (no transactions section). We assume that there is no overdraft limit for accounts.
The name of the resulting file should have the prefix ```Results_``` followed by the name of the input file. For example, the result of processing the above file is place in ```Results_Accounts_and_Transactions.txt``` :

```
2
Ben 3
Cathy 425
```

B. Log every transaction performed in the file ```General_Ledger.txt```, in order of execution. The file starts with the total number of transactions in the ledger, followed by a description of each transaction in the following format:

```
<amount> <sender> -> <receiver>
[<10 blanks><Description left-justified, max 35 characters>]
```

Thus, if a transaction has a description then this is printed with an indentation of 10 places and ragged-left with maximum 35 characters (after the indentation). This can span multiple consecutive lines (same format), as needed.   
For the above example, we have the output

```
2
35 Ben –> Cathy
          Lorem Ipsum is simply a dummy text 
          of the printing and typesetting 
          industry. Lorem Ipsum has been the 
          industry's standard dummy text ever 
          since the 1500s. 
12 Ben –> Cathy 
``` 

C. A third output file ```General_Ledger_Justified_Formatting.txt``` should provide the same information in a slightly different format: The optional description is formatted justified (Block-Satz) by inserting blanks (balanced). For example:

```
2
35 Ben –> Cathy
          Lorem  Ipsum is simply a dummy text 
          of  the  printing  and  typesetting 
          industry.  Lorem Ipsum has been the 
          industry's standard dummy text ever 
          since the 1500s. 
12 Ben –> Cathy 
``` 

D. Implement the interface given in the repository:

```
package assignment2_int;
/*
* Processes transactions as specified in file with name fileName
* Returns the number of accounts with negative balance after execution of the transactions in the file
*
*/

public interface TransactionPerformer {
   public int performTransactions(String fileName) throws Exception;
}
```

You may write as many classes as you need. The class that implements the assignment interface must be named **Assignment2** and must have only one constructor, namely the empty default constructor, as follows: 

```
package <your_team_name>;

/*
* Implements assignment interface
* ...
*
*/
public class Assignment2 ... {

  // empty default constructor, do not change
  public Assignment2(){
  
  }

// Your implementation here, no other constructors allowed   
   
}
```
