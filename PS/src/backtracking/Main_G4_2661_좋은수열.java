package backtracking;

import java.util.Scanner;


/**
 * 백트래킹이 어렵진 않았지만, 좋은 수열 판별하는 부분이 어려웠다.
 * 재귀에 string 넘기는 것도 다시 되새기면 좋을 것 같고.
 * 좋은 수열 판단하는 방법을 떠올리는게.. 쉽지는 않지만 그래도 노력해야지.
 */

public class Main_G4_2661_좋은수열 {
	static int N;
	
	public static void main(String[] args) throws Exception{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		go(0, "");
	}
	
	//수열에 들어갈 숫자 인덱스
	private static void go(int cnt, String numbers) {
		
		if(cnt == N) {
			System.out.println(numbers);
			System.exit(0);
			return;
		}
		
		//좋은 수열 만들기 : i는 수열에 들어갈 숫자이다.
		for(int i = 1; i<=3; i++) {
			//좋은 수열인지 판단하기 ; 좋은 수열이라면 재귀를 탄다.
			if(good(i, numbers)) {
				go(cnt+1, numbers + (i+""));
			}
		}
	}
	
	private static boolean good(int i, String numbers) {
		String temp = numbers + (i+"");
		
		int len = temp.length();
		for(int j = 0, comp = 1; j < len/2; j++, comp++) {
			String rear = temp.substring(len-comp, len);
			String front = temp.substring(len-comp*2, len-comp);
			
			if(rear.equals(front)) return false;
		}
		return true;
	}
}
