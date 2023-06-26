package graph;

import java.util.*;
import java.io.*;

public class Main_G5_5972_택배배송 {
	static int N, M;
	static ArrayList<dNode>[] graph;
	static int[] dist;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		
		graph = new ArrayList[N+1];
		for(int i = 1; i<=N; i++) {
			graph[i] = new ArrayList<dNode>();
		}
		
		StringTokenizer st;
		for(int i = 1; i<=M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[start].add(new dNode(end, cost));
			graph[end].add(new dNode(start, cost));
		}
		
		dist = new int[N+1];
		visited = new boolean[N+1];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		go(1);
		
		System.out.print(dist[N]);
	}
	
	//다익스트라 알고리즘
	private static void go(int start) {

		PriorityQueue<dNode> pqueue = new PriorityQueue<>();
		pqueue.offer(new dNode(start, 0));
		dist[start] = 0;
		
		while(!pqueue.isEmpty()) {
			
			dNode cur = pqueue.poll();
			
			if(visited[cur.idx]) continue;
			visited[cur.idx] = true;
			
			for(dNode adjNode: graph[cur.idx]) {
				if(dist[adjNode.idx] > dist[cur.idx] + adjNode.cost) {
					dist[adjNode.idx] = dist[cur.idx] + adjNode.cost;
					pqueue.offer(new dNode(adjNode.idx, dist[adjNode.idx]));
				}
			}
		}
	}

}

class dNode implements Comparable<dNode>{
	int idx, cost;
	dNode(int idx, int cost){
		this.idx = idx;
		this.cost = cost;
	}
	
	public int compareTo(dNode o) {
		return Integer.compare(this.cost, o.cost);
	}
}
