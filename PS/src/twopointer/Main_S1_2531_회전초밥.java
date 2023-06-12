package twopointer;

import java.util.*;
import java.io.*;

public class Main_S1_2531_회전초밥 {
	static int N, d, k, c;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		d = Integer.parseInt(inputs[1]);
		k = Integer.parseInt(inputs[2]);
		c = Integer.parseInt(inputs[3]);
		
		int[] arr = new int[N + k - 1];
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i = 0; i<k-1; i++) {
			arr[N+i] = arr[i];
		}	//뒤에 앞부분을 더 붙여준다.
		
		int start = 0;
		int end = 0;
		
		int max = Integer.MIN_VALUE;
		int[] selected = new int[3001];	//고른 초밥: true
		
		int selectCount = 0;
		int diffCount = 0;
		
		while(end < N+k-1) {
			if(selectCount <  k) {
				if(selected[arr[end]] < 1) {
					diffCount++;
				}
				selected[arr[end]]++;
				selectCount++;
				end++;
			} 
			
			if(selectCount >= k || end == N+k-2) {
				if(selected[c] < 1) max = Math.max(max, diffCount + 1);
				else max = Math.max(max, diffCount);
				
				selected[arr[start]]--;
				if(selected[arr[start]] < 1) {
					diffCount--;
				}
				selectCount--;
				start++;
			}
		}
		
		System.out.println(max);
	}

}
