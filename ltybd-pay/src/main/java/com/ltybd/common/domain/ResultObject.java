package com.ltybd.common.domain;


public class ResultObject {
    private Object obj;
    private boolean state;

    public ResultObject(Object obj, boolean state) {
        this.obj = obj;
        this.state = state;
    }

    public ResultObject() {
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
