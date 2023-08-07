import javax.swing.*;
import javax.swing.border.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Calculator {

    int buttonWidth = 40, buttonHeight = 40;
    private Color fontColor = new Color(100, 149, 237);
    private Color greenColor = new Color(124,252,0);
    private Font myFont = new Font("Architects Daughter", Font.BOLD, 18);
    private String firstTextFieldInput, secondTextFieldInput;

    JFrame f = new JFrame("Calculator");
    JButton addButton = giveButton(300, 100, buttonWidth, buttonHeight, "./images/plus.png" , "./images/plusGlow.png");
    JButton subButton = giveButton(300, 150, buttonWidth, buttonHeight, "./images/minus.png", "./images/minusGlow.png");
    JButton mulButton = giveButton(300, 200, buttonWidth, buttonHeight, "./images/multiply.png", "./images/multiplyGlow.png");
    JButton divButton = giveButton(300, 250, buttonWidth, buttonHeight, "./images/divide.png", "./images/divideGlow.png");
    JButton clearButton = giveButton(25, 300, 250, 100, "./images/clearBtn.png", "./images/clearBtn.png");

    JLabel firstNumLabel = giveLabel(25, 50, "Enter First Number:-");
    JLabel secondNumLabel = giveLabel(25, 130, "Enter Second Number:-");
    JLabel resultNumLabel = giveLabel(25, 210, "Result:-");
    JLabel warningLabel = giveLabel(25, 210, "Please Enter Valid Numbers!");


    JTextField firstNumTextField = giveTextField(25, 90);
    JTextField secondNumTextField = giveTextField(25, 170);
    JTextField resultNumTextField = giveTextField(25, 250);

    Image calculatorLogo = Toolkit.getDefaultToolkit().getImage("./images/logo.png");

    private Icon giveIcon(int width, int height, String imagePath)
    {
        Image normalImg = Toolkit.getDefaultToolkit().getImage(imagePath);
        Image scaledImage = normalImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
    private JButton giveButton(int x, int y, int width, int height, String imagePath, String glowImagePath)
    {
        JButton button = new JButton(giveIcon(width, height, imagePath));
        button.setBounds(x, y, width, height);
        button.setBorder(null);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setText(null);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setIcon(giveIcon(width, height, glowImagePath));
                button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                button.setIcon(giveIcon(width, height, imagePath));
            }
        });

        return button;
    }

    private JLabel giveLabel(int x, int y, String labelName)
    {
        int width=300, height=40;
        JLabel myLabel = new JLabel(labelName);
        myLabel.setBounds(x, y, width, height);
        myLabel.setForeground(fontColor);
        myLabel.setFont(myFont);
        return myLabel;
    }

    private JTextField giveTextField(int x, int y)
    {
        int width=250, height=30;
        Color gray = new Color(54, 69, 79);

        Border skyBlueBorder = new LineBorder(fontColor, 2, true);
        Border orangeBorder = new LineBorder(new Color(255, 165, 0), 2, true);
        EmptyBorder emptyBorder = new EmptyBorder(0, 10, 0, 0);
        Border skyBlueCompoundBorder = BorderFactory.createCompoundBorder(skyBlueBorder, emptyBorder);
        Border orangeCompoundBorder = BorderFactory.createCompoundBorder(orangeBorder, emptyBorder);

        JTextField myTextField = new JTextField();
        myTextField.setBounds(x, y, width, height);
        myTextField.setBorder(skyBlueCompoundBorder);
        myTextField.setFont(myFont);
        myTextField.setBackground(Color.black);
        myTextField.setForeground(Color.white);
        myTextField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e){
                myTextField.setBorder(orangeCompoundBorder);
                myTextField.setBackground(gray);
            } 
            public void focusLost(FocusEvent e){
                myTextField.setBorder(skyBlueCompoundBorder);
                myTextField.setBackground(Color.black);
            } 
        });
        return myTextField;
    }

    private Boolean checkTextField(){
        firstTextFieldInput = firstNumTextField.getText();
        secondTextFieldInput = secondNumTextField.getText();

        Boolean firstFieldBoolean = true, secondFieldBoolean = true;

        if (firstTextFieldInput.length()==0 || secondTextFieldInput.length()==0)
        {
            return false;
        }

        for (int i=0; i<firstTextFieldInput.length(); i++)
        {
            if (!(firstTextFieldInput.charAt(i)>='0'&&firstTextFieldInput.charAt(i)<='9'))
            {
                firstFieldBoolean=false;
                break;
            }
        }

        for (int i=0; i<secondTextFieldInput.length(); i++)
        {
            if (!(secondTextFieldInput.charAt(i)>='0'&&secondTextFieldInput.charAt(i)<='9'))
            {
                secondFieldBoolean=false;
                break;
            }
        }

        return firstFieldBoolean && secondFieldBoolean;
    }

    private Boolean shouldICalculate()
    {
        clearButton.setVisible(true);

        if (!checkTextField())
        {
            resultNumTextField.setVisible(false);
            resultNumLabel.setVisible(false);
            warningLabel.setVisible(true);
            return false;
        }

        warningLabel.setVisible(false);
        return true;
    }

    private ActionListener doAction(String operation)
    {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if (shouldICalculate())
                {   
                    resultNumLabel.setVisible(true);
                    resultNumTextField.setVisible(true);
                    switch(operation)
                    {
                        case "addition" -> resultNumTextField.setText(String.valueOf(Integer.parseInt(firstTextFieldInput)+Integer.parseInt(secondTextFieldInput)));
                        case "substraction" ->  resultNumTextField.setText(String.valueOf(Integer.parseInt(firstTextFieldInput)-Integer.parseInt(secondTextFieldInput)));
                        case "multiplication" ->  resultNumTextField.setText(String.valueOf(Integer.parseInt(firstTextFieldInput)*Integer.parseInt(secondTextFieldInput)));
                        case "division" ->  resultNumTextField.setText(String.valueOf(Float.parseFloat(firstTextFieldInput)/Float.parseFloat(secondTextFieldInput)));
                    }
                }
            }
        };
    }
    public void showCalculator(){
        
        resultNumLabel.setVisible(false);
        resultNumLabel.setForeground(greenColor);

        resultNumTextField.setVisible(false);
        resultNumTextField.setBorder(BorderFactory.createCompoundBorder(new LineBorder(greenColor, 2, true), new EmptyBorder(0, 10, 0, 0)) );
        resultNumTextField.setForeground(greenColor);

        warningLabel.setVisible(false);
        warningLabel.setForeground(Color.red);

        addButton.addActionListener(doAction("addition"));
        subButton.addActionListener(doAction("substraction"));
        mulButton.addActionListener(doAction("multiplication"));
        divButton.addActionListener(doAction("division"));

        resultNumTextField.addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e){
                resultNumTextField.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage("./images/copyIcon.png"), new Point(0, 0), "copy_cursor"));
            }
            public void mouseClicked(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        clearButton.setVisible(false);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                firstNumTextField.setText(null);
                secondNumTextField.setText(null);
                resultNumLabel.setVisible(false);
                resultNumTextField.setVisible(false);
                warningLabel.setVisible(false);
                clearButton.setVisible(false);
            }
        });
        f.setIconImage(calculatorLogo);
        f.add(addButton);
        f.add(subButton);
        f.add(mulButton);
        f.add(divButton);
        f.add(firstNumLabel);
        f.add(secondNumLabel);
        f.add(resultNumLabel);
        f.add(firstNumTextField);
        f.add(secondNumTextField);
        f.add(resultNumTextField);
        f.add(warningLabel);
        f.add(clearButton);
        f.setSize(400, 450);
        f.setLayout(null);
        f.setVisible(true);
        f.getContentPane().setBackground(Color.BLACK);
    }

    public static void main(String []args)    
    {
        Calculator obj = new Calculator();
        obj.showCalculator();
    }
    
}
