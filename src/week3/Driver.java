package week3;

public class Driver {
    
    public static void main(String[] args) {
        RequestHandlerImpl rh = new RequestHandlerImpl();
        rh.addBank();
        rh.addBank();
        System.out.println(rh.handleURL("lama14.cs.au.dk:40407/Bank/1/getAccount?name=Bill%20Gates"));
        System.out.println(rh.handleURL("lama14.cs.au.dk:40407/Bank/1/getAccount?name=Jens"));
        System.out.println(rh.handleURL("lama14.cs.au.dk:40407/Bank/1/getAccount?name=Jens"));
        
        System.out.println(rh.handleURL("lama14.cs.au.dk:40407/Account/2/getBalance?d=4"));
        System.out.println(rh.handleURL("lama14.cs.au.dk:40407/Account/2/deposit?amount=120.50"));
        System.out.println(rh.handleURL("lama14.cs.au.dk:40407/Account/2/deposit?amount=1"));
        System.out.println(rh.handleURL("lama14.cs.au.dk:40407/Account/2/withdraw?amount=2"));
        System.out.println(rh.handleURL("lama14.cs.au.dk:40407/Account/2/getName?amount=x"));
        
        rh.printHashMaps();
    }
    
}
