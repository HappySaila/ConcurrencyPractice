import java.io.*;
import java.util.Scanner;

/**
 * Created by wlsgra012 on 2016/08/17.
 */
public class ReadWrite {
    BufferedReader br;
    BufferedWriter bw;
    String line;
    String out;
    public static void main(String[] args) {
        ReadWrite rw = new ReadWrite();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter what you want to write");
        String data = sc.nextLine();

        rw.write(data, "data.txt");
        System.out.println("data written");
        rw.read("data.txt");

    }
    public void read(String fileName){
        br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            line = "";
            out = "";
            line = br.readLine();
            while(line!=null){
                line+="\n";
                out+=line;
                line=br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(out);
    }

    public void write(String data, String fileName){
        try {
            bw = new BufferedWriter(new FileWriter(fileName));
            bw.write(data);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
