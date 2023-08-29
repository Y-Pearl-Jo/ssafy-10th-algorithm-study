import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/*
1744. 수묶기
1. 1은 묶지 않고 더한다
2. -1 이하의 값들은 작은수부터 두개씩 묶는다. 
2-1. 가장 작은 음수가 1개 남았을 때, 0이 있었다면 곱해준다. ( = 무시하고 넘어갈 수 있다 )
3. 2이상의 값들은 큰수부터 두개씩 묶는다
3-1. 가장 작은 양수가 1개 남았을 때, 더해준다.
*/ 
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> mList = new ArrayList<Integer>();
		ArrayList<Integer> pList = new ArrayList<Integer>();
		boolean isZero = false;
		int sum = 0;
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			// 음수면 음수 리스트에 추가
			if (num < 0) {
				mList.add(num);
			} 
			// 1이상의 양수면 양수 리스트에 추가
			else if (num > 1) {
				pList.add(num);
			} 
			// 0이 나왔는지 여부만 체크
			else if (num == 0) {
				isZero = true;
			} 
			// 1이면 묶지 않고 더해준다.
			else {
				sum += num;
			}

		}
		
		// -1 이하의 값들은 작은 수부터 두개씩 묶는다.
		Collections.sort(mList);
		while (mList.size() > 1) {
			int first = mList.remove(0);
			int second = mList.remove(0);
			sum += first * second;
		}
		//가장 작은 음수가 1개 남았을 때, 0이 있었다면 곱해준다. ( = 0이 없었다면 음수를 더해주어야 한다.)
		if (!isZero && !mList.isEmpty()) {
			sum += mList.get(0);
		}
		
		// 2이상의 값들은 큰 수부터 두개씩 묶는다.
		Collections.sort(pList, Collections.reverseOrder());
		while (pList.size() > 1) {
			int first = pList.remove(0);
			int second = pList.remove(0);
			sum += first * second;
		}
		// 가장 작은 양수가 1개 남았을 때, 더해준다.
		if (!pList.isEmpty()) {
			sum += pList.get(0);
		}

		System.out.println(sum);
	}

}
