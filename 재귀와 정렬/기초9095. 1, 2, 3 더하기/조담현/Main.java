import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	int t = Integer.parseInt(br.readLine());
	
	for(int i=0; i<t; i++) {
	    int n = Integer.parseInt(br.readLine());
	    System.out.println(numOfCase(n));
	}
	
	
    }
    
    public static int numOfCase(int n) {
	
	if(n==1) {
	    return 1;
	} else if (n==2) {
	    return 2;
	} else if (n==3) {
	    return 4;
	}
	
	// n-3일때 3을 더하는 이외의 2와 1을 더해서 n을 만드는 경우의 수는 n-2와 n-1에서 체크
	// n-2일때 2를 더하는 이외의 1을 더해서 을 만드는 경우의 수는 n-1에서 체크
	// n-1일때 1을 더하는 경우의 수만 남음
	// 그래서 경우의 수를 곱해주지 않고 이대로 더해주어도 괜찮다.
	return numOfCase(n-1) + numOfCase(n-2) + numOfCase(n-3);
	
    }

   

    

}
