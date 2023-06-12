package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * [오답노트]
 * 더하기 연산은 *1을 해서 더하면 되고, 빼기 연산은 *(-1)을해서 더하면 된다.
 * 이걸 생각해내면 조금 더 수월했을지도..
 * 그리고 구현 실력 부족도 한 몫 했다. 구현 연습을 많이 해봐야 할 것 같다. . .
 */

public class Main_G5_7490_0만들기 {
	static int N;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			sb = new StringBuilder(100);
			go(1, 1, 0, 1, "1");
			System.out.println(sb);
		}
		
	}
	
	//cnt: 재귀 횟수, madeNum: 현재 만들어진 숫자, sum: 합계, opt: 연산자(1:+, -1:-, " ":공백), express: 식
	private static void go(int cnt, int madeNum, int sum, int opt, String express) {
		if(cnt == N) {
			sum = sum + (opt * madeNum);
			if(sum == 0) {
				sb.append(express).append("\n");
			}
			return;
		}
		
		//정렬
		//공백연산
		go(cnt+1, madeNum*10 + (cnt+1), sum, opt, express+" "+Integer.toString(cnt+1));
		//더하기연산
		go(cnt+1, cnt+1, sum + (opt * madeNum), 1, express+"+"+Integer.toString(cnt+1));
		//빼기연산
		go(cnt+1, cnt+1, sum + (opt * madeNum), -1, express+"-"+Integer.toString(cnt+1));
	}
	
	
}
