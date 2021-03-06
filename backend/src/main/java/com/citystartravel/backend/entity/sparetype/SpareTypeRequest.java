package com.citystartravel.backend.entity.sparetype;

public class SpareTypeRequest {

    private long id;

    private String name;

    public SpareTypeRequest() {}

    public SpareTypeRequest(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
