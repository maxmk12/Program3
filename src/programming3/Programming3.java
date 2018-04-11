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
    
    public void FWA(int g[][]){
        V = g[0].length;
        int d[][] = new int[V][V];
        int i, j, k;
        for (i = 0; i < V; i++){
            for (j = 0; j < V; j++){
                d[i][j] = g[i][j];
            }  
        }
        for (k = 0; k < V; k++){
            for (i = 0; i < V; i++){
                for (j = 0; j < V; j++){
                    if (d[i][k] + d[k][j] < d[i][j]){
                        d[i][j] = d[i][k] + d[k][j];
                        printSolution(d);
                    }
                }
            }
        }
    }
    
        public void printSolution(int[][] d){
        System.out.println();
        System.out.print("-----");
        for(int yy = 0; yy < V-1; yy++){
            System.out.print("-------");
        }
        System.out.println();
        String[] Alphabet = new String[26];
        for(int uijg = 0; uijg < Alphabet.length; uijg++){
            Alphabet[uijg] = getChar(uijg + 1);
        }
        for(int uij = 0; uij < V; uij++){
            System.out.print("| " + Alphabet[uij] + " |  ");
            if(uij == V-1){
                System.out.println();
            }
        }
        for (int i=0; i<V; ++i)
        {
            for (int j=0; j<V; ++j)
            {
                if (d[i][j]==INF)
                    System.out.print("|INF|  ");
                else
                    if(d[i][j] < 10){
                        System.out.print("| " + d[i][j] + " |  ");
                    }
                    else if(10 <= d[i][j] && d[i][j] < 100){
                        System.out.print("|" + d[i][j] + " |  ");
                    }
                    else{
                        System.out.print("|" + d[i][j] + "|  ");
                    }
                    
            }
            System.out.println();
        }
        System.out.print("-----");
        for(int yy = 0; yy < V-1; yy++){
            System.out.print("-------");
        }
    }
    
    private String getChar(int i){
        if(i > 0 && i < 27){
            return String.valueOf((char)(i+64));
        }
        else{
            return null;
        }
    }
    
    public void print(int p[], int n){
        System.out.print("----");
        for(int h = 1; h < p.length -1; h++){
            System.out.print("------");
        }
        System.out.println();
        for (int i = 1; i < n; i++){
            String v = getChar(p[i] + 1);
            String t = getChar(i + 1);
            System.out.print("|" + v + "" + t + "|  ");
        }
        System.out.print("\n" + "----");
        for(int s = 1; s < p.length -1; s++){
            System.out.print("------");
        }
    }
    
    public void PrimJarnik(int[][] g){ 
        V = g[0].length;
        int root[] = new int[V];
        int key[] = new int [V];
        Boolean S[] = new Boolean[V];
        for (int i = 0; i < V; i++){
            key[i] = Integer.MAX_VALUE;
            S[i] = false;
        }
        key[0] = 0;
        root[0] = -1;
        for (int count = 0; count < V-1; count++){
            int u = m(key, S);
            S[u] = true;
            for (int v = 0; v < V; v++){
                if (g[u][v]!=0 && S[v] == false && g[u][v] <  key[v]){
                    root[v]  = u;
                    key[v] = g[u][v];
                }
            }
        }
        print(root, V);
    }
    
        
    public int m(int k[], Boolean ar[]){
        int m = Integer.MAX_VALUE, min_index=-1;
        for (int v = 0; v < V; v++){
            if (m > k[v] && ar[v] == false){
                m = k[v];
                min_index = v;
            }
        }
        return min_index;
    }
    
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
        
        Programming3 g = new Programming3();
        
        System.out.println("Through Kruskal's Algorithm: ");
        p.Kruskal();
        System.out.println("\n \n" + "Through Prim-Jarnik's Algorithm: ");
        g.PrimJarnik(adjM);
        System.out.println("\n");
        System.out.println("\n \n" + "Floyd-Warshall's Algorithm: ");
        g.FWA(adjM);
        System.out.println("\n");
        
    }
}
