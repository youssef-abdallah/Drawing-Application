package eg.edu.alexu.csd.oop.draw.cs32_cs62.model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Stack;

public class XmlWriter {
	private BufferedWriter writer;
	private String currentElement;
	private Stack<String> stack = new Stack<String>();
	private int indentCnt = 0;
	
	private void indent() {
		int cnt = indentCnt;
		if (currentElement != null) {
			cnt++;
		}
		for(int i = 0; i < cnt; i++) {
			try {
				writer.write('\t');
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private boolean addElement() throws IOException {
		if (currentElement == null) {
			return false;
		}
		stack.push(currentElement);
		writer.write(">");
		currentElement = null;
		return true;
	}
	
	public XmlWriter(BufferedWriter writer) {
		this.writer = writer;
	}
	
	public void addNode(String name) throws IOException {
		if (this.addElement()) {
			writer.write("\n");
		}
		indent();
		writer.write("<");
		writer.write(name);
		currentElement = name;
		return;		
	}
	
	public void addTextNode(String name, String value) throws IOException {
		if (this.addElement()) {
			writer.write("\n");
		}
		indent();
		writer.write("<");
		writer.write(name);
		writer.write(">");
		writer.write(value);
		writer.write("</" + name + ">");
		return;		
	}
	
	public void addAttribute(String name, String value) throws IOException {
		if (currentElement == null) {
			throw new IllegalStateException();
		}
		writer.write(" ");
		writer.write(name);
		writer.write("=\"");
		writer.write(value == null ? "null" : value);
		writer.write("\"");
		return;		
	}
	
	public void pop() throws IOException {
		if (currentElement != null) {
			writer.write("/>\n");
			currentElement = null;
		} else {
			indentCnt = Math.max(indentCnt - 1, 0);
			writer.write("</");
			writer.write(stack.pop());
			writer.write(">\n");
		}
	}
	
	public void close() throws IOException {
		while(stack.size() != 0) {
			this.pop();
		}
		writer.close();
	}
}
