package no_17;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.border.LineBorder;

class SnakeThread extends Thread{
	private Vector<JLabel> snakeV;
	public static int arrow; //이동 방향
	private int headX, headY; // 헤드 포지션
	private int tailX, tailY; // 테일 포지션
	
	public SnakeThread(Vector<JLabel> snakeV) {
		this.snakeV = snakeV;
		arrow = KeyEvent.VK_RIGHT; //(오른쪽) 디폴트 이동방향
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(200);
				boundaryTest();
				settingPosition(); //포지션 값 조정
				snakeV.get(0).setLocation(headX, headY); //헤드포지션으로 이동
				snakeV.get(9).setLocation(tailX, tailY); //테일포지션으로 이동
				moveToTail(); //테일과 몸통 1번으로 이동
			}catch(InterruptedException e) {
				return;
			}
		}
	}
	
	public void moveToTail() {//스네이크 꼬리를 몸통의 첫번째로 이동s
		JLabel tail = snakeV.get(9);
		snakeV.remove(9);
		snakeV.add(1, tail);
	}
	
	public void boundaryTest() { //경계선까지 이동시 튕겨나옴
 		if(headX <= 0 || headX >= 1140 || headY <= 0 || headY >= 740) {
 			switch(arrow) {
			case KeyEvent.VK_UP:  arrow = KeyEvent.VK_DOWN;  break; 
			case KeyEvent.VK_DOWN:  arrow = KeyEvent.VK_UP; break;
			case KeyEvent.VK_LEFT:  arrow = KeyEvent.VK_RIGHT; break;
			case KeyEvent.VK_RIGHT: arrow = KeyEvent.VK_LEFT; break;
			default: return;
		}
 		}
	}
	
	private void settingPosition() { //포지션값 조정 함수
		switch(arrow) {
			case KeyEvent.VK_UP:   
				headX = snakeV.get(0).getX();
				headY = snakeV.get(0).getY() - 30; //헤드 상승
				break; 
			case KeyEvent.VK_DOWN:   
				headX = snakeV.get(0).getX();
				headY = snakeV.get(0).getY() + 30; //헤드 하강
				break;
			case KeyEvent.VK_LEFT:   
				headX = snakeV.get(0).getX() - 30; //헤드 왼쪽
				headY = snakeV.get(0).getY();
				break;
			case KeyEvent.VK_RIGHT:  
				headX = snakeV.get(0).getX() + 30; //헤드 오른쪽
				headY = snakeV.get(0).getY();
				break;
			default: return;
		}
		tailX = snakeV.get(0).getX();
		tailY = snakeV.get(0).getY();
	}
}

public class SnakeGame extends JFrame{
	private ImageIcon headImg = new ImageIcon("src/no_17/head.png");
	private Vector<JLabel> snakeV; //스네이크 몸통 배열
	
	public SnakeGame() {
		setTitle("snakeGame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(null);
		
		snakeV = new Vector<JLabel>();
		
		JLabel head = new JLabel(headImg);
		head.setSize(30, 30);
		head.setBorder(new LineBorder(Color.magenta, 1, false));
		head.setLocation(100, 300);
		snakeV.add(head); //스네이크 벡터에 머리 삽입
		
		for(int i=0; i<9; i++) {
			JLabel bodys = new JLabel();
			bodys.setSize(30, 30);
			bodys.setOpaque(false);
			bodys.setBorder(new LineBorder(Color.magenta, 1, false));
			bodys.setLocation(100 + (i+1)*30, 300);
			snakeV.add(bodys);
		}
		
		for(int i=0; i<snakeV.size(); i++) {
			c.add(snakeV.get(i));
		}
		
		c.addKeyListener(new MoveListener());
		SnakeThread sth = new SnakeThread(snakeV);
		
		setSize(1200, 800);
		setVisible(true);
		c.setFocusable(true);
		c.requestFocus();
		
		sth.start();
	}
	
	class MoveListener extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			SnakeThread.arrow = keyCode; //키값 쓰레드 클레스의 방향 변수에 전달
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SnakeGame();
	}
}
