package implementation;

import java.util.*;
import java.io.*;

public class Main_G5_14719_빗물 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] HW = br.readLine().split(" ");
		int H = Integer.parseInt(HW[0]);
		int W = Integer.parseInt(HW[1]);
		
		boolean[][] map = new boolean[H][W];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<W; i++) {
			int cur = Integer.parseInt(st.nextToken());
			for(int j = 0; j<cur; j++) {
				map[j][i] = true;
			}
		}
		
		int count = 0;
		
		for(int i = 0; i<H; i++) {
			for(int j = 0; j<W; j++) {
				if(!map[i][j]) {
					//좌, 우가 모두 true 벽으로 막혀있는지 확인한다.
					boolean left = false;
					boolean right = false;
					
					int dist = 1;
					while(true) {
						int nc = j - dist;
						if(nc < 0) break;
						if(map[i][nc]) {
							left = true;
							break;
						}
						dist++;
					}

					dist = 1;
					while(true) {
						int nc = j + dist;
						if(nc >= W) break;
						if(map[i][nc]) {
							right = true;
							break;
						}
						dist++;
					}
					
					if(left && right) count++;
				}
			}
		}
		
		System.out.print(count);
	}

}
