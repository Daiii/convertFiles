package file;

import java.io.*;

/**
 * txt文件内容替换
 */
public class TXTLog {

    public static void main(String[] args) throws IOException {
        replace("/Users/zhangbo/Downloads/pid_score_20190516.txt");
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
            buf.append(System.lineSeparator());
        }
        br.close();
        FileOutputStream fos = new FileOutputStream(file);
        PrintWriter pw = new PrintWriter(fos);
        pw.write(buf.toString().toCharArray());
        pw.flush();
        pw.close();
    }
}
