package bruteforce;

import java.util.*;
import java.io.*;

public class Main_S2_18111_마인크래프트 {
	static int N, M, B;
	static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nmb = br.readLine().split(" ");
		N = Integer.parseInt(nmb[0]);
		M = Integer.parseInt(nmb[1]);
		B = Integer.parseInt(nmb[2]);
		
		map = new int[N][M];
		
		StringTokenizer st;
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int minTime = Integer.MAX_VALUE;
		int maxHeight = Integer.MIN_VALUE;
		
		for(int height = 0; height <= 256; height++) {
			int time = 0;
			int inventory = B;
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<M; j++) {
					if(map[i][j] == height) continue;
					else if(map[i][j] > height) {
						time += (map[i][j] - height) * 2;
						inventory += map[i][j] - height;
					} else {
						time += height - map[i][j];
						inventory -= height - map[i][j];
					}
				}
			}
			
			if(inventory < 0) continue;
			
			if(time <= minTime) {
				if(time == minTime) {
					maxHeight = Math.max(maxHeight, height);
				} else {
					minTime = time;
					maxHeight = height;
				}
			}
		}
		System.out.printf("%d %d", minTime, maxHeight);
	}

}
