package dp;

import java.util.*;
import java.io.*;

public class Main_S3_11659_구간합구하기4 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] NM = br.readLine().split(" ");
		int N = Integer.parseInt(NM[0]);
		int M = Integer.parseInt(NM[1]);
		
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] sum = new int[N+1];
		sum[0] = 0;
		for(int i = 1; i<=N; i++) {
			sum[i] = sum[i-1] + arr[i-1];
		}
		
		StringBuilder sb = new StringBuilder(200000);
		for(int i = 0; i<M; i++) {
			String[] inputs = br.readLine().split(" ");
			int s = Integer.parseInt(inputs[0]);
			int e = Integer.parseInt(inputs[1]);
			
			int ans = sum[e] - sum[s-1];
			sb.append(ans).append("\n");
		}
		
		System.out.print(sb);
	}

}
