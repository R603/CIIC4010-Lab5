import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {
	private Random generator = new Random();
	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
			if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame) c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			myPanel.repaint();
			break;
		case 3:		//Right mouse button
			//Do nothing
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame)c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			int gridX = myPanel.getGridX(x, y);
			int gridY = myPanel.getGridY(x, y);
			Color newColor = null;
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {}
				//Had pressed outside
				//Do nothing
			 else if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
				} else if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					}
						else {
							//On the grid other than on the left column and on the top row:
                           do{ switch (generator.nextInt(5)) {
                            case 0:
                            	newColor = Color.YELLOW;
                                break;
                            case 1:
                                newColor = Color.MAGENTA;
                                break;
                            case 2:
                                newColor = Color.BLACK;
                                break;
                            case 3:
                                newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
                                break;
                            case 4:
                                newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
                                break;
                          }
                            } while (myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY].equals(newColor));
                           myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
						}
			myPanel.repaint();
			break;
			
			//Right mouse button
		case 3:	
			
			//its possible that the left button and right button coexist in different grids...
			Component c2 = e.getComponent();
			while (!(c2 instanceof JFrame)) {
				c2 = c2.getParent();
				if (c2 == null) {
					return;
				}
			}
			JFrame myFrame2 = (JFrame)c2;
			MyPanel myPanel2 = (MyPanel) myFrame2.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsets2 = myFrame2.getInsets();
			int x2 = myInsets2.left;
			int y2 = myInsets2.top;
			e.translatePoint(-x2, -y2);
			int x_2 = e.getX();
			int y_2 = e.getY();
			myPanel2.x = x_2;
			myPanel2.y = y_2;
			int gridX2 = myPanel2.getGridX(x_2, y_2);
			int gridY2 = myPanel2.getGridY(x_2, y_2);
			//Color newColor2 = null;
			if ((myPanel2.mouseDownGridX == -1) || (myPanel2.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else if ((gridX2 == -1) || (gridY2 == -1)) {
					//Is releasing outside
				} else if ((myPanel2.mouseDownGridX != gridX2) || (myPanel2.mouseDownGridY != gridY2)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} 
					else {
						//Released the mouse button on the same cell where it was pressed
                       myPanel2.colorArray[gridX2][gridY2] = Color.RED;
						
						/* Why is it only painting RED in the main diagonal row when I right click the colored tile???
						 * is it because of left over code from Lab 5???? It can't be, because I erased most of it..
						 * it may be the ifs/elses...
						 * 
						 * Changed them to else ifs, didnt have any effect...
						 * 
						 * When putting gridX2/Y2, it paints red ONLY when the tile has been initially colored; this could
						 * indicate a priority with case 1 over case 3...
						 * 
						 * En parte ser� el mismo problema del Lab 5? El ejercicio #1?
						 * 
						 * Ok... cuando pongo el do-switch-while del case 1 aqui, me deja pintar de rojo un cuadro ya 
						 * pintado con el left-click y luego se friza el app... Nope, not the solution...
						 * 
						 * La cosa es que solamente se puede pintar justo cuando le hagas left-click...
						 * 
						 * Vamos a bregar con el row de abajo primero... (#1 left to MyPanel class)
						 * 
						*/
		}
			myPanel2.repaint();
			break;
			
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}