package eg.edu.alexu.csd.oop.draw.cs32_cs62.model;

import java.io.BufferedWriter;
import java.io.IOException;

public class JsonWriter {
	private BufferedWriter writer;
	public int flag = 0;
	
	public JsonWriter(BufferedWriter writer) {
		this.writer = writer;
	}
	
	public void startFile() throws IOException{
		writer.write("{\"ShapeArray\" :\n[\n");
		return;
	}
	
	public void startElement(String name) throws IOException{
		if(flag == 0) {
			writer.write("{ \"" + "className" + "\" :  " + "\"" + name + "\"");
		}else {
			writer.write(",\n{ \"" + "className" + "\" :  " + "\"" + name + "\"");
		}
		flag++;
	}
	
	public void addElement(String key, String value) throws IOException{
		writer.write(",\n\"" + key + "\" : " + "\"" + value + "\"");
	}
	
	public void closeElement() throws IOException{
		writer.write("\n}");
	}
	
	public void close() throws IOException {
		writer.write("\n]\n}");
		writer.close();
	}
}
