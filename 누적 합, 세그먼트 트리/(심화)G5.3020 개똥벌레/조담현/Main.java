import java.io.*;

public class Main {

	static int N, H;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]); // 장애물 개수
		H = Integer.parseInt(input[1]); // 높이

		int[] bot = new int[H + 2]; // 높이 + 바닥 + 천장
		int[] top = new int[H + 2]; // 높이 + 바닥 + 천장

		for (int i = 0; i < N / 2; i++) {
			bot[Integer.parseInt(br.readLine())]++;
			top[H - Integer.parseInt(br.readLine()) + 1]++;
		} // 석순과 종유석의 높이에 해당하는 개수를 각각 저장

		for (int i = 1; i <= H; i++) {
			bot[i] += bot[i - 1];
		} // 누적합으로 해당 높이에 해당하는 장애물 개수 저장

		for (int i = H; i >= 1; i--) {
			top[i] += top[i + 1];
		} // 누적합으로 해당 높이에 해당하는 장애물 개수 저장

		int min = N;
		int cnt = 0;

		// 1부터 H까지의 높이로 이동해보기
		for (int i = 1; i <= H; i++) {
			int obs = (bot[H] - bot[i - 1]) + (top[1] - top[i + 1]); // 최대 장애물 개수 - 이전 장애물 개수 = 현재 높이에서 지나가야하는 장애물 개수

			if (obs < min) { // 최소 장애물이 지나가는 구간이면 업데이트
				min = obs;
				cnt = 1;
			} else if (obs == min) // 같은 장애물 개수면 cnt 증가
				cnt++;
		}

		bw.write(min + " " + cnt + "\n");
		bw.flush();

	}

}
