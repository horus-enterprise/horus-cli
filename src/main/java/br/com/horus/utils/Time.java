package br.com.horus.utils;

public class Time {

    public static String secondsToHHmmss(Integer seconds) {
        Integer horas = seconds / 3600;
        Integer minutos = (seconds - (horas * 3600)) / 60;
        Integer segundos = seconds - (horas * 3600) - (minutos * 60);
        return String.format("%d:%d:%d", horas, minutos, segundos);
    }
}
