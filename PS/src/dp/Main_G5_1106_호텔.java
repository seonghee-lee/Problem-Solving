package dp;

import java.util.*;
import java.io.*;

public class Main_G5_1106_호텔 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] cn = br.readLine().split(" ");
		int C = Integer.parseInt(cn[0]);
		int N = Integer.parseInt(cn[1]);
		
		int[] dp = new int[C+101];	//dp[i] : 고객을 i명 늘리는 데 드는 최소 비용
		Arrays.fill(dp, 10000000);
		dp[0] = 0;
		for(int i = 1; i<=N; i++) {
			String[] inputs = br.readLine().split(" ");
			int price = Integer.parseInt(inputs[0]);
			int people = Integer.parseInt(inputs[1]);
			
			for(int j = people, len = C+101; j<len; j++) {
				dp[j] = Math.min(dp[j], price + dp[j-people]);
			}
		}
		
		int min = Integer.MAX_VALUE;
		for(int i = C; i<= C+100; i++) {
			if(dp[i] < min) min = dp[i];
		}
		
		System.out.print(min);
	}

}
