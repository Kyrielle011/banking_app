import java.util.ArrayList;

public class Account {
    public String name;
    private User holder;
    private String userId;
    private ArrayList<Transaction> transactions;

     public Account(String name, User holder, Bank theBank){
        this.name = name;
        this.holder = holder;

        this.userId = theBank.getNewUserId();

        this.transactions = new ArrayList<Transaction>();

        holder.addAccount(this);
        theBank.addAccount(this);


    }

    public String getAccounts(){
         return this.userId;
    }

    public String getSummaryLine(){
         double balance = this.getBalance();

         if(balance>=0){
             return String.format("%s : $%.02f : %s", this.userId, balance, this.name  );
         }else{
             return String.format("%s : $(%.02f) : %s", this.userId, balance, this.name  );
         }

    }

    public double getBalance(){
         double balance = 0;

         for(Transaction t : this.transactions){
             balance +=t.getAmount();
         }
         return balance;
    }

    public void printTransHistory(){
         System.out.printf("\nTransaction history for account %s\n", this.userId);
         for(int t = this.transactions.size()-1; t>=0; t--){
             System.out.printf(this.transactions.get(t).getSummaryLine());
         }
         System.out.println();
    }

    public void addTransaction(double amount){
        Transaction newTrans = new Transaction();
        this.transactions.add(newTrans);
    }
}
