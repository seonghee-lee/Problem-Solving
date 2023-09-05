package prefixsum;

import java.util.*;
import java.io.*;

public class Main_G3_5875_오타 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] arr = br.readLine().toCharArray();
		
		int depth = 0;	// ( 는 +1, )는 -1로 했을 때 누적합
		int left = 0;	// ( 의 개수
		int right = 0;	// ) 의 개수
		int ans = 0;
		
		for(int i = 0, len = arr.length; i<len; i++) {
			if(arr[i] == '(') {
				depth += 1;
				right++;
			} else {
				depth -= 1;
				left++;
			}
			
			//depth == -1: 닫힌 괄호가 하나 더 많다.
			if(depth == -1) {	// )가 더 많은 케이스로, 현재까지의 )의 개수는 모두 오타의 후보이다.
				ans = left;
				break;	//오타의 최대 개수가 1개인데 이미 오타가 나왔으므로 더이상 볼 필요가 없음.
			}
			
			//depth == 1: 닫히는 괄호가 필요하다. 
			if(depth == 1) {	// (가 더 많은 케이스가 될 수도 있다. 이 이후로 ) 없이 (가 나오는 것들은 오타의 후보이다.
				right = 0;	//depth가 1이 된 이후의 ) 개수를 구해야 하므로 right를 0으로 초기화
			}
			
			ans = right;
		}
		
		System.out.println(ans);
	}

}
