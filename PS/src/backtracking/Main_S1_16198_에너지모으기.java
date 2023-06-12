package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [오답노트]
 * 1. 접근
 * 	  arrayList를 사용해야 한다는 것을 몰랐다.
 * 	  - remove(index), add(index, item) 메서드가 있다는 거 암기하기
 *    => 단순히 순열, 조합이라고 해서 배열에 보관한다는 선입견을 버리자!
 *    	  필요에 따라 자료구조를 사용할 수 있고, 항상 배열에 담지 않아도 된다는 걸 잊지말자.
 *       생각에 갇혀서 구현하지 말기.
 *
 */

public class Main_S1_16198_에너지모으기 {
	static int N, max;
	static List<Integer> wList;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		wList = new ArrayList<Integer>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			wList.add(Integer.parseInt(st.nextToken()));
		}
		
		max = Integer.MIN_VALUE;
		go(0, 0);
		System.out.println(max);
	}
	
	private static void go(int cnt, int sum) {
		
		if(cnt == N-2) {
			max = Math.max(max, sum);
			return;
		}
		
		for(int i = 1, size = wList.size() - 1; i<size; i++) {
			int x = wList.get(i);	//제거할 구슬
			int curE = wList.get(i-1) * wList.get(i+1);
			wList.remove(i);
			go(cnt+1, sum + curE);
			wList.add(i, x);
		}
		
	}

	

	

}
