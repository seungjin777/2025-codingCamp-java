package no_29;
import java.util.*;


class Position{
	private int x;
	private int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
}

public class Location {
	private ArrayList<Position> trace = new ArrayList<>();
	private Position p;
	private int x, y;
	private double totalDist = 0;
	
	public Location() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("쥐가 이동한 위치 (x, y)를 5개 입력하시오.");
		for(int i=0; i<5; i++) {
			System.out.print(">> ");
			x = scanner.nextInt();
			y = scanner.nextInt();
			p = new Position(x, y);
			trace.add(p);
		}
		sumDist();
		System.out.println("총 이동 거리는 " + totalDist);
		scanner.close();
	}
	
	public void sumDist() {
		int x1, y1, x2, y2;
		double dist;
		for(int i =1; i<trace.size(); i++) {
			x1 = trace.get(i-1).getX();
			y1 = trace.get(i-1).getY();
			
			x2 = trace.get(i).getX();
			y2 = trace.get(i).getY();
			
			dist = Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
			totalDist += dist;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Location();
	}
}
