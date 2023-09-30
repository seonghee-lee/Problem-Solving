package bruteforce;

import java.util.*;
import java.io.*;

public class Main_S1_15661_링크와스타트 {
	static int N, min;
	static int[][] map;
	static boolean[] selected;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		StringTokenizer st;
		for(int i = 1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		min = Integer.MAX_VALUE;
		selected = new boolean[N+1];
		go(0, 1);
		System.out.println(min);
	}
	
	private static void go(int cnt, int index) {
		if(cnt == N) {
			int start = 0;
			int link = 0;
			boolean sExist = false;
			boolean lExist = false;
			for(int i = 1; i<=N; i++) {
				if(selected[i]) {	//i번 선수가 스타트 팀일때
					sExist = true;
					for(int j = 1; j<=N; j++) {
						if(selected[j]) start += map[i][j];
					}
				} else {	//i번 선수가 링크 팀일때
					lExist = true;
					for(int j = 1; j<=N; j++) {
						if(!selected[j]) link += map[i][j];
					}
				}
			}
			
			if(sExist && lExist) {
				min = Math.min(min, Math.abs(start - link));
			}
			
			return;
		}
		
		selected[index] = true;
		go(cnt + 1, index + 1);
		selected[index] = false;
		go(cnt + 1, index + 1);
	}
}
