package prefixsum;

import java.util.*;
import java.io.*;

public class Main_G5_10713_기차여행 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		int N = Integer.parseInt(nm[0]);	//도시 수
		int M = Integer.parseInt(nm[1]);	//철도 수
		
		int[] arr = new int[M+1];	//여행정보  P[i]
		String[] inputs = br.readLine().split(" ");
		for(int i = 1; i<=M; i++) {
			arr[i] = Integer.parseInt(inputs[i-1]);
		}
		
		int[][] map = new int[N][3];	//map[i][j] : i번 도시의 운임비
		for(int i = 1; i<=N-1; i++) {
			String[] fee = br.readLine().split(" ");
			map[i][0] = Integer.parseInt(fee[0]);	//티켓 구입
			map[i][1] = Integer.parseInt(fee[1]);	//ic 카드 사용
			map[i][2] = Integer.parseInt(fee[2]);	//ic 카드 구매
		}
		
		long[] count = new long[N+1];		//count[i] : i->j 또는 j->i 이동 횟수를 센다.
		for(int i = 1; i<M; i++) {	//i: 일
			int start = 0;
			int end = 0;
			if(arr[i] < arr[i+1]) {
				// 3 5 ... 3비용, 4비용 필요
				start = arr[i];
				end = arr[i+1];
//				for(int j = start; j<end; j++) {
//					count[j]++;
//				}
			} else {
				//7 5 ... 5비용, 6비용 필요
				start = arr[i+1];
				end = arr[i];
//				for(int j = start; j<end; j++) {
//					count[j]++;
//				}
			}
			
			//imos 사용
			count[start]++;
			count[end]--;
		}
		
		//누적합 배열 구하기
		for(int i = 1; i<N; i++) {
			count[i] += count[i-1];
		}
		
		//티켓과 IC 중 싼 것을 선택한다.
		long sum = 0;
		for(int i = 1; i<N; i++) {
			long ticket = count[i] * map[i][0];
			long ic = count[i] * map[i][1] + map[i][2];
			sum += Math.min(ticket, ic);
		}
		
		System.out.println(sum);
		
	}

}
