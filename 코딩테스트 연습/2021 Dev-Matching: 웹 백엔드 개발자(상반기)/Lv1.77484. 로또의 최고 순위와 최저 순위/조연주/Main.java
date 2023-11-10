class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int zero = 0;
        int cnt = 0;
        
        for(int a : lottos){
            if(a==0) {
                zero++;
                continue;
            }
            for(int b : win_nums){
                if(a==b){
                    cnt++;
                    continue;
                }
            }
        }
        
        int ans1 = 7-(cnt+zero);
        int ans2 = 7-cnt;
        
        if(ans1==7) ans1=6;
        if(ans2==7) ans2=6;
        

        int[] answer = {ans1, ans2};
        
        return answer;
    }
}
