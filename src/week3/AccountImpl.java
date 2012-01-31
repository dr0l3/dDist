package week3;

public class AccountImpl implements Account {
    
    private String name;
    private double balance;
    
    public AccountImpl(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void deposit(double amount) {
        balance = balance + amount;
    }

    @Override
    public void withdraw(double amount) {
        balance = balance - amount;
    }

}
