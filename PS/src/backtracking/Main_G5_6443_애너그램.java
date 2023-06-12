package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 애너그램의 수가 십만 개 이하만 주어진다. => 중복 문자들이 있어서 시간초과가 나지 않는다.
 * => 중복 문자들에 대해 연산이 들어가지 않으면 시간초과 없이 구현 가능하다.
 */
public class Main_G5_6443_애너그램 {
	static int wordLen;
	static char[] word;
	static int[] alpha;
	static boolean[] visited;
	static Queue<String> queue = new PriorityQueue<>();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder result = new StringBuilder(100);
		for(int t = 1; t<=T; t++) {
			word = br.readLine().toCharArray();
			wordLen = word.length;
			Arrays.sort(word);
			
			visited = new boolean[wordLen];
			alpha = new int[26];
			
			//단어 스펠링의 알파벳 숫자를 세준다.
			for(int i = 0; i<wordLen; i++) {
				alpha[word[i] - 'a']++;
			}
			
			go(0, "");
			while(!queue.isEmpty()) {
				result.append(queue.poll()).append("\n");
			}
		}
		System.out.print(result);
	}
	
	private static void go(int cnt, String made) {
		
		if(cnt == wordLen) {
			queue.add(made);
			return;
		}
		
		//알파벳을 모두 사용할 때까지 반복한다.
		for(int i = 0; i<26; i++) {
			if(alpha[i] > 0) {		//알파벳이 남아있는 경우
				alpha[i]--;	//알파벳을 사용한다.
				go(cnt+1, made + ((char)('a'+i))+"");
				alpha[i]++;
			}
		}
	}

}
