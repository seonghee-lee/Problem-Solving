package dp;

import java.util.*;
import java.io.*;

public class Main_S1_11052_카드구매하기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	//사려는 카드 수
		int[] arr = new int[N+1];	//카드팩 가격
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		//지불 금액이 최대가 되면서 카드 수가 N이 되도록 카드팩을 구매한다.
		int[] dp = new int[N+1];
		dp[1] = arr[1];
		
		for(int i = 2; i<=N; i++) {
			int cur = arr[i];
			int max = 0;
			for(int j = 1; j < i; j++) {
				max = Math.max(max, dp[j] + arr[i-j]);
			}
			max = Math.max(max, cur);
			dp[i] = max;
		}
		
		System.out.println(dp[N]);
		
	}

}
