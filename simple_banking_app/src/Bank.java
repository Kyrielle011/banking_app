import java.util.ArrayList;
import java.util.Random;

public class Bank {
    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;


    // create a new Bank with empty lists of users and accounts name;
    public Bank(String name){
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }

    public String getNewUserId(){
        String userId;
        Random rnd = new Random();
        int len = 6;
        Boolean nonUnique = false;

        do{
            userId = "";
            for(int i = 0; i<len; i++){
                userId += ((Integer)rnd.nextInt(10)).toString();
            }
            for(User u : this.users){
                if(userId.compareTo(u.getuserId()) == 0){
                    nonUnique = true;
                    break;
                }
            }
        } while(nonUnique);
        return userId;
    }

    public String getNewAccountId(){
        String userId;
        Random rnd = new Random();
        int len = 8;
        Boolean nonUnique = false;

        do{
            userId = "";
            for(int i = 0; i<len; i++){
                userId += ((Integer)rnd.nextInt(10)).toString();
            }
            for(Account a : this.accounts){
                if(userId.compareTo(a.getAccounts()) == 0){
                    nonUnique = true;
                    break;
                }
            }
        } while(nonUnique);
        return userId;
    }

    public void addAccount(Account anAccnt){
        this.accounts.add(anAccnt);
    }

    //create a new user of the bank
    public User addUser(String fName, String lName, String pin){

        //create new user object and add to a list
        User newUser = new User(fName, lName, pin, this);
        this.users.add(newUser);

        // create a new account for user and add to user and bank accounts list
        Account newAccount = new Account("Saving", newUser, this);

        newUser.addAccount(newAccount);
        this.accounts.add(newAccount);

        return newUser;
    }

    public User userLogIn(String userId, String pin){

        // search through list of users
        for(User u : this.users){

            // check user id is correct
            if(u.getuserId().compareTo(userId) == 0 & u.validatePin(pin)){
                return u;
            }

        }
        return null;
    }

    public String getName(){
        return this.name;
    }
}