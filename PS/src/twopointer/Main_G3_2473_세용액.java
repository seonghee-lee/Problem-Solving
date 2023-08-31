package twopointer;

import java.util.*;
import java.io.*;

public class Main_G3_2473_세용액 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		long min = Long.MAX_VALUE;
		int[] ans = new int[3];
		
		top: 
		for(int i = 0; i<N-2; i++) {	//i: 고정 선택 용액
			int start = i + 1;
			int end = N - 1;
			int target = arr[i] * (-1);
			
			while(start < end) {
				long cur = arr[start] + arr[end];
				
				if(cur == target) {
					ans[0] = arr[i];
					ans[1] = arr[start];
					ans[2] = arr[end];
					break top;
				}
				
				if(Math.abs(arr[i] + cur) < min) {
					ans[0] = arr[i];
					ans[1] = arr[start];
					ans[2] = arr[end];
					min = Math.abs(arr[i] + cur);
				}
				
				if(cur > target) end--;
				else start++;
			}
		}
		
		System.out.printf("%d %d %d", ans[0], ans[1], ans[2]);
	}

}
