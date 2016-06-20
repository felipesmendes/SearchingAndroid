package entidades;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Item extends RealmObject {

    @PrimaryKey
    private int ID;

    @Required

    private String nome;

    @Required
    private String serial;

    public Item() {
    }

    public static String nomeFormatado (Item item) {
        return "ID: "+ item.getID() + " Item: " + item.getNome() + " Serial: " + item.getSerial();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Item(int ID, String nome, String serial) {
        this.ID = ID;
        this.nome = nome;
        this.serial = serial;
    }

    public String getNome() {
        return this.nome;
    }

    public String getSerial() {
        return this.serial;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}

