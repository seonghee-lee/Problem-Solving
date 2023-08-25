package graph;

import java.util.*;
import java.io.*;

public class Main_G3_2234_성곽 {
	static int N, M;
	static int[][] map, origin;
	static boolean[][] visited;
	static boolean[][][] visited2;
	static int[] dr = {1, 0, -1, 0};	//남, 동, 북, 서
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		M = Integer.parseInt(nm[0]);
		N = Integer.parseInt(nm[1]);
		map = new int[N*2][M*2];
		origin = new int[N][M];
		StringTokenizer st;
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				origin[i][j] = map[i][j];
			}
		}
		
		visited = new boolean[N][M];
		int room = 0;
		int maxRoomSize = -1;
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(visited[i][j]) continue;
				int size = bfs(i, j);
				room++;
				if(size > maxRoomSize) maxRoomSize = size;
			}
		}
		
		visited2 = new boolean[N][M][4];
		int maxRoomSize2 = -1;
		
		//하나의 벽을 제거했을 때: 해당 칸에서 벽이 있을 때만 부셔보고 간다.
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				boolean[] wall = findWall(i, j);
				
				for(int k = 0; k<4; k++) {
					if(wall[k]) {
						int nr = i + dr[k];
						int nc = j + dc[k];
						
						int newDir = -1;
						if(k == 0) newDir = 2;
						else if(k == 1) newDir = 3;
						else if(k == 2) newDir = 0;
						else newDir = 1;
						
						if(nr < 0 || nr >= N || nc < 0 || nc >= M
								|| visited2[i][j][k] || visited2[nr][nc][newDir]) {
							continue;
						}
						
						visited2[i][j][k] = true;
						visited2[nr][nc][newDir] = true;
						
						for(int p = 0; p < N; p++) {
							for(int q = 0; q<M ; q++) {
								visited[p][q] = false;
							}
						}
						
						//맵 변환
						if(k == 0) {
							map[i][j] -= 8;
							map[nr][nc] -= 2;
						} else if(k == 1) {
							map[i][j] -= 4;
							map[nr][nc] -= 1;
						} else if(k == 2) {
							map[i][j] -= 2;
							map[nr][nc] -= 8;
						} else {
							map[i][j] -= 1;
							map[nr][nc] -= 4;
						}
						
						int size = bfs(i, j);
						
						if(size > maxRoomSize2) maxRoomSize2 = size;
						
						map[i][j] = origin[i][j];
						map[nr][nc] = origin[nr][nc];
					}
				}
			}
		}
		System.out.printf("%d\n%d\n%d", room, maxRoomSize, maxRoomSize2);
	}
	
	private static boolean[] findWall(int r, int c) {
		boolean[] wall = new boolean[4];
		
		int num = map[r][c];
		int mul = 3;
		for(int i = 0; i<4; i++) {
			int binum = (int)Math.pow(2, mul);
			if(num < binum) {
				mul--;
				continue;
			}
			wall[i] = true;
			num -= binum;
			mul--;
		}
		
		return wall;
	}
	
	private static int bfs(int startR, int startC) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {startR, startC});
		visited[startR][startC] = true;
		int size = 1;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			boolean[] wall = findWall(cur[0], cur[1]);

			int nr, nc;
			for(int d = 0; d<4; d++) {
				if(!wall[d]) {
					nr = cur[0] + dr[d];
					nc = cur[1] + dc[d];
					
					if(nr < 0 || nr >= N || nc < 0 || nc >= M || visited[nr][nc]) continue;
					queue.offer(new int[] {nr, nc});
					visited[nr][nc] = true;
					size++;
				}
			}
		}
		
		return size;
	}
}
