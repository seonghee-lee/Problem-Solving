package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 2개의 십자가를 겹치지 않게 놓았을 때, 얻을 수 있는 각 십자가 넓이 곱의 최댓값
 * [오답접근]
 * - 백트래킹으로 하나의 십자가를 최대 크기로 놓고, 다음 십자가를 놓는다.
 *   -> 다음 십자가를 놓았을 때 최댓값이 되는 상황을 정확하게 구하지 못한다.
 * [새로운 방법]
 * - 십자가를 놓을 수 있는 최대 len을 구한다.
 *   그 len까지 십자가를 놓아본다.
 *     - 하나의 십자가를 이렇게 놓고, 두번째 십자가를 이 상태에서 놓는다.
 *     - 두 십자가 넓이의 곱을 구한다.
 */

public class Main_G5_17085_십자가2개놓기 {
	static int N, M, max;
	static char[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		max = Integer.MIN_VALUE;
		
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<M; j++) {
				if(map[i][j] == '#') {
					int maxLen = getMaxLength(i, j);	//첫번째 십자가가 가질 수 있는 최대 길이를 구한다.
					for(int k = 0; k <= maxLen; k++) {		//모든 놓여질 수 있는 첫번째 십자가를 놓는다.
						makeCross(i, j, k, '.');	//십자가를 놓는다.
						
						//두번째 십자가
						for(int r = 0; r<N; r++) {
							for(int c = 0; c<M; c++) {
								if(map[r][c] == '#') {
									int maxLen2 = getMaxLength(r, c);	//두번째 십자가가 가질 수 있는 최대 길이를 구한다.
									
									int width1 = 1 + k*4;			//중심좌표 + 현재 첫번째 십자가 길이 * 4
									int width2 = 1 + maxLen2*4;		//중심좌표 + 최대길이 * 4
									
									max = Math.max(max, width1 * width2);
								}
							}
						}
						makeCross(i, j, k, '#');	//놓은 십자가 되돌리기
					}
				}
			}
		}
		System.out.println(max);
	}

	//maxLen만큼 십자가 모양으로 val을 놓는다.(십자가를 만들때는 '.', 십자가를 없앨때는 '#')
	private static void makeCross(int centerR, int centerC, int len, char val) {
		for(int i = 0; i <= len; i++) {
			for(int d = 0; d<4; d++) {
				int nr = centerR + i * dr[d];
				int nc = centerC + i * dc[d];
				map[nr][nc] = val;
			}
		}
	}

	//(centerR, centerC)를 중심으로 하는 십자가의 최대 길이 반환
	private static int getMaxLength(int centerR, int centerC) {
		int length = 0;
		boolean flag = true;
		while(true) {
			for(int i = 0; i<4; i++) {
				int nr = centerR + length * dr[i];
				int nc = centerC + length * dc[i];
				if(nr < 0 || nr >= N || nc < 0 || nc >=M || map[nr][nc] == '.') {
					flag = false;
					break;
				}
			}
			if(!flag) break;
			length++;
		}
		return length - 1;
	}
	
	
}
