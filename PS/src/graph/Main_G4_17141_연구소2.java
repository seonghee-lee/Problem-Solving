package graph;

import java.util.*;
import java.io.*;

public class Main_G4_17141_연구소2 {
	static int N, M, min, time;
	static boolean possible;
	static int[][] map;
	static boolean[] selected;
	static List<Virus> virusList = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		
		map = new int[N][N];
		StringTokenizer st;
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					virusList.add(new Virus(i, j));
				}
			}
		}
		
		selected = new boolean[virusList.size()];
		min = Integer.MAX_VALUE;
		selectVirus(0, 0);
		
		if(possible) System.out.print(min);
		else System.out.print(-1);
	}
	
	private static boolean checkMap(int[][] map) {
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				if(map[i][j] == 0) return false;
			}
		}
		return true;
	}
	
	private static boolean spread(int[] virus, int[][] map) {
		boolean flag = false;
		Queue<Virus> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, -1, 0, 1};
		
		for(int i = 0; i<M; i++) {
			Virus v = virusList.get(virus[i]);
			queue.offer(new Virus(v.r, v.c));
			visited[v.r][v.c] = true;
		}
		
		while(!queue.isEmpty()) {
			
			for(int q = 0, size = queue.size(); q<size; q++) {
				Virus cur = queue.poll();
				
				int nr, nc;
				for(int i = 0; i<4; i++) {
					nr = cur.r + dr[i];
					nc = cur.c + dc[i];
					if(nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc] || map[nr][nc] == -1) continue;
					
					map[nr][nc]++;
					queue.offer(new Virus(nr, nc));
					visited[nr][nc] = true;
				}
			}
			time++;
			if(checkMap(map)) flag = true;
		}
		
		return flag;
		
	}

	private static void selectVirus(int index, int count){
		if(count == M) {

			int[] virus = new int[M];
			boolean[][] virusMap = new boolean[N][N];
			int idx = 0;
			for(int i = 0, size = virusList.size(); i<size; i++) {
				if(selected[i]) {
					virus[idx++] = i;
					Virus v = virusList.get(i);
					virusMap[v.r][v.c] = true; 
				}
			}
			
			int[][] copyMap = new int[N][N];
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					if(map[i][j] == 1) copyMap[i][j] = -1;
					else copyMap[i][j] = map[i][j];
					
					//바이러스가 아닌 2번칸들을 0번칸으로 갱신한다.
					if(map[i][j] == 2 && !virusMap[i][j]) {
						copyMap[i][j] = 0;
					}
				}
			}
			time = 0;
			if(spread(virus, copyMap)) {
				possible = true;
				min = Math.min(min, time-1);
			}
			return;
		}
		
		if(index >= virusList.size()) return;
		
		selected[index] = true;
		selectVirus(index + 1, count + 1);
		
		selected[index] = false;
		selectVirus(index + 1, count);
	}
}

class Virus{
	int r, c;
	Virus(int r, int c){
		this.r = r;
		this.c = c;
	}
}