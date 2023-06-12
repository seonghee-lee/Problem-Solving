package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main_G5_2866_문자열잘라내기 {
	static int R, C, count;
	static char[][] map;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		
		map = new char[R][C];
		
		for(int i = 0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		List<String> list = new ArrayList<>();
		
		//O(N*N) = 1000 * 1000 = 1000000 : 백만
		for(int i = 0; i<C; i++) {
			StringBuilder sb = new StringBuilder(1000);
			for(int j = 0; j<R; j++) {
				sb.append(map[j][i]);
			}
			list.add(sb.toString());
		}
		
		Set<String> set = new HashSet<>();
		int end = R;
		top:
		for(int start = 1; start < R; start++) {
			for(String str : list) {
				String cur = str.substring(start, end);
				if(!set.contains(cur)) set.add(cur);
				else {
					break top;
				}
			}
			set.clear();
			count++;
		}//O(N * N) = 백만
		
		System.out.println(count);
	}

}
