import java.io.*;
import java.util.*;

class Solution {
    static Map<String, String> parent = new HashMap<>();
    static Map<String, Integer> selfIndex = new HashMap<>();
    
    static void sell(int i, String[] seller, int[] amount, int[] answer){
        
        String dadan = seller[i];
        int profit = 100 * amount[i];
        
        while(!dadan.equals("-")){
            int give = profit / 10;
            int take = profit - give;
            
            answer[selfIndex.get(dadan)] += take;
            
            dadan = parent.get(dadan);
            
            profit /= 10;
            
            if(profit < 1)
                break;
        }
    }
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        
        for(int i = 0; i < enroll.length; i++){
            parent.put(enroll[i], referral[i]);
            selfIndex.put(enroll[i], i);
        }
        
        for(int i = 0; i < seller.length; i++){
            sell(i, seller, amount, answer);
        }
        
        return answer;
    }
}
