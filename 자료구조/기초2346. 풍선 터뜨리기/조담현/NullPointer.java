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
		Balloon balloon = null;
		for (int i = 0; i < n; i++) {
			balloon = (move>=0) ? deque.pollFirst() : deque.pollLast();
			sb.append(balloon.idx);
			sb.append(" ");
			
			move = balloon.element;
			if(move>0) {
				for(int j=0; j<move-1; j++) {
					balloon = deque.pollFirst();
					deque.addLast(balloon);
				}
			} else {
				for(int j=0; j<(-move)-1; j++) {
					balloon = deque.pollLast();
					deque.addFirst(balloon);
				}
			}

		}
		bw.write(sb.toString());
		bw.close();
		

	}

}
