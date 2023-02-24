package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;

public class TelegramBot extends TelegramLongPollingBot {

    public static final String TOKEN = "5998602308:AAHORbFJ0I84WECIe_83ljQfijSotBRexfk";
    public static final String BOT_USERNAME = "JBFREE9NasaBot_bot";

    public static final String URI = "https://api.nasa.gov/planetary/apod?api_key=LPQpFZ1whiXBjncjda5ui00mYAmOnYWMk6euhpj8";
    public static long chat_id;

    public TelegramBot() throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()){
            chat_id=update.getMessage().getChatId();
            switch (update.getMessage().getText()) {
                case "/help" -> sendMessage("Привет, я бот NASA! Я высылаю ссылки на картинки по запросу. " +
                        "Напоминаю, что картинки на сайте NASA обновляются раз в сутки");
                case "/give" -> {
                    try {
                        sendMessage(Utils.getUrl(URI));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                default -> sendMessage("Я не понимаю :(");
            }
        }
    }


    @Override
    public String getBotToken() {
        return TOKEN;
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }


    private void sendMessage(String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId(chat_id);
        message.setText(messageText);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
