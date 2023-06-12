package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * [오답노트]
 * 접근법의 참신함. 쫄지말자.
 * 숫자인데 뒤에 점점 붙여나가야 하는 경우, 기존거에서 10을 곱하고 일의자리수에 더해주기만 하면된다.
 * 재귀함수 파라미터에 값이 여러개 들어가는 걸 겁내지 말자.
 * subset은 아무것도 선택하지 않는것부터 시작한다.
 */

public class Main_G5_1174_줄어드는수 {
	static int N, impossibleAns;
	static int[] numbers;
	static List<Long> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		impossibleAns = -1;
	
		numbers = new int[10];
		
		for(int i = 9; i>= 0; i--) {
			numbers[9-i] = i;
		}
		
		go(0, 0);
		Collections.sort(list);
		
		if(N >= list.size()) System.out.println(impossibleAns);
		else System.out.println(list.get(N));
	}

	private static void go(int index, long sum) {
		if(index == 10) {
			list.add(sum);
			return;
		}
		
		//subset
		go(index+1, (sum * 10)+numbers[index]);	//선택한 경우
		go(index+1, sum); 	//선택하지 않은 경우
		
	}

}
