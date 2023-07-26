import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//	배열의 길이는 문자의 개수
	static char[] character;
//	 배열의 길이는 문자의 개수
	static boolean[] choice;
//	 문자의 개수
	static int nWord;
//	 암호의 길이
	static int pLength;

//	암호의 기준 
//	1. 최소 한개의 모음을 포함(a, e, i, o, u) 
//	2. 최소 두개의 자음을 포함 
//	3. 암호의 순서는 오름차순 정렬
	//gather(모음), consonant(자음)
	public static void password(int depth, int idx, int consonant, int gather) {
		if (depth == nWord) {
			if (idx == pLength && consonant >= 2 && gather >= 1) {
				for (int i = 0; i < nWord; i++) {
					if (choice[i])
						System.out.print(character[i]);
				}
				System.out.println();
			}
			return;
		}
		
		if(character[depth] == 'a' || character[depth] == 'e' || character[depth] == 'i' || character[depth] == 'o' || character[depth] == 'u') {
			choice[depth] = true;
			password(depth + 1, idx + 1, consonant, gather + 1);
			choice[depth] = false;
			password(depth + 1, idx, consonant, gather);
		}else {
			choice[depth] = true;
			password(depth + 1, idx + 1, consonant + 1, gather);
			choice[depth] = false;
			password(depth + 1, idx, consonant, gather);
		}
	}

	public static void main(String[] args) throws IOException {
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		pLength = Integer.parseInt(st.nextToken());
		nWord = Integer.parseInt(st.nextToken());

		character = new char[nWord];
		choice = new boolean[nWord];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < nWord; i++) {
			character[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(character);
				
		password(0, 0, 0, 0);
		System.out.println();

	}
}
