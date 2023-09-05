import java.util.*;
import java.io.*;

public class Main {
    // 변수
    static int[][] arr;
    static int N, x, y;
    static int blue = 0;
    static int white = 0;

    // main
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 한 변의 길이

        // 색 입력받기
        arr = new int[N][N];
        for(int i=0; i<N; i++){
            for(int k=0; k<N; k++){
                arr[i][k] = sc.nextInt();
            }
        }

        // 분할정복
        partition(0, 0,N);

        // 출력
        System.out.println(white);
        System.out.println(blue);

    }

    // 분할
    static void partition(int y, int x, int size){
        // 색이 모두 같으면
        if(isSame(y,x,size)){
            if(arr[y][x]==1){
                blue++;
            }
            else{
                white++;
            }
            return;
        }

        // 아니면 나누기
        size /= 2;

        partition(y,x,size);
        partition(y+size,x,size);
        partition(y,x+size,size);
        partition(y+size,x+size,size);
    }

    // 색이 전부 같은지 검사
    static boolean isSame(int y, int x, int size){
        int color = arr[y][x];

        for(int i=y; i<y+size; i++) {
            for (int k = x; k < x + size; k++) {
                if (arr[i][k] != color) {
                    return false;
                }
            }
        }
        return true;
    }
}
