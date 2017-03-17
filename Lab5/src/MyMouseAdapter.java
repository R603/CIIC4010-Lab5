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
					} else if ( gridX == 0 && gridY == 10) {
						//Released the mouse button on the same cell where it was pressed
						
							//random color center
							for (int i=4; i<7; i++){
								for (int j=4; j<7; j++){
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
                               myPanel.colorArray[i][j] = newColor;
                            }}
					}
							// random color rows
					else if (gridX == 0 && gridY > 0){
                            for (int i=1; i<10; i++){
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
                               myPanel.colorArray[i][myPanel.mouseDownGridY] = newColor;
                            }
					}	
                            // random color columns
					else if (gridX > 0 && gridY == 0){
                            for (int j=1; j<10; j++){
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
                               myPanel.colorArray[myPanel.mouseDownGridX][j] = newColor;
                             }
					}
							//diagonal top left to bottom right
					else if (gridX == 0 && gridY == 0){
                            for (int i=1; i<11; i++){
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
                            	myPanel.colorArray[0][0] = Color.LIGHT_GRAY;
                               myPanel.colorArray[myPanel.mouseDownGridX++][myPanel.mouseDownGridY++] = newColor;
                            }
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
			
		case 3:		//Right mouse button
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
			Color newColor2 = null;
			if ((myPanel2.mouseDownGridX == -1) || (myPanel2.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridX2 == -1) || (gridY2 == -1)) {
					//Is releasing outside
					for (int i=1; i<10; i++){
						for (int j=1; j<11; j++){
                    	do{ switch (generator.nextInt(3)) {
                        case 0:
                        	newColor2 = new Color(0xCD7F32); //Bronze
                            break;
                        case 1:
                            newColor2 = new Color(0xFFD700); //Gold
                            break;
                        case 2:
                            newColor2 = new Color(0x0F52BA); //Sapphire
                            break;
                      }
                        } while (myPanel2.colorArray[myPanel2.mouseDownGridX][myPanel2.mouseDownGridY].equals(newColor2));
                       myPanel2.colorArray[i][j] = newColor2;
                    }}
				} else {
					if ((myPanel2.mouseDownGridX != gridX2) || (myPanel2.mouseDownGridY != gridY2)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
						//Released the mouse button on the same cell where it was pressed
						if ( gridX2 == 0 || gridY2 == 0) {
						}
				}
			}
			myPanel2.repaint();
			break;
			}
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}