import java.io.*;
import java.util.HashMap;
import java.util.Map;

class MainClass {

	public static void main(String[] args) throws IOException {
		System.out.println(solve("1515732322"));
	}

	static int solve(String N) {
		Map<String, String> map = new HashMap<String,String>(){{
			put("1122", "");
			put("2233", "");
			put("3344", "");
			put("4455", "");
			put("5566", "");
			put("6677", "");
			put("7788", "");
			put("8899", "");
		}};
		
		populateMap(map, N);

		int sum = 0;
		for (Map.Entry<String, String> entry : map.entrySet()) {
			sum = (count(entry.getValue(), entry.getKey()) + sum) % 1000000007;
		}

		return sum;
	}

	private static void populateMap(Map<String, String> map, String N) {
		char[] string = N.toCharArray();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			char[] four = entry.getKey().toCharArray();
			String value = entry.getValue();
			for (char c : string) {
				if (c == four[0]) {
					value = value + c;
				} else if (c == four[2]) {
					value = value + c;
				}
			}
			entry.setValue(value);
		}
	}

	static int count(String a, String b) {
		int m = a.length();
		int n = b.length();

		int lookup[][] = new int[m + 1][n + 1];

		for (int i = 0; i <= n; ++i)
			lookup[0][i] = 0;

		for (int i = 0; i <= m; ++i)
			lookup[i][0] = 1;

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (a.charAt(i - 1) == b.charAt(j - 1))
					lookup[i][j] = (lookup[i - 1][j - 1] + lookup[i - 1][j]) % 1000000007;
				else
					lookup[i][j] = lookup[i - 1][j];
			}
		}

		return (int) (lookup[m][n] % 1000000007);
	}

}

