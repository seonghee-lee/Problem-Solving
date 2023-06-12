package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 연쇄적으로 타고 들어갈 수 있으면, 그래서 내가 시작한 값으로 돌아오면 
 * 그 수는 뽑을 수 있는 수이다.
 * 
 * 모든 수에 대해서 해당 작업을 해보고 뽑을 수 있는 수의 리스트를 만들면
 * 가장 많이 뽑았을 때를 구할 수 있다
 */

public class Main_G5_2668_숫자고르기 {
	static int N;
	static int[][] map;
	static boolean[] visited;
	static List<Integer> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		visited = new boolean[N+1];
		
		for(int i = 1; i<=N; i++) {
			int x = Integer.parseInt(br.readLine());
			map[i][0] = i;
			map[i][1] = x;
		}
		
//		O(N) = 100
		for(int i = 1; i<=N; i++) {		//1부터 N까지 모든 숫자에 대해서 반복한다.
			visited[i] = true;
			go(i, map[i][1]);			//재귀는 i의 짝숫자로 간다. <= 내가 향하는 곳
			visited[i] = false;
		}	//100 * 100 = 10000
		
		
		Collections.sort(list);
		
		StringBuilder result = new StringBuilder(120);
		result.append(list.size()).append("\n");
		for(int l : list) {
			result.append(l).append("\n");
		}

		System.out.println(result.toString().trim());
	}
	
	//O(N-1) = 100
	//start: 탐색을 시작하는 수(변하지 않는다), next: 탐색을 시작하는 수의 동일 열 숫자
	private static void go(int start, int next) {
		
		if(start == next) {		//탐색을 시작했던 수와 재귀의 숫자가 같은 경우(처음의 수로 다시 돌아온 것이다)
			list.add(start);	//리스트에 시작 숫자를 추가한다.
		}
		
		if(!visited[next]) {	//방문했던 숫자라면 무한 루프에 빠지게 되니까, 방문하지 않은 숫자에 대해서만 재귀를 진행해야 한다.
			int num = map[next][1];		//짝숫자를 감으로써 다음 타겟이 되는 숫자
			visited[next] = true;		//짝숫자를 방문처리한다.(내가 향하는 곳)
			go(start, num);				//재귀를 탄다.
			visited[next] = false;		//방문 되돌리기
		}
	}
}
