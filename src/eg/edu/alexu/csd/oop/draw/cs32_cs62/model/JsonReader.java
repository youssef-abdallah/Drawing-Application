package eg.edu.alexu.csd.oop.draw.cs32_cs62.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

public class JsonReader {
	
	private BufferedReader br;
	private int counter = 0;
	
	public JsonReader(BufferedReader br) {
		this.br = br;
	}
	
	public void pass2Lines(){
		String s;
		while(counter != 2) {
			try{
				s = br.readLine();
				counter++;
			} catch(IOException e) {
				
			}
			
		}
	}
	
	public String readName() {
		String name = "";
		String s = "";
		try {
			s = br.readLine();
			if(s.equals("]")) {
				return "fileEnded";
			}
			int x = s.indexOf(":");
			name = s.substring(x + 1, s.length() - 1);
			s = name.replaceAll("\"", "").replaceAll(" ", "");
		}catch(IOException e) {
			
		}
		return s;
	}
	
	public HashMap<String, Double> readProperties() throws IOException{
		HashMap<String, Double> m = new HashMap<>();
		String key = "";
		String value = "";
		String s = "";
		
		while(true) {
			s = br.readLine();
			if (s.charAt(0) == '}') {
				break;
			}
			int x = s.indexOf(":");
			key = s.substring(0, x);
			value = s.substring(x+1, s.length()-1);
			key = key.replaceAll("\"", "").replaceAll(" ", "");
			value = value.replaceAll("\"", "").replaceAll(" ", "");
			m.put(key, Double.parseDouble(value));
		}
		return m;
	}
	
	
	
	

}
