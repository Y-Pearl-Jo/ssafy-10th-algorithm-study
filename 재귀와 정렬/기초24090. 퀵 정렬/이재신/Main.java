import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int cnt;
	static int[] result = new int[2];

	public static void quickSort(int[] arr, int start, int end) {
		if (start < end) {
			int pivot = partition(arr, start, end);
			quickSort(arr, start, pivot - 1);
			quickSort(arr, pivot + 1, end);
		}
	}

	public static int partition(int[] arr, int start, int end) {
		int x = arr[end];
		int i = start - 1;

		for (int j = start; j < end; j++) {
			if (arr[j] <= x) {
				swap(arr, ++i, j);
			}
		}

		if (i + 1 != end) {
			swap(arr, i + 1, end);
		}

		return i + 1;
	}

	public static void swap(int[] arr, int idx1, int idx2) {
		int temp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = temp;
		cnt--;
		if (cnt == 0) {
			result[0] = arr[idx1];
			result[1] = arr[idx2];
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		cnt = Integer.parseInt(st.nextToken());

		int[] quickArr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			quickArr[i] = Integer.parseInt(st.nextToken());
		}
		
		quickSort(quickArr, 0, N - 1);

		if (result[0] == 0) {
			System.out.println(-1);
		} else {
			System.out.println(result[0] + " " + result[1]);
		}

	}
}
