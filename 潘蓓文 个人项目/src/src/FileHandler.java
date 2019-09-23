package src;

import java.io.*;

public class FileHandler {
    FileHandler(String orders[]) throws IOException {
        String path = null,type = null;
        if(orders.length>2) {
            path = orders[2].substring(0,orders[2].lastIndexOf("\\"));
            type = orders[2].substring(orders[2].lastIndexOf("."));
        }

        File file = new File(path);   //指定地址文件夹
        File files[] = file.listFiles();   //从指定文件夹获取所有文件
        String reorder[] = {orders[1],null};
        if(file.exists()) {
            if(files==null) {
                System.out.println("该文件夹为空");
                return;
            }
            else {
                for(int i = 0;i<files.length;i++) {
                    if(files[i].isFile() && files[i].getName().endsWith(type)) {
                        reorder[1] = files[i].getPath();
                        System.out.println(files[i].getName());
                    }
                    if("-c".equals(reorder[0])) {
                        CharCount c = new CharCount(reorder);
                    }
                    else if("-w".equals(reorder[0])) {
                        WordCount w = new WordCount(reorder);
                    }
                    else if("-l".equals(reorder[0])) {
                        LineCount l = new LineCount(reorder);
                    }
                    else if("-a".equals(reorder[0])) {
                        SpecialLineCount sl = new SpecialLineCount(reorder);
                    }
                }
            }
        }
    }
}
