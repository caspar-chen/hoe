package com.caspar.hoe;

import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Random manipulation library
 * 
 * @author caspar.chen
 */
public class RandomHoe {

	private static Random r = new Random();
	/**
	 * Get a 32-bit random string
	 * @return The random uuid
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * Get a 32-bit random string with an uppercase
	 * @return The random uuid
	 */
	public static String uuidUpper() {
		return uuid().toUpperCase();
	}

	/**
	 * Get a 16-bit random string
	 * @return The random uuid
	 */
	public static String uuidBit16() {
		return uuid().substring(8, 24);
	}

	/**
	 * Get a 16-bit random string with an uppercase
	 * @return The random uuid
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
	 * @return the next value
	 */
	public static int random(int bound) {
		return r.nextInt(bound);
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
		return r.nextInt(bound - least) + least;
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
		return r.nextLong();
	}
	
	/**
	 * Returns a limit digit pseudorandom 
	 * 
	 * @param limit digit
	 *            
	 * @return the pseudorandom
	 */
	public static String randomLimit(int limit) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < limit; i++) {
			sb.append(random(9));	
		}
		return sb.toString();
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
	 * @param <T>  class
	 * @return a random object
	 */
	public static <T> T randomGet(List<T> list) {
		return list.get(random(list.size()));
	}
	
}
