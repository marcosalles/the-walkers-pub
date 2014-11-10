package models.magic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public enum ColorEnum {
	C, // colorless
	A, // artifact
	L, // land
	W, // white
	U, // blue
	B, // black
	R, // red
	G, // green
	LG, AL, AW, AU, AB, AR, AG, WU, WB, WR, WG, UB, UR, UG, BR, BG, RG, AWU, AWB, AWG, AUB, AUR, AUG, ABR, ARG, WUB, WUR, WUG, WBR, WBG, WRG, UBR, UBG, URG, BRG, AWUB, AUBR, WUBR, WUBG, WURG, WBRG, UBRG, WUBRG, AWUBRG;

	public static Random random;

	public static ColorEnum RANDOM() {
		if (random == null) {
			random = new Random();
		}
		int n = random.nextInt(values().length);
		return values()[n];
	}

	public static ColorEnum fromString(String colorAsString) {
		ColorEnum color = C;
		try {
			color = ColorEnum.valueOf(ColorEnum.class, colorAsString);
		} catch (IllegalArgumentException e) {
		}
		return color;
	}

	public static List<ColorEnum> ALL() {
		return Arrays.asList(values());
	}

	public static Map<String, Boolean> MAP() {
		Map<String,Boolean> map = new HashMap<String, Boolean>();
		map.put(C.toString(), false);
		map.put(W.toString(), false);
		map.put(U.toString(), false);
		map.put(B.toString(), false);
		map.put(R.toString(), false);
		map.put(G.toString(), false);
		return map;
	}
}
