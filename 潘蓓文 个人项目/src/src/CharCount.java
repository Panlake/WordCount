package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CharCount {
    CharCount(String orders[]) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader(orders[1]));

        int charcount=0;
        String c;
        while((c=br.readLine())!=null) {
            charcount+=c.length();
        }

        System.out.println("字符数："+charcount);
        br.close();
    }
}
