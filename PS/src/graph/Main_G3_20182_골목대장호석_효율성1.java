package graph;

import java.util.*;
import java.io.*;

public class Main_G3_20182_골목대장호석_효율성1 {
	static int N, M, A, B, C, min;
	static boolean[] visited;
	static int[] dist;
	static List<Pass>[] graph;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);	//교차로 개수(정점 수)
		M = Integer.parseInt(inputs[1]);	//골목 개수 (간선 수)
		A = Integer.parseInt(inputs[2]);	//출발
		B = Integer.parseInt(inputs[3]);	//도착
		C = Integer.parseInt(inputs[4]);	//돈
		
		graph = new ArrayList[N+1];
		for(int i = 1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i<M; i++) {
			inputs = br.readLine().split(" ");
			int s = Integer.parseInt(inputs[0]);
			int e = Integer.parseInt(inputs[1]);
			int c = Integer.parseInt(inputs[2]);
			graph[s].add(new Pass(e, c));
			graph[e].add(new Pass(s, c));
		}
		
		dist = new int[N+1];
		visited = new boolean[N+1];
		
		//골목별 최대 수금 값 x를 정하여(파라매트릭 서치), A->B를 갈 수 있는지/없는지 판별한다.(다익스트라)
		//다익스트라를 쓰는 이유: 정점이 많을 때 최단거리로 도착점에 도달하기 위해서
		int start = 1;
		int end = 20;
		int min = Integer.MAX_VALUE;
		while(start <= end) {
			int x = (start + end) / 2;
			if(dijkstra(x)) {
				min = Math.min(min, x);
				end = x - 1;
			} else {
				start = x + 1;
			}
		}
		
		if(min == Integer.MAX_VALUE) System.out.print(-1);
		else System.out.print(min);
	}
	
	private static boolean dijkstra(int x) {	//x: 각 골목에서 사용할 수 있는 최대 금액
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(visited, false);
		PriorityQueue<Pass> pqueue = new PriorityQueue<>();
		
		dist[A] = 0;
		pqueue.offer(new Pass(A, 0));
		
		while(!pqueue.isEmpty()) {
			Pass cur = pqueue.poll();
			if(visited[cur.num]) continue;
			
			visited[cur.num] = true;
			if(cur.num == B && dist[B] <= C) {
				return true;
			}
			
			for(Pass next : graph[cur.num]) {
				if(next.cost > x || next.cost > C) continue;
				if(dist[next.num] > dist[cur.num] + next.cost) {
					if(dist[cur.num] + next.cost > C) continue;
					dist[next.num] = dist[cur.num] + next.cost;
					pqueue.offer(new Pass(next.num, dist[next.num]));
				}
			}
		}
		return false;
	}
}

class Pass implements Comparable<Pass> {
	int num, cost;
	Pass(int num, int cost){
		this.num = num;
		this.cost = cost;
	}
	public int compareTo(Pass o) {
		return this.cost - o.cost;
	}
}
