/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programming3;


/* 
* Author: Max Carr, Erin Cook, and Zac Sliker
* Date: 2/27/2018 
* Overview: Runs the Assigned Outlab
*/

public class Graph {
    class Edge{
        public int s, d, w;
        public Edge[] bsrt(Edge ar[]){
            int n = ar.length;
            int k;
            for (int m = n; m >= 0; m--) {
                for (int i = 0; i < n - 1; i++) {
                    k = i + 1;
                    if (ar[i].w > ar[k].w) {
                        ar = swapNumbers(i, k, ar);
                    }
                }
            }
            return ar;
        }
        public Edge[] swapNumbers(int i, int j, Edge[] array) {
            Edge temp;
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            return array;
        }
    };
    private int V, E;
    public Edge[] edge;
    Edge e = new Edge();
    
    public Graph(int v, int e){
        V = v;
        E = e;
        edge = new Edge[E];
        for (int i = 0; i < e; i++){
            edge[i] = new Edge();
        }
    }
    
    private String gC(int i) {
        return i > 0 && i < 27 ? String.valueOf((char)(i + 64)) : null;
    }
    
    public void Kruskal(){
        e.bsrt(edge);
        Edge[] pq = new Edge[E];
        Edge[] f = new Edge[V-1];
        for(int y = 0; y < f.length; y++){
            f[y] = new Edge();
        }
        for(int i = 0; i < E; i++){
            pq[i] = edge[i];
        }
        boolean r = false;
        int g = 0;
        int[] roots = new int[V];
        for(int ui = 0; ui < roots.length; ui++){
            roots[ui] = ui + 1;
        }
        for(int k = 0; k < pq.length; k++){
            for(int j = 0; j < f.length; j++){
                if(roots[pq[k].s -1] == roots[pq[k].d - 1]){
                    r = false;
                    j = f.length;
                }
                else{
                    r = true;
                }
            }
            if(r){
                if(g < f.length){
                    f[g] = pq[k];
                    roots[pq[k].d -1] = roots[pq[k].s -1];
                }
                else{
                    break;
                }
                g++;           
            }
        }
        System.out.print("----");
        for(int h = 0; h < V-2; h++){
            System.out.print("------");
        }
        System.out.println();
        for(int p = 0; p < f.length; p++){
            String y = gC(f[p].s);
            String t = gC(f[p].d);
            System.out.print("|" + y + "" + t + "|  ");
        } 
        System.out.print("\n" + "----");
        for(int s = 0; s < V-2; s++){
            System.out.print("------");
        }
    }
}