import java.util.*;

class UserAccount {
    private String accountNumber;
    private String pin;
    private double balance;
    private List<String> transactionHistory;

    public UserAccount(String accountNumber, String pin, double initialBalance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        transactionHistory.add("Account created with initial balance ₹" + initialBalance);
    }

    public boolean verifyPin(String inputPin) {
        return this.pin.equals(inputPin);
    }

    public void checkBalance() {
        System.out.println("Your current balance is: ₹" + balance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: ₹" + amount);
            System.out.println("Successfully deposited ₹" + amount);
            checkBalance();
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: ₹" + amount);
            System.out.println("Successfully withdrawn ₹" + amount);
            checkBalance();
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    public void showTransactionHistory() {
        System.out.println("\n--- Transaction History ---");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
        System.out.println("----------------------------");
    }
}

public class ATMSystem {

    private static Map<String, UserAccount> userDatabase = new HashMap<>();

    public static void main(String[] args) {
        // Adding sample users
        userDatabase.put("12345", new UserAccount("12345", "1111", 5000));
        userDatabase.put("67890", new UserAccount("67890", "2222", 3000));

        Scanner scanner = new Scanner(System.in);
        System.out.println("===== Welcome to ATM =====");

        System.out.print("Enter Account Number: ");
        String accNo = scanner.nextLine();

        UserAccount currentUser = userDatabase.get(accNo);

        if (currentUser == null) {
            System.out.println("Invalid Account Number.");
            return;
        }

        System.out.print("Enter PIN: ");
        String enteredPin = scanner.nextLine();

        if (!currentUser.verifyPin(enteredPin)) {
            System.out.println("Incorrect PIN. Access Denied!");
            return;
        }

        System.out.println("Login Successful!\n");

        while (true) {
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. View Transaction History");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    currentUser.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ₹");
                    double deposit = scanner.nextDouble();
                    currentUser.deposit(deposit);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ₹");
                    double withdraw = scanner.nextDouble();
                    currentUser.withdraw(withdraw);
                    break;
                case 4:
                    currentUser.showTransactionHistory();
                    break;
                case 5:
                    System.out.println("Thank you for banking with us. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please select 1-5.");
            }
        }
    }
}