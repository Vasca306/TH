package com.example.myapplication;

public class ModalClass {

    int image;
    String text;
    String text1;
    String text2;
    String text3;

    public ModalClass(int image, String text, String text1, String text2, String text3) {
        this.image = image;
        this.text = text;
        this.text1 = text1;
        this.text2 = text2;
        this.text3 = text3;

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText1(){
        return text1;
    }
    public void setText1(){
        this.text1 = text1;
    }

    public String getText2(){
        return text2;
    }
    public void setText2(){
        this.text2 = text2;
    }

    public String getText3(){
        return text3;
    }
    public void setText3(){
        this.text3 = text3;
    }

}
