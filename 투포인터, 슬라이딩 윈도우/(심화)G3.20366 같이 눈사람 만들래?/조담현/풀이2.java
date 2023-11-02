/*
만들 수 있는 눈사람의 모든 조합을 (선택한 눈덩이의 인덱스와 함께)저장한 후, 정렬한다.
눈사람의 최소 높이 차는 인접한 눈사람의 높이 차이므로, 가장 인접한 눈사람이 같은 인덱스(눈덩이)가 아닌 경우를 확인하며 최솟값을 업데이트 한다.
그렇다면 시간복잡도는 O(N^2+NC2+NC2logNC2)이다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class Snowman implements Comparable {
	int snow1;
	int snow2;
	int size;

	Snowman(int snow1, int snow2, int size) {
		this.snow1 = snow1;
		this.snow2 = snow2;
		this.size = size;
	}

	@Override
	public int compareTo(Object o) {
		Snowman s = (Snowman) o;
		return this.size - s.size;
	}
}

public class Main {
	static int N;
	static int[] snow;
	static int minDiff = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		snow = new int[N];
		for (int i = 0; i < N; i++) {
			snow[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(snow);
		Set<Snowman> combinations = new HashSet<>();
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				combinations.add(new Snowman(i, j, snow[i] + snow[j]));
			}
		}
		Snowman[] snowmans = combinations.toArray(new Snowman[0]);
		Arrays.sort(snowmans);
		
		Loop: for (int i = 0; i < snowmans.length; i++) {
			Snowman Elsa = snowmans[i];
			for (int j = i + 1; j < snowmans.length; j++) {
				Snowman Anna = snowmans[j];
				if (Anna.snow1 == Elsa.snow1 || Anna.snow1 == Elsa.snow2 || Anna.snow2 == Elsa.snow1
						|| Anna.snow2 == Elsa.snow2)
					continue;
				else {
					minDiff = Math.min(minDiff, Math.abs(Elsa.size - Anna.size));
					if (minDiff == 0) {
						break Loop;
					}
					break;
				}
			}
		}
		System.out.println(minDiff);
	}
}
