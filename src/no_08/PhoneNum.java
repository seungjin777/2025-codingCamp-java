package no_08;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class PhoneNum {
	private HashMap<String, String> hm;
	private String name, pNum;
	
	public PhoneNum() {
		hm = new HashMap();
	}
	
	public String getNum(String name) {
		return hm.get(name);
	}
	
	public void redFile(String path) {
		String inputLine = "";
		try {
			File file = new File(path);
			FileReader fin = new FileReader(file);
			BufferedReader br = new BufferedReader(fin);
			String line = "";
			while((line = br.readLine()) != null) {
				inputLine = line;
				String [] keyVal = inputLine.split(" ");
				hm.put(keyVal[0], keyVal[1]);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("not found file");
		} catch (IOException e) {
			System.out.println("not found file");
			return;
		}
	}
	
	public static void main(String[] args) {
		PhoneNum pn = new PhoneNum();
		pn.redFile("src/no_08/pnum.txt");
		System.out.println("a의 번호 : " + pn.getNum("a"));
	}		
}
