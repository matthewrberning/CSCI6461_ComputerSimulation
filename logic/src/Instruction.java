public class Instruction {
    public int[] Opcode;
    public int[] IX;
    public int[] R;
    public int I;
    public int[] Address;
    public String content_16;
    public int [] content_2;
    public int content_10;

    public void Instruction(){
        Opcode = new int[6];
        IX = new int[2];
        R = new int[2];
        I = 0;
        Address = new int[5];
        this.content_10 = 0;
        this.content_16 = "0000";
        this.content_2 = new int[16];
        for(int i = 0; i < 16; i++){
            this.content_2[i] = 0;
        }
    }

    public void tran_16to2(){

        String Str1 = this.content_16;
        int[] con_2 = new int[16];
        char[] Str2 = new char[4];
        int ins = 0;                  // all instruction value
        int inp = 0;                               // per digit

        try {
            Str1.getChars(0, 16, Str2, 0);
            for(int i = 0; i < 4; i++){
                if(Str2[i] < 'a'){
                    inp = Str2[i] - '0';
                }else{
                    inp = Str2[i] - 'a' + 10;
                }
                ins = ins + inp * (int)Math.pow(16, 3 - i);

            }

            this.content_10 = ins;                                  // the instruction 10

            for(int j = 0; j < 16; j++){
                con_2[j] = ins / (int)Math.pow(2, 15 - j);
                ins = ins % (int)Math.pow(2, 15 - j);
            }
            this.content_2 = con_2;

        }
        catch( Exception ex) {
            System.out.println("触发异常...");
        }
    }                      // transform 16 to 2

    public void store_content(String content_16_now){

        this.content_16 = content_16_now;              // store 16 instruction
        tran_16to2();                                 // store 2 instruction
        for(int i = 0; i < 6; i++){
            Opcode[i] = content_2[i];
        }
        IX[0] = content_2[6];
        IX[1] = content_2[7];
        R[0] = content_2[8];
        R[1] = content_2[9];
        I = content_2[10];
        for(int i = 0; i < 5; i++){
            Address[i] = content_2[i + 11];
        }

    }






}
