package org.example.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HtmlFileHandler {
    
    private HTMLDocument htmlDocument;

    // 从 HTML 文件中读取内容并生成 HTMLDocument 模型
    public void read(String filePath) throws IOException {
        File file = new File(filePath);
        Document doc = Jsoup.parse(file, "UTF-8");
        
        // 初始化 HTMLDocument
        this.htmlDocument = new HTMLDocument();

        // 将 Jsoup 文档解析为 HTMLDocument 模型
        buildHtmlDocument(doc, htmlDocument);
    }

    // 将 HTMLDocument 模型保存到文件
    public void save(String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(htmlDocument.toString());
        }
    }

    // 使用 Jsoup 构建 HTMLDocument 模型
    private void buildHtmlDocument(Document doc, HTMLDocument htmlDocument) {
        Element head = doc.head();
        Element body = doc.body();

        // 遍历 head 元素
        for (Element element : head.children()) {
            HTMLElement child = new HTMLElement(element.tagName(), element.id());
            child.setTextContent(element.text());
            htmlDocument.appendElement(child.getTagName(), child.getId(), "head", child.getTextContent());
        }

        // 遍历 body 元素
        for (Element element : body.children()) {
            appendJsoupElementToDocument(element, "body", htmlDocument);
        }
    }

    // 递归遍历 Jsoup 元素树并添加到 HTMLDocument
    private void appendJsoupElementToDocument(Element jsoupElement, String parentId, HTMLDocument htmlDocument) {
        HTMLElement newElement = new HTMLElement(jsoupElement.tagName(), jsoupElement.id());
        newElement.setTextContent(jsoupElement.ownText());

        // 添加当前元素到 HTMLDocument
        htmlDocument.appendElement(newElement.getTagName(), newElement.getId(), parentId, newElement.getTextContent());

        // 递归添加子元素
        for (Element child : jsoupElement.children()) {
            appendJsoupElementToDocument(child, newElement.getId(), htmlDocument);
        }
    }
}
