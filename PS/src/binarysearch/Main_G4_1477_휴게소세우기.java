package binarysearch;

import java.util.*;
import java.io.*;

/**
 * [참고 해설] https://namhandong.tistory.com/199
 * 
 * 휴게소가 없는 구간의 최댓값의 최솟값(dist)을 1 ~ 999의 이분탐색으로 하나씩 시도해보자.
 * => 세워져 있는 휴게소 - 휴게소 사이에 dist 로 휴게소를 세울 수 있으면 세우고, 세우지 못하면 세우지 않는다.
 * => 기존에 세워져있는 휴게소-휴게소 사이에서 dist로 최대한 휴게소를 다 세워보고,
 *    그때 몇 개를 지었는 지 세본다.
 *    => M개 보다 적게(또는 같게) 지었다면: dist를 줄여서 더 많이 지어야 한다. // ==가 여기에 들어가는 이유는 최솟값을 구하는 문제니까 dist를 줄여보는 시도를 끝까지 해봐야 하므로. 
 *       M개 보다 많이 지었다면: dist를 키워서 더 조금 지어야 한다.
 */

public class Main_G4_1477_휴게소세우기 {
	static int N, M, L;
	static int[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nml = br.readLine().split(" ");
		N = Integer.parseInt(nml[0]);
		M = Integer.parseInt(nml[1]);
		L = Integer.parseInt(nml[2]);
		
		arr = new int[N+2];
		arr[0] = 0;
		arr[N+1] = L;
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int start = 1;
		int end = L - 1;
		int mid = 0;
		int ans = 0;
		
		while(start <= end) {
			
			mid = (start + end) / 2;	//휴게소 사이의 거리의 최댓값의 최솟값
			int count = 0;	//세울 수 있는 휴게소
			
			for(int i = 1, len = arr.length; i < len; i++) {
				
				int cur = (arr[i] - arr[i-1]) / mid;
				
				if(cur > 0) {
					if((arr[i] - arr[i-1]) % mid == 0) {
						count += cur - 1;
					} else {
						count += cur;
					}
				}
			}
			
			if(count <= M) {
				ans = mid;
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		
		System.out.print(ans);
	}

}
