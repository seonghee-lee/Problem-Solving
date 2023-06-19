package twopointer;

import java.util.*;
import java.io.*;

public class Main_G5_2470_두용액 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] liq = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			liq[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(liq);
		
		long sum = 0;
		
		int start = 0;
		int end = N - 1;
		long min = Integer.MAX_VALUE;
		
		int ans1 = -1;
		int ans2 = -1;
		
		while(start < end) {
			sum = liq[start] + liq[end];
			
			if(sum == 0) {
				ans1 = start;
				ans2 = end;
				break;
			}
			
			if(Math.abs(sum) < Math.abs(min)) {
				min = sum;
				ans1 = start;
				ans2 = end;
			}
			
			if(sum < 0) {
				start++;
			} else end--;
		}
		
		System.out.printf("%d %d", liq[ans1], liq[ans2]);
	}

}
