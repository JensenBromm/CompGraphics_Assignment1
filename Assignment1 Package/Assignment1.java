import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class Assignment1 extends JFrame{

    public Assignment1()
    {
        //Set title of pop out window
        super("Jensen Bromm");

        //create panel for drawing
            MyPanel myPanel=new MyPanel();

        //Create a box that stores all the buttons and other gui things
        Container c=this.getContentPane();
        c.setLayout(new BorderLayout());

        //Create Button to move object up
        JButton up=new JButton("^");
        up.setSize(50, 50);
        up.addActionListener(e->{
            myPanel.moveDrawing(0, -5);
        });
        //Create Button to move object down
        JButton down=new JButton("v");
        down.addActionListener(e->{
            myPanel.moveDrawing(0, 5);
        });
        //Create Button to move object left
        JButton left=new JButton("<");
        left.addActionListener(e->{
            myPanel.moveDrawing(-5, 0);
        });
        //Create Button to move object right
        JButton right=new JButton(">");
        right.addActionListener(e->{
            myPanel.moveDrawing(5, 0);
        });
        //Create Buttons for Colors
        JRadioButton red= new JRadioButton("Red");
        JRadioButton green= new JRadioButton("Green");
        JRadioButton blue= new JRadioButton("Blue");

        red.addActionListener(e->{
            green.setSelected(false);
            blue.setSelected(false);

            myPanel.changeColor(Color.RED);
        });

        green.addActionListener(e->{
            red.setSelected(false);
            blue.setSelected(false);

            myPanel.changeColor(Color.GREEN);
        });

        blue.addActionListener(e->{
            red.setSelected(false);
            green.setSelected(false);

            myPanel.changeColor(Color.BLUE);
        } );

        //Create box to store buttons
        Box box=Box.createVerticalBox();
        //Add buttons
            box.add(red);
            box.add(green);
            box.add(blue);
            box.add(up);
            box.add(down);
            box.add(left);
            box.add(right);

        //Create box to center the buttons on the right side of the screen
            Box box1=Box.createHorizontalBox();
            box1.add(box,BorderLayout.CENTER);
        //Add the box to the pane
        c.add(box1, BorderLayout.EAST);

        c.add(myPanel,BorderLayout.CENTER);

    }
    public static void main(String[] args) throws Exception {
        //Setup GUI window
        Assignment1 frame=new Assignment1();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Program stops when closed

        frame.setBounds(100, 100, 1000, 750);// Set size and bounds of window

        //Set the visibility of the window
        frame.setVisible(true);
    }
}

class MyPanel extends JPanel{

    int x=100;
    int y=100;
    public Color c=Color.RED;
    

    public MyPanel() {
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                //Get mouse coordinates and subtract the radius of circle to center it on mouse
                x = me.getX()-25;
                y = me.getY()-25;
                //Repaints the object
                repaint();
            }
        });
    }

    public void changeColor(Color set)
    {
        //Change color and repaint
        c=set;
        repaint();
    }

    public void moveDrawing(int right, int down)
    {
        //Move Drawings with Arrow Keys
        x+=right;
        y+=down;
        repaint();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(c);
        g.fillOval(x,y, 50, 50);
        g.setColor(Color.BLACK);
        g.drawOval(x, y, 50, 50);

        g.drawLine(x-25,y+25, x+75, y+25);
        g.drawLine(x+25,y-25,x+25,y+75);
    }
}
