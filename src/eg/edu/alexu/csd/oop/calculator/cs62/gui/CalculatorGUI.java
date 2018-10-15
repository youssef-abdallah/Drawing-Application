package eg.edu.alexu.csd.oop.calculator.cs62.gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import eg.edu.alexu.csd.oop.calculator.cs62.MyCalculator;

public class CalculatorGUI {

	private JFrame frame;
	private JTextField textField;
	private MyCalculator calculator = new MyCalculator();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculatorGUI window = new CalculatorGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CalculatorGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 423, 493);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setFont(new Font("Tahoma", Font.BOLD, 24));
		textField.setBounds(12, 13, 385, 67);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		// Row 1
		
		JButton btn7 = new JButton("7");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String EnterNumber = textField.getText() + btn7.getText();
				textField.setText(EnterNumber);
			}
		});
		btn7.setFont(new Font("Tahoma", Font.BOLD, 24));
		btn7.setBounds(12, 93, 60, 60);
		frame.getContentPane().add(btn7);
		
		JButton btn8 = new JButton("8");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String EnterNumber = textField.getText() + btn8.getText();
				textField.setText(EnterNumber);
			}
		});
		btn8.setFont(new Font("Tahoma", Font.BOLD, 24));
		btn8.setBounds(84, 93, 60, 60);
		frame.getContentPane().add(btn8);
		
		JButton btn9 = new JButton("9");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String EnterNumber = textField.getText() + btn9.getText();
				textField.setText(EnterNumber);
			}
		});
		btn9.setFont(new Font("Tahoma", Font.BOLD, 24));
		btn9.setBounds(156, 93, 60, 60);
		frame.getContentPane().add(btn9);
		
		JButton btnPlus = new JButton("+");
		btnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String EnterNumber = textField.getText() + btnPlus.getText();
				textField.setText(EnterNumber);
			}
		});
		btnPlus.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnPlus.setBounds(228, 93, 60, 60);
		frame.getContentPane().add(btnPlus);
		
		//Row 2
		
		JButton btn4 = new JButton("4");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String EnterNumber = textField.getText() + btn4.getText();
				textField.setText(EnterNumber);
			}
		});
		btn4.setFont(new Font("Tahoma", Font.BOLD, 24));
		btn4.setBounds(12, 165, 60, 60);
		frame.getContentPane().add(btn4);
		
		JButton btn5 = new JButton("5");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String EnterNumber = textField.getText() + btn5.getText();
				textField.setText(EnterNumber);
			}
		});
		btn5.setFont(new Font("Tahoma", Font.BOLD, 24));
		btn5.setBounds(84, 165, 60, 60);
		frame.getContentPane().add(btn5);
		
		JButton btn6 = new JButton("6");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String EnterNumber = textField.getText() + btn6.getText();
				textField.setText(EnterNumber);
			}
		});
		btn6.setFont(new Font("Tahoma", Font.BOLD, 24));
		btn6.setBounds(156, 165, 60, 60);
		frame.getContentPane().add(btn6);
		
		JButton btnMinus = new JButton("-");
		btnMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String EnterNumber = textField.getText() + btnMinus.getText();
				textField.setText(EnterNumber);
			}
		});
		btnMinus.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnMinus.setBounds(228, 165, 60, 60);
		frame.getContentPane().add(btnMinus);
		
		//Row 3
		
		JButton btn1 = new JButton("1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String EnterNumber = textField.getText() + btn1.getText();
				textField.setText(EnterNumber);
			}
		});
		btn1.setFont(new Font("Tahoma", Font.BOLD, 24));
		btn1.setBounds(12, 237, 60, 60);
		frame.getContentPane().add(btn1);
		
		JButton btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String EnterNumber = textField.getText() + btn2.getText();
				textField.setText(EnterNumber);
			}
		});
		btn2.setFont(new Font("Tahoma", Font.BOLD, 24));
		btn2.setBounds(84, 237, 60, 60);
		frame.getContentPane().add(btn2);
		
		JButton btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String EnterNumber = textField.getText() + btn3.getText();
				textField.setText(EnterNumber);
			}
		});
		btn3.setFont(new Font("Tahoma", Font.BOLD, 24));
		btn3.setBounds(156, 237, 60, 60);
		frame.getContentPane().add(btn3);
		
		JButton btnMult = new JButton("*");
		btnMult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String EnterNumber = textField.getText() + btnMult.getText();
				textField.setText(EnterNumber);
			}
		});
		btnMult.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnMult.setBounds(228, 237, 60, 60);
		frame.getContentPane().add(btnMult);
		
		//Row 4
		
		JButton btn0 = new JButton("0");
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String EnterNumber = textField.getText() + btn0.getText();
				textField.setText(EnterNumber);
			}
		});
		btn0.setFont(new Font("Tahoma", Font.BOLD, 24));
		btn0.setBounds(12, 309, 60, 60);
		frame.getContentPane().add(btn0);
		
		JButton btnDot = new JButton(".");
		btnDot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String EnterNumber = textField.getText() + btnDot.getText();
				textField.setText(EnterNumber);
			}
		});
		btnDot.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnDot.setBounds(84, 309, 60, 60);
		frame.getContentPane().add(btnDot);
		
		JButton btnEqual = new JButton("=");
		btnEqual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.input(textField.getText());
				textField.setText(calculator.getResult());
			}
		});
		btnEqual.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnEqual.setBounds(156, 309, 60, 60);
		frame.getContentPane().add(btnEqual);
		
		JButton btnDiv = new JButton("/");
		btnDiv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String EnterNumber = textField.getText() + btnDiv.getText();
				textField.setText(EnterNumber);
			}
		});
		btnDiv.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnDiv.setBounds(228, 309, 60, 60);
		frame.getContentPane().add(btnDiv);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.save();
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnSave.setBounds(300, 93, 97, 132);
		frame.getContentPane().add(btnSave);
		
		JButton btnLoad = new JButton("LOAD");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calculator.load();
			}
		});
		btnLoad.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnLoad.setBounds(300, 237, 100, 132);
		frame.getContentPane().add(btnLoad);
		
		JButton btnPre = new JButton("PRE");
		btnPre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(calculator.prev());
			}
		});
		btnPre.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnPre.setBounds(12, 382, 86, 48);
		frame.getContentPane().add(btnPre);
		
		JButton bntCur = new JButton("CUR");
		bntCur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(calculator.current());
			}
		});
		bntCur.setFont(new Font("Tahoma", Font.BOLD, 22));
		bntCur.setBounds(109, 382, 86, 48);
		frame.getContentPane().add(bntCur);
		
		JButton btnNext = new JButton("NXT\r\n");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(calculator.next());
			}
		});
		btnNext.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNext.setBounds(206, 382, 86, 48);
		frame.getContentPane().add(btnNext);
		
		JButton btnClear = new JButton("CLR");
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(null);
			}
		});
		btnClear.setBounds(303, 382, 97, 48);
		frame.getContentPane().add(btnClear);
		
	}
}
