package org.example.html;

import java.util.HashMap;
import java.util.Map;

public class HTMLDocument {
    private HTMLElement root; // HTML 文档的根节点
    private Map<String, HTMLElement> elementsById; // 快速访问元素的 id 索引

    public HTMLDocument() {
        // 初始化一个空的 HTML 文档
        this.root = new HTMLElement("html", "html");
        HTMLElement head = new HTMLElement("head", "head");
        HTMLElement body = new HTMLElement("body", "body");

        // 添加 head 和 body 到 html
        root.addChild(head);
        root.addChild(body);

        // 初始化 id 索引
        elementsById = new HashMap<>();
        elementsById.put("html", root);
        elementsById.put("head", head);
        elementsById.put("body", body);
    }

    /**
     * 根据 ID 查找元素
     */
    public HTMLElement getElementById(String id) {
        return elementsById.get(id);
    }

    /**
     * 添加元素到指定父元素
     * @param tagName 新元素的标签
     * @param idValue 新元素的 ID
     * @param parentId 父元素的 ID
     * @param textContent 新元素的文本内容
     */
    public void appendElement(String tagName, String idValue, String parentId, String textContent) {
        HTMLElement parent = getElementById(parentId);
        if (parent == null) {
            throw new IllegalArgumentException("Parent element not found: " + parentId);
        }
        HTMLElement newElement = new HTMLElement(tagName, idValue);
        newElement.setTextContent(textContent);
        parent.addChild(newElement);
        elementsById.put(idValue, newElement);
    }

    /**
     * 在指定元素之前插入新元素
     */
    public void insertElementBefore(String tagName, String idValue, String referenceId, String textContent) {
        HTMLElement reference = getElementById(referenceId);
        if (reference == null) {
            throw new IllegalArgumentException("Reference element not found: " + referenceId);
        }
        HTMLElement parent = reference.getParent();
        if (parent == null) {
            throw new IllegalArgumentException("Cannot insert before root element.");
        }
        HTMLElement newElement = new HTMLElement(tagName, idValue);
        newElement.setTextContent(textContent);
        parent.insertBefore(newElement, reference);
        elementsById.put(idValue, newElement);
    }

    /**
     * 修改元素的文本内容
     */
    public void editElementText(String id, String newTextContent) {
        HTMLElement element = getElementById(id);
        if (element == null) {
            throw new IllegalArgumentException("Element not found: " + id);
        }
        element.setTextContent(newTextContent);
    }

    /**
     * 修改元素的 ID
     */
    public void editElementId(String oldId, String newId) {
        HTMLElement element = elementsById.remove(oldId);
        if (element == null) {
            throw new IllegalArgumentException("Element not found: " + oldId);
        }
        element.setId(newId);
        elementsById.put(newId, element);
    }

    /**
     * 删除元素
     */
    public void deleteElement(String id) {
        HTMLElement element = elementsById.remove(id);
        if (element == null) {
            throw new IllegalArgumentException("Element not found: " + id);
        }
        HTMLElement parent = element.getParent();
        if (parent != null) {
            parent.removeChild(element);
        }
    }

    /**
     * 打印文档的树状结构
     */
    public void printTree() {
        root.printTree(0);
    }

    /**
     * 按缩进格式打印文档
     */
    public void printIndented() {
        root.printIndented(0);
    }
}
