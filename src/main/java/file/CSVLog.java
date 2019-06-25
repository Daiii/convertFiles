package file;

import com.csvreader.CsvReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * csv文件转换为话单
 */
public class CSVLog {

    public static void main(String[] args) throws IOException {
        List<String> cells = readerCSV("/Users/zhangbo/Downloads/cuts/forum-1.csv");
        writeFile(cells, "/Users/zhangbo/Downloads/cuts/over.txt", false);
    }

    /**
     * 读取csv文件
     *
     * @param filePath 文件路径
     * @return list
     * @throws IOException 异常
     */
    private static List<String> readerCSV(String filePath) throws IOException {
        List<String> cells = new ArrayList<>();
        CsvReader reader = new CsvReader(filePath, ',', Charset.forName("UTF-8"));
        reader.readHeaders();
        reader.setSafetySwitch(false);

        while (reader.readRecord()) {
            StringBuffer sb = new StringBuffer();
            String[] row = reader.getValues();
            for (int i = 0; i < row.length; i++) {
                sb.append(row[i]);
                sb.append("|");
            }
            cells.add(sb.toString().replace("\r", "").substring(0, sb.length() - 1));
        }
        reader.close();
        return cells;
    }

    /**
     * 写入文件
     *
     * @param cells      内容
     * @param targetPath 目标路径
     * @param skipLast   是否跳过最后一行
     * @throws IOException 异常
     */
    private static void writeFile(List<String> cells, String targetPath, boolean skipLast) throws IOException {
        File file = new File(targetPath);
        if (!file.exists()) {
            file.createNewFile();
        }

        if (skipLast == Boolean.TRUE) {
            cells.remove(cells.size() - 1);
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(targetPath, Boolean.TRUE));
        for (String item : cells) {
            bw.write(item);
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}
