package dp2;

import java.util.*;
import java.io.*;

public class Main_G5_4811_알약 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputs = "";
		StringBuilder sb = new StringBuilder();
		
		long[][] dp = new long[32][32];	//dp[w][h]: W가 w개 남고, H가 h개 남았을 때 가능한 문자열 수
		
		//w가 0개 남아있다면, h의 개수와 상관없이 모두 1 : hhhh, hhhhh, hhhhhhhh....
		for(int h = 1; h<=30; h++) {
			dp[0][h] = 1;
		}
		
		for(int w = 1; w<=30; w++) {
			for(int h = 0; h<=30; h++) {
				if(h == 0) {
					dp[w][0] = dp[w-1][1];
				} else {
					dp[w][h] = dp[w-1][h+1] + dp[w][h-1];
				}
			}
		}
		
//		for(int w = 1; w<=30; w++) {
//			for(int h = 0; h<=30; h++) {
//				System.out.print(dp[w][h]+" ");
//			}System.out.println();
//		}
		
		while((inputs = br.readLine()) != null) {
			int N = Integer.parseInt(inputs);
			if(N == 0) break;
			sb.append(dp[N][0]).append("\n");
		}
		
		System.out.print(sb);
	}

}

