package src;

import java.io.IOException;
import java.util.Scanner;

public class WC {
    public static void main(String[] args) throws IOException{
        System.out.println("指令说明：");
        System.out.println("-c 返回指定文件的字符数");
        System.out.println("-w 返回指定文件的单词数");
        System.out.println("-l 返回指定文件的行数");
        System.out.println("-a 返回指定文件的代码行/空行/注释行");
        System.out.println("-s 递归处理指定目录下符合条件的文件");
        System.out.println("-x 调出图形界面");

        System.out.println("请输入指令以及文件路径：");
        System.out.print("wc.exe ");
        Scanner input=new Scanner(System.in);
        String order[]=input.nextLine().split(" ");
        if(order.length==2) {
            if("-c".equals(order[0])) {
                CharCount c = new CharCount(order);
            }
            else if("-w".equals(order[0])) {
                WordCount w = new WordCount(order);
            }
            else if("-l".equals(order[0])) {
                LineCount l = new LineCount(order);
            }
            else if("-a".equals(order[0])) {
                SpecialLineCount sl = new SpecialLineCount(order);
            }
        }
        else if(order.length==3 && "-s".equals(order[0])) {
            FileHandler f = new FileHandler(order);
        }
        else if(order.length==1 && "-x".equals(order[0])){
            GUI g = new GUI();
        }
        else System.out.println("有误，请重新输入:");
    }
}