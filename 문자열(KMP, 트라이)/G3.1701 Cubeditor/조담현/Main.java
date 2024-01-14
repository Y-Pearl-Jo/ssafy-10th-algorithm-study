import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

		String s = br.readLine();
		int max = 0;
        
        	// 모든 부분 문자열에 대해 탐색
		for(int i = 0; i < s.length(); i++) {
			max = Math.max(max, getpi(s.substring(i, s.length())));
		}
		System.out.print(max);
	}
	
    	// 해당 문자열 내에 존재하는 부분 문자열 중 접두사와 접미사가 같은 문자열의 최대 길이 구하기
	public static int getpi(String s) {
		int[] pi = new int[s.length()];
		int j = 0; // 접두사 비교 인덱스
        	int max = 0; 
		for(int i = 1; i < s.length(); i++) { // 접미사 비교 인덱스
			while(j > 0 && s.charAt(i) != s.charAt(j)) {
				j = pi[j - 1];
			}
			if(s.charAt(i) == s.charAt(j)) {
                		pi[i] = ++j;
				max = Math.max(max, pi[i]);
			}
		}
		return max;
	}
}
