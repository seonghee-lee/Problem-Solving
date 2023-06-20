package twopointer;

import java.util.*;
import java.io.*;

/*
 * 3개의 수를 고른다 : 1개의 수를 고정 + 2개의 수를 고른다. => 두 수를 고를 때 고려사항을 유의하며 풀이
 * */

public class Main_G4_3151_합이0 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);	//탐색을 하기 위해 정렬한다.
		long count = 0;		//가능한 경우의 수
		int sum = 0;		//세 수의 합
		
		//1개의 수: i를 고정하고 문제를 푼다.
		for(int i = 0; i<N; i++) {
			if(arr[i] > 0) break;		//양수이면 어차피 뒤의 두 수로 합을 0으로 만들 수 없다.
			
			int left = i + 1;	//i보다 하나 뒤 값으로 설정한다.
			int right = N - 1;	//가장 오른쪽 값으로 설정한다.
			
			while(left < right) {	//포인터가 역전되기 이전까지 반복하며 가능한 경우의 수를 모두 구한다.
				
				sum = arr[i] + arr[left] + arr[right];	//현재 포인터로 설정했을 때 합계
				
				//sum==0인 경우, 완성되었는데 이때 여러가지 형태가 있으며 count 계산을 달리해야한다.
				if(sum == 0) {
					
					//-2 1 1 1 1 1 과 같은 경우 : 조합을 사용하여 2개 선택한다.
					if(arr[left] == arr[right]) {
						int n = right - left + 1;
						count += combination(n);
						break;
					}
					
					//-5 2 2 3 3 3 3 과 같은 경우  || 일반적인 경우
					int lCount = 1;
					int rCount = 1;
					
					while(left + 1 < right && arr[left] == arr[left+1]) {	//다음 것과 동일하면 반복하며 개수 센다.
						lCount++;
						left++;		//포인터가 변하고 있는 걸 유념해야 함.
					}
					
					while(right - 1 > left && arr[right] == arr[right-1]) {	//앞의 것과 동일하면 반복하며 개수 센다.
						rCount++;
						right--;
					}
					
					count += lCount * rCount;	//가능한 모든 경우의 수를 더해준다.
				}
				//else if 가 아닌 이유는? : 위에서 조정한 포인터(left 또는 right)가 다음 값을 가리키게 해야 된다.(sum==0 조건일 때를 한번 돌아야 한다. 안 그러면 포인터가 변하지 않으므로 무한루프)
				if(sum > 0) {	//sum이 양수이면 줄여야한다.
					right--;
				} else {				//sum이 0 또는 음수이면 키워야한다.
					left++;
				}
			}
		}
		System.out.println(count);
	}
	
	private static long combination(int n) {
		return n * (n -1) / 2;
	}
}
