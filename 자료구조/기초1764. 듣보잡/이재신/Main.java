import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Map<String, Integer> map = new HashMap<>();
		List<String> list = new ArrayList<>();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int cnt = 0;
		
		for(int i = 0; i < N; i++) {
			map.put(br.readLine(), 1);
		}
		
		for(int i = 0; i < M; i++) {
			String str = br.readLine();
			if(map.containsKey(str)) {
				list.add(str);
				cnt++;
			}
		}
		
		Collections.sort(list);
		
		bw.write(cnt+ "\n");
		for(int i = 0; i < list.size(); i++) {
			bw.write(list.get(i) + "\n");
		}		
		bw.flush();
		bw.close();
		
	}
}
