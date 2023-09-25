package backtracking;

import java.util.*;
import java.io.*;

public class Main_G3_2026_소풍 {
	static int K, N, F;
	static List[] adjList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] knf = br.readLine().split(" ");
		K = Integer.parseInt(knf[0]);
		N = Integer.parseInt(knf[1]);
		F = Integer.parseInt(knf[2]);
		
		adjList = new ArrayList[N+1];
		for(int i = 1; i<=N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i = 0; i<F; i++) {
			String[] inputs = br.readLine().split(" ");
			int s = Integer.parseInt(inputs[0]);
			int e = Integer.parseInt(inputs[1]);
			adjList[s].add(e);
			adjList[e].add(s);
		}
		
		for(int i = 1; i<=N; i++) {
			Collections.sort(adjList[i]);
		}
		
		boolean[] visited = new boolean[N+1];
		
		for(int i = 1; i<=N; i++) {
			if(adjList[i].size() < K-1) continue;
			
			Arrays.fill(visited, false);
			visited[i] = true;
			go(i, 1, visited);
		}
		
		System.out.print(-1);
	}
	
	private static void go(int cur, int count, boolean[] visited) {
		
		if(count == K) {
			StringBuilder sb = new StringBuilder();
			for(int i = 1; i<=N; i++) {
				if(visited[i]) sb.append(i).append("\n");
			}
			System.out.print(sb);
			System.exit(0);
		}
		
		int size = adjList[cur].size();
		
		for(int i = 0; i<size; i++) {
			int friend = (int)adjList[cur].get(i);
			if(visited[friend]) continue;
			
			//friend와 연결된 친구가 K명 미만이면 절대 함께 소풍갈 수 없다.
			if(adjList[friend].size() < K-1) continue;
			
			//이전까지 친구인 애들과 모두 친구인지 확인한다.
			if(!check(friend, visited)) continue;
			
			visited[friend] = true;
			go(friend, count+1, visited);
			visited[friend] = false;
		}
	}
	
	private static boolean check(int cur, boolean[] visited) {
		for(int i = 1; i<=N; i++) {
			if(i == cur) continue;
			if(visited[i] && !adjList[cur].contains(i)) return false;
		}
		return true;
	}
}
