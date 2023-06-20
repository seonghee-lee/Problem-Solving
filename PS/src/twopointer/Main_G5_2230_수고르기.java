package twopointer;

import java.util.*;
import java.io.*;

public class Main_G5_2230_수고르기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		int N = Integer.parseInt(inputs[0]);
		int M = Integer.parseInt(inputs[1]);
		
		int[] arr = new int[N];
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		int start = 0;
		int end = 0;
		int min = Integer.MAX_VALUE;
		
		while(end < N && start <= end) {
			int cur = arr[end] - arr[start];
			
			if(cur >= M) {
				min = Math.min(min, cur);
				start++;
			}else {
				end++;
			}
		}
		
		System.out.println(min);
	}

}
