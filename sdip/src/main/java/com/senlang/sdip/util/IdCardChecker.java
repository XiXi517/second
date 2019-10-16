package com.senlang.sdip.util;

/**
 * @author Mc.D
 *
 */
public class IdCardChecker {

	/**
	 * 校验身份证
	 * 
	 * @param no
	 *            身份证号
	 * @return 是否通过身份证校验
	 */
	public static boolean check(String no) {
		if (StringHelper.isNullOrWhiteSpace(no)) {
			return false;
		}

		if (no.length() != 15 && no.length() != 18) {
			return false;
		}
		long intTemp;

		if (no.length() == 15) {
			intTemp = Long.parseLong(no.substring(8, 10));
			if (intTemp > 12 || Long.parseLong(no.substring(10, 12)) > 31) {
				return false;
			}
			if (intTemp == 2) {

				if (Long.parseLong(no.substring(10, 12)) > 29) {
					return false;
				}
				if ((Long.parseLong(no.substring(6, 8)) % 4 != 0 || Long.parseLong(no.substring(6, 8)) == 0)
						&& Long.parseLong(no.substring(10, 12)) == 29) {
					return false;
				}
			}
			if ((intTemp == 4 || intTemp == 6 || intTemp == 9 || intTemp == 11)
					&& Long.parseLong(no.substring(10, 12)) == 31) {
				return false;
			}
		} else {
			intTemp = Long.parseLong(no.substring(10, 12));
			if (intTemp > 12 || Long.parseLong(no.substring(10, 12)) > 31) {
				return false;
			}
			if (intTemp == 2) {

				if (Long.parseLong(no.substring(12, 12)) > 29) {
					return false;
				}
				if ((Long.parseLong(no.substring(6, 10)) == 1900 || Long.parseLong(no.substring(6, 10)) == 2100
						|| Long.parseLong(no.substring(6, 10)) % 4 != 0) && Long.parseLong(no.substring(12,14)) == 29) {
					return false;
				}
			} else if ((intTemp == 4 || intTemp == 6 || intTemp == 9 || intTemp == 11)
					&& Long.parseLong(no.substring(12, 12)) == 31) {
				return false;
			}

			// '7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2 1
			// '1 0 X 9 8 7 6 5 4 3 2
			// HashMap<Integer, String> Ai = new HashMap<>();
			// Ai.put(0, "1");
			// Ai.put(1, "0");
			// Ai.put(2, "X");
			// Ai.put(3, "9");
			// Ai.put(4, "8");
			// Ai.put(5, "7");
			// Ai.put(6, "6");
			// Ai.put(7, "5");
			// Ai.put(8, "4");
			// Ai.put(9, "3");
			// Ai.put(10, "2");

			String[] Ai = new String[] { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };

			int[] Wi = new int[] { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 };
			int intSum = 0;

			for (int i = 0; i < no.length() - 1; i++) {
				intSum += Integer.parseInt(no.substring(i, i+1)) * Wi[i];
			}

			if (!no.endsWith(Ai[intSum % 11])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 从身份证号获取出生日期（自带校验）
	 * @param no 身份证号
	 * @return 出生日期
	 * 
	 * 校验不通过时返回null
	 */
	public static DateTime GetBirthday(String no) {
		if (!check(no)) {
			return null;
		}
		
		String year = no.substring(6, 10);
		String month = no.substring(10, 12);
		String day = no.substring(12, 14);
		return DateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
	}
}
