package graph;
import java.util.*;
import java.io.*;

public class Main_G5_1916_최소비용구하기 {
	static int N, M;
	static ArrayList<Node>[] graph;
	static boolean[] visited;
	static int[] dist;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		visited = new boolean[N+1];
		dist = new int[N+1];
		graph = new ArrayList[N+1];
		
		for(int i = 0; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		StringTokenizer st;
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[s].add(new Node(e, weight));
		}
		
		String[] order = br.readLine().split(" ");
		int start = Integer.parseInt(order[0]);
		int end = Integer.parseInt(order[1]);
		
		go(start);
//		System.out.println(Arrays.toString(dist));
		System.out.println(dist[end]);
	}
	
	//다익스트라 알고리즘
	private static void go(int start) {
		PriorityQueue<Node> pqueue = new PriorityQueue<>();
		int INF = 100000001;
		Arrays.fill(dist, INF);
		
		pqueue.offer(new Node(start, 0));
//		visited[start] = true;	//최초 vertex는 무조건 방문 해야되니까 이건 쓰면 안 된다.
		dist[start] = 0;
		
		while(!pqueue.isEmpty()) {
			int curVertex = pqueue.poll().index;
			
			if(visited[curVertex]) continue;
			visited[curVertex] = true;
			
			for(Node next : graph[curVertex]) {
				if(dist[next.index] > dist[curVertex] + next.cost) {
					dist[next.index] = dist[curVertex] + next.cost;
					pqueue.offer(new Node(next.index, dist[next.index]));
				}
			}
		}
	}

}

class Node implements Comparable<Node> {
	int index;
	int cost;
	public Node(int index, int cost) {
		this.index = index;
		this.cost = cost;
	}
	
	public int compareTo(Node o) {
		return Integer.compare(this.cost, o.cost);
	}
}
