package entidades;

/**
 * Created by Felipe on 13/06/2016.
 */
public class Item {
    private String nome;
    private String serial;
    public Item() {}
    public Item(String nome, String serial) {
        this.nome = nome;
        this.serial = serial;
    }
    public String getNome() {
        return nome;
    }
    public String getSerial() {
        return serial;
    }
}

