import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * 1. 완전탐색으로 모든 부분수열을 찾는다면 O(2^n)의 시간복잡도가 들어 문자열의 길이 n의 범위가 최대 1000이므로  시간초과.
 * 
 * 2. LCS dp를 이용한다.
 * 2-1. dp테이블 : 문자열 길이 n * m 크기의 2차원 dp테이블에서 각 문자를 하나씩 추가했을 때 최대 LCS를 저장 
 * 2-2. 점화식 : if (Xi == Yj) LCS(Xi, Yj) : LCS(Xi-1, Yj-1) + 1 추가한 문자가 같다면 기존 문자의 LCS +1                                 
 * if (Xi != Yj) LCS(Xi, Yj) : max( LCS(Xi-1, Yj), LCS(Xi, Yj-1) ) 다르다면 기존 문자중 더 긴 LCS 그대로 가져오기
 * 2-3. 초기화 : i-1, j-1을 구해야 하기 때문에 1,1부터 시작한다.
 */
 
public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str1 = br.readLine().toCharArray();
        char[] str2 = br.readLine().toCharArray();
        int[][] dp =  new int[str1.length+1][str2.length+1];
        
        for(int i=1; i<=str1.length; i++) {
        	for(int j=1; j<=str2.length; j++) {
        		if(str1[i-1]==str2[j-1]) {
        			dp[i][j] = dp[i-1][j-1] + 1;
        		} else {
        			dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);	
        		}
        	}
        }
        
        System.out.println(dp[str1.length][str2.length]);
    }
       
 
}
