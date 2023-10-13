package implementation;

import java.util.*;
import java.io.*;

public class Main_G5_22251_빌런호석 {
	static int N, K, P, X;
	static int[][] numbers = { { 1, 1, 1, 0, 1, 1, 1 }, { 0, 0, 1, 0, 0, 1, 0 }, { 1, 0, 1, 1, 1, 0, 1 },
			{ 1, 0, 1, 1, 0, 1, 1 }, { 0, 1, 1, 1, 0, 1, 0 }, { 1, 1, 0, 1, 0, 1, 1 }, { 1, 1, 0, 1, 1, 1, 1 },
			{ 1, 0, 1, 0, 0, 1, 0 }, { 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 0, 1, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nkpx = br.readLine().split(" ");
		N = Integer.parseInt(nkpx[0]); // 1 ~ N 층까지 표현 가능
		K = Integer.parseInt(nkpx[1]); // 디스플레이에 K자리의 수가 보인다.
		P = Integer.parseInt(nkpx[2]); // 최대 반전 가능한 LED 수
		X = Integer.parseInt(nkpx[3]); // 엘레베이터 실제 층
		
		int[] current = new int[K];
		for(int k = K-1; k>=0; k--) {
			current[k] = X % 10;
			X /= 10;
		}
		
		int result = 0;
		
		//1~N을 K자리에 맞추어서 만든다.
		int[] made = new int[K];
		for(int i = 1; i<=N; i++) {
			int num = i;
			for(int k = K-1; k>=0; k--) {
				made[k] = num % 10;
				num /= 10;
			}
			//반전 LED의 개수를 센다.
			int count = 0;
			for(int p = 0; p<K; p++) {
				for(int q = 0; q<7; q++) {
					if(numbers[current[p]][q] != numbers[made[p]][q]) count++;
				}
			}
			if(count <= P && count > 0) result++;
		}
		
		System.out.println(result);
	}
}
