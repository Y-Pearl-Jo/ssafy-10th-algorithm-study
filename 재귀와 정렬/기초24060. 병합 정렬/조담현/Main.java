import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int count = 0;
	static int n;
	static int k;
	static int [] tmp;
	static int [] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		arr = new int[n];
		tmp = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			arr[i] = num;
		}

		mergeSort(arr, 0, arr.length - 1);

		if(count<k) {
			System.out.println(-1);
		}
	}

	public static int[] mergeSort(int[] arr, int p, int r) {

		if (p < r) {
			int q = (int) (Math.floor((p+r)/2.0));
			mergeSort(arr, p, q);
			mergeSort(arr, q + 1, r);
			merge(arr,p,q,r);
		}

		return arr;
	}

	public static void merge(int[] arr, int p, int q, int r) {
		// 여기에 tmp 배열을 선언했더니 시간 초과
		int i = p;
		int j = q+1;
		int t = 0;
		while(i<=q && j<=r) {
			if(arr[i]<=arr[j]) {
				tmp[t++] = arr[i++];
			} else {
				tmp[t++] = arr[j++];
			}
			count++;
			if(count == k) {
				System.out.println(tmp[t-1]);
			}
		}
		
		while(i<=q) {
			tmp[t++] = arr[i++];
			count++;
			if(count == k) {
				System.out.println(tmp[t-1]);
			}
		}
		
		while(j<=r) {
			tmp[t++] = arr[j++];
			count++;
			if(count == k) {
				System.out.println(tmp[t-1]);
			}
		}
		
		i = p; t = 0;
		while(i<=r) {
			arr[i++] = tmp[t++];

		}
		
	}

}
