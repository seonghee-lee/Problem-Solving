package graph;

import java.util.*;
import java.io.*;

public class Main_G5_21278_호석이두마리치킨 {
	static int N, M;
	static int[] chicken = new int[2];
	static boolean[] visited;
	static int[][] dist;
	static List<Result> result = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		dist = new int[N+1][N+1];	//dist[i][j]: i -> j 최단거리
		
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				if(i != j) dist[i][j] = -1;
			}
		}
		
		for(int i = 0; i<M; i++) {
			String[] se = br.readLine().split(" ");
			int s = Integer.parseInt(se[0]);
			int e = Integer.parseInt(se[1]);
			dist[s][e] = 1;
			dist[e][s] = 1;
		}
		
		for(int k = 1; k<=N; k++) {	// k: 경유지
			for(int i = 1; i<=N; i++) {
				for(int j = 1; j<=N; j++) {
					if(i == j || dist[i][k] == -1 || dist[k][j] == -1) continue;
					if(dist[i][j] == -1) dist[i][j] = dist[i][k] + dist[k][j];
					else dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		visited = new boolean[N+1];
		go(0);
		
		Collections.sort(result);
		Result r = result.get(0);
		System.out.printf("%d %d %d", r.city1, r.city2, r.sum);
	}
	
	private static void go(int cnt) {
		if(cnt == 2) {
			int sum = 0;
			for(int i = 1; i<=N; i++) {
				sum += Math.min(dist[i][chicken[0]], dist[i][chicken[1]]);
			}
			result.add(new Result(chicken[0], chicken[1], sum * 2));
			return;
		}
		
		for(int i = 1; i<=N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			chicken[cnt] = i;
			go(cnt + 1);
			visited[i] = false;
		}
	}
}

class Result implements Comparable<Result>{
	int city1, city2, sum;
	Result(int city1, int city2, int sum){
		this.city1 = city1;
		this.city2 = city2;
		this.sum = sum;
	}
	public int compareTo(Result o) {
		if(this.sum == o.sum) {
			if(this.city1 == o.city1) return this.city2 - o.city2;
			return this.city1 - o.city1;
		}
		return this.sum - o.sum;
	}
}
