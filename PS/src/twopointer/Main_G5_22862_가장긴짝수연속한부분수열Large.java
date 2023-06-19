package twopointer;

import java.util.*;
import java.io.*;

public class Main_G5_22862_가장긴짝수연속한부분수열Large {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		int N = Integer.parseInt(inputs[0]);
		int K = Integer.parseInt(inputs[1]);
		
		int[] arr = new int[N];
		boolean[] state = new boolean[N];	//true: 짝수, false: 홀수
		Arrays.fill(state, false);

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(arr[i] % 2 == 0) state[i] = true;
		}
		
		int start = 0;
		int end = 0;
		
		int max = Integer.MIN_VALUE;
		int count = 0;
		
		while(end < N) {
			//count를 증가시키면서 동작하고, count > K 면 종료해야하므로
			if(count < K) {
				if(!state[end]) {		//end가 홀수일 경우
					count++;			//홀수 개수를 1개 증가시킨다.
				}
				end++;	//뒤로 한 칸 보낸다.
				max = Math.max(max, end - start - count);	//수열 최대 길이 갱신
			} else if(count == K) {		//모든 k를 사용했을 경우
				//뒤에가 짝수일 때
				if(state[end]) {
					end++;	//그냥 뒤로 한 칸 보낸다.
					max = Math.max(max, end - start - count);	//수열 최대 길이 갱신
				} else {	//모든 k를 사용했고 뒤에가 짝수가 아닐 때
					if(!state[start]) {		//앞을 확인한다. 만약 앞이 홀수라면
						count--;		//사용가능한 K를 1늘린다.
					}
					start++;			//start를 뒤로 한 칸 이동한다.
				}
			}
		}
		
		System.out.println(max);

	}

}
