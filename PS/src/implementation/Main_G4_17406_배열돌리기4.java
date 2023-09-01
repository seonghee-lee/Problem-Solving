package implementation;

import java.util.*;
import java.io.*;

public class Main_G4_17406_배열돌리기4 {
	static int N, M, K, min, startR, startC, endR, endC;
	static int[][] map, rotMap;
	static int[] orders;
	static boolean[] selected;
	static int[] dr = {0, 1, 0, -1};	//우, 하, 좌, 상
	static int[] dc = {1, 0, -1, 0};
	static List<Order> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nmk = br.readLine().split(" ");
		N = Integer.parseInt(nmk[0]);
		M = Integer.parseInt(nmk[1]);
		K = Integer.parseInt(nmk[2]);
		
		map = new int[N+1][M+1];
		rotMap = new int[N+1][M+1];
		
		StringTokenizer st;
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i<K; i++) {
			String[] rcs = br.readLine().split(" ");
			int r = Integer.parseInt(rcs[0]);
			int c = Integer.parseInt(rcs[1]);
			int s = Integer.parseInt(rcs[2]);
			
			list.add(new Order(r, c, s));
		}
		
		selected = new boolean[K];
		orders = new int[K];
		min = Integer.MAX_VALUE;
		perm(0);
		
		System.out.println(min);
	}
	
	private static void perm(int count) {
		if(count == K) {
			
			initMap();
			
			for(int i = 0; i<K; i++) {
				Order o = list.get(orders[i]);
				
				int boxCount = o.s;	//무조건 정사각형 형태의 박스임
				for(int k = 0; k<boxCount; k++) {
					startR = o.r - o.s + k;
					startC = o.c - o.s + k;
					endR = o.r + o.s - k;
					endC = o.c + o.s - k;
					
					rotate();
				}
			}
			
			int value = calcArrayValue();
			min = Math.min(min, value);
			
			return;
		}
		
		for(int i = 0; i<K; i++) {
			if(selected[i]) continue;
			selected[i] = true;
			orders[count] = i;
			perm(count + 1);
			selected[i] = false;
		}
	}
	
	private static void initMap() {
		for(int i = 1; i<=N; i++) {
			for(int j = 1; j<=M; j++) {
				rotMap[i][j] = map[i][j];
			}
		}
	}
	
	private static void rotate() {
		int r = startR;
		int c = startC;
		int dir = 0;
		int prev = rotMap[r][c];
		int next = 0;
		
		while(dir < 4) {
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			if(nr >= startR && nr <= endR && nc >= startC && nc <= endC) {
				next = rotMap[nr][nc];
				rotMap[nr][nc] = prev;
				prev = next;
				r = nr;
				c = nc;
			} else {
				dir++;
			}
		}
	}
	
	private static int calcArrayValue() {
		int minValue = Integer.MAX_VALUE;
		
		for(int i = 1; i<=N; i++) {
			int sum = 0;
			for(int j = 1; j<=M; j++) {
				sum += rotMap[i][j];
			}
			minValue = Math.min(minValue, sum);
		}
		
		return minValue;
	}
}

class Order {
	int r, c, s;
	Order(int r, int c, int s){
		this.r = r;
		this.c = c;
		this.s = s;
	}
}
