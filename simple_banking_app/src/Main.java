import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Сначала запрос данный карты (и фио владельца)
        // Вывод Номера аккаунта, текущего баланса, ФИО
        // Окно выбора функций приложения (поплнение средств, снятие, перевод, выход)

        Scanner scanner = new Scanner(System.in);

        // init bank
        Bank theBank = new Bank("Bank Govna");

        //add a user
        User aUser = theBank.addUser("Igor", "Avram", "4444");

        //
        Account newAccount = new Account("Checking", aUser, theBank);

        aUser.addAccount(newAccount);
        theBank.addAccount(newAccount);


        User curUser;

        while (true) {
            // login prompt
            curUser = Main.mainMenuPrompt(theBank, scanner);

            // main menu
            Main.printUserMenu(curUser, scanner);
        }
    }


    public static User mainMenuPrompt(Bank theBank, Scanner scanner) {
        String userID;
        String pin;
        User authUser;

        //validate id and pin
        do{
            System.out.printf("\n Welcome to %s \n",theBank.getName());

            System.out.printf("Enter user ID: ");
            userID = scanner.nextLine();
            System.out.printf("Enter pin: ");
            pin = scanner.nextLine();

            authUser = theBank.userLogIn(userID,pin);
            if(authUser == null){
                System.out.println("Incorrect userID/pin");
            }
        } while(authUser == null);

        return authUser;
    }

    public static void printUserMenu(User theUser, Scanner scanner){

        theUser.printAccountSummary();

        int choice;

        do{
            System.out.println("Choose what would you like to do: ");
            System.out.println(" 1) Show transaction history");
            System.out.println(" 2) Withdraw");
            System.out.println(" 3) Deposit");
            System.out.println(" 4) Transfer");
            System.out.println(" 5) Quit");
            System.out.println("\n Enter choice: ");
            choice = scanner.nextInt();
            if(choice < 1 || choice > 5){
                System.out.println("Invalid choice");
            }
        } while(choice < 1 || choice > 5);

        switch (choice){
            case 1:
                Main.showTransactionHistory(theUser, scanner);
                break;
            case 2:
                Main.withdrawFunds(theUser, scanner);
                break;
            case 3:
                Main.depositFunds(theUser, scanner);
                break;
            case 4:
                Main.transferFunds(theUser, scanner);
                break;
            case 5:
                break;

        }
        if(choice != 5){
            Main.printUserMenu(theUser, scanner);
        }
    }

    public static void showTransactionHistory(User theUser, Scanner scanner){
        int theAccnt;
        do{
            System.out.printf("Enter the number 1- %d of the accoutns whose transactions you want to see: ", theUser.numAccounts());
            theAccnt = scanner.nextInt() - 1;
            if(theAccnt < 0 || theAccnt >= theUser.numAccounts()){
                System.out.println("Invalid account");
            }
        } while(theAccnt < 0 || theAccnt >= theUser.numAccounts());

        theUser.printAccTransactionHistory(theAccnt);
    }

    public static void transferFunds(User theUser, Scanner scanner){
        int fromAcc;
        int toAcc;
        double amount;
        double accBal;

        do{
            System.out.printf("Enter the number (1-%d) of the account to transfer from",theUser.numAccounts());
            fromAcc = scanner.nextInt()-1;
            if(fromAcc < 0 || fromAcc >= theUser.numAccounts()){
                System.out.println("Invalid choice");
            }
        } while(fromAcc < 0 || fromAcc >= theUser.numAccounts());

        accBal = theUser.getAccBalance(fromAcc);
        do{
            System.out.printf("Enter the number (1-%d) of the account to transfer from",theUser.numAccounts());
            toAcc = scanner.nextInt()-1;
            if(toAcc < 0 || toAcc >= theUser.numAccounts()){
                System.out.println("Invalid choice");
            }
        } while(toAcc < 0 || toAcc >= theUser.numAccounts());

        do{
            System.out.printf("Enter the amount to transfer (max $ .02f)", accBal);
            amount = scanner.nextInt();
            if(amount<0){
                System.out.println("Amount must be bigger than 0");
            }
            else if(amount > accBal){
                System.out.printf("Amount must not be greater than balance of $.02f\n", accBal);
            }
        } while(amount < 0 || amount > accBal);

        theUser.addAccTransaction(fromAcc, -1*amount, String.format("Transfer to account %s", theUser.getAccId(toAcc)));

        theUser.addAccTransaction(toAcc, -1*amount, String.format("Transfer to account %s", theUser.getAccId(fromAcc)));
    }

    public static void withdrawFunds(User theUser, Scanner scanner){
        int fromAcc;
        double amount;
        double accBal=0;


        do{
            System.out.printf("Enter the number (1-%d) of the account to transfer from",theUser.numAccounts());
            fromAcc = scanner.nextInt()-1;
            if(fromAcc < 0 || fromAcc >= theUser.numAccounts()){
                System.out.println("Invalid choice");
            }
        } while(fromAcc < 0 || fromAcc >= theUser.numAccounts());

        do{
            System.out.printf("Enter the amount to transfer (max $.02f", accBal);
            amount = scanner.nextInt();
            if(amount<0){
                System.out.println("Amount must be bigger than 0");
            }
            else if(amount > accBal){
                System.out.printf("Amount must not be greater than balance of $.02f\n", accBal);
            }
        } while(amount < 0 || amount > accBal);
        theUser.addAccTransaction(fromAcc, -1*amount, String.format("Transfer to account %s", theUser.getAccId(fromAcc)));
    }

    public static void depositFunds(User theUser, Scanner scanner){
        int toAcc;
        double amount;
        double accBal=0;


        do{
            System.out.printf("Enter the number (1-%d) of the account to transfer from");
            toAcc = scanner.nextInt()-1;
            if(toAcc < 0 || toAcc >= theUser.numAccounts()){
                System.out.println("Invalid choice");
            }
        } while(toAcc < 0 || toAcc >= theUser.numAccounts());

        do{
            System.out.printf("Enter the amount to transfer (max $.02f", accBal);
            amount = scanner.nextInt();
            if(amount<0){
                System.out.println("Amount must be bigger than 0");
            }
            else if(amount > accBal){
                System.out.printf("Amount must not be greater than balance of $.02f\n", accBal);
            }
        } while(amount < 0 || amount > accBal);
        theUser.addAccTransaction(toAcc, amount, String.format("Transfer to account %s", theUser.getAccId(toAcc)));
    }

}