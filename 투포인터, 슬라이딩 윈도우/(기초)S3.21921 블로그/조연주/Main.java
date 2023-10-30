import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N,X,max,cnt;
    static int[] visitor;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = input(st);
        X = input(st);
        visitor = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            visitor[i] = input(st);
        }

        sw(); // 슬라이딩 윈도우

        if(max ==0){
            System.out.println("SAD");
        }
        else{
            System.out.println(max);
            System.out.println(cnt);
        }

    }

    // 슬라이딩 윈도우
    static void sw(){
        int sum = 0;

        for(int i=0; i<N; i++){
            sum += visitor[i];

            if(i == X-1){
                max = sum;
                cnt = 1;
            }

            if(i >= X){
                sum -= visitor[i-X];

                if(sum>max){
                    max = sum;
                    cnt = 1;
                }
                else if(sum==max){
                    cnt++;
                }
            }
        }
    }

    static int input(StringTokenizer st){
        return Integer.parseInt(st.nextToken());
    }
}
