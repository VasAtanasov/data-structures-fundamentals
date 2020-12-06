package implementations.list;

public class CircularArrayList<s> {

	private s[] items;
	private int size;
	private int firstBack; //How far back from the end of the array the first item starts at (if negative it starts after index zero)

	public CircularArrayList() {
		items = (s[]) new Object[8];
		size = 0;
		firstBack = 0;
	}

	public void resizeUp() {
		s[] itemsC = (s[]) new Object[items.length*2];
		System.arraycopy(items,0,itemsC,0,size-firstBack); //Adds all items starting from zero till it reaches the end
		System.arraycopy(items,items.length-firstBack,itemsC,itemsC.length-firstBack,firstBack); //Adds all items Starting from the head of the list that are indexed before 0
		items = itemsC;
	}
	
	public void resizeDown() {
		s[] itemsC = (s[]) new Object[items.length/2];
		System.arraycopy(items,0,itemsC,0,size-firstBack); //Adds all items starting from zero till it reaches the end
		System.arraycopy(items,items.length-firstBack,itemsC,itemsC.length-firstBack,firstBack); //Adds all items Starting from the head of the list that are indexed before 0
		items = itemsC;
	}

	private void checkMem() { //Checks in less than 25% of the Arraysize is used
		if (size < 0.25*items.length && items.length > 16) {
			this.resizeDown();
			this.checkMem();
			return;
		}
		return;
	}	

	public void addLast(s x) {
		if (size == items.length) {
			this.resizeUp();
		}
		if (firstBack>0) {
			items[size-firstBack] = x;
		}
		else {
			items[size+Math.abs(firstBack)] = x;
		}
		size++;
		this.checkMem();
	}

	public void addFirst(s x) {
		if (items.length == size) {
			this.resizeUp();
		}
		if (firstBack>0) {
			items[items.length-firstBack-1]=x;			
		}
		else {
			items[Math.abs(firstBack)]=x;
		}
		size++;
		firstBack++;
		this.checkMem();
	}

	public s get(int i) {
		if (firstBack==0)
			return items[i];
		if (i>firstBack)
			return items[i-firstBack];
		else
			return items[items.length-firstBack+i];

	}

	public int size() {
		return size;
	}

	public s removeLast() {
		s returnItem;
		if (firstBack>0) { //List Starts from the back of the List
			returnItem = items[size-1-firstBack];
			items[size-1-firstBack] = null ;
		}
		else { // Starts at the begining
			returnItem = items[size-1+Math.abs(firstBack)];
			items[size-1+Math.abs(firstBack)] = null;
		}
		size--;
		return returnItem;
	}

	public s removeFirst() {
		s returnItem;
		if (firstBack>0) { //Starts at back somewhere
			returnItem = items[items.length-firstBack];
			items[items.length-firstBack] = null;
		}
		else { //Starts from begining
			returnItem = items[Math.abs(firstBack)];
			items[Math.abs(firstBack)] = null;
		}
		size--;
		firstBack--;
		return returnItem;
	}

	public void printDeque() {
		for(int i =0;i<size;i++) {
			System.out.print(", "+this.removeFirst());
		}
	}

	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}

	public static void main(String[] args) {
		CircularArrayList a = new CircularArrayList();
		for (int i = 0; i < 256; i++) {
			a.addLast("s");
			a.addLast("d");
		}
		System.out.println(a.size());
		System.out.println(a.items.length);
		for (int i = 0; i< 200; i++) {
			System.out.print(a.removeFirst());
		}
	}
}