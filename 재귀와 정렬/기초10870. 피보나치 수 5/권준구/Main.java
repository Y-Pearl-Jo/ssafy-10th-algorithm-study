package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		System.out.println(Fibonacci(N));
	}

	public static int Fibonacci(int N) {
		if (N == 0)
			return 0;
		if (N <= 1)
			return 1;
		return Fibonacci(N - 2) + Fibonacci(N - 1);
	}
}
