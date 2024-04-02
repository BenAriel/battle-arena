package Implementacoes.Classes;

import Implementacoes.Interfaces.InterfaceDoubleLinkedList;
import Implementacoes.Interfaces.InterfacePilha;
import Implementacoes.Excecao.Excecao;

public class Pilha<T> implements InterfacePilha<T> {

    private InterfaceDoubleLinkedList <T> pilha;

    public Pilha()
    {
        this.pilha = new ListaDuplamenteEncadeada<>();
    }

    @Override
    public void push(T element) throws Excecao {
      pilha.addLast(element);
    }

    @Override
    public T pop() throws Excecao {
      return pilha.removeLast();
    }

    @Override
    public T peek() throws Excecao {
       return pilha.peekLast();
    }

    @Override
    public boolean isEmpty() {
      return pilha.isEmpty();
    }

    @Override
    public void show() throws Excecao {
        pilha.showReverse();
    }

    public int getSize()
    {
        return pilha.getSize();
    }
}
