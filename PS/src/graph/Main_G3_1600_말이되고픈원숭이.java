package graph;

import java.util.*;
import java.io.*;

public class Main_G3_1600_말이되고픈원숭이 {
	static int K, W, H;
	static int[][] map;
	static boolean possible;
	static int ans;
	static boolean[][][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static int[] hdr = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] hdc = {-1, -2, -2, -1, 1, 2, 2, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());	//원숭이 점프 횟수
		String[] wh = br.readLine().split(" ");
		W = Integer.parseInt(wh[0]);
		H = Integer.parseInt(wh[1]);
		
		map = new int[H][W];
		StringTokenizer st;
		for(int i = 0; i<H; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[H][W][K+1];	//[K+1]: 몇 번의 점프만에 이 칸에 왔는지
		bfs();
		if(possible)	System.out.print(ans);
		else System.out.print(-1);
	}
	
	private static void bfs() {
		Queue<Monkey> queue = new ArrayDeque<>();
		queue.offer(new Monkey(0, 0, 0, 0));
		
		while(!queue.isEmpty()) {
			Monkey cur = queue.poll();
			
			if(cur.r == H-1 && cur.c == W-1) {
				possible = true;
				ans = cur.move;
				break;
			}
			
			//일반이동
			int nr, nc;
			for(int i = 0; i<4; i++) {
				nr = cur.r + dr[i];
				nc = cur.c + dc[i];
				if(nr < 0 || nr >= H || nc < 0 || nc >= W
						|| map[nr][nc] == 1 || visited[nr][nc][cur.jump]) continue;
				
				visited[nr][nc][cur.jump] = true;
				queue.offer(new Monkey(nr, nc, cur.move + 1, cur.jump));
			}
			
			//점프 이동
			if(cur.jump < K) {
				int hnr, hnc;
				for(int i = 0; i<8; i++) {
					hnr = cur.r + hdr[i];
					hnc = cur.c + hdc[i];
					if(hnr < 0 || hnr >= H || hnc < 0 || hnc >= W
							|| map[hnr][hnc] == 1 || visited[hnr][hnc][cur.jump + 1]) continue;
					
					visited[hnr][hnc][cur.jump + 1] = true;
					queue.offer(new Monkey(hnr, hnc, cur.move + 1, cur.jump + 1));
				}
			}
		}
	}
}

class Monkey{
	int r, c, move, jump;
	Monkey(int r, int c, int move, int jump){
		this.r = r;
		this.c = c;
		this.move = move;	//동작 횟수
		this.jump = jump;	//점프 횟수
	}
}
