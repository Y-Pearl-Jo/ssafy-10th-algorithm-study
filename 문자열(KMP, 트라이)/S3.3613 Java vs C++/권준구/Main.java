import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb;

		String str = br.readLine();
		char[] arr = str.toCharArray();

		// 대문자로 시작, _로 시작, _로 끝
		if (('A' <= arr[0] && arr[0] <= 'Z') || arr[0] == '_' || arr[arr.length - 1] == '_') {
			System.out.println("Error!");

			return;
		}

		boolean Java = true;
		boolean C = true;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == '_') {
				if (i > 1 && arr[i - 1] == '_') { // _가 연속으로 붙어있는지 확인
					System.out.println("Error!");

					return;
				}

				Java = false;
			}

			else if ('A' <= arr[i] && arr[i] <= 'Z') {
				C = false;
			}
		}

		if (!Java && !C) { // 대문자와 _를 같이 사용했다면
			System.out.println("Error!");

			return;
		}

		if (Java && C) { // 글자가 그냥 소문자라면
			System.out.println(str);

			return;
		}

		sb = new StringBuilder();

		// java의 경우 대문자를 _소문자로 변경
		if (Java) {
			for (char c : arr) {
				if ('A' <= c && c <= 'Z') {
					sb.append('_').append((char) (c + 32));
				}

				else {
					sb.append(c);
				}
			}
		}

		// c++의 경우 _소문자를 대문자로 변경
		else {
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] == '_' && i < arr.length - 1) {
					sb.append((char) (arr[++i] - 32));
				}

				else {
					sb.append(arr[i]);
				}
			}
		}

		System.out.println(sb.toString());
	}
}
