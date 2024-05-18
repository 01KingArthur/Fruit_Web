//package cn.techtutorial.model;
//
//public class Product {
//	private int id;
//	private String name;
//	private String category;
//	private Double price;
//	private String image;
//	
//	
//	public Product() {
//	}
//	
//	
//	
//	public Product(int id, String name, String category, Double price, String image) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.category = category;
//		this.price = price;
//		this.image = image;
//	}
//
//	
//	public Product(String productName, String productCategory, Double price, String image) {
//        this.name = productName;
//        this.category = productCategory;
//        this.price = price;
//        this.image = image;
//    }
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getCategory() {
//		return category;
//	}
//
//	public void setCategory(String category) {
//		this.category = category;
//	}
//
//	public Double getPrice() {
//		return price;
//	}
//
//	public void setPrice(Double price) {
//		this.price = price;
//	}
//
//	public String getImage() {
//		return image;
//	}
//
//	public void setImage(String image) {
//		this.image = image;
//	}
//
//	@Override
//	public String toString() {
//		return "Product [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + ", image="
//				+ image + "]";
//	}
//	
//	
//}
package cn.techtutorial.model;

public class Product {
    private int id;
    private String name;
    private String category;
    private Double price;
    private String image;
    private String description;
    private String season;
    private String origin;

    public Product() {
    }

    public Product(int id, String name, String category, Double price, String image, String description, String season, String origin) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.image = image;
        this.description = description;
        this.season = season;
        this.origin = origin;
    }
    
    public Product(int id, String name, String category, Double price) {
    	this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public Product(String name, String category, Double price, String image, String description, String season, String origin) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.image = image;
        this.description = description;
        this.season = season;
        this.origin = origin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + ", image="
                + image + ", description=" + description + ", season=" + season + ", origin=" + origin + "]";
    }
}
