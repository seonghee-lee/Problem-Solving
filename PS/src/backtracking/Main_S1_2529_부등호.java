package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [오답노트]
 * - 스트링 빌더 잊지 말기!
 * - 구현 틀에 사로잡히지 말자. 백트래킹은 밖에서만 할 수 있는 게 아니다.
 *   어느 위치에서 조건이 들어가야 하는 지 문제 읽으면서 차근히 생각해보기. 설계 잘하기.
 *
 */
public class Main_S1_2529_부등호 {
	static int K, count;
	static boolean[] lessThan, selected;
	static int[] numbers;
	static List<String> result = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		lessThan = new boolean[K];
		selected = new boolean[10];
		numbers = new int[K+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<K; i++) {
			String input = st.nextToken();
			if(input.equals("<")) {
				lessThan[i] = true;
			}else {
				lessThan[i] = false;
			}
		}
		
		perm(0, -1);
		
		System.out.println(result.get(count-1));
		System.out.println(result.get(0));
	}

	//idx: 현재까지 선택완료 된 숫자 개수; 확인되어야 할 부등호 인덱스
	private static void perm(int cnt, int idx) {
		if(cnt == K+1) {
			StringBuilder sb = new StringBuilder(20);
			for(int i = 0; i<K+1; i++) {
				sb.append(numbers[i]);
			}
			result.add(sb.toString());
			count++;
			return;
		}
		
		for(int i = 0; i<10; i++) {
			if(selected[i]) continue;

			//부등호에 따라서 선택할 지 말지 결정
			if(idx >= 0) {
				if(lessThan[idx] && numbers[idx] > i) continue;
				else if(!lessThan[idx] && numbers[idx] < i) continue;
			}
			
			numbers[cnt] = i;
			selected[i] = true;

			perm(cnt+1, idx+1);
			
			selected[i] = false;
		}
	}

}
