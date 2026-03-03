package models;

public class Pricing extends Model {
    private int id;
    private int seance_id;
    private float price1;
    private float price2;
    private float price3;

    public Pricing(int seance_id, float price1, float price2, float price3) {
        this.seance_id = seance_id;
        this.price1 = price1;
        this.price2 = price2;
        this.price3 = price3;
    }

    // getters

    public int getId() {
        return id;
    }

    public int getSeance_id() {
        return seance_id;
    }

    public float getPrice1() {
        return price1;
    }

    public float getPrice2() {
        return price2;
    }

    public float getPrice3() {
        return price3;
    }

    // setters

    public void setId(int id) {
        this.id = id;
    }

    public void setSeance_id(int seance_id) {
        this.seance_id = seance_id;
    }

    public void setPrice1(float price1) {
        this.price1 = price1;
    }

    public void setPrice2(float price2) {
        this.price2 = price2;
    }

    public void setPrice3(float price3) {
        this.price3 = price3;
    }
}
