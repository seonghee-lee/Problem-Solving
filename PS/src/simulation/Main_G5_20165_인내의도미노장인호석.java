package simulation;

import java.util.*;
import java.io.*;

public class Main_G5_20165_인내의도미노장인호석 {
	static int N, M, R, score;
	static int[][] original, map;
	static int[] dr = {0, 0, 1, -1};	//동, 서, 남, 북
	static int[] dc = {1, -1, 0, 0};
	static Queue<Domino> queue = new ArrayDeque<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nmr = br.readLine().split(" ");
		N = Integer.parseInt(nmr[0]);
		M = Integer.parseInt(nmr[1]);
		R = Integer.parseInt(nmr[2]);
		
		map = new int[N+1][M+1];
		original = new int[N+1][M+1];
		
		StringTokenizer st;
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				original[i][j] = map[i][j];
			}
		}
		
		for(int i = 1; i<=R; i++) {
			queue.clear();
			
			String[] att = br.readLine().split(" ");
			String[] def = br.readLine().split(" ");
			
			attack(Integer.parseInt(att[0]), Integer.parseInt(att[1]), att[2]);
			
			defense(Integer.parseInt(def[0]), Integer.parseInt(def[1]));
			
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(score).append("\n");
		
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=M; j++) {
				if(map[i][j] == 0) sb.append("F ");
				else sb.append("S ");
			}sb.append("\n");
		}
		
		
		System.out.print(sb);
	}
	
	private static void attack(int r, int c, String d) {
		int dir = -1;
		if(d.equals("E")) dir = 0;
		else if(d.equals("W")) dir = 1;
		else if(d.equals("S")) dir = 2;
		else dir = 3;
		
		queue.offer(new Domino(r, c, map[r][c]));
		
		while(!queue.isEmpty()) {
			Domino cur = queue.poll();
			
			if(map[cur.r][cur.c] == 0) continue;
			
			score++;
			map[cur.r][cur.c] = 0;
			
			int nr, nc;
			for(int i = 1; i<cur.k; i++) {
				nr = cur.r + dr[dir] * i;
				nc = cur.c + dc[dir] * i;
				
				if(nr <= 0 || nr > N || nc <= 0 || nc > M) break;
				
				queue.offer(new Domino(nr, nc, map[nr][nc]));
			}
		}
	}
	
	private static void defense(int r , int c) {
		map[r][c] = original[r][c];
	}

}

class Domino {
	int r, c, k;
	Domino(int r, int c, int k){
		this.r = r;
		this.c = c;
		this.k = k;
	}
}
