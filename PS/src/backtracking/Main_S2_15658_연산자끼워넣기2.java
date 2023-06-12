package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S2_15658_연산자끼워넣기2 {
	static int N, K, max, min;
	static int[] A, op, select;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = N - 1;		//들어가야 하는 연산자 수
		
		A = new int[N];
		select = new int[K];
		op = new int[4];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<4; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}
		
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		
		selectOp(0);
		System.out.printf("%d%n%d", max, min);
	}
	private static void selectOp(int cnt) {
		if(cnt == K) {
			int loop = 0;
			int num = A[0];
			while(loop < N - 1) {
				if(select[loop] == 0) {
					num += A[loop+1];
				}else if(select[loop] == 1) {
					num -= A[loop+1];
				}else if(select[loop] == 2) {
					num *= A[loop+1];
				}else {
					num /= A[loop+1];
				}
				loop++;
			}
			
			max = Math.max(max, num);
			min = Math.min(min, num);
			return;
		}
		
		for(int i = 0; i<4; i++) {
			if(op[i] <= 0) continue;
			select[cnt] = i;
			op[i]--;
			selectOp(cnt+1);
			op[i]++;
		}
	}

}
