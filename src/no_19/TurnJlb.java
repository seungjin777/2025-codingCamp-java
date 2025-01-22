package no_19;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class JlbThread extends Thread{
	private JLabel jlb;
	
	public JlbThread(JLabel jlb) {
		this.jlb = jlb;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(200);
				
			}catch(InterruptedException e) {
				return;
			}
		}
	}
	
	public void rotation() {
		
	}
}

public class TurnJlb extends JFrame{
	private JLabel jlb;
	
	public TurnJlb() {
		setTitle("snakeGame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(null);		
	
		
		jlb = new JLabel("turn");
		jlb.setSize(100,50);
		jlb.setLocation(200, 200);
		c.add(jlb);
		
		JlbThread jth = new JlbThread(jlb);
		
		setSize(500, 500);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TurnJlb();
		
		//폰트 회전 모름 pass
	}
}
