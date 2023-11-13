class Solution {

    static int rotate(int[][] arr, int[] query){
        int sR, sC, eR, eC;
        
        sR = query[0];
        sC = query[1];
        eR = query[2];
        eC = query[3];
        
        int Min = Integer.MAX_VALUE;
        
        int[] temp = {arr[sR][sC], arr[sR][eC], arr[eR][eC], arr[eR][sC]};
        for(int i = 0; i < 4; i++){
            Min = Math.min(Min, temp[i]);
        }
        
        // 북
        for(int i = eC; i > sC; i--){
            arr[sR][i] = arr[sR][i - 1];
            Min = Math.min(Min, arr[sR][i]);
        }
        // 동
        for(int i = eR; i > sR; i--){
            arr[i][eC] = arr[i - 1][eC];
            Min = Math.min(Min, arr[i][eC]);
        }
        // 남
        for(int i = sC; i < eC; i++){
            arr[eR][i] = arr[eR][i + 1];
            Min = Math.min(Min, arr[eR][i]);
        }
        // 서
        for(int i = sR; i < eR; i++){
            arr[i][sC] = arr[i + 1][sC];
            Min = Math.min(Min, arr[i][sC]);
        }
        // temp 저장
        arr[sR][sC + 1] = temp[0];
        arr[sR + 1][eC] = temp[1];
        arr[eR][eC - 1] = temp[2];
        arr[eR - 1][sC] = temp[3];
        
        return Min;
    }
    
    static void make(int[][] arr, int row, int col){
        int num = 1;
        for(int i = 1; i < row + 1; i++){
            for(int j = 1; j < col + 1; j++){
                arr[i][j] = num++;
            }
        }
    }
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[][] arr = new int[rows + 1][columns+1];
        make(arr, rows, columns);
        
        int[] answer = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
            answer[i] = rotate(arr, queries[i]);
        }
        return answer;
    }
}
