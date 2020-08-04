import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class View extends JFrame{
    
    private int [][] maze = 
    { {1,1,1,1,1,1,1,1,1,1,1,1,1},
      {1,0,1,0,1,0,1,0,0,0,0,0,1},
      {1,0,1,0,0,0,1,0,1,1,1,0,1},
      {1,0,0,0,1,1,1,0,0,0,0,0,1},
      {1,0,1,0,0,0,0,0,1,1,1,0,1},
      {1,0,1,0,1,1,1,0,0,0,0,0,1},
      {1,0,1,0,1,0,0,0,1,1,1,0,1},
      {1,0,1,0,1,1,1,0,1,0,1,0,1},
      {1,0,0,0,0,0,0,0,0,0,1,9,1}, //9 at position 11,8
      {1,1,1,1,1,1,1,1,1,1,1,1,1}
    
    };
    
    private final List<Integer> path = new ArrayList<Integer>();
    
    public View(){
        setSize(640,480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        DepthFirst.searchPath(maze,1,1,path);
        System.out.println(path);
    }
    @Override
    public void paint(Graphics g){
    	
        super.paint(g);
        g.translate(50, 50);
        for(int row =0; row< maze.length;row++){
            for(int col =0; col < maze[0].length;col++){
                Color color;
                switch(maze[row][col]){
                    case 1 : color = Color.BLACK ; break;
                    case 9 : color = Color.RED; break;
                    default : color = Color.WHITE;break;
                }
                g.setColor(color);
                g.fillRect(30*col,30* row,30,30);
                g.setColor(Color.BLACK);
                g.drawRect(30*col,30*row,30,30);
            }
        }
       for(int p=0;p<path.size();p+=2) {
    	   int pathX = path.get(p);
    	   int pathY = path.get(p+1);
    	   g.setColor(Color.GREEN);
    	   g.fillRect(pathX*30, pathY*30, 30, 30);
       }
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                View view = new View();
                view.setVisible(true);
            }
        });
    }
}