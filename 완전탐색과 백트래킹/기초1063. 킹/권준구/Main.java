import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dr = { -1, 1, 0, 0, 1, 1, -1, -1 };
	static int[] dc = { 0, 0, 1, -1, -1, 1, 1, -1 };
	static int kingR, kingC, stoneR, stoneC;
	static int[][] arr;

	public static void move(int index) {
		int nr = kingR + dr[index];
		int nc = kingC + dc[index];

		int nrStone = stoneR + dr[index];
		int ncStone = stoneC + dc[index];

		if (nr >= 0 && nr <= arr.length && nc >= 0 && nc > arr[0].length) { // 배열이 넘어가는지 확인
			if (nr == stoneR && nc == stoneC) { // 만약 돌과 킹의 자리가 같다면
				if (nrStone >= 0 && nrStone <= arr.length && ncStone >= 0 && ncStone > arr[0].length) { // 돌의 위치를 옮긴다.
					stoneR = nrStone;
					stoneC = ncStone;
					kingR = nr;
					kingC = nc;
				}
			} else {
				kingR = nr;
				kingC = nc;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[8][8]; // 체스판

		String king = st.nextToken();
		String stone = st.nextToken();

		kingR = king.charAt(1) - '0';
		kingC = king.charAt(0) - 'A' + 1;

		stoneR = stone.charAt(1) - '0';
		stoneC = stone.charAt(0) - 'A' + 1;

		int N = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) { // 방향 담기
			String direction = br.readLine();
			int index = -1;
			if (direction.equals("L")) {
				index = 0;
			} else if (direction.equals("R")) {
				index = 1;
			} else if (direction.equals("B")) {
				index = 2;
			} else if (direction.equals("T")) {
				index = 3;
			} else if (direction.equals("RT")) {
				index = 4;
			} else if (direction.equals("RB")) {
				index = 5;
			} else if (direction.equals("LB")) {
				index = 6;
			} else if (direction.equals("LT")) {
				index = 7;
			}

			move(index);
		}
		char ky = (char) ('A' + kingC - 1);
		char sy = (char) ('A' + stoneC - 1);
		System.out.println(ky + "" + kingR + " " + sy + stoneR);
	}

}
