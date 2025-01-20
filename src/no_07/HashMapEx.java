package no_07;

import java.util.*;

public class HashMapEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String city;
		String peo;
		String input;
		
		Scanner scanner = new Scanner(System.in);
		
		HashMap<String, String> hm = new HashMap();
		for(int i=0; i<10; i++) {
			System.out.print("도시와 인구 입력>> ");
			city = scanner.next();
			peo = scanner.next();
			hm.put(city, peo);
		}
		
		while(true) {
			System.out.print("인구수 궁금한 도시를 입력: ");
			input = scanner.next();
			if(input.equals("q")) break;
			
			System.out.println(input + "의 인구수 : " + hm.get(input));
		}
		System.out.println("종료");
		scanner.close();
	}
}
