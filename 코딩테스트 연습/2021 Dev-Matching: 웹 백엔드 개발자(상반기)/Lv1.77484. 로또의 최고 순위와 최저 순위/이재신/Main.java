class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        boolean[] num = new boolean[45 + 1];
       
        for(int i = 0; i < 6; i++){
            num[win_nums[i]] = true;
        }
        
        int cnt = 0;
        int zero_cnt = 0;
        
        for(int i = 0; i < 6; i++){
            // 훼손된 경우
            if(lottos[i] == 0)
                zero_cnt++;
            // 훼손되지 않은 경우
            // win_nums에 존재하는지 여부
            else if(num[lottos[i]])
                cnt++;
        }
        
        int worst = 7 - cnt;
        int best = 7 - cnt - zero_cnt;
        
        answer[0] = (best == 7 ? 6 : best);
        answer[1] = (worst == 7 ? 6 : worst);
        
        return answer;
    }
}
