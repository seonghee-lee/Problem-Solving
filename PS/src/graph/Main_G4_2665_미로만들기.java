package graph;

import java.util.*;
import java.io.*;

public class Main_G4_2665_미로만들기 {
	static int N, min;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1}; 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		StringTokenizer st;
		for(int i = 0; i<N; i++) {
			String[] inputs = br.readLine().split("");
			for(int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(inputs[j]);
			}
		}
		
		visited = new boolean[N][N];
		min = Integer.MAX_VALUE;
		bfs();
		
		System.out.print(min);
	}
	
	private static void bfs() {
		PriorityQueue<Room> pqueue = new PriorityQueue<>();
		pqueue.offer(new Room(0, 0, 0));
		visited[0][0] = true;
		
		while(!pqueue.isEmpty()) {
			Room cur = pqueue.poll();
			
			//도착한 경우
			if(cur.r == N-1 && cur.c == N-1) {
				min = Math.min(min, cur.count);
				break;
			}
			
			int nr, nc;
			for(int i = 0; i<4; i++) {
				nr = cur.r + dr[i];
				nc = cur.c + dc[i];
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) continue;
				
				if(map[nr][nc] == 0) {	//검은방
					pqueue.offer(new Room(nr, nc, cur.count + 1));
					visited[nr][nc] = true;
				} else {
					pqueue.offer(new Room(nr, nc, cur.count));
					visited[nr][nc] = true;
				}
			}
		}
	}
}

class Room implements Comparable<Room>{
	int r, c, count;
	Room(int r, int c, int count){
		this.r = r;
		this.c = c;
		this.count = count;
	}
	
	public int compareTo(Room o) {
		return Integer.compare(this.count, o.count);
	}
}
