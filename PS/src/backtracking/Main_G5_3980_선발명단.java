package backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 어떻게 선수들의 능력치를 선택했을 때 팀의 최대 능력치를 구할 수 있는가? 0인 것의 포지션은 선택하지 않는다. -> 자동적으로 백트래킹
 * 됨 합계를 들고다니면서 고른다. 다른 선수에 의해 선택된 포지션은 선택되면 안된다.
 *
 */
public class Main_G5_3980_선발명단 {
	static int[][] map;
	static int max;
	static boolean[] selectedPosition; // 선택된 포지션 : true

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			map = new int[11][11];
			max = Integer.MIN_VALUE;
			selectedPosition = new boolean[11];

			StringTokenizer st;

			for (int i = 0; i < 11; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 11; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			go(0, 0);
			System.out.println(max);
		}
	}

	// cnt: 재귀 타는 횟수 == 현재 선수 번호, sum: 현재까지 능력치 합계
	private static void go(int cnt, int sum) {

		if (cnt == 11) {
			max = Math.max(max, sum);
			return;
		}

		for (int j = 0; j < 11; j++) { // 선수 cnt의 포지션에 따른 능력치들 중 선택한다.

			if (map[cnt][j] == 0 || selectedPosition[j])
				continue;

			selectedPosition[j] = true; // 포지션 선택

			go(cnt + 1, sum + map[cnt][j]);
			selectedPosition[j] = false; // 원래대로 돌려놓기.
		}
	}
}
