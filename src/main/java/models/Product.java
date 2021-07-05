package models;

import org.json.simple.JSONObject;

public class Product {
    public String name;
    public Integer price;
    public String description;
    public Integer amount;
    public String productID;

    public Product(String name, Integer price, String description, Integer amount) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.amount = amount;
    }

    public void setProductId(String productID) {
        this.productID = productID;
    }

    public String getProductJsonRepresentationAsString() {
        JSONObject productJsonRepresentation = new JSONObject();
        productJsonRepresentation.put("nome", this.name);
        productJsonRepresentation.put("preco", this.price);
        productJsonRepresentation.put("descricao", this.description);
        productJsonRepresentation.put("quantidade", this.amount);
        return productJsonRepresentation.toJSONString();
    }
}
