package implementation;

import java.util.*;
import java.io.*;

/**
 * 1 <= (회전 횟수) <= 10^9
 * : x번 회전했을때 현재와 같은 상황인지 알아내서 회전수를 줄인다.
 *   -> 회전은 상자별로 각각 수행한다. (상자별로 새로운 회전수를 가지므로)
 */

public class Main_G5_16927_배열돌리기2 {
	static int N, M, R;
	static int[][] map;
	static int[] dr = {1, 0, -1, 0};	//하, 우, 상, 좌
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nmr = br.readLine().split(" ");
		N = Integer.parseInt(nmr[0]);
		M = Integer.parseInt(nmr[1]);
		R = Integer.parseInt(nmr[2]);
		
		map = new int[N][M];
		
		StringTokenizer st;
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int boxCount = Math.min(N, M) / 2;
		int n = N; int m = M;	//박스 별 가로, 세로 길이
		
		for(int i = 0; i<boxCount; i++) {
			int mod = 2 * n + 2 * m - 4;	//i번 박스는 mod회 회전하면 원상태로 돌아온다.
			rotate(i, mod);	//회전 시작 좌표는 (i, i)이다.
			
			n -= 2;
			m -= 2;	//안쪽 상자는 가로 세로가 2씩 작음
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				sb.append(map[i][j]).append(" ");
			}sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void rotate(int start, int mod) {
		
		int rot = R % mod;	//상자를 rot회 회전시킨다.
		
		for(int i = 0; i<rot; i++) {	//i: 현재 회전 수
			int r = start;
			int c = start;
			int prev = map[r][c];
			int next = -1;
			int dir = 0;	//값을 이동시킬 방향
			
			while(dir < 4) {	//하, 우, 상, 좌로 값을 이동시킨다.
				int nr = r + dr[dir];
				int nc = c + dc[dir];
				
				//다음 좌표가 현재 상자 범위 내에 있다면,
				if(nr >= start && nr < N - start && nc >= start && nc < M - start) {
					next = map[nr][nc];
					map[nr][nc] = prev;
					prev = next;
					r = nr;
					c = nc;
				} else {
					dir++;	//다음 방향으로 변경한다.
				}
			}
		}
	}
}
