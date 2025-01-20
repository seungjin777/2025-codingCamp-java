package no_06;
import java.util.*;

public class ArrayInt {
	
	public static void copy(int a[], int b[]) { //a배열 b배열에 복사
		
		for(int i = 0; i < b.length; i++) {
			if(i >= a.length) {
				b[i] = 0;
			}
			else {
				b[i] = a[i];
			}
		}
		
		if(a.length > b.length) {
			System.out.println("범위 초과 뒤 짤림");
		}
	}
	
	public static boolean compare(int a[], int b[]) { //두 배열 비교 함수
		int len = a.length;
		
		for(int i=0; i<len; i++) {
			if(a[i] != b[i]) return false;
		}
		return true;
	}
	
	public static int sum(int a[]) { //배열 원소의 합
		int add = 0;
		for(int i=0; i<a.length; i++) {
			add += a[i];
		}
		return add;
	}
	
	public static String toString(int a[]) { //배열 스트링으로 리턴
		StringBuffer str = new StringBuffer("[");
		str.append(a[0]);
		for(int i=1; i<a.length; i++) {
			str.append(", " + Integer.toString(a[i]));
		}
		str.append("]");
		return str.toString();
	}
	
	public static void sort(int a[]) { //정렬
		for(int i=0; i<a.length - 1; i++) {
			int max = i;
			for(int j=i + 1; j<a.length; j++) {
				if(a[j] < a[max]) {
					max = j;
				}
			}
		}
	}
	
	public void swap(int a, int b, int tmp) {
		tmp = a;
		a = b;
		b = tmp;
	}
	
	public static int[] merge(int a[], int b[]) { //두 배열 합병
		int len = a.length + b.length;
		int arr[] = new int[len];
		
		for(int i = 0; i<a.length; i++) {
			arr[i] = a[i];
		};
		
		for(int i = a.length; i<b.length; i++) {
			arr[i] = b[i];
		};
		
		return arr;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayInt ai = new ArrayInt();
		
	}

}
