import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int A;
	static int[] arr;
	static int cnt;
	
	
	public static void InsertationSort() {
		for (int i = 1; i < A; i++) {
			int temp = arr[i];
			int j = i - 1;

			while (j >= 0 && arr[j] > temp) {
				arr[j + 1] = arr[j];
				cnt--;

				if (cnt == 0) {
					System.out.println(arr[j]);
					return;
				}

				j--;
			}
			if (j != i - 1) {
				arr[j + 1] = temp;
				cnt--;
			}

			if (cnt == 0) {
				System.out.println(temp);
				return;
			}

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		A = Integer.parseInt(st.nextToken());
		cnt = Integer.parseInt(st.nextToken());

		arr = new int[A];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < A; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		InsertationSort();

		if (cnt != 0) {
			System.out.println(-1);
		}

	}
}
