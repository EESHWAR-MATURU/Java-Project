import java.util.Scanner;

public class ATM {
    private String atmNumber;
    private int pin;
    private double balance;
    private String[] transactions;
    private int transactionCount;

    public ATM(String atmNumber, int pin, double balance) {
        this.atmNumber = atmNumber;
        this.pin = pin;
        this.balance = balance;
        this.transactions = new String[10];
        this.transactionCount = 0;
    }

    public boolean validateUser(String atmNumber, int pin) {
        return (this.atmNumber.equals(atmNumber) && this.pin == pin);
    }

    public void checkBalance() {
        System.out.println("Available balance: $" + balance);
    }

    public void withdrawAmount(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
        } 
        else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } 
        else {
            balance -= amount;
            String transaction = "Withdrawal of $" + amount;
            addTransaction(transaction);
            System.out.println("Transaction successful.");
        }
    }

    public void depositAmount(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
        } 
        else {
            balance += amount;
            String transaction = "Deposit of $" + amount;
            addTransaction(transaction);
            System.out.println("Transaction successful.");
        }
    }

    public void viewMiniStatement() {
        System.out.println("Transaction history:");
        for (int i = 0; i < transactionCount; i++) {
            System.out.println(transactions[i]);
        }
    }

    private void addTransaction(String transaction) {
        if (transactionCount == 10) {
            for (int i = 0; i < 9; i++) {
                transactions[i] = transactions[i + 1];
            }
            transactionCount--;
            }
            transactions[transactionCount++] = transaction;
}

public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    
    ATM[] accounts = {
        new ATM("1029384756", 1234, 600000.0),
        new ATM("0192837465", 5678, 500000.0),
        new ATM("5748392016", 1029, 300000.0)
    };
    
    System.out.print("Enter ATM number: ");
    String atmNumber = scanner.nextLine();
    System.out.print("Enter PIN: ");
    int pin = scanner.nextInt();
    
    ATM currentAccount = null;
    for (ATM account : accounts) {
        if (account.validateUser(atmNumber, pin)) {
            currentAccount = account;
            break;
        }
        }
    
    if (currentAccount == null) {
        System.out.println("Invalid ATM number or PIN.");
        return;
    }
    
    int choice;
    do {
        System.out.println("Please select an operation:");
        System.out.println("1. Check available balance");
        System.out.println("2. Withdraw amount");
        System.out.println("3. Deposit amount");
        System.out.println("4. View mini statement");
        System.out.println("5. Exit");
        System.out.print("Choice: ");
        choice = scanner.nextInt();
      
    switch (choice) {
        case 1:
            currentAccount.checkBalance();
        break;
        case 2:
            System.out.print("Enter amount to withdraw: ");
            double withdrawalAmount = scanner.nextDouble();
            currentAccount.withdrawAmount(withdrawalAmount);
        break;
        case 3:
            System.out.print("Enter amount to deposit: ");
            double depositAmount = scanner.nextDouble();
            currentAccount.depositAmount(depositAmount);
        break;
        case 4:
            currentAccount.viewMiniStatement();
        break;
        case 5:
            System.out.println("Exiting ATM.");
        break;
        default:
            System.out.println("Invalid choice. Please try again.");
}
  
    System.out.println();
  
} 
while (choice != 5);

    scanner.close();
}
}
