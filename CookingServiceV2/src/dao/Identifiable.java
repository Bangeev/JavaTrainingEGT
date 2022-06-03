package dao;

public interface Identifiable<K>{
    K getId();
    void setId(long id);

}
