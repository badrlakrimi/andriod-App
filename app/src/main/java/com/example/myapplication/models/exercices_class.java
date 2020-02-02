package com.example.myapplication.models;

public class exercices_class {

    private String id_exercises;
    private String nbrSets_exercises;
    private String nom_exercises;
    private String img_exercises;

    public exercices_class(String id_exercises,String nbrSets_exercises, String nom_exercises) {

        this.id_exercises = id_exercises;
        this.nbrSets_exercises = nbrSets_exercises;
        this.nom_exercises = nom_exercises;
    }

    public exercices_class(String id_exercises,String nbrSets_exercises, String nom_exercises,String img_exercises) {

        this.id_exercises = id_exercises;
        this.nbrSets_exercises = nbrSets_exercises;
        this.nom_exercises = nom_exercises;
        this.img_exercises = img_exercises;
    }


    public exercices_class() {  
    }

    public String getImg_exercises() {
        return img_exercises;
    }

    public void setImg_exercises(String img_exercises) {
        this.img_exercises = img_exercises;
    }

    public String getId_exercises() {
        return id_exercises;
    }

    public void setId_exercises(String id_exercises) {
        this.id_exercises = id_exercises;
    }

    public String getNbrSets_exercises() {
        return nbrSets_exercises;
    }

    public void setNbrSets_exercises(String nbrSets_exercises) {
        this.nbrSets_exercises = nbrSets_exercises;
    }

    public String getNom_exercises() {
        return nom_exercises;
    }

    public void setNom_exercises(String nom_exercises) {
        this.nom_exercises = nom_exercises;
    }


    @Override
    public String toString() {
        return "exercices_class{" +
                "id_exercises='" + id_exercises + '\'' +
                ", nbrSets_exercises='" + nbrSets_exercises + '\'' +
                ", nom_exercises='" + nom_exercises + '\'' +
                ", img_exercises='" + img_exercises + '\'' +
                '}';
    }
}
