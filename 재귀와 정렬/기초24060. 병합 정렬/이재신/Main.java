import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] sort;
	static int cnt;
	static int result = 0;

	public static void swap(int[] arr, int small, int big) {
		int temp = arr[big];
		arr[big] = arr[small];
		arr[small] = temp;
	}

	public static void mergeSort(int[] arr, int start, int end) {
		if (start < end) {
			int mid = (start + end) / 2;
			mergeSort(arr, start, mid);
			mergeSort(arr, mid + 1, end);
			merge(arr, start, mid, end);
		}
	}

	public static void merge(int[] arr, int start, int mid, int end) {
		int i = start;
		int j = mid + 1;
		int t = 0;

		while (i <= mid && j <= end) {
			if (arr[i] <= arr[j]) {
				sort[t++] = arr[i++];
			} else {
				sort[t++] = arr[j++];
			}
		}

		while (i <= mid) {
			sort[t++] = arr[i++];
		}

		while (j <= end) {
			sort[t++] = arr[j++];
		}

		i = start;
		t = 0;

		while (i <= end) {
			arr[i++] = sort[t++];
			cnt--;
			if (cnt == 0) {
				result = sort[t - 1];
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		cnt = Integer.parseInt(st.nextToken());

		int[] mergeArr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			mergeArr[i] = Integer.parseInt(st.nextToken());
		}

		sort = new int[N];

		mergeSort(mergeArr, 0, N - 1);

		if(result==0) {
			System.out.println(-1);
		} else {
			System.out.println(result);
		}

	}
}
