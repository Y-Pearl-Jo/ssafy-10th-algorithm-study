// 20768kb 192ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // 전역 변수
    static int N;
    static int[] seq;
    static int[][] room,like;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};

    // 메인 함수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        room = new int[N+2][N+2];

        // 방 끄트머리를 어떻게한담
        Arrays.fill(room[0],-1);
        Arrays.fill(room[N+1],-1);
        for(int i=1; i<=N; i++){
            room[i][0] = -1;
            room[i][N+1] = -1;
        }

        // 선호 학생 입력받기
        like = new int[(N*N)+1][4];
        seq = new int[N*N];
        for(int i=0; i<N*N; i++){
            st = new StringTokenizer(br.readLine());
            // 현재 학생의 번호
            int stNum = Integer.parseInt(st.nextToken());
            seq[i] = stNum;
            // 좋아하는 학생의 번호
            for(int k=0; k<4; k++){
                like[stNum][k] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 자리 배정
        for(int i=0; i<N*N; i++){
            locate(seq[i]);
        }

        // 만족도 계산
        int ans = 0;
        for(int i=1; i<=N*N; i++){
            ans += calc(i);
        }

        // 정답 출력
        System.out.println(ans);

    }

    // 만족도 계산
    static int calc(int stNum){
        int satis = 0;
        for(int y=1; y<=N; y++){
            for(int x=1; x<=N; x++){
                if(room[y][x]==stNum){
                    for(int d=0; d<4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];
                        // 좋아하는 학생 체크
                        for (int idx=0; idx<4; idx++) {
                            if (room[ny][nx] == like[stNum][idx]) {
                                if(satis==0) satis += 1;
                                else satis *= 10;
                            }
                        }
                    }
                }

            }
        }

        return satis;
    }

    // 자리 배정
    static void locate(int stNum){
        int[][] freind = new int[N+2][N+2];
        int[][] empty = new int[N+2][N+2];

        // 아무도 앉지 않은 자리 -> 동서남북 살펴보기
        for(int y=1; y<=N; y++){
            for(int x=1; x<=N; x++){
                if(room[y][x]==0){
                    for(int d=0; d<4; d++){
                        int nx = x+dx[d];
                        int ny = y+dy[d];
                        // 2. 비어있는 칸 체크
                        if(room[ny][nx]==0){
                            empty[y][x]++;
                        }
                        // 1. 좋아하는 학생 체크
                        else{
                            for(int idx = 0; idx<4; idx++){
                                if(room[ny][nx] == like[stNum][idx]) freind[y][x]++;
                            }
                        }
                    }
                }
            }
        }

        // 자리 고르기
        // 1. 좋아하는 학생 수
        int max = 0;
        ArrayList<Integer> X = new ArrayList<>();
        ArrayList<Integer> Y = new ArrayList<>();
        for(int y=1; y<=N; y++){
            for(int x=1; x<=N; x++){
                if(freind[y][x]>max){
                    max = freind[y][x];
                    X.clear();
                    Y.clear();
                }
                if(freind[y][x]==max && room[y][x]==0){
                    X.add(x);
                    Y.add(y);
                }
            }
        }

        // 2. 빈칸 수
        max = 0;
        ArrayList<Integer> X2 = new ArrayList<>();
        ArrayList<Integer> Y2 = new ArrayList<>();
        for(int idx=0; idx<X.size(); idx++){
            int x = X.get(idx);
            int y = Y.get(idx);
            if(empty[y][x]>max){
                max = empty[y][x];
                X2.clear();
                Y2.clear();
            }
            if(empty[y][x]==max && room[y][x]==0){
                X2.add(x);
                Y2.add(y);
            }
        }

        // 3. 왼쪽 위부터 채우기
        room[Y2.get(0)][X2.get(0)] = stNum;

    }
    
}
