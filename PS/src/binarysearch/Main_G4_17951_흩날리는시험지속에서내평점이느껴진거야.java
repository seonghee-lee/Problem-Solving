package binarysearch;

import java.util.*;
import java.io.*;

public class Main_G4_17951_흩날리는시험지속에서내평점이느껴진거야 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nk = br.readLine().split(" ");
		int N = Integer.parseInt(nk[0]);
		int K = Integer.parseInt(nk[1]);
		
		int[] arr = new int[N];
		int total = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			total += arr[i];
		}
		
		int start = 0;
		int end = total;
		int mid = -1;
		int ans = 0;
		
		while(start <= end) {
			mid = (start + end) / 2;
			
			int count = 0;
			int sum = 0;
			int min = Integer.MAX_VALUE;
			
			for(int i = 0; i<N; i++) {
				sum += arr[i];
				if(sum >= mid) {
					count++;
					min = Math.min(min, sum);
					sum = 0;
				}
			}
			
			if(count == K) {
				ans = Math.max(min, ans);
				start = mid + 1;
			} else if(count > K) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		System.out.println(ans);
	}
}
