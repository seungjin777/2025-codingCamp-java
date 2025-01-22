package no_26;


interface AdderInterface{
	int add(int x, int y);
	int add(int n);
}

public class MyAdder implements AdderInterface{
	
	@Override
	public int add(int x, int y) {
		return x + y;
	}
	
	@Override
	public int add(int n) {
		int sum = 0;
		for(int i = 1; i <= n; i++) {
			sum += i;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyAdder adder = new MyAdder();
		System.out.println(adder.add(5, 10));
		System.out.println(adder.add(10));
	}
}
