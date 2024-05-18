package cn.techtutorial.model;

public class Cart {
    private int id;
    private int userId;
    private int productId;
    private int quantity;
    private String name;
    private String category;
    private Double price;
    public Cart() {
    }

    public Cart(int userId, int productId, int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }
    public Cart(int productid, String name, String category, Double price, int quantity) {
        this.productId = productid;
    	this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }
    
    // Getter and setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and setter for userId
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Getter and setter for productId
    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    // Getter and setter for quantity
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

//package cn.techtutorial.model;
//
//public class Cart extends Product{
//	private int quantity;
//	
//
//	
//
//	public Cart() {
//	}
//
//
//	public int getQuantity() {
//		return quantity;
//	}
//
//	public void setQuantity(int quantity) {
//		this.quantity = quantity;
//	}
//	
//}
