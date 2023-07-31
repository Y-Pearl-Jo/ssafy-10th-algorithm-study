import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		ArrayDeque<int[]> list = new ArrayDeque<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int[] arr = { i + 1, Integer.parseInt(st.nextToken()) };
			list.add(arr);
		}
		
		while (list.size() > 1) {
			int[] arr = list.pollFirst();
			sb.append(arr[0]).append(" ");
			int n = arr[1];
			// 양수일 때
			if (n > 0) {
				for (int i = 1; i < n; i++) {
					list.offerLast(list.pollFirst()); // 덱 맨 앞쪽을 덱 맨 뒤쪽에 더한다.
				}
				// 음수일 때
			} else if (n < 0) {
				for (int i = n; i != 0; i++) {
					list.offerFirst(list.pollLast()); // 덱 마지막 쪽을 제거한 다음 덱의 앞쪽에 삽입한다.
				}
			}
		}
		sb.append(list.poll()[0]);
		bw.write(sb + "\n");
		bw.flush();
		bw.close();
	}
}
