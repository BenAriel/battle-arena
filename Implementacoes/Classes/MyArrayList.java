package Implementacoes.Classes;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import Implementacoes.Interfaces.MyInterface;

public class MyArrayList<E> implements MyInterface<E>
{

   
	class Node
	{
		
        private E data;    
        private Node next; 
        private Node prev;	
                
       
        public Node(E data) 
        {        	
            this.data = data;    
            this.next = null; 
            this.prev = null; 
        }
        @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		Node other = (Node) obj;
		return data == other.data;
	}
    }
	
	
    private Node head;    
    private Node tail;
    private int size;
    
	public MyArrayList() {
		head = null;
		tail = null;
		size = 0;
	}


    public void show()
	{
	    Node p = head;
	    
		if(p == null) {
			System.out.println("LISTA VAZIA \n");
		}
		else 
	    {
	        while( p != null )
	        {
	            System.out.println("\n Dado: " + p.data + "\n");
	            
	    	    p = p.next;
	        }
	    }
		
		System.out.println("size = " + size + "\n");
	}

	public void showReverse()
	{
	    Node p = tail; 
	    
		if(p == null) {
			System.out.println("LISTA VAZIA \n");
		}
		else 
	    {
	        while( p != null )
	        {
	            System.out.println("\n Dado: " + p.data );
	            
	    	    p = p.prev; 
	        }
	    }
		
		System.out.println("size = " + size + "\n");
	}
	
	

	
	
	
	public void addFirst(E dado)
	{
		Node novo = new Node(dado);
	    
		if(head == null) {
			head = novo;	
			tail = novo;	
		} 
		else {
			// Anexa
			novo.next = head;
			head.prev = novo;
			head = novo;
		}
		
		size++;
	}
	
	
	
	

	public void addLast(E dado)
	{		 
	    Node novo = new Node(dado);    


	    if( head == null ) { 
	        head = novo;       	
	        tail = novo;
	    }
	    else
	    {
			// Anexa
	    	novo.prev = tail; 
	    	tail.next = novo;
	        tail = novo;
	    }
	    
	    size++;
	}

	
	
	
	
	public boolean addAfter(E dado, E criterio)
	{
	    Node p = searchNode(criterio);

	    if( p == null )	
	    {
	        System.out.println("Criterio invalido \n");
	        return false;
	    }
	    else
	    {
	        Node novo = new Node(dado);
	        if(p.next == null) {
	        	tail = novo;
	        }
	        
	    	
	        novo.next = p.next;
	        novo.prev = p;		
	        p.next = novo;
	        
	        // novidade
	        Node frente = novo.next;	
	        if(frente != null) {		
	        	frente.prev = novo;
	        }
		    
		    size++;
		    
		    return true;
	    }
	}
	
	
	public E peekFirst()
	{	
		if( head == null ) {
	        System.out.println("Lista Vazia!!! \n");
	        return null;
	    }
		else {
			return head.data;
		}
	}
	
	
	public E peekLast()
	{
        if (tail == null) {
        	System.out.println("Lista Vazia!!! \n");
            return null;
        }
        else {
            return tail.data;
        }
	}
	

	private Node searchNode(E criterio)
	{
	    Node p = head;		

	    while( p != null )
	    {
	    	
	        if( p.data.equals(criterio) ) {
	        	System.out.println();
	            return p;
	        }
	        p = p.next;
	    }
	    
	    return null;
	}

	
	public E search(E criterio)
	{
		Node p = searchNode(criterio);
		
		if(p == null) {
			return null;
		} else {
			return p.data;
		}
	}

	
	
	public E removeFirst()
	{	
		Node p = head;
		E dadoRetorno = null;

		if( head == null ) {
	        System.out.println("Lista Vazia!!! \n");
	    }
		else
		{
			dadoRetorno = p.data;
			
			if (head == tail) 
			{
				System.out.println("Remove unico elemento\n");
                head = null;
                tail = null;
            } 
			else {
			
                head = head.next;
                head.prev = null;
			}
			
			p.next = null; // isola elemento removido
			
			size--;
		}

		return dadoRetorno;
	}
	
	
	public E removeLast() 
	{
		E dadoRetorno = null;

        if (tail == null) {
        	System.out.println("Lista Vazia!!! \n");
            return null;
        }
        else 
        {
            dadoRetorno = tail.data;
            
            if (head == tail) 
            {
            	System.out.println("Remove unico elemento\n");
            	head = null;
            	tail = null;
            } 
            else 
            {
            	
        		Node anterior = tail.prev;  
        		tail.prev = null;			
                tail = anterior;			
                tail.next = null;
            }
            
            size--;
        }

        return dadoRetorno;
	}
	
	
	
public E removeByCriteria(E criterio)
	{


		if( head == null ) {
	        
	        return null;
	    }

		Node removido = searchNode(criterio); 
		
		if(removido == null) {
			System.out.println("lista vazia");
			return null;
		}
		else
		{
			if(removido == head)
			{
				return removeFirst();
			}
			else if(removido==tail)
			{
				return removeLast();
			}
			else
			
			{
				Node anterior = removido.prev;
				Node posterior = removido.next;
				anterior.next= posterior;
				posterior.prev = anterior;
				removido.next=null;
				removido.prev=null;
                size--;
				return removido.data;
			}
		}
		
		
		

	}

	public int getSize() {
		return size;
	}

    @Override
    public int size() {
        return getSize();
    }

    @Override
    public boolean isEmpty() {
        if(size == 0) {
        	return true;
        } else {
        	return false;
        }
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
		E dataTemp = (E) o;
        Node p = head;		

	    while( p != null )
	    {
	    	
	        if( p.data.equals(dataTemp) ) {
	        	System.out.println();
	            return true;
	        }
	        p = p.next;
	    }
	    
	    return false;
    }

    @SuppressWarnings("rawtypes")
	@Override
    public Iterator iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    @Override
    public Object[] toArray() {
       Object[] array = new Object[size];
		Node p = head;
	   for(int i = 0; i < size; i++) {
		   array[i] = p.data;
		   p = p.next;
	   }
	   return array;
    }

    @Override
    public Object[] toArray(Object[] a) {
		Node p = head;
       for(int i = 0; i < size; i++) {
		a[i] = p.data;
		p = p.next;
	   }
	   return a;
    }

    @Override
    public boolean add(Object e) {
       	@SuppressWarnings("unchecked")
		E dado = (E) e; 
            Node novo = new Node(dado);    
    
            // verifica se lista está vazia
            if( head == null ) { 
                head = novo;       	
                tail = novo;
        		
            }
            else
            {
                
                novo.prev = tail; 
                tail.next = novo;
                tail = novo;
            }
            
            size++;
            return true;
        }

    public boolean remove(Object o) {
        
		if( isEmpty() ) {
	        System.out.println("Lista Vazia!!! \n");
	        return false;
	    }

        @SuppressWarnings("unchecked")
		E criterio = (E) o;
		Node removido = searchNode(criterio); // null: criterio nao existe OU criterio esta no 1o elemento
		
		if(removido == null) {
			return false;
		}
		else
		{
			if(removido == head)
			{
				 removeFirst();
			}
			else if(removido==tail)
			{
				removeLast();
			}
			else
			
			{
				Node anterior = removido.prev;
				Node posterior = removido.next;
				anterior.next= posterior;
				posterior.prev = anterior;
				removido.next=null;
				removido.prev=null;
                size--;
			}
			return true;
		}
    }

    @Override
    public boolean containsAll(@SuppressWarnings("rawtypes") Collection c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(@SuppressWarnings("rawtypes") Collection c) {
		boolean modified = false;
		for (Object e : c) {
			while (contains(e)) {
				add(e);
				modified = true;
			}
		}
		return modified;
	}

    @Override
    public boolean addAll(int index, @SuppressWarnings("rawtypes") Collection c) {
		boolean modified = false;
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		else if(index==0)
		{
		for (Object e : c) {
			@SuppressWarnings("unchecked")
			E dado = (E) e; 
            addFirst(dado);
			modified = true;


		}
		}
		else if(index==size)
		{
			for (Object e : c) {
				@SuppressWarnings("unchecked")
				E dado = (E) e; 
				addLast(dado);
				modified = true;
				
			}
		}
		else {
			Node p = head;
			for(int i = 0; i < index; i++) {
				p = p.next;
			}
			for (Object e : c) {
				@SuppressWarnings("unchecked")
				E dado = (E) e; 
				addAfter(dado,p.data);
				modified = true;
			 p = p.next;
			}
		}
		return modified;
    }

    @Override
    public boolean removeAll(@SuppressWarnings("rawtypes") Collection c) {
      
            boolean modified = false;
            for (Object e : c) {
                while (contains(e)) {
                    remove(e);
                    modified = true;
                }
            }
            return modified;
        }

    @Override
    public boolean retainAll(@SuppressWarnings("rawtypes") Collection c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retainAll'");
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }

    @Override
    public Object get(int index) {
        if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		else if(index==0)
		{
			return head.data;
		}
		else if(index==size-1)
		{
			return tail.data;
		}
		else {
			Node p = head;
			for(int i = 0; i < index; i++) {
				p = p.next;
			}
			return p.data;
		}
    }

    @Override
    public Object set(int index, Object element) {
		@SuppressWarnings("unchecked")
		Node novo = new Node((E) element);
       if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of bounds");
		}
		else if(index==0)
		{
			Node p = head;
			novo.next = p.next;
			p.next.prev = novo;
			novo.prev = null;
			p.next = null;
			p.prev=null;
			head = novo;
			return novo.data;

		}
		else if(index==size-1)
		{
			Node p = tail;
			novo.next = null;
			novo.prev = p.prev;
			p.prev.next = novo;
			p.next = null;
			p.prev=null;
			tail = novo;
			return novo.data;
		}
		else {
			Node p = head;
			for(int i = 0; i < index; i++) {
				p = p.next;
			}
			novo.next = p.next;
			novo.prev = p.prev;
			p.prev.next = novo;
			p.next= null;
			p.prev=null;
			return novo.data;

		}
    }

    @SuppressWarnings("unchecked")
	@Override
public void add(int index, Object element) {
    if (index < 0 || index > size) {
        throw new IndexOutOfBoundsException("Index out of bounds");
    }
    if (index == 0) {
        addFirst((E) element);
    } else if (index == size) {
        addLast((E) element);
    } else {
        Node novo = new Node((E) element);
        Node p = head;
        for (int i = 0; i < index - 1; i++) {
            p = p.next;
        }
        novo.prev = p;
        novo.next = p.next;
        p.next.prev = novo;
        p.next = novo;
        size++;
    }
}


    @Override
    public Object remove(int index) {
       if(index < 0 || index >= size) {
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
	   }
	   if (index == 0) {
		E retorno = removeFirst();
		return retorno;
	   }
	   else if(index==size-1)
	   {
		E retorno = removeLast();
		return retorno;
	   }
	   else {
		Node p = head;
			for(int i = 0; i < index; i++) {
				p = p.next;
			}
			 remove(p.data);
             E retorno = p.data;
                return retorno;
			
	   }
    }

    @Override
    public int indexOf(Object o) {
        Node p = head;
			for(int i = 0; i < size; i++) {
				if(p.data.equals(o)) {
					return i;
				}
				p = p.next;
			}
			return -1;
    }

    @SuppressWarnings("unused")
	@Override
    public int lastIndexOf(Object o) {
		Node p = head;
		int retorno = -1;
		for(int i = 0; i < size; i++) {
			if(p.data.equals(o)) {
				retorno= i;
			}
			p = p.next;
			return retorno;
		}
		return -1;
    }

    @SuppressWarnings("rawtypes")
	@Override
    public ListIterator listIterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
    }
		@SuppressWarnings("rawtypes")
		@Override
	    public ListIterator listIterator(int index) {
	        // TODO Auto-generated method stub
	        throw new UnsupportedOperationException("Unimplemented method 'listIterator'");
	    }
                @SuppressWarnings("rawtypes")
				@Override
        		public List subList(int fromIndex, int toIndex) {
        			// TODO Auto-generated method stub
        			throw new UnsupportedOperationException("Unimplemented method 'subList'");
        		}

                public static void main(String[] args) {
                    @SuppressWarnings("unchecked")
					List<String> lista = new MyArrayList<String>();
                    lista.add("QUERO");
                    lista.add("testar");
                    lista.add("STRING");
                    lista.remove("testar");
                    String x = lista.get(1);
                    System.out.println(x);
                    int y = lista.size();
                    System.out.println(y);
                }
    
}
