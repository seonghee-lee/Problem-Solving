package softeer;

import java.util.*;
import java.io.*;

public class Main_HSAT3_플레이페어 {
	static char[] message, key;
	static char[][] map = new char[5][5];
	static Queue<char[]> queue = new ArrayDeque<>();
	static StringBuilder ans = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		message = br.readLine().toCharArray();
		key = br.readLine().toCharArray();
		
		//1. 표를 만든다.
		makeTable();
		
		//2. 메시지를 두 글자씩 나눈다.
		splitMessage();
		
		//3. 표를 보면서 메시지를 암호화한다.
		cipher();
		
		System.out.print(ans);
	}
	
	private static void cipher() {
		char letter1, letter2;
		int r1, c1, r2, c2;
		
		while(!queue.isEmpty()) {
			char[] cur = queue.poll();
			letter1 = cur[0];
			letter2 = cur[1];
			
			r1 = findRow(letter1);
			c1 = findCol(letter1);
			
			r2 = findRow(letter2);
			c2 = findCol(letter2);
			
			if(r1 == r2) {	//1. 두 글자가 같은 행에 존재하면
				int nc1 = c1 + 1;
				int nc2 = c2 + 1;
				
				if(nc1 >= 5) nc1 %= 5;
				if(nc2 >= 5) nc2 %= 5;
				
				ans.append(map[r1][nc1]).append(map[r2][nc2]);
			} else if(c1 == c2) {	//2. 두 글자가 표에서 같은 열에 존재하면
				int nr1 = r1 + 1;
				int nr2 = r2 + 1;
				
				if(nr1 >= 5) nr1 %= 5;
				if(nr2 >= 5) nr2 %= 5;
				
				ans.append(map[nr1][c1]).append(map[nr2][c2]);
			} else {	//3. 두 글자가 표에서 서로 다른 행, 열에 존재하면
				ans.append(map[r1][c2]).append(map[r2][c1]);
			}
		}
	}
	
	private static int findRow(char letter) {
		int row = -1;
		
		for(int i = 0; i<5; i++) {
			for(int j = 0; j<5; j++) {
				if(map[i][j] == letter) return i;
			}
		}
		
		return row;
	}
	
	private static int findCol(char letter) {
		int col = -1;
		
		for(int i = 0; i<5; i++) {
			for(int j = 0; j<5; j++) {
				if(map[i][j] == letter) return j;
			}
		}
		
		return col;
	}
	
	private static void splitMessage() {
		
		boolean flag = false;
		int index = 0;
		char[] m = new char[2];
		
		while(true) {
			
			if(index == message.length) break;
			
			if(!flag) {
				m = new char[2];
				m[0] = message[index++];
				flag = true;
			} else {
				char cur = message[index];
				if(m[0] == cur) {
					if(m[0] == 'X') m[1] = 'Q';
					else m[1] = 'X';
				} else {
					m[1] = cur;
					index++;
				}
				queue.offer(m);
				flag = false;
			}
		}
		
		if(flag) {
			m[1] = 'X';
			queue.offer(m);
		}
	}
	
	private static void makeTable() {
		boolean[] alpha = new boolean[26];
		int r = 0; int c = 0;
		
		for(int i = 0, len = key.length; i<len; i++) {
			if(!alpha[key[i] - 'A']) {
				map[r][c] = key[i];
				alpha[key[i] - 'A'] = true;
				
				c++;
				if(c >= 5) {
					r++;
					c = 0;
				}
			}
		}
		
		for(int i = 0; i<26; i++) {
			if(i == 9) continue;
			if(!alpha[i]) {
				map[r][c] = (char)('A' + i);
				
				c++;
				if(c >= 5) {
					r++;
					c = 0;
				}
			}
		}
	}

}
