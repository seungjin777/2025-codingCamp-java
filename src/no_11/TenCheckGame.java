package no_11;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class TenCheckGame extends JFrame{
	private JLabel tens[] = new JLabel[10];
	private int numCounter;
	
	public TenCheckGame() {
		setTitle("game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(null);
		
		numCounter = 1;
		
		for(int i=0; i<tens.length; i++) {
			tens[i] = new JLabel(Integer.toString(i+1));
			tens[i].setSize(20, 20);
			int x = (int)(Math.random()*400 + 10);
			int y = (int)(Math.random()*400 + 10);
			tens[i].setLocation(x, y);
			tens[i].setVisible(true);
			tens[i].addMouseListener(new JlbListener());
			c.add(tens[i]);
		}
		
		setSize(500, 500);
		setVisible(true);
	}
	
	class JlbListener extends MouseAdapter{
		public void mousePressed(MouseEvent e) {
			JLabel jlb = (JLabel)e.getSource();
			int jlbNum = Integer.parseInt(jlb.getText());
			
			if(jlbNum == numCounter) {
				numCounter++;
				jlb.setVisible(false);
			}
			jlb.getParent().repaint();
			
			if(numCounter > 10) {
				int result = JOptionPane.showConfirmDialog(jlb, "Replay?");
				if(result == JOptionPane.CANCEL_OPTION) {
					System.out.println("JCWS");
				}else if(result == JOptionPane.YES_OPTION) {
					System.exit(0);
				}else {
					new TenCheckGame();
				}
				
			}
		}
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TenCheckGame();
	}

}
