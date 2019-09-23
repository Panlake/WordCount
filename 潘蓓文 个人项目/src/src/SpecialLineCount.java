package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SpecialLineCount {
    SpecialLineCount(String orders[]) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader(orders[1]));

        int codelinecount=0;
        int blanklinecount=0;
        int notelinecount=0;

        String r="\\s*";
        String sl;
        boolean flag = false;
        while((sl=br.readLine())!=null) {
            sl = sl.trim();

            if(sl.contains("/*")) {
                notelinecount++;
                flag = true;
                if(!(sl.startsWith("/*"))) {
                    codelinecount++;
                }
            }
            else if(flag == true){
                notelinecount++;
                if (sl.contains("*/")) {
                    flag = false;
                    if(!(sl.startsWith("*/"))) {
                        codelinecount++;
                    }
                }
            }
            else if(sl.contains("//")) {
                notelinecount++;
                if(!(sl.startsWith("//"))) {
                    codelinecount++;
                }
            }
            else if(sl.matches(r)){
                blanklinecount++;
            }
            else codelinecount++;
        }

        System.out.println("代码行数+空行数+注释行数："+codelinecount+","+blanklinecount+","+notelinecount);
        br.close();
    }
}
