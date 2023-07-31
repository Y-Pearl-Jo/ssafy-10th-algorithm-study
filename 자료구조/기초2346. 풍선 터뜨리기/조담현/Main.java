/*
Deque 구현체로 LinkedList 사용하여, 메모리 초과 발생
Deque deque = new LinkedList();`
원인) LinkedList는 이중 연결리스트로 메모리 사용량이 많음
단순히 Deque의 맨 앞 / 뒤에서 item 을 삽입 / 삭제 하는 경우,
LinkedList 보다는 ArrayDeque을 사용하는 것이 메모리량에서 효율적
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
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
		Deque<Balloon> deque = new ArrayDeque<>();
		
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int element = 0;
		for (int i = 0; i < n; i++) {
			element = Integer.parseInt(st.nextToken());
			deque.addLast(new Balloon(i+1, element));
		}
		
		// NullPointerException이 계속 발동해서 0번째를 직접 sb에 append
		StringBuilder sb = new StringBuilder();
		Balloon balloon = deque.pollFirst();
		sb.append(balloon.idx);
		sb.append(" ");
		int move = balloon.element;
		
		while(!(deque.isEmpty())) {
			
			if(move>0) {
				for(int j=0; j<move-1; j++) {
					balloon = deque.pollFirst();
					deque.addLast(balloon);
				}
				balloon = deque.pollFirst();
			} 
			else {
				for(int j=0; j<(-move)-1; j++) {
					balloon = deque.pollLast();
					deque.addFirst(balloon);
				}
				balloon = deque.pollLast();
			}
			move = balloon.element;
			sb.append(balloon.idx);
			sb.append(" ");

		}
		bw.write(sb.toString());
		bw.close();
		

	}

}
