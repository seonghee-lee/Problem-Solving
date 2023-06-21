package twopointer;

import java.util.*;
import java.io.*;

public class Main_G4_13422_도둑 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder(10000);
		for(int t = 1; t<=T; t++) {
			String[] inputs = br.readLine().split(" ");
			int N = Integer.parseInt(inputs[0]);
			int M = Integer.parseInt(inputs[1]);	//윈도우 크기
			int K = Integer.parseInt(inputs[2]);	//방범장치
			
			int[] house = new int[N + M - 1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i<N; i++) {
				house[i] = Integer.parseInt(st.nextToken());
			}
			
			//앞, 뒤가 연결되어 있는 걸 붙여준다.
			for(int i = 0; i<M-1; i++) {
				house[N+i] = house[i];
			}
			
			int count = 0;	//방법의 수
			int start = 0;
			int end = 0;
			int sum = 0;
			
			while(end < N + M - 1) {
				
				if(N == M) {
					if(end >= N) break;
				}
				
				if(end - start + 1 < M) {
					sum += house[end];
					end++;
					continue;
				}
				
				if(end - start + 1 == M) {
					if(end >= N+M-1) break;
					
					sum += house[end];
					
					if(sum < K) {
						count++;
					}
					end++;
				} else {
					sum -= house[start];
					start++;
				}
			}
			sb.append(count).append("\n");
		}
		System.out.print(sb);
	}
}
