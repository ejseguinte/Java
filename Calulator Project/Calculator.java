import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.event.ActionEvent;
/** The <tt>Calculator</tt> class creates a GUI with the buttons using a GridBagLayout to evenly distribute all the buttons
 *  After pressing a button on the calulator the calulator becomes the focus object and keyboard input is now allowed.
 *  There is only on useable that being the letter x both the key x and the button x activate the variable. This program uses
 *  the <tt>Evaluator</tt> written by Erwin Seguinte.
 *
 *  @author Erwin Seguinte
 *  @version May 9, 2015
 */
public class Calculator {
   private JButton button;    //creates a golbal button
   private JTextField text;   //creates a global text field for input
   /** initalizes the <tt>Calculator</tt> GUI for actual use.
    *  @param args The string arguments passed in main
    */  
   public static void main(String[] args){
      Calculator gui = new Calculator();
   }
   
   /** The <tt>Calculator</tt> consturctor creates a GUI with the buttons using a GridBagLayout to evenly distribute all the buttons
   *  After pressing a button on the calulator the calulator, the calculator becomes the focus object and keyboard input is now allowed.
   *  There is only one useable variable that being the letter x both the key x and the button x activate the variable. This program uses
   *  the <tt>Evaluator</tt> written by Erwin Seguinte.
   */
   public Calculator(){
      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(new Dimension(287,169));
      frame.setResizable(true);
      frame.setLocationRelativeTo ( null );
      frame.setLayout(new GridBagLayout());
      GridBagConstraints c = new GridBagConstraints();
      Evaluator eval = new Evaluator();
      frame.setTitle("Calculator");
      
      text = new JTextField("0");
      text.setBackground(Color.WHITE);
      text.setHorizontalAlignment(JTextField.RIGHT);
      text.setEditable(false);
      c.fill = GridBagConstraints.BOTH;
      c.anchor = GridBagConstraints.FIRST_LINE_START;
      c.gridwidth = 8; 
      c.gridx = 0;
      c.gridy = 0;
      frame.add(text, c);
      
      Action press = new AbstractAction(){
         public void actionPerformed(ActionEvent e) {
            String buttonText = ((JButton) e.getSource()).getText();
            if (buttonText.equals("Back")){
               String temp = text.getText();
               if(!temp.isEmpty()){
                  temp = temp.substring(0,temp.length()-1);
                  text.setText(temp);
               }
            }else if (buttonText.equals("Clear")){
               text.setText("");
            }else if (buttonText.equals("Var")){
               text.setText(text.getText() + "x");
            }else if (buttonText.equals("Enter")){
               String temp = text.getText();
               String result = ""+eval.math(temp);
               text.setText(result);
            }else{
               if ((text.getText().equals("Error"))){
                  text.setText(""); 
               }
               text.setText(text.getText() + buttonText);
            }    
         }
      };
      
      button = new JButton(""+7);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridwidth = 1; 
      c.gridx = 0;
      c.gridy = 1;
      button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('7'),"press");
      button.getActionMap().put("press", press);
      button.addActionListener(press);
      frame.add(button, c);
      button = new JButton(""+8);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 1;
      c.gridy = 1;
      button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('8'),"press");
      button.getActionMap().put("press", press);
      button.addActionListener(press); 
      frame.add(button, c);
      button = new JButton(""+9);
      c.fill = GridBagConstraints.HORIZONTAL; 
      c.gridx = 2;
      c.gridy = 1;
      button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('9'),"press");
      button.getActionMap().put("press", press);
      button.addActionListener(press);
      frame.add(button, c);
      button = new JButton(""+4);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 0;
      c.gridy = 2;
      button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('4'),"press");
      button.getActionMap().put("press", press);
      button.addActionListener(press);
      frame.add(button, c);
      button = new JButton(""+5);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 1;
      c.gridy = 2;
      button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('5'),"press");
      button.getActionMap().put("press", press);
      button.addActionListener(press);
      frame.add(button, c);
      button = new JButton(""+6);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 2;
      c.gridy = 2;
      button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('6'),"press");
      button.getActionMap().put("press", press);
      button.addActionListener(press);
      frame.add(button, c);
      button = new JButton(""+3);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 0;
      c.gridy = 3;
      button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('3'),"press");
      button.getActionMap().put("press", press);
      button.addActionListener(press);
      frame.add(button, c);
      button = new JButton(""+2);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 1;
      c.gridy = 3;
      button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('2'),"press");
      button.getActionMap().put("press", press);
      button.addActionListener(press);
      frame.add(button, c);
      button = new JButton(""+1);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 2;
      c.gridy = 3;
      button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('1'),"press");
      button.getActionMap().put("press", press);
      button.addActionListener(press);
      frame.add(button, c);
      button = new JButton(""+0);
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 0;
      c.gridy = 4;
      button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('0'),"press");
      button.getActionMap().put("press", press);
      button.addActionListener(press);
      c.gridwidth = 1;
      frame.add(button, c);
      
      button = new JButton("=");
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 1;
      c.gridy = 4;
      button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('='),"press");
      button.getActionMap().put("press", press);
      button.addActionListener(press);
      c.gridwidth = 1;
      frame.add(button, c);
      
      button = new JButton(".");
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 2;
      c.gridy = 4;
      button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('.'),"press");
      button.getActionMap().put("press", press);
      button.addActionListener(press);
      frame.add(button, c);
      button = new JButton("(");
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 3;
      c.gridy = 1;
      button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('('),"press");
      button.getActionMap().put("press", press);
      button.addActionListener(press);
      frame.add(button, c);
      button = new JButton("^");
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 3;
      c.gridy = 2;
      button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('^'),"press");
      button.getActionMap().put("press", press);
      button.addActionListener(press);
      frame.add(button, c);
      button = new JButton("*");
      c.fill = GridBagConstraints.HORIZONTAL; 
      c.gridx = 3;
      c.gridy = 3;
      button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('*'),"press");
      button.getActionMap().put("press", press);
      button.addActionListener(press);
      frame.add(button, c);
      button = new JButton("+");
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 3;
      c.gridy = 4;
      button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('+'),"press");
      button.getActionMap().put("press", press);
      button.addActionListener(press);
      frame.add(button, c);
      button = new JButton(")");
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 5;
      c.gridy = 1;
      button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(')'),"press");
      button.getActionMap().put("press", press);
      button.addActionListener(press);
      frame.add(button, c);
      button = new JButton("%");
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 5;
      c.gridy = 2;
      button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('%'),"press");
      button.getActionMap().put("press", press);
      button.addActionListener(press);
      frame.add(button, c);
      button = new JButton("/");
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 5;
      c.gridy = 3;
      button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('/'),"press");
      button.getActionMap().put("press", press);
      button.addActionListener(press);
      frame.add(button, c);
      button = new JButton("-");
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 5;
      c.gridy = 4;
      button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('-'),"press");
      button.getActionMap().put("press", press);
      button.addActionListener(press);
      frame.add(button, c);
      button = new JButton("Back");
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 8;
      c.gridy = 0;
      button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE,0),"press");
      button.getActionMap().put("press", press);
      button.addActionListener(press);
      frame.add(button, c);
      button = new JButton("Clear");
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 8;
      c.gridy = 1;
      button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE ,InputEvent.SHIFT_MASK),"press");
      button.getActionMap().put("press", press);
      button.addActionListener(press);
      frame.add(button, c);
      button = new JButton("Var");
      c.fill = GridBagConstraints.HORIZONTAL;
      c.gridx = 8;
      c.gridy = 2;
      button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('x'),"press");
      button.getActionMap().put("press", press);
      button.addActionListener(press);
      frame.add(button, c);
      button = new JButton("Enter");
      c.fill = GridBagConstraints.VERTICAL;
      c.gridx = 8;
      c.gridy = 3;
      button.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"press");
      button.getActionMap().put("press", press);
      button.addActionListener(press);
      c.gridheight = 4;
      frame.add(button, c);
      frame.setVisible(true);
   }
}