package graph;

import java.util.*;
import java.io.*;

public class Main_G4_16973_직사각형탈출 {
	static int N, M, H, W, startR, startC, endR, endC;
	static int[][] map, cost;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static List<Rec> wall = new ArrayList<>();
	static boolean reach;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		
		map = new int[N+1][M+1];
		cost = new int[N+1][M+1];
		visited = new boolean[N+1][M+1];
		for(int i = 1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					wall.add(new Rec(i, j));
				}
			}
		}
		
		String[] inputs = br.readLine().split(" ");
		H = Integer.parseInt(inputs[0]);
		W = Integer.parseInt(inputs[1]);
		startR = Integer.parseInt(inputs[2]);
		startC = Integer.parseInt(inputs[3]);
		endR = Integer.parseInt(inputs[4]);
		endC = Integer.parseInt(inputs[5]);
		
		bfs();
		if(!reach) System.out.print(-1);
		else System.out.print(cost[endR][endC]);
	}
	
	private static void bfs() {
		Queue<Rec> queue = new ArrayDeque<>();
		queue.offer(new Rec(startR, startC));
		visited[startR][startC] = true;
		
		while(!queue.isEmpty()) {
			Rec cur = queue.poll();
			
			if(cur.r == endR && cur.c == endC) {
				reach = true;
				return;
			}
			
			//cur.r, cur.c를 시작점으로 하는 직사각형을 [상, 좌, 하, 우]에서 만들 수 있는 지 확인한다.
			for(int i = 0; i<4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				
				if(nr <= 0 || nr > N || nc <= 0 || nc > M || visited[nr][nc]) continue;
				
				//사각형을 만들 수 있는 조건: 끝 점들이 범위 안에 있어야 한다 & 사각형 범위 안에 벽이 없으면 된다.
				if(make(nr, nc)) {
					queue.offer(new Rec(nr, nc));
					visited[nr][nc] = true;
					cost[nr][nc] = cost[cur.r][cur.c] + 1;
				}
			}
		}
	}
	
	private static boolean make(int r, int c) {
		int endR = r + H - 1;
		int endC = c + W - 1;
		
		if(endR > N || endC > M) return false;
		
		for(Rec w : wall) {
			if(w.c >= c && w.c <= endC && w.r >= r && w.r <= endR) {
				return false;
			}
		}
		return true;
	}
}	

class Rec{
	int r, c;
	Rec(int r, int c){
		this.r = r;
		this.c = c;
	}
}
