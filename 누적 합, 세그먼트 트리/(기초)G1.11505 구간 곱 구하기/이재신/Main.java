import java.io.*;
import java.util.*;
// 메모리: 116312 KB, 시간: 496 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
    // 숫자가 커지지 않게 줄이는 나머지 변수
	static final int MOD = 1000000007;
	static long[] num, tree;
	static int N, M, K;
    
    // 세그먼트 트리 형태의 배열 생성
    // 이진트리의 형태이기 때문에 배열로도 만들기 가능
	static void makeSegment(int start, int end, int node) {
        // 깊이가 깊어질 때 왼쪽 자식노드는 * 2, 오른쪽 자식노드는 * 2 + 1
		int leftNode = (node << 1);
		int rightNode = (node << 1) + 1;
        // 리프 노드에 도착했음을 의미
		if (start == end)
			tree[node] = num[start];
        // 리프노드가 아닌 경우 좌, 우 자식노드를 재귀 호출 후 결과값 곱하기
		else {
            // 혹시나 터지지 않기 위한 중간값 찾는 법
			int mid = start + (end - start) / 2;
			makeSegment(start, mid, leftNode);
			makeSegment(mid + 1, end, rightNode);

			tree[node] = (tree[leftNode] * tree[rightNode]) % MOD;
		}
	}

    // 찾고자 하는 구간의 곱
	static long findValue(int start, int end, int node, int left, int right) {
        // 깊이가 깊어질 때 왼쪽 자식노드는 * 2, 오른쪽 자식노드는 * 2 + 1
		int leftNode = (node << 1);
		int rightNode = (node << 1) + 1;

        // 원하는 인덱스 구간을 벗어남
		if (left > end || right < start)
			return 1;
        // 해당 노드 값이 구간 안에 완전히 들어오는 경우
		if (left <= start && end <= right) {
			return tree[node];
		}
        // 혹시나 터지지 않기 위한 중간값 찾는 법
		int mid = start + (end - start) / 2;
        // 곱셈이다 보니 숫자가 커질 수 있어 long 자료형
		long leftValue = findValue(start, mid, leftNode, left, right);
		long rightValue = findValue(mid + 1, end, rightNode, left, right);

		return (leftValue * rightValue) % MOD;
	}

	static long update(int start, int end, int node, int idx, long val) {
	    // 깊이가 깊어질 때 왼쪽 자식노드는 * 2, 오른쪽 자식노드는 * 2 + 1    
        int leftNode = (node << 1);
		int rightNode = (node << 1) + 1;
        
        // 인덱스를 벗어난 경우 원래의 값 반환
		if (idx < start || idx > end) {
			return tree[node];
		}
        // 리프 노드에 도착한 경우 변경 실시
		if (start == end) {
			return tree[node] = val;
		}
        // 혹시나 터지지 않기 위한 중간값 찾는 법
		int mid = (start + end) / 2;
        // 곱셈이다 보니 숫자가 커질 수 있어 long 자료형
		long left = update(start, mid, leftNode, idx, val);
		long right = update(mid + 1, end, rightNode, idx, val);
        // 이번 노드의 값을 초기화 하면서 반환
		return tree[node] = (left * right) % MOD;
	}

	public static void solution() throws IOException {
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
            // a : 메서드 종류
            // (b, c) : a == 1 ? (b번째 수, c로 변경) : (b부터, c까지 곱)
			int a = init(st);
			int b = init(st);
			int c = init(st);
            // a가 1이면 수정(U)
			if (a == 1) {
				update(1, N, 1, b, c);
			}
            // a가 2이면 조회(R)
            else if (a == 2) {
				sb.append(findValue(1, N, 1, b, c)).append("\n");
			}
		}

		bw.write(sb.toString());
		bw.flush();
	}

	public static void make() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = init(st);
		M = init(st);
		K = init(st);

		num = new long[N + 1];
		for (int i = 1; i <= N; i++) {
			num[i] = init();
		}

		tree = new long[N * 4];

		makeSegment(1, N, 1);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		make();
		solution();
	}

	public static int init() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	public static int init(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}

}
