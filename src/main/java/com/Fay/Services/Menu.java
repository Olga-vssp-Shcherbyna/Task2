package com.Fay.Services;

import com.Fay.TextComponents.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.Fay.Services.DocumentService.findUniqueWord;
import static com.Fay.Services.DocumentService.getTextDocument;
import static com.Fay.TextComponents.Document.getInstance;

public class Menu {
    public static void menu() throws IOException {
        System.out.print("If you want to:\n exit - enter \"0\"\n upload text from file - enter \"1\"\n analyse text from command line - enter \"2\"\n So, task number is - ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int variant = 0;
        try {
            variant = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            System.out.println("Please, write correct task number");
            menu();
        }
        switch (variant) {
            case 0:
                System.exit(0);
            case 1: {
                System.out.println("Enter filepath please");
                String filePath = null;
                try {
                    filePath = reader.readLine();
                } catch (IOException e) {
                    System.out.println("Wrong filepath, please, try again");
                }
                Document document = getTextDocument(filePath);
                findUniqueWord(document);
                break;
            }
            case 2: {
                System.out.println("Please, enter text");
                Document document = getDocumentFromCommandLine(reader);
                findUniqueWord(document);
                break;
            }
            default:
                System.out.println("Invalid task number, please, try again");
        }
        menu();
    }

    static Document getDocumentFromCommandLine(BufferedReader reader) throws IOException {
        try {
            return getInstance(reader.readLine());
        } catch (IOException e) {
            System.out.println("Invalid input, please, try again");
            menu();
        }
        return getInstance("");
    }
}
