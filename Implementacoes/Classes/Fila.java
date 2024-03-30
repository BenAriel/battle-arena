package Implementacoes.Classes;
import Implementacoes.Interfaces.InterfaceDoubleLinkedList;
import Implementacoes.Interfaces.InterfaceFila;
import Implementacoes.Excecao.Excecao;

public class Fila<T> implements InterfaceFila<T> {

    private InterfaceDoubleLinkedList<T> fila;

    public Fila()
    {
        this.fila = new ListaDuplamenteEncadeada<>();
    }

    @Override
    public void add(T element) throws Excecao {
       fila.addLast(element);
    }

    @Override
    public T remove() throws Excecao {
       return fila.removeFirst();
    }

    @Override
    public T peek() throws Excecao {
      return fila.peekFirst();
    }

    @Override
    public boolean isEmpty() throws Excecao {
       return fila.isEmpty();
    }

    @Override
    public void show() throws Excecao {
       fila.show();
    }

    public int getSize()
    {
        return fila.getSize();
    }
}
