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
    private int ricoverati_con_sintomi;
    @SerializedName("terapia_intensiva")
    private  int terapia_intensiva;
    @SerializedName("totale_ospedalizzati")
    private int totale_ospedalizzati;
    @SerializedName("isolamento_domiciliare")
    private int isolamneto_domiciliare;
    @SerializedName("totale_positivi")
    private int totale_positivi;
    @SerializedName("variazione_totali_positivi")
    private int variazione_totali_positivi;
    @SerializedName("nuovi_positivi")
    private int nuovi_positivi;
    @SerializedName("dimessi_guariti")
    private int dimessi_guariti;
    @SerializedName("deceduti")
    private int deceduti;
    @SerializedName("totale_casi")
    private int totale_casi;
    @SerializedName("tamponi")
    private int tamponi;
    @SerializedName("casi_testati")
    private int casi_testati;


    private dati(Parcel in) {
        data = in.readString();
        stato = in.readString();
        ricoverati_con_sintomi = in.readInt();
        terapia_intensiva = in.readInt();
        totale_ospedalizzati = in.readInt();
        isolamneto_domiciliare = in.readInt();
        totale_positivi = in.readInt();
        variazione_totali_positivi = in.readInt();
        nuovi_positivi = in.readInt();
        dimessi_guariti = in.readInt();
        deceduti = in.readInt();
        totale_casi = in.readInt();
        tamponi = in.readInt();
        casi_testati = in.readInt();
    }
    /*
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

     */
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(data);
        dest.writeString(stato);
        dest.writeInt(ricoverati_con_sintomi);
        dest.writeInt(terapia_intensiva);
        dest.writeInt(totale_ospedalizzati);
        dest.writeInt(isolamneto_domiciliare);
        dest.writeInt(totale_positivi);
        dest.writeInt(variazione_totali_positivi);
        dest.writeInt(nuovi_positivi);
        dest.writeInt(dimessi_guariti);
        dest.writeInt(deceduti);
        dest.writeInt(totale_casi);
        dest.writeInt(tamponi);
        dest.writeInt(casi_testati);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getData() {
        return data;
    }

    public String getStato() {
        return stato;
    }

    public int getRicoverati_con_sintomi() {
        return ricoverati_con_sintomi;
    }

    public int getTerapia_intensiva() {
        return terapia_intensiva;
    }

    public int getTotale_ospedalizzati() {
        return totale_ospedalizzati;
    }

    public int getIsolamneto_domiciliare() {
        return isolamneto_domiciliare;
    }

    public int getTotale_positivi() {
        return totale_positivi;
    }

    public int getVariazione_totali_positivi() {
        return variazione_totali_positivi;
    }

    public int getNuovi_positivi() {
        return nuovi_positivi;
    }

    public int getDimessi_guariti() {
        return dimessi_guariti;
    }

    public int getDeceduti() {
        return deceduti;
    }

    public int getTotale_casi() {
        return totale_casi;
    }

    public int getTamponi() {
        return tamponi;
    }

    public int getCasi_testati() {
        return casi_testati;
    }
}
