package net.lhc.cakeshop.entitys;

public class Search {
    private String size1;
    private String size2;
    private String price1;
    private String price2;

    public String getSize1() {
        return size1;
    }

    public void setSize1(String size1) {
        this.size1 = size1;
    }

    public String getSize2() {
        return size2;
    }

    public void setSize2(String size2) {
        this.size2 = size2;
    }

    public String getPrice1() {
        return price1;
    }

    public void setPrice1(String price1) {
        this.price1 = price1;
    }

    public String getPrice2() {
        return price2;
    }

    public void setPrice2(String price2) {
        this.price2 = price2;
    }

    public Search(String size1, String size2, String price1, String price2) {
        this.size1 = size1;
        this.size2 = size2;
        this.price1 = price1;
        this.price2 = price2;
    }

    public Search() {
    }

    @Override
    public String toString() {
        return "Search{" +
                "size1='" + size1 + '\'' +
                ", size2='" + size2 + '\'' +
                ", price1='" + price1 + '\'' +
                ", price2='" + price2 + '\'' +
                '}';
    }
}
