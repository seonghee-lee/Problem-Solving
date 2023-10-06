package graph;

import java.util.*;
import java.io.*;

public class Main_G3_17182_우주탐사선 {
	static int N, K, min;
	static int[][] graph;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nk = br.readLine().split(" ");
		N = Integer.parseInt(nk[0]);	//행성의 수
		K = Integer.parseInt(nk[1]);	//발사되는 행성의 위치
		
		graph = new int[N][N];
		StringTokenizer st;
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//플로이드 와샬 알고리즘
		//graph[i][j]: i에서 j로 가는데 걸리는 최소시간 (모든 정점 방문은 보장X)
		for(int k = 0; k<N; k++) {	//k: 경유지
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
				}
			}
		}
		
		min = Integer.MAX_VALUE;
		visited = new boolean[N];
		visited[K] = true;
		dfs(K, 0);
		
		System.out.print(min);
	}
	
	private static void dfs(int cur, int time) {
		if(finish()) {
			min = Math.min(min, time);
			return;
		}
		
		for(int i = 0; i<N; i++) {
			if(cur == i || visited[i]) continue;
			visited[i] = true;
			dfs(i, time + graph[cur][i]);
			visited[i] = false;
		}
	}
	
	private static boolean finish() {
		for(int i = 0; i<N; i++) {
			if(!visited[i]) return false;
		}
		return true;
	}
}
