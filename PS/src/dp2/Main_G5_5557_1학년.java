package dp2;

import java.util.*;
import java.io.*;

public class Main_G5_5557_1학년 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = Integer.parseInt(st.nextToken());
		
		long[][] dp = new long[N][21];	//dp[i][j]: i인덱스까지 연산했을 때, 결과 j가 나오는 경우의 수
		dp[1][arr[1]] = 1;
		
		for(int i = 2; i<N; i++) {
			for(int j = 0; j<=20; j++) {
				long val1 = 0; 
				long val2 = 0;
				
				if(j-arr[i] >= 0 && j-arr[i] < 21) val1 = dp[i-1][j-arr[i]];
				if(j+arr[i] >= 0 && j+arr[i] < 21) val2 = dp[i-1][j+arr[i]];
				
				dp[i][j] = val1 + val2;
			}
		}
		
		System.out.print(dp[N-1][answer]);
	}

}
