package org.saurav.simpletests.ds;
/**
 * A class for linked list implemenation in java.
 * Insert happens at the end
 * @author I054564
 *
 */
public class LinkedListTest {
	
	public static void main(String a[]){
		
		LinkedList lllst = new LinkedList();
		Link link = null;
		link = lllst.insertAtFirst(10);
		link = lllst.insert(link, 20);
		link = lllst.insert(link, 30);
		link = lllst.insert(link, 40);
		
		//display
		lllst.display();
		lllst.deleteAtFirst();
		lllst.display();
		//lllst.deleteAtFirst();
		lllst.reverseList();
		lllst.display();
		
		lllst.reverseList();
		lllst.display();
	}

}


class Link {
	int a;
	Link next;
	Link prev;
	
	Link(int a){
		this.a = a;
		//next = null;
	}
	
//	Link(Link link, int a){
//		this.a = a;
//		link.next = this;
//	}
}

class LinkedList {
	private Link first;
	private Link end;
	
	public LinkedList(){
		
	}
	
	public Link insert(Link link,int a){
		boolean result = false;
		Link link1 = new Link(a);
		first = link1;
		link1.next = link;
		link.prev = link1;
		//end = link;
		System.out.println("insert done");
		return link1;
	}
	
	public Link insertAtFirst(int a){
		
		Link link = new Link(a);
		first = link;
		end = link;
		link.next =null;
		link.prev =null;
		System.out.println("insert at first done");
		return link;
	}
	
	public boolean display(){
		boolean result = false;
		Link link  = first;
		System.out.println("Linked list display starts");
		while(link !=null){
			System.out.println(link.a);
			link = link.next;
		}
		return true;
	}
	
	public boolean reverseList(){
		Link link = end;
		//Link tempEnd = end;
		
		Link temp =null;
		while(end !=null){
			//System.out.println(link.a);
			temp = link.next;
			if(link.next == null){
				first = link;
			}
			end = link.prev;
			link.next =link.prev;
			link.prev = temp;
			link =end;
		}
		//first =link;
		end = temp.next;
		System.out.println("reversal of linked list done");
		return true;
	}
	
	public boolean deleteAtFirst(){
		boolean result = false;
		Link temp = first;
		
		//temp.prev =null;
		first = temp.next;
		first.prev =null;
		temp = null;
		System.out.println("delete done");
		return true;
	}
}

