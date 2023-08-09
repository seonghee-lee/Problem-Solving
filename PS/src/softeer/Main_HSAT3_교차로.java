package softeer;

import java.util.*;
import java.io.*;

/**
 * 미해결
 * 40.0 / 100 
 */

public class Main_HSAT3_교차로 {
	static Queue<Car>[] road;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		road = new ArrayDeque[4];
		for (int i = 0; i < 4; i++) {
			road[i] = new ArrayDeque<>();
		}

		for (int i = 0; i < N; i++) {
			String[] inputs = br.readLine().split(" ");
			int enter = Integer.parseInt(inputs[0]);
			char r = inputs[1].charAt(0);

			if (r == 'A') {
				road[0].offer(new Car(i, enter));
			} else if (r == 'B') {
				road[1].offer(new Car(i, enter));
			} else if (r == 'C') {
				road[2].offer(new Car(i, enter));
			} else {
				road[3].offer(new Car(i, enter));
			}
		}

		List<Car> result = new ArrayList<>();

		int time = 0;
		boolean isStuck = true;

		while (true) {

			isStuck = true;
			if (allEmpty())
				break;
			
			int getTime = noTime(time);
			
			//계산이 필요 없는 시간은 반복을 돌지 않도록 해야 시간초과가 안 남.
			if(getTime > 0) {
				time = getTime;
				continue;
			}
			
			if (getTime == 0) {

				// 교착 상태 확인 : 현재 시간에서, 모든 도로에 차가 있어서 아무도 나갈 수 없다.
				boolean[] car = new boolean[4];
				for (int i = 0; i < 4; i++) {
					if (road[i].isEmpty())
						continue;
					Car temp = road[i].peek();
					if (temp.enter <= time) {
						car[i] = true;
					}
				}

				for (int i = 0; i < 4; i++) {
					if (!car[i]) {
						isStuck = false;
						break;
					}
				}

				if (isStuck)
					break;

				// 현재 시간에 지나갈 수 있는 차들은 지나가게 한다.
				for (int i = 0; i < 4; i++) { // i: 도로번호
					if (road[i].isEmpty())
						continue;

					Car cur = road[i].peek();	//도로의 가장 앞 차
					if (cur.enter > time)		//아직 등장 타이밍이 아니라면 넘어간다.
						continue;

					// 오른쪽 도로를 확인한다.
					int right = i - 1;
					if (right < 0)
						right = 3;

					//오른쪽 도로가 비어있으면 무조건 갈 수 있음. || 오른쪽 도로의 맨 앞 차량이 아직 등장하지 않았으면 갈 수 있음.
					if (road[right].isEmpty() || road[right].peek().enter > time) {
						cur.canGo = true;
					}
				}

				for (int i = 0; i < 4; i++) {
					if (road[i].isEmpty())
						continue;

					if (road[i].peek().canGo) {
						Car go = road[i].poll();
						go.out = time;
						result.add(go);
					}
				}
			}

			time++;
		}

		Collections.sort(result);

		StringBuilder sb = new StringBuilder();
		int curCar = -1;
		for (Car c : result) {
			curCar = c.number;
			sb.append(c.out).append("\n");
		}

		if (curCar < N - 1) {
			for (int i = curCar + 1; i < N; i++) {
				sb.append(-1).append("\n");
			}
		}

		System.out.print(sb.toString().trim());
	}

	private static boolean allEmpty() {
		int size = 0;
		for (int i = 0; i < 4; i++) {
			size += road[i].size();
		}

		if (size == 0)
			return true;

		return false;
	}

	private static int noTime(long time) {
		boolean flag = true;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 4; i++) {
			if (road[i].isEmpty())
				continue;
			
			Car peek = road[i].peek();
			
			if (peek.enter <= time)	flag = false;	//현재 시간에 갈 수 있는 차량이 존재한다.
			else {
				min = Math.min(min, peek.enter);
			}
		}
		
		if(!flag) return 0;
		else return min;	//min시간이 되어서야 갈 수 있는 차량이 존재한다.
	}

}

class Car implements Comparable<Car> {
	int number, enter;
	int out;
	boolean canGo = false;

	Car(int number, int enter) {
		this.number = number;
		this.enter = enter;
	}

	public int compareTo(Car o) {
		return this.number - o.number;
	}
}
