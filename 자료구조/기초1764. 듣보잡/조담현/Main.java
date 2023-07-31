
//	  중복값만 남기기 이부분이 500000x500000연산 
//		list1.retainAll(list2); 
// list 두개 만들어서 retainAll()으로 연산하면 속도가 size()*size()
// contains 메소드들은 속도가 O(1)



import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		HashMap<String, Integer> hashmap = new HashMap<>();
		
		// 듣도 못한 사람 n명 저장
		for(int i=0; i<n; i++) {
			String str = br.readLine();			
			hashmap.put(str, 1);

		}
		
		List<String> list = new ArrayList<>();
		
		// 보도 못한 사람 m명 저장하며 키값이 있는지 찾기
		for(int i=0; i<m; i++) {
			String str = br.readLine();
			if(hashmap.containsKey(str)) {
				list.add(str);
			} 
		}
		
		// 정렬
		Collections.sort(list);
		
		StringBuilder sb = new StringBuilder();
		System.out.println(list.size());
		for(String s : list) {
			sb.append(s+"\n");
		}
		bw.write(sb.toString());
		bw.close();
	}

}
