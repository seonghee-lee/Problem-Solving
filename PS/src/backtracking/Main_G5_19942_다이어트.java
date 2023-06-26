package backtracking;

import java.util.*;
import java.io.*;

public class Main_G5_19942_다이어트 {
	static int N, min, cost;
	static int mp, mf, ms, mv;
	static int[][] arr;
	static boolean[] selected;
	static boolean have;
	static List<Integer> list = new ArrayList<>();
	static List<String> ans = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String[] inputs = br.readLine().split(" ");
		mp = Integer.parseInt(inputs[0]);
		mf = Integer.parseInt(inputs[1]);
		ms = Integer.parseInt(inputs[2]);
		mv = Integer.parseInt(inputs[3]);
		
		arr = new int[N][5];
		StringTokenizer st;
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<5; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		selected = new boolean[N];
		min = Integer.MAX_VALUE;
		
		subset(0);

		if(have) {
			Collections.sort(ans);
			StringBuilder sb = new StringBuilder();
			sb.append(min).append("\n");
			sb.append(ans.get(0));
			System.out.print(sb);
		} else {
			System.out.println(-1);
		}
	}
	
	private static void subset(int idx) {
		if(idx == N) {
			cost = 0;
			if(possible()) {
				have = true;
				if(cost <= min) {
					StringBuilder output = new StringBuilder();
					for(int i = 0; i<N; i++) {
						if(selected[i]) {
							output.append(i+1).append(" ");
						}
					}
					if(cost == min) {
						ans.add(output.toString());
					} else {
						ans.clear();
						ans.add(output.toString());
					}
					min = Math.min(min, cost);
				}
			}
			return;
		}
		
		selected[idx] = true;
		subset(idx + 1);
		selected[idx] = false;
		subset(idx + 1);
	}
	
	private static boolean possible() {
		boolean flag = false;
		
		int p = 0;
		int f = 0;
		int s = 0;
		int v = 0;
		
		for(int i = 0; i<N; i++) {
			if(selected[i]) {
				p += arr[i][0];
				f += arr[i][1];
				s += arr[i][2];
				v += arr[i][3];
				cost += arr[i][4];
			}
		}
		
		if(p >= mp && f >= mf && s >= ms && v >= mv) flag = true;
		
		return flag;
	}
}
