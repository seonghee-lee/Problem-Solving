package binarysearch;

import java.util.*;
import java.io.*;

/**
 * (0, 0)에서 시작해서 y좌표가 T가 될 때까지 오른다. 
 * 최단경로로 가야하므로 bfs 사용 -> visited 배열 사용하면 메모리 터짐
 * 이동할 수 있는 y에 대해서 x좌표를 담은 인접 set 사용
 * 방문한 x에 대해서는 제거
 */

public class Main_G4_2412_암벽등반 {
	static int N, T;
	static Set<Integer>[] set;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nt = br.readLine().split(" ");
		N = Integer.parseInt(nt[0]);
		T = Integer.parseInt(nt[1]);
		
		set = new HashSet[200001];
		for(int i = 0; i<=200000; i++) {
			set[i] = new HashSet<Integer>();
		}
		
		for(int i = 0; i<N; i++) {
			String[] inputs = br.readLine().split(" ");
			int x = Integer.parseInt(inputs[0]);
			int y = Integer.parseInt(inputs[1]);
			set[y].add(x);
		}
		
		int ans = bfs(0, 0);
		if(ans == -1) System.out.println(-1);
		else System.out.println(ans);
	}
	
	private static int bfs(int startX, int startY) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[]{startX, startY});
		
		int count = 0;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int q = 0; q<size; q++) {
				int[] cur = queue.poll();
				
				if(cur[1] == T) {
					return count;
				}
				
				for(int i = -2; i<=2; i++) {
					if(cur[1] + i < 0) continue;
					int newY = cur[1] + i;
					
					if(set[newY].isEmpty()) continue;
					
					List<Integer> removeList = new ArrayList<>();
					
					for(Integer newX : set[newY]) {
						if(Math.abs(cur[0] - newX) <= 2) {
							queue.offer(new int[] {newX, newY});
							removeList.add(newX);
						}
					}
					
					for(Integer k : removeList) {
						set[newY].remove(k);
					}
				}
			}
			count++;
		}
		return -1;
	}
}
