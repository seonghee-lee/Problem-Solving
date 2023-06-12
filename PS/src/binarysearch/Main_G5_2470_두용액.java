package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 이분탐색이라도, 모든 경우를 탐색해야 하는 건 변함이 없다!
 * 대신 최소였던 값을 저장해두는 방식을 사용!
 * */
public class Main_G5_2470_두용액 {
	static int N;
	static int[] value;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		value = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			value[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(value);
		
		int[] result = new int[2];
		
		int startIdx = 0;
		int endIdx = N-1;
		long minDiff = 2000000001l;
		
		while(startIdx < endIdx) {
			
			long sum = value[startIdx] + value[endIdx];
			
			if(Math.abs(sum) < Math.abs(minDiff)) {
				minDiff = sum;
				result[0] = value[startIdx];
				result[1] = value[endIdx];
			}
						
			if(sum > 0) {
				endIdx--;
			} else if(sum < 0) {
				startIdx++;
			} else if(sum == 0) {
				break;
			}
		}
		Arrays.sort(result);
		System.out.printf("%d %d", result[0], result[1]);
	}

}
