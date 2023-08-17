package dp2;

import java.util.*;
import java.io.*;

public class Main_G5_2229_조짜기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] scores = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i<=N; i++) {
			scores[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N+1];	//dp[i]: i번째 학생까지 조를 짰을 때, 조가 잘 짜여진 정도의 최댓값
		
		for(int i = 1; i<=N; i++) {
			int max = -1;	int min = 10001;
			
			for(int j = i; j > 0; j--) {	//이전 사람들과 조를 짰을 때를 앞으로 한명씩 늘려가면서 해본다.
				max = Math.max(max, scores[j]);
				min = Math.min(min, scores[j]);
				
				dp[i] = Math.max(dp[i], max - min + dp[j-1]);
			}
		}
		
		System.out.print(dp[N]);
	}
}
