//30388KB	460ms

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	static int N; // 학생 수
	static int[][] map; // 학생의 자리를 저장하는 배열
	static int[] dx = { -1, 0, 1, 0 }; // 상우하좌
	static int[] dy = { 0, 1, 0, -1 };
	static HashMap<Integer, Integer[]> hashMap = new HashMap<>(); // 해시맵에 좋아하는 친구 저장
	static int sum; // 만족도의 합

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		// 입력
		for (int i = 0; i < N * N; i++) {
			st = new StringTokenizer(br.readLine());
			int student = Integer.parseInt(st.nextToken());
			int friend1 = Integer.parseInt(st.nextToken());
			int friend2 = Integer.parseInt(st.nextToken());
			int friend3 = Integer.parseInt(st.nextToken());
			int friend4 = Integer.parseInt(st.nextToken());

			// 해시맵에 현재 학생이 좋아하는 친구들의 정보를 저장
			hashMap.put(student, new Integer[] { friend1, friend2, friend3, friend4 });

			// 현재 학생의 자리를 정해주는 메소드
			findPosition(student);

		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}

		// 만족도의 합 초기화
		sum = 0;
		// 총 만족도를 계산하는 메소드
		calculate();

		// 출력
		System.out.println(sum);

	}

	// 현재 학생의 자리를 정해주는 메소드
	private static void findPosition(int student) {

		// 해시맵을 통해서 현재 학생이 좋아하는 친구 정보를 불러옴
		Integer friends[] = hashMap.get(student);
		int f1 = friends[0];
		int f2 = friends[1];
		int f3 = friends[2];
		int f4 = friends[3];

		// 좋아하는 친구의 수, 빈 자리의 수, 행, 열 정보를 리스트에 저장해서 정렬할 것임
		ArrayList<Integer[]> list = new ArrayList<>();

		// 행렬을 순회하면서 각 위치마다 좋아하는 친구의 수와 빈 공간을 카운트 함
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int friendsCount = 0;
				int emptyCount = 0;

				// 4방탐색
				for (int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];

					if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
						continue;
					}

					int node = map[nx][ny];

					// 현재 위치 주변에 좋아하는 친구가 있다면 카운트 증가
					if (node == f1 || node == f2 || node == f3 || node == f4) {
						friendsCount++;
					}
					// 현재 위치 주변에 빈 공간이 있다면 카운트 증가
					if (node == 0) {
						emptyCount++;
					}
				}

				// 리스트에 저장
				list.add(new Integer[] { friendsCount, emptyCount, i, j });

			}
		}

		// 리스트에 저장한 정보를 바탕으로 우선순위대로 정렬
		Collections.sort(list, (o1, o2) -> {
		    if (o1[0] < o2[0])
		        return 1;
		    else if (o1[0] == o2[0]) {
		        if (o1[1] < o2[1])
		            return 1;
		        else if (o1[1] == o2[1]) {
		            if (o1[2] > o2[2])
		                return 1;
		            else if (o1[2] == o2[2]) {
		                if (o1[3] > o2[3])
		                    return 1;
		            }
		        }
		    }
		    return -1;
		});

		// 우선순위가 높은 순으로 그 자리가 빈공간인지 확인.
		for (int i = 0; i < list.size(); i++) {
			int x = list.get(i)[2]; // 행 정보
			int y = list.get(i)[3]; // 열 정보

			// 빈공간이라면 거기가 바로 현재 학생의 자리다!
			if (map[x][y] == 0) {
				map[x][y] = student;
				return;
			}
		}

	}

	// 총 만족도를 계산하는 메소드
	private static void calculate() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				int count = 0; // 인접한 칸에 앉은 좋아하는 학생의 수
				int student = map[i][j];
				Integer friends[] = hashMap.get(student);

				if (friends != null) { // 좋아하는 친구 정보가 있는 경우에만
					// 4방 탐색해서
					for (int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];

						if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
							continue;
						}

						// 탐색한 위치의 값이 현재 node값의 학생이 좋아하는 친구들의 정보와 일치한다면
						int node = map[nx][ny];
						for (int k = 0; k < 4; k++) {
							if (node == friends[k])
								count++;
						}
					}
				}

				if (count == 1) {
					sum += 1;
				} else if (count == 2) {
					sum += 10;
				} else if (count == 3) {
					sum += 100;
				} else if (count == 4) {
					sum += 1000;
				}

			}
		}
	}

}
