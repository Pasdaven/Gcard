package com.pasdaven.backend.service;

import java.io.FileInputStream;
import java.io.IOException;

public class FileSystemService {
    public String readFile(String filePath) throws IOException {
        // 建立文件輸入流
        FileInputStream inputStream = new FileInputStream(filePath);
        // 建立字串生成器
        StringBuilder builder = new StringBuilder();
        // 讀取文件內容
        int ch;
        while ((ch = inputStream.read()) != -1) {
            builder.append((char) ch);
        }
        // 關閉文件輸入流
        inputStream.close();
        // 返回文件內容
        return builder.toString();
    }
}
