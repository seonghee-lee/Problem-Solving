package binarysearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
 * 가장 인접한 두 공유기 사이의 "거리"를 "최대"로 해야한다.
 * "거리"를 최소 ~ 최대 사이에서 바꾸면서 만족하는 최대를 구해보자.
 * */
public class Main_G4_2110_공유기설치 {
	static int N, C;
	static int[] home;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		C = Integer.parseInt(inputs[1]);
		
		home = new int[N];
		for(int i = 0; i<N; i++) {
			home[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(home);
		
		//start, end, mid는 공유기 사이의 "거리"이다.
		long start = 1;							//최소 거리가 가질 수 있는 최솟값
		long end = home[N-1] - home[0];		//최소 거리가 가질 수 있는 최댓값
		long answer = 0;
		
		while(start <= end) {
			
			long mid = (start + end) / 2;	//mid 거리로 공유기를 설치해본다.
			
			int wifiCnt = install(mid);
			
			if(wifiCnt >= C) {		//정답이거나, 거리를 늘려야한다.
				answer = mid;
				start = mid + 1;
			} else {				//거리를 줄여야한다.
				end = mid - 1;
			}
		}
		
		System.out.println(answer);
		
	}
	
	private static int install(long distance) {
		int count = 0;
		
		//첫번째 집은 항상 공유기를 설치한다.
		count++;
		int installed = home[0];
		
		for(int i = 1, len = home.length; i<len; i++) {
			if(count >= C) break;
			
			if(home[i] >= installed + distance) {
				count++;
				installed = home[i];
			}
		}
		
		return count;
	}

}
