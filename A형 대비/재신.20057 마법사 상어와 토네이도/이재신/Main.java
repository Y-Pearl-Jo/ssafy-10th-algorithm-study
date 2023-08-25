import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] sand;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { -1, 0, 1, 0 };
	static int direc = 0;
	// 최종 결과
	static int out = 0;
	// 이동 횟수
	static int cnt = 1;

	// 역 달팽이 모양
	static void move(int r, int c) {
		while (cnt != N * N) {
			// 역 달팽이 조건에 따른 방향 조정
			if (c >= N / 2 && r + c == N - 1)
				direc = 0;
			else if (c < N / 2 && r - c == 1)
				direc = 1;
			else if (c < N / 2 && r + c == N - 1)
				direc = 2;
			else if (c > N / 2 && r == c)
				direc = 3;
			// 다음 위치로 이동
			r = r + dr[direc];
			c = c + dc[direc];
			split(r, c);
			cnt++;
		}
	}

	// 격자의 범위를 벗어나는 모래의 양을 out에 더하기
	// 일정 비율에 따라 모래의 양 분산하기
	// 알파는 현재 무브 방향으로 한칸 더
	// 모래가 있던 칸에는 쌓임
	static void split(int r, int c) {
		int mount = sand[r][c];
		// 주변에 흩어지는 양
		int one = mount * 1 / 100;
		int two = mount * 2 / 100;
		int five = mount * 5 / 100;
		int seven = mount * 7 / 100;
		int ten = mount / 10;
		// 최종 알파 위치에 쌓일 모래의 양
		int alpha = mount - 2*(one + two + seven + ten) - five;
		if (direc == 0) {
			check(r - 1, c + 1, one);
			check(r + 1, c + 1, one);
			check(r, c - 2, five);
			check(r - 1, c - 1, ten);
			check(r + 1, c - 1, ten);
			check(r, c - 1, alpha);
		} else if (direc == 1) {
			check(r - 1, c - 1, one);
			check(r - 1, c + 1, one);
			check(r + 2, c, five);
			check(r + 1, c - 1, ten);
			check(r + 1, c + 1, ten);
			check(r + 1, c, alpha);
		} else if (direc == 2) {
			check(r - 1, c - 1, one);
			check(r + 1, c - 1, one);
			check(r, c + 2, five);
			check(r - 1, c + 1, ten);
			check(r + 1, c + 1, ten);
			check(r, c + 1, alpha);
		} else if (direc == 3) {
			check(r + 1, c - 1, one);
			check(r + 1, c + 1, one);
			check(r - 2, c, five);
			check(r - 1, c - 1, ten);
			check(r - 1, c + 1, ten);
			check(r - 1, c, alpha);
		}
		//방향 짝홀에 따른 중복 부분
		if(direc % 2 == 0) {
			check(r - 2, c, two);
			check(r + 2, c, two);
			check(r - 1, c, seven);
			check(r + 1, c, seven);
		}
		else if(direc % 2 == 1) {
			check(r, c - 2, two);
			check(r, c + 2, two);
			check(r, c - 1, seven);
			check(r, c + 1, seven);
		}

		sand[r][c] = 0;
	}

	static void check(int r, int c, int mount) {
		if (r < 0 || c < 0 || r >= N || c >= N) {
			out += mount;
		} else {
			sand[r][c] += mount;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = init(br.readLine());
		sand = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				sand[i][j] = init(st.nextToken());
			}
		}

		move(N / 2, N / 2);

		System.out.println(out);
	}

	static int init(String str) {
		return Integer.parseInt(str);
	}

}
