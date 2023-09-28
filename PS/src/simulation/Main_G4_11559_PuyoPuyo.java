package simulation;

import java.util.*;
import java.io.*;

public class Main_G4_11559_PuyoPuyo {
	static char[][] map = new char[12][6];
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i<12; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int chain = 0;
		while(true) {
			if(remove() > 0) {
				gravity();
				chain++;
			} else break;
		}
		System.out.println(chain);
	}
	
	private static int remove() {
		int count = 0;
		//같은색 뿌요가 4개 이상 상하좌우로 연결되어 있으면 없앤다.
		for(int i = 0; i<12; i++) {
			for(int j = 0; j<6; j++) {
				if(map[i][j] == '.') continue;
				if(go(i, j)) count++;
			}
		}
		return count;
	}
	
	private static boolean go(int r, int c) {
		char puyo = map[r][c];
		boolean[][] visited = new boolean[12][6];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {r, c});
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int nr, nc;
			for(int d = 0; d<4; d++) {
				nr = cur[0] + dr[d];
				nc = cur[1] + dc[d];
				if(nr < 0 || nr >= 12 || nc < 0 || nc >= 6 
						|| visited[nr][nc] || map[nr][nc] != puyo) continue;
				queue.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
			}
		}
		
		int count = 0;
		for(int i = 0; i<12; i++) {
			for(int j = 0; j<6; j++) {
				if(visited[i][j]) {
					count++;
				}
			}
		}
		
		if(count < 4) return false;
		
		for(int i = 0; i<12; i++) {
			for(int j = 0; j<6; j++) {
				if(visited[i][j]) {
					map[i][j] = '.';
				}
			}
		}
		return true;
	}
	
	private static void gravity() {
		for(int c = 0; c<6; c++) {
			int empty = 0;
			int r = 11;
			while(r >= 0) {
				if(map[r][c] != '.') {
					if(empty != 0) {
						map[r+empty][c] = map[r][c];
						map[r][c] = '.';
					}
				}
				else empty++;
				r--;
			}
		}
	}
}
