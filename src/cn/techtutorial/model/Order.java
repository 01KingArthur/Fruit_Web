package cn.techtutorial.model;

public class Order extends Product {
    private int orderId;
    private int UserId;
    private String Address;
    private String status;
    private String o_date;
    private int o_quantity;
    private double total_price;

    public Order() {
    }

    public Order(int orderId, int UserId, String Address, String status, String o_date, int o_quantity, double total_price) {
        super();
        this.orderId = orderId;
        this.UserId = UserId;
        this.Address = Address;
        this.status = status;
        this.o_date = o_date;
        this.o_quantity = o_quantity;
        this.total_price = total_price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getO_date() {
        return o_date;
    }

    public void setO_date(String o_date) {
        this.o_date = o_date;
    }

    public int getO_quantity() {
        return o_quantity;
    }

    public void setO_quantity(int o_quantity) {
        this.o_quantity = o_quantity;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }
}
