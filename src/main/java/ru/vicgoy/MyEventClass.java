package ru.vicgoy;

import org.telegram.telegrambots.api.objects.User;

import java.util.ArrayList;
import java.util.Date;

public class MyEventClass {
    private String date;
    private String name;
    private String whosName;
    private ArrayList<String> people = new ArrayList<String>();

    MyEventClass(String date, String name, ArrayList<String> people, String whosName) {
        this.whosName = whosName;
        this.date = date;
        this.name = name;
        this.people = people;
    }

    MyEventClass(String date, String name, String whosName) {
        this.whosName = whosName;
        this.date = date;
        this.name = name;

    }

/*    @Override
    public String toString() {
        return date + " числа, вот этот злодей: " + whosName.toString() + " Запланировал мероприятие: " + name+" А вот они подписались на него: "+people.toString();
    }*/
    public String toString() {
        return date + " числа, вот этот злодей: " + whosName.toString() + " Запланировал мероприятие: " + name; //" А вот они подписались на него: ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyEventClass that = (MyEventClass) o;

        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (whosName != null ? !whosName.equals(that.whosName) : that.whosName != null) return false;
        return people != null ? people.equals(that.people) : that.people == null;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (whosName != null ? whosName.hashCode() : 0);
        result = 31 * result + (people != null ? people.hashCode() : 0);
        return result;
    }
}
