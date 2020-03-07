package main.java.com.seleniumgridmultiplethread.project;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class CardData {

    @SerializedName("pan")
    @Expose
    private Pan pan;

}
