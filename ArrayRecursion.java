public class ArrayRecursion{

    // public static boolean search(int item, int[] arr, int start){
    //     if (arr.equals(null)) {
    //         throw new IllegalArgumentException();
    //      }
      
    //      else if (arr.length == start + 1){
    //         return false;
    //      }
    //      else if (arr[start] == item){
    //         return true;
    //      }
    //      else {
    //         return search(item, arr, start+1);
    //      }

    // }

    // public static boolean search(Object item, Object[] arr, int start){
    //     if (item instanceof String){
    //         new String[] arr;
    //     }
    //     if (arr.equals(null)) {
    //         throw new IllegalArgumentException();
    //      }
      
    //      else if (arr.length == start + 1){
    //         return false;
    //      }
    //      else if (arr[start].equals(item)){
    //         return true;
    //      }
    //      else {
    //         return search(item, arr, start+1);
    //      }

    // }

    // public static boolean search(String item, new String[] arr, int start){
    //     if (arr.equals(null)) {
    //         throw new IllegalArgumentException();
    //      }
      
    //      else if (arr.length == start + 1){
    //         return false;
    //      }
    //      else if (arr[start] == item){
    //         return true;
    //      }
    //      else {
    //         return search(item, arr, start+1);
    //      }

    // }

    // public static boolean search(double item, double[] arr, int start){
    //     if (arr.equals(null)) {
    //         throw new IllegalArgumentException();
    //      }
      
    //      else if (arr.length == start + 1){
    //         return false;
    //      }
    //      else if (arr[start] == item){
    //         return true;
    //      }
    //      else {
    //         return search(item, arr, start+1);
    //      }

    // }

    // public static boolean search(char item, char[] arr, int start){
    //     if (arr.equals(null)) {
    //         throw new IllegalArgumentException();
    //      }
      
    //      else if (arr.length == start + 1){
    //         return false;
    //      }
    //      else if (arr[start] == item){
    //         return true;
    //      }
    //      else {
    //         return search(item, arr, start+1);
    //      }

    // }
    // public static boolean search(float item, float[] arr, int start){
    //     if (arr.equals(null)) {
    //         throw new IllegalArgumentException();
    //      }
      
    //      else if (arr.length == start + 1){
    //         return false;
    //      }
    //      else if (arr[start] == item){
    //         return true;
    //      }
    //      else {
    //         return search(item, arr, start+1);
    //      }

    // }

    public static boolean search(Object item, Object arr[], int start){
        if (arr == null) {
            throw new IllegalArgumentException();
         }
         else if (arr.length == 0){
            return false;
         }
      
         else if (arr.length == start + 1){
            return false;
         }
         else if (arr[start].equals(item)){
            return true;
         }
         else {
            return search(item, arr, start+1);
         }

    }
    

    public static String reverseArrayToString(Object[] arr, int index ){
        // index = arr.length -1;
        
        if (arr == null){
            return "";
        }
        else if (arr.length == 0){
            return "[]";
        }
        else if (arr.length == 1){
            return "[" + arr[index] + "]";
        }
        else if (index == arr.length - 1){
            return "[" + arr[index] + ", ";
        }
        else if (index == 0){
            String temp = arr[index] + "]";
            return  reverseArrayToString(arr, index+1) + temp;
        }
        else {
            String temp = arr[index] + ", " ;
            return reverseArrayToString(arr, index+1) + temp;
        }
    }

    public static void main(String args[]){
        String[] arr =  {"abc","def","ghi","klm","nop","qrs"};
        System.out.print(reverseArrayToString(new String[] {"abc", "rfd"}, 0));
        // System.out.print(search("def", new String[] {"abc","def","ghi","klm","nop","qrs"} , 0));
    }
}