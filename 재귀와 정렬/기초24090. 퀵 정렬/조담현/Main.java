import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int count = 0;
	static int n;
	static int k;
	static int[] res = new int[2];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			arr[i] = num;
		}

		quickSort(arr, 0, arr.length - 1);

		if (res[0] == 0) {
			System.out.println(-1);
		} else {
			System.out.println(res[0] + " " + res[1]);
		}
	}

	public static int[] quickSort(int[] arr, int p, int r) {

		if (p < r) {
			int q = partition(arr, p, r);
			quickSort(arr, p, q - 1);
			quickSort(arr, q + 1, r);
		}

		return arr;
	}

	public static int partition(int[] arr, int p, int r) {
		// 맨 우측 값을 pivot으로 설정
		int x = arr[r];
		int i = p - 1;

		// 피벗보다 인덱스가 작은 배열을 돌며 작은 값을 피벗의 왼쪽으로
		for (int j = p; j <= r - 1; j++) {
			if (arr[j] <= x) {
				int temp = arr[++i];
				arr[i] = arr[j];
				arr[j] = temp;
				count++;
				if (count == k) {
					res[0] = arr[i];
					res[1] = arr[j];
				}
			}

		}
		// 다 돌은 후 피벗을 왼쪽에 저장한 i번째 값 뒤로 이동
		if (i + 1 != r) {
			int temp = arr[i + 1];
			arr[i + 1] = arr[r];
			arr[r] = temp;
			count++;
			if (count == k) {
				res[0] = arr[i + 1];
				res[1] = arr[r];
			}
		}

		return i + 1;
	}

}
