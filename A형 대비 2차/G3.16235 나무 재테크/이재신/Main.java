import java.io.*;
import java.util.*;

// 메모리 296600 KB, 시간 752 ms
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int N, M, K;
	static int[][] add, soil;
	static int[] adjR = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] adjC = { -1, 0, 1, -1, 1, -1, 0, 1 };
	static Deque<Tree> treeList = new LinkedList<>();
	static Queue<Tree> dieList, tempList;

	// 봄 : 양분 섭취 및 나이 증가
	static void spring() {
		for (int i = 0; i < treeList.size();) {
			// 나무는 해당 칸에 있는 양분만 먹음
			// 하나의 칸에 나무가 여러 그루 있다면, 나이가 어린 순
			Tree cur = treeList.poll();
			// 자신의 나이만큼 양분을 먹고, 나이 1증가
			if (soil[cur.r][cur.c] >= cur.age) {
				soil[cur.r][cur.c] -= cur.age;
				cur.age++;
				i++;
				treeList.add(cur);
			} else {
				// 만약 땅에 양분이 부족하면 양분 못 먹고 즉사
				dieList.add(cur);
			}
		}
	}

	// 여름 : 봄에 죽은 나무가 양분화
	static void summer() {
		for (Tree t : dieList) {
			// 죽은 나무마다 나이 / 2 값 양분 추가(소숫점 버림)
			soil[t.r][t.c] += t.age / 2;
		}
	}

	// 가을 : 나무 번식
	static void autumn() {
		tempList = new LinkedList<>();
		// 나이가 5의 배수이면
		for (Tree t : treeList) {
			if (t.age % 5 == 0) {
				tempList.add(t);
			}
		}
		while (!tempList.isEmpty()) {
			Tree t = tempList.poll();
			// 인접 8개의 칸에 나이가 1인 나무 생성
			for (int i = 0; i < 8; i++) {
				int nR = t.r + adjR[i];
				int nC = t.c + adjC[i];
				if (index(nR, nC)) {
					treeList.addFirst(new Tree(nR, nC, 1));
				}
			}
		}
	}

	// 겨울 : 각 칸에 add 만큼 양분 추가
	static void winter() {
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				soil[i][j] += add[i][j];
			}
		}
	}

	static void solution() {
		for (int i = 0; i < K; i++) {
			dieList = new LinkedList<>();
			spring();
			summer();
			autumn();
			winter();
		}
		System.out.println(treeList.size());
	}

	static void make() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = init(st);
		M = init(st);
		K = init(st);

		add = new int[N + 1][N + 1];
		soil = new int[N + 1][N + 1]; // 양분

		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N + 1; j++) {
				add[i][j] = init(st);
				soil[i][j] = 5;
			}
		}
		// 나무 리스트에 추가
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			// new Tree(r, c, age)
			treeList.add(new Tree(init(st), init(st), init(st)));
		}
	}

	public static void main(String[] args) throws Exception {
		make();
		solution();
	}

	static class Tree implements Comparable<Tree> {
		int r, c, age;

		public Tree(int r, int c, int age) {
			this.r = r;
			this.c = c;
			this.age = age;
		}

		@Override
		public int compareTo(Tree t) {
			return this.age - t.age;
		}
	}

	static boolean index(int r, int c) {
		if (r > 0 && r <= N && c > 0 && c <= N) {
			return true;
		}
		return false;
	}

	static int init() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	static int init(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}
