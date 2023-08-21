package dp2;

import java.util.*;
import java.io.*;

public class Main_G5_16400_소수화폐 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		//N이하의 소수들
		boolean[] isPrime = new boolean[N+1];
		Arrays.fill(isPrime, true);
		
		isPrime[1] = false;
		
		for(int i = 2; i<=N; i++) {
			if(isPrime[i]) {
				for(int j = i * i; j<=N; j++) {
					if(j % i == 0) isPrime[j] = false;
				}
			}
		}
		
		List<Integer> money = new ArrayList<>();	//화폐
		for(int i = 2; i<=N; i++) {
			if(isPrime[i]) money.add(i);
		}
		
		long[] dp = new long[N+1];	//dp[i]: i원을 지불하기 위한 경우의 수
		dp[0] = 1;
		
		for(int i = 0, len = money.size(); i<len; i++) {
			int cur = money.get(i);
			
			for(int j = cur; j<=N; j++) {
				dp[j] = (dp[j] + dp[j - cur]) % 123456789;
			}
		}
		
		System.out.println(dp[N]);
		
	}

}
