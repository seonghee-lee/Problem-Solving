package dp2;

import java.util.*;
import java.io.*;

public class Main_G5_14699_관악산등산 {
	static int N, M, maxDepth;
	static int[] height, dp;
	static List<Integer>[] adjList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		
		height = new int[N+1];
		adjList = new ArrayList[N+1];

		for(int i = 1; i<=N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i<=N; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i<=M; i++) {
			String[] inputs = br.readLine().split(" ");
			int s = Integer.parseInt(inputs[0]);
			int e = Integer.parseInt(inputs[1]);
			
			//낮은곳 -> 높은곳으로 가도록 인접리스트 만듦
			if(height[s] > height[e]) adjList[e].add(s);
			else adjList[s].add(e);
		}
		
		dp = new int[N+1];	//dp[i]: i쉼터에서 출발해서 산을 오를 때 최대로 방문할 수 있는 쉼터 개수
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i<=N; i++) {	//i: 출발하는 쉼터
			go(i);
		}
		
		for(int i = 1; i<=N; i++) {
			sb.append(dp[i]).append("\n");
		}
		
		System.out.print(sb);
	}
	
	private static int go(int cur) {
		
		if(dp[cur] > 0) {
			return dp[cur];
		}
		
		for(Integer k : adjList[cur]) {
			dp[cur] = Math.max(dp[cur], go(k));
		}
		
		return ++dp[cur];
	}
}
