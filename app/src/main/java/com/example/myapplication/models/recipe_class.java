package com.example.myapplication.models;

public class recipe_class {

    private String recipeId;
    private String recipeNume;
    private String recipeImage;
    private String recipeDesc;

    public recipe_class(String recipeId, String recipeNume, String recipeImage, String recipeDesc) {
        this.recipeId = recipeId;
        this.recipeNume = recipeNume;
        this.recipeImage = recipeImage;
        this.recipeDesc = recipeDesc;
    }

    public recipe_class() {
    }

    @Override
    public String toString() {
        return "recipe_class{" +
                "recipeId='" + recipeId + '\'' +
                ", recipeNume='" + recipeNume + '\'' +
                ", recipeImage='" + recipeImage + '\'' +
                ", recipeDesc='" + recipeDesc + '\'' +
                '}';
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeNume() {
        return recipeNume;
    }

    public void setRecipeNume(String recipeNume) {
        this.recipeNume = recipeNume;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }

    public String getRecipeDesc() {
        return recipeDesc;
    }

    public void setRecipeDesc(String recipeDesc) {
        this.recipeDesc = recipeDesc;
    }
}
