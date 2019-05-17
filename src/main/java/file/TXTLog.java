package file;

import java.io.*;

public class TXTLog {

    public static void main(String[] args) throws IOException {
        long begin = System.currentTimeMillis();
        replace("/Users/zhangbo/Downloads/pid_score_20190516.txt");
        long end = System.currentTimeMillis();
        System.out.println("done.......");
        System.out.println(String.format("times : %s", (end - begin) / 1000));
    }

    private static void replace(String filePath) throws IOException {
        int count = 0;
        String row = "";
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        StringBuffer buf = new StringBuffer();
        while ((row = br.readLine()) != null) {
            count++;
            if (count == 1) {
                continue;
            }
            buf.append(row.substring(1, row.length() - 1));
            buf = buf.append(System.getProperty("line.separator"));
        }
        br.close();
        FileOutputStream fos = new FileOutputStream(file);
        PrintWriter pw = new PrintWriter(fos);
        pw.write(buf.toString().toCharArray());
        pw.flush();
        pw.close();
    }
}
