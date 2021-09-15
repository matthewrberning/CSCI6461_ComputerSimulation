import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    //General Purpose Register 0 Label, Text Field, and Load Button
    private static JLabel GPR0label;
    private static JButton GPR0LDbutton;
    private static JLabel GPR0text;
    //General Purpose Register 1 Label, Text Field, and Load Button
    private static JLabel GPR1label;
    private static JButton GPR1LDbutton;
    private static JLabel GPR1text;
    //General Purpose Register 2 Label, Text Field, and Load Button
    private static JLabel GPR2label;
    private static JButton GPR2LDbutton;
    private static JLabel GPR2text;
    //General Purpose Register 3 Label, Text Field, and Load Button
    private static JLabel GPR3label;
    private static JButton GPR3LDbutton;
    private static JLabel GPR3text;

    //Index Register 1 Label, Text Field, and Load Button
    private static JLabel IXR1label;
    private static JButton IXR1LDbutton;
    private static JLabel IXR1text;
    //Index Register 2 Label, Text Field, and Load Button
    private static JLabel IXR2label;
    private static JButton IXR2LDbutton;
    private static JLabel IXR2text;
    //Index Register 3 Label, Text Field, and Load Button
    private static JLabel IXR3label;
    private static JButton IXR3LDbutton;
    private static JLabel IXR3text;


    //Program Counter Label, Text Field, and Load Button
    private static JLabel PClabel;
    private static JButton PCLDbutton;
    private static JLabel PCtext;

    //MAR Label, Text Field, and Load Button
    private static JLabel MARlabel;
    private static JButton MARLDbutton;
    private static JLabel MARtext;

    //MBR Label, Text Field, and Load Button
    private static JLabel MBRlabel;
    private static JButton MBRLDbutton;
    private static JLabel MBRtext;

    //Index Register Label, Text Field
    private static JLabel IRlabel;
    private static JLabel IRtext;

    //Memory Fault Register Label, Text Field
    private static JLabel MFRlabel;
    private static JLabel MFRtext;

    //default strings
    private static String default16 = "000000 00 00 0 00000";
    private static String default12 = "0000 000 0000";


    //default values
    private static String GPR0vaule = "000000 00 00 0 00000";


    //IPL Button
    private static JButton IPLbutton;


    public static void main(String[] args) {

        //build the panel to hold the elements inside the frame
        JPanel panel = new JPanel();

        //build the frame to hold the GUI
        JFrame frame = new JFrame();
        frame.setSize(750,500); //set the size in pix
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //set the close operation

        //add the panel to the frame
        frame.add(panel);

        panel.setLayout(null);

        //General Purpose Register 0 Label, Text Field, and Load Button
        GPR0label = new JLabel("GPR 0");
        GPR0label.setBounds(10, 20, 80, 25);
        panel.add(GPR0label);

        GPR0text = new JLabel(default16);
        GPR0text.setBounds(60, 20, 165, 25);
        panel.add(GPR0text);

        GPR0LDbutton = new JButton("LD");
        GPR0LDbutton.setBounds(230, 20, 50,25);
        GPR0LDbutton.addActionListener(new GUI());
        panel.add(GPR0LDbutton);

        //General Purpose Register 1 Label, Text Field, and Load Button
        GPR1label = new JLabel("GPR 1");
        GPR1label.setBounds(10, 50, 80, 25);
        panel.add(GPR1label);

        GPR1text = new JLabel(default16);
        GPR1text.setBounds(60, 50, 165, 25);
        panel.add(GPR1text);

        GPR1LDbutton = new JButton("LD");
        GPR1LDbutton.setBounds(230, 50, 50,25);
        GPR1LDbutton.addActionListener(new GUI());
        panel.add(GPR1LDbutton);


        //General Purpose Register 2 Label, Text Field, and Load Button
        GPR2label = new JLabel("GPR 2");
        GPR2label.setBounds(10, 80, 80, 25);
        panel.add(GPR2label);

        GPR2text = new JLabel(default16);
        GPR2text.setBounds(60, 80, 165, 25);
        panel.add(GPR2text);

        GPR2LDbutton = new JButton("LD");
        GPR2LDbutton.setBounds(230, 80, 50,25);
        GPR2LDbutton.addActionListener(new GUI());
        panel.add(GPR2LDbutton);


        //General Purpose Register 3 Label, Text Field, and Load Button
        GPR3label = new JLabel("GPR 3");
        GPR3label.setBounds(10, 110, 80, 25);
        panel.add(GPR3label);

        GPR3text = new JLabel(default16);;
        GPR3text.setBounds(60, 110, 165, 25);
        panel.add(GPR3text);

        GPR3LDbutton = new JButton("LD");
        GPR3LDbutton.setBounds(230, 110, 50,25);
        GPR3LDbutton.addActionListener(new GUI());
        panel.add(GPR3LDbutton);


        //Index Register 1 Label, Text Field, and Load Button
        IXR1label = new JLabel("IXR 1");
        IXR1label.setBounds(10, 170, 80, 25);
        panel.add(IXR1label);

        IXR1text = new JLabel(default16);
        IXR1text.setBounds(60, 170, 165, 25);
        panel.add(IXR1text);

        IXR1LDbutton = new JButton("LD");
        IXR1LDbutton.setBounds(230, 170, 50,25);
        IXR1LDbutton.addActionListener(new GUI());
        panel.add(IXR1LDbutton);

        //Index Register 2 Label, Text Field, and Load Button
        IXR2label = new JLabel("IXR 2");
        IXR2label.setBounds(10, 200, 80, 25);
        panel.add(IXR2label);

        IXR2text = new JLabel(default16);
        IXR2text.setBounds(60, 200, 165, 25);
        panel.add(IXR2text);

        IXR2LDbutton = new JButton("LD");
        IXR2LDbutton.setBounds(230, 200, 50,25);
        IXR2LDbutton.addActionListener(new GUI());
        panel.add(IXR2LDbutton);


        //Index Register 3 Label, Text Field, and Load Button
        IXR3label = new JLabel("IXR 3");
        IXR3label.setBounds(10, 230, 80, 25);
        panel.add(IXR3label);

        IXR3text = new JLabel(default16);
        IXR3text.setBounds(60, 230, 165, 25);
        panel.add(IXR3text);

        IXR3LDbutton = new JButton("LD");
        IXR3LDbutton.setBounds(230, 230, 50,25);
        IXR3LDbutton.addActionListener(new GUI());
        panel.add(IXR3LDbutton);



        //Program Counter Label, Text Field, and Load Button
        PClabel = new JLabel("PC");
        PClabel.setBounds(410, 20, 80, 25);
        panel.add(PClabel);

        PCtext = new JLabel(default12);
        PCtext.setBounds(460, 20, 165, 25);
        panel.add(PCtext);

        PCLDbutton = new JButton("LD");
        PCLDbutton.setBounds(630, 20, 50,25);
        PCLDbutton.addActionListener(new GUI());
        panel.add(PCLDbutton);



        //MAR Label, Text Field, and Load Button
        MARlabel = new JLabel("MAR");
        MARlabel.setBounds(410, 50, 80, 25);
        panel.add(MARlabel);

        MARtext = new JLabel(default12);
        MARtext.setBounds(460, 50, 165, 25);
        panel.add(MARtext);

        MARLDbutton = new JButton("LD");
        MARLDbutton.setBounds(630, 50, 50,25);
        MARLDbutton.addActionListener(new GUI());
        panel.add(MARLDbutton);


        //MBR Label, Text Field, and Load Button
        MBRlabel = new JLabel("MBR");
        MBRlabel.setBounds(410, 80, 80, 25);
        panel.add(MBRlabel);

        MBRtext = new JLabel(default16);
        MBRtext.setBounds(460, 80, 165, 25);
        panel.add(MBRtext);

        MBRLDbutton = new JButton("LD");
        MBRLDbutton.setBounds(630, 80, 50,25);
        MBRLDbutton.addActionListener(new GUI());
        panel.add(MBRLDbutton);


        //IR Label, Text Field
        IRlabel = new JLabel("IR");
        IRlabel.setBounds(410, 110, 80, 25);
        panel.add(IRlabel);

        IRtext = new JLabel(default16);
        IRtext.setBounds(460, 110, 165, 25);
        panel.add(IRtext);




        //MFR Label, Text Field
        MFRlabel = new JLabel("MFR");
        MFRlabel.setBounds(410, 140, 80, 25);
        panel.add(MFRlabel);

        MFRtext = new JLabel("0000");
        MFRtext.setBounds(460, 140, 165, 25);
        panel.add(MFRtext);







        JLabel instruction = new JLabel("INSTRUCTION");
        instruction.setBounds(150, 300, 100, 25);
        panel.add(instruction);

        JTextField operationTextField = new JTextField("000000");
        operationTextField.setBounds(10, 330, 80, 25);
        panel.add(operationTextField);

        JLabel operation = new JLabel("OPERATION");
        operation.setBounds(10, 360, 80, 25);
        panel.add(operation);

        JTextField GPRTextField = new JTextField("00");
        GPRTextField.setBounds(100, 330, 40, 25);
        panel.add(GPRTextField);

        JLabel GPR = new JLabel("GPR");
        GPR.setBounds(100, 360, 80, 25);
        panel.add(GPR);

        JTextField IXRTextField = new JTextField("00");
        IXRTextField.setBounds(150, 330, 40, 25);
        panel.add(IXRTextField);

        JLabel IXR = new JLabel("IXR");
        IXR.setBounds(150, 360, 80, 25);
        panel.add(IXR);

        JTextField ITextField = new JTextField("0");
        ITextField.setBounds(200, 330, 20, 25);
        panel.add(ITextField);

        JLabel I = new JLabel("I");
        I.setBounds(200, 360, 80, 25);
        panel.add(I);


        JTextField AddressTextField = new JTextField("00000");
        AddressTextField.setBounds(230, 330, 80, 25);
        panel.add(AddressTextField);

        JLabel Address = new JLabel("Address");
        Address.setBounds(230, 360, 80, 25);
        panel.add(Address);


        IPLbutton = new JButton("IPL");
        IPLbutton.setBounds(630, 400, 70,25);
        IPLbutton.addActionListener(new GUI());
        IPLbutton.setBackground(Color.RED);
        IPLbutton.setOpaque(true);
        panel.add(IPLbutton);



        frame.setVisible(true); //make the GUI visible

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == IPLbutton){
            System.out.println(" IPL Button Clicked");

        }

        else if(e.getSource() == GPR0LDbutton){
            System.out.println("GPR Load Button Clicked");

//            String operation =




        }

    }
}
