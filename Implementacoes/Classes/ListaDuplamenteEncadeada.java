package Implementacoes.Classes;

import Implementacoes.Interfaces.InterfaceDoubleLinkedList;
import Implementacoes.Excecao.Excecao;

public class ListaDuplamenteEncadeada<T> implements  InterfaceDoubleLinkedList<T> {

    public class Node
    {
        private T dado;
        private Node next;
        private Node prev;


        public Node(T dado)
        {
            this.dado=dado;
            this.next=null;
            this.prev=null;
        }


    }
    private Node head;
    private Node tail;
    private int size;
    public ListaDuplamenteEncadeada()
    {
        this.head=null;
        this.tail=null;
        this.size= 0;
    }

    @Override
    public void addFirst(T value) {
        Node novo = new Node(value);
        if(head==null)
        {
            head=novo;
            tail=novo;
        }
        else{
            novo.next = head;
            head.prev=novo;
            head=novo;
        }
        size++;
    }

    @Override
    public void addLast(T value) {
        Node novo = new Node(value);
       if(tail==null)
       {
        head=novo;
        tail=novo;
       }
       else
       {
        novo.prev= tail;
        tail.next = novo;
        tail=novo;
       }
       size++;
    }

    @Override
    public boolean addAfter(T dado, T crit) {
       Node p = searchNode(crit);
       if(p==null)
       return false;
        Node novo = new Node(dado);
       if(p==tail)
       {
        addLast(novo.dado);
       }
       else{
        novo.next=p.next;
        novo.prev=p;
        p.next.prev = novo;
        p.next = novo;
        size++;
       }
       return true;
    }

    @Override
    public T peekFirst() throws Excecao{
        if(head==null)
        throw new Excecao("Lista vazia");
        
        T retorno = head.dado;
        return retorno;
    }

    @Override
    public T peekLast() throws Excecao{
        if(tail==null)
        throw new Excecao("Lista vazia");
        
        T retorno = tail.dado;
        return retorno;
    }

    @Override
    public T removeFirst()throws Excecao {
      if(head==null)
      throw new Excecao("Lista vazia");
      T retorno = head.dado;
      if(head==tail)
      {
      head=null;
      tail=null;
      }
     else
     {
      head=head.next;
      head.prev.next=null;
      head.prev=null;
     }
     size--;
     return retorno;
    }

    @Override
    public T removeLast()  throws Excecao{
        if(tail==null)
      throw new Excecao("Lista vazia");
      T retorno = tail.dado;
      if(head==tail)
      {
      head=null;
      tail=null;
      }
     else
     {
      tail=tail.prev;
      tail.next.prev=null;
      tail.next =null;
     }
     size--;
     return retorno;
    }

    @Override
    public T remove(T crit) throws Excecao {
        if(head==null)
        throw new Excecao("Lista vazia");

       Node p = searchNode(crit);
       if(p==head)
       return removeFirst();
       else if(p==tail)
       return removeLast();
       else
       {
        Node anterior = p.prev;
        Node posterior = p.next;
        anterior.next = posterior;
        posterior.prev = anterior;
        p.next=null;
        p.prev=null;
        size--;
        return p.dado;
       }

    }

    @Override
    public void show() throws Excecao{
        if(head==null)
        throw new Excecao("Lista vazia");
       Node p = head;
       while(p!= null)
       {
        System.out.println(p.dado + "   ");
        p=p.next;
       }
    }

    @Override
    public void showReverse() throws Excecao {
        if(tail==null)
        throw new Excecao("Lista vazia");
       Node p = tail;
       while(p!= null)
       {
        System.out.println(p.dado + "   ");
        p=p.prev;
       }
    }
    private Node searchNode(T criterio)
	{
	    Node p = head;	

	    while( p != null )
	    {
	    	
	        if( p.dado.equals(criterio) ) {
	        	System.out.println();
	            return p;
	        }
	        p = p.next;
	    }
	    
	    return null;
	}

    public T search(T critério) 
    {
        Node p = searchNode(critério);
        if(p==null)
        return null;
        else
        return p.dado;
    }
    
    public boolean isEmpty()
     {
        return size==0;
     }

    public int getSize()
    {
        return this.size;
    }
}
