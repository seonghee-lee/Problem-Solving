package dp2;

import java.util.*;
import java.io.*;

public class Main_G5_12865_평범한배낭 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nk = br.readLine().split(" ");
		int N = Integer.parseInt(nk[0]);	//물품 수
		int K = Integer.parseInt(nk[1]);	//배낭 용량
		
		int[] weights = new int[N+1];
		int[] values = new int[N+1];
		
		for(int i = 1; i<=N; i++) {
			String[] wv = br.readLine().split(" ");
			weights[i] = Integer.parseInt(wv[0]);
			values[i] = Integer.parseInt(wv[1]);
		}
		
		long[][] dp = new long[N+1][K+1];		//dp[i][j] : j까지 고려했을 때(담거나,안 담거나) i용량 배낭에 담을 수 있는 최대 가치
		for(int i = 1; i<=N; i++) {
			int w = weights[i];	//현재 물건의 무게
			int v = values[i];	//현재 물건의 가치
			
			for(int k = 1; k<=K; k++) {	//배낭의 용량을 1씩 키워가며 물건을 담아본다.
				if(w > k) dp[i][k] = dp[i-1][k];	//현재 물건을 담을 수 없으면, 이전 물건을 담았던 결과를 그대로 사용한다.
				else {
					dp[i][k] = Math.max(v + dp[i-1][k-w], dp[i-1][k]);	//현재 물건을 담았을 때와 안 담았을 때 중 큰 값을 사용한다.
				}
			}
		}

		System.out.print(dp[N][K]);
	}

}
