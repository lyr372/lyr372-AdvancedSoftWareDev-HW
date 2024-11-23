package org.example;

import java.io.IOException;

import org.example.html.HtmlFileHandler;

public class HtmlFileHandlerTest {
    public static void main(String[] args) {
        HtmlFileHandler handler = new HtmlFileHandler();
        
        try {
            // 测试读取文件
            handler.read("src/resources/example.html");
            
            // 保存编辑后的 HTML 文档
            handler.save("src/resources/output.html");
            System.out.println("\nHTML 文档已成功保存到 src/resources/output.html");
            
        } catch (IOException e) {
            System.err.println("操作失败: " + e.getMessage());
        }
    }
}
