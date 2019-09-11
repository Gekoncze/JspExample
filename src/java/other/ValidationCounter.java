package other;


public class ValidationCounter {
    private static int count = 0;
    
    public static synchronized int getCount(){
        return count;
    }
    
    public static synchronized void increment(){
        count++;
    }
}
