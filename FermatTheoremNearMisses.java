import java.io.*;
import java.util.Scanner;

public class FermatTheoremNearMisses {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner STDIN_SCANNER = new Scanner(System.in);
		int n = 0, k = 0;

		// Accept n value until is 2 < n < 12
		while(n <= 2 || n >= 12) {
			System.out.print("Enter integer n such that 2 < n < 12: ");
			n = STDIN11_SCANNER.nextInt();
		}

		// Accept k value until is k > 10
		while(k <= 10) {
			System.out.print("Enter upper limit k for x and y (k > 10): ");
			k = STDIN_SCANNER.nextInt();
		}

		int miss = Integer.MAX_VALUE;
		double relativMiss = Integer.MAX_VALUE;


		for(int x = 10; x <= k; x++) {
			for(int y = 10; y <= k; y++) {
				// Calculate (x^n + y^n)
				long lhs = (int)(Math.pow(x, n) + Math.pow(y, n));
				if(lhs < 0) {
					System.out.print("OVERFLOW ERROR!! Choose lower values of n and k");
					return;
				}
				// Calculate z value using x^n + y^n = z^n
				int z = (int)Math.pow(lhs, 1.0 / n);
				
				// Find “miss” as the smallest of these two values: [(x^n + y^n) - z^n] or [(z+1)n - (x^n + y^n)
				int diff = (int)(lhs - Math.pow(z, n));
				if(Math.pow(z + 1, n) - lhs < diff) {
					diff = (int)(Math.pow(z + 1, n) - lhs);
					z++;
				}
				//Calculate Relative Miss
				double relDiff = 100. * diff / lhs;
				if(relDiff < relativMiss) {
					miss = diff;
					relativMiss = relDiff;
					System.out.printf("x = %d, y = %d, z = %d, miss = %d, relative miss = %.6f%%\n", x, y, z, diff, relDiff);
				}
			}
		}
		STDIN_SCANNER.close();
	}
}