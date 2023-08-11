package dp2;

import java.util.*;
import java.io.*;

public class Main_G5_14728_벼락치기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nt = br.readLine().split(" ");
		int N = Integer.parseInt(nt[0]);
		int T = Integer.parseInt(nt[1]);
		
		int[][] prob = new int[N+1][2];
		for(int i = 1; i<=N; i++) {
			String[] inputs = br.readLine().split(" ");
			prob[i][0] = Integer.parseInt(inputs[0]);
			prob[i][1] = Integer.parseInt(inputs[1]);
		}
		
		Arrays.sort(prob, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
		
		int minTime = prob[0][0];
		int[][] dp = new int[N+1][T+1];	//dp[i][j] : i과목까지 포함했을 때, j시간동안 얻을 수 있는 최대 점수
		
		for(int i = 1; i<=N; i++) {
			int time = prob[i][0];
			int score = prob[i][1];
			for(int j = minTime; j<=T; j++) {
				if(j-time < 0) dp[i][j] = dp[i-1][j];
				else{
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-time] + score);
				}
			}
		}
		
		System.out.print(dp[N][T]);
	}

}
