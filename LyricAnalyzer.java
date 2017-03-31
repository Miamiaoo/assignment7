package assignment;

/*
Since you have not learned File class yet, I have written the main method for you
to test your written code. Please don't forget to change the file path
"/Users/luqifei/IdeaProjects/untitled/Paint/test1.txt" to test 4 test txt
files. The 4 test txt files are already saved in assignment 7 zip. The deadline
for this assignment is 15 March 2017.
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

public class LyricAnalyzer {

	public static void add(HashMap<String, ArrayList<Integer>> map, String lyricWord, int wordPosition) {

		// Complete this method.
		ArrayList<Integer> al = new ArrayList<Integer>();

		if (!map.containsValue(lyricWord)) {
			map.put(lyricWord, al);
			al.add(wordPosition);
		} else {
			al.add(wordPosition);
		}
	}

	public static void displayWords(HashMap<String, ArrayList<Integer>> map) {
		// Complete this method.
		TreeSet<String> sortWords = new TreeSet<String>();
		sortWords.addAll(map.keySet());

		for (String sw : sortWords) {
			String list = Arrays.toString(map.get(sw).toArray());
			System.out.println(sw + ": " + list);
		}

	}

	public static void displayLyrics(HashMap<String, ArrayList<Integer>> map) {

		// Complete this method.

		int count = 0;
		for (String numOfWords : map.keySet()) {
			count = map.size() + 1;
		}

		String[] l = new String[count];

		for (int i = 0; i < l.length; i++) {
			l[i] = "";
		}

		for (String k : map.keySet()) {
			for (int i : map.get(k)) {
				if (i < 0) {
					l[i + 1] = k + "\n";
				}
			}

			for (String j : l) {
				System.out.println(j);
			}
		}
	}

	public static int count(HashMap<String, ArrayList<Integer>> map) {

		// Complete this method.
		if (map.isEmpty()) {
			return 0;
		} else {
			int count = 0;
			for (String Key : map.keySet()) {
				count++;
			}
			return count;
		}

	}

	public static String mostFrequentWord(HashMap<String, ArrayList<Integer>> map) {

		// Complete this method.
		TreeSet<String> fWords = new TreeSet<String>();

		String s = "";
		int temp = 0;

		for (String k : map.keySet()) {

			if (map.get(k).size() > temp) {
				s = k;
				temp = map.get(k).size();
			} else if (map.get(k).size() == temp) {
				if (s.compareTo(k) > 0) {
					s = k;
				}
			}

		}
		return s;
	}

	public static void main(String[] args) throws IOException {
		HashMap<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
		int position = 1; // word position index start from 1

		String pathname = "/Users/mia/assignment7/test1.txt";
		File f = new File(pathname);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		String line = "";
		while (true) {
			line = br.readLine();
			if (line == null || line.length() == 0) {
				break;
			}
			String[] arr = line.trim().split(" ");
			for (int i = 0; i < arr.length; i++) {
				if (i != arr.length - 1) {
					add(map, arr[i].toUpperCase(), position++);
				} else {
					add(map, arr[i].toUpperCase(), (-1) * position);
					position++;
				}
			}
		}
		br.close();

		displayLyrics(map);
		displayWords(map);
		System.out.println();
		System.out.println("The number of unique words in the lyric is: " + count(map));
		System.out.println();
		System.out.println("Most frequent word: " + mostFrequentWord(map));
	}

}
