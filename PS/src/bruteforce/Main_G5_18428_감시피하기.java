package bruteforce;

import java.util.*;
import java.io.*;

public class Main_G5_18428_감시피하기 {
	static int N;
	static char[][] map;
	static List<AislePos> empty = new ArrayList<>();
	static List<AislePos> teacher = new ArrayList<>();
	static int[] selected = new int[3];
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1};
	static boolean possible;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		StringTokenizer st;
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j<N; j++) {
				map[i][j] = st.nextToken().charAt(0);
				if(map[i][j] == 'X') empty.add(new AislePos(i, j));
				else if(map[i][j] == 'T') teacher.add(new AislePos(i, j));
			}
		}
		
		go(0, 0);
		if(possible) System.out.println("YES");
		else System.out.println("NO");
	}
	
	private static void go(int cnt, int start) {
		if(possible) return;
		
		if(cnt == 3) {
			boolean success = true;
			changeMap();
			for(AislePos t: teacher) {
				int nr, nc;
				for(int d = 0; d<4; d++) {
					int k = 1;
					while(true) {
						nr = t.r + dr[d] * k;
						nc = t.c + dc[d] * k;
						if(nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] == 'O') break;
						if(map[nr][nc] == 'S') {
							success = false;
							break;
						}
						k++;
					}
					if(!success) break;
				}
				if(!success) break;
			}
			restoreMap();
			if(success) possible = true;
			return;
		}
		
		for(int i = start, size = empty.size(); i<size; i++) {
			selected[cnt] = i;
			go(cnt+1, i+1);
		}
	}
	
	private static void changeMap() {
		int r, c;
		for(int i = 0; i<3; i++) {
			r = empty.get(selected[i]).r;
			c = empty.get(selected[i]).c;
			map[r][c] = 'O';
		}
	}
	
	private static void restoreMap() {
		int r, c;
		for(int i = 0; i<3; i++) {
			r = empty.get(selected[i]).r;
			c = empty.get(selected[i]).c;
			map[r][c] = 'X';
		}
	}
}

class AislePos{
	int r, c;
	AislePos(int r, int c){
		this.r = r;
		this.c = c;
	}
}