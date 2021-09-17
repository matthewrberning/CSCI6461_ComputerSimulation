package simulatorBench;

import java.util.*;

public class X86GPRegister {
	protected BitSet[] bitset = new BitSet[4];
	protected int[] value = new int[4];

	protected int nbits;
	protected abstractUpdate subject;

	public X86GPRegister(abstractUpdate subject) {
		this.bitset[0] = new BitSet(16);
		this.bitset[1] = new BitSet(16);
		this.bitset[2] = new BitSet(16);
		this.bitset[3] = new BitSet(16);

		this.value[0] = 0;
		this.value[1] = 0;
		this.value[2] = 0;
		this.value[3] = 0;

		this.subject = subject;
		this.nbits = 16;
	}	

public int GetValueWithInt(int i8Idx) {
	int bitInteger = 0;
	for (int i = 0; i < nbits -1; i++)
		if (this.bitset[i8Idx].get(i))
			bitInteger |= (1 << i);
	if (this.bitset[i8Idx].get(nbits-1) == false) {
		value[i8Idx] = bitInteger;
	} else {
		value[i8Idx] = -bitInteger;
	}
	return value[i8Idx];
}

public String GetBinaryString(int i8Idx) {
	StringBuilder s = new StringBuilder();
	for (int i = 0; i < nbits; i++) {
		s.append(bitset[i8Idx].get(i) == true ? 1 : 0);
	}
	//this.subject.updateUserConsole(bitset.toString() + "\n");
	return s.toString();
}

public void SetValue(int i8Idx,int value) {
	
	int i8value = java.lang.Math.abs((int) value);

	bitset[i8Idx].clear();
	int index = 0;
	while (i8value != 0L) {
		if (i8value % 2L != 0) {
			this.bitset[i8Idx].set(index);
		}
		++index;
		i8value = i8value >>> 1;
	}

	if (value < 0) { // is negative number, set the highest bit to 1
		this.bitset[i8Idx].set(nbits-1);
	}
	this.value[i8Idx] = value;
	this.subject.updateData(this);
}
}

