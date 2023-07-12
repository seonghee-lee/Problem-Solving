package prefixsum;

import java.util.*;
import java.io.*;

public class Main_G5_10427_빚 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t = 1; t<=T; t++) {
			String[] inputs = br.readLine().split(" ");
			int N = Integer.parseInt(inputs[0]);
			int[] arr = new int[N + 1];
			for(int i = 1; i<=N; i++) {
				arr[i] = Integer.parseInt(inputs[i]);
			}
			
			Arrays.sort(arr);	//greedy: 연달아 선택하는 것이 최소를 만듦으로 정렬
			
			int[] accum = new int[N+1];	//arr 누적합
			for(int i = 1; i<=N; i++) {
				accum[i] = arr[i] + accum[i-1];
			}
			
			int[] sum = new int[N+1];	//S(i): i번을 선택하여 추가적으로 갚아야 하는 돈의 최솟값
			for(int i = 2; i<=N; i++) {
				int min = Integer.MAX_VALUE;
				for(int start = 1; start <= N - i + 1; start++) {
					int end = start + i - 1;
					int pay = i * arr[end];
					int origin = accum[end] - accum[start - 1];
					min = Math.min(min, pay - origin);
				}
				sum[i] = min;
			}
			
			int cur = 0;
			for(int i = 1; i<=N; i++) {
				cur += sum[i];
			}
			sb.append(cur).append("\n");
		}
		
		System.out.print(sb);
	}

}
