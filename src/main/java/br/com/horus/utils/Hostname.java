package br.com.horus.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Hostname {

    public static String getHostname() {
        String hostname = null;

        try {
            InetAddress ip;
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return hostname;
    }
}
