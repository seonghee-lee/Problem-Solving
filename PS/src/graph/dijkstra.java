//package graph;
//import java.util.*;
//import java.io.*;
//
//public class dijkstra {
//	static boolean[] check;
//	static int[] dist;
//	static ArrayList<Node>[] graph;
//	
//	static int N, M;
//	
//	public static void main(String[] args) throws Exception{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		N = Integer.parseInt(br.readLine());	//정점 개수
//		M = Integer.parseInt(br.readLine());	//간선 개수
//		
//		graph = new ArrayList[N+1];		//0번은 건너뜀
//		for(int i = 0; i<=N; i++) {
//			graph[i] = new ArrayList<>();
//		}
//		
//		StringTokenizer st;
//		for(int i = 0; i<M; i++) {
//			st = new StringTokenizer(br.readLine());
//			int v = Integer.parseInt(st.nextToken());
//			int w = Integer.parseInt(st.nextToken());
//			int cost = Integer.parseInt(st.nextToken());
//			
//			graph[v].add(new Node(w, cost));
//		}
//		
//		String[] order = br.readLine().split(" ");
//		int start = Integer.parseInt(order[0]);
//		int end = Integer.parseInt(order[1]);
//		
//		go(N, start);
//	}
//	
//	private static void go(int N , int start) {
//		check = new boolean[N+1];
//		dist = new int[N+1];
//		int INF = Integer.MAX_VALUE;
//		
//		Arrays.fill(dist, INF);		//거리 배열을 무한대로 채운다.
//		dist[start] = 0;			//시작점: 거리를 0으로 세팅한다.
//		
//		PriorityQueue<Node> pq = new PriorityQueue<>();
//		pq.offer(new Node(start, 0));		//시작점을 큐에 넣는다.
//		
//		while(!pq.isEmpty()) {
//			int nowVertex = pq.poll().index;
//			
//			if(check[nowVertex]) continue;	//방문한 곳이라면 방문하지 않음
//			check[nowVertex] = true;		//방문처리
//			
//			//연결된 정점들과 비교한다.
//			for(Node next : graph[nowVertex]) {
//				if(dist[next.index] > dist[nowVertex] + next.cost) {
//					dist[next.index] = dist[nowVertex] + next.cost;
//					
//					pq.offer(new Node(next.index, dist[next.index]));
//				}
//			}
//		}
//		
//		//최소 거리 출력
//		for(int i : dist) {
//			if(i == INF) System.out.print(0 + " ");
//			else System.out.print(i + " ");
//		}
//		
//	}
//	
//	
//}
//
//class Node implements Comparable<Node>{
//	int index;
//	int cost;
//	
//	public Node(int index, int cost) {
//		this.index = index;
//		this.cost = cost;
//	}
//	
//	public int compareTo(Node o) {
//		//Integer 기본 정렬 : 가중치가 작은 순서대로 정렬한다.
//		return Integer.compare(this.cost, cost);
//	}
//	
//}
//
//
