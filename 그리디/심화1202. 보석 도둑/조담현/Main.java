/*
 * 1. 보석의 무게를 기준으로 오름차 순 정렬한다.
 * 2. 가방의 무게를 기준으로 오름차 순 정렬한다.
 * 3. 가방을 하나씩 보면서, 가방의 용량보다 작은 보석(가방에 넣을 수 있는 보석)들을 모두 우선순위 큐에 넣고
 * 4. 우선순위 큐에서 가장 비싼 보석을 꺼낸다.
 * 5. 이후 다음 가방에서도, 가방의 용량은 점점 커지므로 작은 보석들이 남아있는 큐에서 3번부터 반복하면 된다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] jewels = new int[N][2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			jewels[i][0] = m;
			jewels[i][1] = v;
		}
		
		//  1. 보석의 무게를 기준으로 오름차 순 정렬한다.
		Arrays.sort(jewels, (o1, o2) -> {
			return o1[0] - o2[0];
		});
		
		// 2. 가방의 무게를 기준으로 오름차 순 정렬한다.
		int[] bags = new int[K];
		for(int i=0; i<K; i++) {
			int c = Integer.parseInt(br.readLine());
			bags[i] = c;
		}
		Arrays.sort(bags);
		
		
		PriorityQueue<int[]> queue = new PriorityQueue<>((o1,o2)-> {
			return o2[1] - o1[1];
		});
		int idx = 0;
		long sum = 0;
		for(int i=0; i<K; i++) {
			// 3. 가방을 하나씩 보면서, 가방의 용량보다 작은 보석(가방에 넣을 수 있는 보석)들을 모두 우선순위 큐에 넣고
			while(idx<N) {
				if(jewels[idx][0]<=bags[i]) {
					queue.offer(jewels[idx++]);
				} else {
					break;
				}
			}
			// 4. 우선순위 큐에서 가장 비싼 보석을 꺼낸다.
			if(!queue.isEmpty()) {
				int[] jewel = queue.poll();
				sum+= jewel[1];
			}
			
		}
		// 시간 복잡도는 O(N+K)
		System.out.println(sum);
		
	} 

}
