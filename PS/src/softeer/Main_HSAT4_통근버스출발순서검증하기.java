package softeer;

import java.util.*;
import java.io.*;

public class Main_HSAT4_통근버스출발순서검증하기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] smaller = new int[N][N];	//smaller[i][j] : arr[i]보다 작은 수가 j인덱스 까지 몇 개 존재하는지
		for(int i = 0; i<N; i++) {
			for(int j = i+1; j<N; j++) {
				if(arr[i] > arr[j]) smaller[i][j] = smaller[i][j-1] + 1;
				else smaller[i][j] = smaller[i][j-1];
			}
		}

		long count = 0;
		
		for(int i = 0; i<N-2; i++) {
			int num1 = arr[i];
			int second = i + 1;
			
			while(second < N-1) {
				if(num1 > arr[second]) second++;
				else {
					int thirdCnt = smaller[i][N-1] - smaller[i][second];
					count += thirdCnt;
					second++;
				}
			}
		}
		
		System.out.print(count);
	}

}
