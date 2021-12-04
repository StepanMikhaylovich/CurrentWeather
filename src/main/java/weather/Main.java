package weather;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String cityName = scanner.nextLine();
        do {
            String apiKey = "3a25ecbe9bf76ba9ddddc2a9f63383ba";
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + apiKey);
            JsonFactory jsonFactory = new JsonFactory();
            JsonParser parser = jsonFactory.createParser(url);
            parser.nextToken();
            while (parser.hasCurrentToken()) {
                if (parser.currentToken().equals(JsonToken.FIELD_NAME)) {
                    if (parser.currentName().equals("temp")) {
                        parser.nextValue();
                        Float temp = parser.getFloatValue();
                        System.out.println("Температура в " + cityName + ": " + (temp - 273) + " C°");
                    }
                }
                parser.nextToken();
            }
            cityName = scanner.nextLine();
        } while (!cityName.equals("exit"));

    }

}
