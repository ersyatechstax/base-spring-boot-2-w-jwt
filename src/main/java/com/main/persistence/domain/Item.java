package com.main.persistence.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "ITEMS")
public class Item  extends Base{

    private String name;

    private Integer stock;

    private String description;
}
