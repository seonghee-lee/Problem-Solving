package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 참 하나는 모르고 둘은 모르는게 바로 나 이지 않을까..^^..
 * 하다보면 나아지겠지.. 어제보단 오늘이, 오늘보단 내일이.. 더 나아지겠지..
 * 왜 재귀를 똑똑하게 쓰는 법을 생각해내지 못할까.. 문제를 더 많이 풀어봐야겠지..
 */

public class Main_S1_14888_연산자끼워넣기 {
	static int N, min, max;
	static int[] numbers, opt;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		numbers = new int[N];
		opt = new int[4];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<4; i++) {
			opt[i] = Integer.parseInt(st.nextToken());
		}
		
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		
		go(1, numbers[0]);
		System.out.println(max);
		System.out.println(min);
	}
	
	private static void go(int cnt, int calc) {
		
		if(cnt == N) {
			min = Math.min(min, calc);
			max = Math.max(max, calc);
			return;
		}
		
		for(int i = 0; i<4; i++) {
			if(opt[i] > 0) {
				opt[i]--;
				switch(i) {
					case 0: go(cnt+1, calc + numbers[cnt]);
							break;
					case 1: go(cnt+1, calc - numbers[cnt]);
							break;
					case 2: go(cnt+1, calc * numbers[cnt]);
							break;
					case 3: go(cnt+1, calc / numbers[cnt]);
							break;
				}
				opt[i]++;
			}
		}
	}
}
