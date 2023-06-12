package twopointer;

import java.util.*;
import java.io.*;

public class Main_S1_20922_겹치는건싫어 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		int N = Integer.parseInt(inputs[0]);
		int K = Integer.parseInt(inputs[1]);
		
		int[] numbers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] frequency = new int[100001];	//수열에서 숫자가 몇 번 출현했는 지
		
		int start = 0;
		int end = 0;
		int dist = 0;
		int max = Integer.MIN_VALUE;
		
		while(end < N && start <= end) {
			int curNum = numbers[end];
			
			if(frequency[curNum] < K) {
				frequency[curNum]++;
				end++;
				dist++;
			} else {
				//max 값을 갱신한다.
				max = Math.max(max, dist);
				
				//start를 옮긴다.
				//dist를 갱신한다.
				int count = 0;
				for(int i = start; i<=end; i++) {
					frequency[numbers[i]]--;
					count++;
					if(numbers[i] == curNum) {
						start += count;
						dist -= count;
						break;
					}
				}
			}
		}
		max = Math.max(max, dist);
		System.out.println(max);
	}
}
