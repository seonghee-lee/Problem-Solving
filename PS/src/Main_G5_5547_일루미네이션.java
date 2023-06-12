import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G5_5547_일루미네이션 {
    static int W, H, sum;
    static int[][] map;
    static boolean[][] visited;
    static int[] drEven = { -1, -1, 0, 0, 1, 1 }; // y가 짝수일때 (0, 2, 4, ...)
    static int[] dcEven = { 0, 1, -1, 1, 0, 1 };
    static int[] drOdd = { -1, -1, 0, 0, 1, 1 }; // y가 홀수일때 (인덱스 0부터 시작)
    static int[] dcOdd = { -1, 0, -1, 1, -1, 0 };
    static Queue<Pos> queue = new ArrayDeque<>();
    static Queue<Pos> check = new ArrayDeque<>();

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        visited = new boolean[H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    queue.offer(new Pos(i, j));
                }
            }
        }

        checkBFS();

        bfs();

        System.out.println(sum);
    }

    // 내부 공간일 경우(바깥과 닿아있지 않으면...) map에서 -1로 변경한다
    //possible한 걸 표시해주는 2차원배열을 만들고, possible한 것과 인접한 것은 그냥 되도록 만들어버리기.
    private static void checkBFS() {
        check.offer(new Pos(0, 0));
        while (!check.isEmpty()) {
            boolean flag = false;
//            for (int q = 0, size = check.size(); q < 6; q++) {
                Pos cur = check.poll();
                visited[cur.r][cur.c] = true;
                int nr, nc;
                
                if (cur.r % 2 == 0) {
                    for (int i = 0; i < 6; i++) {
                        nr = cur.r + drEven[i];
                        nc = cur.c + dcEven[i];
                        if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
                            flag = true;
                            continue;
                        }
                        if(visited[nr][nc]) continue;
                        check.offer(new Pos(nr, nc));
                    }
                } else {
                    for (int i = 0; i < 6; i++) {
                        nr = cur.r + drOdd[i];
                        nc = cur.c + dcOdd[i];
                        if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
                            flag = true;
                            continue;
                        }
                        if(visited[nr][nc]) continue;
                        check.offer(new Pos(nr, nc));
                    }
                }
                
                if(!flag) {
                    map[cur.r][cur.c] = -1; 
                }
//            }
        }
    }

    private static void bfs() {
        while (!queue.isEmpty()) {
            Pos cur = queue.poll();
            int nr, nc;
            if (cur.r % 2 == 0) { // y가 짝수일때
                for (int i = 0; i < 6; i++) {
                    nr = cur.r + drEven[i];
                    nc = cur.c + dcEven[i];
                    if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
                        sum++;
                        continue;
                    }
                    if (map[nr][nc] == 1 || map[nr][nc] == -1)
                        continue;
                    sum++;
                }
            } else {
                for (int i = 0; i < 6; i++) {
                    nr = cur.r + drOdd[i];
                    nc = cur.c + dcOdd[i];
                    if (nr < 0 || nr >= H || nc < 0 || nc >= W) {
                        sum++;
                        continue;
                    }
                    if (map[nr][nc] == 1 || map[nr][nc] == -1)
                        continue;
                    sum++;
                }
            }
        }
    }
}