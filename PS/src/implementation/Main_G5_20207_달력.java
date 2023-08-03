package implementation;

import java.util.*;
import java.io.*;

public class Main_G5_20207_달력 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] count = new int[366];
		List<Schedule> list = new ArrayList<>();
		
		for(int i = 1; i<=N; i++) {
			String[] inputs = br.readLine().split(" ");
			int start = Integer.parseInt(inputs[0]);
			int end = Integer.parseInt(inputs[1]);
			
			list.add(new Schedule(start, end));
		}
		
		Collections.sort(list);
		
		for(Schedule cur : list) {
			int start = cur.start;
			int end = cur.end;
			
			for(int i = start; i<=end; i++) {
				count[i]++;
			}
		}
		
		int width = 0;
		int height = 0;
		int sum = 0;
		
		for(int i = 1; i<=365; i++) {
			if(count[i] == 0) {
				sum += width * height;
				width = 0;
				height = 0;
			} else {
				width++;
				height = Math.max(height, count[i]);
			}
		}
		
		sum += width * height;
		
		System.out.print(sum);
	}
}

class Schedule implements Comparable<Schedule>{
	int start, end;
	Schedule(int start, int end){
		this.start = start;
		this.end = end;
	}
	
	public int compareTo(Schedule o) {
		if(this.start < o.start) {
			return -1;
		} else if(this.start == o.start) {
			return o.end - this.end;
		} else {
			return 1;
		}
	}
}
