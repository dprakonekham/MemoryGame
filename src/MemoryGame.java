//Description: A game where you have to match pairs of images until you find of all the pairs

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.ArrayList;
import javax.swing.Timer;

public class MemoryGame implements ItemListener,ActionListener {
    private JToggleButton gameboy;
    private JToggleButton gameboy2;
    private JToggleButton clock;
    private JToggleButton clock2;
    private JToggleButton circus;
    private JToggleButton circus2;
    private JToggleButton lightbulb;
    private JToggleButton lightbulb2;
    private JToggleButton camera;
    private JToggleButton camera2;
    private JToggleButton burger;
    private JToggleButton burger2;
    private Timer thetimer; // the swing timer
    private ImageIcon micon;
    private ImageIcon igameboy;
    private ImageIcon iclock;
    private ImageIcon icircus;
    private ImageIcon ilightbulb;
    private ImageIcon icamera;
    private ImageIcon iburger;
    private int countingtime; //the timer that increases incrementally
    private int matchingcounter = -1; //Counts the amount of times an image is pressed
    private int minutes = 0;
    private int seconds = 0;
    private int hour = 0;
    public static boolean DEBUG = false; //debug flag: ENABLES WHEN TWO TILES ARE CLICKED

    private JMenuItem pause;
    private JMenuItem resume;
    private JMenuItem exit;
    private JMenuItem reveal;
    private JMenuItem vhelp;
    private JMenuItem about;
    private boolean timerflag = true; //checks if the timer is on or off

    public MemoryGame() {
        JFrame frame = new JFrame("Memory Game");
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null); //centered in the middle of the screen
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        JMenuBar bar = new JMenuBar();

        JMenu action = new JMenu("Action");
        action.setMnemonic('a');
        JMenu help = new JMenu("Help");
        help.setMnemonic('h');

        JMenu gt = new JMenu("Game Timer");
        gt.setMnemonic('t');
        pause = new JMenuItem("Pause",'p');
        pause.setAccelerator(KeyStroke.getKeyStroke("control P"));
        resume = new JMenuItem("Resume",'r');
        resume.setAccelerator(KeyStroke.getKeyStroke("control R"));

        gt.add(pause);
        gt.add(resume);

        reveal = new JMenuItem("Reveal",'r');
        exit = new JMenuItem("Exit",'x');
        vhelp = new JMenuItem("View Help...",'h');
        about = new JMenuItem("About",'a');

        pause.addActionListener(this);
        resume.addActionListener(this);
        reveal.addActionListener(this);
        exit.addActionListener(this);
        vhelp.addActionListener(this);
        about.addActionListener(this);

        action.add(gt);
        action.add(reveal);
        action.addSeparator();
        action.add(exit);

        bar.add(action);

        help.add(vhelp);
        help.addSeparator();
        help.add(about);
        bar.add(help);

        micon = new ImageIcon("MemoryGame.png");
        frame.setIconImage(micon.getImage()); //The icon of the program is now MemoryGame
        igameboy = new ImageIcon("1.png");
        iclock = new ImageIcon("2.png");
        icircus = new ImageIcon("3.png");
        ilightbulb = new ImageIcon("4.png");
        icamera = new ImageIcon("5.png");
        iburger = new ImageIcon("6.png");

        JPanel timer = new JPanel(new GridLayout(1, 4));
        JLabel t = new JLabel("00:00:00"); //Timer Display
        t.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        t.setHorizontalAlignment(JLabel.CENTER);
        timer.add(t);
        frame.add(timer, BorderLayout.NORTH);//Will be added up north

        gameboy = new JToggleButton(micon); //Creating and adding icons to the JToggleButtons
        gameboy.addItemListener(this);

        gameboy2 = new JToggleButton(micon);
        gameboy2.addItemListener(this);

        clock = new JToggleButton(micon);
        clock.addItemListener(this);

        clock2 = new JToggleButton(micon);
        clock2.addItemListener(this);

        circus = new JToggleButton(micon);
        circus.addItemListener(this);

        circus2 = new JToggleButton(micon);
        circus2.addItemListener(this);

        lightbulb = new JToggleButton(micon);
        lightbulb.addItemListener(this);

        lightbulb2 = new JToggleButton(micon);
        lightbulb2.addItemListener(this);

        camera = new JToggleButton(micon);
        camera.addItemListener(this);

        camera2 = new JToggleButton(micon);
        camera2.addItemListener(this);

        burger = new JToggleButton(micon);
        burger.addItemListener(this);

        burger2 = new JToggleButton(micon);
        burger2.addItemListener(this);

        ArrayList<JToggleButton> collection = new ArrayList<>(); //Creating a list so I can shuffle it
        collection.add(gameboy);
        collection.add(gameboy2);
        collection.add(clock);
        collection.add(clock2);
        collection.add(circus);
        collection.add(circus2);
        collection.add(lightbulb);
        collection.add(lightbulb2);
        collection.add(camera);
        collection.add(camera2);
        collection.add(burger);
        collection.add(burger2);
        Collections.shuffle(collection); //The tiles are randomly shuffled

        JPanel match = new JPanel(new GridLayout(3, 4));// Adding the elements of the shuffled list to a JPanel
        for (int i = 0; i < collection.size(); i++) {
            match.add(collection.get(i));
        }
        frame.add(match, BorderLayout.CENTER); //Adding the JPanel will the shuffled contents

        frame.setJMenuBar(bar);
        frame.setVisible(true);

        if(DEBUG)
        {
                gameboy.setIcon(igameboy);
                gameboy2.setIcon(igameboy);
                clock.setIcon(iclock);
                clock2.setIcon(iclock);
                circus.setIcon(icircus);
                circus2.setIcon(icircus);
                lightbulb.setIcon(ilightbulb);
                lightbulb2.setIcon(ilightbulb);
                camera.setIcon(icamera);
                camera2.setIcon(icamera);
                burger.setIcon(iburger);
                burger2.setIcon(iburger);
        }

        ActionListener timerAL = new ActionListener() {
            public void actionPerformed(ActionEvent ee)
            {
                thetimer.setDelay(10);
                countingtime++;
                seconds = countingtime / 100;

                if(seconds == 60) //everytime the timer reaches 60 seconds, one minute is added
                {
                    countingtime = 0; //resets to 0 when seconds reaches 60
                    minutes = minutes + 1;
                }
                if(minutes == 60)//everytime the timer reaches 60 minute, one hour is added
                {
                    minutes = 0;//resets to 0 when minutes reaches 60
                    hour = hour + 1;
                }

                t.setText(String.format("%02d", hour) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds)); //Adding the time to the JLabel t

                if (!gameboy.isEnabled() && !clock.isEnabled() && !circus.isEnabled() && !lightbulb.isEnabled() && !camera.isEnabled() && !burger.isEnabled())
                {
                    thetimer.stop(); //stops the timer
                    timerflag = false;
                }
                if(DEBUG) { //If the debug flag is true: enter DEBUG mode
                    if (!gameboy.isEnabled() && !clock.isEnabled() && !circus.isEnabled() && !lightbulb.isEnabled() && !camera.isEnabled() && !burger.isEnabled()) { // when all the tiles are matched, all the tiles will be replaced with the game image
                        gameboy.setIcon(micon);
                        gameboy2.setIcon(micon);
                        clock.setIcon(micon);
                        clock2.setIcon(micon);
                        circus.setIcon(micon);
                        circus2.setIcon(micon);
                        lightbulb.setIcon(micon);
                        lightbulb2.setIcon(micon);
                        camera.setIcon(micon);
                        camera2.setIcon(micon);
                        burger.setIcon(micon);
                        burger2.setIcon(micon);
                    }
                    if (matchingcounter > 2) {
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            System.out.println("Interruption");
                        }
                        if (gameboy.isEnabled()) {
                            gameboy.setSelected(false);
                        }
                        if (gameboy2.isEnabled()) {
                            gameboy2.setSelected(false);
                        }
                        if (clock.isEnabled()) {
                            clock.setSelected(false);
                        }
                        if (clock2.isEnabled()) {
                            clock2.setSelected(false);
                        }
                        if (circus.isEnabled()) {
                            circus.setSelected(false);
                        }
                        if (circus2.isEnabled()) {
                            circus2.setSelected(false);
                        }
                        if (lightbulb.isEnabled()) {
                            lightbulb.setSelected(false);
                        }
                        if (lightbulb2.isEnabled()) {
                            lightbulb2.setSelected(false);
                        }
                        if (camera.isEnabled()) {
                            camera.setSelected(false);
                        }
                        if (camera2.isEnabled()) {
                            camera2.setSelected(false);
                        }
                        if (burger.isEnabled()) {
                            burger.setSelected(false);
                        }
                        if (burger2.isEnabled()) {
                            burger2.setSelected(false);
                        }
                        matchingcounter = 0;
                    }
                }//end of debug
            if(matchingcounter>2)//If # of clicks is more than 2
            {
                try
                {
                    Thread.sleep(1500); //Pauses the thread for 1.5 seconds
                }
                catch(InterruptedException e)
                {
                    System.out.println("Interruption");
                }
                if(gameboy.isEnabled())
                {
                    gameboy.setIcon(micon);//resets the icon to default
                    gameboy.setSelected(false);
                }
                if(gameboy2.isEnabled())
                {
                    gameboy2.setIcon(micon);//resets the icon to default
                    gameboy2.setSelected(false);
                }
                if(clock.isEnabled())
                {
                    clock.setIcon(micon);//resets the icon to default
                    clock.setSelected(false);
                }
                if(clock2.isEnabled())
                {
                    clock2.setIcon(micon);//resets the icon to default
                    clock2.setSelected(false);
                }
                if(circus.isEnabled())
                {
                    circus.setIcon(micon);//resets the icon to default
                    circus.setSelected(false);
                }
                if(circus2.isEnabled())
                {
                    circus2.setIcon(micon);//resets the icon to default
                    circus2.setSelected(false);
                }
                if(lightbulb.isEnabled())
                {
                    lightbulb.setIcon(micon);//resets the icon to default
                    lightbulb.setSelected(false);
                }
                if(lightbulb2.isEnabled())
                {
                    lightbulb2.setIcon(micon);//resets the icon to default
                    lightbulb2.setSelected(false);
                }
                if(camera.isEnabled())
                {
                    camera.setIcon(micon);//resets the icon to default
                    camera.setSelected(false);
                }
                if(camera2.isEnabled())
                {
                    camera2.setIcon(micon);//resets the icon to default
                    camera2.setSelected(false);
                }
                if(burger.isEnabled())
                {
                    burger.setIcon(micon);//resets the icon to default
                    burger.setSelected(false);
                }
                if(burger2.isEnabled())
                {
                    burger2.setIcon(micon);//resets the icon to default
                    burger2.setSelected(false);
                }
                matchingcounter = 0; //Resets the number of clicks to 0
            }
        }
    };
        thetimer = new Timer(10,timerAL);
        thetimer.setInitialDelay(0);
    }
    public void itemStateChanged(ItemEvent ie) {
        thetimer.start(); //Starting the timer when the item state is changed
        timerflag = true;
            if (matchingcounter < 3) { // If the amount of clicks exceeds 2
                if (ie.getSource().equals(gameboy)) {
                    matchingcounter++;//increment # of clicks
                    gameboy.setIcon(igameboy);//Changing the icon to the real icon
                    if (gameboy.isSelected() && gameboy2.isSelected()) // If it is a match, disabled the buttons
                    {
                        gameboy.setEnabled(false);//disabled the JToggleButton
                        gameboy2.setEnabled(false);//disabled the JToggleButton
                        matchingcounter = -1;//resets the # of clicks
                    } else if (ie.getStateChange() == ItemEvent.DESELECTED) { //This is to ensure that if the user clicks the same icon it will be reset
                        gameboy.setIcon(micon);
                        matchingcounter = -1;//resets the # of clicks
                    } else if (!(gameboy.isSelected() && gameboy2.isSelected())) {// if it is not a match, increment # of clicks
                        matchingcounter++; //increments the # of clicks
                    }
                } else if (ie.getSource().equals(gameboy2)) {
                    matchingcounter++;//Starting the timer when the item state is changed
                    gameboy2.setIcon(igameboy);//Changing the icon to the real icon
                    if (gameboy2.isSelected() && gameboy.isSelected())// If it is a match, disabled the buttons
                    {
                        gameboy.setEnabled(false);//disabled the JToggleButton
                        gameboy2.setEnabled(false);//disabled the JToggleButton
                        matchingcounter = -1;//resets the # of clicks
                    } else if (ie.getStateChange() == ItemEvent.DESELECTED) {//This is to ensure that if the user clicks the same icon it will be reset
                        gameboy2.setIcon(micon);
                        matchingcounter = -1;//resets the # of clicks
                    } else if (!(gameboy2.isSelected() && gameboy.isSelected())) { // if it is not a match, increment # of clicks
                        matchingcounter++;//increments the # of clicks
                    }
                } else if (ie.getSource().equals(clock)) {
                    matchingcounter++;//increments the # of clicks
                    clock.setIcon(iclock);//Changing the icon to the real icon
                    if (clock.isSelected() && clock2.isSelected())// If it is a match, disabled the buttons
                    {
                        clock.setEnabled(false);//disabled the JToggleButton
                        clock2.setEnabled(false);//disabled the JToggleButton
                        matchingcounter = -1;//resets the # of clicks
                    } else if (ie.getStateChange() == ItemEvent.DESELECTED) {//This is to ensure that if the user clicks the same icon it will be reset
                        clock.setIcon(micon);//Changing the icon to default
                        matchingcounter = -1;//resets the # of clicks
                    } else if (!(clock.isSelected() && clock2.isSelected())) {// if it is not a match, increment # of clicks
                        matchingcounter++;//increments the # of clicks
                    }
                } else if (ie.getSource().equals(clock2)) {
                    matchingcounter++;
                    clock2.setIcon(iclock);
                    if (clock2.isSelected() && clock.isSelected())// If it is a match, disabled the buttons
                    {
                        clock.setEnabled(false);
                        clock2.setEnabled(false);
                        matchingcounter = -1;
                    } else if (ie.getStateChange() == ItemEvent.DESELECTED) {
                        clock2.setIcon(micon);
                        matchingcounter = -1;
                    } else if (!(clock2.isSelected() && clock.isSelected())) {
                        matchingcounter++;
                    }
                } else if (ie.getSource().equals(circus)) {
                    matchingcounter++;
                    circus.setIcon(icircus);
                    if (circus.isSelected() && circus2.isSelected())// If it is a match, disabled the buttons
                    {
                        circus.setEnabled(false);
                        circus2.setEnabled(false);
                        matchingcounter = -1;
                    } else if (ie.getStateChange() == ItemEvent.DESELECTED) {
                        circus.setIcon(micon);
                        matchingcounter = -1;
                    } else if (!(circus.isSelected() && circus2.isSelected())) {
                        matchingcounter++;
                    }
                } else if (ie.getSource().equals(circus2)) {
                    matchingcounter++;
                    circus2.setIcon(icircus);
                    if (circus2.isSelected() && circus.isSelected())// If it is a match, disabled the buttons
                    {
                        circus.setEnabled(false);
                        circus2.setEnabled(false);
                        matchingcounter = -1;
                    } else if (ie.getStateChange() == ItemEvent.DESELECTED) {
                        circus2.setIcon(micon);
                        matchingcounter = -1;
                    } else if (!(circus2.isSelected() && circus.isSelected())) {
                        matchingcounter++;
                    }
                } else if (ie.getSource().equals(lightbulb)) {
                    matchingcounter++;
                    lightbulb.setIcon(ilightbulb);
                    if (lightbulb.isSelected() && lightbulb2.isSelected())// If it is a match, disabled the buttons
                    {
                        lightbulb.setEnabled(false);
                        lightbulb2.setEnabled(false);
                        matchingcounter = -1;
                    } else if (ie.getStateChange() == ItemEvent.DESELECTED) {
                        lightbulb.setIcon(micon);
                        matchingcounter = -1;
                    } else if (!(lightbulb.isSelected() && lightbulb2.isSelected())) {
                        matchingcounter++;
                    }
                } else if (ie.getSource().equals(lightbulb2)) {
                    matchingcounter++;
                    lightbulb2.setIcon(ilightbulb);
                    if (lightbulb2.isSelected() && lightbulb.isSelected())// If it is a match, disabled the buttons
                    {
                        lightbulb.setEnabled(false);
                        lightbulb2.setEnabled(false);
                        matchingcounter = -1;
                    } else if (ie.getStateChange() == ItemEvent.DESELECTED) {
                        lightbulb2.setIcon(micon);
                        matchingcounter = -1;
                    } else if (!(lightbulb2.isSelected() && lightbulb.isSelected())) {
                        matchingcounter++;
                    }
                } else if (ie.getSource().equals(camera)) {
                    matchingcounter++;
                    camera.setIcon(icamera);
                    if (camera.isSelected() && camera2.isSelected())// If it is a match, disabled the buttons
                    {
                        camera.setEnabled(false);
                        camera2.setEnabled(false);
                        matchingcounter = -1;
                    } else if (ie.getStateChange() == ItemEvent.DESELECTED) {
                        camera.setIcon(micon);
                        matchingcounter = -1;
                    } else if (!(camera.isSelected() && camera2.isSelected())) {
                        matchingcounter++;
                    }
                } else if (ie.getSource().equals(camera2)) {
                    matchingcounter++;
                    camera2.setIcon(icamera);
                    if (camera2.isSelected() && camera.isSelected())// If it is a match, disabled the buttons
                    {
                        camera.setEnabled(false);
                        camera2.setEnabled(false);
                        matchingcounter = -1;
                    } else if (ie.getStateChange() == ItemEvent.DESELECTED) {
                        camera2.setIcon(micon);
                        matchingcounter = -1;
                    } else if (!(camera2.isSelected() && camera.isSelected())) {
                        matchingcounter++;
                    }
                } else if (ie.getSource().equals(burger)) {
                    matchingcounter++;
                    burger.setIcon(iburger);
                    if (burger.isSelected() && burger2.isSelected())// If it is a match, disabled the buttons
                    {
                        burger.setEnabled(false);
                        burger2.setEnabled(false);
                        matchingcounter = -1;
                    } else if (ie.getStateChange() == ItemEvent.DESELECTED) {
                        burger.setIcon(micon);
                        matchingcounter = -1;
                    } else if (!(burger.isSelected() && burger2.isSelected())) {
                        matchingcounter++;
                    }
                } else if (ie.getSource().equals(burger2)) {
                    matchingcounter++;
                    burger2.setIcon(iburger);
                    if (burger2.isSelected() && burger.isSelected())// If it is a match, disabled the buttons
                    {
                        burger.setEnabled(false);
                        burger2.setEnabled(false);
                        matchingcounter = -1;
                    } else if (ie.getStateChange() == ItemEvent.DESELECTED) {
                        burger2.setIcon(micon);
                        matchingcounter = -1;
                    } else if (!(burger2.isSelected() && burger.isSelected())) {
                        matchingcounter++;
                    }
                }
            }
        }
        public void reveal()
        {
            if(DEBUG)
            {
                return;
            }
            else
            {
                thetimer.stop();
                timerflag = false;
                gameboy.setIcon(igameboy);
                gameboy2.setIcon(igameboy);
                clock.setIcon(iclock);
                clock2.setIcon(iclock);
                circus.setIcon(icircus);
                circus2.setIcon(icircus);
                lightbulb.setIcon(ilightbulb);
                lightbulb2.setIcon(ilightbulb);
                camera.setIcon(icamera);
                camera2.setIcon(icamera);
                burger.setIcon(iburger);
                burger2.setIcon(iburger);
            }
        }
        public void actionPerformed(ActionEvent ae)
        {
            if(ae.getSource().equals(pause))
            {
                if(!timerflag)
                {
                    return;
                }
                else
                {
                    thetimer.stop();
                }
            }
            else if(ae.getSource().equals(resume))
            {
                if(!timerflag)
                {
                    return;
                }
                thetimer.start();
            }
            else if(ae.getSource().equals(reveal))
            {
                reveal();
            }
            else if(ae.getSource().equals(exit))
            {
                System.exit(0);
            }
            else if(ae.getSource().equals(vhelp))
            {
                thetimer.stop();
                JOptionPane.showMessageDialog(null,"Match the tile with the same exact tile until you match all of them. The timer starts when you click on a tile","Help",JOptionPane.INFORMATION_MESSAGE);

            }
            else if(ae.getSource().equals(about))
            {
                thetimer.stop();
                JOptionPane.showMessageDialog(null,"(c)Danny Prakonekham","About",JOptionPane.INFORMATION_MESSAGE,micon);
            }

        }
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                for(int i = 0;i<args.length;i++)//looping args
                {
                    if(args[i].equals("debug"))//if it contains debug
                    {
                        DEBUG = true; //debug mode activated
                    }
                    else
                    {
                        DEBUG = false;
                    }
                }
                new MemoryGame();
            }
        });
    }

}
