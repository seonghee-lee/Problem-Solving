package simulation;

import java.util.*;
import java.io.*;

public class Main_G2_17825_주사위윷놀이 {
	static int[] dice = new int[10];
	static int[] horse = new int[10];	//이동하는 말의 순서
	static int max = Integer.MIN_VALUE;
	static boolean[] end = new boolean[5];	//end[i]: i번 말은 도착함
	static int[] position = new int[5];	//position[i]: i번 말의 현재 위치(번호)
	static int[] course = new int[5];		//course[i]: i번 말의 코스
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		for(int i = 0; i<10; i++) {
			dice[i] = Integer.parseInt(inputs[i]);
		}
		
		setOrder(0);
		System.out.println(max);
	}
	
	private static void setOrder(int cnt) {
		if(cnt == 10) {
			game();
			return;
		}
		
		for(int i = 1; i<=4; i++) {
			horse[cnt] = i;
			setOrder(cnt + 1);
		}
	}
	
	private static void game() {
		int score = 0;
		Arrays.fill(end, false);
		Arrays.fill(position, 0);
		Arrays.fill(course, 1);
		
		int cur = 0;
		int next = 0;
		
		for(int i = 0; i<10; i++) {
			cur = horse[i];	//현재 이동하는 말
			
			if(end[cur]) return;	//이 말은 이동할 수 없으므로, 잘못된 경우라서 return
			
			//말의 다음 위치를 구한다.
			if(course[cur] == 1) {
				if(position[cur] == 10) {
					next = course2(position[cur], dice[i]);
					if(exist(next, 2)) return; 
					course[cur] = 2;
				} else if(position[cur] == 20) {
					next = course3(position[cur], dice[i]);
					if(exist(next, 3)) return;
					course[cur] = 3;
				} else if(position[cur] == 30) {
					next = course4(position[cur], dice[i], 1);
					if(exist(next, 4)) return;
					course[cur] = 4;
				} else {
					next = position[cur] + (dice[i] * 2);
					if(exist(next, 1)) return;
				}
			} else if(course[cur] == 2) {
				next = course2(position[cur], dice[i]);
				if(exist(next, 2)) return; 
			} else if(course[cur] == 3) {
				next = course3(position[cur], dice[i]);
				if(exist(next, 3)) return;
			} else {
				next = course4(position[cur], dice[i], 4);
				if(exist(next, 4)) return;
			}
			
			position[cur] = next;
			
			if(position[cur] > 40) {
				end[cur] = true;
			} else {
				score += next;
			}
		}
		
		max = Math.max(max, score);
		return;
	}
	
	private static boolean exist(int n, int c) {
		if(n > 40) return false;
		if(n == 30) {
			if(c != 1) {	//2, 3, 4코스에 있는 30
				for(int i = 1; i<=4; i++) {
					if(position[i] == n && course[i] != 1) return true;
				}
			} else {	//코스 1에 있는 30
				for(int i = 1; i<=4; i++) {
					if(position[i] == n && course[i] == 1) return true;
				}
			}
		} else if(n == 25 ||  n == 35 || n == 40) {
			for(int i = 1; i<=4; i++) {
				if(position[i] == n) return true;
			}
		} else {
			for(int i = 1; i<=4; i++) {
				if(position[i] == n && course[i] == c) return true;
			}
		}
		return false;
	}
	
	private static int course2(int curNum, int diceNum) {
		while(diceNum > 0) {
			if(curNum < 19) curNum += 3;
			else if(curNum == 19) curNum += 6;
			else curNum += 5;
			diceNum--;
		}
		return curNum;
	}
	
	private static int course3(int curNum, int diceNum) {
		while(diceNum > 0) {
			if(curNum < 24) curNum += 2;
			else if(curNum == 24) curNum += 1;
			else curNum += 5;
			diceNum--;
		}
		return curNum;
	}
	
	private static int course4(int curNum, int diceNum, int call) {
		while(diceNum > 0) {
			if(curNum == 30) {
				if(call == 1) {
					curNum -= 2;
				}
				else curNum += 5;
			}
			else if(curNum > 25 && curNum < 30) curNum -= 1;
			else curNum += 5;
			diceNum--;
		}
		return curNum;
	}
}
