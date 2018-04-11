/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
* Author: Max Carr, Erin Cook, and Zac Sliker
* Date: 2/27/2018 
* Overview: Runs the Assigned Outlab
*/

public class Programming3 {
    public final int INF = 99999;
    private int V, E;
    
    public static void main(String[] args){
        Path file = Paths.get("input.txt");
        Charset chars = Charset.forName("US-ASCII");
        int i = 0;
        {
        try (BufferedReader rder = Files.newBufferedReader(file, chars))
        {
           
            String l = null;
            while((l = rder.readLine()) != null){
                i++;
            }
        } catch (IOException x)
            {
                System.err.format("IOException: %s%n", x);
            }
        }
            
        String[][] words = new String[i][i];
        {
        try (BufferedReader rder = Files.newBufferedReader(file, chars))
        {
            String ln = null;
            for (int j = 0; (ln = rder.readLine()) != null; j++){
                words[j] = ln.split(",");
                String inEachLine = ln.replace(",", System.lineSeparator());
            }    
        } catch (IOException x)
            {
                System.err.format("IOException: %s%n", x);
            }
        }
        int[][] adjM = new int[words.length][words[0].length];
        
        for(int g = 0, t = 1; t < words[0].length + 1; g++, t++){
            for(int h = 0; h < words[0].length; h++){
                
                if("inf".equals(words[t][h])){
                    words[t][h] = "99999";
                }
                
                try{
                adjM[g][h] = Integer.parseInt(words[t][h]);
                }catch (NumberFormatException nfe){
                    System.out.println("You done messed up A-ARon");                 
                }
            }
        }
        
        int t, d = 0;
        t = adjM[0].length;
        for(int y = 0; y < adjM.length; y++){
            for(int b = 0; b < adjM[0].length; b++){
                if(adjM[y][b] != 0 && adjM[y][b] != 99999){
                    d++;
                }
            }
        }
        
        Graph p = new Graph(t, d);
        int tt =0;
        for(int u = 0; u < adjM[0].length ; u++){
            for(int w = 0; w < adjM[0].length; w++){
                if(adjM[u][w] != 0 && adjM[u][w] != 99999){
                    p.edge[tt].w = adjM[u][w];
                    p.edge[tt].s = u+1;
                    p.edge[tt].d = w+1;
                    tt++;
                }              
            }           
        }
        System.out.println("Through Kruskal's Algorithm: ");
        p.Kruskal();
        
    }
}
