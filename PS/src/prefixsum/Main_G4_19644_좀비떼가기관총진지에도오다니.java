package prefixsum;

import java.util.*;
import java.io.*;

public class Main_G4_19644_좀비떼가기관총진지에도오다니 {
	static int L, Ml, Mk, C;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		L = Integer.parseInt(br.readLine());	//기관총 진지 앞쪽 길의 거리
		String[] inputs = br.readLine().split(" ");
		Ml = Integer.parseInt(inputs[0]);	//기관총의 유효 사거리
		Mk = Integer.parseInt(inputs[1]);	//기관총의 1m당 살상력
		C = Integer.parseInt(br.readLine());	//지뢰의 개수 : 지뢰는 1m 떨어진 좀비한테만 사용 가능
		
		int[] zombie = new int[L+1];
		for(int i = 1; i<=L; i++) {
			zombie[i] = Integer.parseInt(br.readLine());
		}
		
		boolean live = true;
		long[] attackSum = new long[L+1];		//attackSum[i]: 사거리 고려 없이, 좀비 i가 받은 기관총 공격의 총합 (사거리 고려는 누적합의 뺄셈으로 적용.)
		
		//총으로 죽이지 못하면 지뢰로 죽여야한다.
		for(int i = 1; i<=L; i++) {
			int considerMl = Math.max(0,  i - Ml);
			long prevDamage = attackSum[i-1] - attackSum[considerMl];
			
			if(zombie[i] <= prevDamage + Mk) {	//기관총으로 사살 가능
				attackSum[i] = attackSum[i-1] + Mk;	//현재 좀비가 받은 총 기관총 공격을 누적합에 저장한다.
			} else {	//지뢰로 사살
				if(C > 0) {	//지뢰가 남아있다면
					C--;	//지뢰 사용
					attackSum[i] = attackSum[i-1];	//지뢰 공격은 누적합에 포함x (기관총과 지뢰는 동시 사용 불가능)
				} else {
					live = false;
					break;
				}
			}
		}
		
		if(live) System.out.println("YES");
		else System.out.println("NO");
		
	}
}
