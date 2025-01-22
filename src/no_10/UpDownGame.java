package no_10;
import java.util.*;

public class UpDownGame {
	private int ranNum; //정답
	private int startNum; //시작 숫자
	private int endNum; //끝 숫자
	private int count; //시도 횟수
	private int input; //입력 
	
	public UpDownGame() {
		input = -1;
	}
	
	private void settingRand() {
		ranNum = (int)(Math.random()*100);
		startNum = 0;
		endNum = 99;
		count = 1;
		input = -1;
	}
	
	public void run() {
		Scanner scanner = new Scanner(System.in);
		String op = "y";
		
		settingRand();
		System.out.println("수를 결정하였습니다. 맞추어 보세요");
		
		while(true) {
			System.out.println(Integer.toString(startNum) + "-" + Integer.toString(endNum));
			System.out.print(count + ">> ");
			input = scanner.nextInt();
			
			if(input == ranNum) {
				System.out.println("맞았습니다.");
				System.out.print("다시하겠습니까?(y/n)>> ");
				op = scanner.next();
				if(op.equals("n")) return;
				settingRand();
				System.out.println("수를 결정하였습니다. 맞추어 보세요");
			}else if(input < ranNum) {
				startNum = input;
				System.out.println("더 높게");
				count++;
			}else if(input > ranNum) {
				endNum = input;
				System.out.println("더 낮게");
				count++;
			}
		}
		
		//scanner.close();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UpDownGame game = new UpDownGame();
		game.run();
	}
}
