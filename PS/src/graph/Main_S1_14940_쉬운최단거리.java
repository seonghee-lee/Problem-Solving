package graph;
import java.util.*;
import java.io.*;
/*
 * bfs
 * 현재 점에서 도착지까지의 거리를 구한다.
 * 도착할 수 있는 경우 거리를 구해서 ANS에 넣고, 도착할 수 없는 경우 ANS에 -1을 넣는다.
 * */
public class Main_S1_14940_쉬운최단거리 {
	static int N, M, destR, destC;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		StringTokenizer st;
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					destR = i;
					destC = j;
				}
			}
		}
		
		go(destR, destC);
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(!visited[i][j] && map[i][j] != 0) {
					map[i][j] = -1;
				}
			}
		}
		
		StringBuilder sb = new StringBuilder(100);
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				sb.append(map[i][j]).append(" ");
			}sb.append("\n");
		}
		
		System.out.print(sb);

	}
	
	private static void go(int startR, int startC) {
		Queue<Pos> queue = new ArrayDeque<>();
		
		queue.offer(new Pos(startR, startC));
		visited[startR][startC] = true;
		map[startR][startC] = 0;
		int dist = 0;
		
		while(!queue.isEmpty()) {
			dist++;
			for(int q = 0, size = queue.size(); q<size; q++) {
				Pos cur = queue.poll();
				
				int nr, nc;
				for(int i = 0; i<4; i++) {
					nr = cur.r + dr[i];
					nc = cur.c + dc[i];
					if(nr < 0 || nr >=N || nc < 0 || nc >=M 
							|| map[nr][nc] == 0 || visited[nr][nc]) continue;
					
					queue.offer(new Pos(nr, nc));
					visited[nr][nc] = true;
					map[nr][nc] = dist;
				}
			}
		}
	}
}

class Pos{
	int r, c;
	Pos(int r, int c){
		this.r = r;
		this.c = c;
	}
}
