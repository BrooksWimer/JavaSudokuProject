

public class StringRecursion{


    public static void printReverse(String str){
        if (str.equals("") || str.equals(null)){
            
        }

        if (str.length() == 1){
            System.out.print(str.charAt(0));
        }
        else{
            printReverse(str.substring(1));
            System.out.print(str.charAt(0));
        }
    }

    public static String trim(String str){
        if (str.equals(null)){
            return "null";
        }
        else if (str.length() <= 1){
            return str;
        }
        else if (str.charAt(0) != ' ' && str.charAt(str.length()-1) != ' '){
            return str;
        }
        else if (str.charAt(0) == ' ' && str.charAt(str.length()-1) == ' '){
            String s = trim(str.substring(1, str.length()-1));
            return s;
        }
        else if (str.charAt(0) == ' '){
            String s = trim(str.substring(1));
            return s;
        }
        else {
            String s = trim(str.substring(0, str.length()-1));
            return s;
        }
    }

    public static int find(char ch, String str){
        if (str.equals("") || str.equals(null)){
            return -1;
        }
        else if (str.charAt(0) == ch){
            return 0;
        }
        else if (str.length() > 0) {
            int temp = find(ch, str.substring(1));
            if (temp == -1){
                return -1;
            }
            else{
                return temp + 1;
            }
        }
        return -1;
        }

    public static String weave(String str1, String str2){
        if (str1.equals(null) || str2.equals(null)){
            throw new IllegalArgumentException();
        }
        else if (str1.equals("")){
            return str2;
        }
        else if (str2.equals("")){
            return str1;
        }
        else {
            String temp = ""+ str1.charAt(0) + str2.charAt(0);
            
            return temp + weave(str1.substring(1), str2.substring(1));

        }
    }

    public static int indexOf(char ch, String str){
        return find(ch, str);
    }

    

    public static void main(String[] args){
        // printReverse("Terriers");
        String s = "   Hello, Rabbit      ";
        System.out.print(trim(s) + "t");
        // System.out.print(weave("", "bbbbbb"));
    }
}