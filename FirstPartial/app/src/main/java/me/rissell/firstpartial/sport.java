package me.rissell.firstpartial;

/**
 * Created by gdaalumno on 2/16/16.
 */
public class sport {

    private String name;
    private String country;
    private String image;

    public sport(String name, String country, String image){
        this.name = name;
        this.country = country;
        this.image = image;
    }

    public void setName(String name){
        this.name = name;
    }
    public void setCountry(String country){
        this.country = country;
    }
    public void setImage(String image){
        this.image = image;
    }

    public String getName(){
        return this.name;
    }
    public String getCountry(){
        return this.country;
    }
    public String getImage(){
        return this.image;
    }
}
