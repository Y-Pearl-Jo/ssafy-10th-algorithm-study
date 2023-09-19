import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[][] arr = new int[n][n];

		long[][] d = new long [n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		d[0][0] = 1;
    
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (d[i][j] == 0)
					continue;
				if (i==n-1&&j==n-1) break;
				int move = arr[i][j];
				int nr = i + move;
				int nc = j + move;
				if (nr < n) {
					d[nr][j]+=d[i][j];
				}

				if (nc < n) {
					d[i][nc]+=d[i][j];
				}
			}
		}

		System.out.println(d[n - 1][n - 1]);
	}

}
