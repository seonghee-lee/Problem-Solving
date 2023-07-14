package prefixsum;

import java.util.*;
import java.io.*;

public class Main_G5_20159_동작그만밑장빼기냐 {
	static int N, max;
	static int[] card;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		card = new int[N+1];
		int[] oddSum = new int[N / 2 + 1];
		int[] evenSum = new int[N / 2 + 1];
		int total = 0;
		int oIdx = 1; int eIdx = 1;
		String[] inputs = br.readLine().split(" ");
		for(int i = 1; i<=N; i++) {
			card[i] = Integer.parseInt(inputs[i-1]);
			if(i % 2 != 0) {
				oddSum[oIdx] = oddSum[oIdx - 1] + card[i];
				oIdx++;
			} else {
				evenSum[eIdx] = evenSum[eIdx - 1] + card[i];
				eIdx++;
			}
			total += card[i];
		}
		
		int max = Integer.MIN_VALUE;
		int me = 0;
		
		//나한테 밑장을 줬을 경우
		for(int i = 1; i<=N/2; i++) {
			me = oddSum[i-1] + evenSum[N/2] - evenSum[i-1];
			max = Math.max(max, me);
		}
		
		//상대방에게 밑장을 줬을 경우
		for(int i = 1; i<=N/2; i++) {
			me = oddSum[i] + evenSum[N/2 - 1] - evenSum[i-1];
			max = Math.max(max, me);
		}
		
		System.out.print(max);
		
	}

}
