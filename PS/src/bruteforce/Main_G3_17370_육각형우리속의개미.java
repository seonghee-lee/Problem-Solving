package bruteforce;

import java.util.*;

/**
 * 이전에 어느 방향에서 왔는지를 확인하는 방식으로 구현
 * 재귀함수에서 visited 시점 결정 주의
 */

public class Main_G3_17370_육각형우리속의개미 {
	static int N, count;
	static boolean[][] visited = new boolean[200][200];
	static int[] dr = {-1, -1, 1, 1, 1, -1};	//상, 우상, 우하, 하, 좌하, 좌상
	static int[] dc = {0, 1, 1, 0, -1, -1};
	static int[][] move = new int[6][];	//move[i][]: 이전 이동이 i방향일 때, 현재 이동으로 가능한 방향 
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		move[0] = new int[] {1, 5};
		move[1] = new int[] {0, 2};
		move[2] = new int[] {1, 3};
		move[3] = new int[] {2, 4};
		move[4] = new int[] {3, 5};
		move[5] = new int[] {0, 4};
		
		visited[101][100] = true;	//시작 지점
		go(100, 100, 0, 0);		//위로 1 이동하면서 시작한다.
		
		System.out.println(count);
	}
	
	private static void go(int r, int c, int dir, int turn) {
		if(visited[r][c]) {
			if(turn == N) count++;
			return;
		}
		
		if(turn == N) return;
		
		visited[r][c] = true;
		
		int nr, nc, nd;
		for(int i = 0; i<2; i++) {
			nd = move[dir][i];
			nr = r + dr[nd];
			nc = c + dc[nd];
			go(nr, nc, nd, turn+1);
		}
		
		visited[r][c] = false;
	}
}
