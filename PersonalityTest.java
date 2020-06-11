package hmw8;
/* Name:Kesojan Premakumar
 * 20837310
 * HMW 8 Q1
 * March 30 2020
 * Personality Test(tests personality according to answers)
 * input= file name and output file name
 * output=Generates new file with the personality results and statistics
 */
import java.util.*;
import java.io.*;

public class PersonalityTest {
	public static final int DIMENSION = 4;

	public static void main(String[] args) throws FileNotFoundException {
		// initialize scanner
		Scanner console = new Scanner(System.in);
		System.out.print("Input file name: ");
		String fileName = console.nextLine();
		printData(fileExists(fileName, console), console);
	}

	public static void printData(String fileName, Scanner console) throws FileNotFoundException {
		//this method retrieves and displays information
		Scanner input = new Scanner(new File(fileName));
		System.out.print("Output file name: ");

		String outputName = console.nextLine();

		PrintStream output = new PrintStream(outputName);
		int i = 1;
		// check which line to analyze
		while (input.hasNextLine()) {

			if (i == 1 || i % 2 != 0) {
				i++;
				// prints name
				String Line = input.nextLine();
				output.println(Line + ":");
			}

			else if (i % 2 == 0) {
				i++;
				String wordRead = input.nextLine();
				// prints results
				countResult(wordRead, output, outputName);
				output.println("\n");
			}
		}
	}

	public static void countResult(String wordRead, PrintStream output, String outputName) throws FileNotFoundException {
		// Initializing array
		double[] aCount = new double[DIMENSION];
		double[] bCount = new double[DIMENSION];
		// loop to iterate through entire string
		for (int j = 0; j < (wordRead.length() / 7); j++) {

			for (int k = 0; k < 7; k++) {
				// checking for answers
				if (k == 0) {
					if (wordRead.charAt(7 * j + k) == 'a' || wordRead.charAt(7 * j + k) == 'A') {
						aCount[0] += 1;
					}
					if (wordRead.charAt(7 * j + k) == 'b' || wordRead.charAt(7 * j + k) == 'B') {
						bCount[0] += 1;
					}
				}

				else if (k == 1 || k == 2) {
					if (wordRead.charAt(7 * j + k) == 'a' || wordRead.charAt(7 * j + k) == 'A') {
						aCount[1] += 1;
					}
					if (wordRead.charAt(7 * j + k) == 'b' || wordRead.charAt(7 * j + k) == 'B') {
						bCount[1] += 1;
					}
				}

				else if (k == 3 || k == 4) {
					if (wordRead.charAt(7 * j + k) == 'a' || wordRead.charAt(7 * j + k) == 'A') {
						aCount[2] += 1;
					}
					if (wordRead.charAt(7 * j + k) == 'b' || wordRead.charAt(7 * j + k) == 'B') {
						bCount[2] += 1;
					}
				}

				else if (k == 5 || k == 6) {
					if (wordRead.charAt(7 * j + k) == 'a' || wordRead.charAt(7 * j + k) == 'A') {
						aCount[3] += 1;
					}
					if (wordRead.charAt(7 * j + k) == 'b' || wordRead.charAt(7 * j + k) == 'B') {
						bCount[3] += 1;
					}
				}

			}
		}
		// printing results
		for (int k = 0; k < aCount.length; k++) {
			output.print((int) aCount[k] + "A-" + (int) bCount[k] + "B ");
		}
		output.println();
		output.print("[" + Math.round(bCount[0] * 100.0 / (bCount[0] + aCount[0])) + "%");
		for (int k = 1; k < aCount.length; k++) {
			// calculation for percentage
			output.print(", " + Math.round(bCount[k] * 100.0 / (bCount[k] + aCount[k])) + "%");
		}
		output.print("] = ");
		dimension(aCount, bCount, output);

	}

	public static void dimension(double[] aCount, double[] bCount, PrintStream output) {
		int[] percent = new int[4];

		for (int k = 0; k < aCount.length; k++) {
			percent[k] = ((int) ((bCount[k] / (bCount[k] + aCount[k])) * 100));
			// calculating personality
			if (percent[k] == 50) {
				output.print("X");
			} else if (k == 0) {
				if (percent[k] > 50) {
					output.print("I");
				} else if (percent[k] < 50) {
					output.print("E");
				}
			}

			else if (k == 1) {
				if (percent[k] > 50) {
					output.print("N");
				} else if (percent[k] < 50) {
					output.print("S");
				}
			}

			else if (k == 2) {
				if (percent[k] > 50) {
					output.print("F");
				} else if (percent[k] < 50) {
					output.print("T");
				}
			}

			else if (k == 3) {
				if (percent[k] > 50) {
					output.print("P");
				} else if (percent[k] < 50) {
					output.print("J");
				}
			}

		}
		System.out.println();

	}

	public static String fileExists(String fileName, Scanner console) {
		// this method checks if user input file exists in system
		File fileCheck = new File(fileName);
		while (!fileCheck.exists()) {
			System.out.print("File not found. Try again: ");
			fileName = console.nextLine();
			fileCheck = new File(fileName);
		}
		return fileName;
	}

}
