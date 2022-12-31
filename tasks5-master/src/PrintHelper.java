public class PrintHelper {

    public static void printTask(int n){
        String str_n = Integer.toString(n);
        String output = "Task ";
        output += str_n;
        for (int i = 0; i < 16 - str_n.length(); i++) {
            output += "-";
        }
        System.out.println(output);
    }

    public static void blank() {
        System.out.println();
    }

}
