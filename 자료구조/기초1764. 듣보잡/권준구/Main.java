/*

후기 : 단순 이중포문으로 작성한 결과 시간초과로 해결하지 못함

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<String> list = new ArrayList<>();
		String[] arr = new String[N + M];

		for (int i = 0; i < N + M; i++) {
			arr[i] = br.readLine();
		}

		// 0과 3을 구분을 할 것이다. N=3, M=4
		for (int i = 0; i < N; i++) {
			for (int j = N; j < arr.length; j++) {
				if (arr[i].equals(arr[j])) {
					list.add(arr[i]);
				}
			}
		}
		Collections.sort(list);
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}

*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// N : 듣못사
		int N = Integer.parseInt(st.nextToken());
		// M : 보못사
		int M = Integer.parseInt(st.nextToken());

		// 듣보잡을 저장하는 list
		List<String> list = new ArrayList<>();
		// list말고 Hashset을 사용한 이유 : 순서를 구별하지 않기 때문에 더 빠름
		HashSet<String> set = new HashSet<>();

		// 듣못사 set에 저장
		for (int i = 0; i < N; i++) {
			set.add(br.readLine());
		}

		// 보못사 입력받고 바로 듣못사와 비교
		for (int i = 0; i < M; i++) {
			String dms = br.readLine();
			if (set.contains(dms)) {
				list.add(dms);
			}
		}

		// 사전순으로 나열
		Collections.sort(list);

		System.out.println(list.size());

		for (String s : list) {
			System.out.println(s);
		}

	}
}
