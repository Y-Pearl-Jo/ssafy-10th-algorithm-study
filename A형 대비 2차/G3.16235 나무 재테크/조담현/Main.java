import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;

class Soil {
	int nutrient;
	ArrayList<Tree> aliveTrees;
	ArrayList<Tree> deadTrees;

	Soil(int nutrient) {
		this.nutrient = nutrient;
		aliveTrees = new ArrayList<>();
		deadTrees = new ArrayList<>();
	}
}

class Tree implements Comparable {
	boolean isAlive;
	int age;

	Tree(int age) {
		this.age = age;
	}

	@Override
	public int compareTo(Object o) {
		Tree t = (Tree) o;
		return this.age - t.age;
	}

}

public class Main {
	static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };
	static int N, M, K;
	static Soil[][] land;
	static int[][] A;
	static int total;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		land = new Soil[N][N];
		A = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				land[i][j] = new Soil(5);
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			Tree tree = new Tree(age);
			land[row - 1][col - 1].aliveTrees.add(tree);
		}

		for (int i = 0; i < K; i++) {
			spring();
			summer();
			fall();
			winter();
		}

		total = findAliveTrees();

		System.out.println(total);
	}

	private static int findAliveTrees() {
		int aliveTree = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Soil soil = land[i][j];
				aliveTree += soil.aliveTrees.size();
			}
		}
		return aliveTree;
	}

	private static void spring() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Soil soil = land[i][j];
				Collections.sort(soil.aliveTrees);
				Iterator<Tree> iter = soil.aliveTrees.iterator();
				while (iter.hasNext()) {
					Tree t = iter.next();
					if (t.age <= soil.nutrient) {
						soil.nutrient -= t.age;
						t.age++;
					} else {
						soil.deadTrees.add(t);
						iter.remove();
					}
				}
			}
		}
	}

	private static void summer() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Soil soil = land[i][j];
				Iterator<Tree> iter = soil.deadTrees.iterator();
				while (iter.hasNext()) {
					Tree t = iter.next();
					soil.nutrient += (t.age / 2);
					iter.remove();
				}
			}
		}
	}

	private static void fall() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Soil soil = land[i][j];
				for (Tree t : soil.aliveTrees) {
					if (t.age % 5 == 0) {
						for (int d = 0; d < 8; d++) {
							int nr = i + dr[d];
							int nc = j + dc[d];
							if (nr >= 0 && nc >= 0 && nr < N & nc < N) {
								land[nr][nc].aliveTrees.add(new Tree(1));
							}
						}
					}
				}
			}
		}
	}

	private static void winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Soil soil = land[i][j];
				soil.nutrient += A[i][j];
			}
		}
	}

}
