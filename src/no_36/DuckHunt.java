package no_36;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//------------------------------------------오브젝트 클래스------------------------------------//
class Duck{ //오리 객체
	private final ImageIcon duckImg = new ImageIcon("src/no_36/duck.jpg");
	private JLabel duckJlb;
	private int x, y;
	private int arrow; //오리의 움직임 방향
	private int ranCnt; //랜덤 카운트
	public boolean isCollistion; //충돌 여부 감지
	public boolean isLive; // 타켓팅 가능한 상태인가
	
	public Duck() { 
		isLive = true; //나 살았쪙!!
		isCollistion = false; //충돌하지 않음
		ranCnt = 0;
		duckJlb = new JLabel(duckImg);
		duckJlb.setSize(70, 70);
		duckJlb.setLocation(0, 0);
		duckJlb.setOpaque(true);
		duckJlb.setBackground(Color.yellow);
		duckJlb.setVisible(true);
		x = 0;
		y = 0;
	}
	
	public void DuckReset() {
		isLive = true; //나 살았쪙!!
		isCollistion = false; //충돌하지 않음
		ranCnt = 0;
		duckJlb.setSize(70, 70);
		duckJlb.setLocation(0, 0);
		duckJlb.setOpaque(true);
		duckJlb.setBackground(Color.yellow);
		duckJlb.setVisible(true);
		x = 0;
		y = 0;
	}
	
	public JLabel getDuckJlb() { //오리 레이블 반환
		return this.duckJlb;
	}
	
	public void setPosition(int x, int y) { //덕 레이블 포지션 설정
		this.x = x;
		this.y = y;
		duckJlb.setLocation(x, y);
	}
	
	public int getPosX() {
		return this.x;
	}
	
	public int getPosY() {
		return this.y;
	}
	
	public void aiMove() {
		ranCnt++;
		if(ranCnt == 10) {
			arrow = (int)(Math.random()*2);
			ranCnt = 0;
		}
		//경계선 체크
		if(this.getPosX() <= 0)
			arrow = 0;
		else if(this.getPosX() >= 630)
			arrow = 1;
		
		if(arrow == 0) { //오른쪽으로 이동
			this.setPosition(x + 10, y);
		}
		else if(arrow == 1) { //왼쪽으로 이동
			this.setPosition(x - 10, y);
		}
	}
}

class Gun{ //총 클래스
	private JLabel gunJlb;
	private int x, y;
	
	public Gun() {
		gunJlb = new JLabel();
		gunJlb.setSize(50, 50);
		gunJlb.setLocation(300, 610);
		gunJlb.setOpaque(true);
		gunJlb.setBackground(Color.black);
		x = 300;
		y = 610;
	}
	
	public JLabel getGunJlb() { //오리 레이블 반환
		return this.gunJlb;
	}
	
	public void setPosition(int x, int y) { //덕 레이블 포지션 설정
		this.x = x;
		this.y = y;
		gunJlb.setLocation(x, y);
	}
	
	public int getPosX() {
		return this.x;
	}
	
	public int getPosY() {
		return this.y;
	}
}

class Bullet{ //총알 클래스
	private JLabel bulletJlb;
	private int x, y;
	
	public Bullet(int x, int y) {
		this.x = x; this.y = y;
		bulletJlb = new JLabel();
		bulletJlb.setSize(10, 10);
		bulletJlb.setLocation(x, y);
		bulletJlb.setOpaque(true);
		bulletJlb.setBackground(Color.magenta);
	}
	
	public JLabel getBulletJlb() { //오리 레이블 반환
		return this.bulletJlb;
	}
	
	public void setPosition(int x, int y) { //덕 레이블 포지션 설정
		this.x = x;
		this.y = y;
		bulletJlb.setLocation(x, y);
	}
	
	public int getPosX() {
		return this.x;
	}
	
	public int getPosY() {
		return this.y;
	}
}

//------------------------------------------쓰레드 클래스------------------------------------//
class DuckThread extends Thread{ //오리 쓰레드
	private Duck duck;
	private boolean work;
	private Container c;
	private BulletThread bulletTh; //총알 쓰레드
	
	public DuckThread(Duck duck, Container c) {
		this.duck = duck;
		this.c = c;
		work = true;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				if(duck.isCollistion == true) { //충돌시 오리 객체 소멸후 재생성
					duck.getDuckJlb().setVisible(false);
					
					Thread.sleep(1000);//약간의 쿨타임
					duck.DuckReset(); //오리 객체 초기화
				}
				if(work == false) Thread.yield(); //잠시대기
				Thread.sleep(20);
				duck.aiMove();
			}catch(InterruptedException e) { return; }
		}
		
		
		//여기서 타켓이 이러나야함--------------------------------------------------------------------
		//즉 총알 쓰레드에 오리객체가 연결되어야함
	}
	
	public void setBulletThread(BulletThread bulletTh) { //총알 객체를 받아옴
		this.bulletTh = bulletTh;
	}
}

class GunThread extends Thread{ //총 쓰레드
	private Gun gun;
	public boolean work;
	private int arrow; //총의 방향
	
	public GunThread(Gun gun) {
		this.gun = gun;
		work = false;
	}
	
	@Override
	public void run() {
		while(true) {
			if(work) {
				if(arrow == KeyEvent.VK_LEFT) {
					gun.setPosition(gun.getPosX() - 10, gun.getPosY()); //오른쪽으로 이동
					work = false;
				} else if(arrow == KeyEvent.VK_RIGHT) {
					gun.setPosition(gun.getPosX() + 10, gun.getPosY()); //왼쪽으로 이동
					work = false;
				}
			}
			else {
				Thread.yield(); //실행 대기 상태로 만듦
			}
		}
	}
	
	public void setArrow(int arrow) {
		this.arrow = arrow;
	}
}

class BulletThread extends Thread{ //총알 쓰레드
	private Bullet bullet;
	private Duck duck;
	 
	public void setDuck(Duck duck) { //duck객체를 받아옴
		this.duck = duck;
	}
	
	public BulletThread(Bullet bullet, Duck duck) {
		this.bullet = bullet;
		this.duck = duck;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(10);
				bullet.setPosition(bullet.getPosX(), bullet.getPosY() - 5);
				
				if(isTerrgetting() == true) {
					System.out.println("targgetting!!");
					duck.isCollistion = true; //오리 객체에 충돌 정보를 전달
					//위 불린값이 전달 됨으로써 해당 쓰레드에서 소멸 작동
				}
				
				if(bullet.getPosY() <= 0) { //최상단까지 갈 경우 삭제
					this.bullet.getBulletJlb().setVisible(false); 
					this.bullet = null; //해당 객체의 레퍼런스 참조를 끊음, 가비지 컬렉션에 의해 자동으로 소멸
					return;
				}
				
			}catch(InterruptedException e) {
				return;
			} catch(NullPointerException e) {
				System.out.println("bullet이 null임");
				return;
			}
		}
	}
	
	private boolean isTerrgetting() { //총알 맞으면 트루리턴
		if(duck.getPosX() <= bullet.getPosX() &&  bullet.getPosX() <= duck.getPosX() + 70 &&
		   duck.getPosY() <= bullet.getPosY() && bullet.getPosY() <= duck.getPosY() + 70 &&
		   duck.isLive == true //지금은 살아있는 상태
		   ) 
		{ //x 범위
			duck.isLive = false; //나 죽었어 ㅠㅠ
			return true; //맞음 리턴
		}
		else return false; //안맞음
	}
}


//------------------------------------------메인 클래스------------------------------------//
public class DuckHunt extends JFrame
{
	private Duck duck; //오리 객체 선언
	private Gun gun; //총 객체 선언
	private Bullet bullet; //총알 객체 선언
	private DuckThread duckTh; //오리 쓰레드 선언
	private GunThread gunTh; //총 쓰레드 선언
	private BulletThread bulletTh; //총알 쓰레드 선언
	private Container c;

	public DuckHunt() 
	{
		super("duckHunt");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		c = getContentPane();
		c.setLayout(null);
		
		//필요한 객체 생성
		duck = new Duck(); //오리 객체 생성
		gun = new Gun(); //총 객체 생성
		
		//필요한 쓰레드 생성
		duckTh = new DuckThread(duck, c);
		gunTh = new GunThread(gun);
		
		//오브젝트 패널에 삽입
		c.add(duck.getDuckJlb());
		c.add(gun.getGunJlb());
		
		c.addKeyListener(new MainListener());
		
		setSize(700, 700);
		setVisible(true);
		c.setFocusable(true);
		c.requestFocus();
		
		//쓰레드 시작
		duckTh.start();
		gunTh.start();
	}

	class MainListener extends KeyAdapter
	{
		public void keyPressed(KeyEvent e) 
		{
			int keyCode = e.getKeyCode();
			
			switch(keyCode) 
			{
				case KeyEvent.VK_LEFT:
					gunTh.work = true;
					gunTh.setArrow(keyCode); //총 쓰레드에 방향 전달
					break;
				case KeyEvent.VK_RIGHT:
					gunTh.work = true;
					gunTh.setArrow(keyCode); //총 쓰레드에 방향 전달
					break;
				case KeyEvent.VK_UP:
					bullet = new Bullet(gun.getPosX() + 20, gun.getPosY()); //총알 객체 생성
					c.add(bullet.getBulletJlb());
					bulletTh = new BulletThread(bullet, duck); //총알 쓰레드 생성
					bulletTh.start();
					
					duckTh.setBulletThread(bulletTh); //오리 객체에 총알 객체 저장
					break;
				default: 
					break;
			}
		}
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		new DuckHunt();
	}
}
