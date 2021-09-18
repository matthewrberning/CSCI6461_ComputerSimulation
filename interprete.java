public class interprete{
    public void interpret(){
        int regGP_update = Integer.parseInt(tfGPROp.getText());
        //Todo: interpret the text here,
        Instruction instruction_now = new Instruction(tfGPROp.getText());   // get the instruction
        int i32Idx = instruction_now.get_R();
        //int i32Idx = ? R0 - R3
        int i32Value = instruction_now.get_Address();



        int i32I = instruction_now.get_I();
        int i32IX = instruction_now.get_IX();

        // there will be a function of computing the Effective Address in the future
        int i32EAdress = i32Value;
        if(i32I == 0){
            if(i32I == 0){
                if(i32IX == 0){
                    i32EAdress = i32Value;
                }
                else{
                    // EA = c(IX) + c(Address Field)
                }
            }
            if(i32I == 1){
                if(i32IX == 0){
                    //EA = c(c(Address Field))
                }
                else{
                    //c(c(IX) + c(Address Field))
                }
            }
        }



        //int i32Value = ?
        int i32Op = instruction_now.get_Opcode();
        //int i32Op = ? store or load
        if (i32Op == 1){
            this.x86Cpu.SetGPR(i32Idx, i32Value);
        }
        //if load -> this.x86Cpu.SetGPR(i32Idx, i32Value);
        if(i32Op == 2){
            this.x86Cpu.SetMemory(i32Idx, i32Value);
        }
        //if store -> this.x86Cpu.SetMemory(i32Idx, i32Value);
    }

}
