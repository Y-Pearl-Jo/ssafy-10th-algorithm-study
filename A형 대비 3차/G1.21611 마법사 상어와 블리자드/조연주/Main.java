// 20160kb 148ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int N,M,sr,sc,cnt1,cnt2,cnt3;
    static int[] nums;
    static int[][] index,org;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 한 변의 길이
        M = Integer.parseInt(st.nextToken()); // 블리자드 시전 횟수

        // 마법사 상어 위치 -> 가운데
        sr = ((N+1)/2)-1;
        sc = sr;

        // 초기맵 입력받기
        org = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int k=0; k<N; k++){
                org[i][k] = Integer.parseInt(st.nextToken());
            }
        }

        // 1.블리자드(삭제) -> 2.이동 -> 3.[ 폭발(삭제)->이동 ] -> 4.변화(증가)
        nums = new int[(N*N)-1];
        makeArrays();

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()); // 방향
            int s = Integer.parseInt(st.nextToken()); // 거리
            blizzard(d,s); // 1
            move(); // 2
            bomb(); // 3
            change(); // 4
        }

        // 정답 출력
        System.out.println(cnt1+(cnt2*2)+(cnt3*3));

    }

    // 확인용
    static void print(){
        for(int i=0; i<nums.length; i++){
            System.out.print(nums[i]+" ");
        }
        System.out.println();
        System.out.println();
    }

    // 달팽이 배열 만들기
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    static void makeArrays(){
        index = new int[N][N];
        int num = (int) Math.pow(N,2)-2;
        int r = 0;
        int c = 0;
        int d = 0;
        while(num>0){
            while(true){
                index[r][c] = num; // 배열에 인덱스 값 넣기 (달팽이)
                nums[num] = org[r][c]; // 구슬 배열에 숫자 넣기
                r += dr[d];
                c += dc[d];
                num--;
                if(r<0 || c<0 || r>=N || c>=N || index[r][c]!=0){
                    r -= dr[d];
                    c -= dc[d];
                    num++;
                    break;
                }
            }
            d = (d+1)%4;
        }
    }

    // 블리자드
    static int[] br = {0,-1,1,0,0};
    static int[] bc = {0,0,0,-1,1};
    static void blizzard(int d, int s){
        int r = sr;
        int c = sc;
        while(s-- > 0){
            r += br[d];
            c += bc[d];
            nums[index[r][c]] = 0;
        }
    }

    // 구슬 이동
    static void move() {
        int[] temp = new int[nums.length];
        int idx = 0;

        for(int i=0; i<nums.length; i++){
            if(nums[i]!=0){
                temp[idx++] = nums[i];
            }
        }
        nums = temp.clone();
    }

    // 구슬 폭발
    static void bomb(){
        boolean isBombed = true;
        while(isBombed){
            isBombed = false;
            int cnt = 1;
            int startIdx = 0;
            for(int i=0; i<nums.length-1; i++){
                if(nums[i]==0) break;
                if(nums[i]==nums[i+1]){
                    cnt++;
                }
                else{
                    if(cnt>=4){
                        isBombed = true;
                        switch (nums[i]){
                            case 1:
                                cnt1 += cnt;
                                break;
                            case 2:
                                cnt2 += cnt;
                                break;
                            case 3:
                                cnt3 += cnt;
                        }
                        for(int idx=startIdx; idx<=i; idx++){
                            nums[idx] = 0;
                        }
                    }
                    cnt = 1;
                    startIdx = i+1;
                }
            }
            move();
        }
    }

    // 구슬 변화
    static void change(){
        int[] temp = new int[nums.length];
        int idx = 0;
        int len = 1;

        for(int i=0; i<nums.length; i++){
            if(nums[i]==0 || idx>=nums.length) break;
            if(i<nums.length-1 && nums[i]==nums[i+1]){
                len++;
            }
            else{
                temp[idx] = len;
                temp[idx+1] = nums[i];
                idx += 2;
                len = 1;
            }
        }
        nums = temp.clone();
    }

}
