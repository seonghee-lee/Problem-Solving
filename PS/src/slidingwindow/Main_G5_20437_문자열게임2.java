package slidingwindow;

import java.io.*;
import java.util.*;

public class Main_G5_20437_문자열게임2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder(30000);
		for(int t = 1; t<=T; t++) {
			char[] str = br.readLine().toCharArray();
			int K = Integer.parseInt(br.readLine());
			
			List<Integer>[] indexList = new ArrayList[26];	//출현 알파벳의 인덱스들을 저장할 리스트
			for(int i = 0; i<26; i++) {
				indexList[i] = new ArrayList<Integer>();
			}
			
			for(int i = 0, len = str.length; i<len; i++) {
				char cur = str[i];
				indexList[cur - 'a'].add(i);
			}
			
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			
			boolean flag = false;
			
			if(K == 1) {
				min = 1;
				max = 1;
				flag = true;
			} else {
				for(int i = 0; i<26; i++) {
				
					if(indexList[i].size() < K) continue;
					
					flag = true;
					
					//슬라이딩 윈도우 
					int start = 0;
					int end = 0;
					
					while(end < indexList[i].size()) {
					
						if(end - start + 1 == K) {	//요소의 개수가 K개이면,
							int val1 = (int) indexList[i].get(end);
							int val2 = (int) indexList[i].get(start);
							min = Math.min(min, val1- val2 + 1);
							max = Math.max(max, val1 - val2 + 1);
						}
						
						end++;
						
						if(end - start + 1 > K) start++;
					}
				}
			}
			
			if(!flag) sb.append(-1).append("\n");
			else sb.append(min).append(" ").append(max).append("\n");
		}
		System.out.print(sb);
	}
}
