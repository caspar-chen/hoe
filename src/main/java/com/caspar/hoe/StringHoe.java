package com.caspar.hoe;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map.Entry;

/**
 * String manipulation library
 * 
 */
public class StringHoe {

	/**
	 * The empty String.
	 */
	public static final String EMPTY = "";

	/**
	 * A String for a space character.
	 */
	public static final String SPACE = " ";

	/**
	 * Appends Strings
	 * 
	 * <pre>
	 * append(&quot;h&quot;, &quot;o&quot;, &quot;e&quot;) = &quot;hoe&quot;
	 * </pre>
	 * 
	 * @param appends	appends an array of strings to append
	 * @return full String
	 */
	public static String append(final String... appends) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < appends.length; i++) {
			sb.append(appends[i]);
		}
		return sb.toString();
	}

	/**
	 * append before the string
	 * <pre>
	 * appendPre(&quot;e&quot;, &quot;h&quot;, &quot;o&quot;) = &quot;hoe&quot;
	 * </pre>
	 * 
	 * @param str initial String
	 * @param appends appends an array of strings to append
	 * @return full String 
	 */
	public static String appendPre(final String str, final String... appends) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < appends.length; i++) {
			sb.append(appends[i]);
		}
		return sb.append(str).toString();
	}

	/**
	 * Replace consecutive whitespace characters with a single space.
	 * 
	 * @param value
	 *            input String
	 * @return collapsed String
	 */
	public static String collapseWhitespace(final String value) {
		return trim(value).replaceAll("\\s\\s+", " ");
	}

	/**
	 * <p>
	 * Counts how many times the substring appears in the larger string.
	 * </p>
	 * 
	 * <pre>
	 * countMatches(null, *)       = 0
	 * countMatches("", *)         = 0
	 * countMatches("abba", null)  = 0
	 * countMatches("abba", "")    = 0
	 * countMatches("abba", "a")   = 2
	 * countMatches("abba", "ab")  = 1
	 * countMatches("abba", "xxx") = 0
	 * </pre>
	 * 
	 * @param str
	 *            the CharSequence to check, may be null
	 * @param sub
	 *            the substring to count, may be null
	 * @return the number of occurrences, 0 if either CharSequence is
	 *         {@code null}
	 */
	public static int countMatches(final CharSequence str,
			final CharSequence sub) {
		if (isEmpty(str) || isEmpty(sub)) {
			return 0;
		}
		int count = 0;
		int idx = 0;
		while ((idx = str.toString().indexOf(sub.toString(), idx)) != -1) {
			count++;
			idx += sub.length();
		}
		return count;
	}

	/**
	 * <p>
	 * Counts how many times the char appears in the given string.
	 * </p>
	 * 
	 * <pre>
	 * countMatches(null, *)       = 0
	 * countMatches("", *)         = 0
	 * countMatches("abba", 0)  = 0
	 * countMatches("abba", 'a')   = 2
	 * countMatches("abba", 'b')  = 2
	 * countMatches("abba", 'x') = 0
	 * </pre>
	 * 
	 * @param str
	 *            the CharSequence to check, may be null
	 * @param ch
	 *            the char to count
	 * @return the number of occurrences, 0 if the CharSequence is {@code null}
	 */
	public static int countMatches(final CharSequence str, final char ch) {
		if (isEmpty(str)) {
			return 0;
		}
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (ch == str.charAt(i)) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Converts all HTML entities to html code.
	 * 
	 * @param encodedHtml
	 *            The encoded HTML
	 * @return The decoded HTML
	 */
	public static String htmlDecodeHtmlCode(final String encodedHtml) {
		return htmlDecode(encodedHtml, 0);
	}

	/**
	 * Converts all HTML entities to decimal code.
	 * 
	 * @param encodedHtml
	 *            The encoded HTML
	 * @return The decoded HTML
	 */
	public static String htmlDecodeDecimalCode(final String encodedHtml) {
		return htmlDecode(encodedHtml, 1);
	}

	/**
	 * Convert all html characters to HTML entities.
	 * 
	 * @param html
	 *            The HTML to encode
	 * @return The encoded data
	 */
	public static String htmlEncodeByHtmlCode(final String html) {
		return htmlEncode(html, 0);
	}

	/**
	 * Convert all html characters to HTML entities.
	 * 
	 * @param html
	 *            The HTML to encode
	 * @return The encoded data
	 */
	public static String htmlEncodeByDecimalCode(final String html) {
		return htmlEncode(html, 1);
	}

	/**
	 * Checks if a CharSequence is whitespace, empty or null
	 * 
	 * <pre>
	 * StringUtil.isEmpty(null)      = true
	 * StringUtil.isEmpty("null")    = true
	 * StringUtil.isEmpty("NULL")    = true
	 * StringUtil.isEmpty("")        = true
	 * StringUtil.isEmpty(" ")       = true
	 * StringUtil.isEmpty("abc")     = false
	 * StringUtil.isEmpty("  abc  ") = false
	 * </pre>
	 * 
	 * @param cs
	 *            the CharSequence to check
	 * @return  true if CharSequence is null or empty
	 */
	public static boolean isEmpty(final CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0
				|| "null".equalsIgnoreCase(cs.toString())) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if a CharSequence is not whitespace, empty and null
	 * @param cs
	 *            the CharSequence to check
	 * @return  true if CharSequence is not whitespace, empty and null
	 * @see StringHoe#isEmpty
	 */
	public static boolean isNotEmpty(final CharSequence cs) {
		return (!isEmpty(cs));
	}

	/**
	 * Get a 32-bit md5 encrypted string
	 * 
	 * @param source The input string
	 * @return encrypted string
	 */
	public static String md5Bit32(String source) {
		return md5(source, 32);
	}

	/**
	 * Get a 16-bit md5 encrypted string
	 * 
	 * @param source The input string
	 * @return encrypted string
	 */
	public static String md5Bit16(String source) {
		return md5(source, 16);
	}

	/**
	 * Removes all spaces on left and right
	 * 
	 * @param value
	 *            The input String
	 * @return String without left and right border spaces
	 */
	public static String trim(final String value) {
		return value == null ? null : value.trim();
	}

	/**
	 * Removes all spaces
	 * 
	 * @param str
	 *            The input String
	 * @return String without spaces
	 */
	public static String trimAll(final String str) {
		return str.replaceAll("\\s*", "");
	}

	/**
	 * Removes all spaces on left
	 * 
	 * @param value
	 *            The input String
	 * @return String without left border spaces
	 */
	public static String trimLeft(final String value) {
		return value.replaceAll("^\\s+", "");
	}

	/**
	 * Removes all spaces on right
	 * 
	 * @param value
	 *            The input String
	 * @return String without left border spaces
	 */
	public static String trimRight(final String value) {
		return value.replaceAll("\\s+$", "");
	}

	/**
	 * Gets the leftmost {@code len} characters of a String.
	 * 
	 * <pre>
	 * left(null, *)    = null
	 * left("", *)      = ""
	 * left("abc", 0)   = ""
	 * left("abc", 2)   = "ab"
	 * left("abc", 4)   = "abc"
	 * </pre>
	 * 
	 * @param str
	 *            the String to get the leftmost characters from, may be null
	 * @param len
	 *            the length of the required String
	 * @return the leftmost characters, {@code null} if null String input
	 */
	public static String left(final String str, final int len) {
		if (str == null) {
			return null;
		}
		if (len < 0) {
			return EMPTY;
		}
		if (str.length() <= len) {
			return str;
		}
		return str.substring(0, len);
	}

	/**
	 * <p>
	 * Gets the rightmost {@code len} characters of a String.
	 * </p>
	 * 
	 * <pre>
	 * right(null, *)    = null
	 * right("", *)      = ""
	 * right("abc", 0)   = ""
	 * right("abc", 2)   = "bc"
	 * right("abc", 4)   = "abc"
	 * </pre>
	 * 
	 * @param str
	 *            the String to get the rightmost characters from, may be null
	 * @param len
	 *            the length of the required String
	 * @return the rightmost characters, {@code null} if null String input
	 */
	public static String right(final String str, final int len) {
		if (str == null) {
			return null;
		}
		if (len < 0) {
			return EMPTY;
		}
		if (str.length() <= len) {
			return str;
		}
		return str.substring(str.length() - len);
	}

	/**
	 * <p>
	 * Gets {@code len} characters from the middle of a String.
	 * </p>
	 * 
	 * <pre>
	 * mid(null, *, *)    = null
	 * mid("", 0, *)      = ""
	 * mid("abc", 0, 2)   = "ab"
	 * mid("abc", 0, 4)   = "abc"
	 * mid("abc", 2, 4)   = "c"
	 * mid("abc", 4, 2)   = ""
	 * mid("abc", -2, 2)  = "ab"
	 * </pre>
	 * 
	 * @param str
	 *            the String to get the characters from, may be null
	 * @param pos
	 *            the position to start from, negative treated as zero
	 * @param len
	 *            the length of the required String
	 * @return the middle characters, {@code null} if null String input
	 */
	public static String mid(final String str, int pos, final int len) {
		if (str == null) {
			return null;
		}
		if (len < 0 || pos > str.length()) {
			return EMPTY;
		}
		if (pos < 0) {
			pos = 0;
		}
		if (str.length() <= pos + len) {
			return str.substring(pos);
		}
		return str.substring(pos, pos + len);
	}

	/**
	 * <p>
	 * Joins the elements of the provided array into a single String containing
	 * the provided list of elements.
	 * </p>
	 * 
	 * <p>
	 * No delimiter is added before or after the list. A {@code null} separator
	 * is the same as an empty String (""). Null objects or empty strings within
	 * the array are represented by empty strings.
	 * </p>
	 * 
	 * <pre>
	 * join(null, *)                = null
	 * join([], *)                  = ""
	 * join([null], *)              = ""
	 * join(["a", "b", "c"], "--")  = "a--b--c"
	 * join(["a", "b", "c"], null)  = "abc"
	 * join(["a", "b", "c"], "")    = "abc"
	 * join([null, "", "a"], ',')   = ",,a"
	 * </pre>
	 * 
	 * @param array
	 *            the array of values to join together, may be null
	 * @param separator
	 *            the separator character to use, null treated as ""
	 * @return the joined String, {@code null} if null array input
	 */
	public static String join(final Object[] array, final String separator) {
		if (array == null) {
			return null;
		}
		return join(array, separator, 0, array.length);
	}

	public static String join(final long[] array, final String separator) {
		if (array == null) {
			return null;
		}
		return join(array, separator, 0, array.length);
	}

	public static String join(final int[] array, final String separator) {
		if (array == null) {
			return null;
		}
		return join(array, separator, 0, array.length);
	}

	public static String join(final byte[] array, final String separator) {
		if (array == null) {
			return null;
		}
		return join(array, separator, 0, array.length);
	}

	public static String join(final short[] array, final String separator) {
		if (array == null) {
			return null;
		}
		return join(array, separator, 0, array.length);
	}

	public static String join(final double[] array, final String separator) {
		if (array == null) {
			return null;
		}
		return join(array, separator, 0, array.length);
	}

	public static String join(final float[] array, final String separator) {
		if (array == null) {
			return null;
		}
		return join(array, separator, 0, array.length);
	}

	/**
	 * <p>
	 * Joins the elements of the provided array into a single String containing
	 * the provided list of elements.
	 * </p>
	 * 
	 * <p>
	 * No delimiter is added before or after the list. A {@code null} separator
	 * is the same as an empty String (""). Null objects or empty strings within
	 * the array are represented by empty strings.
	 * </p>
	 * 
	 * <pre>
	 * join(null, *, *, *)                = null
	 * join([], *, *, *)                  = ""
	 * join([null], *, *, *)              = ""
	 * join(["a", "b", "c"], "--", 0, 3)  = "a--b--c"
	 * join(["a", "b", "c"], "--", 1, 3)  = "b--c"
	 * join(["a", "b", "c"], "--", 2, 3)  = "c"
	 * join(["a", "b", "c"], "--", 2, 2)  = ""
	 * join(["a", "b", "c"], null, 0, 3)  = "abc"
	 * join(["a", "b", "c"], "", 0, 3)    = "abc"
	 * join([null, "", "a"], ',', 0, 3)   = ",,a"
	 * </pre>
	 * 
	 * @param array
	 *            the array of values to join together, may be null
	 * @param separator
	 *            the separator character to use, null treated as ""
	 * @param startIndex
	 *            the first index to start joining from.
	 * @param endIndex
	 *            the index to stop joining from (exclusive).
	 * @return the joined String, {@code null} if null array input; or the empty
	 *         string
	 */
	public static String join(final Object[] array, String separator,
			final int startIndex, final int endIndex) {
		if (array == null) {
			return null;
		}
		if (separator == null) {
			separator = EMPTY;
		}

		final int noOfItems = endIndex - startIndex;
		if (noOfItems <= 0) {
			return EMPTY;
		}

		final StringBuilder buf = new StringBuilder(noOfItems * 16);

		for (int i = startIndex; i < endIndex; i++) {
			if (i > startIndex) {
				buf.append(separator);
			}
			if (array[i] != null) {
				buf.append(array[i]);
			}
		}
		return buf.toString();
	}

	public static String join(final long[] array, String separator,
			final int startIndex, final int endIndex) {
		if (array == null) {
			return null;
		}
		Long[] arrayNew = new Long[array.length];
		for (int i = 0; i < array.length; i++) {
			arrayNew[i] = array[i];
		}
		return join(arrayNew, separator, startIndex, endIndex);
	}

	public static String join(final int[] array, String separator,
			final int startIndex, final int endIndex) {
		if (array == null) {
			return null;
		}
		Integer[] arrayNew = new Integer[array.length];
		for (int i = 0; i < array.length; i++) {
			arrayNew[i] = array[i];
		}
		return join(arrayNew, separator, startIndex, endIndex);
	}

	public static String join(final short[] array, String separator,
			final int startIndex, final int endIndex) {
		if (array == null) {
			return null;
		}
		Short[] arrayNew = new Short[array.length];
		for (int i = 0; i < array.length; i++) {
			arrayNew[i] = array[i];
		}
		return join(arrayNew, separator, startIndex, endIndex);
	}

	public static String join(final byte[] array, String separator,
			final int startIndex, final int endIndex) {
		if (array == null) {
			return null;
		}
		Byte[] arrayNew = new Byte[array.length];
		for (int i = 0; i < array.length; i++) {
			arrayNew[i] = array[i];
		}
		return join(arrayNew, separator, startIndex, endIndex);
	}

	public static String join(final char[] array, String separator,
			final int startIndex, final int endIndex) {
		if (array == null) {
			return null;
		}
		Character[] arrayNew = new Character[array.length];
		for (int i = 0; i < array.length; i++) {
			arrayNew[i] = array[i];
		}
		return join(arrayNew, separator, startIndex, endIndex);
	}

	public static String join(final double[] array, String separator,
			final int startIndex, final int endIndex) {
		if (array == null) {
			return null;
		}
		Double[] arrayNew = new Double[array.length];
		for (int i = 0; i < array.length; i++) {
			arrayNew[i] = array[i];
		}
		return join(arrayNew, separator, startIndex, endIndex);
	}

	public static String join(final float[] array, String separator,
			final int startIndex, final int endIndex) {
		if (array == null) {
			return null;
		}
		Float[] arrayNew = new Float[array.length];
		for (int i = 0; i < array.length; i++) {
			arrayNew[i] = array[i];
		}
		return join(arrayNew, separator, startIndex, endIndex);
	}

	/**
	 * Returns a new string of a given length such that the beginning of the
	 * string is padded.
	 * 
	 * @param value
	 *            The input String
	 * @param pad
	 *            The pad
	 * @param length
	 *            Length of the String we want
	 * @return Padded String
	 */
	public static String padLeft(final String value, final String pad,
			final int length) {
		if (value.length() > length) {
			return value;
		}
		return append(repeat(pad, length - value.length()), value);
	}

	/**
	 * Returns a new string of a given length such that the ending of the string
	 * is padded.
	 * 
	 * @param value
	 *            The input String
	 * @param length
	 *            Max length of String.
	 * @param pad
	 *            Character to repeat
	 * @return Right padded String
	 */
	public static String padRight(final String value, String pad,
			final int length) {
		if (value.length() > length) {
			return value;
		}
		return append(value, repeat(pad, length - value.length()));
	}

	/**
	 * Transform to camelCase
	 * 
	 * @param value
	 *            The input String
	 * @return String in camelCase.
	 */
	public static String toCamelCase(final String value) {
		return lowerFirst(toStudlyCase(value));
	}

	/**
	 * Transform to StudlyCaps.
	 * 
	 * @param value
	 *            The input String
	 * @return String in StudlyCaps.
	 */
	public static String toStudlyCase(final String value) {
		if (isEmpty(value)) {
			return null;
		}
		String[] words = collapseWhitespace(trim(value)).split(
				"\\s*(_|-|\\s)\\s*");
		StringBuffer sb = new StringBuffer();
		for (String w : words) {
			sb.append(upperFirst(w));
		}
		return sb.toString();
	}

	/**
	 * Decamelize String
	 * 
	 * <pre>
	 * toDecamelize("hello Hoe",null) = "hello hoe"
	 * toDecamelize("hello Hoe","-") = "hello-hoe"
	 * </pre>
	 * 
	 * @param value
	 *            The input String
	 * @param separator
	 *            The separator character to use
	 * @return String decamelized.
	 */
	public static String toDecamelize(final String value, String separator) {
		if (separator == null) {
			separator = SPACE;
		}
		String camelCasedString = toCamelCase(value);
		String[] words = camelCasedString.split("(?=\\p{Upper})");
		StringBuffer sb = new StringBuffer();
		for (String w : words) {
			sb.append(lowerFirst(w)).append(separator);
		}
		return removeLast(sb.toString());
	}

	/**
	 * Transform to kebab-case.
	 * 
	 * @param value
	 *            The input String
	 * @return String in kebab-case.
	 */
	public static String toKebabCase(final String value) {
		return toDecamelize(value, "-");
	}

	/**
	 * Transform to snake_case.
	 * 
	 * @param value
	 *            The input String
	 * @return String in snake_case.
	 */
	public static String toSnakeCase(final String value) {
		return toDecamelize(value, "_");
	}

	/**
	 * Split lines to an array
	 * 
	 * @param input
	 *            The input String
	 * @return lines in an array
	 */
	public static String[] lines(String input) {
		if (input == null) {
			return new String[0];
		}
		return input.split("\r\n?|\n");
	}

	/**
	 * <p>
	 * Reverses a String.
	 * </p>
	 * 
	 * <pre>
	 * reverse(&quot;bat&quot;) = &quot;tab&quot;
	 * </pre>
	 * 
	 * @param str
	 *            the String to reverse, may be null
	 * @return the reversed String, {@code null} if null String input
	 */
	public static String reverse(final String str) {
		if (str == null) {
			return null;
		}
		return new StringBuilder(str).reverse().toString();
	}

	/**
	 * Returns a repeated string given a multiplier.
	 * 
	 * @param value
	 *            The input String
	 * @param multiplier
	 *            Number of repeats
	 * @return The String repeated
	 */
	public static String repeat(final String value, final int multiplier) {
		if (isEmpty(value)) {
			return null;
		}
		if (multiplier < 0) {
			throw new IllegalArgumentException(Long.toString(multiplier));
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < multiplier; i++) {
			sb.append(value);
		}
		return sb.toString();
	}

	/**
	 * Returns a repeated string given a multiplier and separated separator
	 * 
	 * <pre>
	 * repeat(&quot;hoe&quot;, &quot;-&quot;, 3) = &quot;hoe-hoe-hoe&quot;
	 * </pre>
	 * 
	 * @param value
	 *            The input String
	 * @param separator
	 *            The separator character to use, null treated as ""
	 * @param multiplier
	 *            Number of repeats
	 * @return The String repeated
	 */
	public static String repeat(final String value, String separator,
			final int multiplier) {
		if (isEmpty(value)) {
			return null;
		}
		if (multiplier < 0) {
			throw new IllegalArgumentException(Long.toString(multiplier));
		}
		if (separator == null) {
			separator = EMPTY;
		}

		String[] strArr = new String[multiplier];
		for (int i = 0; i < multiplier; i++) {
			strArr[i] = value;
		}
		return join(strArr, separator);
	}

	/**
	 * Return remove the first char of the string
	 * 
	 * @param value
	 *            The input String
	 * @return String tail
	 */
	public static String removeFirst(final String value) {
		return right(value, value.length() - 1);
	}

	/**
	 * Return remove the first char of the string
	 * 
	 * @param value
	 *            The input String
	 * @return String head
	 */
	public static String removeLast(final String value) {
		return left(value, value.length() - 1);
	}

	/**
	 * Remove all non word characters.
	 * 
	 * @param value
	 *            The input String
	 * @return String without non-word characters
	 */
	public static String removeNonWords(final String value) {
		return value.replaceAll("[^\\w]+", "");
	}

	/**
	 * Converts the first character of string to upper case.
	 * 
	 * @param value
	 *            The string to convert.
	 * @return Returns the converted string.
	 */
	public static String upperFirst(final String value) {
		if (isEmpty(value)) {
			return null;
		}
		return left(value, 1).toUpperCase() + right(value, value.length() - 1);
	}

	/**
	 * Converts the first character of string to lower case.
	 * 
	 * @param value
	 *            The string to convert.
	 * @return Returns the converted string.
	 */
	public static String lowerFirst(final String value) {
		if (isEmpty(value)) {
			return null;
		}
		return left(value, 1).toLowerCase() + right(value, value.length() - 1);
	}

	/**
	 * Get a md5 encrypted string
	 * 
	 * @param source
	 * @param bit
	 *            16 or 32
	 * @return
	 */
	private static String md5(String source, int bit) {
		if (source == null || source == "") {
			return null;
		}
		MessageDigest md;
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(source.getBytes(Charset.defaultCharset()));
			byte tmp[] = md.digest();
			char chstr[] = new char[tmp.length * 2];
			int k = 0;
			for (int i = 0; i < tmp.length; i++) {
				byte byte0 = tmp[i];
				chstr[k++] = hexDigits[byte0 >>> 4 & 0xf];
				chstr[k++] = hexDigits[byte0 & 0xf];
			}
			source = new String(chstr);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if (bit == 16) {
			return source.substring(8, 24);
		}
		return source;
	}

	/**
	 * Converts all HTML entities to applicable characters.
	 * 
	 * @param encodedHtml
	 *            The encoded HTML
	 * @param encodedType
	 *            0:html code, 1:decimal code ; default:1
	 * @return The decoded HTML
	 */
	private static String htmlDecode(final String encodedHtml, int encodedType) {
		String str = collapseWhitespace(encodedHtml);
		for (Entry<String, HtmlChar> entry : HtmlCharCode.decodedEntities
				.entrySet()) {
			switch (encodedType) {
			case 0:
				str = str
						.replaceAll(entry.getKey(), entry.getValue().getName());
				break;
			case 1:
				str = str.replaceAll(entry.getKey(), entry.getValue()
						.getDecimalCode());
				break;
			default:
				str = str.replaceAll(entry.getKey(), entry.getValue()
						.getDecimalCode());
				break;
			}
		}
		return str;
	}

	/**
	 * Convert all applicable characters to HTML entities.
	 * 
	 * @param html
	 *            The HTML to encode
	 * @param encodedType
	 *            0:html code, 1:decimal code ; default:1
	 * @return The encoded data
	 */
	private static String htmlEncode(final String html, int encodedType) {
		String str = collapseWhitespace(html);
		for (Entry<String, HtmlChar> entry : HtmlCharCode.decodedEntities
				.entrySet()) {
			switch (encodedType) {
			case 0:
				str = str
						.replaceAll(entry.getValue().getName(), entry.getKey());
				break;
			case 1:
				str = str.replaceAll(entry.getValue().getDecimalCode(),
						entry.getKey());
				break;
			default:
				str = str.replaceAll(entry.getValue().getDecimalCode(),
						entry.getKey());
				break;
			}
		}
		return str;
	}
}
