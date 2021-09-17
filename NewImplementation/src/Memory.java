import java.util.*;
import java.util.stream.IntStream;

public class Memory {
    private String[] memory;
    private String[][] content; // pair of address and value of memory
    private abstractUpdate subject;

    public Memory(abstractUpdate subject) {
        this.subject = subject;
        memory = new String[2048];
        content = new String[2048][2];
        init();
    }

    /**
     * initialize each memory location to null
     */
    private void init() {
        for (int i = 0; i < 2048; i++) {
            memory[i] = null;
        }
        initContent();
    }

    public String[][] GetContent() {
        return content;
    }

    private void initContent() {
        updateContent();
    }

    public boolean Set(int index, String content) {
        boolean bResult = true;
        memory[index] = content;
        updateContent();
        this.subject.updateData(this);

        return bResult;
    }


    /**
     * update all memory content
     */
    private void updateContent() {
        for (int i = 0; i < 2048; i++) {
            String address = Integer.toString(i);
            content[i][0] = address;
            if (memory[i] != null) {
                content[i][1] = (memory[i]);
            } else {
                content[i][1] = "";
            }
        }
    }

	/*private String getBinaryString(BitSet bs) {

		StringBuilder s = new StringBuilder();
		for (int i = 0; i < nbits; i++) {
			s.append(bs.get(i) == true ? 1 : 0);
		}
		return s.toString();
	}*/


}
