import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] num;
	static boolean[] select;
	static int k;

	public static void lotto(int depth, int idx) {
		if (depth == k) {
			if (idx == 6) {
				for (int i = 0; i < k; i++) {
					if(select[i])
						System.out.print(num[i] + " ");
				}
				System.out.println();
			}
			return;
		}

		select[depth] = true;
		lotto(depth + 1, idx + 1);
		select[depth] = false;
		lotto(depth + 1, idx);
	}

	public static void main(String[] args) throws IOException {
		StringTokenizer st;

		while(true) {
			String temp = br.readLine();
			if(temp.equals("0")) break;
			
			st = new StringTokenizer(temp);
			k = Integer.parseInt(st.nextToken());
			
			num = new int[k];
			select = new boolean[k];

			for (int i = 0; i < k; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}

			lotto(0, 0);
			System.out.println();
		}
	}
}
