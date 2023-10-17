package implementation;

import java.util.*;
import java.io.*;

public class Main_G3_15684_사다리조작 {
	static int N, M, H;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nmh = br.readLine().split(" ");
		N = Integer.parseInt(nmh[0]);	//세로선 개수
		M = Integer.parseInt(nmh[1]);	//가로선 개수
		H = Integer.parseInt(nmh[2]);	//가로선을 놓을 수 있는 위치의 개수
		
		map = new int[N+1][H+1];	//map[i][j]: i세로선의 j높이에서 연결된 가로선
		
		for(int i = 0; i<M; i++) {
			String[] inputs = br.readLine().split(" ");
			int a = Integer.parseInt(inputs[0]);	//a번 점선 위치에서 연결한다.
			int b = Integer.parseInt(inputs[1]);	//b번 세로선과 b+1번 세로선을
			map[b][a] = b+1;
			map[b+1][a] = b;
		}
		
		if(possible()) {
			System.out.print(0);
			System.exit(0);
		}
		
		int ans = -1;
		for(int i = 1; i<=3; i++) {	//추가해야하는 가로선의 개수
			if(go(0, i)) {
				ans = i;
				break;
			}
		}
		System.out.print(ans);
	}
	
	private static boolean go(int cnt, int limit) {
		if(cnt == limit) {
			if(possible()) return true;
			return false;
		}
		
		//가로선을 놓을 자리를 선정한다. 기존의 선이랑 연속하거나 접하면 안된다.
		for(int b = 1; b<N; b++) {
			for(int a = 1; a<=H; a++) {
				if(map[b][a] > 0 || map[b+1][a] > 0) continue;
				map[b][a] = b+1;
				map[b+1][a] = b;
				if(go(cnt + 1, limit)){
					return true;
				}
				map[b][a] = 0;
				map[b+1][a] = 0;
			}
		}
		return false;
	}
	
	private static boolean possible() {
		for(int i = 1; i<=N; i++) {
			int height = 1;
			int cur = i;
			while(height <= H) {
				if(map[cur][height] == 0) {
					height++;
				} else {
					cur = map[cur][height];
					height++;
				}
			}
			if(cur != i) return false;
		}
		return true;
	}
}
