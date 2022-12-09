import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
class ChessBoard extends JFrame implements ActionListener
{
private JButton button[][];
private JButton pieceToMove;
private String turn;
private Container container;
ChessBoard()
{
initMethod();
setView();
}
private void initMethod()
{
button=new JButton[8][8];
pieceToMove=null;
turn=new String("white");
container=getContentPane();
}
private void setView()
{
for(int i=0;i<8;i++)
{
for(int j=0;j<8;j++)
{
button[i][j]=new JButton();
button[i][j].setBorder(null);
if((i%2==0 && j%2!=0) || (i%2!=0 && j%2==0)) button[i][j].setBackground(Color.black);
else button[i][j].setBackground(Color.white);
}
}
button[0][0].setIcon(new ImageIcon("whiteRook.png"));
button[0][1].setIcon(new ImageIcon("whiteKnight.png"));
button[0][2].setIcon(new ImageIcon("whiteBishop.png"));
button[0][3].setIcon(new ImageIcon("whiteKing.png"));
button[0][4].setIcon(new ImageIcon("whiteQueen.png"));
button[0][5].setIcon(new ImageIcon("whiteBishop.png"));
button[0][6].setIcon(new ImageIcon("whiteKnight.png"));
button[0][7].setIcon(new ImageIcon("whiteRook.png"));

button[7][0].setIcon(new ImageIcon("blackRook.png"));
button[7][1].setIcon(new ImageIcon("blackKnight.png"));
button[7][2].setIcon(new ImageIcon("blackBishop.png"));
button[7][3].setIcon(new ImageIcon("blackKing.png"));
button[7][4].setIcon(new ImageIcon("blackQueen.png"));
button[7][5].setIcon(new ImageIcon("blackBishop.png"));
button[7][6].setIcon(new ImageIcon("blackKnight.png"));
button[7][7].setIcon(new ImageIcon("blackRook.png"));

for(int i=0;i<8;i++)
{
button[1][i].setIcon(new ImageIcon("whitePawn.png"));
button[6][i].setIcon(new ImageIcon("blackPawn.png"));
}

container.setLayout(new GridLayout(8,8));
for(int i=0;i<8;i++)
{
for(int j=0;j<8;j++)
{
container.add(button[i][j]);
button[i][j].addActionListener(this);
}
}

Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
int height=600;
int width=700;
setLocation((d.width/2)-(width/2),(d.height/2)-(height/2));
setSize(width,height);
setVisible(true);
setDefaultCloseOperation(EXIT_ON_CLOSE);
}
public void actionPerformed(ActionEvent ev)
{
JButton selectedButton=(JButton)ev.getSource();
if(selectedButton==pieceToMove) return;
Icon selectedButtonIcon=selectedButton.getIcon();
if(selectedButtonIcon==null && pieceToMove==null) return;
if(selectedButtonIcon!=null)
{
String nameOfSelectedButton=selectedButtonIcon.toString();
if(pieceToMove==null && nameOfSelectedButton.startsWith(turn)==false) 
{
JOptionPane.showMessageDialog(ChessBoard.this,"Turn : "+turn);
return;
}
String nameOfPieceToMove=null;
if(pieceToMove!=null)
{
nameOfPieceToMove=pieceToMove.getIcon().toString();
if((nameOfSelectedButton.startsWith("black") && nameOfPieceToMove.startsWith("white")) || (nameOfSelectedButton.startsWith("white") && nameOfPieceToMove.startsWith("black")))
{
movePiece(pieceToMove,selectedButton);
pieceToMove=null;
return;
}
}
if(nameOfSelectedButton.startsWith("black"))
{
if(pieceToMove!=null && nameOfPieceToMove.startsWith("black"))
{
for(int i=0;i<8;i++)
{
for(int j=0;j<8;j++)
{
button[i][j].setBorder(null);
}
}
}
pieceToMove=selectedButton;
if(nameOfSelectedButton.startsWith("blackPawn")) tryingToMoveBlackPawn(pieceToMove);
if(nameOfSelectedButton.startsWith("blackRook")) tryingToMoveBlackRook(pieceToMove);
if(nameOfSelectedButton.startsWith("blackKnight")) tryingToMoveBlackKnight(pieceToMove);
if(nameOfSelectedButton.startsWith("blackBishop")) tryingToMoveBlackBishop(pieceToMove);
if(nameOfSelectedButton.startsWith("blackQueen")) tryingToMoveBlackQueen(pieceToMove);
if(nameOfSelectedButton.startsWith("blackKing")) tryingToMoveBlackKing(pieceToMove);
}
else
{
if(pieceToMove!=null && nameOfPieceToMove.startsWith("white"))
{
for(int i=0;i<8;i++)
{
for(int j=0;j<8;j++)
{
button[i][j].setBorder(null);
}
}
}
pieceToMove=selectedButton;
if(nameOfSelectedButton.startsWith("whitePawn")) tryingToMoveWhitePawn(pieceToMove);
if(nameOfSelectedButton.startsWith("whiteRook")) tryingToMoveWhiteRook(pieceToMove);
if(nameOfSelectedButton.startsWith("whiteKnight")) tryingToMoveWhiteKnight(pieceToMove);
if(nameOfSelectedButton.startsWith("whiteBishop")) tryingToMoveWhiteBishop(pieceToMove);
if(nameOfSelectedButton.startsWith("whiteQueen")) tryingToMoveWhiteQueen(pieceToMove);
if(nameOfSelectedButton.startsWith("whiteKing")) tryingToMoveWhiteKing(pieceToMove);
}
}
else
{
movePiece(pieceToMove,selectedButton);
pieceToMove=null;
return;
}
}
private void tryingToMoveBlackPawn(JButton selectedButton)
{
int rowIndex=-1;
int columnIndex=-1;
for(int i=0;i<8;i++)
{
for(int j=0;j<8;j++)
{
if(selectedButton==button[i][j])
{
rowIndex=i;
columnIndex=j;
break;
}
}
}
if(rowIndex==0) 
{
return;
}
Icon checkIcon=button[rowIndex-1][columnIndex].getIcon();
if(checkIcon==null) 
{
button[rowIndex-1][columnIndex].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
if(rowIndex==6)
{
if(button[rowIndex-2][columnIndex].getIcon()==null)
{
button[rowIndex-2][columnIndex].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
}
if(columnIndex!=0) 
{
Icon leftIcon=button[rowIndex-1][columnIndex-1].getIcon();
if(leftIcon!=null)
{
boolean leftExists=leftIcon.toString().startsWith("white");
if(leftExists) button[rowIndex-1][columnIndex-1].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
if(columnIndex!=7)
{
Icon rightIcon=button[rowIndex-1][columnIndex+1].getIcon();
if(rightIcon!=null)
{
boolean rightExists=rightIcon.toString().startsWith("white");
if(rightExists) button[rowIndex-1][columnIndex+1].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
}
private void tryingToMoveBlackRook(JButton pieceToMove)
{
int rowIndex=-1;
int columnIndex=-1;
for(int i=0;i<8;i++)
{
for(int j=0;j<8;j++)
{
if(pieceToMove==button[i][j])
{
rowIndex=i;
columnIndex=j;
break;
}
}
}
Icon checkIcon=null;
for(int i=rowIndex+1;i<8;i++)
{
checkIcon=button[i][columnIndex].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("white"))
{
button[i][columnIndex].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[i][columnIndex].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
for(int i=rowIndex-1;i>=0;i--)
{
checkIcon=button[i][columnIndex].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("white"))
{
button[i][columnIndex].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[i][columnIndex].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
for(int i=columnIndex+1;i<8;i++)
{
checkIcon=button[rowIndex][i].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("white"))
{
button[rowIndex][i].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[rowIndex][i].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
} 
for(int i=columnIndex-1;i>=0;i--)
{
checkIcon=button[rowIndex][i].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("white"))
{
button[rowIndex][i].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[rowIndex][i].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
private void tryingToMoveBlackKnight(JButton pieceToMove)
{
int rowIndex=-1;
int columnIndex=-1;
for(int i=0;i<8;i++)
{
for(int j=0;j<8;j++)
{
if(pieceToMove==button[i][j])
{
rowIndex=i;
columnIndex=j;
}
}
}
Icon checkIcon=null;
if((rowIndex+2)<8)
{
if((columnIndex+1)<8) 
{
checkIcon=button[rowIndex+2][columnIndex+1].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("black"))
{
// do nothing
}
else
{
button[rowIndex+2][columnIndex+1].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
if((columnIndex-1)>=0) 
{
checkIcon=button[rowIndex+2][columnIndex-1].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("black"))
{
}
else
{
button[rowIndex+2][columnIndex-1].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
}
if((rowIndex-2)>=0)
{
if((columnIndex+1)<8) 
{
checkIcon=button[rowIndex-2][columnIndex+1].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("black"))
{
}
else
{
button[rowIndex-2][columnIndex+1].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
if((columnIndex-1)>=0) 
{
checkIcon=button[rowIndex-2][columnIndex-1].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("black"))
{
}
else
{
button[rowIndex-2][columnIndex-1].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
}
if((columnIndex+2)<8)
{
if((rowIndex+1)<8) 
{
checkIcon=button[rowIndex+1][columnIndex+2].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("black"))
{
}
else
{
button[rowIndex+1][columnIndex+2].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
if((rowIndex-1)>=0) 
{
checkIcon=button[rowIndex-1][columnIndex+2].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("black"))
{
}
else
{
button[rowIndex-1][columnIndex+2].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
}
if((columnIndex-2)>=0)
{
if((rowIndex+1)<8) 
{
checkIcon=button[rowIndex+1][columnIndex-2].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("black"))
{
}
else
{
button[rowIndex+1][columnIndex-2].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
if((rowIndex-1)>=0) 
{
checkIcon=button[rowIndex-1][columnIndex-2].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("black"))
{
}
else
{
button[rowIndex-1][columnIndex-2].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
}
}
private void tryingToMoveBlackBishop(JButton pieceToMove)
{
int rowIndex=-1;
int columnIndex=-1;
int i,j;
for(i=0;i<8;i++)
{
for(j=0;j<8;j++)
{
if(pieceToMove==button[i][j])
{
rowIndex=i;
columnIndex=j;
break;
}
}
}
Icon checkIcon=null;
for(i=rowIndex-1,j=columnIndex+1;i>=0 && j<8;i--,j++)
{
checkIcon=button[i][j].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("white"))
{
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
for(i=rowIndex-1,j=columnIndex-1;i>=0 && j>=0;i--,j--)
{
checkIcon=button[i][j].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("white"))
{
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
for(i=rowIndex+1,j=columnIndex+1;i<8 && j<8;i++,j++)
{
checkIcon=button[i][j].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("white"))
{
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
for(i=rowIndex+1,j=columnIndex-1;i<8 && j>=0;i++,j--)
{
checkIcon=button[i][j].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("white"))
{
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
private void tryingToMoveBlackQueen(JButton pieceToMove)
{
int rowIndex=-1;
int columnIndex=-1;
int i,j;
for(i=0;i<8;i++)
{
for(j=0;j<8;j++)
{
if(pieceToMove==button[i][j])
{
rowIndex=i;
columnIndex=j;
break;
}
}
}
Icon checkIcon=null;
for(i=rowIndex+1;i<8;i++)
{
checkIcon=button[i][columnIndex].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("white"))
{
button[i][columnIndex].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[i][columnIndex].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
for(i=rowIndex-1;i>=0;i--)
{
checkIcon=button[i][columnIndex].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("white"))
{
button[i][columnIndex].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[i][columnIndex].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
for(i=columnIndex+1;i<8;i++)
{
checkIcon=button[rowIndex][i].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("white"))
{
button[rowIndex][i].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[rowIndex][i].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
} 
for(i=columnIndex-1;i>=0;i--)
{
checkIcon=button[rowIndex][i].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("white"))
{
button[rowIndex][i].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[rowIndex][i].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
for(i=rowIndex-1,j=columnIndex+1;i>=0 && j<8;i--,j++)
{
checkIcon=button[i][j].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("white"))
{
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
for(i=rowIndex-1,j=columnIndex-1;i>=0 && j>=0;i--,j--)
{
checkIcon=button[i][j].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("white"))
{
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
for(i=rowIndex+1,j=columnIndex+1;i<8 && j<8;i++,j++)
{
checkIcon=button[i][j].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("white"))
{
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
for(i=rowIndex+1,j=columnIndex-1;i<8 && j>=0;i++,j--)
{
checkIcon=button[i][j].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("white"))
{
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
private void tryingToMoveBlackKing(JButton pieceToMove)
{
int rowIndex=-1;
int columnIndex=-1;
for(int i=0;i<8;i++)
{
for(int j=0;j<8;j++)
{
if(pieceToMove==button[i][j])
{
rowIndex=i;
columnIndex=j;
}
}
}
Icon checkIcon=null;
if((rowIndex+1)<8)
{
checkIcon=button[rowIndex+1][columnIndex].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("black"))
{
}
else
{
button[rowIndex+1][columnIndex].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
if((columnIndex+1)<8)
{
checkIcon=button[rowIndex+1][columnIndex+1].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("black"))
{
}
else
{
button[rowIndex+1][columnIndex+1].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
if((columnIndex-1)>=0)
{
checkIcon=button[rowIndex+1][columnIndex-1].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("black"))
{
}
else
{
button[rowIndex+1][columnIndex-1].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
}
if((rowIndex-1)>=0)
{
checkIcon=button[rowIndex-1][columnIndex].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("black"))
{
}
else
{
button[rowIndex-1][columnIndex].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
if((columnIndex+1)<8)
{
checkIcon=button[rowIndex-1][columnIndex+1].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("black"))
{
}
else
{
button[rowIndex-1][columnIndex+1].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
if((columnIndex-1)>=0)
{
checkIcon=button[rowIndex-1][columnIndex-1].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("black"))
{
}
else
{
button[rowIndex-1][columnIndex-1].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
}
if((columnIndex+1)<8)
{
checkIcon=button[rowIndex][columnIndex+1].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("black"))
{
}
else
{
button[rowIndex][columnIndex+1].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
if((columnIndex-1)>=0)
{
checkIcon=button[rowIndex][columnIndex-1].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("black"))
{
}
else
{
button[rowIndex][columnIndex-1].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
}






private void tryingToMoveWhitePawn(JButton selectedButton)
{
int rowIndex=-1;
int columnIndex=-1;
for(int i=0;i<8;i++)
{
for(int j=0;j<8;j++)
{
if(selectedButton==button[i][j])
{
rowIndex=i;
columnIndex=j;
break;
}
}
}
if(rowIndex==7)
{
return;
}
Icon checkIcon=button[rowIndex+1][columnIndex].getIcon();
if(checkIcon==null)
{
button[rowIndex+1][columnIndex].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
if(rowIndex==1)
{
if(button[rowIndex+2][columnIndex].getIcon()==null)
{
button[rowIndex+2][columnIndex].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
}
if(columnIndex!=0) 
{
Icon leftIcon=button[rowIndex+1][columnIndex-1].getIcon();
if(leftIcon!=null)
{
boolean leftExists=leftIcon.toString().startsWith("black");
if(leftExists) button[rowIndex+1][columnIndex-1].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
if(columnIndex!=7) 
{
Icon rightIcon=button[rowIndex+1][columnIndex+1].getIcon();
if(rightIcon!=null)
{
boolean rightExists=rightIcon.toString().startsWith("black");
if(rightExists) button[rowIndex+1][columnIndex+1].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
}

private void tryingToMoveWhiteRook(JButton pieceToMove)
{
int rowIndex=-1;
int columnIndex=-1;
for(int i=0;i<8;i++)
{
for(int j=0;j<8;j++)
{
if(pieceToMove==button[i][j])
{
rowIndex=i;
columnIndex=j;
break;
}
}
}
Icon checkIcon=null;
for(int i=rowIndex+1;i<8;i++)
{
checkIcon=button[i][columnIndex].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("black"))
{
button[i][columnIndex].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[i][columnIndex].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
for(int i=rowIndex-1;i>=0;i--)
{
checkIcon=button[i][columnIndex].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("black"))
{
button[i][columnIndex].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[i][columnIndex].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
for(int i=columnIndex+1;i<8;i++)
{
checkIcon=button[rowIndex][i].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("black"))
{
button[rowIndex][i].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[rowIndex][i].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
} 
for(int i=columnIndex-1;i>=0;i--)
{
checkIcon=button[rowIndex][i].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("black"))
{
button[rowIndex][i].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[rowIndex][i].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
private void tryingToMoveWhiteKnight(JButton pieceToMove)
{
int rowIndex=-1;
int columnIndex=-1;
for(int i=0;i<8;i++)
{
for(int j=0;j<8;j++)
{
if(pieceToMove==button[i][j])
{
rowIndex=i;
columnIndex=j;
}
}
}
Icon checkIcon=null;
if((rowIndex+2)<8)
{
if((columnIndex+1)<8) 
{
checkIcon=button[rowIndex+2][columnIndex+1].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("white"))
{
// do nothing
}
else
{
button[rowIndex+2][columnIndex+1].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
if((columnIndex-1)>=0) 
{
checkIcon=button[rowIndex+2][columnIndex-1].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("white"))
{
}
else
{
button[rowIndex+2][columnIndex-1].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
}
if((rowIndex-2)>=0)
{
if((columnIndex+1)<8) 
{
checkIcon=button[rowIndex-2][columnIndex+1].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("white"))
{
}
else
{
button[rowIndex-2][columnIndex+1].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
if((columnIndex-1)>=0) 
{
checkIcon=button[rowIndex-2][columnIndex-1].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("white"))
{
}
else
{
button[rowIndex-2][columnIndex-1].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
}
if((columnIndex+2)<8)
{
if((rowIndex+1)<8) 
{
checkIcon=button[rowIndex+1][columnIndex+2].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("white"))
{
}
else
{
button[rowIndex+1][columnIndex+2].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
if((rowIndex-1)>=0) 
{
checkIcon=button[rowIndex-1][columnIndex+2].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("white"))
{
}
else
{
button[rowIndex-1][columnIndex+2].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
}
if((columnIndex-2)>=0)
{
if((rowIndex+1)<8) 
{
checkIcon=button[rowIndex+1][columnIndex-2].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("white"))
{
}
else
{
button[rowIndex+1][columnIndex-2].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
if((rowIndex-1)>=0) 
{
checkIcon=button[rowIndex-1][columnIndex-2].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("white"))
{
}
else
{
button[rowIndex-1][columnIndex-2].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
}
}
private void tryingToMoveWhiteBishop(JButton pieceToMove)
{
int rowIndex=-1;
int columnIndex=-1;
int i,j;
for(i=0;i<8;i++)
{
for(j=0;j<8;j++)
{
if(pieceToMove==button[i][j])
{
rowIndex=i;
columnIndex=j;
break;
}
}
}
Icon checkIcon=null;
for(i=rowIndex-1,j=columnIndex+1;i>=0 && j<8;i--,j++)
{
checkIcon=button[i][j].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("black"))
{
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
for(i=rowIndex-1,j=columnIndex-1;i>=0 && j>=0;i--,j--)
{
checkIcon=button[i][j].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("black"))
{
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
for(i=rowIndex+1,j=columnIndex+1;i<8 && j<8;i++,j++)
{
checkIcon=button[i][j].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("black"))
{
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
for(i=rowIndex+1,j=columnIndex-1;i<8 && j>=0;i++,j--)
{
checkIcon=button[i][j].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("black"))
{
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
private void tryingToMoveWhiteQueen(JButton pieceToMove)
{
int rowIndex=-1;
int columnIndex=-1;
int i,j;
for(i=0;i<8;i++)
{
for(j=0;j<8;j++)
{
if(pieceToMove==button[i][j])
{
rowIndex=i;
columnIndex=j;
break;
}
}
}
Icon checkIcon=null;
for(i=rowIndex+1;i<8;i++)
{
checkIcon=button[i][columnIndex].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("black"))
{
button[i][columnIndex].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[i][columnIndex].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
for(i=rowIndex-1;i>=0;i--)
{
checkIcon=button[i][columnIndex].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("black"))
{
button[i][columnIndex].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[i][columnIndex].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
for(i=columnIndex+1;i<8;i++)
{
checkIcon=button[rowIndex][i].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("black"))
{
button[rowIndex][i].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[rowIndex][i].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
} 
for(i=columnIndex-1;i>=0;i--)
{
checkIcon=button[rowIndex][i].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("black"))
{
button[rowIndex][i].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[rowIndex][i].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
for(i=rowIndex-1,j=columnIndex+1;i>=0 && j<8;i--,j++)
{
checkIcon=button[i][j].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("black"))
{
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
for(i=rowIndex-1,j=columnIndex-1;i>=0 && j>=0;i--,j--)
{
checkIcon=button[i][j].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("black"))
{
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
for(i=rowIndex+1,j=columnIndex+1;i<8 && j<8;i++,j++)
{
checkIcon=button[i][j].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("black"))
{
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
for(i=rowIndex+1,j=columnIndex-1;i<8 && j>=0;i++,j--)
{
checkIcon=button[i][j].getIcon();
if(checkIcon!=null)
{
if(checkIcon.toString().startsWith("black"))
{
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
break;
}
button[i][j].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
private void tryingToMoveWhiteKing(JButton pieceToMove)
{
int rowIndex=-1;
int columnIndex=-1;
for(int i=0;i<8;i++)
{
for(int j=0;j<8;j++)
{
if(pieceToMove==button[i][j])
{
rowIndex=i;
columnIndex=j;
}
}
}
Icon checkIcon=null;
if((rowIndex+1)<8)
{
checkIcon=button[rowIndex+1][columnIndex].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("white"))
{
}
else
{
button[rowIndex+1][columnIndex].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
if((columnIndex+1)<8)
{
checkIcon=button[rowIndex+1][columnIndex+1].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("white"))
{
}
else
{
button[rowIndex+1][columnIndex+1].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
if((columnIndex-1)>=0)
{
checkIcon=button[rowIndex+1][columnIndex-1].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("white"))
{
}
else
{
button[rowIndex+1][columnIndex-1].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
}
if((rowIndex-1)>=0)
{
checkIcon=button[rowIndex-1][columnIndex].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("white"))
{
}
else
{
button[rowIndex-1][columnIndex].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
if((columnIndex+1)<8)
{
checkIcon=button[rowIndex-1][columnIndex+1].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("white"))
{
}
else
{
button[rowIndex-1][columnIndex+1].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
if((columnIndex-1)>=0)
{
checkIcon=button[rowIndex-1][columnIndex-1].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("white"))
{
}
else
{
button[rowIndex-1][columnIndex-1].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
}
if((columnIndex+1)<8)
{
checkIcon=button[rowIndex][columnIndex+1].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("white"))
{
}
else
{
button[rowIndex][columnIndex+1].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
if((columnIndex-1)>=0)
{
checkIcon=button[rowIndex][columnIndex-1].getIcon();
if(checkIcon!=null && checkIcon.toString().startsWith("white"))
{
}
else
{
button[rowIndex][columnIndex-1].setBorder(BorderFactory.createLineBorder(Color.yellow,3));
}
}
}





















private void movePiece(JButton pieceToMove,JButton targetButton)
{
Border border=targetButton.getBorder();
if(border!=null)
{
String nameOfPieceToMove=pieceToMove.getIcon().toString();
if(nameOfPieceToMove.endsWith("Pawn.png"))
{
int index=-1;
for(int i=0;i<8;i++)
{
if(targetButton==button[0][i] || targetButton==button[7][i])
{
index=i;
break;
}
}
if(index!=-1)
{
Panel panel=new Panel();
panel.setLayout(new GridLayout(2,2));
panel.add(new JButton(new ImageIcon("blackQueen.png")));
panel.add(new JButton(new ImageIcon("blackKnight.png")));
panel.add(new JButton(new ImageIcon("blackBishop.png")));
panel.add(new JButton(new ImageIcon("blackRook.png")));
JOptionPane.showMessageDialog(null,panel);
}
}
if(turn.equals("white")) turn=new String("black");
else turn=new String("white");
ImageIcon img=(ImageIcon)pieceToMove.getIcon();
pieceToMove.setIcon(null);
Icon targetIcon=targetButton.getIcon();
targetButton.setIcon(img);
if(targetIcon!=null && targetIcon.toString().endsWith("King.png"))
{
String message="";
if(turn.equalsIgnoreCase("White")) message="Black wins.";
else message="White wins.";
JOptionPane.showMessageDialog(null,message);
System.exit(0);
}
}
else
{
System.out.println("Invalid selection");
}
for(int i=0;i<8;i++)
{
for(int j=0;j<8;j++)
{
button[i][j].setBorder(null);
}
}
}
public static void main(String gg[])
{
ChessBoard c=new ChessBoard();
}
}