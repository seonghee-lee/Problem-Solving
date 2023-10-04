package prefixsum;

import java.util.*;
import java.io.*;

/**
 * arr[i] - arr[j] - K(i-j)
 * = (arr[i] - K*i) - (arr[j] - K*j)
 * 두 항은 각각 독립적인 연산으로 이루어진다. 
 * i가 for문으로 증가할 때 (자석의 오른쪽 끝) 
 * j항은 독립적인 min값을 갱신하며 MAX를 구한다. (자석의 왼쪽끝이 1일때부터 i-1일때까지의 최솟값) : 1씩 증가하며 오기 때문에 이중 반복문이 필요하지 않다.
 * 
 * 자석이 SN으로 놓여있을 때와 NS로 놓여있을 때의 경우를 둘 다 고려한 최댓값을 구해야 한다.
 * => 자석을 뒤집는 것은 에너지 배열을 뒤집는 것과 동일
 */

public class Main_G3_28303_자석 {
	static int N, K;
	static long max, minSNJ, minNSJ;
	static int[] arr, reverse;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nk = br.readLine().split(" ");
		N = Integer.parseInt(nk[0]);
		K = Integer.parseInt(nk[1]);
		arr = new int[N+1];
		reverse = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i<=N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			reverse[N-i+1] = arr[i];
		}
		
		max = Long.MIN_VALUE;
		minSNJ = Long.MAX_VALUE;
		minNSJ = Long.MAX_VALUE;
		
		for(int i = 2; i<=N; i++) {
			int j = i - 1;
			minSNJ = Math.min(minSNJ, arr[j] - K * j);
			minNSJ = Math.min(minNSJ, reverse[j] - K * j);
			max = Math.max(max, Math.max(arr[i] - K * i - minSNJ, reverse[i] - K * i - minNSJ));
		}
		
		System.out.println(max);
	}
}
