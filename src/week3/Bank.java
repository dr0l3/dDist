package week3;

public interface Bank {

    /*
     * Return an existing Account if one exists with the given name.
     * Otherwise create a new account
     */
    public Account getAccount(String name);
    
}
