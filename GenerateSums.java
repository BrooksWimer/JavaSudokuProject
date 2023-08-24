

public class GenerateSums{

    public static String generateSums(int n){
        int sum = 1;
        String s = "1";
        String t = "1";
        for (int i = 2; i <= n; i++){
            s+= "\n";
            sum+= i;
	        t += " + ";
            t += i;
            s += t + " = ";
            s+= sum;
        }
        return s;
    }
    
        
public static void main(String args[]){
    System.out.print(generateSums(10));
    // System.out.print(s);
    
}

}