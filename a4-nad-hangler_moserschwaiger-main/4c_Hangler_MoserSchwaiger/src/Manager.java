import java.util.ArrayList;
import java.util.List;

public class Manager {
    private List<Account> accounts = new ArrayList<>();
    private List<Ident> idElems = new ArrayList<>();

    private Manager(){}

    public void addAccount(Account account){
        this.accounts.add(account);
        this.idElems.add(account);
    }
}
