package net.lhc.cakeshop.entitys;

public class Order {
    private int id;
    private String userName;//下单用户名
    private String time;//下单时间
    private String list;//订单列表
    private Float totalPrice;//总价
    private int tag;//订单状态

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getList() {
        return list;
    }

    public void setList(String list) {
        this.list = list;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public Order(int id, String userName, String time, String list, Float totalPrice, int tag) {
        this.id = id;
        this.userName = userName;
        this.time = time;
        this.list = list;
        this.totalPrice = totalPrice;
        this.tag = tag;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", time='" + time + '\'' +
                ", list='" + list + '\'' +
                ", totalPrice=" + totalPrice +
                ", tag=" + tag +
                '}';
    }
}
