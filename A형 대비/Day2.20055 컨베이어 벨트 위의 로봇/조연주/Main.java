// 13872kb 236ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,K,cnt;
    static int[] belt;
    static boolean[] robot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 내구도 입력받기
        belt = new int[N*2];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<belt.length; i++){
            belt[i] = Integer.parseInt(st.nextToken());
        }

        // 1.회전 -> 2.로봇 이동 -> 3.로봇 올리기 -> 4.내구도 확인
        robot = new boolean[N];
        do {
            cnt++;
            lotate(); // 1
            moveRobot(); // 2
            addRobot(); // 3
        } while (!isOver()); // 4

        System.out.println(cnt);

    }

    // 1. 회전
    static void lotate(){
        // 벨트 회전
        int temp = belt[belt.length-1];
        for(int i=belt.length-1; i>0; i--){
            belt[i] = belt[i-1];
        }
        belt[0] = temp;

        // 로봇 회전
        boolean tempR = robot[robot.length-1];
        for(int i=robot.length-1; i>0; i--){
            robot[i] = robot[i-1];
        }
        robot[robot.length-1] = false; // 로봇 내리기
        robot[0] = false;

    }

    // 2. 이동 -> 다음 칸에 로봇이 없고, 내구도가 1이상
    static void moveRobot(){
        for(int i=robot.length-2; i>=0; i--){
            if(robot[i] && !robot[i+1] && belt[i+1]>0){
                robot[i] = false;
                robot[i+1] = true;
                belt[i+1]--;
            }
        }

        robot[robot.length-1] = false; // 로봇 내리기
    }

    // 3. 로봇 올리기 -> 올리는 칸의 내구도가 0이상
    static void addRobot(){
        if(belt[0]>0){
            robot[0] = true; // 로봇 올리기
            belt[0]--; // 내구도 감소
        }
    }

    // 4. 내구도가 0인 칸의 개수 세기 -> K개 이상이면 종료
    static boolean isOver(){
        int cnt = 0;
        for(int i=0; i<belt.length; i++){
            if(belt[i]==0){
                cnt++;
                if(cnt==K) return true;
            }
        }
        return false;
    }

}
