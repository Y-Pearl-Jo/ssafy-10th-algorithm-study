// 61360kb 644ms
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static int N,M;
	static int[] sum;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = input(st);
		M = input(st);
		sum = new int[N+1];
		
		// 누적합 배열 만들기
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			int n = input(st);
			sum[i] = sum[i-1]+n;
		}
		
		prefix();
		
		bw.flush();
		bw.close();

	}
	
	static void prefix() throws IOException {
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			int a = input(st);
			int b = input(st);
			bw.write(sum[b]-sum[a-1]+"\n");
		}
	}
	
	// -----------
	
	static int input() throws IOException {
		return Integer.parseInt(br.readLine());
	}
	
	static int input(StringTokenizer st) throws IOException {
		return Integer.parseInt(st.nextToken());
	}

}
