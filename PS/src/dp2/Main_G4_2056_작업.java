package dp2;

import java.util.*;
import java.io.*;

public class Main_G4_2056_작업 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N+1];	//dp[i]: 작업i가 끝나는 시간
		
		String[] first = br.readLine().split(" ");
		int time = Integer.parseInt(first[0]);
		
		dp[1] = time;
		
		for(int i = 2; i<=N; i++) {
			String[] work = br.readLine().split(" ");

			time = Integer.parseInt(work[0]);	//작업 소요 시간
			int k = Integer.parseInt(work[1]);	//선행작업의 개수
			int max = 0;
			
			for(int j = 0; j<k; j++) {
				max = Math.max(max, dp[Integer.parseInt(work[2 + j])]);
			}
			
			dp[i] = max + time;
		}
		
		int ans = Integer.MIN_VALUE;
		for(int i = 1; i<=N; i++) {
			if(ans < dp[i]) ans = dp[i];
		}
		
		System.out.println(ans);
	}
}
