package graph;

import java.util.*;
import java.io.*;

public class Main_G5_13023_ABCDE {
	static ArrayList[] list;
	static boolean[] visited;
	static boolean flag;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		int N = Integer.parseInt(nm[0]);
		int M = Integer.parseInt(nm[1]);
		
		list = new ArrayList[N];
		for(int i = 0; i<N; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i<M; i++) {
			String[] inputs = br.readLine().split(" ");
			int s = Integer.parseInt(inputs[0]);
			int e = Integer.parseInt(inputs[1]);
			list[s].add(e);
			list[e].add(s);
		}
		
		for(int i = 0; i<N; i++) {
			visited = new boolean[N];
			flag = false;
			visited[i] = true;
			dfs(i, 0);
			if(flag) break;
		}
		
		if(flag) System.out.print(1);
		else System.out.print(0);
		
	}
	
	private static void dfs(int cur, int depth) {
		if(depth >= 4) {
			flag = true;
			return;
		}
		
		for(int i = 0, len = list[cur].size(); i<len; i++) {
			int next = (int)list[cur].get(i);
			if(visited[next]) continue;
			visited[next] = true;
			dfs(next, depth + 1);
			visited[next] = false;
		}
	}
}
