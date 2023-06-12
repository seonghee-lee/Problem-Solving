package backtracking;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main_G5_2023_신기한소수 {
	static int N;
	static Queue<Integer> pqueue = new PriorityQueue<>();
	public static void main(String[] args) throws Exception{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		go(0, 0);
		StringBuilder sb = new StringBuilder(100);
		while(!pqueue.isEmpty()) {
			sb.append(pqueue.poll()).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void go(int cnt, int number) {
		if(cnt == N) {
			pqueue.offer(number);
			return;
		}
		
		for(int i = 0; i < 10; i++) {
			int cur = 10 * number + i;
			if(isPrime(cur)) {
				go(cnt + 1, cur);
			}
		}
	}
	
	private static boolean isPrime(int n) {
		if(n == 1 || n == 0) return false;
		for(int i = 2; i<=Math.sqrt(n); i++) {
			if(n % i == 0) return false;
		}
		return true;
	}

}
