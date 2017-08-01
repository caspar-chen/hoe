package com.caspar.hoe;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Random manipulation library
 * 
 * @author caspar.chen
 * @date 2017-7-28
 */
public class RandomHoe {

	/**
	 * Get a 32-bit random string
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * Get a 32-bit random string with an uppercase
	 */
	public static String uuidUpper() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	/**
	 * Get a 16-bit random string
	 */
	public static String uuidBit16() {
		return uuid().substring(8, 24);
	}

	/**
	 * Get a 16-bit random string with an uppercase
	 */
	public static String uuidBit16Upper() {
		return uuidBit16().toUpperCase();
	}

	/**
	 * Returns a pseudorandom, uniformly distributed {@code int} value between 0
	 * (inclusive) and the specified value (exclusive)
	 * 
	 * @param bound
	 *            the upper bound (exclusive). Must be positive.
	 * @see ThreadLocalRandom
	 */
	public static int random(int bound) {
		return ThreadLocalRandom.current().nextInt(bound);
	}

	/**
	 * Returns a pseudorandom, uniformly distributed value between the given
	 * least value (inclusive) and bound (exclusive).
	 * 
	 * @param least
	 *            the least value returned
	 * @param bound
	 *            the upper bound (exclusive)
	 * @return the next value
	 */
	public static int random(int least, int bound) {
		return ThreadLocalRandom.current().nextInt(least, bound);
	}

	/**
	 * Returns a pseudorandom, uniformly distributed value between the given
	 * least value (inclusive) and bound (exclusive).
	 * 
	 * @param least
	 *            the least value returned
	 * @param bound
	 *            the upper bound (exclusive)
	 * @return the next value
	 */
	public static long random(long least, long bound) {
		return ThreadLocalRandom.current().nextLong(least, bound);
	}
	
	/**
	 * Returns a limit digit pseudorandom 
	 * 
	 * @param limit digit
	 *            
	 * @return the pseudorandom
	 */
	public static long randomLimit(int limit) {
		long least = Long.valueOf(StringHoe.padRight("1", "0", limit));
		long bound = Long.valueOf(StringHoe.padRight("1", "0", limit + 1));
		return random(least, bound);
	}
	
	/**
	 * Returns a random object in the collection
	 * @param strings	the collection
	 * @return a random object
	 */
	public static String randomGet(String[] strings) {
		return strings[random(strings.length)];
	}
	
	/**
	 * Returns a random object in the collection
	 * @param ints the collection
	 * @return a random object
	 */
	public static int randomGet(int[] ints) {
		return ints[random(ints.length)];
	}

	/**
	 * Returns a random object in the collection
	 * @param doubles the collection
	 * @return a random object
	 */
	public static double randomGet(double[] doubles) {
		return doubles[random(doubles.length)];
	}
	
	/**
	 * Returns a random object in the collection
	 * @param floats the collection
	 * @return a random object
	 */
	public static float randomGet(float[] floats) {
		return floats[random(floats.length)];
	}
	
	/**
	 * Returns a random object in the collection
	 * @param longs the collection
	 * @return a random object
	 */
	public static long randomGet(long[] longs) {
		return longs[random(longs.length)];
	}
	
	/**
	 * Returns a random object in the collection
	 * @param shorts the collection
	 * @return a random object
	 */
	public static short randomGet(short[] shorts) {
		return shorts[random(shorts.length)];
	}
	
	/**
	 * Returns a random object in the collection
	 * @param list the collection
	 * @return a random object
	 */
	public static <T> T randomGet(List<T> list) {
		return list.get(random(list.size()));
	}
	
}
