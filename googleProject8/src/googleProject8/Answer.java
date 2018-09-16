package googleProject8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Answer {   
		
	public static void main(String args[]){
		
		int[] list = {1,7,3,21,13,19};
		System.out.println("Number of unmatched guards = " + answer(list));
	}
	
		public static int answer(int[] banana_list) { 
	        // Your code goes here.
			
			//determine infinite loop pairs, stored as an adjacency matrix
			@SuppressWarnings("unchecked") 
			List<Integer>[] infinitePairGraph = new List[banana_list.length];
			for (int i = 0; i < banana_list.length; i++){
				infinitePairGraph[i] = new ArrayList<Integer>();
			}
			
			
			for (int i = 0; i < banana_list.length; i++){
				for (int j=0;j < banana_list.length; j++ ){
					
					if (infiniteLoop(banana_list[i],banana_list[j])){
						infinitePairGraph[i].add(j);
						infinitePairGraph[j].add(i);
					}
				}
			}
			//graph matching algorithm returns unmatched guards
			
			return maxMatching(infinitePairGraph);
    } 
		
	//will it infinite loop method
		public static boolean infiniteLoop(int x,int y){
			int z;
			z = (x+y)/gcd(x,y);
			
			//function is true when not a power of 2
			return (z & (z-1)) != 0;
		}
		
	//GCD method
		public static int gcd(int x, int y){
			if (y == 0){
				return x;
			}
			else{
				return gcd(y,x%y);
			}
		}
		
		//blossom algorithm
		//least common ancestor method
		static int lca(int[] match, int[] base, int[] p, int a, int b){
			boolean[] used = new boolean[match.length];
			while (true){
				a = base[a];
				used[a] = true;
				if (match[a] == -1){
					break;
				}
				a = p[match[a]];
			}
			while (true){
				b = base[b];
				if (used[b]){
					return b;
				}
				b = p[match[b]];
			}
		}
		
		static void markPath(int[] match, int[] base, boolean[] blossom, int[] p, int v, int b, int children){
			for (;base[v] != b; v = p[match[v]]){
				blossom[base[v]] = blossom[base[match[v]]] = true;
				p[v] = children;
				children = match[v];
			}
		}
		
		static int findPath(List<Integer>[] graph,int[] match, int[] p, int root){
			int n = graph.length;
			boolean[] used = new boolean[n];
			Arrays.fill(p, -1);
			int[] base = new int[n];
			
			for (int i = 0; i < n; i++){
				base[i] = i;
			}
			
			used[root] = true;
			int qh = 0;
			int qt = 0;
			int[] q = new int[n];
			q[qt++] = root;
			
			while(qh < qt){
				int v= q[qh++];
				
				for (int to : graph[v]){
					if (base[v] == base[to] || match[v] == to){
						continue;
					}
					if (to == root || match[to] != -1 && p[match[to]] != -1){
						int currentBase = lca(match, base,p,v,to);
						boolean[] blossom = new boolean[n];
						markPath(match,base,blossom,p,v,currentBase,to);
						markPath(match,base,blossom,p,to,currentBase,v);
						
						for(int i = 0; i < n; i++){
							if (blossom[base[i]]){
								base[i] = currentBase;
								if (!used[i]){
									used[i] = true;
									q[qt++] = i;
								}
							}
						}
						
					}
					else if (p[to] == -1){
						p[to] = v;
						if (match[to] == -1){
							return to;
						}
						to = match[to];
						used[to] = true;
						q[qt++] = to;
					}
				}
			}
			return -1;
		}
		
		public static int maxMatching(List<Integer>[] graph){
			int n = graph.length;
			int[] match = new int[n];
			Arrays.fill(match, -1);
			int[] p = new int[n];
			
			for(int i = 0; i < n; i++){
				if (match[i] == -1){
					int v = findPath(graph,match,p,i);
					while (v != -1){
						int pv = p[v];
						int ppv = match[pv];
						match[v] = pv;
						match[pv] = v;
						v = ppv;
					}
				}
			}
			
			int unMatched = n;
			for (int i = 0; i < n; i++){
				if (match[i] != -1){
					unMatched--;
				}
			}
			return unMatched;
		}
}
		
	

