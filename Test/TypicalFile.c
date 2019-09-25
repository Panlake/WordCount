public class CharCount {
    CharCount(String orders[]) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader(orders[1]));    //用于测试，统计字符数的功能

        int charcount=0;
        String c;
        while((c=br.readLine())!=null) {
            charcount+=c.length();
        }

        System.out.println("字符数："+charcount);
        br.close();
    }
}