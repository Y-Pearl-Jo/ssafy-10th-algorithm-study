import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st;

	st = new StringTokenizer(br.readLine());

	char[] King = st.nextToken().toCharArray();
	char[] Stone = st.nextToken().toCharArray();
	int time = init(st.nextToken());

	for (int i = 0; i < time; i++) {
	    String oper = br.readLine();
	    switch (oper) {
	    case "R":
		if (King[1] == Stone[1] && King[0] + 1 == Stone[0] && Stone[0] != 'H') {
		    King[0]++;
		    Stone[0]++;
		} else if ((King[1] != Stone[1] || King[0] + 1 != Stone[0]) && King[0] != 'H') {
		    King[0]++;
		}
		break;
	    case "L":
		if (King[1] == Stone[1] && King[0] - 1 == Stone[0] && Stone[0] != 'A') {
		    King[0]--;
		    Stone[0]--;
		} else if ((King[1] != Stone[1] || King[0] - 1 != Stone[0]) && King[0] != 'A') {
		    King[0]--;
		}
		break;
	    case "B":
		if (King[0] == Stone[0] && King[1] - 1 == Stone[1] && Stone[1] != '1') {
		    King[1]--;
		    Stone[1]--;
		} else if ((King[0] != Stone[0] || King[1] - 1 != Stone[1]) && King[1] != '1') {
		    King[1]--;
		}
		break;
	    case "T":
		if (King[0] == Stone[0] && King[1] + 1 == Stone[1] && Stone[1] != '8') {
		    King[1]++;
		    Stone[1]++;
		} else if ((King[0] != Stone[0] || King[1] + 1 != Stone[1]) && King[1] != '8') {
		    King[1]++;
		}
		break;
	    case "RT":
		if (King[0] + 1 == Stone[0] && King[1] + 1 == Stone[1] && Stone[1] != '8' && Stone[0] != 'H') {
		    King[0]++;
		    Stone[0]++;
		    King[1]++;
		    Stone[1]++;
		} else if ((King[0] + 1 != Stone[0] || King[1] + 1 != Stone[1]) && King[1] != '8' && King[0] != 'H') {
		    King[0]++;
		    King[1]++;
		}
		break;
	    case "LT":
		if (King[0] - 1 == Stone[0] && King[1] + 1 == Stone[1] && Stone[1] != '8' && Stone[0] != 'A') {
		    King[0]--;
		    Stone[0]--;
		    King[1]++;
		    Stone[1]++;
		} else if ((King[0] - 1 != Stone[0] || King[1] + 1 != Stone[1]) && King[1] != '8' && King[0] != 'A') {
		    King[0]--;
		    King[1]++;
		}
		break;
	    case "RB":
		if (King[0] + 1 == Stone[0] && King[1] - 1 == Stone[1] && Stone[1] != '1' && Stone[0] != 'H') {
		    King[0]++;
		    Stone[0]++;
		    King[1]--;
		    Stone[1]--;
		} else if ((King[0] + 1 != Stone[0] || King[1] - 1 != Stone[1]) && King[1] != '1' && King[0] != 'H') {
		    King[0]++;
		    King[1]--;
		}
		break;
	    case "LB":
		if (King[0] - 1 == Stone[0] && King[1] - 1 == Stone[1] && Stone[1] != '1' && Stone[0] != 'A') {
		    King[0]--;
		    Stone[0]--;
		    King[1]--;
		    Stone[1]--;
		} else if ((King[0] - 1 != Stone[0] || King[1] - 1 != Stone[1]) && King[1] != '1' && King[0] != 'A') {
		    King[0]--;
		    King[1]--;
		}
		break;
	    }
	}
	System.out.printf("%c%c\n", King[0], King[1]);
	System.out.printf("%c%c", Stone[0], Stone[1]);

    }

    public static int init(String str) {
	return Integer.parseInt(str);
    }
}
