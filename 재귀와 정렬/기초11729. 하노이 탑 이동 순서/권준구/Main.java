import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
	
    static StringBuffer sb;
    static int cnt = 0;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuffer();

        int N = Integer.parseInt(br.readLine());

        hanoi(1, 2, 3, N);

        System.out.println(cnt);
        System.out.print(sb);
    }

    public static void hanoi(int one, int two, int three, int N){
        if(N == 1) {
            cnt++;
            sb.append(one+" "+three).append("\n");
            return;
        }

        hanoi(one, three, two, N-1);
        sb.append(one+" "+three).append("\n");
        cnt++;
        hanoi(two, one, three, N-1);
    }
}
