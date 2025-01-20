package no_04;

import java.util.*;

public class SumSame {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("정답을 입력하시오: ");
		int target = scanner.nextInt();
		System.out.println("배열의 크기를 입력하시오:");
		int index = scanner.nextInt();
		
		int []arr = new int[index];
		int i;
		System.out.println("배열의 입력값을 띄어쓰기를 활용하여 입력하시오:");
		for(i=0; i<index; i++) {
			arr[i] = scanner.nextInt();
		}
		System.out.println("결과: " + Arrays.toString(twoSum(arr, target)));
		
		scanner.close();
	}
	
	public static int[] twoSum(int[] nums, int target) {
		HashMap<Integer, Integer> hm = new HashMap();
		int ans[] = new int[2];
		
		for(int i = 0; i< nums.length; i++) {
			hm.put(nums[i], (target - nums[i]));
		}
		
		for(int i = 0; i <nums.length; i++) {
			for(int j = 0; j <nums.length; j++) {
				if(hm.get(nums[i]) == nums[j] && i != j) {
					ans[0] = i;
					ans[1] = j;
				}
			}
		}
		
		Arrays.sort(ans);
		return ans;
	}

}
