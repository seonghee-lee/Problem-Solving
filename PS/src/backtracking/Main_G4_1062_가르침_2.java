package backtracking;

import java.util.*;
import java.io.*;

public class Main_G4_1062_가르침_2 {
	static int N, K, M, max;
	static boolean[] learned = new boolean[26];
	static List<char[]> words = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nk = br.readLine().split(" ");
		N = Integer.parseInt(nk[0]);
		K = Integer.parseInt(nk[1]);
		
		if(K < 5) {
			System.out.println(0);
			System.exit(0);
		}
		
		//a n t i c 
		learned[0] = true;	learned[2] = true;	learned[8] = true;
		learned[13] = true;	learned[19] = true;
		
		for(int i = 0; i<N; i++) {
			String str = br.readLine();
			char[] word = str.substring(4, str.length() - 4).toCharArray();
			words.add(word);
		}
		
		M = K - 5;	//배울 수 있는 글자 수
		max = Integer.MIN_VALUE;
		learn(0, 0);
		System.out.println(max);
	}
	
	private static void learn(int cnt, int idx) {
		if(cnt == M) {
			int count = 0;
			for(char[] w : words) {
				boolean possible = true;
				for(int i = 0, len=w.length; i<len; i++) {
					if(!learned[w[i] - 'a']) {
						possible = false;
						break;
					}
				}
				if(possible) count++; 
			}
			max = Math.max(max, count);
			return;
		}
		
		for(int i = idx; i<26; i++) {
			if(learned[i]) continue;
			learned[i] = true;
			learn(cnt + 1, i + 1);
			learned[i] = false;
		}
	}
}
