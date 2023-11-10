class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[][] arr = new int[rows][columns];
        
        // 배열 만들기
        int num = 1;
        for(int r=0; r<rows; r++){
            for(int c=0; c<columns; c++){
                arr[r][c] = num++;
            }
        }
        
        // 배열 회전
        int[] answer = new int[queries.length];
        int[][] arrCopy = new int[rows][columns];
        
        for(int i=0; i<queries.length; i++){
            // 배열 복사
            for(int k=0; k<rows; k++){
                arrCopy[k] = arr[k].clone();
            }
            
            // 쿼리
            int[] q = queries[i];
            int r1 = q[0]-1; // y //1
            int c1 = q[1]-1; // x //1
            int r2 = q[2]-1; //4
            int c2 = q[3]-1; //3
            
            // 회전
            int min = 100001;
            //  >
            for(int k=c1; k<c2; k++){
                arr[r1][k+1] = arrCopy[r1][k];
                min = Math.min(min,arr[r1][k+1]);
            }
            // v
            for(int k=r1; k<r2; k++){
                arr[k+1][c2] = arrCopy[k][c2];
                min = Math.min(min, arr[k+1][c2]);
            }
            // <
            for(int k=c2; k>c1; k--){
                arr[r2][k-1] = arrCopy[r2][k];
                min = Math.min(min,arr[r2][k-1]);
            }
            // ^
            for(int k=r2; k>r1; k--){
                arr[k-1][c1] = arrCopy[k][c1];
                min = Math.min(min,arr[k-1][c1]);
            }
            answer[i] = min;
        }
        
        return answer;
    }
    
}
