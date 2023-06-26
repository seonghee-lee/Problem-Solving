package backtracking;

import java.util.*;
import java.io.*;

public class Main_G4_1405_미친로봇 {
	static int N;
	static double result;
	static int[] prob;
	static int[] way;
	static boolean[][] visited;
	static int[] dr = {0, 0, 1, -1};	//동, 서, 남, 북
	static int[] dc = {1, -1, 0, 0};
	static int cnt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		prob = new int[4];
		String[] inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		for(int i = 1; i<=4; i++) {
			prob[i-1] = Integer.parseInt(inputs[i]);
		}
		
		visited = new boolean[1000][1000];
		way = new int[N];
		
		visited[500][500] = true;
		run(0, 500, 500);
		
		System.out.print(result);
	}
	
	private static void run(int count, int r, int c) {
		if(count == N) {
			double cur = 1.0;
			for(int i = 0; i<N; i++) {
				cur *= prob[way[i]] / 100.0;
			}
			
			result += cur;
			return;
		}
		
		int nr, nc;
		for(int i = 0; i<4; i++) {
			nr = r + dr[i];
			nc = c + dc[i];
			if(visited[nr][nc]) continue;
			
			visited[nr][nc] = true;
			way[count] = i;
			run(count+1, nr, nc);
			visited[nr][nc] = false;
		}
	}
}
