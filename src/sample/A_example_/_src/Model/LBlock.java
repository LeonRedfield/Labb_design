/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.A_example_._src.Model;

/**
 *
 * @author Teddy
 */
public class LBlock extends Shapes{
    public LBlock()
    {
        pixelgrid = new int[][]{
            {0, 1, 0}, 
            {0, 1, 0},
            {0, 1, 1}
        };
        pixelgrid = remodelShape(pixelgrid);
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


}
