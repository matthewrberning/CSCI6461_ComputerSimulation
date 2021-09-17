import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;




        import java.awt.EventQueue;
        import java.lang.Thread;
        import java.util.ArrayList;
        import java.util.BitSet;
        import java.util.List;
        import java.util.Observable;
        import java.util.Observer;

        import javax.swing.JFrame;
        import javax.swing.JButton;
        import java.awt.event.ActionListener;
        import java.io.BufferedReader;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.InputStreamReader;
        import java.awt.event.ActionEvent;
        import javax.swing.JComboBox;
        import javax.swing.JTextArea;
        import javax.swing.JTextPane;
        import javax.swing.SwingUtilities;
        import javax.swing.table.DefaultTableModel;

        import javax.swing.JScrollBar;
        import javax.swing.JSlider;
        import javax.swing.JList;
        import javax.swing.JTable;
        import java.awt.TextField;
        import javax.swing.JLabel;

        import java.awt.BorderLayout;
        import java.awt.Color;
        import java.awt.Font;
        import java.awt.ScrollPane;
        import javax.swing.JScrollPane;
        import javax.swing.JPanel;
        import javax.swing.Box;
        import javax.swing.JTextField;
        import javax.swing.JInternalFrame;
        import javax.swing.JRadioButton;
        import java.awt.Component;

public class X86ArchSimulator implements abstractUpdate, ActionListener {
    public static int regFontWidth 	= 50;
    public static int regFontHeight = 30;

    public static int regBoxWidth = 200;
    public static int regBoxHeight = 30;

    private JFrame frmX86Simulator;
    private JButton btnIPL;
    private JButton btnRun;
    private JTextArea txtrConsole;
    private JLabel lblConsoleLable;
    private JTable jtMemory;
    private JScrollPane spMemory;

    //private RomLoader loader;
    //private CentralProcessor cpu;
    private Memory memory;
    private boolean status;
    private boolean isRunning;
    private JLabel lblMemory;
    private JScrollPane spRegister;
    private JLabel lblGPR;
    private JScrollPane spIndexRegister;
    private JLabel lblIndexRegister;
    private JButton btnSingleStep;
    private JButton btnHalt;
    private JTextField tfCCR;
    private JLabel lblPC;
    private JTextField tfPC;
    private JLabel lblIR;
    private JTextField tfIR;
    private JLabel lblMAR;
    private JTextField tfMAR;
    private JLabel lblMBR;
    private JTextField tfMBR;
    private JLabel lblMFR;
    private JTextField tfMFR;
    private JLabel lblMSR;
    private JTextField tfMSR;
    private JScrollPane spConsole;
    private JTable jtRegister;
    private JTable jtIndexRegister;
    private JLabel lblPhase;
    private JRadioButton rdbtnIndirect;
    private JTextField tfLoadPC;
    private JButton btnLoadToPC;
    private JTextField tfR0;
    private JTextField tfR1;
    private JTextField tfR2;
    private JTextField tfR3;
    private JLabel lblloadPC;
    private JButton btnR0Load;
    private JButton btnR1Load;
    private JButton btnR2Load;
    private JButton btnR3Load;
    private JLabel lblX1;
    private JTextField tfX1;
    private JButton btnX1Load;
    private JLabel lblX2;
    private JTextField tfX2;
    private JButton btnX2Load;
    private JLabel lblX3;
    private JTextField tfX3;
    private JButton btnX3Load;

    private JButton btnCleanConsole;
    private JTextField tfLoadIR;
    private JTextField tfLoadToMAR;
    private JTextField tfLoadToMBR;
    private JButton btnLoadToIR;
    private JButton btnLoadToMAR;
    private JButton btnLoadToMBR;
    private JButton btnLoadData;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    X86ArchSimulator objWindow = new X86ArchSimulator();
                    objWindow.frmX86Simulator.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public X86ArchSimulator() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        status = false;
        isRunning = false;
        memory = new Memory(this);
        //cpu = new CentralProcessor(this, this, this, memory);
        //loader = new RomLoader(this, cpu, memory);

        frmX86Simulator = new JFrame();
        frmX86Simulator.setTitle("x86_64 Architecture Simulator");
        frmX86Simulator.setBounds(100, 100, 1600, 800);
        frmX86Simulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmX86Simulator.getContentPane().setLayout(null);

        // add button "IPL"
        btnIPL = new JButton("IPL");
        btnIPL.setForeground(Color.RED);
        btnIPL.setFont(new Font("Arial", Font.BOLD, 20));
        btnIPL.setBounds(500, 550, 110, 27);
        frmX86Simulator.getContentPane().add(btnIPL);
        btnIPL.addActionListener(this);

        // add button "Load"
        btnRun = new JButton("Load");
        btnRun.setFont(new Font("Arial", Font.BOLD, 20));
        btnRun.setBounds(380, 550, 110, 27);
        frmX86Simulator.getContentPane().add(btnRun);
        btnRun.addActionListener(this);

        // add button "St+"
        btnRun = new JButton("St+");
        btnRun.setFont(new Font("Arial", Font.BOLD, 20));
        btnRun.setBounds(260, 550, 110, 27);
        frmX86Simulator.getContentPane().add(btnRun);
        btnRun.addActionListener(this);

        // add button "Store"
        btnRun = new JButton("Store");
        btnRun.setFont(new Font("Arial", Font.BOLD, 20));
        btnRun.setBounds(140, 550, 110, 27);
        frmX86Simulator.getContentPane().add(btnRun);
        btnRun.addActionListener(this);

        // add button "Run"
        btnRun = new JButton("Run");
        btnRun.setFont(new Font("Arial", Font.BOLD, 20));
        btnRun.setBounds(500, 600, 110, 27);
        frmX86Simulator.getContentPane().add(btnRun);
        btnRun.addActionListener(this);

        // add button "Halt"
        btnHalt = new JButton("Halt");
        btnHalt.setFont(new Font("Arial", Font.BOLD, 20));
        btnHalt.addActionListener(this);
        btnHalt.setBounds(500, 640, 110, 27);
        frmX86Simulator.getContentPane().add(btnHalt);

        // Memory
        jtMemory = new JTable();
        jtMemory.setFont(new Font("Arial", Font.PLAIN, 18));
        jtMemory.setBounds(700, 505, 72, 18);
        spMemory = new JScrollPane(jtMemory);
        spMemory.setSize(352, 359);
        spMemory.setLocation(816, 281);
        frmX86Simulator.getContentPane().add(spMemory);

        lblMemory = new JLabel("RAM");
        lblMemory.setFont(new Font("Arial", Font.BOLD, 20));
        lblMemory.setBounds(937, 260, 72, 18);
        frmX86Simulator.getContentPane().add(lblMemory);

        // GPR
        jtRegister = new JTable();
        jtRegister.setFont(new Font("Arial", Font.PLAIN, 20));
        spRegister = new JScrollPane(jtRegister);
        spRegister.setBounds(14, 100, 238, 100);
        frmX86Simulator.getContentPane().add(spRegister);

        lblGPR = new JLabel("GPR");
        lblGPR.setFont(new Font("Arial", Font.BOLD, 20));
        lblGPR.setBounds(14, 76, regFontWidth, regFontHeight);
        frmX86Simulator.getContentPane().add(lblGPR);

        // IX
        jtIndexRegister = new JTable();
        jtIndexRegister.setFont(new Font("Arial", Font.PLAIN, 20));
        spIndexRegister = new JScrollPane(jtIndexRegister);
        spIndexRegister.setBounds(14, 264, 238, 73);
        frmX86Simulator.getContentPane().add(spIndexRegister);

        lblIndexRegister = new JLabel("IX");
        lblIndexRegister.setFont(new Font("Arial", Font.BOLD, 20));
        lblIndexRegister.setBounds(14, 228, regFontWidth, regFontHeight);
        frmX86Simulator.getContentPane().add(lblIndexRegister);

        // CCR
		/*JLabel lblCCR = new JLabel("CCR");
		lblCCR.setFont(new Font("Arial", Font.BOLD, 20));
		lblCCR.setBounds(14, 45, regFontWidth, regFontHeight);
		frmX86Simulator.getContentPane().add(lblCCR);

		tfCCR = new JTextField();
		tfCCR.setFont(new Font("Arial", Font.PLAIN, 20));
		tfCCR.setEditable(false);
		tfCCR.setBounds(67, 42, 185, 24);
		frmX86Simulator.getContentPane().add(tfCCR);
		tfCCR.setColumns(10);*/

        // Program counter
        lblPC = new JLabel("PC");
        lblPC.setFont(new Font("Arial", Font.BOLD, 20));
        lblPC.setBounds(714, 76, regFontWidth, regFontHeight);
        frmX86Simulator.getContentPane().add(lblPC);

        tfPC = new JTextField();
        tfPC.setEditable(false);
        tfPC.setFont(new Font("Arial", Font.PLAIN, 20));
        tfPC.setBounds(767, 75, 185, 24);
        frmX86Simulator.getContentPane().add(tfPC);
        tfPC.setColumns(10);

        // Instruction register
        lblIR = new JLabel("IR");
        lblIR.setFont(new Font("Arial", Font.BOLD, 20));
        lblIR.setBounds(714, 113, regFontWidth, regFontHeight);
        frmX86Simulator.getContentPane().add(lblIR);

        tfIR = new JTextField();
        tfIR.setFont(new Font("Arial", Font.PLAIN, 20));
        tfIR.setEditable(false);
        tfIR.setBounds(767, 110, 185, 24);
        frmX86Simulator.getContentPane().add(tfIR);
        tfIR.setColumns(10);

        // memory address register
        lblMAR = new JLabel("MAR");
        lblMAR.setFont(new Font("Arial", Font.BOLD, 20));
        lblMAR.setBounds(714, 150, regFontWidth, regFontHeight);
        frmX86Simulator.getContentPane().add(lblMAR);

        tfMAR = new JTextField();
        tfMAR.setFont(new Font("Arial", Font.PLAIN, 20));
        tfMAR.setEditable(false);
        tfMAR.setBounds(767, 147, 185, 24);
        frmX86Simulator.getContentPane().add(tfMAR);
        tfMAR.setColumns(10);

        // memory buffer register
        lblMBR = new JLabel("MBR");
        lblMBR.setFont(new Font("Arial", Font.BOLD, 20));
        lblMBR.setBounds(714, 181, regFontWidth, regFontHeight);
        frmX86Simulator.getContentPane().add(lblMBR);

        tfMBR = new JTextField();
        tfMBR.setFont(new Font("Arial", Font.PLAIN, 20));
        tfMBR.setEditable(false);
        tfMBR.setBounds(767, 178, 185, 24);
        frmX86Simulator.getContentPane().add(tfMBR);
        tfMBR.setColumns(10);

        // machine fault register
        lblMFR = new JLabel("MFR");
        lblMFR.setFont(new Font("Arial", Font.BOLD, 20));
        lblMFR.setBounds(714, 212, regFontWidth, regFontHeight);
        frmX86Simulator.getContentPane().add(lblMFR);

        tfMFR = new JTextField();
        tfMFR.setFont(new Font("Arial", Font.PLAIN, 20));
        tfMFR.setEditable(false);
        tfMFR.setBounds(767, 212, 185, 24);
        frmX86Simulator.getContentPane().add(tfMFR);
        tfMFR.setColumns(10);
        // machine status register
		/*lblMSR = new JLabel("MSR");
		lblMSR.setFont(new Font("Arial", Font.BOLD, 20));
		lblMSR.setBounds(14, 252, regFontWidth, regFontHeight);
		frmX86Simulator.getContentPane().add(lblMSR);

		tfMSR = new JTextField();
		tfMSR.setFont(new Font("Arial", Font.PLAIN, 20));
		tfMSR.setEditable(false);
		tfMSR.setBounds(67, 249, 185, 24);
		frmX86Simulator.getContentPane().add(tfMSR);
		tfMSR.setColumns(10);*/

        // add PC load textfiled
        tfLoadPC = new JTextField();
        tfLoadPC.setFont(new Font("Arial", Font.PLAIN, 20));
        tfLoadPC.setBounds(1004, 73, 79, 24);
        frmX86Simulator.getContentPane().add(tfLoadPC);
        tfLoadPC.setColumns(10);

        // add PC load button
        btnLoadToPC = new JButton("PC Load");
        btnLoadToPC.setFont(new Font("Arial", Font.BOLD, 20));
        btnLoadToPC.setBounds(1096, 72, 140, 27);
        frmX86Simulator.getContentPane().add(btnLoadToPC);

        // add R0 label
        JLabel lblR0 = new JLabel("R0");
        lblR0.setFont(new Font("Arial", Font.BOLD, 20));
        lblR0.setBounds(270, 100, 30, 18);
        frmX86Simulator.getContentPane().add(lblR0);
        // add R1 label
        JLabel lblR1 = new JLabel("R1");
        lblR1.setFont(new Font("Arial", Font.BOLD, 20));
        lblR1.setBounds(270, 131, 30, 18);
        frmX86Simulator.getContentPane().add(lblR1);
        // add R2 label
        JLabel lblR2 = new JLabel("R2");
        lblR2.setFont(new Font("Arial", Font.BOLD, 20));
        lblR2.setBounds(270, 162, 30, 18);
        frmX86Simulator.getContentPane().add(lblR2);
        // add R3 label
        JLabel lblR3 = new JLabel("R3");
        lblR3.setFont(new Font("Arial", Font.BOLD, 20));
        lblR3.setBounds(270, 193, 30, 18);
        frmX86Simulator.getContentPane().add(lblR3);

        // add text field for load R0
        tfR0 = new JTextField();
        tfR0.setFont(new Font("Arial", Font.PLAIN, 20));
        tfR0.setBounds(304, 100, 79, 24);
        frmX86Simulator.getContentPane().add(tfR0);
        tfR0.setColumns(10);
        // add text field for load R1
        tfR1 = new JTextField();
        tfR1.setFont(new Font("Arial", Font.PLAIN, 20));
        tfR1.setBounds(304, 130, 79, 24);
        frmX86Simulator.getContentPane().add(tfR1);
        tfR1.setColumns(10);
        // add text field for load R2
        tfR2 = new JTextField();
        tfR2.setFont(new Font("Arial", Font.PLAIN, 20));
        tfR2.setBounds(304, 161, 79, 24);
        frmX86Simulator.getContentPane().add(tfR2);
        tfR2.setColumns(10);

        // add text field for load R3
        tfR3 = new JTextField();
        tfR3.setFont(new Font("Arial", Font.PLAIN, 20));
        tfR3.setBounds(304, 193, 79, 24);
        frmX86Simulator.getContentPane().add(tfR3);
        tfR3.setColumns(10);

        // add button for load PC
        lblloadPC = new JLabel("PC");
        lblloadPC.setFont(new Font("Arial", Font.BOLD, 20));
        lblloadPC.setBounds(970, 76, 30, 18);
        frmX86Simulator.getContentPane().add(lblloadPC);
        btnLoadToPC.addActionListener(this);

        // add button for load R0
        btnR0Load = new JButton("R0 Load");
        btnR0Load.setFont(new Font("Arial", Font.BOLD, 20));
        btnR0Load.setBounds(396, 100, 130, 27);
        frmX86Simulator.getContentPane().add(btnR0Load);
        btnR0Load.addActionListener(this);
        // add button for load R1
        btnR1Load = new JButton("R1 Load");
        btnR1Load.setFont(new Font("Arial", Font.BOLD, 20));
        btnR1Load.setBounds(396, 129, 130, 27);
        frmX86Simulator.getContentPane().add(btnR1Load);
        btnR1Load.addActionListener(this);
        // add button for load R2
        btnR2Load = new JButton("R2 Load");
        btnR2Load.setFont(new Font("Arial", Font.BOLD, 20));
        btnR2Load.setBounds(396, 160, 130, 27);
        frmX86Simulator.getContentPane().add(btnR2Load);
        btnR2Load.addActionListener(this);
        // add button for load R3
        btnR3Load = new JButton("R3 Load");
        btnR3Load.setFont(new Font("Arial", Font.BOLD, 20));
        btnR3Load.setBounds(396, 191, 130, 27);
        frmX86Simulator.getContentPane().add(btnR3Load);
        btnR3Load.addActionListener(this);

        // add label for X1
        lblX1 = new JLabel("X1");
        lblX1.setFont(new Font("Arial", Font.BOLD, 20));
        lblX1.setBounds(270, 263, 30, 18);
        frmX86Simulator.getContentPane().add(lblX1);
        // add label for X2
        lblX2 = new JLabel("X2");
        lblX2.setFont(new Font("Arial", Font.BOLD, 20));
        lblX2.setBounds(270, 292, 30, 18);
        frmX86Simulator.getContentPane().add(lblX2);
        // add label for X3
        lblX3 = new JLabel("X3");
        lblX3.setFont(new Font("Arial", Font.BOLD, 20));
        lblX3.setBounds(270, 319, 30, 18);
        frmX86Simulator.getContentPane().add(lblX3);

        // add text field for X1
        tfX1 = new JTextField();
        tfX1.setFont(new Font("Arial", Font.PLAIN, 20));
        tfX1.setBounds(304, 262, 79, 24);
        frmX86Simulator.getContentPane().add(tfX1);
        tfX1.setColumns(10);

        // add text field for X2
        tfX2 = new JTextField();
        tfX2.setFont(new Font("Arial", Font.PLAIN, 20));
        tfX2.setBounds(304, 291, 79, 24);
        frmX86Simulator.getContentPane().add(tfX2);
        tfX2.setColumns(10);

        // add text filed for X3
        tfX3 = new JTextField();
        tfX3.setFont(new Font("Arial", Font.PLAIN, 20));
        tfX3.setBounds(304, 318, 79, 24);
        frmX86Simulator.getContentPane().add(tfX3);
        tfX3.setColumns(10);

        // add button for X1 load
        btnX1Load = new JButton("X1 Load");
        btnX1Load.setFont(new Font("Arial", Font.BOLD, 20));
        btnX1Load.setBounds(396, 260, 130, 27);
        frmX86Simulator.getContentPane().add(btnX1Load);
        btnX1Load.addActionListener(this);

        // add button for X2 load
        btnX2Load = new JButton("X2 Load");
        btnX2Load.setFont(new Font("Arial", Font.BOLD, 20));
        btnX2Load.setBounds(396, 290, 130, 27);
        frmX86Simulator.getContentPane().add(btnX2Load);
        btnX2Load.addActionListener(this);

        // add button for X3 load
        btnX3Load = new JButton("X3 Load");
        btnX3Load.setFont(new Font("Arial", Font.BOLD, 20));
        btnX3Load.setBounds(396, 317, 130, 27);
        frmX86Simulator.getContentPane().add(btnX3Load);
        btnX3Load.addActionListener(this);

        // add button to clear the console
        btnCleanConsole = new JButton("Clear");
        btnCleanConsole.setFont(new Font("Arial", Font.BOLD, 20));
        btnCleanConsole.setBounds(271, 597, 113, 27);
        frmX86Simulator.getContentPane().add(btnCleanConsole);
        btnCleanConsole.addActionListener(this);
        // add label for loading data to IR
        JLabel lblIR2 = new JLabel("IR");
        lblIR2.setFont(new Font("Arial", Font.BOLD, 20));
        lblIR2.setBounds(979, 113, 26, 18);
        frmX86Simulator.getContentPane().add(lblIR2);
        // add text field for loading data to IR
        tfLoadIR = new JTextField();
        tfLoadIR.setFont(new Font("Arial", Font.PLAIN, 20));
        tfLoadIR.setBounds(1004, 112, 79, 24);
        frmX86Simulator.getContentPane().add(tfLoadIR);
        tfLoadIR.setColumns(10);
        // add button for loading data to IR
        btnLoadToIR = new JButton("IR Load");
        btnLoadToIR.setFont(new Font("Arial", Font.BOLD, 20));
        btnLoadToIR.setBounds(1096, 111, 130, 27);
        frmX86Simulator.getContentPane().add(btnLoadToIR);
        btnLoadToIR.addActionListener(this);
        // add label for loading data to MAR
        JLabel lblMAR2 = new JLabel("MAR");
        lblMAR2.setFont(new Font("Arial", Font.BOLD, 20));
        lblMAR2.setBounds(950, 150, 50, 18);
        frmX86Simulator.getContentPane().add(lblMAR2);
        // add text field for loading data to MAR
        tfLoadToMAR = new JTextField();
        tfLoadToMAR.setFont(new Font("Arial", Font.PLAIN, 20));
        tfLoadToMAR.setBounds(1004, 149, 79, 24);
        frmX86Simulator.getContentPane().add(tfLoadToMAR);
        tfLoadToMAR.setColumns(10);
        // add button for loading data to MAR
        btnLoadToMAR = new JButton("MAR Load");
        btnLoadToMAR.setFont(new Font("Arial", Font.BOLD, 20));
        btnLoadToMAR.setBounds(1096, 148, 170, 27);
        frmX86Simulator.getContentPane().add(btnLoadToMAR);
        btnLoadToMAR.addActionListener(this);
        // add label for loading data to MBR
        JLabel lblMBR2 = new JLabel("MBR");
        lblMBR2.setFont(new Font("Arial", Font.BOLD, 20));
        lblMBR2.setBounds(950, 182, 50, 18);
        frmX86Simulator.getContentPane().add(lblMBR2);
        // add text field for loading data to MBR
        tfLoadToMBR = new JTextField();
        tfLoadToMBR.setFont(new Font("Arial", Font.PLAIN, 20));
        tfLoadToMBR.setBounds(1004, 179, 79, 24);
        frmX86Simulator.getContentPane().add(tfLoadToMBR);
        tfLoadToMBR.setColumns(10);
        // add button to loading data to MBR
        btnLoadToMBR = new JButton("MBR Load");
        btnLoadToMBR.setFont(new Font("Arial", Font.BOLD, 20));
        btnLoadToMBR.setBounds(1096, 178, 170, 27);
        frmX86Simulator.getContentPane().add(btnLoadToMBR);
        btnLoadToMBR.addActionListener(this);
        // add button for loading data to memory
        btnLoadData = new JButton("Load MBR To Memory");
        btnLoadData.setFont(new Font("Arial", Font.BOLD, 20));
        btnLoadData.setBounds(1290, 178, 280, 27);
        frmX86Simulator.getContentPane().add(btnLoadData);
        btnLoadData.addActionListener(this);

        // add Phase lable
        lblPhase = new JLabel("Powered On");
        lblPhase.setFont(new Font("Arial", Font.PLAIN, 20));
        lblPhase.setForeground(Color.BLUE);
        lblPhase.setBounds(650, 550, 314, 18);
        frmX86Simulator.getContentPane().add(lblPhase);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRun)
        {// Respond to clicking the Run button
            if (status == false)
            {

            }
        } else if (e.getSource() == btnIPL)
        { // Respond to clicking the IPL button
            LoadProgram("InitProgLoad.txt");
            Display();
        }
    }

    public List<String> readRomProgramToStringArrList(String filePath) {
        List<String> list = new ArrayList<String>();
        try {
            String encoding = "GBK";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    list.add(lineTxt);
                }
                bufferedReader.close();
                read.close();
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean LoadProgram(String programPath) {
        boolean bResult = false;
        List<String> rom_program_list = this.readRomProgramToStringArrList(programPath);
        int len = rom_program_list.size();
        if (len == 0) {
            bResult = false;
        }

        for (int i = 0; i < len; i++) {
            String[] segments = rom_program_list.get(i).split(" ");
            memory.Set(Integer.parseInt(segments[0],16),segments[1]);
        }

        return bResult;
    }

    public void updateData(Object obj) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // TODO Auto-generated method stub
                if (obj instanceof Memory) {
                    String memoryColumn[] = { "Address", "Content" };
                    if (jtMemory != null) {
                        DefaultTableModel memoryTableModel = (DefaultTableModel) jtMemory.getModel();
                        if (memoryTableModel != null) {
                            memoryTableModel.setDataVector(memory.GetContent(), memoryColumn);
                        }
                    }
                }
            }
        });
    }

    void Display() {
        // Display memory content
        String memoryColumn[] = { "Address", "Value" };
        DefaultTableModel memoryTableModel = (DefaultTableModel) jtMemory.getModel();
        memoryTableModel.setDataVector(memory.GetContent(), memoryColumn);
    }

}
