import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

class Lump {
	int num1, num2; // 내가 고른 두 수
	int sum; // 두 수의 합

	public Lump(int num1, int num2, int sum) {
		this.num1 = num1;
		this.num2 = num2;
		this.sum = sum;
	}

}

class Main {

	static int N, min;
	static int[] arr; // 입력값 받을 배열
	static List<Lump> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		min = Integer.MAX_VALUE;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 클래스에 첫번째 수, 두번째 수, 두개의 합 넣고 list에 담아
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				list.add(new Lump(i, j, arr[i] + arr[j]));
			}
		}

		// 합이 작은 순부터 정렬 ( 왜why? 나는 두 합의 차가 작은 것을 원하니까 정렬하고 앞뒤로 뺄거임)
		Collections.sort(list, new Comparator<Lump>() {

			@Override
			public int compare(Lump o1, Lump o2) {
				return o1.sum - o2.sum;
			}
		});

		// list 에서 순서대로 뽑아서
		for (int i = 0; i < list.size() - 1; i++) {
			Lump lump1 = list.get(i);
			Lump lump2 = list.get(i + 1);

			// Lump에 들어가 있는 숫자가 중복으로 들어가 있는지 확인
			if (lump1.num1 == lump2.num1 || lump1.num1 == lump2.num2 || lump1.num2 == lump2.num1
					|| lump1.num2 == lump2.num2) {
				continue;
			}

			// 중복이 없는 것이 확인 됐으면 최소값 구해
			min = Math.min(min, lump2.sum - lump1.sum);
		}

		System.out.println(min);
	}
}
