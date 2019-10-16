/**
 * 
 */
package com.senlang.sdip.util.poi;

import java.util.ArrayList;

import com.senlang.sdip.util.FormatException;

/**
 * @author Mc.D
 *
 */
public final class Position {
	private final static ArrayList<String> position = new ArrayList<>();
	static {
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < 702; i++) {
			position.add(i < alpha.length() ? String.valueOf(alpha.charAt(i))
					: String.valueOf(alpha.charAt((i / 26) - 1) + String.valueOf(alpha.charAt(i % 26))));
		}
		// for (int i = 0; i < position.length; i++) {
		// System.out.println(position[i]);
		// }
	}

	public static int get(String position) throws FormatException {
		if (!Position.position.contains(position)) {
			throw new FormatException();
		}
		
		return Position.position.indexOf(position);
	}
}
