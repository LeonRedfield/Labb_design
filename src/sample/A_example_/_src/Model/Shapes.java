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
abstract public class Shapes {
    protected int positionX;
    protected int positionY;
    protected int pixelgrid[][];
    
    public int[][] getPixelGrid()
    {
        return pixelgrid;
    }
    
    public int getY() {
        return positionY;
    }

    public int getX() {
        return positionX;
    }

    public void setY(int Y) {
        positionY = Y;
    }

    public void setX(int X) {
        positionX = X;
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
    
    public void rotateLeft()
    {
        int temp[][] = new int[3][3];
        temp =rotateRightXThree();
        pixelgrid = remodelShape(temp);
    }
    
    private int[][] rotateRightXThree()
    {
        int temp[][] = new int[3][3];
        int temp2[][] = new int[3][3];
        for (int i = 0; i < pixelgrid.length; i++) 
        {
            for (int j = 0; j < pixelgrid[0].length; j++) 
            {
                temp[j][pixelgrid.length-1-i] = pixelgrid[i][j];
            }
        }
        
        for (int i = 0; i < temp.length; i++) 
        {
            for (int j = 0; j < temp[0].length; j++) 
            {
                temp2[j][temp.length-1-i] = temp[i][j];
            }
        }
        for (int i = 0; i < temp2.length; i++) 
        {
            for (int j = 0; j < temp2[0].length; j++) 
            {
                temp[j][temp2.length-1-i] = temp2[i][j];
            }
        }

        return temp;
    }
    
    public void rotateRight()
    {
        //in procces:
        int temp[][] = new int[3][3];
        
        for (int i = 0; i < pixelgrid.length; i++) 
        {
            for (int j = 0; j < pixelgrid[0].length; j++) 
            {
                temp[j][pixelgrid.length-1-i] = pixelgrid[i][j];
            }
        }   
        pixelgrid = remodelShape(temp);
    }
    /**
     *  recieves an 3x3 2d array, temp[][],and trims it to and 2x3 or 3x2 array, 
     *  dending of the shape of 1:s in the grid. It returns the trimmed array.
     * @param temp
     * @return remodel
     */
    protected int[][] remodelShape(int[][] temp)
    {
        //Remodel:
        int[][] remodel;
        int[] ones = new int[temp.length];
        int[] column = new int[temp[0].length];
        int beginatC = 0;
        int maxC = 0;
        int beginatR = 0;
        int maxR =0;
        int rows = 0, columns =0 ;
        //count painted rows and column:
        for(int i = 0; i < temp.length; i++) 
        { 
            for(int j=0; j<temp[0].length; j++)
            {
                if(temp[i][j] == 1)
                {
                    ones[i] +=1;                    
                }
                
                if(temp[j][i] == 1)
                {
                   column[i]+=1;
                } 
            }
        }
        
       //set settings for remodel and loop:
        if(column[0] >=1 && column[2] >=1)
        {
            beginatC = 0; 
            maxC = temp[0].length;
            columns = 3;
        }
        else if(column[0] >=1)
        {
            beginatC = 0;
            maxC = temp[0].length -1;
            columns = 2;
        }
        else
        {
            beginatC = 1; 
            maxC = temp[0].length;
            columns = 2;
        }
        
        if(ones[0] >= 1 && ones[2] >= 1)
        {
            rows = 3;
            beginatR = 0;
            maxR = temp.length;
        }
        else if(ones[0] >= 1)
        {
            rows = 2;
            beginatR = 0;
            maxR = temp.length-1;
            
        }
        else
        {
            rows = 2;
            beginatR = 1;
            maxR = temp.length;
        }
        //set rows and columns to remodel:
        remodel = new int[rows][columns];
        //begin to remodel;
        for(int i = beginatR; i < maxR; i++) 
        { 
            for(int j = beginatC; j<maxC; j++)
            {
                if(beginatR < 1 && beginatC < 1)
                {
                    remodel[i][j] = temp[i][j];
                }
                else if(beginatR < 1 && beginatC >= 1)
                {
                    remodel[i][j-1] = temp[i][j];
                }
                else if(beginatR >= 1 && beginatC < 1)
                {
                    remodel[i-1][j] = temp[i][j];
                }
                else
                {
                    remodel[i-1][j-1] = temp[i][j];
                } 
            }
        }
        return remodel;
    }
    
    public boolean hitBottom(int playfieldlenght)
    {
        
        int bottom = 0;
        for(int i=0; i<getPixelGrid().length; i++)
        {
            for(int j=0; j<getPixelGrid()[0].length; j++)
            {
                if(getPixelGrid()[i][j] == 1)
                {
                    bottom = i;
                }
            }
        }

        if(playfieldlenght - 1 <= bottom + getY())
        {
            //System.out.println("Hitted bottom at : " + bottom);
            return true;
        }
        else
        {
            return false;
        }      
    }
}
