package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * true, false 배열을 사용하는 방법도 있다는 걸 잊지 말자!
 * 조합에서 start하는거 1더해줄 때 "i+1"이다ㅠㅠㅠㅠ !!!
 */
public class Main_G4_1062_가르침 {
	static int N, K, max;
	static char[][] words;
	static boolean[] alpha;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		K = Integer.parseInt(inputs[1]);
		
		words = new char[N][];
		for(int i = 0; i<N; i++) {
			words[i] = br.readLine().toCharArray();
		}
		
		alpha = new boolean[26];
		//a n t i c 를 true로 변경
		alpha['a'-'a'] = true;
		alpha['n'-'a'] = true;
		alpha['t'-'a'] = true;
		alpha['i'-'a'] = true;
		alpha['c'-'a'] = true;
		
		if(K - 5 < 0) System.out.println("0");
		else if(K == 26) System.out.println(N);
		else {
			max = Integer.MIN_VALUE;
			go(0, 0);
			System.out.println(max);
		}
		
	}

	private static void go(int cnt, int start) {
		if(cnt == K-5) {
			boolean possible;
			int count = 0;
			for(int i = 0; i<N; i++) {
				possible = true;
				for(int j = 0, len = words[i].length; j<len; j++) {
					if(!alpha[words[i][j] - 'a']) {
						possible = false;
						break;
					}
				}
				if(possible) count++;
			}
			max = Math.max(max, count);
			
			return;
		}

		for(int i = start; i<26; i++) {
			if(alpha[i]) {
				continue;
			}else {
				alpha[i] = true;
				go(cnt+1, i+1);
				alpha[i] = false;
			}
		}
	}
}

