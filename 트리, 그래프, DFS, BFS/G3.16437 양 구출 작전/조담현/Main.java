import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Island {
	String animal;
	long count = 0;
	int dest = 0;
	ArrayList<Integer> chlidren = new ArrayList<>();

}

public class Main {
	static int N;
	static Island[] islands;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		islands = new Island[N + 1];
		for (int i = 1; i <= N; i++) {
			islands[i] = new Island();
		}
		islands[1].animal = "S";
		for (int i = 2; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String animal = st.nextToken();
			long count = Long.parseLong(st.nextToken());
			int destination = Integer.parseInt(st.nextToken());
			islands[i].dest = destination;
			islands[destination].chlidren.add(i);
			if (animal.equals("S")) {
				islands[i].animal = "S";
				islands[i].count = count;
			} else {
				islands[i].animal = "W";
				islands[i].count = count;
			}
		}

		System.out.println(moveSheep(islands[1]));
	}

	public static long moveSheep(Island now) {
		long sum = 0;
		
		for(int next : now.chlidren) {
			sum += moveSheep(islands[next]);
		}
		
		
 		if(now.animal.equals("S")) {
			return sum + now.count;
		} else {
			return sum - now.count >= 0 ? sum - now.count : 0;
		}
	}
}
