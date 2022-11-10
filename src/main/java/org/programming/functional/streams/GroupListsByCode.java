package org.programming.functional.streams;

import org.apache.commons.lang3.tuple.Pair;

import java.util.LinkedHashMap;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

/**
 * Group into lists by category
 */
public class GroupListsByCode {

	public static void main(String[] args) {

		List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

		LinkedHashMap<String, List<Integer>> collect = numbers.stream()
			.map(GroupListsByCode::classifyByCode)
			.collect(groupingBy(Pair::getKey,
								LinkedHashMap::new,
								mapping(Pair::getValue,
										toList())));

		System.out.println(collect);
	}

	private static Pair<String, Integer> classifyByCode(int number) {

		return number % 2 == 0
			? Pair.of("EVEN NUMBERS", number)
			: Pair.of("ODD NUMBERS", number);
	}
}
