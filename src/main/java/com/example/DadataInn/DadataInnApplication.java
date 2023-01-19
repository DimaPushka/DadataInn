package com.example.DadataInn;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class DadataInnApplication {

    public static void main(String[] args) {
        Scanner str = new Scanner(System.in);
        String first;
        System.out.print("Введите ИНН: ");
        first = str.nextLine();
        System.out.println(message("https://suggestions.dadata.ru/suggestions/api/4_1/rs/findById/party", "" + first));
    }

    public static String message(String linkURL, String urlParam) {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(linkURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization", "Token 5aca1b4081ef5604ffa22a187a4dcd34a8327a62");
            connection.setUseCaches(false);
            connection.setDoOutput(true);


            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(String.format("{ \"query\": \"%s\" }", urlParam));
            wr.close();

            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }

            rd.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
