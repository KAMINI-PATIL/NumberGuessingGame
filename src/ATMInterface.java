import java.util.Scanner;

// Class representing the User's Bank Account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            System.out.println("Initial balance cannot be negative. Setting balance to 0.");
            this.balance = 0;
        }
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        } else {
            return false;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

// Class representing the ATM Machine
class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean exit = false;

        System.out.println("Welcome to the ATM!");
        while (!exit) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    exit = true;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void checkBalance() {
        System.out.println("Your current balance is: $" + String.format("%.2f", account.getBalance()));
    }

    private void depositMoney() {
        System.out.print("Enter the amount to deposit: $");
        double amount = scanner.nextDouble();
        if (account.deposit(amount)) {
            System.out.println("Deposit successful. Your new balance is: $" + String.format("%.2f", account.getBalance()));
        } else {
            System.out.println("Invalid amount. Deposit failed.");
        }
    }

    private void withdrawMoney() {
        System.out.print("Enter the amount to withdraw: $");
        double amount = scanner.nextDouble();
        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful. Your new balance is: $" + String.format("%.2f", account.getBalance()));
        } else {
            System.out.println("Insufficient balance or invalid amount. Withdrawal failed.");
        }
    }
}

// Main Class to Run the ATM Interface
public class ATMInterface {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(500.00); // Initial balance of $500
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}

