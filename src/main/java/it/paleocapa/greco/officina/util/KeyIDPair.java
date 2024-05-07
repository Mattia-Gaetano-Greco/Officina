package it.paleocapa.greco.officina.util;

public class KeyIDPair {
    String key;
    String id;

    public KeyIDPair() {}

    public KeyIDPair(String key, String id) {
        this.key = key;
        this.id = id;
    }

    public KeyIDPair(String key, int id) {
        this.key = key;
        this.id = String.valueOf(id);
    }

    public String getKey() {
        return key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
