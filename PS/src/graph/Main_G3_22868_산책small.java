package graph;

import java.util.*;
import java.io.*;

/**
 * 최단거리는 BFS!!!
 * 왜 지나온 길을 저장하면 안 되지? 왜 꼭 역추적으로 구현해야 되는거지..ㅜㅜ
 */

public class Main_G3_22868_산책small {
	static int N, M, min;
	static boolean[] minVisited;
	static int[] path;
	static List<Integer>[] adjList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		
		adjList = new ArrayList[N+1];
		for(int i = 1; i<=N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i<M; i++) {
			String[] inputs = br.readLine().split(" ");
			int from = Integer.parseInt(inputs[0]);
			int to = Integer.parseInt(inputs[1]);
			
			adjList[from].add(to);
			adjList[to].add(from);
		}
		
		for(int i = 1; i<=N; i++) {
			Collections.sort(adjList[i]);
		}
		
		String[] se = br.readLine().split(" ");
		int S = Integer.parseInt(se[0]);
		int E = Integer.parseInt(se[1]);
		
		path = new int[N+1];	//시작점 ~ 끝점 까지 지나간 경로 : path[i] : i를 거쳐서 현재 인덱스에 도착함.
		boolean[] visited = new boolean[N+1];
		boolean[] evisited = new boolean[N+1];
		
		int smin = bfs(S, E, visited);
		
		//갈때 방문했던 점들은 미리 방문처리를 해 놓는다.
		int last = path[E];
		while(last > 0) {
			evisited[last] = true;
			last = path[last];
		}
		
		evisited[E] = false;	//시작점은 방문처리 해제
		evisited[S] = false;
		
		int emin = bfs(E, S, evisited);
		
		System.out.println(smin + emin);
	}
	
	private static int bfs(int start, int end, boolean[] visited) {
		Queue<Vertex> queue = new ArrayDeque<>();
		queue.offer(new Vertex(start, 0));
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			Vertex cur = queue.poll();
			
			for(Integer i : adjList[cur.idx]) {
				if(visited[i]) continue;
				queue.offer(new Vertex(i, cur.dist + 1));
				visited[i] = true;
				path[i] = cur.idx;
				
				if(i == end) {
					return cur.dist + 1;
				}
			}
		}
		
		return 0;
	}
	
}

class Vertex {
	int idx, dist;
	Vertex(int idx, int dist){
		this.idx = idx;
		this.dist = dist;
	}
}
