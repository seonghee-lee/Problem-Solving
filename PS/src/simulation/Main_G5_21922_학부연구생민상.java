package simulation;

import java.util.*;
import java.io.*;

public class Main_G5_21922_학부연구생민상 {
	static int N, M;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dr = {-1, 1, 0, 0};	//상, 하, 우, 좌
	static int[] dc = {0, 0, 1, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		map = new int[N+1][M+1];
		visited = new boolean[N+1][M+1][4];
		
		int startR = 0; int startC = 0;
		List<int[]> aircon = new ArrayList<>();
		
		StringTokenizer st;
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					aircon.add(new int[] {i, j});
				}
			}
		}
		
		for(int i = 0, size = aircon.size(); i<size; i++) {
			int[] cur = aircon.get(i);
			for(int d = 0; d<4; d++) {	
				go(cur[0], cur[1], d);
			}
		}
		
		int count = 0;
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=M; j++) {
				if(visited[i][j][0] || visited[i][j][1] || visited[i][j][2] || visited[i][j][3]) {
					count++;
				}
			}
		}
		
		System.out.println(count);
	}
	
	private static void go(int r, int c, int dir) {
		
		if(r <= 0 || r > N || c <= 0 || c > M || visited[r][c][dir]) return;
		
		visited[r][c][dir] = true;
		
		if(map[r][c] == 1) {
			if(dir <= 1) {
				go(r + dr[dir], c + dc[dir], dir);
			} else if(dir == 2) {
				go(r + dr[3], c + dc[3], 3);
			} else {
				go(r + dr[2], c + dc[2], 2);
			}
		} else if(map[r][c] == 2) {
			if(dir >= 2) {
				go(r + dr[dir], c + dc[dir], dir);
			} else if(dir == 0) {
				go(r + dr[1], c + dc[1], 1);
			} else {
				go(r + dr[0], c + dc[0], 0);
			}
		} else if(map[r][c] == 3) {
			int ndir = (dir + 2) % 4;
			go(r + dr[ndir], c + dc[ndir], ndir);
		} else if(map[r][c] == 4) {
			int ndir = 3 - dir;
			go(r + dr[ndir], c + dc[ndir], ndir);
		} else {
			go(r + dr[dir], c + dc[dir], dir);
		}
		
	}

}
