import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] nLotto;
	static boolean[] choice;
	static int k;

	public static void lotto(int depth, int idx) {
		if (depth == k) {
			if (idx == 6) {
				for (int i = 0; i < k; i++) {
					if(choice[i])
						System.out.print(nLotto[i] + " ");
				}
				System.out.println();
			}
			return;
		}

		choice[depth] = true;
		lotto(depth + 1, idx + 1);
		choice[depth] = false;
		lotto(depth + 1, idx);
	}

	public static void main(String[] args) throws IOException {
		StringTokenizer st;

		while(true) {
			String temp = br.readLine();
			if(temp.equals("0")) break;
			
			st = new StringTokenizer(temp);
			k = Integer.parseInt(st.nextToken());
			
			nLotto = new int[k];
			choice = new boolean[k];

			for (int i = 0; i < k; i++) {
				nLotto[i] = Integer.parseInt(st.nextToken());
			}

			lotto(0, 0);
			System.out.println();
		}
	}
}
