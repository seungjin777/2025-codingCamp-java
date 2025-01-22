package mini_01;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Mini_01 extends JFrame {
	private MainPanel panel = new MainPanel();
	
	public Mini_01() {
		setTitle("mini01");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		setSize(700, 600);
		setVisible(true);
	}
	
	class MainPanel extends JPanel {
		public void MainPanel(Graphics g) {
			super.paintComponent(g);
			
			
		}
	}
	
	class MainListener extends MouseAdapter {
		//public void Mos
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Mini_01();
	}
}
