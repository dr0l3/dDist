package week3;

public interface RequestHandler {

    /*
     * Take an URL and parse it to find out what 
     * class, object and method to call.
     * Do the requested actions and return
     * a response as a string.
     * 
     * Url structure:
     * /<class>/<object>/<method>?<parameter_0>=<value_0>&...<parameter_i>=<value_i>
     */
    public String handleURL(String url);
    
}
