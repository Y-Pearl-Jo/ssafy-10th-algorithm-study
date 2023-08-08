import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int k = 0;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int n = Integer.parseInt(br.readLine());
	
	System.out.println(hanoyCount(n));
	hanoy(n, 1, 2, 3);
	System.out.println(sb.toString());
	
    }
    
    public static void hanoy (int n, int start, int middle, int end) {
	if(n==1) {
	    sb.append(start + " " + end + "\n");
	    return;
	}
	
	//n-1개를 가운데로 이동하고
	hanoy(n-1, start, end, middle);
	// n번째 원판을 옮긴다.
	sb.append(start + " " + end + "\n");
	// 가운데의 n-1개를 마지막으로 옮긴다.
	hanoy(n-1, middle, start, end);
    }
    
    public static int hanoyCount(int n) {
	if(n==1) return 1;
  // n-1개를 middle로 옮기고, 바닥 원판을 end로 옮긴후에 n-1개를 다시 end로 옮긴다.
	return 2*hanoyCount(n-1)+1;
    }
   

    

}
