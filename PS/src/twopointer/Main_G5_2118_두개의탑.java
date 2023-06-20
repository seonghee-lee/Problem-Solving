package twopointer;

import java.util.*;
import java.io.*;

public class Main_G5_2118_두개의탑 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dist = new int[N];
		
		for(int i = 0; i<N; i++) {
			dist[i] = Integer.parseInt(br.readLine());
		}
		
		int[] acSum = new int[N+1];	//누적합 배열
		acSum[0] = 0;
		for(int i = 0; i<N; i++) {
			acSum[i+1] = acSum[i] + dist[i];
		}
		
		int max = Integer.MIN_VALUE;
		int start = 0;
		int end = start + 1;
		
		while(end < N && start < end) {
			int val1 = acSum[end] - acSum[start];
			int val2 = acSum[N] - val1;
			
			max = Math.max(max, Math.min(val1, val2));
			
			if(val1 < val2) end++;
			else start++;
		}
		
		System.out.println(max);
		
	}

}
