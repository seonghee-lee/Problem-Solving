package dp;

import java.util.*;
import java.io.*;

public class Main_S1_1495_기타리스트 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		int N = Integer.parseInt(inputs[0]);
		int S = Integer.parseInt(inputs[1]);
		int M = Integer.parseInt(inputs[2]);
		
		int[] arr = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean[][] dp = new boolean[N+1][M+1];	//dp[i][j]: i번 곡을 볼륨 j로 연주할 수 있다/없다.
		dp[0][S] = true;

		int minus, plus;
		for(int i = 1; i<=N; i++) {	//현재 연주하는 곡
			boolean possible = false;
			for(int j = 0; j<=M; j++) {	//모든 볼륨에 대해 반복한다.
				if(dp[i-1][j]) {	//바로 전 곡을 연주했던 볼륨이라면
					minus = j - arr[i];
					plus = j + arr[i];
					
					if(minus >= 0) {
						dp[i][minus] = true;
						possible = true;
					}
					if(plus <= M) {
						dp[i][plus] = true;
						possible = true;
					}
				}
			}
			if(!possible) {
				System.out.println(-1);
				return;
			}
		}
		
		for(int i = M; i>=0; i--) {
			if(dp[N][i]) {
				System.out.print(i);
				break;
			}
		}
	}
}
