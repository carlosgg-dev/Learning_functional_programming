package org.programming.functional.streams;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.filtering;
import static java.util.stream.Collectors.toList;

/**
 * Collect two opposite filters from a list, and place them in two lists
 */
public class CollectBothFilters {

	public static void main(String[] args) {

		List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

		Map<String, List<Integer>> result = numbers.stream()
			.collect(Collectors.teeing(
				filtering(i -> i % 2 == 0, toList()),
				filtering(i -> i % 2 != 0, toList()),
				(resA, resB) -> {
					Map<String, List<Integer>> classifiedNumbers = new HashMap<>();
					classifiedNumbers.put("EVEN NUMBERS", resA);
					classifiedNumbers.put("ODD NUMBERS", resB);
					return classifiedNumbers;
				}
			));

		System.out.println(result);
	}
}
