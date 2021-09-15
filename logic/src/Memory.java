import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Memory {
    public String[] content;


    public Memory(){
        this.content = new String[1024];

    }



    public void store_d(int address_now, String content_now){
        this.content[address_now] = content_now;
    }

    public String load_d(int address_now){
        return this.content[address_now];
    }

    public List<String> readRomProgramToStringArrList(String filePath) {
        ArrayList list = new ArrayList();

        try {
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                while((lineTxt = bufferedReader.readLine()) != null) {
                    list.add(lineTxt);
                }

                bufferedReader.close();
                read.close();
            }
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        return list;
    }

}
