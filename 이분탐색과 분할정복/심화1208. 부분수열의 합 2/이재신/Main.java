import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, S;
	static int[] num;
	static List<Integer> lSumList = new ArrayList<>();
	static List<Integer> rSumList = new ArrayList<>();
	static long count = 0;

	static void cntSum() {
		int left = 0;
		int right = rSumList.size() - 1;
		
		//투 포인터 종료 조건
		while (left < lSumList.size() && right >= 0) {
			int l = lSumList.get(left);
			int r = rSumList.get(right);
			// 합이 원하는 수일 경우
			if (l + r == S) {
				// 우측 부분수열 리스트에 같은 값이 있는지 확인
				long rcnt = 0;
				while (right >= 0 && rSumList.get(right) == r) {
					right--;
					rcnt++;
				}
				// 좌측 부분수열 리스트에 같은 값이 있는지 확인
				long lcnt = 0;
				while (left < lSumList.size() && lSumList.get(left) == l) {
					left++;
					lcnt++;
				}
				// 좌 우측에 중복되는 값의 곱 만큼 경우의 수 가능
				count += lcnt * rcnt;
			} 
			// 합이 작을 경우 left 증가 시켜 합 증가
			else if (l + r < S)
				left++;
			// 합이 클 경우 right 감소 시켜 합 감소
			else if (l + r > S)
				right--;
		}

	}

	static void makeList(int depth, int end, int sum, List<Integer> sumList) {
		if (depth == end) {
			sumList.add(sum);
			return;
		}

		makeList(depth + 1, end, sum + num[depth], sumList);
		makeList(depth + 1, end, sum, sumList);
	}

	static void solution() {
		//이분 탐색을 위한 좌 우측 부분 수열 리스트 생성
		makeList(0, (N - 1) / 2, 0, lSumList);
		makeList((N - 1) / 2, N, 0, rSumList);
		Collections.sort(lSumList);
		Collections.sort(rSumList);
		cntSum();
		//원하는 합이 0 인경우 좌 우측에서 아무것도 안고른 경우의 수를 제거하기 위해 1만큼 감소
		System.out.println(S == 0 ? count - 1 : count);

	}

	static void make() throws IOException {
		st = new StringTokenizer(br.readLine());
		// 입력 값 할당
		N = init(st.nextToken());
		S = init(st.nextToken());

		// N개의 요소를 가진 배열 생성
		num = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = init(st.nextToken());
		}
	}

	public static void main(String[] args) throws IOException {
		make();
		solution();
	}

	static int init(String str) {
		return Integer.parseInt(str);
	}
}
