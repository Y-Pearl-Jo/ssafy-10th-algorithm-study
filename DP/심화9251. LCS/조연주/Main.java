//16084kb 116ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();

        int[][] arr = new int[str1.length()+1][str2.length()+1];

        // ?
        for(int i=1; i<=str1.length(); i++){
            for(int k=1; k<=str2.length(); k++){
                if(str1.charAt(i-1)==str2.charAt(k-1)){
                    arr[i][k] = arr[i-1][k-1]+1;
                }
                else{
                    arr[i][k] = Math.max(arr[i][k-1],arr[i-1][k]);
                }
            }
        }

        // 출력
        System.out.println(arr[str1.length()][str2.length()]);

    }
}
