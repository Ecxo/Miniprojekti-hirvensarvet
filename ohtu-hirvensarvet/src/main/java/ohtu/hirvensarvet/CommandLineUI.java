/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ohtu.hirvensarvet;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author lasse
 */
public class CommandLineUI implements UI {
    private final CommandReader scanner;
    private final ArticleValidator validator = new ArticleValidator();
    private Printer printer;
    public CommandLineUI (CommandReader scanner, Printer printer) {
        
        this.scanner = scanner;
        this.printer = printer;
    }
    
    /**
     * Queries for user input, partitions it into
     * space-separated subparts and returns those substrings
     * as an array.
     * 
     * @param prompt String signifying that 
     * the program is waiting for user input.
     * @return array of space-separated substrings
     */
    @Override
    public String[] getCommand(String prompt) {
        printer.print(prompt+" ");
        String input = scanner.nextLine();
        return input.split(" ");
    }
    
    public void saveEntries(String formattedEntries) {
        printer.println("Please specify the name of the file to save into, or 'cancel' to cancel the operation:");
        String filename = getCommand(">")[0];
        if(filename.equals("cancel")) {
            return;
        }
        
        File file = new File("target" + File.separator + filename);
        
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(formattedEntries);
            writer.close();
        } catch(Exception e) {
            printer.println("Something went wrong");
            return;
        }
        
        printer.println("Entries successfully saved to: '" +
                file.getAbsolutePath() + "'");
    }
    
    @Override
    public int getInt(String prompt) {
        printer.print(prompt+" ");
        int input = scanner.nextInt();
        return input;
    }    
    /**
     * Create a new article entry and query user for field types
     * and corresponding field values of the entry.
     * 
     * @param key Id-string of the article
     * @return Article object
     * 
     * 
     */
    @Override
    public Article addArticle(String key) {
        Article article = new Article(key);
        //get citation type
        printer.println("Please select citation type: \n");
        //printer.print("Supported types: ");
        for (int i = 0; i < ArticleValidator.citation_types.length; i++) {
            printer.print("(" + i + ")" + " " + ArticleValidator.citation_types[i]
                + "\n");
        }
        
        int citation_type = 0;
        
        try {
            citation_type = getInt(">");
        } catch(Exception e) {
            printer.println("Invalid citation type");
            return addArticle(key);
        }
        
        article.setCitationType(ArticleValidator.citation_types[citation_type]);
        
        // Getting all mandatory fields
        for (String mandatory_field : ArticleValidator.mandatory_fields[citation_type]) {
            printer.println("Please enter " + mandatory_field + ": ");
            String[] input = getCommand(">");
            String value = "";
            for (int i = 0; i < input.length; i++ ) {
                
                String next;
                
                if(i == input.length - 1) {
                    next = input[i];
                } else {
                    next = input[i] + " ";
                }
                
                value = value.concat(next);
            }
            article.addField(mandatory_field, value);
        }
        
        
        printer.println("Add: Enter [field type] [field value] or help to display field types.\n"
                + "Enter done to quit.");
        while(true) {
            String[] input = getCommand(">");
            if (input[0].toLowerCase().equals("done")) {
                break;
            }
            // Display available field types
            if (input[0].toLowerCase().equals("help")) {
                printer.println("Valid types:\n" + displayFieldTypes());
                continue;
            }
           
            String type = input[0];
			if(!ArticleValidator.isValidFieldName(type)){
				printer.println("\""+type+"\" is not a valid BibTex field.");
				continue;
			}

            String value = "";
            for (int i = 1; i < input.length; i++ ) {
                
                String next;
                
                if(i == input.length - 1) {
                    next = input[i];
                } else {
                    next = input[i] + " ";
                }
                
                value = value.concat(next);
            }
            
            article.addField(type, value);
            //printer.print(article.toString());
        }
        //printer.print(article.toString());
        return article;
    }
    
    /**
     * Display available commands.
     */
    @Override
    public void displayMenu() {
        printer.println("Available commands:");
        printer.println("add");
        printer.println("remove");
        printer.println("list");
        printer.println("save");
        printer.println("quit");
        printer.println("help");
    }
    
    public String displayFieldTypes() {
        String types = "";
        for (String type : ArticleValidator.valid_fields) {
            types += type + "\n";
        }
        return types;
    }
}
