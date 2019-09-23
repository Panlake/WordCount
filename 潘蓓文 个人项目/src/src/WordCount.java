package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCount {
    WordCount(String orders[]) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader(orders[1]));

        int wordcount=0;
        String w=null;
        String s="[A-Za-z]+\\b";
        Pattern p=Pattern.compile(s);
        while((w=br.readLine())!=null) {
            Matcher m=p.matcher(w);
            while(m.find()){
                wordcount++;
            }
        }

        System.out.println("单词数："+wordcount);
        br.close();
    }
}
