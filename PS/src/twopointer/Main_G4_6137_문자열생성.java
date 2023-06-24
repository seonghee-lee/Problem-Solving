package twopointer;

import java.util.*;
import java.io.*;

public class Main_G4_6137_문자열생성 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		char[] str = new char[N];
		for(int i = 0; i<N; i++) {
			str[i] = br.readLine().charAt(0);
		}
		
		char[] ans = new char[N];
		int left = 0;
		int right = N - 1;
		int idx = 0;
		
		while(left <= right) {
			if(str[left] < str[right]) {
				ans[idx++] = str[left];
				left++;
			} else if(str[left] > str[right]) {
				ans[idx++] = str[right];
				right--;
			} else {
				int nleft = left;
				int nright = right;
				
				while(true) {
					nleft++;
					nright--;
					
					if(nleft > nright) {	//아무거나 상관없음
						ans[idx++] = str[left];
						left++;
						break;
					}

					if(str[nleft] < str[nright]) {
						ans[idx++] = str[left];
						left++;
						break;
					} else {
						ans[idx++] = str[right];
						right--;
						break;
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder(2500);
		for(int i = 0; i<N; i++) {
			if(i != 0 && i % 80 == 0) sb.append("\n");
			sb.append(ans[i]);
		}
		System.out.print(sb);

	}
}
