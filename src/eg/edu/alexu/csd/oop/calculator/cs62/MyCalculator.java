package eg.edu.alexu.csd.oop.calculator.cs62;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eg.edu.alexu.csd.oop.calculator.Calculator;

public class MyCalculator implements Calculator {
	
	
	private Integer currentIdx, lastCurrent;
	private String[] expressions; 
	private boolean noInput;
	private Integer far;
	
	public MyCalculator(){
		currentIdx = -1;
		lastCurrent = -1;
		expressions = new String[5];
		noInput = true;
		far = 0;
	}
	
	private double performOperation(double x, double y, String operator) {
		double ans;
		if (operator.compareTo("+") == 0) {
			ans = x + y;
		} else if (operator.compareTo("-") == 0) {
			ans = x - y;
		} else if (operator.compareTo("*") == 0) {
			ans = x * y;
		} else {
			ans = x / y;
		}
		return ans;
	}

	@Override
	public void input(String s) {
		// TODO Auto-generated method stub
		currentIdx = lastCurrent;
		currentIdx++;
		currentIdx %= 5;
		expressions[currentIdx] = s;
		lastCurrent = currentIdx;
		noInput = false;
		
	}

	@Override
	public String getResult() {
		// TODO Auto-generated method stub
		Stack<String> st = new Stack<>();
		Pattern pattern = Pattern.compile("((-?\\d*\\.\\d+)|(-?\\d+)|([\\+\\-\\*/\\(\\)]))");
		Matcher m = pattern.matcher(expressions[currentIdx]);
		while(m.find()) {
			st.push(m.group());
		    if (st.size() == 3) {
		    	double x = Double.parseDouble(st.pop());
		    	String operator = st.pop();
		    	double y = Double.parseDouble(st.pop());
		    	double ans = performOperation(y, x, operator);
		    	st.push(String.valueOf(ans));
		    } else if (st.size() == 2 && st.peek().charAt(0) == '-' && st.peek().length() > 1) {
		    	double x = Double.parseDouble(st.pop());
		    	double y = Double.parseDouble(st.pop());
		    	double ans = performOperation(y, x, "+");
		    	st.push(String.valueOf(ans));
		    }
		}
		if (st.size() == 1) {
			return st.pop();
		}
		return null;
	}

	@Override
	public String current() {
		// TODO Auto-generated method stub
		if (noInput) return null;
		return expressions[currentIdx];
	}

	@Override
	public String prev() {
		if (noInput) return null;
		// TODO Auto-generated method stub
		currentIdx--;
		currentIdx += 5;
		currentIdx %=5;
		if (expressions[currentIdx] == null) {
			currentIdx++;
			currentIdx %= 5;
			return null;
		}
		far--;
		if (Math.abs(far) == 5) {
			currentIdx++;
			currentIdx %= 5;
			if(far > 0) far = 4;
			else far = -4;
			return null;
		}
		return expressions[currentIdx];
	}

	@Override
	public String next() {
		if (noInput) return null;
		// TODO Auto-generated method stub
		currentIdx++;
		currentIdx %=5;
		if (expressions[currentIdx] == null) {
			currentIdx--;
			currentIdx += 5;
			currentIdx %= 5;
			return null;
		}
		far++;
		if (Math.abs(far) == 5) {
			currentIdx--;
			currentIdx += 5;
			currentIdx %= 5;
			if (far > 0) far = 4;
			else far = -4;
			return null;
		}
		return expressions[currentIdx];
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("state.txt"))) {
            bw.write(currentIdx.toString());
            bw.newLine();
            bw.write(lastCurrent.toString());
            bw.newLine();
            for(int i = 0; i < 5; i++) {
            	if (expressions[i] == null) {
            		bw.write("null");
            	}
            	else bw.write(expressions[i]);
            	bw.newLine();
            }
            bw.write(noInput == true ? "1" : "0");
            bw.newLine();
            bw.write(far.toString());
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }

	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		try (BufferedReader br = new BufferedReader(new FileReader("state.txt"))) {
            currentIdx = Integer.valueOf(br.readLine());
            lastCurrent = Integer.valueOf(br.readLine());
            for(int i = 0; i < 5; i++) {
            	String tmp = br.readLine();
            	if (tmp.compareTo("null") == 0) {
            		expressions[i] = null;
            	} else {
            		expressions[i] = tmp;
            	}
            }
            int flg = Integer.valueOf(br.readLine());
            noInput = flg == 1 ? true : false;
            far = Integer.valueOf(br.readLine());
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
	}

}
