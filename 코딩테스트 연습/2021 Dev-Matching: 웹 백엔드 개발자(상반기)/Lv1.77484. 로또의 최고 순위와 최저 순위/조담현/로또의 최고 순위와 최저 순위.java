class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int sameCnt = 0;
        int zeroCnt = 0;
        for(int i=0; i<lottos.length; i++) {
            if(lottos[i]==0) zeroCnt++;
            for(int j=0; j<win_nums.length; j++) {
                if(lottos[i]==win_nums[j]) sameCnt++;
            }
        }
        
        
        int[] answer = {(7-(sameCnt+zeroCnt)) <= 5 ? (7-(sameCnt+zeroCnt)) : 6, (7-sameCnt) <= 5 ? (7-sameCnt) : 6};
        return answer;
    }
}
