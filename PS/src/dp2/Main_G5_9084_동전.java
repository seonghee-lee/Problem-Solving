package dp2;

import java.util.*;
import java.io.*;

public class Main_G5_9084_동전 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());	//동전의 가지수
			int[] coin = new int[N+1];	//화폐 단위 저장
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 1; i<=N; i++) {
				coin[i] = Integer.parseInt(st.nextToken());
			}
			
			int target = Integer.parseInt(br.readLine());	//목표 금액
			int[] dp = new int[target + 1];
			dp[0] = 1;
			
			for(int c = 1; c<=N; c++) {
				int curCoin = coin[c];	//사용하는 동전
				
				for(int i = 1; i<=target; i++) {	//i: 만드는 금액
					if(curCoin > i) continue;
					dp[i] += dp[i - curCoin];
				}
			}
			
			sb.append(dp[target]).append("\n");
		}
		
		System.out.print(sb);
	}

}
