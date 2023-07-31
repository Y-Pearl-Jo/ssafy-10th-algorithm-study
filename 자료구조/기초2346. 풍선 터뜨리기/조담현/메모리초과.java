import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Balloon {
	int idx;
	int element;
	
	public Balloon(int idx, int element) {
		this.idx = idx;
		this.element = element;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Deque<Balloon> deque = new LinkedList<>();
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int element = Integer.parseInt(st.nextToken());
			deque.addLast(new Balloon(i+1, element));
		}
		int move = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			Balloon balloon = deque.pollFirst();
			sb.append(balloon.idx);
			sb.append(" ");
			
			move = balloon.element;
			if(move>0) {
				for(int j=0; j<move-1; j++) {
					Balloon b = deque.pollFirst();
					deque.addLast(b);
				}
			} else {
				for(int j=0; j<Math.abs(move); j++) {
					Balloon b = deque.pollFirst();
					deque.addLast(b);
				}
			}

		}
		bw.write(sb.toString());
		bw.close();
		

	}

}
