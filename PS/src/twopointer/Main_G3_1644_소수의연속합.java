package twopointer;

import java.util.*;

public class Main_G3_1644_소수의연속합 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		boolean[] isPrime = new boolean[N+1];
		Arrays.fill(isPrime, true);
		isPrime[1] = false;
		
		//에라토스테네스의 체  + long형 for문
		for(long i = 2; i<=N; i++) {
			if(isPrime[(int)i]) {
				for(long j = i * i ; j<=N; j += i) {
					isPrime[(int)j] = false;
				}
			}
		}
		
		int size = 0;
		
		for(int i = 2; i<=N; i++) {
			if(isPrime[i])  size++;
		}
		
		int[] numbers = new int[size+1];		//소수 배열
		int idx = 1;
		for(int i = 2; i<=N; i++) {
			if(isPrime[i]) numbers[idx++] = i;
		}
		
		//예외 조건 주의!!!
		if(N == 1) {
			System.out.println(0);
			return;
		}
		
		int start = 1;
		int end = 1;
		
		int count = 0;
		int sum = numbers[start];
		
		while(start <= end) {
			
			if(sum <= N) {
				if(sum == N) count++;
				end++;
				if(end > size) break;
				sum += numbers[end];
			} else {
				sum -= numbers[start];
				start++;
			}
		}
		
		System.out.print(count);
	}

}
