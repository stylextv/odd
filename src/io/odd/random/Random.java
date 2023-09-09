package io.odd.random;

import java.util.UUID;

public class Random {
	
	private static final long NEXT_STATE_MULTIPLIER = 214013;
	private static final long NEXT_STATE_INCREMENT = 2531011;
	private static final long SEED_STATE_SKIP_AMOUNT = 2;
	
	private static final long LOWER_LONG_HALF_MASK = 0xFFFFFFFFL;
	private static final long LOWEST_LONG_QUARTER_MASK = 0xFFFFL;
	private static final long LOWEST_LONG_EIGHTH_MASK = 0xFFL;
	private static final long LOWEST_LONG_BIT_MASK = 0x1L;
	
	private static final int ASCII_CHAR_RANGE = 128;
	private static final int ASCII_LETTER_CHAR_RANGE = 26;
	private static final int ASCII_LETTER_CHAR_OFFSET = 65;
	private static final int ASCII_DIGIT_CHAR_RANGE = 10;
	private static final int ASCII_DIGIT_CHAR_OFFSET = 48;
	
	private long state;
	
	public Random() {
		this(System.nanoTime());
	}
	
	public Random(long seedState) {
		seed(seedState);
	}
	
	public UUID nextUUID() {
		long l1 = nextLong();
		long l2 = nextLong();
		
		return new UUID(l1, l2);
	}
	
	public float nextFloat(float range, float offset) {
		return nextFloat(range) + offset;
	}
	
	public float nextFloat(float range) {
		return nextFloat() * range;
	}
	
	public float nextFloat() {
		float f = (float) nextLong() / Long.MAX_VALUE;
		
		if(f < 0) return -f;
		return f;
	}
	
	public double nextDouble(double range, double offset) {
		return nextDouble(range) + offset;
	}
	
	public double nextDouble(double range) {
		return nextDouble() * range;
	}
	
	public double nextDouble() {
		double d = (double) nextLong() / Long.MAX_VALUE;
		
		if(d < 0) return -d;
		return d;
	}
	
	public boolean nextBool() {
		return (nextLong() & LOWEST_LONG_BIT_MASK) == 1;
	}
	
	public byte nextByte(int range, int offset) {
		return (byte) (nextByte(range) + offset);
	}
	
	public byte nextByte(int range) {
		int b = nextByte() % range;
		
		if(b < 0) return (byte) (b + range);
		return (byte) b;
	}
	
	public byte nextByte() {
		return (byte) (nextLong() % LOWEST_LONG_EIGHTH_MASK);
	}
	
	public char nextAsciiDigitChar() {
		return nextChar(ASCII_DIGIT_CHAR_RANGE, ASCII_DIGIT_CHAR_OFFSET);
	}
	
	public char nextAsciiLetterChar() {
		return nextChar(ASCII_LETTER_CHAR_RANGE, ASCII_LETTER_CHAR_OFFSET);
	}
	
	public char nextAsciiChar() {
		return nextChar(ASCII_CHAR_RANGE);
	}
	
	public char nextChar(int range, int offset) {
		return (char) (nextChar(range) + offset);
	}
	
	public char nextChar(int range) {
		return (char) (nextChar() % range);
	}
	
	public char nextChar() {
		return (char) (nextLong() & LOWEST_LONG_QUARTER_MASK);
	}
	
	public short nextShort(int range, int offset) {
		return (short) (nextShort(range) + offset);
	}
	
	public short nextShort(int range) {
		int s = nextShort() % range;
		
		if(s < 0) return (short) (s + range);
		return (short) s;
	}
	
	public short nextShort() {
		return (short) (nextLong() & LOWEST_LONG_QUARTER_MASK);
	}
	
	public int nextInt(int range, int offset) {
		return nextInt(range) + offset;
	}
	
	public int nextInt(int range) {
		int i = nextInt() % range;
		
		if(i < 0) return i + range;
		return i;
	}
	
	public int nextInt() {
		return (int) (nextLong() & LOWER_LONG_HALF_MASK);
	}
	
	public long nextLong(long range, long offset) {
		return nextLong(range) + offset;
	}
	
	public long nextLong(long range) {
		long l = nextLong() % range;
		
		if(l < 0) return l + range;
		return l;
	}
	
	public long nextLong() {
		return state();
	}
	
	public void seed(long state) {
		this.state = state;
		
		for(int i = 0; i < SEED_STATE_SKIP_AMOUNT; i++) advanceState();
	}
	
	private long state() {
		long s = state;
		
		advanceState();
		return s;
	}
	
	private void advanceState() {
		state = state * NEXT_STATE_MULTIPLIER + NEXT_STATE_INCREMENT;
	}
	
}
