package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 중복처리!!!!!
 *
 */

public class Main_S1_1342_행운의문자열 {
	static int count, len;
	static char[] word, made;
	static int[] alpha;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		word = br.readLine().toCharArray();
		len = word.length;
		made = new char[len];
		alpha = new int[26];
		
		for(int i = 0; i<len; i++) {
			alpha[word[i] - 'a']++;
		}
		
		go(0);
		System.out.println(count);
	}
	
	private static void go(int cnt) {
		
		if(cnt == len) {
			boolean flag = true;
			for(int i = 1; i<len; i++) {
				char prev = made[i-1];
				if(prev == made[i]) {
					flag = false;
					break;
				}
			}
			if(flag) count++;
			return;
		}
		
		for(int i = 0; i<26; i++) {
			if(alpha[i] > 0) {
				alpha[i]--;
				made[cnt] = (char)('a'+i);
				go(cnt+1);
				alpha[i]++;
			}
		}
	}
}
