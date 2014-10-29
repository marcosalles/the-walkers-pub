package models.magic;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public enum Color {
	L,		//land
	A,		//artifact
	C,		//colorless
	W,		//white
	U,		//blue
	B,		//black
	R,		//red
	G,		//green
	LG,
	AL,
	AW,
	AU,
	AB,
	AR,
	AG,
	WU,
	WB,
	WR,
	WG,
	UB,
	UR,
	UG,
	BR,
	BG,
	RG,
	AWU,
	AWB,
	AWG,
	AUB,
	AUR,
	AUG,
	ABR,
	ARG,
	WUB,
	WUR,
	WUG,
	WBR,
	WBG,
	WRG,
	UBR,
	UBG,
	URG,
	BRG,
	AWUB,
	AUBR,
	WUBR,
	WUBG,
	WURG,
	WBRG,
	UBRG,
	WUBRG,
	AWUBRG;
	
	public static Random random;
	public static Color RANDOM() {
		if (random == null) random = new Random();
		int n = random.nextInt(values().length);
		return values()[n];
	}
	public static List<Color> ALL() {
		return Arrays.asList(values());
	}
}
