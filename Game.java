package Pianica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.Random;

public class Game extends JFrame {
    JButton start = new JButton("Next");
    JButton jpg = new JButton("");
    JLabel panel1 = new JLabel();
    JLabel panel2= new JLabel();
    JLabel label1 = new JLabel("");
    JLabel label2 = new JLabel("");
    JPanel panel = new JPanel();
    eHandler handler = new eHandler();
    Player one;
    Player two;
    JLabel[][] rubashki = new JLabel[2][60];
    GridBagConstraints bag = new GridBagConstraints();

    public Game(Player one, Player two){
        setLayout(new FlowLayout());
        add(panel);
        panel.setLayout(new GridBagLayout());


        bag.fill = GridBagConstraints.HORIZONTAL;
        bag.weightx = 0.5;
        bag.insets = new Insets(0, 0,0, 5 );
        bag.gridx = 0;
        bag.gridy = 0;
        panel.add(panel1, bag);


        bag.fill = GridBagConstraints.HORIZONTAL;
        bag.weightx = 0.5;
        bag.insets = new Insets(0, 5,0, 0 );
        bag.gridx = 1;
        bag.gridy = 0;
        panel.add(panel2, bag);

        rubashki();

        /*bag.fill = GridBagConstraints.HORIZONTAL;
        bag.weightx = 0.5;
        bag.gridx = 2;
        bag.gridy = 0;
        panel.add(label1, bag);



        bag.fill = GridBagConstraints.HORIZONTAL;
        bag.anchor = GridBagConstraints.CENTER;
        bag.weightx = 0.5;
        bag.gridx = 3;
        bag.gridy = 0;
        panel.add(label2, bag);*/

        /*bag.fill = GridBagConstraints.HORIZONTAL;
        bag.weightx = 0.5;
        bag.gridx = 0;
        bag.gridy = 20;
        bag.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(start, bag);*/


        peretasovka(one, two);

        panel1.setIcon(imgtoicon("C:\\Users\\Aver\\Pictures\\card\\" + one.getFirst().getRankString() + "\\" + one.getFirst().getRankString() + "_" + one.getFirst().getMastString() + ".png", 120, 167));
        panel2.setIcon(imgtoicon("C:\\Users\\Aver\\Pictures\\card\\" + two.getFirst().getRankString() + "\\" + two.getFirst().getRankString() + "_" + two.getFirst().getMastString() + ".png", 120, 167));
        label1.setText(Integer.toString(one.getAmount()));
        label2.setText(Integer.toString(two.getAmount()));
        this.one = one;
        this.two = two;
        start.addActionListener(handler);
        jpg.addActionListener(handler);
    }
    public void rubashki(){
        for (int i = 0; i < 50; i++) {
            bag.fill = GridBagConstraints.HORIZONTAL;
            bag.weightx = 0.5;
            bag.insets = new Insets(0, 0,0, 5);
            bag.gridx = 0;
            bag.gridy = i + 1;
            rubashki[0][i] = new JLabel();
            panel.add(rubashki[0][i], bag);
            rubashki[0][i].setIcon(imgtoicon("C:\\Users\\Aver\\Pictures\\card\\Rubaska.jpg",120, 12));

            bag.fill = GridBagConstraints.HORIZONTAL;
            bag.weightx = 0.5;
            bag.insets = new Insets(0, 5,0, 0);
            bag.gridx = 1;
            bag.gridy = i + 1;
            rubashki[1][i] = new JLabel();
            panel.add(rubashki[1][i], bag);
            rubashki[1][i].setIcon(imgtoicon("C:\\Users\\Aver\\Pictures\\card\\Rubaska.jpg",120, 12));
        }
        for (int i = 18; i < 50; i++) {
            delRubaska(0, i);
            delRubaska(1, i);
        }
    }

    public void addRubaska(int x, int y){
        rubashki[x][y].setVisible(true);
    }

    public void delRubaska(int x, int y){
        rubashki[x][y].setVisible(false);
    }

    public ImageIcon imgtoicon(String s, int x, int y){
        ImageIcon imageIcon = new ImageIcon(s);
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(x, y, Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

    public void play(int level) throws InterruptedException {
        int i = 0;
        while (one.EstKarti() && two.EstKarti()){
            jpg.doClick();
            Thread.sleep(level);
            start.doClick();
            i++;
        }
        if (one.getAmount() == 0){
            panel1.setIcon(imgtoicon("C:\\Users\\Aver\\Pictures\\card\\DEFOULT.png", 120, 167));
        }
        else if (two.getAmount() == 0){
            panel2.setIcon(imgtoicon("C:\\Users\\Aver\\Pictures\\card\\DEFOULT.png", 120, 167));
        }
    }

    public void peretasovka(Player one, Player two) {
        boolean[][] koloda = new boolean[4][9];
        int k = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 9; j++) {
                koloda[i][j] = false;
            }
        }

        int rank;
        int mast;
        int monetka = 0;
        Random random = new Random();


        while (k != 36) {
            mast = random.nextInt(4);
            rank = random.nextInt(9);

            if (koloda[mast][rank] == false) {
                koloda[mast][rank] = true;
                if (monetka == 0) {
                    one.add(rank, mast);
                    monetka = 1;
                }
                else {
                    two.add(rank, mast);
                    monetka = 0;
                }
                k++;
            }
        }
    }

    public int sravnenie(Card one, Card two) {

        if (one.getRank() == Enum.Rank.SIX.ordinal() && two.getRank() == Enum.Rank.TUZ.ordinal())
            return 1;
        else if (one.getRank() == Enum.Rank.TUZ.ordinal() && two.getRank() == Enum.Rank.SIX.ordinal())
            return 0;
        else if (one.getRank() > two.getRank())
            return 1;
        else if (one.getRank() < two.getRank())
            return 0;
        else if (one.getRank() == two.getRank())
            return 3;
        else
            return 4;
    }




    public class eHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jpg){

                panel1.setIcon(imgtoicon("C:\\Users\\Aver\\Pictures\\card\\" + one.getFirst().getRankString() + "\\" + one.getFirst().getRankString() + "_" + one.getFirst().getMastString() + ".png", 120, 167));
                panel2.setIcon(imgtoicon("C:\\Users\\Aver\\Pictures\\card\\" + two.getFirst().getRankString() + "\\" + two.getFirst().getRankString() + "_" + two.getFirst().getMastString() + ".png", 120, 167));
            }
            if (e.getSource() == start) {
                /*if ((sravnenie(one.getFirst(), two.getFirst()) == 1) && (two.getAmount() == 1)) {
                    addRubaska(0, one.getAmount() + 0);
                    delRubaska(1, two.getAmount() - 1);

                    one.DobavlenieOne(one.getFirst());
                    one.DobavlenieTwo(two.getFirst());
                    two.Ubavlenie();


                    panel1.setIcon(imgtoicon("C:\\Users\\Aver\\Pictures\\card\\" + one.getFirst().getRankString() + "\\" + one.getFirst().getRankString() + "_" + one.getFirst().getMastString() + ".png", 120, 167));
                    panel2.setIcon(imgtoicon("C:\\Users\\Aver\\Pictures\\card\\DEFOULT.png", 120, 167));

                    label1.setText(Integer.toString(one.getAmount()));
                    label2.setText(Integer.toString(two.getAmount()));
                }
                else if ((sravnenie(one.getFirst(), two.getFirst()) == 0) && (one.getAmount() == 1)) {
                    addRubaska(1, two.getAmount() + 0);
                    delRubaska(0, one.getAmount() - 1);

                    two.DobavlenieOne(two.getFirst());
                    two.DobavlenieTwo(one.getFirst());
                    one.Ubavlenie();


                    panel1.setIcon(imgtoicon("C:\\Users\\Aver\\Pictures\\card\\DEFOULT.png", 120, 167));
                    panel2.setIcon(imgtoicon("C:\\Users\\Aver\\Pictures\\card\\" + two.getFirst().getRankString() + "\\" + two.getFirst().getRankString() + "_" + two.getFirst().getMastString() + ".png", 120, 167));

                    label1.setText(Integer.toString(one.getAmount()));
                    label2.setText(Integer.toString(two.getAmount()));
                }*/
                if (sravnenie(one.getFirst(), two.getFirst()) == 1) {
                    /*panel1.setIcon(imgtoicon("C:\\Users\\Aver\\Pictures\\card\\" + one.getFirst().getRankString() + "\\" + one.getFirst().getRankString() + "_" + one.getFirst().getMastString() + ".png", 120, 167));
                    panel2.setIcon(imgtoicon("C:\\Users\\Aver\\Pictures\\card\\" + two.getFirst().getRankString() + "\\" + two.getFirst().getRankString() + "_" + two.getFirst().getMastString() + ".png", 120, 167));
*/
                    addRubaska(0, one.getAmount() + 0);
                    delRubaska(1, two.getAmount() - 1);

                    one.DobavlenieOne(one.getFirst());
                    one.DobavlenieTwo(two.getFirst());
                    two.Ubavlenie();

                    /*label1.setText(Integer.toString(one.getAmount()));
                    label2.setText(Integer.toString(two.getAmount()));*/
                }
                else if (sravnenie(one.getFirst(), two.getFirst()) == 0) {
                    /*panel1.setIcon(imgtoicon("C:\\Users\\Aver\\Pictures\\card\\" + one.getFirst().getRankString() + "\\" + one.getFirst().getRankString() + "_" + one.getFirst().getMastString() + ".png", 120, 167));
                    panel2.setIcon(imgtoicon("C:\\Users\\Aver\\Pictures\\card\\" + two.getFirst().getRankString() + "\\" + two.getFirst().getRankString() + "_" + two.getFirst().getMastString() + ".png", 120, 167));
*/
                    addRubaska(1, two.getAmount() + 0);
                    delRubaska(0, one.getAmount() - 1);

                    two.DobavlenieOne(two.getFirst());
                    two.DobavlenieTwo(one.getFirst());
                    one.Ubavlenie();


                    /*label1.setText(Integer.toString(one.getAmount()));
                    label2.setText(Integer.toString(two.getAmount()));*/
                }
                else if (sravnenie(one.getFirst(), two.getFirst()) == 3) {
                    /*panel1.setIcon(imgtoicon("C:\\Users\\Aver\\Pictures\\card\\" + one.getFirst().getRankString() + "\\" + one.getFirst().getRankString() + "_" + one.getFirst().getMastString() + ".png", 120, 167));
                    panel2.setIcon(imgtoicon("C:\\Users\\Aver\\Pictures\\card\\" + two.getFirst().getRankString() + "\\" + two.getFirst().getRankString() + "_" + two.getFirst().getMastString() + ".png", 120, 167));
*/
                    ArrayDeque<Card> reserv = new ArrayDeque<Card>();
                    while (sravnenie(one.getFirst(), two.getFirst()) == 3) {
                        delRubaska(0, one.getAmount() - 1);
                        delRubaska(0, one.getAmount() - 2);
                        delRubaska(1, two.getAmount() - 1);
                        delRubaska(1, two.getAmount() - 2);
                        reserv.addLast(one.poolFirst());
                        reserv.addLast(two.poolFirst());
                        reserv.addLast(one.poolFirst());
                        reserv.addLast(two.poolFirst());
                    }

                    if (sravnenie(one.getFirst(), two.getFirst()) == 1) {
                        while (reserv.size() > 0) {
                            addRubaska(0, one.getAmount() + 0);
                            one.DobavlenieTwo(reserv.pollFirst());

                        }
                    }
                    else if (sravnenie(one.getFirst(), two.getFirst()) == 0) {
                        while (reserv.size() > 0) {
                            addRubaska(1, two.getAmount() + 0);
                            two.DobavlenieTwo(reserv.pollFirst());

                        }
                    }

                    /*label1.setText(Integer.toString(one.getAmount()));
                    label2.setText(Integer.toString(two.getAmount()));*/
                }
            }
        }
    }
}
