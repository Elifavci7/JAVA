import java.util.ArrayList;
import java.util.Scanner;

// BankAccount Class
class BankAccount {
    private int accountNumber;
    private String ownerName;
    private double balance;

    public BankAccount(int accountNumber, String ownerName, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Money deposited. New balance: " + balance);
        } else {
            System.out.println("Invalid amount!");
        }
    }

      public void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Money was withdrawn.New balance: " + balance);
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }
}


// Bank Class
class Bank {
    private ArrayList<BankAccount> accounts = new ArrayList<>();
    private int accountCounter = 1;

    public void createAccount(String ownerName, double initialBalance) {
        BankAccount account = new BankAccount(accountCounter++, ownerName, initialBalance);
        accounts.add(account);
        System.out.println("Account created! Account number: " + account.getAccountNumber());
    }

    public BankAccount findAccount(int accountNumber) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber() == accountNumber) {
                return acc;
            }
        }
        return null;
    }


// Main Class
    public class bank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {
            System.out.println("\n--- BAKING SYSTEM ---");
            System.out.println("1- Create Account");
            System.out.println("2- Deposit money");
            System.out.println("3- Withdraw Money");
            System.out.println("4-Check Balance");
            System.out.println("0- Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scanner.nextLine(); // buffer temizleme
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Opening Balance: ");
                    double balance = scanner.nextDouble();
                    bank.createAccount(name, balance);
                    break;

                case 2:
                    System.out.print("Account Number: ");
                    int depositAccNo = scanner.nextInt();
                    BankAccount depositAcc = bank.findAccount(depositAccNo);
                    if (depositAcc != null) {
                        System.out.print("Enter deposit amount: ");
                        double amount = scanner.nextDouble();
                        depositAcc.deposit(amount);
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 3:
                    System.out.print("Account number: ");
                    int withdrawAccNo = scanner.nextInt();
                    BankAccount withdrawAcc = bank.findAccount(withdrawAccNo);
                    if (withdrawAcc != null) {
                        System.out.print("Enter withdrawal amount: ");
                        double amount = scanner.nextDouble();
                        withdrawAcc.withdraw(amount);
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 4:
                    System.out.print("Account number: ");
                    int accNo = scanner.nextInt();
                    BankAccount acc = bank.findAccount(accNo);
                    if (acc != null) {
                        System.out.println("Account Holder: " + acc.getOwnerName());
                        System.out.println("Account Balance: " + acc.getBalance());
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 0:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
            scanner.close();  
        }
    }
    
    
    
    
    }
}



