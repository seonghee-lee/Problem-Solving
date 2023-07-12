package prefixsum;

/*
 * 누적합: 주어진 배열을 이용하여, 내가 구하고자 하는 '다른 요소'의 누적합을 구할 수 있다.
 * 여기서는 다른 요소 == i번째 악보를 칠 때까지의 실수 횟수의 누적합
 * */

import java.util.*;
import java.io.*;

public class Main_S1_21318_피아노체조 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		String[] inputs = br.readLine().split(" ");
		for(int i = 1; i<=N; i++) {
			arr[i] = Integer.parseInt(inputs[i-1]);
		}
		
		int[] mistakeSum = new int[N+1];
		for(int i = 1; i<N; i++) {
			if(arr[i] > arr[i+1]) {
				mistakeSum[i+1] = mistakeSum[i] + 1;
			} else {
				mistakeSum[i+1] = mistakeSum[i];
			}
		}
		
		int Q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int q = 1; q<=Q; q++) {
			String[] se = br.readLine().split(" ");
			int s = Integer.parseInt(se[0]);
			int e = Integer.parseInt(se[1]);
			
			sb.append(mistakeSum[e] - mistakeSum[s]).append("\n");
		}
		
		System.out.print(sb);
	}

}
