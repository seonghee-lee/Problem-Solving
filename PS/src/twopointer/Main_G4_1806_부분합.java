package twopointer;

import java.util.*;
import java.io.*;
	
public class Main_G4_1806_부분합 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		int N = Integer.parseInt(inputs[0]);
		int S = Integer.parseInt(inputs[1]);
		
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int min = Integer.MAX_VALUE;
		
		int sum = 0;
		int start = 0;
		int end = 0;
		
		boolean flag = false;
		
		while(end <= N) {
			
			if(sum < S) {
				if(end == N) break;		//**계속 틀린 부분!!! : 한 바퀴 다시 돌아왔을 때를 생각해야한다.
				sum += arr[end];
				end++;
			} else {
				flag = true;
				
				sum -= arr[start];
				start++;
				min = Math.min(min, end - start + 1);
			}
		}
		
		if(!flag) System.out.println(0);
		else System.out.println(min);
	}

}
