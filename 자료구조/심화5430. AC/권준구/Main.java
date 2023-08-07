import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuffer sb = new StringBuffer();
		int T = sc.nextInt();

		for (int i = 0; i < T; i++) {
			Deque<Integer> dq = new LinkedList<>();
			boolean direction = true;
			boolean error = false;

			String p = sc.next(); // RDD
			int n = sc.nextInt(); // 4
			String str = sc.next();
			str = str.substring(1, str.length() - 1); // []자르기
			String arr[] = str.split(","); // 나누기

			if (str != "") {
				for (int j = 0; j < arr.length; j++) {
					dq.add(Integer.parseInt(arr[j]));
				}
			}

			for (int j = 0; j < p.length(); j++) {
				switch (p.charAt(j)) {
				case 'R':
					direction = !direction;
					break;

				case 'D':
					if (!dq.isEmpty()) {
						if (direction) { // 순방향
							dq.pollFirst();
						} else { // 역방향
							dq.pollLast();
						}
						break;
					} else {
						error = true;
						break;
					}
				default:
					break;
				}

				// 비어있는 덱 원소 삭제 시도시
				if (error)
					break;
			}

			if (error) { // 비어있는 덱 원소 삭제 시도시
				sb.append("error").append("\n");
			} else {
				sb.append("[");
				if (direction) {
					while (!dq.isEmpty()) {
						sb.append(dq.pollFirst());
						if (dq.isEmpty())
							break;
						sb.append(",");
					}

				} else {
					while (!dq.isEmpty()) {
						sb.append(dq.pollLast());
						if (dq.isEmpty())
							break;
						sb.append(",");
					}
				}
				sb.append("]").append("\n");
			}
		}
		System.out.println(sb);
	}
}
