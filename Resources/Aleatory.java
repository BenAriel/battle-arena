package Resources;

import java.util.Random;

public class Aleatory<T> {
    Random rand = new Random();
    
    public Aleatory() {}
    
    public int randrange(int num) {
        return rand.nextInt(num);
    }
    
    public int randrange(int num1, int num2) {
        return rand.nextInt(num2 - num1) + num1;
    }
    
    public T[] shuffle(T[] lista) {
        for (int i = 0; i < lista.length; i++) {
            int indiceAleatorio = randrange(lista.length);
            T valorArmazenado = lista[indiceAleatorio];

            lista[indiceAleatorio] = lista[i];
            lista[i] = valorArmazenado;
        }
        
        return lista;
    }
    
}