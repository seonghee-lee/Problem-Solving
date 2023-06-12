package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 내 문제점: 재귀 설계를 어려워한다.. 많이 해보면 나아질거다.
 * 
 * ㄱ모양을 만드는 것이 핵심!
 * ㄱ모양1, ㄱ모양2, ㄱ모양3, ㄱ모양4를 만들어본다.
 * 만들 수 있는 경우라는 건 해당 좌표를 중심으로 했을 때를 의미한다.
 * 만들 수 있는 경우 
 *   방문처리를 한다.
 *   강도를 계산한다.
 *   좌표를 갱신한다. ===> 재귀를 태운다.
 *   방문처리를 되돌린다.
 * 만들 수 없는 경우
 *   해당 좌표를 넘어가야 하므로 좌표를 갱신하고 재귀를 태운다.
 * 이렇게 하면 전체에 대해 가능한가
 *      : flat하게 생각하자. 
 *        첫번째 visit처리 한 게 다시 false로 바뀔 때가 있겠지.
 *        그러면 다음 좌표에 대해 처음부터 진행할 수 있는거고, 그렇게 되니 전체가 가능한 것.
 */

public class Main_G5_18430_무기공학 {
	static int N, M, max, result;
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		go(0, 0, 0);
		System.out.println(max);
	}
	
	private static void go(int r, int c, int sum) {
		
		if(c == M) {
			c = 0;
			r++;
		}
		
		if(r == N) {
			max = Math.max(max, sum);
			return;
		}
		
		//ㄱ모양을 만든다.
		int p = 0;
		if(!visited[r][c]) {
			if(inRange(r, c-1) && inRange(r+1, c) 
					&& !visited[r][c-1] && !visited[r+1][c]) {
				//방문처리
				visited[r][c-1] = visited[r][c] = visited[r+1][c] = true;
				
				//강도 계산
				p = map[r][c-1] + 2 * map[r][c] + map[r+1][c];
				
				//재귀 태우기
				go(r, c+1, sum + p);
				
				//방문처리 되돌리기
				visited[r][c-1] = visited[r][c] = visited[r+1][c] = false;
			} 
			if(inRange(r-1, c) && inRange(r, c-1)
					&& !visited[r-1][c] && !visited[r][c-1]) {
				visited[r-1][c] = visited[r][c] = visited[r][c-1] = true;
				p = map[r-1][c] + 2 * map[r][c] + map[r][c-1];
				go(r, c+1, sum + p);
				visited[r-1][c] = visited[r][c] = visited[r][c-1] = false;
			} 
			if(inRange(r-1, c) && inRange(r, c+1) 
					&& !visited[r-1][c] && !visited[r][c+1]) {
				visited[r-1][c] = visited[r][c] = visited[r][c+1] = true;
				p = map[r-1][c] + 2 * map[r][c] + map[r][c+1];
				go(r, c+1, sum + p);
				visited[r-1][c] = visited[r][c] = visited[r][c+1] = false;
			} 
			if(inRange(r, c+1) && inRange(r+1, c) 
					&& !visited[r][c+1] && !visited[r+1][c]) {
				visited[r][c+1] = visited[r][c] = visited[r+1][c] = true;
				p = map[r][c+1] + 2 * map[r][c] + map[r+1][c];
				go(r, c+1, sum + p);
				visited[r][c+1] = visited[r][c] = visited[r+1][c] = false;
			}
		}
		//ㄱ을 만들 수 없는 경우
		go(r, c+1, sum);
	}
	
	private static boolean inRange(int r, int c) {
		if(r < 0 || r >= N || c < 0 || c >= M) return false;
		return true;
	}
}
