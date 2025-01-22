package no_09;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Coffee extends JFrame{
	private int items[] = new int[5]; //현재 자판기의 재료 변수
	private JLabel volumes[] = new JLabel[5]; //재고 잔량 표시 레이블
	
	public Coffee(){
		for(int i=0; i < volumes.length; i++) {
			volumes[i] = new JLabel();
		}
		
		setTitle("no_09 coffee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		MainPanel mp = new MainPanel(); //메인 패널
		ButtonPanel bp = new ButtonPanel(mp); //버튼 패널
		
		c.add(mp, BorderLayout.CENTER);
		c.add(bp, BorderLayout.SOUTH);
		
		setSize(700, 500);
		setVisible(true);
		
		reset(); //커피머신 충전 
		mp.volPaint(volumes, items);
	}
	
	private void reset() { //재료를 10으로 초기화하고 볼륨을 10사이즈로 새로 생성
		for(int i = 0; i< this.items.length; i++) { //모든 재료 10으로 초기화
			this.items[i] = 10;
			volumes[i].setOpaque(true);
			volumes[i].setBackground(Color.red);
			volumes[i].setSize(50, (items[i]*20));
			volumes[i].setLocation((i+1)*105, 50);
		}
	}
	
	class MainPanel extends JPanel{
		public MainPanel() {
			this.setLayout(null);
			JLabel text = new JLabel("Welcome, Hot Coffee !!", JLabel.CENTER);
			text.setSize(700, 30);
			text.setOpaque(true);
			text.setBackground(Color.pink);
			this.add(text);
			
			JLabel jlbBack[] = new JLabel[5];
			JLabel jlbText[] = new JLabel[5];
			
			jlbText[0] = new JLabel("Cup");
			jlbText[1] = new JLabel("Coffee");
			jlbText[2] = new JLabel("Water");
			jlbText[3] = new JLabel("Sugar");
			jlbText[4] = new JLabel("Cream");
			
			
			for(int i=0; i < jlbBack.length; i++) {
				jlbBack[i] = new JLabel();
				jlbBack[i].setOpaque(false);
				//jlbBack[i].setBackground(Color.GRAY);
				jlbBack[i].setSize(50, 200);
				jlbBack[i].setLocation((i+1)*105, 50);
				jlbBack[i].setBorder(new LineBorder(Color.gray, 1, false));
				jlbText[i].setLocation((i+1)*105, 260);
				jlbText[i].setSize(60, 15);
				this.add(jlbBack[i]);
				this.add(jlbText[i]);
			}
		}
	
		public void volPaint(JLabel volumes[], int items[]) {
			for(int i=0; i< volumes.length; i++) {
				volumes[i].setOpaque(true);
				volumes[i].setBackground(Color.red);
				volumes[i].setSize(50, (items[i]*20));
				volumes[i].setLocation((i+1)*105, 50 + (200 -(items[i]*20)));
				this.add(volumes[i]);
			}
			this.repaint();
		}
	}	
	
	class ButtonPanel extends JPanel{
		private MainPanel mainP;
		JButton black, sugar, dabang, reset;
		public ButtonPanel(MainPanel mainP) {
			this.mainP = mainP;
			this.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
			black = new JButton("Black Coffee");
			sugar = new JButton("Suger Coffee");
			dabang = new JButton("Dabang Coffee");
			reset = new JButton("Reset");
			
			black.addActionListener(new BtnListener(mainP));
			sugar.addActionListener(new BtnListener(mainP));
			dabang.addActionListener(new BtnListener(mainP));
			reset.addActionListener(new BtnListener(mainP));
			
			this.add(black);
			this.add(sugar);
			this.add(dabang);
			this.add(reset);
		}
	}
	
	class BtnListener implements ActionListener {
		private MainPanel mainP;
		
		public BtnListener(MainPanel mainP) {
			this.mainP = mainP;
		}
		
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton)e.getSource();
			String op = btn.getText();
			
			switch(op) {
				case "Black Coffee": 
					if(isEmpty() == false) break; //재고가 없으면 멈춤
					items[0]--; //컴--
					items[1] -= 2; //커피--
					items[2]--; //물--
					break;
				case "Suger Coffee":
					if(isEmpty() == false) break; //재고가 없으면 멈춤
					items[0]--; //컵--
					items[1]--; //커피--
					items[2]--; //물--
					items[3] -= 2; //설탕--
					break;
				case "Dabang Coffee": 
					if(isEmpty() == false) break; //재고가 없으면 멈춤
					items[0]--; //컵--
					items[1]--; //커피--
					items[2]--; //물--
					items[3]--; //설탕--
					items[4]--; //크림--
					break;
				case "Reset": 
					reset(); //리셋
					mainP.volPaint(volumes, items); //드로우
					return;
				default: return;
			}
			
			mainP.volPaint(volumes, items); //드로우
			if(isEmpty() == true) //재고 있으면
				JOptionPane.showMessageDialog( mainP, "뜨거워요, 즐거운 하루~~.");
		}
		public boolean isEmpty() {
			for(int i=0; i<items.length; i++) {
				if(items[i] <= 0) {
					JOptionPane.showMessageDialog( mainP, "부족한 것이 있습니다. 채워주세요.");
					return false;
				}
			}
			return true;
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Coffee();
	}

}






