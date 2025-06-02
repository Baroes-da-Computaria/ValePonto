package com.map.Vale.Ponto.model.product;

public interface ProductSalesView {
    Long getId();

    String getName();

    String getCategory();

    String getDescription();

    Double getPrice();

    String getImageURL();

    String getSubtitle();

    Long getPoints();

    Long getTotalSold();
}
