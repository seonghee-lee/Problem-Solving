package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_S1_1325_효율적인해킹BFS {
	static int N, M;
	static List<Integer>[] adjList;
	static int[] cnt;		//idx가 해킹할 수 있는 컴퓨터의 개수
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adjList = new ArrayList[N+1];
		cnt = new int[N+1];
				
		for(int i = 0; i<=N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for(int i = 0 ; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList[a].add(b);
		}
		
		//컴퓨터만큼 반복한다. i: 컴퓨터 번호
		for(int i = 1; i<=N; i++) {
			visited = new boolean[N+1];
			bfs(i);		//i컴퓨터를 해킹할 수 있는 컴퓨터 조사 (== i가 신뢰하는 컴퓨터 조사)
			
//			System.out.println(Arrays.toString(cnt));
		}
		
		List<Integer> result = new ArrayList<>();
		int max = Integer.MIN_VALUE;
		for(int i = 1; i<=N; i++) {
			if(cnt[i] > max) {
				max = cnt[i];
				result.clear();
				result.add(i);
			}else if(cnt[i] == max) {
				result.add(i);
			}
		}
		
		StringBuilder sb = new StringBuilder(100);
		for(int r : result) {
			sb.append(r).append(" ");
		}
		
		System.out.println(sb.toString().trim());
	}
	
	private static void bfs(int computer) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(computer);
		visited[computer] = true;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			for(int num : adjList[cur]) {		//cur 컴퓨터가 신뢰하는 모든 컴퓨터를 본다.
				if(!visited[num]) {
					cnt[num]++;		//해킹할 수 있으므로 cnt증가
					visited[num] = true;
					queue.offer(num);		//연쇄적 해킹 하기 위해 큐에 추가
				}
			}
		}
	}
}

/*
 * 1
 * 2
 * 3 : 1 -> 2
 * 4 : 3
 * 5 : 3
 * 
 * */
