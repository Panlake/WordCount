package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LineCount {
    LineCount(String orders[]) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader(orders[1]));

        int linecount=0;
        while((br.readLine())!=null) {
            linecount++;
        }

        System.out.println("行数："+linecount);
        br.close();
    }
}
