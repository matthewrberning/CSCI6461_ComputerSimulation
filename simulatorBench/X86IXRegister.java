package simulatorBench;

import java.util.BitSet;
import java.util.Observable;
import java.util.Observer;

public class X86IXRegister {
	protected BitSet bitset;
	protected int value;
	protected int nbits;
	protected abstractUpdate subject;
	
	public X86IXRegister(abstractUpdate subject) {
		this.subject = subject;
		this.bitset = new BitSet(16);
		this.value = 0;
		this.nbits = 16;
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
}
