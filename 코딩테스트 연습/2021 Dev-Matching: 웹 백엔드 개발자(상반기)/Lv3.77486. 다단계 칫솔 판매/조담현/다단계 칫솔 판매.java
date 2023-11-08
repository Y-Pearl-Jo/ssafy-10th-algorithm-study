import java.util.HashMap;
import java.util.Map;

class Person {
		String parent;
		int value;		
	}

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		Map<String, Person> people = new HashMap<>();
		
		for(int i=0; i<enroll.length; i++) {
			people.put(enroll[i], new Person());
		}
		
		for(int i=0; i<referral.length; i++) {
			people.get(enroll[i]).parent = referral[i];
		}
		
		for(int i=0; i<seller.length; i++) {
			double money = amount[i] * 100;
			Person now = people.get(seller[i]);
			while(true) {
                
				if(money*0.1<1.0) {
					now.value+=(int)money;
					break;
				}
				now.value += money - Math.floor(money*0.1);
				Person parent = people.get(now.parent); 				
				if(now.parent.equals("-")) {
                    break;
                }
                now = parent;
                money = Math.floor(money*0.1);
			}
		}
		
		int[] answer = new int [enroll.length];
		for(int i=0; i<enroll.length; i++) {
			answer[i] = people.get(enroll[i]).value;
		}
		return answer;
	}
}
