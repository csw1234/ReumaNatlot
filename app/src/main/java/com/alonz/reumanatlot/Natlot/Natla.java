package com.alonz.reumanatlot.Natlot;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by alonz on 16/11/2017.
 */
@Entity
public class Natla {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public Natla() {
    }

    String Url;
    boolean favorite=false;

    public Natla(String url) {
    Url=url;
    }
    public int getIdText(){
        return id;
    }
    public String getUrl(){
        return Url;
    }
    public Boolean getFavorite(){
        return favorite;
    }
    public void setFavorite(Boolean favorite){
         this.favorite=favorite;
    }
    public void setUrl(String url){
        this.Url=url;
    }

}
