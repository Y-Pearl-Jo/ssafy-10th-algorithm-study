import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static int n;
    static StringBuilder sb = new StringBuilder();
    static int i = -1;
    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	int n = Integer.parseInt(br.readLine());
	System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
	
	fib(n);
	bw.write(sb.toString());
	bw.close();
    }

    public static void fib(int n) {
	int it = ++i;
	if(n == 0) {
	    underbar(it);
	    sb.append("\"재귀함수가 뭔가요?\"\n");
	    underbar(it);
	    sb.append("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
	    underbar(it);
	    sb.append("라고 답변하였지.\n");
	    return;
	} else {
	    underbar(it);
	    sb.append("\"재귀함수가 뭔가요?\"\n");
	    underbar(it);
	    sb.append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");
	    underbar(it);
	    sb.append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");
	    underbar(it);
	    sb.append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
	    fib(n-1);
	    underbar(it);
	    sb.append("라고 답변하였지.\n");
	    return;
	}
	
	
	
	
    }
    
    public static void underbar(int i) {
	    for(int j=0; j<i; j++) {
		sb.append("____");
	    }
	}

    

}
