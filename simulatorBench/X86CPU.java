package simulatorBench;

import java.util.BitSet;

public class X86CPU {
	private X86GPRegister regGPGroup;
	private X86IXRegister regX1, regX2, regX3;
	private X86PCRegister regPC;
	private String[][] GPRContent; 
	private String[][] IXContent;
	private abstractUpdate subject;
	private X86Memory memory;
	
	public X86CPU(abstractUpdate subject,  X86Memory memory) {
		this.subject = subject;
		this.memory = memory;

		init();
	}
	
	private void init() {
		regPC = new X86PCRegister(subject);
		regX1 = new X86IXRegister(subject);
		regX2 = new X86IXRegister(subject);
		regX3 = new X86IXRegister(subject);
		regGPGroup = new X86GPRegister(subject);

		GPRContent = new String[4][2];
		IXContent = new String[3][2];

		initGPRContent();
		initIXContent();
	}
	
	private void initGPRContent() {
		updateGPRContent();
	}
	
	private void initIXContent() {
		updateIXContent();
	}
	
	public void SetMemory(int i32Idx, int i32Value)
	{
		memory.Set(i32Idx,Integer.toString(i32Value));
	}
	
	public void SetPC(int value)
	{
		regPC.SetValue(value);
	}
	
	public String GetPC() {
		return Integer.toString(regPC.GetValueWithInt());
	}
	
	public int SetGPR(int i8Idx, int value) 
	{
			if (i8Idx >= 0 && i8Idx <4)
			{
				regGPGroup.SetValue(i8Idx,value);
			}
			else 
			{
			//this.SetMFR(6);
			return -2;
			}
		
		updateGPRContent();
		return 0;
	}
	
	private void updateGPRContent() {
		// update GPRs content
		GPRContent[0][0] = "0";
		GPRContent[0][1] = Integer.toString(regGPGroup.GetValueWithInt(0));
		GPRContent[1][0] = "1";
		GPRContent[1][1] = Integer.toString(regGPGroup.GetValueWithInt(1));
		GPRContent[2][0] = "2";
		GPRContent[2][1] = Integer.toString(regGPGroup.GetValueWithInt(2));
		GPRContent[3][0] = "3";
		GPRContent[3][1] = Integer.toString(regGPGroup.GetValueWithInt(3));
		this.subject.updateData(regGPGroup);
	}
	
	private void updateIXContent() {
		IXContent[0][0] = "1";
		IXContent[0][1] = Integer.toString(regX1.GetValueWithInt());
		IXContent[1][0] = "2";
		IXContent[1][1] = Integer.toString(regX2.GetValueWithInt());
		IXContent[2][0] = "3";
		IXContent[2][1] = Integer.toString(regX3.GetValueWithInt());
		this.subject.updateData(regX1);
	}
}


