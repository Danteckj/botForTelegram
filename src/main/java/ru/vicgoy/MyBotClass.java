package ru.vicgoy;

import com.sun.javafx.collections.MappingChange;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.*;


public class MyBotClass extends TelegramLongPollingBot {
 private static HashMap<String,MyEventClass> eventClassHashMap = new HashMap();

    public static void main(String[] args) {

        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new MyBotClass());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }




    public void onUpdateReceived(Update e) {

        MyEventClass eventClass = new MyEventClass("12.05.2023","Тестовый ивент. ","@Vicgoy");
        eventClassHashMap.put("12.06.43",eventClass);
        Message msg = e.getMessage(); // Это нам понадобится
        String txt = msg.getText();
        String m;
        ArrayList<String> phrases = new ArrayList<String>();
        phrases.add("Здарова корова");
        phrases.add("Йоу, че как?");
        phrases.add("Есть че?");
        phrases.add("Добрый вечер, вы сегодня особенно прекрасны");
        phrases.add("Погнали в Тему");
        phrases.add("Че тебе от меня надо?");
        phrases.add("Я тебя люблю");
        if (txt.equals("/start")) {
            sendMsg(msg, "Я бесполезный бот нашего чатика, рад познакомиться");
        } else if (txt.equals("/гори в аду")) {
            sendMsg(msg, "а ты ни очень ругаться умеешь");
        } else if (txt.equals("/chat")) {
            int random = new Random().nextInt(6);
            m = phrases.get(random);
            sendMsg(msg, m);
        } else if (txt.contains("/addevent")){
            ArrayList<String> people = new ArrayList<String>();
            String name = txt.toString().substring(9);


            MyBotClass.newEvent(new Date().toString(),name,"@"+msg.getFrom().getUserName().toString());

        } else if (txt.equals("/events")){
            String s = new String();
            for (Map.Entry<String, MyEventClass> entry: eventClassHashMap.entrySet()){
                sendMsg(msg,entry.getValue().toString());
            }
            }
        }


    @SuppressWarnings("deprecation") // Означает то, что в новых версиях метод уберут или заменят
    private void sendMsg(Message msg, String text) {
        SendMessage s = new SendMessage();
        s.setChatId(msg.getChatId()); // Боту может писать не один человек, и поэтому чтобы отправить сообщение, грубо говоря нужно узнать куда его отправлять
        s.setText(text);
        try { //Чтобы не крашнулась программа при вылете Exception
            sendMessage(s);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "Freeisbeebot";
    }

    public String getBotToken() {
        return "491176303:AAE_Tu3qi45GY5T0HR7XRvlbGkBYSsdQOE8";
    }

    private static void newEvent(String date, String name,  String whosName) {
        MyEventClass newEvent = new MyEventClass(date, name, whosName);
        eventClassHashMap.put(date, newEvent);
    }

 }
