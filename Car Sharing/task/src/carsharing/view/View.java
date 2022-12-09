package carsharing.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public interface View {
    void printInfo();

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    default String collectDataFromUser(String message, String currentValue) {
        System.out.println(message);
        if (!currentValue.isEmpty()) System.out.println("Current value: " + currentValue);
        String currString = null;
        try {
            currString = readInputString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currString != null && currString.isEmpty() ? currentValue : currString;
    }

    default boolean checkAction(int selectedElement, int minElem, int maxElem) {
        return selectedElement >= minElem && selectedElement <= maxElem;
    }

    default int readAction() {
        int selectedElement;
        while (true) {
            try {
                selectedElement = Integer.parseInt(readInputString());
                break;
            } catch (NumberFormatException e) {
                System.out.println("incorrect number! Make your choice:");
            } catch (IOException e) {
                System.out.println("An error while reading input number!");
                System.exit(0);
            }
        }
        return selectedElement;
    }

    default String readInputString() throws IOException {
        String data;
        try {
            data = reader.readLine();
        } catch (IOException e) {
            throw new IOException(e);
        }
        return data.trim();
    }
}
