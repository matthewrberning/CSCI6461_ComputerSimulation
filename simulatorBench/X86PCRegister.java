package simulatorBench;

import java.util.BitSet;


public class X86PCRegister {
	protected BitSet bitset;
	protected int value;
	protected int nbits;
	protected abstractUpdate subject;
	
	public X86PCRegister(abstractUpdate subject) {
		this.bitset = new BitSet(12);
		this.subject = subject;
		// PC's first value shouled be 6 as 0-5 for reserved
		this.value = 6;
		this.nbits = 12;
	}
	
	public int GetValueWithInt() {
		int bitInteger = 0;
		for (int i = 0; i < nbits -1; i++)
			if (this.bitset.get(i))
				bitInteger |= (1 << i);
		if (this.bitset.get(nbits-1) == false) {
			value = bitInteger;
		} else {
			value = -bitInteger;
		}
		return value;
	}

	public String GetBinaryString() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < nbits; i++) {
			s.append(bitset.get(i) == true ? 1 : 0);
		}
		//this.subject.updateUserConsole(bitset.toString() + "\n");
		return s.toString();
	}
	
	public void SetValue(int value) {
		int i32value = java.lang.Math.abs((int) value);

		bitset.clear();
		int index = 0;
		while (i32value != 0L) {
			if (i32value % 2L != 0) {
				this.bitset.set(index);
			}
			++index;
			i32value = i32value >>> 1;
		}

		if (value < 0) { // is negative number, set the highest bit to 1
			this.bitset.set(nbits-1);
		}
		
		this.value = value;
		this.subject.updateData(this);
	}
}
