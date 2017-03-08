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
public class JBlock extends Shapes{
    public JBlock()
    {
        
        pixelgrid = new int[][]{
            {0, 1, 0}, 
            {0, 1, 0},
            {1, 1, 0}
        };
        pixelgrid = remodelShape(pixelgrid);
    }


}
