package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 이런 종료 해결하는 데 있어서 내가 재귀가 익숙하지 않은 게 보인다.
 * 잘 짚고 넘어가자. ..... 인간은 같은 실수를 반복한다는 게 정말 딱 맞다. 저번에도 이 부분에서 못풀었던 게 딱 기억이 나네..
 * 재귀 풀 때 어렵고 헷갈리고 생각하기 복잡한 거 알지만
 * 그래도 머릿속으로 설계를 확실히 하자. 나도 할 수 있다. 그래야 시간 낭비 더 하지 않고 정확하게 풀 수 있다.
 */

public class Main_S1_16987_계란으로계란치기 {
	static int N, max;
	static class Egg{
		int s, w;
		boolean broken;
		public Egg(int s, int w, boolean broken) {
			this.s = s;
			this.w = w;
			this.broken = broken;
		}
	}
	static List<Egg> eggs = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			eggs.add(new Egg(s, w, false));
		}
		
		max = 0;
		go(0, 0);
		System.out.println(max);
	}
	
	private static void go(int cur, int bCount) {
		
		//N만큼 돌았거나(내가 손에 쥔 게 맨 오른쪽 계란), 모든 계란이 깨진 경우 재귀 종료
		if(cur == N || bCount == N) {
			max = Math.max(max, bCount);
			return;
		}
		
		for(int i = 0; i<N; i++) {
			if(cur == i) {
				continue;
			}
			
			Egg curEgg = eggs.get(cur);		//손에 들고 있는 계란
			Egg heatEgg = eggs.get(i);		//칠 계란
			
			if(curEgg.broken || heatEgg.broken) {
				go(cur+1, bCount);
				continue;
			}

			//서로의 내구도에 손상 입히기
			curEgg.s -= heatEgg.w;
			heatEgg.s -= curEgg.w;
			
			int curBrokenCount = 0;
			if(curEgg.s <= 0) {
				curEgg.broken = true;
				curBrokenCount++;
			}
			if(heatEgg.s <= 0) {
				heatEgg.broken = true;
				curBrokenCount++;
			}
			
			go(cur+1, bCount + curBrokenCount);
			
			//되돌리기
			curEgg.s += heatEgg.w;
			heatEgg.s += curEgg.w;
			curEgg.broken = false;
			heatEgg.broken = false;
		}
	}

}
