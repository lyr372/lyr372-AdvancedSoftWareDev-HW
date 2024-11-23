package org.example;

import org.example.html.HTMLDocument;

public class HTMLDocumentTest {
    public static void main(String[] args) {
        HTMLDocument doc = new HTMLDocument();

        // 测试 appendElement
        doc.appendElement("h1", "title", "body", "Welcome to my webpage");
        doc.appendElement("p", "description", "body", "This is a paragraph.");
        doc.appendElement("ul", "list", "body", null);
        doc.appendElement("li", "item1", "list", "Item 1");
        doc.appendElement("li", "item2", "list", "Item 2");
        doc.appendElement("li", "item3", "list", "Item 3");
        doc.printTree();

        // 测试 insertElementBefore
        doc.insertElementBefore("div", "footer", "list", "Footer content");
        doc.printTree();

        // 测试 editElementText
        doc.editElementText("description", "Updated paragraph content.");
        doc.printTree();

        // 测试 editElementId
        doc.editElementId("item2", "item-2-renamed");
        doc.printTree();

        // 测试 deleteElement
        doc.deleteElement("item3");
        doc.printTree();

        // 测试 printIndented
        doc.printIndented();
    }
}
