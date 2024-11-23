package org.example.html;

import java.util.ArrayList;
import java.util.List;

public class HTMLElement {
    String tagName; // 标签名
    String id; // 元素 ID
    private String textContent; // 文本内容
    private HTMLElement parent; // 父节点
    private List<HTMLElement> children; // 子节点列表

    public HTMLElement(String tagName, String id) {
        this.tagName = tagName;
        this.id = id;
        this.children = new ArrayList<>();
    }
     // 获取标签名
    public String getTagName() {
        return tagName;
    }

    // 获取元素 ID
    public String getId() {
        return id;
    }

    // 获取文本内容
    public String getTextContent() {
        return textContent;
    }

    // 设置文本内容
    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    // 设置 ID
    public void setId(String id) {
        this.id = id;
    }

    // 获取父节点
    public HTMLElement getParent() {
        return parent;
    }

    // 添加子节点
    public void addChild(HTMLElement child) {
        child.parent = this;
        children.add(child);
    }

    // 在指定子节点前插入新节点
    public void insertBefore(HTMLElement newElement, HTMLElement reference) {
        int index = children.indexOf(reference);
        if (index == -1) {
            throw new IllegalArgumentException("Reference element not found.");
        }
        newElement.parent = this;
        children.add(index, newElement);
    }

    // 删除子节点
    public void removeChild(HTMLElement child) {
        children.remove(child);
        child.parent = null;
    }

    // 打印树状结构
    public void printTree(int level) {
        String spaces = " ".repeat(level * 2);
        System.out.println(spaces + tagName + (id != null ? "#" + id : ""));
        for (HTMLElement child : children) {
            child.printTree(level + 1);
        }
    }

    // 打印缩进格式
    public void printIndented(int level) {
        String spaces = " ".repeat(2 * level);
        System.out.print(spaces + "<" + tagName);
        if (id != null) {
            System.out.print(" id=\"" + id + "\"");
        }
        System.out.println(">");

        if (textContent != null && !textContent.isEmpty()) {
            System.out.println(spaces + " ".repeat(2) + textContent);
        }

        for (HTMLElement child : children) {
            child.printIndented(level + 1);
        }

        System.out.println(spaces + "</" + tagName + ">");
    }
}
