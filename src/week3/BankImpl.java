package week3;

import java.util.HashMap;

public class BankImpl implements Bank {
    
    private HashMap<String, Account> accounts;
    
    public BankImpl() {
        accounts = new HashMap<String, Account>();
    }

    @Override
    public Account getAccount(String name) {
        // Return the account if it is in the bank
        if(accounts.containsKey(name)) {
            return accounts.get(name);
        }
        
        // Create a new account if it does not exist
        Account account = new AccountImpl(name);
        accounts.put(name, account);
        return account;
    }

}
