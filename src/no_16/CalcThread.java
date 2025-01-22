package no_16;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MainThread extends Thread{
	private JTextField output;
	private JTextField input1;
	private JTextField input2;
	private int x, y;
	public static boolean work = false; //작업 진행 여부
	
	public MainThread(JTextField input1, JTextField input2, JTextField output) {
		this.output = output;
		this.input1 = input1;
		this.input2 = input2;
	}
	
	@Override
	public void run() {
		while(true)
		{
			if(work) {
				int x = Integer.parseInt(input1.getText()); //인풋1의 텍스트 int형으로 가져옴
				int y = Integer.parseInt(input2.getText());	//인풋2의 텍스트 int형으로 가져옴
				output.setText(Integer.toString(x+y));
				work = false;
			}else {
				Thread.yield(); //실행 대기 상태로 만듦
				
			}
		}
	}
}

public class CalcThread extends JFrame{
	private JTextField input1;
	private JTextField input2;
	private JTextField output;
	private JButton calcBtn;
	
	public CalcThread() {
		setTitle("calc");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(null);
		
		input1 = new JTextField(10);
		input1.setLocation(70, 20);
		input1.setSize(80, 30);
		
		input2 = new JTextField(10);
		input2.setLocation(150, 20);
		input2.setSize(80, 30);
		
		calcBtn = new JButton("calc");
		calcBtn.setLocation(80, 60);
		calcBtn.setSize(60,30);
		
		output = new JTextField(10);
		output.setLocation(80, 100);
		output.setSize(80, 30);
		
		c.add(input1);
		c.add(input2);
		c.add(calcBtn);
		c.add(output);
		
		calcBtn.addActionListener(new btnListener());
		
		MainThread th = new MainThread(input1, input2, output);
		
		setSize(300, 200);
		setVisible(true);
		
		th.start();
		th.work = false;
	}
	
	class btnListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			MainThread.work = true;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CalcThread();
	}
}
