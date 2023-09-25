package backtracking;

import java.util.*;
import java.io.*;

public class Main_G4_15659_연산자끼워넣기3 {
	static int N, min, max;
	static int[] arr, opr;
	static Deque<Integer> numList = new ArrayDeque<>();
	static List<Integer> oprList = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		opr = new int[4];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i<4; i++) {
			opr[i] = Integer.parseInt(st.nextToken());
		}
		
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		
		int[] selected = new int[N-1];
		go(0, selected);
		
		System.out.printf("%d\n%d", max, min);
	}
	
	private static void go(int count, int[] selected) {
		
		if(count == N-1) {
			
			numList.clear();
			oprList.clear();
			
			numList.add(arr[0]);
			for(int i = 1; i<N; i++) {
				if(selected[i-1] <=1) {		// + 또는 -
					oprList.add(selected[i-1]);
					numList.add(arr[i]);
				} else {	// * 또는 /
					int top = numList.pollLast();
					int num = 0;
					if(selected[i-1] == 2) num = top * arr[i];
					else num = top / arr[i];
					numList.add(num);
				}
			}
			
			int cur = numList.poll();
			for(int i = 1, len = numList.size(); i<=len; i++) {
				int op = oprList.get(i-1);
				int nextNum = numList.poll();
				
				if(op == 0) cur += nextNum;
				else if(op == 1) cur -= nextNum;
				else if(op == 2) cur *= nextNum;
				else cur /= nextNum;
			}
			
			max = Math.max(max, cur);
			min = Math.min(min, cur);
			
			return;
		}
		
		for(int i = 0; i<4; i++) {
			if(opr[i] <= 0) continue;
			
			selected[count] = i;
			opr[i]--;
			go(count+1, selected);
			opr[i]++;
		}
	}
}
