package dp2;

import java.util.*;
import java.io.*;

public class Main_G4_18427_함께블록쌓기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nmh = br.readLine().split(" ");
		int N = Integer.parseInt(nmh[0]);
		int M = Integer.parseInt(nmh[1]);
		int H = Integer.parseInt(nmh[2]);
		
		List<Integer>[] student = new ArrayList[N+1];
		for(int i = 1; i<=N; i++) {
			student[i] = new ArrayList<>();
			String[] inputs = br.readLine().split(" ");
			for(int j = 0, len = inputs.length; j<len; j++) {
				student[i].add(Integer.parseInt(inputs[j]));
			}
			Collections.sort(student[i]);
		}
		
		long[][] dp = new long[N+1][H+1];
		for(int i = 0; i<=N; i++) {
			dp[i][0] = 1;
		}
		
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=H; j++) {
				dp[i][j] += dp[i-1][j];
				dp[i][j] %= 10007;
				for(Integer k : student[i]) {
					if(j < k) break;
					dp[i][j] += dp[i-1][j-k];
					dp[i][j] %= 10007;
				}
			}
		}
		System.out.print(dp[N][H]);
	}
}
