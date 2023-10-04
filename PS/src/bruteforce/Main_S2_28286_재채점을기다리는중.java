package bruteforce;

import java.util.*;
import java.io.*;

public class Main_S2_28286_재채점을기다리는중 {
	static int N, K, max;
	static int[] answer, omr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nk = br.readLine().split(" ");
		N = Integer.parseInt(nk[0]);
		K = Integer.parseInt(nk[1]);
		answer = new int[N];
		omr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			answer[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			omr[i] = Integer.parseInt(st.nextToken());
		}
		
		max = Integer.MIN_VALUE;
		go(0);
		System.out.println(max);
	}
	
	private static void go(int cnt) {
		if(cnt > K) return;
		if(cnt <= K) {
			int correct = 0;
			for(int i = 0; i<N; i++) {
				if(answer[i] == omr[i]) correct++;
			}
			max = Math.max(max, correct);
		}
		
		int temp = 0;
		for(int p = 0; p<N; p++) {
			temp = pull(p);
			go(cnt + 1);
			pullBack(p, temp);
			
			temp = push(p);
			go(cnt + 1);
			pushBack(p, temp);
		}
	}
	
	private static int pull(int p) {
		int temp = omr[p];
		for(int i = p; i<N-1; i++) {
			omr[i] = omr[i+1];
		}
		omr[N-1] = 0;
		return temp;
	}
	
	private static void pullBack(int p, int temp) {
		for(int i = N-1; i>p; i--) {
			omr[i] = omr[i-1];
		}
		omr[p] = temp;
	}
	
	private static int push(int p) {
		int temp = omr[N-1];
		for(int i = N-1; i>p; i--) {
			omr[i] = omr[i-1];
		}
		omr[p] = 0;
		return temp;
	}
	
	private static void pushBack(int p, int temp) {
		for(int i = p; i<N-1; i++) {
			omr[i] = omr[i+1];
		}
		omr[N-1] = temp;
	}

}
