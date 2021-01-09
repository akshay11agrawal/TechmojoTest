package com.tm.test;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class TweetsCounter {

	private static Map<String, Integer> tagCountMap = new HashMap<String, Integer>();
	private static int counter = 10;
	private static final ArrayList<Character> invalid = new ArrayList(Arrays.asList(' ', ',', '#'));

	public static void main(String[] args) {

		if (args.length > 0) {

			for (int index = 0; index < args.length; index++) {
				countHashtag(args[index]);
			}

			// sort the map based on the values in descending order
			HashMap<String, Integer> collect = tagCountMap.entrySet().stream()
					.sorted(Collections.reverseOrder(comparingByValue()))
					.collect(toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));

			System.out.println("Top 10 tweets are as follows : ");

			printTagNames(collect);

		} else {
			System.out.println("Please pass the tweets to the main program");
		}

	}

	public static void countHashtag(String statement) {
		try {
			ArrayList<Integer> indexes = new ArrayList<>();
			// collect the hashatags indexes
			for (int ind = 0; ind < statement.length(); ind++) {
				if (statement.charAt(ind) == '#') {
					indexes.add(ind);
				}
			}

			for (int ind = 0; ind < indexes.size(); ind++) {
				StringBuffer tag = new StringBuffer();

				// filter the hashtag content
				for (int posn = indexes.get(ind) + 1; posn < statement.length(); posn++) {
					if (!invalid.contains(statement.charAt(posn))) {
						tag = tag.append(statement.charAt(posn));
					} else {
						break;
					}
				}

				String tagName = tag.toString();

				// count the hashtag
				if (!tagCountMap.containsKey(tagName)) {
					tagCountMap.put(tagName, 1);
				} else {
					tagCountMap.put(tagName, tagCountMap.get(tagName) + 1);
				}
			}
		} catch (Exception exception) {
			System.out.println("Error occurred while counting the hashtags, please try again later");
		}

	}

	public static void printTagNames(Map<String, Integer> temp) {
		try {
			int tempCounter = 1;
			for (String tagName : temp.keySet()) {
				if (counter == 0) {
					break;
				}
				System.out.println(tempCounter++ + ") " + tagName);
				counter--;
			}
		}

		catch (Exception exception) {
			System.out.println("Error occurred while printing the hashtags, please try again later");
		}
	}

}
