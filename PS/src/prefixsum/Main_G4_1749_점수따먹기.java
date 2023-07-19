package prefixsum;

import java.util.*;
import java.io.*;

public class Main_G4_1749_점수따먹기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		int N = Integer.parseInt(nm[0]);
		int M = Integer.parseInt(nm[1]);
		int[][] arr = new int[N+1][M+1];
		
		StringTokenizer st;
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//누적합 배열 구하기
		long[][] sum = new long[N+1][M+1];
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=M; j++) {
				sum[i][j] = arr[i][j] + sum[i][j-1];
			}
		}
		
		for(int j = 1; j<=M; j++) {
			for(int i = 1; i<=N; i++) {
				sum[i][j] += sum[i-1][j];
			}
		}
		
		long max = Long.MIN_VALUE;
		for(int r1 = 1; r1 <=N; r1++) {
			for(int c1 = 1; c1 <= M; c1++) {
				for(int r2 = r1; r2 <= N; r2++) {
					for(int c2 = c1; c2<=M; c2++) {
						long cur = sum[r2][c2] - sum[r2][c1-1] - sum[r1-1][c2] + sum[r1-1][c1-1];
						if(cur > max) max = cur;
					}
				}
			}
		}
		
		System.out.print(max);
	}

}
