package no_01;

public class RandomAvg {

	public static void main(String[] args) {
		
		int n = (int)(Math.random()*10);
		int k[] = new int[10];
		int sum = 0, avg;
		
		for(int i=0; i<k.length; i++) {
			k[i] = (int)(Math.random()*101 + 100);
			System.out.println((i+1) + ": " + k[i]);
		}
		System.out.println("");
		
		for(int i=0; i<k.length; i++) {
			sum += k[i];
		}
		
		avg = (int)(sum / (k.length));
		
		System.out.println("sum = " + sum + " avg = " + avg);
	}
}
