package bruteforce;

import java.util.*;
import java.io.*;
/**
 * N: 500000 --> O(N) 가능
 * 전략: 최대한 가깝게 만들고, (+), (-)로 조정한다.
 * how?: 0~999999을 다 해본다. 각 번호가 최대한 가깝다고 가정한다.
 * 		 만들수 없다면: continue
 * 		 만들 수 있다면 : N과 차이를 계산 -> min 갱신
 */
public class Main_G5_1107_리모컨 {
	static boolean[] broken = new boolean[10];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int N = Integer.parseInt(input);
		int K = Integer.parseInt(br.readLine());
		if(K > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i<K; i++) {
				int n = Integer.parseInt(st.nextToken());
				broken[n] = true;
			}
		}
		
		if(N == 100) System.out.print(0);
		else {
			//모든 번호를 탐색했을 때의 최솟값
			int searchMin = Integer.MAX_VALUE;
			for(int i = 0; i<=999999; i++) {
				int result = pushable(i);
				if(result >= 0) {
					int diff = Math.abs(N - i);
					searchMin = Math.min(searchMin, diff + result);
				}
			}
			
			//숫자 버튼을 한 번도 누르지 않고, (+), (-) 로만 찾는 경우
			int moveMin = Math.abs(N - 100);
			
			System.out.println(Math.min(searchMin,  moveMin));
		}
	}
	
	private static int pushable(int n) {
		if(n == 0) {
			if(broken[n]) return -1;
			else return 1;
		}
		int res = 0;
		int length = 0;
		while(n > 0) {
			res  = n % 10;
			if(broken[res]) return -1;
			n /= 10;
			length++;
		}
		return length;
	}
}
