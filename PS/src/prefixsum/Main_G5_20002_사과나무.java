package prefixsum;

import java.util.*;
import java.io.*;

public class Main_G5_20002_사과나무 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N+1][N+1];
		for(int i = 1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[N+1][N+1];
		//행 연산
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				dp[i][j] = dp[i][j-1] + map[i][j];
			}
		}
		
		//열 연산
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				dp[j][i] = dp[j-1][i] + dp[j][i]; 
			}
		}
		
		int max = Integer.MIN_VALUE;
		//정사각형의 시작점 결정 -> 끝점은 자동 결정
		for(int r1 = 1; r1<=N; r1++) {
			for(int c1 = 1; c1<=N; c1++) {
				for(int k = 0; k<=N; k++) {	//정사각형 한 변의 길이
					int r2 = r1 + k;
					int c2 = c1 + k;
					if(r2 > N || c2 > N) break;
					int sum = dp[r2][c2] - dp[r2][c1-1] - dp[r1-1][c2] + dp[r1-1][c1-1];
					max = Math.max(max, sum);
				}
			}
		}
		System.out.print(max);
	}

}
