import java.util.ArrayList;
import java.util.Scanner;

// Bank Account class to store account details
class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited $" + amount);
            System.out.println("New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew $" + amount);
            System.out.println("New balance: $" + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds");
        }
    }
}

public class BankingApplication {
    private static ArrayList<BankAccount> accounts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Banking Application");
        
        while (true) {
            displayMenu();
            int choice = getUserChoice();
            
            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    System.out.println("Thank you for using our Banking Application!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nBanking Menu:");
        System.out.println("1. Create New Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Check Balance");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void createAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        
        // Check if account already exists
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                System.out.println("Account number already exists!");
                return;
            }
        }

        System.out.print("Enter account holder name: ");
        String accountHolder = scanner.nextLine();
        
        System.out.print("Enter initial deposit amount: ");
        double initialBalance = Double.parseDouble(scanner.nextLine());

        if (initialBalance >= 0) {
            BankAccount newAccount = new BankAccount(accountNumber, accountHolder, initialBalance);
            accounts.add(newAccount);
            System.out.println("Account created successfully!");
        } else {
            System.out.println("Initial balance cannot be negative!");
        }
    }

    private static BankAccount findAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        System.out.println("Account not found!");
        return null;
    }

    private static void deposit() {
        BankAccount account = findAccount();
        if (account != null) {
            System.out.print("Enter deposit amount: ");
            double amount = Double.parseDouble(scanner.nextLine());
            account.deposit(amount);
        }
    }

    private static void withdraw() {
        BankAccount account = findAccount();
        if (account != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = Double.parseDouble(scanner.nextLine());
            account.withdraw(amount);
        }
    }

    private static void checkBalance() {
        BankAccount account = findAccount();
        if (account != null) {
            System.out.println("Account Holder: " + account.getAccountHolder());
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Current Balance: $" + account.getBalance());
        }
    }
}

	
