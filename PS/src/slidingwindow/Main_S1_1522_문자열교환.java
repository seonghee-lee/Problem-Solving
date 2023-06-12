package slidingwindow;

import java.util.*;
import java.io.*;

public class Main_S1_1522_문자열교환 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] str = br.readLine().toCharArray();
		
		int aCount = 0;
		int bCount = 0;
		int strLen = str.length;
		
		for(int i = 0, len = str.length; i<len; i++) {
			if(str[i] == 'a') aCount++;
		}
		
		int min = Integer.MAX_VALUE;
		
		for(int start = 0; start < strLen; start++) {
			bCount = 0;
			for(int end = start; end < start + aCount; end++) {
				int endIdx = end % strLen;
				if(str[endIdx] == 'b') bCount++;
			}
			min = Math.min(min, bCount);
		}
		System.out.println(min);
	}

}
