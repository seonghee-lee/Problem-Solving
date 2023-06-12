package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 시작 문자는 전체 단어를 정렬했을 때 가장 첫번째 글자 : 방문처리, 출력
 * 해당 글자를 기준으로 왼쪽 / 오른쪽 으로 나뉜다.
 * 오른쪽 먼저 진행하고, 모두 끝난 후 왼쪽에 대해 진행한다.
 * 
 * 1. 정렬한다.
 * 2. 가장 앞글자를 선택한다. : 방문처리, 출력하기
 *    - 글자를 방문처리 한다.
 *    - 전체 글자 중 방문처리 된 글자라면 출력한다. : 이 방식으로 해야 원하는 단어를 출력할 수 있음.
 * 3. 해당 글자에 대해 오른쪽, 왼쪽으로 나눈다.
 * 4. 오른쪽 먼저 진행한다... => 반복

 *
 * [오답노트]
 * 구현 문제의 경우에, 주어진 케이스만 생각하지 말고 특히 "중복"된 경우에 대해서 생각해봐야 한다..
 * 방문처리 여부 조건문에 포함될 수 있음을 염두해두자.
 * 재귀문제 어려워보여도 손으로 써보고 부딪혀보면 풀 수 있다.
 *
 */

public class Main_G5_16719_ZOAC {
	static int len;
	static char[] word;
	static boolean[] used;
	static StringBuilder sb = new StringBuilder(100);
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		word = br.readLine().toCharArray();
		len = word.length;
		used = new boolean[len];
		
		go(0, len-1);
		System.out.print(sb);
	}
	
	//cnt: 현재까지 출력된 글자수(재귀횟수), startIdx: 정렬을 시작할 , endIdx: 정렬을 끝낼 인덱스
	private static void go(int startIdx, int endIdx) {
		
		if(startIdx >= endIdx) {
			//끝에 한글자 남았을 경우를 위한 출력
			if(endIdx >= 0 && !used[endIdx]) {
				used[endIdx] = true;
				
				for(int i = 0; i<len; i++) {
					if(used[i]) sb.append(word[i]);
				}sb.append("\n");
			}
			
			return;
		}
		
		//startIdx ~ endIdx 까지의 문자들을 정렬한다.
		int curLen = endIdx - startIdx + 1;
		char[] temp = new char[curLen];
		for(int i = startIdx, j = 0; i <= endIdx; i++, j++) {
			temp[j] = word[i];
		}

		Arrays.sort(temp);
		
		//가장 앞글자를 선택한다. //가장 앞글자의 인덱스가 startIdx가 된다.
		char s = temp[0];
		int sIdx = -1;
		for(int i = 0; i<len; i++) {
			if(word[i] == s && !used[i] && i >= startIdx) {		//조건 중요!!!
				sIdx = i;
				break;
			}
		}
		
		//해당 글자 방문처리 + 출력
		if(!used[sIdx]) {
			used[sIdx] = true;

			for(int i = 0; i<len; i++) {
				if(used[i]) sb.append(word[i]);
			}sb.append("\n");

			//오른쪽으로 진행
			go(sIdx + 1, endIdx);
			//왼쪽으로 진행
			go(startIdx, sIdx-1);
		}
	}
}
