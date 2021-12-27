
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class Tic_tac_toe extends JFrame implements ActionListener {
// to get screen to set text on click

    public static int boardSize = 3;

    public static enum gameStates{
        Incomplete, Xwins, Zwins, Tie;
    }

    private JButton[][] buttons= new JButton[boardSize][boardSize];
    boolean crossTurn = true;

    public Tic_tac_toe(){
        super.setTitle("Tic Tac Toe");
        super.setSize(900, 900);
        GridLayout gridLayout = new GridLayout(boardSize, boardSize);
        super.setLayout(gridLayout);
        Font font = new Font("Arial", 1, 200);
        // 1 is  bold style and 200px is size

            for(int row = 0; row < boardSize; row++)
             {
            for(int column = 0; column < boardSize; column++)
                {
                JButton button = new JButton("");
                button.setFont(font);       
                buttons[row][column] = button;
                button.addActionListener(this);
                super.add(button);


                }
            }
        super.setVisible(true);
        super.setResizable(false);
    }

    @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton)e.getSource();
            makeMove(clickedButton);
            gameStates gs = this.getGameStatus();
            if(gs== gameStates.Incomplete) return;
            declarewinner(gs);  

            int choice= JOptionPane.showConfirmDialog(this, "Do you wanna restart the game");
            if(choice== JOptionPane.YES_OPTION){
                   for(int row=0;row<boardSize;row++) {
                    for(int col=0; col<boardSize;col++){
                    buttons[row][col].setText("");
                      
                    }
                }
                crossTurn= true;
            }
            else super.dispose();
        }

        private void makeMove(JButton clickedButton) {
            String btntext= clickedButton.getText();
            if(btntext.length()>0){
                JOptionPane.showMessageDialog(this, "Invalid Move");
            }
            else{
                if(crossTurn==true){
                    clickedButton.setText("X");
                }
                else clickedButton.setText("0");
                crossTurn=!crossTurn;

            }
        }
        private gameStates getGameStatus(){
            String text1= "", text2= "";
            int row=0, col=0;

    // check for text in all rows
            while(row<boardSize){
                col=0;
                while(col<boardSize-1){
                    text1= buttons[row][col].getText();
                    text2= buttons[row][col+1].getText();
                    if(!text1.equals(text2) || text1.length()==0){
                        break;
                    }
                    col++;
                }
                if(col==boardSize-1){
                    if(text1.equals("X")) return gameStates.Xwins;
                    else return gameStates.Zwins;
                }
                row++;
            }


    // check for text in all columns
                col=0;
             while(col<boardSize){
                row=0;
                while(row<boardSize-1){
                    text1= buttons[row][col].getText();
                    text2= buttons[row+1][col].getText();
                    if(!text1.equals(text2) || text1.length()==0){
                        break;
                    }
                    row++;
                }
                if(row==boardSize-1){
                    if(text1.equals("X")) return gameStates.Xwins;
                    else return gameStates.Zwins;
                }
                col++;
            }

    // check for text in 1st diagnol starting from row=0
            row=0;
            col=0;
             while(col<boardSize){
                    text1= buttons[row][col].getText();
                    text2= buttons[row+1][col+1].getText();
                    if(!text1.equals(text2) || text1.length()==0){
                        break;
                    }
                    row++;
                    col++;
                }
                if(row==boardSize-1){
                    if(text1.equals("X")) return gameStates.Xwins;
                    else return gameStates.Zwins;
                }
    // check for text in 2nd diagnol starting from row=boardSize-1;

            row=boardSize-1;
            col=0;
             while(col<boardSize && row>0){
                    text1= buttons[row][col].getText();
                    text2= buttons[row-1][col+1].getText();
                    if(!text1.equals(text2) || text1.length()==0){
                        break;
                    }
                    row--;
                    col++;
                }
                if(col==boardSize-1){
                    if(text1.equals("X")) return gameStates.Xwins;
                    else return gameStates.Zwins;
                }

                String text= "";
                for(int r=0;r<boardSize;r++) {
                    for(int c=0; c<boardSize;c++){

                        text =buttons[r][c].getText();
                        if(text.length()==0){
                            return gameStates.Incomplete;
                        }
                    }
                }
                return gameStates.Tie;

        }

        private void declarewinner(gameStates gs){
            if(gs== gameStates.Xwins)
            JOptionPane.showMessageDialog(this, "X has won the game");
             else if(gs== gameStates.Zwins)
            JOptionPane.showMessageDialog(this, "Z has won the game");
            else
            JOptionPane.showMessageDialog(this, "oops! Tie");
        }

}
