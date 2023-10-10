import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 꼭지점으로 사각형의 개수를 구하므로 map은 boolean형으로 만들어도 될 듯
// x,y가 100까지 범위를 알려줌 , x좌표y좌표 이므로 map[y][x]로 봐야함
// 격자 밖으로 벗어나지 않는다 > 범위 설정 필요 없을 듯

public class Main {
	static int[] dr = { 0, -1, 0, 1 }; // 방향을 나타내는 배열 (0: →, 1: ↑, 2: ←, 3: ↓)
	static int[] dc = { 1, 0, -1, 0 };

	static boolean[][] map; // 드래곤 커브가 그려진 부분을 표시하기 위한 2차원 배열
	static int x, y, d, g; // x, y: 시작 좌표, d: 시작 방향, g: 드래곤 커브 세대

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		map = new boolean[101][101]; // 지도 배열 초기화 (최대 크기인 101x101)

		int N = Integer.parseInt(br.readLine()); // 드래곤 커브의 개수를 입력 받음

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());

			drawDragonCurve(); // 드래곤 커브를 그리는 함수 호출
		}

		int ans = 0; // 꼭짓점으로 이루어진 사각형의 개수를 저장할 변수
		for (int i = 0; i < 100; i++) { // 100x100 크기의 격자에서 확인
			for (int j = 0; j < 100; j++) {
				if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) {
					ans++; // 꼭짓점으로 이루어진 사각형을 발견하면 개수 증가
				}
			}
		}

		System.out.println(ans); // 결과 출력
	}

	// 드래곤 커브를 그리는 함수
	private static void drawDragonCurve() {
		List<Integer> directions = new ArrayList<>(); // 드래곤 커브의 방향 정보를 저장하는 리스트
		directions.add(d); // 시작 방향을 리스트에 추가

		// 드래곤 커브의 세대(g)에 따라 방향 정보를 확장
		for (int i = 0; i < g; i++) {
			for (int j = directions.size() - 1; j >= 0; j--) {
				directions.add((directions.get(j) + 1) % 4); // 이전 세대의 방향 정보를 바탕으로 다음 세대의 방향을 추가
			}
		}

		map[y][x] = true; // 시작 좌표를 드래곤 커브로 채움

		// 방향 정보를 기반으로 드래곤 커브를 그려나감
		for (int i = 0; i < directions.size(); i++) {
			y += dr[directions.get(i)];
			x += dc[directions.get(i)];
			map[y][x] = true; // 현재 위치를 드래곤 커브로 채움
		}
	}
}
