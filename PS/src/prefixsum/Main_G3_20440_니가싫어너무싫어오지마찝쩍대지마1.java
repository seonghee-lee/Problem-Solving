package prefixsum;

import java.util.*;
import java.io.*;

//좌표 압축 개념 익히기 : 이 문제에서는 TreeSet을 사용해서 중복 제거 + 오름차순 자동 정렬
//리스트에서 인덱스 찾는 방법은 Collections.binarySearch(list, key)

public class Main_G3_20440_니가싫어너무싫어오지마찝쩍대지마1 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] sArr = new int[N];	//시작시간을 저장하는 배열
		int[] eArr = new int[N];	//끝 시간을 저장하는 배열
		
		TreeSet<Integer> treeSet = new TreeSet<>();		//좌표 압축을 위해, 정렬 + 중복제거 수행
		
		for(int i = 0; i<N; i++) {
			String[] inputs = br.readLine().split(" ");
			int start = Integer.parseInt(inputs[0]);
			int end = Integer.parseInt(inputs[1]);
			
			treeSet.add(start);
			treeSet.add(end);
			
			sArr[i] = start;
			eArr[i] = end;
		}
		
		List<Integer> compressed = new ArrayList<>(treeSet);	//original index를 얻을 때 binary search를 하기 위해 리스트로 옮겨담는다.
		int[] sum = new int[treeSet.size()];	//압축된 좌표에 대한 누적합 배열
		
		for(int i = 0; i<N; i++) {		//i: sArr과 eArr에 순차적으로 접근한다.
			int sIdx = Collections.binarySearch(compressed, sArr[i]);	//압축 좌표의 인덱스를 찾음
			int eIdx = Collections.binarySearch(compressed, eArr[i]);
			
			//압축 좌표의 누적합에서 imos 기법 사용
			sum[sIdx] += 1;
			sum[eIdx] -= 1;
		}
		
		//온전한 누적합 배열 구하기
		for(int i = 1, len = treeSet.size(); i<len; i++) {
			sum[i] += sum[i-1]; 
		}
		
		int max = 0;	//최대 모기의 수
		int start = -1;	//정답 구간의 시작 인덱스
		int end = -1;	//정답 구간의 끝 인덱스
		
		for(int i = 0, len = treeSet.size(); i<len; i++) {
			if(sum[i] > max) {
				max = sum[i];
				start = i;
				end = i;
			} else if(sum[i] == max && end == i-1) {	//연속한 경우만 취급
				end = i;
			}
		}
		
		StringBuilder ans = new StringBuilder();
		ans.append(max).append("\n");
		ans.append(compressed.get(start)).append(" ").append(compressed.get(end+1));
		
		System.out.print(ans);
	}
}
