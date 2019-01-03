package com.example.avinash.retrofit;

import com.google.gson.annotations.SerializedName;

public class Repo {

    @SerializedName("name")
   private  String Name;

    @SerializedName("owner")

   private Owner owner;

    @SerializedName("forks")
    private Integer forks;



    public void Repo(String Name,Owner owner,Integer forks){
        this.Name = Name;
        this.owner =owner;
        this.forks = forks;
    }




    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public  Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Integer getForks() {
        return forks;
    }

    public void setForks(Integer forks) {
        this.forks = forks;
    }
}

