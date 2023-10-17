import java.util.ArrayList;
public class User {
    private String fName;
    private String lName;
    private String userID;
    private String pin;


    private ArrayList<Account> accounts;

    // create a new user
    public User(String fName, String lName, String pin, Bank  theBank){
        this.fName = fName;
        this.lName = lName;
        this.pin = pin;

        // for new unique user id
        this.userID = theBank.getNewUserId();

        this.accounts = new ArrayList<Account>();

        System.out.printf("This account of %s %s with userId %s", fName, lName, userID);
    }

    public void addAccount(Account anAcc){
        this.accounts.add(anAcc);
    }

    public String getuserId(){
        return this.userID;
    }

    // check a given pin matches the true User pin
    public boolean validatePin(String aPin){
        return Integer.parseInt(pin) == Integer.parseInt(aPin);
    }

    public void printAccountSummary(){
        System.out.printf("\n %s's accounts summary \n", this.fName);
        for(int a = 0; a< this.accounts.size(); a++){
            System.out.printf("%d) %s\n", a+1, this.accounts.get(a).getSummaryLine());

            System.out.println();
        }
    }
    public int numAccounts(){
        return this.accounts.size();
    }

    public void printAccTransactionHistory(int accIdx){
        this.accounts.get(accIdx).printTransHistory();
    }

    public double getAccBalance(int accIdx){
        return this.accounts.get(accIdx).getBalance();
    }

    public String getAccId(int accIdx){
        return this.accounts.get(accIdx).getAccounts();
    }

    public void addAccTransaction(int accIdx, double amount, String format){
        this.accounts.get(accIdx).addTransaction(amount);
    }
}

