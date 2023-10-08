package graph;

import java.util.*;
import java.io.*;

public class Main_G3_11779_최소비용구하기2 {
	static int N, M, S, E;
	static List<City>[] adjList;	//자료형 꼭 적어주기
	static boolean[] visited;
	static int[] minCost, prevVisit;
	static PriorityQueue<City> pqueue = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		adjList = new ArrayList[N+1];
		for(int i = 1; i<=N; i++) {
			adjList[i] = new ArrayList<City>();
		}
		
		for(int i = 0; i<M; i++) {
			String[] inputs = br.readLine().split(" ");
			int s = Integer.parseInt(inputs[0]);
			int e = Integer.parseInt(inputs[1]);
			int cost = Integer.parseInt(inputs[2]);
			adjList[s].add(new City(e, cost));
		}
		
		String[] se = br.readLine().split(" ");
		S = Integer.parseInt(se[0]);
		E = Integer.parseInt(se[1]);
		
		visited = new boolean[N+1];
		minCost = new int[N+1];
		prevVisit = new int[N+1];
		
		Arrays.fill(minCost, Integer.MAX_VALUE);
		minCost[S] = 0;
		pqueue.offer(new City(S, 0));
		go();
		
		StringBuilder sb = new StringBuilder();
		sb.append(minCost[E]).append("\n");
		
		//끝점부터 역추적(나를 호출한 애)해서 경로 구하기
		Stack<Integer> stack = new Stack<>();
		stack.push(E);
		int cur = -1;
		int target = E;
		while(true) {
			cur = prevVisit[target];
			if(cur == 0) break;
			stack.push(cur);
			target = cur;
		}
		
		sb.append(stack.size()).append("\n");
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		
		System.out.println(sb);
	}
	
	private static void go() {	//다익스트라
		while(!pqueue.isEmpty()) {
			City cur = pqueue.poll();
			if(visited[cur.num]) continue;
			visited[cur.num] = true;

			for(int i = 0, size = adjList[cur.num].size(); i<size; i++) {
				City next = adjList[cur.num].get(i);
				
				if(minCost[next.num] > minCost[cur.num] + next.cost) {
					minCost[next.num] = minCost[cur.num] + next.cost;
					prevVisit[next.num] = cur.num; 	//지나온 길을 구하기 위해, 이전 도시 저장
					pqueue.offer(new City(next.num, minCost[next.num]));
				}
			}
		}
	}
}

class City implements Comparable<City>{
	int num, cost;
	City(int num, int cost){
		this.num = num;
		this.cost = cost;
	}
	public int compareTo(City o) {
		return this.cost - o.cost;
	}
}
