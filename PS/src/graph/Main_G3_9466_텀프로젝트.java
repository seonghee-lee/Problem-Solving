package graph;

import java.util.*;
import java.io.*;

public class Main_G3_9466_텀프로젝트 {
	static int N, team;
	static boolean success;
	static boolean[] visited;
	static List<Integer>[] reverseAdj;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for(int t = 1; t<=T; t++) {
			int N = Integer.parseInt(br.readLine());	//학생 수
			
			reverseAdj = new ArrayList[N+1];
			
			for(int i = 1; i<=N; i++) {
				reverseAdj[i] = new ArrayList<>();
			}
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 1; i<=N; i++) {
				int want = Integer.parseInt(st.nextToken());
				reverseAdj[want].add(i);
			}
			
			visited = new boolean[N+1];
			team = 0;
			
			for(int i = 1; i<=N; i++) {
				success = false;
				dfs(i, i, 0);
			}
			
			sb.append(N-team).append("\n");
		}
		
		System.out.print(sb);
		
	}
	
	private static void dfs(int start, int cur, int count) {
		
		if(count > 0 && start == cur) {
			success = true;	//팀을 만들 수 있음.
			return;
		}
		
		if(visited[cur]) return;
		
		visited[cur] = true;
		
		for(Integer k : reverseAdj[cur]) {
			dfs(start, k, count+1);
			if(success) break;
		}
		
		if(success) team++;
	}
}
