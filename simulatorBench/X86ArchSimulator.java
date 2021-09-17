package simulatorBench;

import java.awt.EventQueue;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;
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
	private JButton btIPL;
	private JButton btRun;
	private JButton btSingleStep;
	private JButton btHalt;
	private JButton btPCLoad;
	private JButton btGPROp;
	private JButton btX1Load;
	private JButton btX2Load;
	private JButton btX3Load;
	private JButton btLoadToIR;
	private JButton btLoadToMAR;
	private JButton btLoadToMBR;
	private JButton btLoadData;
	
	private JTable jtMemory;
	private JScrollPane spMemory;

	private X86CPU x86Cpu;
	private X86Memory memory;
	private JLabel lblMemory;
	private JScrollPane spRegister;
	private JLabel lblGPR;
	private JScrollPane spIndexRegister;
	private JLabel lblIndexRegister;

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
	private JTextField tfPCLoad;
	private JTextField tfGPROp;

	private JTextField tfX1;
	private JTextField tfX2;
	private JTextField tfX3;

	private JTextField tfLoadIR;
	private JTextField tfLoadToMAR;
	private JTextField tfLoadToMBR;
	
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

		memory = new X86Memory(this);
		x86Cpu = new X86CPU(this,memory);
		//loader = new RomLoader(this, cpu, memory);

		frmX86Simulator = new JFrame();
		frmX86Simulator.setTitle("x86_64 Architecture Simulator");
		frmX86Simulator.setBounds(100, 100, 1600, 800);
		frmX86Simulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmX86Simulator.getContentPane().setLayout(null);

		// add button "IPL"
		btIPL = new JButton("IPL");
		btIPL.setForeground(Color.RED);
		btIPL.setFont(new Font("Arial", Font.BOLD, 20));
		btIPL.setBounds(500, 550, 110, 27);
		frmX86Simulator.getContentPane().add(btIPL);
		btIPL.addActionListener(this);
		
		// add button "Load"
		btSingleStep = new JButton("Single Step");
		btSingleStep.setFont(new Font("Arial", Font.BOLD, 20));
		btSingleStep.setBounds(240, 550, 200, 27);
		frmX86Simulator.getContentPane().add(btSingleStep);
		btSingleStep.addActionListener(this);

		// add button "Run"
		btRun = new JButton("Run");
		btRun.setFont(new Font("Arial", Font.BOLD, 20));
		btRun.setBounds(500, 600, 110, 27);
		frmX86Simulator.getContentPane().add(btRun);
		btRun.addActionListener(this);
		
		// add button "Halt"
		btHalt = new JButton("Halt");
		btHalt.setFont(new Font("Arial", Font.BOLD, 20));
		btHalt.addActionListener(this);
		btHalt.setBounds(500, 640, 110, 27);
		frmX86Simulator.getContentPane().add(btHalt);

		// Memory
		jtMemory = new JTable();
		jtMemory.setFont(new Font("Arial", Font.PLAIN, 18));
		jtMemory.setBounds(700, 505, 72, 18);
		spMemory = new JScrollPane(jtMemory);
		spMemory.setSize(352, 359);
		spMemory.setLocation(816, 300);
		frmX86Simulator.getContentPane().add(spMemory);

		lblMemory = new JLabel("RAM");
		lblMemory.setFont(new Font("Arial", Font.BOLD, 20));
		lblMemory.setBounds(937, 280, 72, 18);
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
		JLabel lblCCR = new JLabel("CCR");
		lblCCR.setFont(new Font("Arial", Font.BOLD, 20));
		lblCCR.setBounds(14, 45, regFontWidth, regFontHeight);
		frmX86Simulator.getContentPane().add(lblCCR);

		tfCCR = new JTextField();
		tfCCR.setFont(new Font("Arial", Font.PLAIN, 20));
		tfCCR.setEditable(false);
		tfCCR.setBounds(67, 42, 185, 24);
		frmX86Simulator.getContentPane().add(tfCCR);
		tfCCR.setColumns(10);

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
		lblMSR = new JLabel("MSR");
		lblMSR.setFont(new Font("Arial", Font.BOLD, 20));
		lblMSR.setBounds(714, 252, regFontWidth, regFontHeight);
		frmX86Simulator.getContentPane().add(lblMSR);

		tfMSR = new JTextField();
		tfMSR.setFont(new Font("Arial", Font.PLAIN, 20));
		tfMSR.setEditable(false);
		tfMSR.setBounds(767, 249, 185, 24);
		frmX86Simulator.getContentPane().add(tfMSR);
		tfMSR.setColumns(10);

		// add PC load textfiled
		tfPCLoad = new JTextField();
		tfPCLoad.setFont(new Font("Arial", Font.PLAIN, 20));
		tfPCLoad.setBounds(1004, 73, 79, 24);
		frmX86Simulator.getContentPane().add(tfPCLoad);
		tfPCLoad.setColumns(10);

		// add PC load button
		btPCLoad = new JButton("PC Load");
		btPCLoad.setFont(new Font("Arial", Font.BOLD, 20));
		btPCLoad.setBounds(1096, 72, 140, 27);
		frmX86Simulator.getContentPane().add(btPCLoad);
		btPCLoad.addActionListener(this);


		// add text field for load R0
		tfGPROp = new JTextField();
		tfGPROp.setFont(new Font("Arial", Font.PLAIN, 20));
		tfGPROp.setBounds(304, 100, 79, 24);
		frmX86Simulator.getContentPane().add(tfGPROp);
		tfGPROp.setColumns(10);


		// add button for load/store R0
		btGPROp = new JButton("GPR_OP");
		btGPROp.setFont(new Font("Arial", Font.BOLD, 20));
		btGPROp.setBounds(396, 100, 130, 27);
		frmX86Simulator.getContentPane().add(btGPROp);
		btGPROp.addActionListener(this);


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
		btX1Load = new JButton("X1 Load");
		btX1Load.setFont(new Font("Arial", Font.BOLD, 20));
		btX1Load.setBounds(396, 260, 130, 27);
		frmX86Simulator.getContentPane().add(btX1Load);
		btX1Load.addActionListener(this);

		// add button for X2 load
		btX2Load = new JButton("X2 Load");
		btX2Load.setFont(new Font("Arial", Font.BOLD, 20));
		btX2Load.setBounds(396, 290, 130, 27);
		frmX86Simulator.getContentPane().add(btX2Load);
		btX2Load.addActionListener(this);

		// add button for X3 load
		btX3Load = new JButton("X3 Load");
		btX3Load.setFont(new Font("Arial", Font.BOLD, 20));
		btX3Load.setBounds(396, 317, 130, 27);
		frmX86Simulator.getContentPane().add(btX3Load);
		btX3Load.addActionListener(this);

		// add text field for loading data to IR
		tfLoadIR = new JTextField();
		tfLoadIR.setFont(new Font("Arial", Font.PLAIN, 20));
		tfLoadIR.setBounds(1004, 112, 79, 24);
		frmX86Simulator.getContentPane().add(tfLoadIR);
		tfLoadIR.setColumns(10);
		// add button for loading data to IR
		btLoadToIR = new JButton("IR Load");
		btLoadToIR.setFont(new Font("Arial", Font.BOLD, 20));
		btLoadToIR.setBounds(1096, 111, 130, 27);
		frmX86Simulator.getContentPane().add(btLoadToIR);
		btLoadToIR.addActionListener(this);

		// add text field for loading data to MAR
		tfLoadToMAR = new JTextField();
		tfLoadToMAR.setFont(new Font("Arial", Font.PLAIN, 20));
		tfLoadToMAR.setBounds(1004, 149, 79, 24);
		frmX86Simulator.getContentPane().add(tfLoadToMAR);
		tfLoadToMAR.setColumns(10);
		// add button for loading data to MAR
		btLoadToMAR = new JButton("MAR Load");
		btLoadToMAR.setFont(new Font("Arial", Font.BOLD, 20));
		btLoadToMAR.setBounds(1096, 148, 170, 27);
		frmX86Simulator.getContentPane().add(btLoadToMAR);
		btLoadToMAR.addActionListener(this);

		// add text field for loading data to MBR
		tfLoadToMBR = new JTextField();
		tfLoadToMBR.setFont(new Font("Arial", Font.PLAIN, 20));
		tfLoadToMBR.setBounds(1004, 179, 79, 24);
		frmX86Simulator.getContentPane().add(tfLoadToMBR);
		tfLoadToMBR.setColumns(10);
		// add button to loading data to MBR
		btLoadToMBR = new JButton("MBR Load");
		btLoadToMBR.setFont(new Font("Arial", Font.BOLD, 20));
		btLoadToMBR.setBounds(1096, 178, 170, 27);
		frmX86Simulator.getContentPane().add(btLoadToMBR);
		btLoadToMBR.addActionListener(this);
		// add button for loading data to memory
		btLoadData = new JButton("Load MBR To Memory");
		btLoadData.setFont(new Font("Arial", Font.BOLD, 20));
		btLoadData.setBounds(1290, 178, 280, 27);
		frmX86Simulator.getContentPane().add(btLoadData);
		btLoadData.addActionListener(this);
		
		// add Phase lable
		lblPhase = new JLabel("Powered On");
		lblPhase.setFont(new Font("Arial", Font.PLAIN, 20));
		lblPhase.setForeground(Color.BLUE);
		lblPhase.setBounds(650, 550, 314, 18);
		frmX86Simulator.getContentPane().add(lblPhase);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btRun) 
		{// Respond to clicking the Run button
			
		} 
		else if (e.getSource() == btIPL) 
			{ // Respond to clicking the IPL button
				LoadProgram("InitProgLoad.txt");
				Display();
			}
		else if (e.getSource() == btSingleStep )
		{
			//todo: implement the function in X86CPU class
			//read data from regPC
			//execute memory content based on the regPC
			//show status of the MAR and MBR
		}
		else if (e.getSource() == btHalt)
		{
			
		}
		else if (e.getSource() == btPCLoad)
		{
			try {
				int i32PCupdate = Integer.parseInt(tfPCLoad.getText());
				this.x86Cpu.SetPC(i32PCupdate);
			} catch (NumberFormatException exception) {
			}
			
		}
		else if (e.getSource() == btGPROp)
		{
			try {
				int regGP_update = Integer.parseInt(tfGPROp.getText());
				//Todo: interpret the text here, 
				//int i32Idx = ? R0 - R3
				//int i32Value = ?
				//int i32Op = ? store or load
				//if load -> this.x86Cpu.SetGPR(i32Idx, i32Value);
				//if store -> this.x86Cpu.SetMemory(i32Idx, i32Value);
			} catch (NumberFormatException exception) {
			}
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
				if (obj instanceof X86Memory) {
					String memoryColumn[] = { "Address", "Content" };
					if (jtMemory != null) {
						DefaultTableModel memoryTableModel = (DefaultTableModel) jtMemory.getModel();
						if (memoryTableModel != null) {
							memoryTableModel.setDataVector(memory.GetContent(), memoryColumn);
						}
					}
				} 
				else if (obj instanceof X86GPRegister) 
				{
					String GPRColumn[] = { "Register", "Content" };
					if (jtRegister != null) {
						DefaultTableModel registerTableModel = (DefaultTableModel) jtRegister.getModel();
						if (registerTableModel != null) {
							//registerTableModel.setDataVector(cpu.GetGPRContent(), GPRColumn);
						}
					}
				} else if (obj instanceof X86IXRegister) 
				{
					if (jtIndexRegister != null) {
						String Column[] = { "Register", "Content" };
						DefaultTableModel IXTableModel = (DefaultTableModel) jtIndexRegister.getModel();
						if (IXTableModel != null) {
							//IXTableModel.setDataVector(cpu.GetIXContent(), Column);
						}
					}
				}
				else if (obj instanceof X86PCRegister) 
				{
					if (tfPC != null) {
						tfPC.setText(x86Cpu.GetPC());
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
