package no_05;

import java.util.*;

public class Polygon {
	private int []xlist;
	private int []ylist;
	
	public Polygon() {
		xlist = new int[5];
		ylist = new int[5];
	}
	
	public Polygon(int x[], int y[]) {
		this.xlist = x;
		this.ylist = y;
	}
	
	public String toString() {
		return Arrays.toString(xlist) + ", " + Arrays.toString(xlist);
	}
	
	public boolean equals(int x[], int y[]) {
		int n = x.length;
		for(int i=0; i<n; i++) {
			if(x[i] != y[i]) {
				return false;
			}
		}
		return true;
	}
	
	public void set() {
		Arrays.sort(xlist);
		Arrays.sort(ylist);
	}
	
	public boolean containes(int x, int y) {
		int count = 0;
		for(int i = 0; i < xlist.length; i++) {
			if(x == xlist[i]) count++;
		}
		
		for(int i = 0; i < xlist.length; i++) {
			if(x == xlist[i]) count++;
		}
		
		if(count == 2) return true;
		else return false;
	}
	
	public static void main(String[] args) {
		int x[] = {1,2,3,4,5};
		int y[] = {6,7,8,9,10};
		new Polygon(x, y);
	}

}
