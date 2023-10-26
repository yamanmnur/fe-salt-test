package com.salt.fesalttest.core.response;

public class GenericResponse<T> extends BasicResponse {
    public GenericResponse(MetaData metaData) {
        super(metaData);
    }
    public GenericResponse() {}
    private T data;

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
