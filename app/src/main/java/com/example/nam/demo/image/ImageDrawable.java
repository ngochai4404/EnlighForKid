package com.example.nam.demo.image;

import android.util.Log;

import com.example.nam.demo.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Nam on 7/14/2016.
 */

public class ImageDrawable  {
    private ArrayList<String > list = new ArrayList<>();
    private ArrayList<String > alphabets = new ArrayList<>();
    private ArrayList<String > animal= new ArrayList<>();
    private ArrayList<String > classroom= new ArrayList<>();
    private ArrayList<String > color= new ArrayList<>();
    private ArrayList<String > country= new ArrayList<>();
    private ArrayList<String > food= new ArrayList<>();
    private ArrayList<String > fruits= new ArrayList<>();
    private ArrayList<String > jobs= new ArrayList<>();
    private ArrayList<String > number= new ArrayList<>();
    private ArrayList<String > shapes= new ArrayList<>();
    private ArrayList<String > transport= new ArrayList<>();
    private ArrayList<String > verb= new ArrayList<>();
    private int x;
    private int y;
    public ImageDrawable(){

        findId();
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void removeQues(int t, int a, int b){
        list.remove(t);
        this.x = a;
        this.y = b;
        switch (a){
            case 0:
                alphabets.remove(b);
                return;
            case 1:
                animal.remove(b);
                return;
            case 2:
                classroom.remove(b);
                return;
            case 3:
                color.remove(b);
                return;
            case 4:
                country.remove(b);
                return;
            case 5:
                food.remove(b);
                return;
            case 6:
                fruits.remove(b);
                return;
            case 7:
                jobs.remove(b);
                return;
            case 8:
                number.remove(b);
                return;
            case 9:
                shapes.remove(b);
                return;
            case 10:
                transport.remove(b);
                return;
        }
    }

    public int  random(){
        Random random = new Random();
        x = random.nextInt(11);
        switch (x){
            case 0:
                y=random.nextInt(alphabets.size());
                return findID_Name(alphabets.get(y));
            case 1:
                y=random.nextInt(animal.size());
                return findID_Name(animal.get(y));
            case 2:
                y=random.nextInt(classroom.size());
                return findID_Name(classroom.get(y));
            case 3:
                y=random.nextInt(color.size());
                return findID_Name(color.get(y));
            case 4:
                y=random.nextInt(country.size());
                return findID_Name(country.get(y));
            case 5:
                y=random.nextInt(food.size());
                return findID_Name(food.get(y));
            case 6:
                y=random.nextInt(fruits.size());
                return findID_Name(fruits.get(y));
            case 7:
                y=random.nextInt(jobs.size());
                return findID_Name(jobs.get(y));
            case 8:
                y=random.nextInt(number.size());
                return findID_Name(number.get(y));
            case 9:
                y=random.nextInt(shapes.size());
                return findID_Name(shapes.get(y));
            case 10:
                y=random.nextInt(transport.size());
                return findID_Name(transport.get(y));
        }
        return 0;
    }
    public String random1(){
        Random random = new Random();
        int x = random.nextInt(11);
        int y;
        switch (x){
            case 0:
                y=random.nextInt(alphabets.size()+0);
                return list.get(findID_Name(alphabets.get(y)));
            case 1:
                y=random.nextInt(animal.size()+0);
                return list.get(findID_Name(animal.get(y)));
            case 2:
                y=random.nextInt(classroom.size()+0);
                return list.get(findID_Name(classroom.get(y)));
            case 3:
                y=random.nextInt(color.size()+0);
                return list.get(findID_Name(color.get(y)));
            case 4:
                y=random.nextInt(country.size()+0);
                return list.get(findID_Name(country.get(y)));
            case 5:
                y=random.nextInt(food.size()+0);
                return list.get(findID_Name(food.get(y)));
            case 6:
                y=random.nextInt(fruits.size()+0);
                return list.get(findID_Name(fruits.get(y)));
            case 7:
                y=random.nextInt(jobs.size()+0);
                return list.get(findID_Name(jobs.get(y)));
            case 8:
                y=random.nextInt(number.size()+0);
                return list.get(findID_Name(number.get(y)));
            case 9:
                y=random.nextInt(shapes.size());
                return list.get(findID_Name(shapes.get(y)));
            case 10:
                y=random.nextInt(transport.size()+0);
                return list.get(findID_Name(transport.get(y)));
        }
        return null;
    }
    public int findID_Name(String name){
        for(int i=0;i<list.size();i++){
            if(list.get(i).equals(name))
                return i;
        }
        Log.d("MYTAG", name);
        return -1;
    }
    public ArrayList<String> list(String name){
        switch (name){
            case "list":
                return list;
            case "alphabets":
                return alphabets;
            case "animal":
                return animal;
            case "classroom":
                return classroom;
            case "color":
                return color;
            case "country":
                return country;
            case "food":
                return food;
            case "fruits":
                return fruits;
            case "jobs":
                return jobs;
            case "number":
                return number;
            case "shapes":
                return shapes;
            case "transport":
                return transport;

        }
        return list;
    }
    public String getFullName(int id){

        return list.get(id);
    }
    public String name(int id){
        String f = list.get(id);
        String [] cut = f.split("_");
        f=cut[1];
        f  = String.valueOf(f.charAt(0)).toUpperCase()+f.substring(1);
        return f;
    }
    public String getName(String fullName){
        String f =fullName;
        String [] cut = f.split("_");
        f=cut[1];
        f  = String.valueOf(f.charAt(0)).toUpperCase()+f.substring(1);
        return f;
    }
    public String group(String name){
        if(name.startsWith("alphabets"))
            return "alphabets";
        if(name.startsWith("animal"))
            return "animal";
        if(name.startsWith("classroom"))
            return "classroom";
        if(name.startsWith("color"))
            return "color";
        if(name.startsWith("country"))
            return "country";
        if(name.startsWith("food"))
            return "food";
        if(name.startsWith("fruits"))
            return "fruits";
        if(name.startsWith("jobs"))
            return "jobs";
        if(name.startsWith("number"))
            return "number";
        if(name.startsWith("shapes"))
            return "shapes";
        if(name.startsWith("transport"))
            return "transport";
        return "alphabets";

    }
    public ArrayList<String> start(){
        ArrayList<String> p = new ArrayList<>();
        p.add(alphabets.get(23));
        p.add(animal.get(0));
        p.add(classroom.get(0));
        p.add(color.get(0));
        p.add(country.get(0));
        p.add(food.get(0));
        p.add(fruits.get(0));
        p.add(jobs.get(0));
        p.add(number.get(0));
        p.add(shapes.get(0));
        p.add(transport.get(0));

        return p;
    }
    public void findId(){
        Field[] drawables =R.drawable.class.getDeclaredFields();
        for (Field i:drawables) {
            try {
                if(i.getName().startsWith("alphabets")){
                    list.add(i.getName());
                    alphabets.add(i.getName());
                }if(i.getName().startsWith("animal")){
                    list.add(i.getName());
                    animal.add(i.getName());
                }if(i.getName().startsWith("classroom")){
                    list.add(i.getName());
                    classroom.add(i.getName());
                }if(i.getName().startsWith("color")){
                    list.add(i.getName());
                    color.add(i.getName());
                }if(i.getName().startsWith("country")){
                    list.add(i.getName());
                    country.add(i.getName());
                }if(i.getName().startsWith("food")){
                    list.add(i.getName());
                    food.add(i.getName());
                }if(i.getName().startsWith("jobs")){
                    list.add(i.getName());
                    jobs.add(i.getName());
                }if(i.getName().startsWith("fruits")){
                    list.add(i.getName());
                    fruits.add(i.getName());
                }if(i.getName().startsWith("number")){
                    list.add(i.getName());
                    number.add(i.getName());
                }if(i.getName().startsWith("shapes")){
                    list.add(i.getName());
                    shapes.add(i.getName());
                }if(i.getName().startsWith("transport")) {
                    list.add(i.getName());
                    transport.add(i.getName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
