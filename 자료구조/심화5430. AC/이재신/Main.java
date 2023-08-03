import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Deque<Integer> deque = new ArrayDeque<>();

		int test_case = Integer.parseInt(br.readLine());

		test: for (int i = 0; i < test_case; i++) {
			StringBuilder sb = new StringBuilder();
			String operator = br.readLine();
			int num = Integer.parseInt(br.readLine());
			
			String arr = br.readLine();

			if (arr.length() != 2) {
				arr = arr.substring(1, arr.length() - 1);
				String[] nArr = arr.split(",");

				for (int j = 0; j < nArr.length; j++) {
					deque.add(Integer.parseInt(nArr[j]));
				}
			}
			boolean LR = true;

			for (int j = 0; j < operator.length(); j++) {
				switch (operator.charAt(j)) {
				case 'R':
					LR = !LR;
					break;
				case 'D':
					if (num == 0) {
						bw.write("error\n");
						bw.flush();
						continue test;
					}
					if (LR == true) {
						deque.pollFirst();
						num--;
					} else {
						deque.pollLast();
						num--;
					}
				}
			}
			
			if (num == 0)
				sb.append("[]\n");
			else {
				sb.append("[");
				for (int j = 0; j < num; j++) {
					if (LR == true) {
						sb.append(deque.pollFirst()).append(",");
					} else {
						sb.append(deque.pollLast()).append(",");
					}
				}
				sb.replace(sb.length() - 1, sb.length(), "]");
				sb.append("\n");
			}
			bw.write(sb.toString());
			bw.flush();
		}

		bw.close();

	}
}
