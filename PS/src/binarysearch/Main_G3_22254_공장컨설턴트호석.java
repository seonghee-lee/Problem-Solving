package binarysearch;

import java.util.*;
import java.io.*;

public class Main_G3_22254_공장컨설턴트호석 {
	static int N, X;
	static int[] present;
	static PriorityQueue<Line> queue = new PriorityQueue<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nx = br.readLine().split(" ");
		N = Integer.parseInt(nx[0]);
		X = Integer.parseInt(nx[1]);
		present = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) present[i] = Integer.parseInt(st.nextToken());
		
		int start = 1;
		int end = N;
		int mid = 0;
		int min = Integer.MAX_VALUE;
		
		while(start <= end) {		//O(logN)
			mid = (start + end) / 2;	//라인 수
			process(mid);			//O(N)
			long time = 0;
			while(!queue.isEmpty()) {	//O(N)
				time = queue.poll().endTime;
			}
			if(time > X) {
				start = mid + 1;
			} else {
				min = Math.min(min, mid);
				end = mid - 1;
			}
		}
		
		System.out.println(min);
	}
	
	private static void process(int line) {
		queue.clear();
		for(int i = 0; i<line; i++) {
			queue.offer(new Line(i, 0));
		}
		for(int i = 0; i<N; i++) {
			Line front = queue.poll();
			front.endTime += present[i];
			queue.offer(front);
		}
	}
}

class Line implements Comparable<Line> {
	int idx;
	long endTime;
	Line(int idx, long endTime){
		this.idx = idx;
		this.endTime = endTime;
	}
	
	public int compareTo(Line o) {
		if(this.endTime == o.endTime) return this.idx - o.idx;
		return Long.compare(this.endTime, o.endTime);
	}
}

