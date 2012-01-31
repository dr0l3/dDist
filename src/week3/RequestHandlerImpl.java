package week3;

import java.util.HashMap;

public class RequestHandlerImpl implements RequestHandler {
    
    private HashMap<Integer, Bank> banks;
    private HashMap<Integer, Account> accounts;
    private int counter;

    public RequestHandlerImpl() {
        banks = new HashMap<Integer, Bank>();
        accounts = new HashMap<Integer, Account>();
        counter = 0;
    }
    
    public void addBank() {
        banks.put(counter, new BankImpl());
        counter++;
    }
    
    @Override
    public String handleURL(String url) {
        
        
        // Decode url here!!
        
        
        String[] splittedUrl = url.split("[/\\?]");
        
        String urlClass = splittedUrl[1];
        String urlObject = splittedUrl[2];
        String urlMethod = splittedUrl[3];
        String urlParams = splittedUrl[4];
        
        if(urlClass.equals("Bank")) {
            Bank bank = banks.get(Integer.parseInt(urlObject));
            if(bank == null) {
                return "The bank does not exist.";
            }
            HashMap<String, String> map = parseParameters(urlParams);
            Account acc = bank.getAccount(map.get("name"));
            // put account into accounts hashmap if not already in
            if(!accounts.containsValue(acc)) {
                counter = counter++;
                accounts.put(counter, acc);
            }
            return acc.getName() + "'s account has balance " + acc.getBalance();
        }
        else if(urlClass.equals("Account")) {
            Account acc = accounts.get(Integer.parseInt(urlObject));
            if(acc == null) {
                return "The account does not exist";
            }
            
            HashMap<String, String> map = parseParameters(urlParams);
            
            // Find out what method to call (very generic)
            if(urlMethod.equals("withdraw")) {
                acc.withdraw(Double.valueOf(map.get("amount")));
                return "Saldo: " + acc.getBalance();
            }
            else if(urlMethod.equals("deposit")) {
                acc.deposit(Double.valueOf(map.get("amount")));
                return "Saldo: " + acc.getBalance();
            }
            else if(urlMethod.equals("getName")) {
                return "Name: " + acc.getName();
            }
            else if(urlMethod.equals("getBalance")) {
                return "Saldo: " + acc.getBalance();
            }
        }
        
        return "";
    }

    public void printHashMaps() {
        System.out.println(banks.toString());
        System.out.println(accounts.toString());
    }
    
    private HashMap<String, String> parseParameters(String params) {
        String[] nameValue = params.split("&");
        
        HashMap<String, String> map = new HashMap<String, String>();
        for(int i = 0; i < nameValue.length; i++) {
            String[] param = nameValue[i].split("=");
            map.put(param[0], param[1]);
        }
        
        return map;
    }
}
