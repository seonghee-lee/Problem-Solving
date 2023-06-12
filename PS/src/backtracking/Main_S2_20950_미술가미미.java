package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_S2_20950_미술가미미 {
	static int N, ans;
	static int[][] inputs;
	static int[] gomduri, mix;
	static boolean[] selected;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		inputs = new int[N][3];
		gomduri = new int[3];
		mix = new int[3];
		selected = new boolean[N];
		
		StringTokenizer st;
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<3; j++) {
				inputs[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<3; i++) {
			gomduri[i] = Integer.parseInt(st.nextToken());
		}
		
		ans = Integer.MAX_VALUE;
		
		subset(0, 0);
		System.out.println(ans);
	}
	
	private static void subset(int index, int count) {
		if(count > 7) return;
		
		if(index == N) {
			Arrays.fill(mix, 0);
			int K = 0;
			for(int i = 0; i<N; i++) {
				if(selected[i]) {
					K++;
				}
			}
			
			if(K == 0 || K == 1) return;
			
			for(int i = 0; i<N; i++) {
				if(selected[i]) {
					mix[0] += inputs[i][0];
					mix[1] += inputs[i][1];
					mix[2] += inputs[i][2];
				}
			}
			
			for(int i = 0; i<3; i++) {
				mix[i] /= K;
			}
			
			int diff = 0;
			for(int i = 0; i<3; i++) {
				diff += Math.abs(gomduri[i] - mix[i]);
			}
			
			ans = Math.min(ans, diff);
			
			return;
		}
		selected[index] = true;
		subset(index+1, count+1);
		
		selected[index] = false;
		subset(index+1, count);
	}
	
}
