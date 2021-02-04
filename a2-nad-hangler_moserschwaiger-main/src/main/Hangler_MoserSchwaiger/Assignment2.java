package Hangler_MoserSchwaiger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Read from files, execute the transactions and write log-files
 */
public class Assignment2 implements assignment2_int.TransactionPerformer{

    public Assignment2(){}

    private ArrayList<TransactionDetails> transactions = new ArrayList<>();
    private HashMap<String, Account> accounts = new HashMap<>();

    @Override
    public int performTransactions(String fileName) throws Exception {

        File file = new File (fileName);

        Scanner myReader = new Scanner(file);

        int accCnt = myReader.nextInt();
        createAccounts(accCnt,myReader);

        int transCnt = myReader.nextInt();

        storeTransactions(transCnt, myReader);

        executeTransaction();

        writeUpdatedBalancesToFile(fileName);
        writeTransactionLogToFile("General_Ledger.txt", false);
        writeTransactionLogToFile("General_Ledger_Justified_Formatting.txt", true);

        return negativBalancesCnt();
    }

    /**
     * Create an account object an stores in the hashmap
     * @param accCnt number of incoming accounts from the input file
     * @param myReader scanner which read already the input file
     */
    private void createAccounts(int accCnt, Scanner myReader){

        for(int i=0; i<accCnt; i++){
            String name = myReader.next();

            if(!accounts.containsKey(name))
                accounts.put(name,new Account(name,myReader.nextInt()));
            else
                System.out.println("Name does already exists, adding to accounts failed!");
        }
        //displayAccounts();
    }

    /**
     * Creat a transaction objects and store they in a arraylist
     *
     * @param transCnt number of incoming transactions from the input file
     * @param myReader scanner which read already the input file
     */
    private void storeTransactions(int transCnt, Scanner myReader){
        if (transCnt == 0)
            return;

        TransactionDetails td = null;
        transactions.add(new TransactionDetails(
                myReader.next(), myReader.next(), myReader.nextInt(), myReader.nextLong(), myReader.nextLine()));

        transCnt--;

        while (transCnt > 0) {
            td = new TransactionDetails(
                    myReader.next(), myReader.next(), myReader.nextInt(), myReader.nextLong(), myReader.nextLine());

            for (int i = 0; i < transactions.size(); i++) {
                if (transactions.get(i).getTimestamp() >= td.getTimestamp()) {
                    transactions.add(i,td);
                    break;
                }

                //store the transaction on the last position in the arraylist because of its timestamp
                if(i == transactions.size()-1) {
                    transactions.add(td);
                    break;
                }
            }
            transCnt--;
        }
        //displayTransactions();
    }

    /**
     * Execute all the transactions in the order, how it is stored in the list
     */
    private void executeTransaction() {
        for(int i=0; i<transactions.size(); i++){
            if(receiverAndSenderExists(i)) {
                setReceiversBalance(transactions.get(i).getReceiver(), i);
                setSendersBalance(transactions.get(i).getSender(), i);
            }
        }
    }

    /**
     * Write all accounts into a file with the new balances after all transactions
     *
     * @param fileName name of the input file
     * @throws IOException
     */
    private void writeUpdatedBalancesToFile(String fileName) throws IOException {
        String outFileName = "Results_" + getFileNameFromAbsolutPath(fileName);;

        FileWriter writer = new FileWriter(outFileName);
        writer.write(accounts.size() + "\n");

        for(Map.Entry<String, Account> acc : accounts.entrySet()){
            writer.write(acc.getValue().getName() + " " + acc.getValue().getBalance() + "\n");
        }
        writer.close();
    }
    /**
     * Write the transactions log into a outputfile
     * @param outFileName name of the output file
     * @param justification is 'true' if the description of a transaction should be write in justification
     * @throws IOException
     */
    private void writeTransactionLogToFile(String outFileName, boolean justification) throws IOException {

        FileWriter writer = new FileWriter(outFileName);
        writer.write(transactions.size() + "\n");

        for(TransactionDetails t : transactions) {
            writer.write(t.getAmount() + " " + t.getSender() + " -> " + t.getReceiver() + "\n");
            if (t.getDescription().length() > 0)
                if (justification)
                    writer.write(formatDescriptionJustification(t.getDescription()));
                else
                    writer.write(formatDescription(t.getDescription()));
        }

        writer.close();
    }

    /**
     * Format the description to a text with max 35 characters in one line!
     *
     * The description is stored word by word in a string array. Then it is scanned
     * line by line and checked that the 35 characters are not exceeded per line.
     *
     * If the next word would exceed the 35 characters (incl. spaces) per line,
     * then a line break is made and the same is done with the next line until you
     * reach the end of the array.
     * @param desc
     * @return
     */
    private String formatDescription(String desc){
        String[] words = splitSentence(desc);
        StringBuilder out = new StringBuilder();
        int charCnt = 0;
        boolean firstWord = true;

        for(String s : words){

            if(firstWord) {
                charCnt += s.length();
                out.append("          " + s);
                firstWord = false;
            }

            else{
                //counts the chars per line
                charCnt += s.length() + 1; //+1 for space
                if(charCnt <= 35)
                    out.append(" " + s);
                else {
                    out.append(System.getProperty("line.separator") + "          " + s);
                    charCnt = s.length();
                }
            }
        }

        //when a description exists, then a new line is made
        if(out.toString().length() > 0)
            out.append(System.getProperty("line.separator"));

        return out.toString();
    }

    /**
     * Format the description to a justified text with 35 characters in one line!
     *
     * The description is stored word by word in a string array. Then it is scanned
     * line by line and checked that the 35 characters are not exceeded per line.
     * For each line the method makeJustifiedLine is called to justify the line.
     * By default the last line is not justified but normal.
     *
     * @param description of the specific transaction
     * @return the justified description text
     */
    private String formatDescriptionJustification(String description) {
        String[] words = splitSentence(description);
        StringBuilder out = new StringBuilder();
        int charCnt = 0;
        int charsPerLine = 35;
        int startIndex = 0;
        boolean firstWord = true;

        //lines with blockset
        for(int i=0; i<words.length; i++){
            charCnt += words[i].length();
            if(charCnt > charsPerLine){
                out.append("          " +
                        makeJustifyLine(startIndex, i-1,words,charCnt-words[i].length()-1,charsPerLine) + "\n");
                charCnt = words[i].length();
                startIndex = i;
            }
            charCnt++; //+1 for the Space after a word
        }

        //last line of description, is not justified
        for(int i=startIndex; i< words.length; i++){
            if(firstWord) {
                out.append("          " + words[i]);
                firstWord = false;
            }
            else
                out.append(" " + words[i]);
        }

        //when a description exists, then a new line is made
        if(out.toString().length() > 0)
            out.append(System.getProperty("line.separator"));

        return out.toString();
    }

    /**
     * This methode format a part of a sentence to a blockset line (35 characters in one line)
     * first it is calculated how many spaces have to be added (additional to the 'normal' spaces between the words)
     *
     * In the while loop the additional spaces are inserted between the words and additionally it calculates how
     * many spaces are still missing and thus how many spaces have to be added next to have an even distribution
     * of the spaces at the end.
     *
     * @param startIndex with which word (index of the array words) starts this line
     * @param endIndex with which word the line ends
     * @param words array with all words of the description
     * @param charCnt number of chars of all words together (needed to fill up the rest with spaces to get 35 chars per line)
     * @param charsPerLine how many chars (incl. spaces) per line max
     * @return justified line
     */
    private String makeJustifyLine(int startIndex, int endIndex, String[] words, int charCnt, int charsPerLine) {
        StringBuilder out = new StringBuilder();
        boolean firstWord = true;

        //how many additional spaces are needed for a blockset
        int addSpaceCnt = charsPerLine - charCnt;
        //how many spaces per space have to be added, to get a good distribution
        int spacesPerSpace = roundUp(addSpaceCnt, (endIndex - startIndex));
        int tmpSpacesPerSpace = spacesPerSpace;


        for(int i=startIndex; i <= endIndex; i++)
        {
            if(firstWord) {
                out.append(words[i]);
                firstWord = false;
            }
            else {
                if (addSpaceCnt > 0) {

                    //add spaces per space
                    while (tmpSpacesPerSpace > 0) {
                        out.append(" ");
                        tmpSpacesPerSpace--;
                        addSpaceCnt--;
                    }

                    //calculates the number of spaces to be added to the next space
                    if(addSpaceCnt > 0) {
                        tmpSpacesPerSpace = roundUp(addSpaceCnt, (endIndex - i));
                    }
                }
                out.append(" " + words[i]);
            }
        }
        return out.toString();
    }

    /**
     * Split a sentence by every space an stores it in a string
     * @param s string which get splitted
     * @return string array which stores every word seperate
     */
    private String[] splitSentence(String s){
        if(s.charAt(0) == ' ')
            s= s.substring(1);

        String[] out = s.split(" ");

        return out;
    }

    /**
     * Counts the number of arrays with negative balance
     * @return number of accounts with negative balance
     */
    private int negativBalancesCnt() {
        int cnt=0;

        for(Map.Entry<String, Account> acc : accounts.entrySet()){
            if(acc.getValue().getBalance() < 0)
                cnt++;
        }
        return cnt;
    }

    /**
     * Set the new receiver balance after a transaction
     * @param name of the receiver
     * @param i index of the transaction in the arraylist
     */
    private void setReceiversBalance(String name, int i){
        accounts.get(name).setBalance(
                accounts.get(name).getBalance() + transactions.get(i).getAmount());
    }

    /**
     * Set the new sender balance after a transaction
     * @param name of the sender
     * @param i index of the transaction in the arraylist
     */
    private void setSendersBalance(String name, int i){
        accounts.get(name).setBalance(
                accounts.get(name).getBalance() - transactions.get(i).getAmount());
    }

    /**
     * Extract the file name (i.e. filename.txt) from the absolut path
     * @param absolutPath
     * @return file name
     */
    private String getFileNameFromAbsolutPath(String absolutPath){
        String[] path = absolutPath.split("/");

        return path[path.length-1];
    }

    /**
     * rount up a number to int when its not int (i.e. 1,1 gets 2)
     * @param num
     * @param divisor
     * @return
     */
    public int roundUp(int num, int divisor) {
        return (num + divisor - 1) / divisor;
    }

    /**
     * Contains if the receiver and the sender exists in the hashmap
     * @param i index from the transaction list
     * @return if receiver and sender exists
     */
    private boolean receiverAndSenderExists(int i){
        return accounts.containsKey(transactions.get(i).getReceiver()) && accounts.containsKey(transactions.get(i).getSender());
    }

    /**
     * displays all transactions from the arraylist in the console
     */
    private void displayTransactions() {
        for(TransactionDetails t : transactions){
            System.out.println("Receiver: " + t.getReceiver() + "  Sender: " + t.getSender() + "  Amount: " + t.getAmount()
                    + "  Timestamp: " + t.getTimestamp() + "  Description: " + t.getDescription());
        }
    }
    /**
     * displays all accounts from the hashmap in the console
     */
    private void displayAccounts() {
        for (Map.Entry<String, Account> acc : accounts.entrySet()) {
            System.out.println("Name: " + acc.getValue().getName()+ "  Balance: " + acc.getValue().getBalance());
        }
    }
}
