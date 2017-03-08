/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.A_example_._src.Model;
import Model.Shapes;

/**
 *
 * @author Teddy
 */
public class IBlock extends Shapes{
    private boolean isrotated;
    private int[][] IblockRotated;
    private int[][] IblockNormalState;
    
    public IBlock()
    {
        pixelgrid = new int[][]{
            {1}, 
            {1},
            {1}, 
            {1} 
        };
        IblockNormalState = pixelgrid;
        IblockRotated = new int[][]{
            {1, 1, 1, 1}
        };          
    }

    @Override
    public void rotateLeft() {
        if(!isrotated)
        {
            pixelgrid = IblockRotated;
            isrotated = true;
        }
        else
        {
            pixelgrid = IblockNormalState;
            isrotated = false;
        }
    }
    
    public void printBlock()
    {
        for(int i=0; i<pixelgrid.length;i++)
        {
            for(int j=0; j<pixelgrid.length;j++)
            {
                System.out.print(pixelgrid[i][j]);
            }
            System.out.print("\n");
        }
    }

    @Override
    public void rotateRight() {
        if(!isrotated)
        {
            pixelgrid = IblockRotated;
            isrotated = true;
        }
        else
        {
            pixelgrid = IblockNormalState;
            isrotated = false;
        }
    }

}
