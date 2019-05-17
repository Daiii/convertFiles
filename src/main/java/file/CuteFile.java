package file;

import java.io.*;

/**
 * 割接文件
 */
public class CuteFile {

    public static void main(String[] args) throws IOException {
        cuteFile("/Users/zhangbo/Downloads/cuts/forum-1.csv", "/Users/zhangbo/Downloads/cuts/", 1025 * 256);
    }


    /**
     * 根据大小割接文件
     *
     * @param sourceFile 源文件
     * @param targetPath 目标路径
     * @param fileSize   割接大小
     * @throws IOException 异常
     */
    private static void cuteFile(String sourceFile, String targetPath, int fileSize) throws IOException {
        FileInputStream fis = null;
        File file = null;

        fis = new FileInputStream(sourceFile);
        file = new File(sourceFile);

        // 割接文件大小
        byte[] bytes = new byte[fileSize];
        int len = 0;
        int seq = 1;

        String name = file.getName();
        int lastIndex = name.lastIndexOf(".");
        String prefix = name.substring(0, lastIndex);
        String suffix = name.substring(lastIndex, name.length());

        while ((len = fis.read(bytes)) != -1) {
            // 目标目录/原文件名-自增序列.后缀
            FileOutputStream fos = new FileOutputStream(targetPath + "/" + prefix + "-" + seq + suffix);
            fos.write(bytes, 0, len);
            fos.close();
            seq++;
        }

        if (fis != null) {
            fis.close();
        }
    }
}
