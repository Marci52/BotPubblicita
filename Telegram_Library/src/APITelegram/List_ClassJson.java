/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package APITelegram;

import java.util.ArrayList;

/**
 *
 * @author marcello
 */
public class List_ClassJson {

    public ArrayList<ClassJson> lista;

    public List_ClassJson() {
	lista = new ArrayList<>();
    }

    public void aggiungi(ClassJson oggetto) {
	lista.add(oggetto);
    }

    public ClassJson getOggetto(int i) {
	return lista.get(i);
    }

    public boolean vuota() {
	return lista.isEmpty();
    }
    
    public int lunghezza(){
	return lista.size();
    }

    @Override
    public String toString() {
	return "List_ClassJson{" + "lista=" + lista.toString() + '}';
    }
    
    public void svuota(){
	lista.clear();
    }
}
