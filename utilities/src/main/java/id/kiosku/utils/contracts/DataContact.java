package id.kiosku.utils.contracts;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Dodi on 01/08/2018.
 */

public class DataContact implements Parcelable{
    @SerializedName("id")
    public String ID;
    public String name;
    public ArrayList<String> numbers;
    public ArrayList<String> emails;
    {
        numbers = new ArrayList<>();
        emails = new ArrayList<>();
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ID);
        dest.writeString(this.name);
        dest.writeStringList(this.numbers);
        dest.writeStringList(this.emails);
    }

    public DataContact() {
    }

    protected DataContact(Parcel in) {
        this.ID = in.readString();
        this.name = in.readString();
        this.numbers = in.createStringArrayList();
        this.emails = in.createStringArrayList();
    }

    public static final Creator<DataContact> CREATOR = new Creator<DataContact>() {
        @Override
        public DataContact createFromParcel(Parcel source) {
            return new DataContact(source);
        }

        @Override
        public DataContact[] newArray(int size) {
            return new DataContact[size];
        }
    };
}