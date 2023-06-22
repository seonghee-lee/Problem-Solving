package twopointer;

import java.util.*;
import java.io.*;
/*
 * 맞추긴 했는데 너무 어렵게 생각해서 문제 풂.
 * 내 풀이: 몸통을 먼저 조합으로 구하고, 머리를 투포인터로 구해서 조건확인
 * 찾아본 풀이: 이중 포문으로 눈뭉치 2개를 선택해서 일단 눈사람 1개를 만들고, 투포인터로 나머지 눈사람 1개를 만들어서 둘을 비교한다. => 훨씬 구현도 간단하고 빠를듯.
 * */
public class Main_G3_20366_같이눈사람만들래 {
	static boolean[] selected;
	static int[] arr, body;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		selected = new boolean[N];
		body = new int[2];
		
		Arrays.sort(arr);
		
		go(N, 0, 0);
		
		System.out.print(min);
	}
	
	private static void go(int N, int count, int start) {
		if(count == 2) {
			int left = 0;
			int right = 1;
			int bodyDiff = arr[body[1]] - arr[body[0]];
			while(left <= right && right < N) {
				
				if(selected[right]) {
					right++;
					continue;
				}
				
				if(selected[left]) {
					left++;
					continue;
				}
				
				if(left == right) {
					right++;
					continue;
				}
				
				if(arr[left] > arr[body[0]] || arr[right] > arr[body[1]]) break;
				
				int cur =  (arr[right] + arr[body[1]]) - (arr[left] + arr[body[0]]);
				if(arr[body[0]] >= arr[right]) {
					cur = Math.min(cur, Math.abs((arr[right] + arr[body[0]]) - (arr[left] + arr[body[1]])));
				}
				min = Math.min(min, cur);
				
				int headDiff = arr[right] - arr[left];
				if(headDiff > bodyDiff) {
					left++;
				} else {
					right++;
				}
			}
			return;
		}
		
		for(int i = start; i<N; i++) {
			body[count] = i;
			selected[i] = true;
			go(N, count+1, i + 1);
			selected[i] = false;
		}
	}
}
