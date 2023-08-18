package dp2;

import java.util.*;
import java.io.*;

public class Main_G5_17265_나의인생에는수학과함께 {
	static int[] dr = {-1, 0};
	static int[] dc = {0, -1};	//위에서 오거나 왼쪽에서 오거나
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[][] map = new char[N+1][N+1];
		
		for(int i = 1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j<=N; j++) {
				map[i][j] = st.nextToken().charAt(0);
			}
		}
		
		int[][] maxDP = new int[N+1][N+1];
		int[][] minDP = new int[N+1][N+1];
		
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				maxDP[i][j] = Integer.MIN_VALUE;
				minDP[i][j] = Integer.MAX_VALUE;
			}
		}
		
		maxDP[1][1] = map[1][1] - '0';
		minDP[1][1] = map[1][1] - '0';
		
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				if(i == 1 && j == 1) continue;
				
				//CHAR TO INT
				int cur = map[i][j] - '0';
				
				if(map[i][j] >= '0' && map[i][j] <= '5') {	//숫자일때
					int nr, nc;
					for(int d = 0; d<2; d++) {
						nr = i + dr[d];
						nc = j + dc[d];
						if(nr <= 0 || nr > N || nc <= 0 || nc > N) continue;
						
						char op = map[nr][nc];	//연산자
						
						if(op == '+') {
							maxDP[i][j] = Math.max(maxDP[i][j], maxDP[nr][nc] + cur);
							minDP[i][j] = Math.min(minDP[i][j], minDP[nr][nc] + cur);
						} else if(op == '-') {
							maxDP[i][j] = Math.max(maxDP[i][j], maxDP[nr][nc] - cur);
							minDP[i][j] = Math.min(minDP[i][j], minDP[nr][nc] - cur);
						} else {
							maxDP[i][j] = Math.max(maxDP[i][j], maxDP[nr][nc] * cur);
							minDP[i][j] = Math.min(minDP[i][j], minDP[nr][nc] * cur);
						}
					}
				} else {	//연산자일때
					int nr, nc;
					maxDP[i][j] = Integer.MIN_VALUE;
					minDP[i][j] = Integer.MAX_VALUE;
					for(int d = 0; d<2; d++) {
						nr = i + dr[d];
						nc = j + dc[d];
						
						if(nr <= 0 || nr > N || nc <= 0 || nc > N) continue;
						
						maxDP[i][j] = Math.max(maxDP[nr][nc], maxDP[i][j]);
						minDP[i][j] = Math.min(minDP[nr][nc], minDP[i][j]);
					}
				}
			}
		}
		
		System.out.printf("%d %d", maxDP[N][N], minDP[N][N]);
	}
}
