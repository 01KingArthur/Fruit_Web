package cn.techtutorial.model;

public class OrderDetail {
    private int orderdetailId;
    private int orderId;
    private int productId;
    private String productName;
    private String category;
    private int quantity;
    private Double price;

    public OrderDetail() {
    }
    public OrderDetail(int orderdetailId, int orderId, int productId, int quantity) {
        super();
        this.orderdetailId = orderdetailId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public OrderDetail(int orderdetailId, int orderId, int productId, String productName, String category, int quantity, double price) {
        super();
        this.orderdetailId = orderdetailId;
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public int getOrderDetailId() {
        return orderdetailId;
    }

    public void setOrderDetailId(int orderdetailId) {
        this.orderdetailId = orderdetailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
