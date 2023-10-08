package graph;

import java.util.*;
import java.io.*;

public class Main_G3_1238_파티 {
	static int N, M, X;
	static List<Village>[] adjList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nmx = br.readLine().split(" ");
		N = Integer.parseInt(nmx[0]);
		M = Integer.parseInt(nmx[1]);
		X = Integer.parseInt(nmx[2]);
		
		adjList = new ArrayList[N+1];
		for(int i = 1; i<=N; i++) {
			adjList[i] = new ArrayList<Village>();
		}
		
		for(int i = 0; i<M; i++) {
			String[] inputs = br.readLine().split(" ");
			int s = Integer.parseInt(inputs[0]);
			int e = Integer.parseInt(inputs[1]);
			int t = Integer.parseInt(inputs[2]);
			adjList[s].add(new Village(e, t));
		}

		int max = Integer.MIN_VALUE;
		
		//X에서 출발했을 때, 각 마을로 가는 최소시간
		int[] back = dijkstra(X);
		
		//학생 i: i -> X 최소 시간 구하기
		for(int i = 1; i<=N; i++) {
			int[] go = dijkstra(i);
			max = Math.max(max, go[X] + back[i]);
		}
		
		System.out.println(max);
		
	}
	
	private static int[] dijkstra(int start) {
		int[] dist = new int[N+1];
		boolean[] visited = new boolean[N+1];
		PriorityQueue<Village> pqueue = new PriorityQueue<>();
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		pqueue.offer(new Village(start, 0));
		
		while(!pqueue.isEmpty()) {
			Village cur = pqueue.poll();
			
			if(visited[cur.num]) continue;
			visited[cur.num] = true;
			
			for(Village next : adjList[cur.num]) {
				if(dist[next.num] > dist[cur.num] + next.time) {
					dist[next.num] = dist[cur.num] + next.time;
					pqueue.offer(new Village(next.num, dist[next.num]));
				}
			}
		}
		return dist;
	}

}

class Village implements Comparable<Village> {
	int num, time;
	Village(int num, int time){
		this.num = num;
		this.time = time;
	}
	
	public int compareTo(Village o) {
		return this.time - o.time;
	}
}