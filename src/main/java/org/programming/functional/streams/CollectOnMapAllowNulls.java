package org.programming.functional.streams;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Collect values on a map, allowing null values.
 * Normally the requirements are to make it null safe, but the toMap() method
 * doesn't allow to collect null values. This example shows how to do it
 */
@Slf4j
public class CollectOnMapAllowNulls {

	public static void main(String[] args) {

		Map<Integer, List<Integer>> ratesByRoom = collectRatesByRoom(List.of(1, 2, 3, 4, 5));

		log.info(ratesByRoom.toString());
	}

	private static Map<Integer, List<Integer>> collectRatesByRoom(final List<Integer> roomIds) {

		return roomIds.stream()
			.collect(HashMap::new,
					 (map, roomId) -> map.put(roomId, obtainRatesByRoom(roomId)),
					 HashMap::putAll);
	}

	// Simple simulation of obtaining rates. A real case could be calling an API
	private static List<Integer> obtainRatesByRoom(final int num) {

		return num % 2 == 0
			? null
			: IntStream.range(0, 4)
				.mapToObj(index -> index + 100 * num)
				.toList();
	}
}
