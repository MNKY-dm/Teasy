package models;

public class Pricing extends Model {
    private int id;
    private int seance_id;
    private Float price1;
    private Float price2;
    private Float price3;

    public Pricing(int seance_id, Float price1, Float price2, Float price3) {
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

    public Float getPrice1() {
        return price1;
    }

    public Float getPrice2() {
        return price2;
    }

    public Float getPrice3() {
        return price3;
    }

    // setters

    public void setId(int id) {
        this.id = id;
    }

    public void setSeance_id(int seance_id) {
        this.seance_id = seance_id;
    }

    public void setPrice1(Float price1) {
        this.price1 = price1;
    }

    public void setPrice2(Float price2) {
        this.price2 = price2;
    }

    public void setPrice3(Float price3) {
        this.price3 = price3;
    }
}
