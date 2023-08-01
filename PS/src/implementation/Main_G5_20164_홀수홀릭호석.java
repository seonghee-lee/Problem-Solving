package implementation;

import java.io.*;

public class Main_G5_20164_홀수홀릭호석 {
	static int min, max;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputs = br.readLine();
		
		int[] start = new int[inputs.length()];
		
		int odd = 0;
		for(int i = 0, len = inputs.length(); i<len; i++) {
			start[i] = inputs.charAt(i) - '0';
			if(start[i] % 2 == 1) {
				odd++;
			}
		}
		
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		
		go(inputs, odd);
		
		System.out.printf("%d %d", min, max);
	}
	
	private static void go(String number, int count) {
		if(number.length() == 1) {
			
			min = Math.min(min, count);
			max = Math.max(max, count);
			
			return;
		}
		
		else if(number.length() == 2) {
			int calc = (number.charAt(0) - '0') + (number.charAt(1) - '0');
			String newNum = calc + "";
			
			int odd = countOdd(newNum);
			
			go(newNum, count + odd);
		}
		
		else {
			int length = number.length();
			for(int i = 0; i<length-1; i++) {
				for(int j = i+1; j<length-1; j++) {
					
					int num1 = makeNum(0, i, number);
					int num2 = makeNum(i+1, j, number);
					int num3 = makeNum(j+1, length-1, number);
					
					int calc = num1 + num2 + num3;
					String newNum= calc + "";
					
					int odd = countOdd(newNum);
					
					go(newNum, count + odd);
				}
			}
		}
	}
	
	private static int makeNum(int start, int end, String number) {
		String made = "";
		for(int i = start; i<=end; i++) {
			made += number.charAt(i);
		}
		
		return Integer.parseInt(made);
	}
	
	private static int countOdd(String number) {
		int odd = 0;
		for(int i = 0, len = number.length(); i<len; i++) {
			int cur = number.charAt(i) - '0';
			if(cur % 2 != 0) odd++;
		}
		return odd;
	}
}
