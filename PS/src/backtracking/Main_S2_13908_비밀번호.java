package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_S2_13908_비밀번호 {
	static int N, M, count;
	static int[] include, result;
	static boolean flag;
	static boolean[] check;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		include = new int[M];
		result = new int[N];
		check = new boolean[M];
		
		if(M != 0) {
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i<M; i++) {
				include[i] = Integer.parseInt(st.nextToken());
			}
		}
		
		perm(0);
		System.out.println(count);
	}

	private static void perm(int cnt) {
		
		if(cnt == N) {
			
			//필수 비밀번호를 포함한다면
			Arrays.fill(check, false);
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<M; j++) {
					if(result[i] == include[j]) {
						check[j] = true;
					}
				}
			}
			
			flag = true;
			for(int i = 0; i<M; i++) {
				if(!check[i]) {
					flag = false;
					break;
				}
			}
			
			if(!flag) return;
			
			count++;
			return;
		}
		
		for(int i = 0; i<= 9; i++) {
			result[cnt] = i;
			perm(cnt+1);
		}
	}

}
