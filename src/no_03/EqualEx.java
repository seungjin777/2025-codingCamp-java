package no_03;

import java.util.Scanner;

public class EqualEx {

	public static boolean equalInt(int a[], int b[]) {
		
		for(int i = 0; i<a.length; i++) {
			if(a[i] != b[i]) return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("배열의 크기를 입력하시오: ");
		int index = scanner.nextInt();
		
		int[] a = new int[index];
		int[] b = new int[index];
		int i;
		System.out.println("A 배열의 입력값을 띄어쓰기를 활용하여 입력하시오:");
		for(i = 0; i < index; i++) {
			a[i] = scanner.nextInt();
		}
		
		System.out.println("B 배열의 입력값을 띄어쓰기를 활용하여 입력하시오:");
		for(i = 0; i < index; i++) {
			b[i] = scanner.nextInt();
		}
		
		if(equalInt(a, b) == true) {
			System.out.println("==");
		}
		else
			System.out.println("!=");
		
		scanner.close();
		
	}
}
