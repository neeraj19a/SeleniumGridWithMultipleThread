package main.java.com.seleniumgridmultiplethread.project;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Pan {

    @SerializedName("PrimaryAccountNumber")
    @Expose
    private String primaryAccountNumber;
    @SerializedName("CardholderName")
    @Expose
    private String cardholderName;
    @SerializedName("AddressStreet")
    @Expose
    private String addressStreet;
    @SerializedName("AddressStreet2")
    @Expose
    private String addressStreet2;
    @SerializedName("AddressStreet3")
    @Expose
    private String addressStreet3;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("State")
    @Expose
    private String state;
    @SerializedName("AddressPostalCode")
    @Expose
    private String addressPostalCode;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("Locale")
    @Expose
    private String locale;
    @SerializedName("Cvv2Value")
    @Expose
    private String cvv2Value;
    @SerializedName("ExpirationDate")
    @Expose
    private String expirationDate;
    @SerializedName("isVIPReject")
    @Expose
    private String isVIPReject;
    @SerializedName("AddressVerified")
    @Expose
    private String addressVerified;
    @SerializedName("VTS_Enabled")
    @Expose
    private String vTSEnabled;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("OFAC_Status")
    @Expose
    private String oFACStatus;
    @SerializedName("DataRowID")
    @Expose
    private String dataRowID;
    @SerializedName("BIN")
    @Expose
    private String bIN;
    @SerializedName("Category")
    @Expose
    private String category;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("FullName")
    @Expose
    private String fullName;
}
