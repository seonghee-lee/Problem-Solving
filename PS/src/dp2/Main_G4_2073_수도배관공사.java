package dp2;

import java.util.*;
import java.io.*;

public class Main_G4_2073_수도배관공사 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		int D = Integer.parseInt(inputs[0]);
		int P = Integer.parseInt(inputs[1]);
		
		List<Pipe> pipeList = new ArrayList<>();
		for(int i = 0; i<P; i++) {
			String[] p = br.readLine().split(" ");
			int length = Integer.parseInt(p[0]);
			int capacity = Integer.parseInt(p[1]);
			
			pipeList.add(new Pipe(length, capacity));
		}
		
		Collections.sort(pipeList);
		
		int[][] dp = new int[P+1][D+1];
		
		for(int i = 1; i<=P; i++) {
			Pipe cur = pipeList.get(i-1);
			int curLength = cur.length;
			int curCapacity = cur.capacity;
			
			for(int j = 1; j<=D; j++) {
				if(j < curLength) dp[i][j] = dp[i-1][j];
				else if(j == curLength) {
					dp[i][j] = Math.max(curCapacity, dp[i-1][j]);
				}
				else {
					int newVal = Math.min(curCapacity, dp[i-1][j - curLength]);
					dp[i][j] = Math.max(newVal, dp[i-1][j]);
				}
			}
		}
		
		int max = Integer.MIN_VALUE;
		for(int i = 1; i<= P; i++) {
			if(dp[i][D] > max) max = dp[i][D];
		}
		
		System.out.println(max);
	}
}

class Pipe implements Comparable<Pipe>{
	int length, capacity;
	Pipe(int length, int capacity){
		this.length = length;
		this.capacity = capacity;
	}
	
	public int compareTo(Pipe o) {
		if(this.length == o.length) return this.capacity - o.capacity;
		return this.length - o.length;
	}
}
