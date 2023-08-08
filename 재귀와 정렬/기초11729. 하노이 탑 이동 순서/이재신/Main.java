//결국 답지를 오픈해버린 문제
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int cnt = 0;

	public static void hanoi(int step, int from, int via, int to) {
		if( step == 1) {
			sb.append(from + " " + to).append("\n");
			cnt++;
		}
		else {
			hanoi(step - 1, from, to, via);
			sb.append(from + " " + to).append("\n");
			cnt++;
			hanoi(step - 1, via, from, to);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		
		hanoi(N, 1, 2, 3);
		System.out.println(cnt);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
