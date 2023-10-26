package com.salt.fesalttest.core.response;

import java.util.List;

public class GenericListResponse<T> extends BasicResponse {
    public GenericListResponse(MetaData metaData) {
        super(metaData);
    }
    public GenericListResponse() {
        super();
    }
    private List<T> data;

    public List<T> getData() {
        return this.data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
