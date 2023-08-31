/* 
 * 1. boolean 배열로 K+1크기의 배열을 만들어 K번 전자기기가 켜져있는 지 체크한다.
 * 2. K번 for문을 돌며
 * 2-1. 만약 현재 켜져있는 플러그 수가 N개 미만이라면
 * 1. 이미 켜져있는(true인) 전자기기는 count하지 않고 지나간다. 
 * 2. 만약 켜져있지 않은(false인) 전자기기는 해당 인덱스를 true로 만들고 현재 켜져있는 개수를 +1 count한다.
 * 2-2. 현재 켜져있는 개수가 N개인데, i번째에서 추가로 4번의 상황에서 켜져있지 않은 전자기기가 들어온다면 
 * 1.  arraylist의 인덱스중  boolean 배열에서 켜져있는 전자기기들을 확인하여 
 * 2. 현재 켜져있는 전자기기 중 다시 차례가 돌아오지 않거나, 가장 늦게 차례가 돌아오는 전자기기를 뽑아 boolean을 false로, 현재 전자기기를 true로 하고 플러그를 빼는 횟수를 +1 한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		boolean[] plugOn = new boolean[K+1];
		int[] arr = new int[K+1];
 		for(int i=1; i<=K; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
 		int ans = 0; // 플러그를 빼는 최소 횟수
 		int plugOnCount = 0; // 현재 꼽혀있는 플러그 수
 		ArrayList<Integer> onList = new ArrayList<>(); // 현재 켜져있는 전자기기 리스트
 		for(int i=1; i<=K; i++) {
 			int name = arr[i]; // 현재 꽂힌 전자기기의 번호
 			// 이미 꽂혀있는 플러그거나, 플러그가 여유있다면 꽂고, 꽂혀있는 여부를 true로 한다. 이후 꽂혀있는 전자기기 List에 추가해준다.
 			if(plugOn[name]==true) continue;
 			if(plugOnCount<N) {
 				plugOn[name] = true;
 				onList.add(name);
 				plugOnCount++;
 			} 
 			// 플러그가 부족한데 꽂아야 한다면
 			else {
 				// 켜져있는 전자기기의 첫 등장 인덱스를 확인했는지 여부를 확인할 변수 count
 				int count = 0;
 				
 				// 전자기기 번호 : 처음 등장하는 인덱스 저장할 해쉬맵
 				HashMap<Integer, Integer> map = new HashMap<>();
 				for(int j=0; j<onList.size(); j++) {
 					map.put(onList.get(j), 101); // 켜져있는 전자기기 번호, 첫 등장하게 되는 인덱스 초기화. 등장하지 않으면 101이라는 값을 가진다.
 				}
 				for(int j=i+1; j<=K; j++) { 
 					if(count == onList.size()) break; // 켜져있는 모든 전자기기의 첫 등장 인덱스를 확인했다면 break;
 					int now = arr[j]; // 지금 확인하는 전자기기 번호
 					// 켜져있는 전자기기이면서 첫번째 인덱스를 체크하지 않았다면 체크.
 					if(onList.contains(now) && map.get(now) == 101) {
 						map.put(now, j);
 						count++;
 					}
 				}
 				//  밸류값(다시 등장하는) 기준으로 내림차순 정렬하여 가장 최댓값인(늦게 사용하는 값인) 전자기기를 뽑고, 새 전자기기를 꼽는다.
 				List<Entry<Integer, Integer>> entryList = new LinkedList<>(map.entrySet());
 				entryList.sort(Entry.comparingByValue(Collections.reverseOrder()));
 				int off = entryList.get(0).getKey(); // 뽑아야할 전자기기의 번호
 				plugOn[off] = false;
 				onList.remove((Integer)off);
 				plugOn[name] = true;
 				onList.add(name);
 				ans++;
 			}
 		}
		System.out.println(ans);
	} 

}
