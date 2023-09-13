// 11584kb 80ms

import java.util.*;
import java.io.*;

public class Main {
    // 변수
    static int s,N,K,R1,R2,C1,C2;
    static int[][] arr;

    // main
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        s = Integer.parseInt(st.nextToken()); // 시간 -> 0 ~ 10
        N = Integer.parseInt(st.nextToken()); // 나누어지는 단위 -> 3 ~ 8
        K = Integer.parseInt(st.nextToken()); // 검정색으로 채울 부분

        // 전체 배열을 만들면?
        // len(N^s)의 최대 크기 : 8^10 = 약 10억
        // 배열의 최대 크기 = 10억*10억 = 100억
        // 따라서 -> 정답 배열만 만들기
        R1 = Integer.parseInt(st.nextToken());
        R2 = Integer.parseInt(st.nextToken());
        C1 = Integer.parseInt(st.nextToken());
        C2 = Integer.parseInt(st.nextToken());
        arr = new int[R2-R1+1][C2-C1+1]; // 정답을 저장할 배열

        // 계산
        int len = (int) Math.pow(N, s);
        partition(0,0,0,len,0);

        // 출력
        StringBuilder sb = new StringBuilder();
        for(int[] ar : arr) {
            for(int a : ar) {
                sb.append(a);
            }
            sb.append("\n");
        }
        System.out.println(sb);

        br.close();
    }

    // 분할정복
    static void partition(int cnt, int y, int x, int len, int num) {
        // y시작점이 R2보다 작거나, y끝점이 R1보다 클 때
        // x시작점이 C2보다 작거나, x끝점이 C1보다 클 때
        if(y>R2 || x>C2 || y+len<=R1 || x+len<=C1){
            return;
        }
        // s만큼 재귀하면 num을 넣고 리턴
        if(cnt==s) {
            arr[y-R1][x-C1] = num;
            return;
        }

        int start = (N-K)/2; // 검정색 : 시작하는 지점
        int end = start+K-1; // 검정색 : 끝나는 지점
        int nextLen = len/N;

        // 재귀
        for(int i=0; i<N; i++) {
            for(int k=0; k<N; k++) {
                if(num==0 && i>=start && i<=end && k>=start && k<=end) {
                    partition(cnt+1, y+(i*nextLen), x+(k*nextLen), nextLen, 1);
                }
                else{
                    partition(cnt+1, y+(i*nextLen), x+(k*nextLen), nextLen, num);
                }
            }
        }
    }

}
