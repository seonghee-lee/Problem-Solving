package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 문자열을 뒤집을 때 : 스트링버퍼/스트링빌더의 reverse() 메서드를 사용한다.
 * - 1) 아이디어내서 단순반복문으로 풀었더니 오답 -> 반례찾음. 오답일 수 밖에 없었다.
 * - 2) 일반적인 재귀는 시간초과..
 * - [찾아본 풀이] 1이랑 2를 합쳐야한다. 미친.
 * 
 * 제대로 된 해결책을 생각해내지 못한 이유는.
 * 문제의 전체적인 흐름을 관통하는 방법이 아닌 것들이였기 때문이다.
 * 문제에서 필수로 해결해야 했던 부분은.. 어떻게 될 지 모르는 가능성을 해결하는 것 -> 재귀로 모든 탐색을 해야한다.
 * 그 와중에 시간이 초과나지 않기 위해서는? 뒤에서부터 하면 되는지/안되는지를 확연히 판별할 수 있게 된다.(다른 가능성을 배제. 오로지 결과가 참/거짓인지 판단가능)
 * 이 두 방법을 합쳐서 해결할 생각을 했어야한다.
 */
public class Main_G5_12919_A와B2 {
	static int dist;
	static String before, after;
	static boolean possible;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		before = br.readLine();
		after = br.readLine();
		
		dist = after.length() - before.length();
		
		go(0, after);
		if(possible) System.out.println("1");
		else System.out.println("0");
	}
	
	private static void go(int cnt, String str) {

		if(str.length() == before.length()) {
			if(str.equals(before)) possible = true;
			return;
		}
		
		//맨 뒤 A를 뺀다.
		if(str.charAt(str.length()-1) == 'A') {
			go(cnt+1, str.substring(0, str.length()-1));
		}
			
		//뒤집고 맨 뒤 B를 뺀다.
		if(str.charAt(0) == 'B') {
			StringBuilder sb = new StringBuilder(str);
			str = sb.reverse().toString();
			go(cnt+1, str.substring(0, str.length()-1));
			
		}
	}
}
