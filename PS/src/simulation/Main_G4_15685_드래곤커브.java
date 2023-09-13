package simulation;

import java.util.*;
import java.io.*;

public class Main_G4_15685_드래곤커브 {
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {1, 0, -1, 0};
	static boolean[][] map = new boolean[101][101];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		List<Integer> dragonDir = new ArrayList<>();
		
		for(int i = 0; i<N; i++) {
			String[] inputs = br.readLine().split(" ");
			int y = Integer.parseInt(inputs[0]);
			int x = Integer.parseInt(inputs[1]);
			int d = Integer.parseInt(inputs[2]);
			int g = Integer.parseInt(inputs[3]);
			
			dragonDir.clear();
			dragonDir.add(d);
			
			int endX = x + dx[d];
			int endY = y + dy[d];

			map[x][y] = true;
			
			if(endX >= 0 && endY >= 0 && endX <=100 && endY <= 100)
				map[endX][endY] = true;
			
			for(int j = 0; j<g; j++) {
				int lastIdx = dragonDir.size() - 1;
				for(int k = lastIdx; k >= 0; k--) {
					int dir = dragonDir.get(k);
					int ndir = (dir + 1) % 4;
					
					dragonDir.add(ndir);
					endX += dx[ndir];
					endY += dy[ndir];
					
					if(endX < 0 || endY < 0 || endX > 100 || endY > 100) continue;
					
					map[endX][endY] = true;
				}
			}
		}
		
		int count = 0;
		
		for(int x = 0; x<100; x++) {
			for(int y = 0; y<100; y++) {
				if(map[x][y] && map[x+1][y] && map[x][y+1] && map[x+1][y+1]) count++;
			}
		}
		
		System.out.println(count);
	}
}
