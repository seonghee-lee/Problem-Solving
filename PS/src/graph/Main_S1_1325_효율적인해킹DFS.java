package graph;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 시간초과...
 */

public class Main_S1_1325_효율적인해킹DFS {
	static int N, M, max, count;
	static int[][] map;
	static boolean[] hacked;
	static int[] memo;
	static List<Integer> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[M][2];
		hacked = new boolean[N+1];
		
		for(int i = 0 ; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			map[i][0] = Integer.parseInt(st.nextToken());
			map[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i<=N; i++) {
			Arrays.fill(hacked, false);
			count = 0;

			hacking(i);
			
			if(count > max) {
				max = count;
				list.clear();
				list.add(i);
			}else if(count == max) {
				list.add(i);
			}
		
		}
		
		Collections.sort(list);
		
		StringBuilder sb = new StringBuilder(100);
		for(int i : list) {
			sb.append(i).append(" ");
		}
		System.out.println(sb.toString().trim());
	}
	
	private static void hacking(int computer) {
		for(int i = 0; i<M; i++) {
			if(map[i][1] == computer) {
				if(!hacked[map[i][1]]) {
					hacked[map[i][1]] = true;
					count++;
					hacking(map[i][0]);
				}
			}
		}
	}

}
