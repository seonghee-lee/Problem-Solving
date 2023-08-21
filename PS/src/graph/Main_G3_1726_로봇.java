package graph;

import java.util.*;
import java.io.*;

public class Main_G3_1726_로봇 {
	static int N, M, min;
	static int startR, startC, startDir;
	static int endR, endC, endDir;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dr = {0, 0, 0, 1, -1};
	static int[] dc = {0, 1, -1, 0, 0};
	static Queue<Robot> queue;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		
		map = new int[N+1][M+1];
		visited = new boolean[N+1][M+1][5];
		
		StringTokenizer st;
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	
		String[] sinfo = br.readLine().split(" ");
		startR = Integer.parseInt(sinfo[0]);
		startC = Integer.parseInt(sinfo[1]);
		startDir = Integer.parseInt(sinfo[2]);
		
		String[] einfo = br.readLine().split(" ");
		endR = Integer.parseInt(einfo[0]);
		endC = Integer.parseInt(einfo[1]);
		endDir = Integer.parseInt(einfo[2]);
		
		min = Integer.MAX_VALUE;
		bfs();
		
		System.out.print(min);
		
	}
	
	private static void bfs() {
		queue = new ArrayDeque<>();
		queue.offer(new Robot(startR, startC, startDir, 0, 0));
		visited[startR][startC][startDir] = true;
		
		while(!queue.isEmpty()) {
			Robot cur = queue.poll();
			
			if(cur.r == endR && cur.c == endC) {
				int ans = -1;
				
				if(cur.dir != endDir) {
					if((cur.dir == 1 && endDir == 2)
					|| (cur.dir == 2 && endDir == 1)
					|| (cur.dir == 3 && endDir == 4)
					|| (cur.dir == 4 && endDir == 3)) {
						ans = cur.turn + 2 + cur.dist;
					} else {
						ans = cur.turn + 1 + cur.dist;
					}
				} else {
					ans = cur.turn + cur.dist;
				}
				
				min = Math.min(min, ans);
			}
			
			move(cur);
			
			turnLeft(cur);
			
			turnRight(cur);
		}
	}
	
	private static void move(Robot cur) {
		
		int nr, nc;
		for(int k = 1; k<=3; k++) {
			nr = cur.r + dr[cur.dir] * k;
			nc = cur.c + dc[cur.dir] * k;
			
			if(nr <= 0 || nr > N || nc <= 0 || nc > M || map[nr][nc] == 1) break;
			
			if(visited[nr][nc][cur.dir]) continue;
			
			queue.offer(new Robot(nr, nc, cur.dir, cur.turn, cur.dist + 1));
			visited[nr][nc][cur.dir] = true;
		}
	}
	
	private static void turnLeft(Robot cur) {
		
		int newDir = 0;
		
		if(cur.dir == 1) newDir = 4;
		else if(cur.dir == 2) newDir = 3;
		else if(cur.dir == 3) newDir = 1;
		else newDir = 2;
		
		if(visited[cur.r][cur.c][newDir]) return;
		
		queue.offer(new Robot(cur.r, cur.c, newDir, cur.turn + 1, cur.dist));
		visited[cur.r][cur.c][newDir] = true;
	}
	
	private static void turnRight(Robot cur) {
		
		int newDir = 0;
		
		if(cur.dir == 1) newDir = 3;
		else if(cur.dir == 2) newDir = 4;
		else if(cur.dir == 3) newDir = 2;
		else newDir = 1;
		
		if(visited[cur.r][cur.c][newDir]) return;
		
		queue.offer(new Robot(cur.r, cur.c, newDir, cur.turn + 1, cur.dist));
		visited[cur.r][cur.c][newDir] = true;
	}
}

class Robot{
	int r, c, dir, turn, dist;
	Robot(int r, int c, int dir, int turn, int dist){
		this.r = r;
		this.c = c;
		this.dir = dir;
		this.turn = turn;
		this.dist = dist;
	}
}
