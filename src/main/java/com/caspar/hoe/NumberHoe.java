package com.caspar.hoe;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Number manipulation library
 * 
 * @author caspar.chen
 * @date 2017-7-26
 */
public class NumberHoe {

	private static final String[] CN_UPPER_NUMBER = { "零", "壹", "贰", "叁", "肆",
			"伍", "陆", "柒", "捌", "玖" };
	private static final String[] CN_UPPER_MONETRAY_UNIT = { "分", "角", "元",
			"拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾",
			"佰", "仟" };
	private static final String CN_FULL = "整";
	private static final String CN_NEGATIVE = "负";
	/**
	 * 金额的精度，默认值为2
	 */
	private static final int MONEY_PRECISION = 2;
	private static final String CN_ZEOR_FULL = "零元" + CN_FULL;

	/**
	 * Check if the string is a positive integer
	 * @param str	input string
	 * @return	The String to check
	 */
	public static boolean isPositiveInteger(String str) {
		return Pattern.compile("[1-9]\\d*").matcher(str).matches();
	}
	
	/**
	 * Check if the string is a Non-positive integer (0 and negative integer)
	 * @param str	input string
	 * @return	The String to check
	 */
	public static boolean isNonPositiveInteger(String str) {
		return Pattern.compile("-[1-9]\\d*|0*").matcher(str).matches();
	}
	
	/**
	 * Check if the string is a negative integer
	 * @param str	input string
	 * @return	The String to check
	 */
	public static boolean isNegativeInteger(String str) {
		return Pattern.compile("-[1-9]\\d*").matcher(str).matches();
	}
	
	/**
	 * Check if the string is a nonnegative integer (0 and positive integer)
	 * @param str	input string
	 * @return	The String to check
	 */
	public static boolean isNonNegativeInteger(String str) {
		return Pattern.compile("[1-9]\\d*|0").matcher(str).matches();
	}
	
	/**
	 * Check if the string is a number
	 * @param str	input string
	 * @return	The String to check
	 */
	public static boolean isNumber(String str) {
		return Pattern.compile("(\\-|\\+)?\\d+(\\.\\d+)?").matcher(str).matches();
	}
	
	/**
	 * Greatest common divisor
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int gcd(int a, int b) {
		return a % b == 0 ? b : gcd(b, a % b);
	}

	/**
	 * Greatest common divisor
	 */
	public static int gcd(int... numbers) {
		int[] intArr = Arrays.copyOf(numbers, numbers.length);
		Arrays.sort(intArr);
		int tmpGcd = intArr[0];
		for (int i = 0; i < intArr.length - 1; i++) {
			tmpGcd = gcd(tmpGcd, intArr[i + 1]);
		}
		return tmpGcd;
	}

	/**
	 * Least common multiple
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}

	/**
	 * Least common multiple
	 */
	public static int lcm(int... numbers) {
		int[] intArr = Arrays.copyOf(numbers, numbers.length);
		Arrays.sort(intArr);
		int product = 0;
		for (int i = 0; i < intArr.length - 1; i++) {
			product = intArr[i] * intArr[i + 1];
		}
		return product / gcd(numbers);
	}

	/**
	 * 把输入的金额转换为汉语中人民币的大写
	 * 
	 * @param money
	 *            输入的金额
	 * @return 对应的汉语大写
	 */
	public static String toCNMontrayUnit(BigDecimal money) {
		StringBuffer sb = new StringBuffer();
		// -1, 0, or 1 as the value of this BigDecimal is negative, zero, or
		// positive.
		int signum = money.signum();
		// 零元整的情况
		if (signum == 0) {
			return CN_ZEOR_FULL;
		}
		// 这里会进行金额的四舍五入
		long number = money.movePointRight(MONEY_PRECISION).setScale(0, 4)
				.abs().longValue();
		// 得到小数点后两位值
		long scale = number % 100;
		int numUnit = 0;
		int numIndex = 0;
		boolean getZero = false;
		// 判断最后两位数，一共有四中情况：00 = 0, 01 = 1, 10, 11
		if (!(scale > 0)) {
			numIndex = 2;
			number = number / 100;
			getZero = true;
		}
		if ((scale > 0) && (!(scale % 10 > 0))) {
			numIndex = 1;
			number = number / 10;
			getZero = true;
		}
		int zeroSize = 0;
		while (true) {
			if (number <= 0) {
				break;
			}
			// 每次获取到最后一个数
			numUnit = (int) (number % 10);
			if (numUnit > 0) {
				if ((numIndex == 9) && (zeroSize >= 3)) {
					sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
				}
				if ((numIndex == 13) && (zeroSize >= 3)) {
					sb.insert(0, CN_UPPER_MONETRAY_UNIT[10]);
				}
				sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
				sb.insert(0, CN_UPPER_NUMBER[numUnit]);
				getZero = false;
				zeroSize = 0;
			} else {
				++zeroSize;
				if (!(getZero)) {
					sb.insert(0, CN_UPPER_NUMBER[numUnit]);
				}
				if (numIndex == 2) {
					if (number > 0) {
						sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
					}
				} else if (((numIndex - 2) % 4 == 0) && (number % 1000 > 0)) {
					sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
				}
				getZero = true;
			}
			// 让number每次都去掉最后一个数
			number = number / 10;
			++numIndex;
		}
		// 如果signum == -1，则说明输入的数字为负数，就在最前面追加特殊字符：负
		if (signum == -1) {
			sb.insert(0, CN_NEGATIVE);
		}
		// 输入的数字小数点后两位为"00"的情况，则要在最后追加特殊字符：整
		if (!(scale > 0)) {
			sb.append(CN_FULL);
		}
		return sb.toString();
	}

	/**
	 * 把输入的金额转换为汉语中人民币的大写
	 * 
	 * @param numberStr
	 *            输入的金额
	 * @return 对应的汉语大写
	 */
	public static String toCNMontrayUnit(String numberStr) {
		return toCNMontrayUnit(new BigDecimal(numberStr));
	}

}
