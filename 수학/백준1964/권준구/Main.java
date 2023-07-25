import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bf.readLine());

		long sum = 5;
		long x = 7;

		for (int i = 2; i < N; i++) {
			sum += x;
			x += 3;
		}

		System.out.println(sum % 45678);
	}
}
