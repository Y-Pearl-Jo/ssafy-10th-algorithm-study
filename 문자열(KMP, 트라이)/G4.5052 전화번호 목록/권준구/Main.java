import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Baek_5052_전화번호목록 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<String> set;
        
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine());

            set = new HashSet<>();
            String[] arr = new String[n];
            boolean flag = true;

            for (int i = 0; i < n; i++) {
                String str = br.readLine();
                arr[i] = str;
                set.add(str);
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < arr[i].length(); j++) {
                	//문자열이 존재한다면
                    if (set.contains(arr[i].substring(0, j))) {
                        flag = false;
                        break;
                    }
                }
            }

            if (flag == true)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}
