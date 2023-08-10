package softeer;

import java.util.*;
import java.io.*;

/**
 * 문제 핵심: 출근할 때는 T를 제외한 정점에 여러번 방문할 수 있고, 퇴근할 때는 S를 제외한 정점에 여러번 방문할 수 있다.
 * <출근>
 * 일반적인 그래프 방문으로 문제를 풀면 안된다. 왜? visited 되었다고 방문하지 않으면 안 된다.
 * 현재 노드와 인접한 다른 노드를 거쳐서도 T에 도달할 수 있으면, 그 인접 노드를 방문하고 현재 노드를 또 방문할 수 있어야 한다.
 * 그러기 위해서 방문한 임의 노드의 인접노드(=새롭게 방문한 노드)가 T로 갈 수 있는 노드인 지 매번 탐색해야 한다.
 * => 노드 수는 최대 10만개이므로 시간초과 발생.
 * 
 * <해결방법>
 * 역방향 인접그래프를 만들어서 T에서 출발했을 때 해당 노드를 방문할 수 있는 지 표시한다.
 * => S에서 T로 가는 visited배열(인접그래프 탐색)과 T에서 S로 가는 visited배열(역인접그래프 탐색)을 비교해서 
 *    둘 다 true라면 해당 정점은 출근길에 들를 수 있는 정점임을 알 수 있다.
 *    
 * A에서 B를 앞을 보고 걸어갔다면(A -> B), B에서 A로 뒤로 걸어갈 수 있다는 의미다.(A <- B)
 * 
 * 퇴근길도 동일한 방법으로 진행한다.
 */

public class Main_HSAT6_출퇴근길 {
	static int N, M;
	static boolean[] svisited, srvisited, evisited, ervisited;
	static List<Integer>[] adjustList, adjustReverseList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
		
		adjustList = new List[N+1];
		adjustReverseList = new List[N+1];
		
		for(int i = 1; i<=N; i++) {
			adjustList[i] = new ArrayList<>();
			adjustReverseList[i] = new ArrayList<>();
		}
		
		for(int i = 1; i<=M; i++) {
			String[] node = br.readLine().split(" ");
			int from = Integer.parseInt(node[0]);
			int to = Integer.parseInt(node[1]);
			
			adjustList[from].add(to);
			adjustReverseList[to].add(from);
		}
		
		String[] se = br.readLine().split(" ");
		int S = Integer.parseInt(se[0]);
		int T = Integer.parseInt(se[1]);
		
		//출근 시 사용
		svisited = new boolean[N+1];
		srvisited = new boolean[N+1];
		
		//퇴근 시 사용
		evisited = new boolean[N+1];
		ervisited = new boolean[N+1];
		
		//출근
		svisited[T] = true;
		dfs(S, svisited, adjustList);
		dfs(T, srvisited, adjustReverseList);
		
		//퇴근
		evisited[S] = true;
		dfs(T, evisited, adjustList);
		dfs(S, ervisited, adjustReverseList);
		
		int count = 0;
		
		for(int i = 1; i<=N; i++) {
			 if(svisited[i] && srvisited[i] && evisited[i] && ervisited[i]) {
				 count++;
			 }
		}
		
		System.out.print(count - 2);
	}
	
	private static void dfs(int cur, boolean[] visited, List<Integer>[] adjList) {
		if(visited[cur]) return;
		
		visited[cur] = true;
		
		for(Integer i : adjList[cur]) {
			dfs(i, visited, adjList);
		}
	}
}
