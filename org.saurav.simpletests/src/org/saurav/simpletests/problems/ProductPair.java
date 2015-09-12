package org.saurav.simpletests.problems;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ProductPair {
	
	public static void main(String a[]) throws Exception{
		//Read input from stdin and provide input before running
		
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] values= line.trim().split("\\s+");
        int N = Integer.parseInt(values[0]);
        int productpairs = 0; //inialize it with 0.
        
        if(N>100000 || N<1){
        	System.out.println("input of number of N should not be greater than 10^5 or less than 1");
        	return;
        }
        
        int k = Integer.parseInt(values[1]);
        if(k<1){
        	System.out.println("input of number of N should not be greater than 10^10 or less than 1");
        	return;
        }
        
        
        Tree productPairTree = new Tree();
        
        
        for (int i = 0; i < N-1; i++) {
            
            //read the actual values now
            
            line = br.readLine();
            String[] lineValues= line.trim().split("\\s+");
            int parentValue = Integer.parseInt(lineValues[0]);
            if(parentValue < 1){
            	System.out.println("values not accepted if parent value is less than 1");
            	continue;
            }
            
            int descendantValue  =Integer.parseInt(lineValues[1]);
            if(descendantValue > N){
            	System.out.println("values not accepted if descendant value is less than N");
            	continue;
            }
            productPairTree.addNode(parentValue, descendantValue);
            
        }
        // print the product pairs now
        
		HashMap<Integer, Node> nodes = productPairTree.getNodes();
		Iterator it = nodes.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			Node node = (Node)pair.getValue();
			Integer nodeValue = node.getValue();
			ArrayList<Integer> children = node.getChildren();
			for (Iterator iterator = children.iterator(); iterator.hasNext();) {
				Integer descendantValue = (Integer) iterator.next();
				if(nodeValue * descendantValue <= k){
					productpairs++;
				}
			}
		}

        System.out.println("Product pairs found are:: " +productpairs);
	
	
	}
}

	class Tree {
	  HashMap<Integer,Node> nodes;
	  
	  public Tree(){
		  nodes = new HashMap<Integer, Node>();
	  }
	  
	  
	  public Node addNode(Integer value) {
	      return this.addNode(value, null);
	  }
	  
	  public Node addNode(Integer parentValue, Integer desValue) {
		  Node node = null;
		  if(!nodes.containsKey(parentValue)){
			  node = new Node(parentValue);
			  nodes.put(parentValue, node);
		  }
		  else{
			  node = nodes.get(parentValue);
		  }
		  
	      if (parentValue != null) {
	          nodes.get(parentValue).addChild(desValue);
	      }

	      return node;
	  }
	  
	  public HashMap<Integer,Node> getNodes(){
		  return nodes;
	  }
	}

	

	class Node {
		Integer value; //value of the node
		List<Integer> children; //children of node
		
		

		public Node(Integer value){
			this.value = value;
			children = new ArrayList<Integer>();
		}
		
		 public Integer getValue() {
		        return value;
		    }

		    public ArrayList<Integer> getChildren() {
		        return (ArrayList<Integer>) children;
		    }

		    // Public interface
		    public void addChild(Integer value) {
		        children.add(value);
		    }
		    
		    @Override
		    public boolean equals(Object arg0) {
		    	// TODO Auto-generated method stub
		    	return value.equals(((Node)arg0).value);
		    }
		    
		    @Override
		    public int hashCode() {
		    	// TODO Auto-generated method stub
		    	int hash = 3;
				hash = 7 * hash + value.hashCode();
				
				return hash;
		    }
	}

