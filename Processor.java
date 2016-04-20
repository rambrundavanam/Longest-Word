package com.aspera.lcw;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Ram date:04/19/2016
 */
/**
 * @author Ram
 *
 */
/**
 * @author Ram
 *
 */
/**
 * @author Ram
 *
 */
public class Processor {

	private HashSet<String> set;

	/**
	 * @param file
	 * @return an array of words in the file
	 * @throws IOException
	 */
	public String[] parseFile(File file) throws IOException {

		// delimiter should be "\n" in Unix systems!
		String delims = "\r\n";

		BufferedInputStream stream = new BufferedInputStream(
				new FileInputStream(file));

		byte[] data = new byte[(int) file.length()];

		stream.read(data);
		stream.close();

		String buffer = new String(data, "UTF-8").trim();
		return buffer.split(delims);
	}
	
	/**
	 * @param word
	 * @param searchInSet
	 * @return boolean if the word is a valid concatenated word
	 * (According to our definition of valid concatenated word)
	 */
	public boolean isValidConcatWord(String word, boolean searchInSet) {

		if (word == null) {
			return false;
		}

		int wordSize = word.length();
		if (wordSize == 0) {
			return false;
		}
		if (searchInSet) {
			if (set.contains(word)) {
				return true;
			}
		}
		for (int i = 1; i <= wordSize - 1; i++) {
			String w1 = word.substring(0, i);
			String w2 = word.substring(i, wordSize);
			if (set.contains(w1)) {
				if (isValidConcatWord(w2, true)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * @param path
	 * This method processes the file and searches for the 
	 * concatenated words in the path provided and prints the results
	 */
	public void processFile(String path) {

		long start = System.currentTimeMillis();
		String[] list;
		int concatWordCount = 0;

		String largerWord = null;
		String secondLargerWord = null;

		File file = new File(path);

		try {
			list = parseFile(file);

			set = new HashSet<String>(Arrays.asList(list));

			for (int i = 0; i < list.length; i++) {
				if (isValidConcatWord(list[i], false)) {
					concatWordCount++;
					if (largerWord == null) {
						largerWord = list[i];
					}
					if (secondLargerWord == null) {
						secondLargerWord = list[i];
					}
					int currSize = list[i].length();
					if (currSize < secondLargerWord.length()) {
						continue;
					} else if (currSize < largerWord.length()) {
						secondLargerWord = list[i];
					} else {
						secondLargerWord = largerWord;
						largerWord = list[i];
					}
				}
			}
			System.out.println("No of Concatenated words:" + concatWordCount);
			System.out.println("Largest Concatenated word: " + largerWord
					+ " with size: " + largerWord.length());
			System.out.println("Second Largest Concatenated word: "
					+ secondLargerWord + " with size: "
					+ secondLargerWord.length());

		} catch (IOException e) {
			System.out.println("Unable to read the file");
		}

		long end = System.currentTimeMillis();
		System.out.println("Total time taken to retrieve the results:"
				+ (end - start) + " milliseconds.");
	}
}