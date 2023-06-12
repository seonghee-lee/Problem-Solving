package dp;

import java.util.*;
import java.io.*;

public class Main_S1_12101_123더하기2_재귀 {
	static int N, K, count;
	static boolean possible;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		K = Integer.parseInt(inputs[1]);
		
		go(0, "");
		if(!possible) System.out.println(-1);
	}
	
	private static void go(int sum, String fomula) {
		if(sum == N) {
			count++;
			if(count == K) {
				//정답 출력
				System.out.println(fomula.substring(1));
				possible = true;
			}
			return;
		}
		
		for(int i = 1; i<=3; i++) {
			if(sum + i > N) continue;
			go(sum + i, fomula + "+" + i);
		}
	}

}

