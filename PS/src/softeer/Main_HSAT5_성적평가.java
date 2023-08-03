package softeer;

import java.util.*;
import java.io.*;

public class Main_HSAT5_성적평가 {
	public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] score = new int[4][N+1];  //score[i][j]: i대회에 j의 점수
        int[][] result = new int[5][N+1];

        StringTokenizer st;
        PriorityQueue<Person> pq = new PriorityQueue<>();
        
        for(int i = 1; i<=3; i++){
            st = new StringTokenizer(br.readLine(), " ");
            
            for(int j = 1; j<=N; j++){
                score[i][j] = Integer.parseInt(st.nextToken());
                pq.offer(new Person(j, score[i][j]));
            }

            int prev = -1;
            int rank = 0;
            int same = 0;

            while(!pq.isEmpty()){
                Person cur = pq.poll();
                if(prev == cur.score){
                    result[i][cur.index] = rank;
                    same++;
                } else{
                    rank++;
                    if(same > 0) rank += same;
                    result[i][cur.index] = rank;
                    prev = cur.score;
                    same = 0;
                }
            }
        }

        //최종 등수 구하기
        PriorityQueue<Person> pq2 = new PriorityQueue<>();
        for(int i = 1; i<=N; i++){
            int sum = score[1][i] + score[2][i] + score[3][i];
            pq2.offer(new Person(i, sum));
        }

        int prev = -1;
        int rank = 0;
        int same = 0;

        while(!pq2.isEmpty()){
            Person cur = pq2.poll();
            if(prev == cur.score){
                    result[4][cur.index] = rank;
                    same++;
                } else{
                    rank++;
                    if(same > 0) rank += same;
                    result[4][cur.index] = rank;
                    prev = cur.score;
                    same = 0;
                }
        }

        //결과 출력
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i<=4; i++){
            for(int j = 1; j<=N; j++){
                sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}

class Person implements Comparable<Person> {
    int index, score;
    Person(int index, int score){
        this.index = index;
        this.score = score;
    } 

    public int compareTo(Person o){
        return o.score - this.score;
    }
}
