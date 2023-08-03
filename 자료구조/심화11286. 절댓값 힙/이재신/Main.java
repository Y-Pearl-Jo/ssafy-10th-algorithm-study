import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> absMin = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if(Math.abs(o1) == Math.abs(o2)) {
					return o1 - o2;
				}
				return Math.abs(o1) - Math.abs(o2);
			}
		});
		
		int nInput = Integer.parseInt(br.readLine());
		int input = 0;
		
		for(int i = 0; i < nInput; i++) {
			input = Integer.parseInt(br.readLine());
			
			if(input == 0) {
				System.out.println(absMin.isEmpty() ? 0 : absMin.poll());
			}
			else {
				absMin.offer(input);
			}
		}
		
	}

}
