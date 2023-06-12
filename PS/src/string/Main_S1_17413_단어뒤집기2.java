package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_S1_17413_단어뒤집기2 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		char[] input = str.toCharArray();
		
		int tagStart = 0;
		int tagEnd = 0;
		boolean inTag = false;
		Stack<Character> stack = new Stack<>();
		StringBuilder sb = new StringBuilder(str.length());
		
		for(int i = 0, len=input.length; i<len; i++) {
			if(input[i] == '<') {
				while(!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				tagStart = i;
				inTag = true;
			}else if(input[i] == '>') {
				tagEnd = i;
				inTag = false;
				sb.append(str.substring(tagStart, tagEnd+1));
			}else if(input[i] == ' ' && !inTag) {
				while(!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				sb.append(" ");
			}else {
				if(!inTag) {
					stack.push(input[i]);
				}
			}
		}
		
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		System.out.println(sb);
	}
}
