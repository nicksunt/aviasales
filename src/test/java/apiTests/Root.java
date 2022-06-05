package apiTests;

import java.util.ArrayList;
import java.util.List;
public class Root {
    private boolean success;

    private List<Data> data;

    private String currency;

    public Root(boolean success, List<Data> data, String currency) {
        this.success = success;
        this.data = data;
        this.currency = currency;
    }

    public void setSuccess(boolean success){
        this.success = success;
    }
    public boolean getSuccess(){
        return this.success;
    }
    public void setData(List<Data> data){
        this.data = data;
    }
    public List<Data> getData(){
        return this.data;
    }
    public void setCurrency(String currency){
        this.currency = currency;
    }
    public String getCurrency(){
        return this.currency;
    }
}

