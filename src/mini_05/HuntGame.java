package mini_05;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

//--------------------------------클래스 들----------------------------------------------------
class Player{ //플레이어 클래스
	private JLabel playerJlb;
	private int x, y;
	private int arrow;
	
	public Player() {
		playerJlb = new JLabel("P");
		playerJlb.setSize(20, 20);
		playerJlb.setOpaque(true);
		playerJlb.setBackground(Color.magenta);
		playerJlb.setVisible(true);
		playerJlb.setLocation(50, 500);
	}
	
	public void move() {
		
		if(this.x <= 0) arrow = KeyEvent.VK_RIGHT;
		if(this.y <= 0) arrow = KeyEvent.VK_DOWN;
		if(this.x >= 800) arrow = KeyEvent.VK_LEFT;
		if(this.y >= 600) arrow = KeyEvent.VK_UP;
		
		switch(arrow) {
		case KeyEvent.VK_UP: this.setPosition(x, y - 5); break;
		case KeyEvent.VK_DOWN: this.setPosition(x, y + 5); break;
		case KeyEvent.VK_RIGHT: this.setPosition(x + 5, y); break;
		case KeyEvent.VK_LEFT: this.setPosition(x - 5, y); break;
		default: break;
		}
		
	}
	
	public void setArrow(int arrow) {
		this.arrow = arrow;
	}
	
	public JLabel getPlayerJlb() {
		return this.playerJlb;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		playerJlb.setLocation(x, y);
	}
	
	public int getPosX() {
		return this.x;
	}
	
	public int getPosY() {
		return this.y;
	}
}

class Animal{ //동물 클래스
	private JLabel animalJlb;
	private int x, y;
	
	public Animal() {
		animalJlb = new JLabel("X");
		animalJlb.setBackground(Color.yellow);
		animalJlb.setOpaque(true);
		animalJlb.setSize(20, 20);
		animalJlb.setVisible(true);
		x = (int)(Math.random()*700);
		y = (int)(Math.random()*500);
		animalJlb.setLocation(x, y);
	}
	
	public JLabel getAnimalJlb() {
		return this.animalJlb;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		animalJlb.setLocation(x, y);
	}
	
	public int getPosX() {
		return this.x;
	}
	
	public int getPosY() {
		return this.y;
	}
}

class Enemy{ //적 클래스
	private JLabel enemyJlb;
	private int x, y;
	private int moveCnt;
	private int arrow;
	
	public Enemy() {
		moveCnt = 0;
		enemyJlb = new JLabel("O");
		enemyJlb.setSize(15, 15);
		enemyJlb.setVisible(true);
		x = (int)(Math.random()*700);
		y = (int)(Math.random()*500);
		enemyJlb.setLocation(x, y);
		arrow = (int)(Math.random()*4);
	}
	
	public void MoveGameAi() {
		if(moveCnt < 10) {
			//경계선 체크
			if(this.x <= 0) arrow = 2;
			if(this.y <= 0) arrow = 1;
			if(this.x >= 800) arrow = 3;
			if(this.y >= 600) arrow = 0;
			
		
			switch(arrow) {
			case 0: this.setPosition(x, y - 10); break;
			case 1: this.setPosition(x, y + 10); break;
			case 2: this.setPosition(x + 10, y); break;
			case 3: this.setPosition(x - 10, y); break;
			default: break;
			}
			moveCnt++;
		}
		else
		{
			arrow = (int)(Math.random()*4);
			moveCnt = 0;
		}
	}
	
	public JLabel getEnemyJlb() {
		return this.enemyJlb;
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		enemyJlb.setLocation(x, y);
	}
	
	public int getPosX() {
		return this.x;
	}
	
	public int getPosY() {
		return this.y;
	}
}

class MainPanel extends JPanel{ //메인 화면 클래스
	private Player player; //플레이어 객체 선언
	private Animal animal1; //동물 객체 선언
	private Animal animal2; //동물 객체 선언
	private JLabel goal; //목표 레이블 선언
	
	private PlayerThread playerTh; //플레이어 쓰레드 선언
	private AnimalThread animalTh1; //애니멀1 쓰레드 선언
	private AnimalThread animalTh2; //애니멀2 쓰레드 선언
	private ArrayList<EnemyThread> enemyThList; //적군 쓰레드 리스트 선언
	
	public MainPanel() {
		this.setLayout(null);
		
		goal = new JLabel("G"); //목표 레이블 생성
		goal.setSize(30, 30);
		goal.setLocation(670, 20);
		goal.setBackground(Color.red);
		goal.setOpaque(true);
		
		player = new Player(); //플레이어 객체 생성
		animal1 = new Animal(); //동물1 객체 생성
		animal2 = new Animal(); //동물2 객체 생성
		
		this.add(player.getPlayerJlb());
		this.add(animal1.getAnimalJlb());
		this.add(animal2.getAnimalJlb());
		this.add(goal);
		
		playerTh = new PlayerThread(player); //플레이어 쓰레드 생성
		animalTh1 = new AnimalThread(animal1); //애니멀1 쓰레드 생성
		animalTh2 = new AnimalThread(animal2); //애니멀2 쓰레드 생성
		
		enemyThList = new ArrayList<EnemyThread>(); //적군 객체 리스트 생성, 적군 생성, 레이블 삽입, 쓰레드 시작
		for(int i=0; i<20; i++) {
			enemyThList.add(new EnemyThread(new Enemy()));
			this.add(enemyThList.get(i).getEnemy().getEnemyJlb());
			enemyThList.get(i).start();
		}
		
		playerTh.start();
		
		this.addKeyListener(new MoveListener()); //키보드 리스너
		this.setFocusable(true);
		this.requestFocus();
	}
	
	class MoveListener extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			player.setArrow(keyCode);//쓰레드에 방향 전달
			playerTh.interrupt(); //다시 실행
		}
	}

}

//------------------------------------쓰레드 클래스-----------------------------------------------
class PlayerThread extends Thread{
	private Player player;
	private boolean work;
	
	public PlayerThread(Player player) {
		this.player = player;
		work = false;
	}
	
	@Override
	public void run() {
		while(true) {
			//움직임--------------------------------------------
		}
	}
}

class AnimalThread extends Thread{
	private Animal animal;
	
	public AnimalThread(Animal animal) {
		this.animal = animal;
	}
	
	@Override
	public void run() {
		
	}
}

class EnemyThread extends Thread{
	private Enemy enemy;
	
	public EnemyThread(Enemy enemy) {
		this.enemy = enemy;
	}
	
	@Override
	public void run() {
		while(true) {
			try{
				Thread.sleep(100);
				enemy.MoveGameAi();
			}catch (InterruptedException e){return;}
		}
	}
	
	public Enemy getEnemy() {
		return this.enemy;
	}
}

//------------------------------------메인 클래스-------------------------------------------------
public class HuntGame extends JFrame{
	private MainPanel mainPanel;
	
	public HuntGame() {
		super("huntGame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel = new MainPanel();
		setContentPane(mainPanel);
		
		setSize(800, 600);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new HuntGame();
	}
}
