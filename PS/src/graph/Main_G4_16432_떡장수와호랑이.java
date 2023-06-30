package graph;
/*
 * 단순히 백트래킹으로 풀면 시간초과가 난다.
 * visited 배열에 dp 개념을 섞어서 만들어야 시간 내에 완수할 수 있음.
 * 다시 되돌아온 상황에서, 다음날의 기록을 보고 내가 이걸 고르고 갔다 온 적이 있는지/없는지 를 확인하는 게 문제 풀이의 핵심!
 * */
import java.util.*;
import java.io.*;

public class Main_G4_16432_떡장수와호랑이 {
	static int N;
	static boolean fin;
	static boolean[][] visited;
	static ArrayList<Integer>[] list;
	static int[] result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new ArrayList[N+1];
		for(int i = 1; i<=N; i++) {
			list[i] = new ArrayList<>();
		}
		StringTokenizer st;
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			for(int j = 0; j<m; j++) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		visited = new boolean[N+2][10];		//visited[i][j] : i-1번째 날에 j번 떡을 먹고 간 "경우를 확인했는가?" => DP 관점에서 생각하기. 전날에 j번 떡을 먹고 온 거야?
		result = new int[N+1];
		dfs(1, 0);
		
		StringBuilder sb = new StringBuilder(2000);
		if(!fin) System.out.print(-1);
		else {
			for(int i = 1; i<=N; i++) {
				sb.append(result[i]).append("\n");
			}
		}
		System.out.print(sb);
		
	}
	
	private static void dfs(int day, int prev) {
		
		if(day == N + 1) {	//마지막 날까지 떡을 다 골랐다면
			fin = true;		//성공
			return;			//종료
		}
		
		for(int i = 1; i<=9; i++) {
			//오늘의 떡이 아니거나, 전날 떡이랑 똑같거나, 이 떡을 고르고 다음날로 진행해 본 경험이 이미 있다면(경험하고 다시 돌아온 상태라는 걸 의미함), continue
			if(!list[day].contains(i) || i == prev || visited[day+1][i]) continue;
			visited[day+1][i] = true;	//i떡을 고르고 다음날로 진행해보자!
			result[day] = i;	//떡 고름
			dfs(day+1, i);		//재귀 타기
			
			if(fin) return;		//이걸 거쳐서 나가지 못했다는 건, 끝까지 재귀를 못탔다는 것 -> 전날 떡과 중복되는 떡을 골랐다는 거다. 이전 반복문으로 다시 고고..
		}
	}
}
