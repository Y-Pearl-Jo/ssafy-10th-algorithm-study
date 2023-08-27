import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static final int nine = 6;
	static final int three = 2;
	static LinkedList<Integer>[] Gear = new LinkedList[5];

	static void reverseClock(int gear) {
		Gear[gear].offerLast(Gear[gear].pollFirst());
	}

	static void clock(int gear) {
		Gear[gear].offerFirst(Gear[gear].pollLast());
	}

	/**
	 * 시작 기어의 왼쪽 기어 회전 boolean rot / int gear rot 시계 !rot 반시계
	 */
	static void leftRotation(boolean rot, int gear) {
		if (rot) {
			if (gear == 1) {
				clock(gear);
				return;
			}
			if (Gear[gear].get(nine) != Gear[gear - 1].get(three)) {
				leftRotation(!rot, gear - 1);
				clock(gear);
			} else {
				clock(gear);
				return;
			}
		} else {
			if (gear == 1) {
				reverseClock(gear);
				return;
			}
			if (Gear[gear].get(nine) != Gear[gear - 1].get(three)) {
				leftRotation(!rot, gear - 1);
				reverseClock(gear);
			} else {
				reverseClock(gear);
				return;
			}
		}
	}

	/**
	 * 시작 기어의 오른쪽 기어 회전 boolean rot / int gear rot 시계 !rot 반시계
	 */
	static void rightRotation(boolean rot, int gear) {
		if (rot) {
			if (gear == 4) {
				clock(gear);
				return;
			}
			if (Gear[gear].get(three) != Gear[gear + 1].get(nine)) {
				rightRotation(!rot, gear + 1);
				clock(gear);
			} else {
				clock(gear);
				return;
			}
		} else {
			if (gear == 4) {
				reverseClock(gear);
				return;
			}
			if (Gear[gear].get(three) != Gear[gear + 1].get(nine)) {
				rightRotation(!rot, gear + 1);
				reverseClock(gear);
			} else {
				reverseClock(gear);
				return;
			}
		}
	}

	static void spinGear() throws IOException {
		st = new StringTokenizer(br.readLine());
		int gear = init(st.nextToken());
		int direction = init(st.nextToken());
		// 입력 받은 기어의 회전 방향이 시계 방향
		if (direction == 1) {
			boolean rot = true;
			leftRotation(rot, gear);
			reverseClock(gear);
			rot = true;
			rightRotation(rot, gear);
		}
		// 입력 받은 기어의 회전 방향이 반시계 방향
		else if (direction == -1) {
			boolean rot = false;
			leftRotation(rot, gear);
			clock(gear);
			rot = false;
			rightRotation(rot, gear);
		}
	}

	static void makeGear() throws IOException {
		String[] info = new String[5];
		for (int i = 1; i < 5; i++) {
			Gear[i] = new LinkedList<>();
		}
		for (int i = 1; i < 5; i++) {
			info[i] = br.readLine();
		}
		// 기어 정보 입력
		for (int i = 0; i < 8; i++) {
			for (int j = 1; j < 5; j++) {
				Gear[j].offerLast(init(info[j].charAt(i)));
			}
		}
	}

	static int result() {
		int sum = 0;
		for (int i = 1; i < 5; i++) {
			if (Gear[i].get(0) == 1) {
				switch (i) {
				case 1:
					sum += 1;
					break;
				case 2:
					sum += 2;
					break;
				case 3:
					sum += 4;
					break;
				case 4:
					sum += 8;
					break;
				}
			}
		}
		return sum;
	}

	public static void main(String[] args) throws IOException {

		makeGear();
		int time = init(br.readLine());
		for (int i = 0; i < time; i++) {
			spinGear();
		}

		System.out.println(result());
	}

	static int init(String str) {
		return Integer.parseInt(str);
	}

	static int init(char c) {
		return c - '0';
	}
}
