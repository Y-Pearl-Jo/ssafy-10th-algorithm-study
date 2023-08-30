import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K;
	static LinkedList<Integer> seq = new LinkedList<>();
	static int[] elec = new int[101];
	static ArrayList<Integer> multi = new ArrayList<>();

	static int solution() {
		int cnt = 0;
		while (!seq.isEmpty()) {
			if (multi.contains(seq.get(0))) {
				seq.remove(0);
				continue;
			}
			if (multi.size() < N) {
				multi.add(seq.remove(0));
			} else {
				boolean flag = false;
				// 현재 꽂혀있는 콘센트 중에 더이상 나오지 않는게 있는 경우
				for (int i = 0; i < multi.size(); i++) {
					if (!seq.contains(multi.get(i))) {
						multi.remove(i);
						flag = true;
						break;
					}
				}
				if (!flag) {
					// 모두 다시 등장 하는 경우
					ArrayList<Integer> temp = new ArrayList<>();
					for (int i = 1; i < seq.size(); i++) {
						if (multi.contains(seq.get(i)) && !temp.contains(seq.get(i))) {
							temp.add(seq.get(i));
						}
					}
					multi.remove(multi.indexOf(temp.get(temp.size() - 1)));
				}
				cnt++;
				multi.add(seq.remove(0));
			}
		}
		return cnt;
	}

	static void make() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = init(st.nextToken());
		K = init(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			seq.add(init(st.nextToken()));
		}
	}

	public static void main(String[] args) throws IOException {
		make();
		System.out.println(solution());
	}

	static int init(String str) {
		return Integer.parseInt(str);
	}
}
