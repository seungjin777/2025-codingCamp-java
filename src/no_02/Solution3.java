package no_02;

import java.util.Arrays;
import java.util.Scanner;

public class Solution3 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("배열의 크기를 입력하시오: ");
		int index = scanner.nextInt();
		
		int[] arr = new int[index];
		int i;
		System.out.println("배열의 입력값을 띄어쓰기를 활용하여 입력하시오:");
		for(i = 0; i < index; i++) {
			arr[i] = scanner.nextInt();
		}
		
		System.out.println("결과: " + singleNumber(arr));
		
		scanner.close();
	}
	
	public static int singleNumber(int[] nums) {
		int i;
		Arrays.sort(nums);
		
		for(i=1; i<nums.length; i++) {
			if(nums[i-1] == nums[i]) {
				i++;
			}
			else {
				break;
			}
		}
		return nums[i-1];
	}
}
