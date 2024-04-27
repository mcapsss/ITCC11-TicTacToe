package tictactoe;
// Tictactoe is fun!
import java.awt.*;
import java.awt.event.*;

public class tictactoe extends Frame implements ActionListener {
 Button[] buttons = new Button[9]; 
 int turn = 0; 
 Label statusLabel = new Label("X turn");

 
 public tictactoe() {
     setTitle("Tic Tac Toe");
     setSize(350, 350); 
     setLayout(new BorderLayout());
     setVisible(true);

     addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
             System.exit(0);
         }
     });

     Panel grid = new Panel(new GridLayout(3, 3));
     for (int i = 0; i < 9; i++) {
         buttons[i] = new Button();
         buttons[i].addActionListener(this);
         grid.add(buttons[i]);
     }
     add(grid, BorderLayout.CENTER);

     Panel bottomPanel = new Panel();
     Button newGame = new Button("New Game");
     newGame.addActionListener(e -> resetGame());
     bottomPanel.add(newGame);
     bottomPanel.add(statusLabel);
     add(bottomPanel, BorderLayout.SOUTH);
 }
 
 public void actionPerformed(ActionEvent e) {
     Button b = (Button)e.getSource();
     if (b.getLabel().equals("")) { 
         if (turn % 2 == 0) {
             b.setLabel("X");
             statusLabel.setText("O's turn");
         } else {
             b.setLabel("O");
             statusLabel.setText("X's turn");
         }
         turn++;
         checkWinner();
     }
 }


 private void checkWinner() {
   
     for (int i = 0; i < 3; i++) {
         if (!buttons[3 * i].getLabel().equals("") &&
             buttons[3 * i].getLabel().equals(buttons[3 * i + 1].getLabel()) &&
             buttons[3 * i + 1].getLabel().equals(buttons[3 * i + 2].getLabel())) {
             gameOver(buttons[3 * i].getLabel());
         }
         if (!buttons[i].getLabel().equals("") &&
             buttons[i].getLabel().equals(buttons[i + 3].getLabel()) &&
             buttons[i + 3].getLabel().equals(buttons[i + 6].getLabel())) {
             gameOver(buttons[i].getLabel());
         }
     }
     if (!buttons[0].getLabel().equals("") &&
         buttons[0].getLabel().equals(buttons[4].getLabel()) &&
         buttons[4].getLabel().equals(buttons[8].getLabel())) {
         gameOver(buttons[0].getLabel());
     }
     if (!buttons[2].getLabel().equals("") &&
         buttons[2].getLabel().equals(buttons[4].getLabel()) &&
         buttons[4].getLabel().equals(buttons[6].getLabel())) {
         gameOver(buttons[2].getLabel());
     }
 }

 private void gameOver(String winner) {
     statusLabel.setText(winner + " wins");
     for (Button b : buttons) {
         b.setEnabled(false);
     }
 }

 
 private void resetGame() {
     for (Button b : buttons) {
         b.setLabel("");
         b.setEnabled(true);
     }
     turn = 0;
     statusLabel.setText("X's turn");
 }

 public static void main(String[] args) {
     new tictactoe(); 
 }
}
