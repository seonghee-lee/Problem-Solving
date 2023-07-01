package simulation;

import java.util.*;
import java.io.*;

public class Main_G4_17140_이차원배열과연산 {
	static int r, c, k;
	static int row, col;
	static int[][] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] rck = br.readLine().split(" ");
		r = Integer.parseInt(rck[0]);
		c = Integer.parseInt(rck[1]);
		k = Integer.parseInt(rck[2]);
		
		arr = new int[101][101];
		for(int i = 1; i<=3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j<=3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		row = 3;
		col = 3;
		int time = 0;
		
		while(time < 101) {
			if(arr[r][c] == k) break;
			if(row >= col) {
				for(int i = 1; i<=row; i++) {
					calcR(i);
				}
			} else {
				for(int i = 1; i<=col; i++) {
					calcC(i);
				}
			}
			time++;
		}
		
		if(time > 100) {
			System.out.print(-1);
		}
		else System.out.print(time);
	}
	
	private static void calcR(int row) {
		int[] count = new int[101];
		for(int i = 1; i<=col; i++) {
			count[arr[row][i]]++;
		}
		List<Number> list = new ArrayList<>();
		for(int i = 1; i<=100; i++) {
			if(count[i] > 0) {
				list.add(new Number(i, count[i]));
			}
		}
		
		Collections.sort(list);
		
		for(int i = 0; i<=col; i++) {
			arr[row][i] = 0;
		}
		
		int idx = 1;
		for(int i = 0, size = list.size(); i<size; i++) {
			if(idx >= 101) break;
			arr[row][idx++] = list.get(i).number;
			arr[row][idx++] = list.get(i).count;
		}
		
		col = Math.max(col, list.size() * 2);
	}
	
	private static void calcC(int col) {
		int[] count = new int[101];
		for(int i = 1; i<=row; i++) {
			count[arr[i][col]]++;
		}
		
		List<Number> list = new ArrayList<>();
		for(int i = 1; i<=100; i++) {
			if(count[i] > 0) {
				list.add(new Number(i, count[i]));
			}
		}
		
		Collections.sort(list);
		
		for(int i = 0; i<=row; i++) {
			arr[i][col] = 0;
		}
		
		int idx = 1;
		for(int i = 0, size = list.size(); i<size; i++) {
			if(idx >= 101) break;
			arr[idx++][col] = list.get(i).number;
			arr[idx++][col] = list.get(i).count;
		}
		
		row = Math.max(row, list.size() * 2);
	}
}

class Number implements Comparable<Number>{
	int number;	//수
	int count;	//등장 횟수
	Number(int number, int count){
		this.number = number;
		this.count = count;
	}
	
	public int compareTo(Number o) {
		if(this.count > o.count) return 1;
		else if(this.count == o.count) {
			return this.number - o.number;
		} else return -1;
	}
}
