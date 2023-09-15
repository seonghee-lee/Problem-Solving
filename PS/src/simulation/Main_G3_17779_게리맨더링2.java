package simulation;

import java.util.*;
import java.io.*;

public class Main_G3_17779_게리맨더링2 {
	static int N, ans;
	static int[][] popul;	//인구수
	static int[][] map;
	static int[] dx = {1, 1, 1, 1};
	static int[] dy = {-1, 1, 1, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		popul = new int[N+1][N+1];
		
		StringTokenizer st;
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=N; j++) {
				popul[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = Integer.MAX_VALUE;
		
		for(int x = 1; x<=N; x++) {
			for(int y = 1; y<=N; y++) {
				for(int d1 = 1; d1<=N; d1++) {
					for(int d2 = 1; d2<=N; d2++) {
						if(x+d1+d2 > N) continue;
						if(y-d1 < 1 || y + d2 > N) continue;
						map = new int[N+1][N+1];
						
						boundary(x, y, d1, d2);
						fillNum(x, y, d1, d2);
						
						int[] count = new int[6];
						count = getCount();
						
						int max = Integer.MIN_VALUE;
						int min = Integer.MAX_VALUE;
						for(int i = 1; i<=5; i++) {
							if(count[i] > max) max = count[i];
							if(count[i] < min) min = count[i];
						}
						ans = Math.min(ans, max-min);
					}
				}
			}
		}
		
		System.out.println(ans);
	}
	
	private static void boundary(int x, int y, int d1, int d2) {
		toFive(x, y, x+d1, y-d1, 0);
		toFive(x, y, x+d2, y+d2, 1);
		toFive(x+d1, y-d1, x+d1+d2, y-d1+d2, 2);
		toFive(x+d2, y+d2, x+d2+d1, y+d2-d1, 3);
		
		//경계선 안을 5로 채운다.
		for(int i = 1; i<=N; i++) {
			boolean flag = false;
			int start = -1;
			for(int j = 1; j<=N; j++) {
				if(map[i][j] == 5) {
					if(!flag) {
						flag = true;
						start = j;
					}
					else {
						for(int k = start+1; k<j; k++) {
							map[i][k] = 5;
						}
					}
				}
			}
		}
	}
	
	private static void toFive(int sx, int sy, int ex, int ey, int dir) {
		int x = sx;
		int y = sy;
		while(true) {
			map[x][y] = 5;
			
			if(x == ex && y == ey) break;
			
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			
			if(nx <= 0 || nx > N || ny <= 0 || ny > N) continue;
			
			x = nx;
			y = ny;
		}
	}
	
	private static void fillNum(int x, int y, int d1, int d2) {
		fill(1, 1, x+d1-1, y, 1);
		fill(1, y+1, x+d2, N, 2);
		fill(x+d1, 1, N, y-d1+d2-1, 3);
		fill(x+d2+1, y-d1+d2, N, N, 4);
	}
	
	private static void fill(int sx, int sy, int ex, int ey, int num) {
		int x = sx;
		int y = sy;
		while(true) {
			
			if(x > ex) break;
			
			if(num == 1 || num == 3) {
				if(y > ey || map[x][y] == 5) {
					y = sy;
					x++;
					continue;
				} 
			} else {
				 if(y > ey) {
						y = sy;
						x++;
						continue;
				 }
				 else if(map[x][y] == 5) {
					y++;
					continue;
				}
			}
			map[x][y] = num;
			y++;
		}
	}
	
	private static int[] getCount() {
		int[] result = new int[6];
		
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=N; j++) {
				if(map[i][j] == 1) result[1] += popul[i][j];
				else if(map[i][j] == 2) result[2] += popul[i][j];
				else if(map[i][j] == 3) result[3] += popul[i][j];
				else if(map[i][j] == 4) result[4] += popul[i][j];
				else result[5] += popul[i][j];
			}
		}
		
		return result;
	}
}
