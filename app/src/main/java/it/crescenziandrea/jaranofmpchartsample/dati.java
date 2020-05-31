package it.crescenziandrea.jaranofmpchartsample;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class dati implements Parcelable {

    @SerializedName("data")
    private String data;
    @SerializedName("stato")
    private String stato;
    @SerializedName("ricoverati_con_sintomi")
    private String ricoverati_con_sintomi;
    @SerializedName("terapia_intensiva")
    private  int terapia_intensiva;
    @SerializedName("totale ospedalizzati")
    private int totale_ospedalizzati;

    private dati(Parcel in) {
        data = in.readString();
        stato = in.readString();
        ricoverati_con_sintomi = in.readString();
        terapia_intensiva = in.readInt();
        totale_ospedalizzati = in.readInt();

    }

    public String getData() {
        return data;
    }

    public String getStato() {
        return stato;
    }

    public String getRicoverati_con_sintomi() {
        return ricoverati_con_sintomi;
    }

    public int getTerapia_intensiva() {
        return terapia_intensiva;
    }

    public int getTotale_ospedalizzati() {
        return totale_ospedalizzati;
    }
/*
    public dynamic getTotale_ospedalizzati()(string key)
    {
        if(dictionary.ContainsKey(key))
        {
            var temp = dictionary[key];

            switch (temp.Type)
            {
                case "bool":
                    return Convert.ToBoolean(temp.Value);

                case "int"
                    return Convert.ToInt(temp.Value);

                case "string"
                    return temp.Value;
            }
        }

        return "NULL";
    }

*/


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(data);
        dest.writeString(stato);
        dest.writeString(ricoverati_con_sintomi);
        dest.writeInt(terapia_intensiva);
        dest.writeInt(totale_ospedalizzati);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<dati> CREATOR = new Creator<dati>() {
        @Override
        public dati createFromParcel(Parcel in) {
            return new dati(in);
        }

        @Override
        public dati[] newArray(int size) {
            return new dati[size];
        }
    };
}
