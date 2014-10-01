/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ohtu.hirvensarvet;

import java.util.Scanner;

/**
 *
 * @author lasse
 */
public class CommandLineUI implements UI {
    private final CommandReader scanner;
    private final ArticleValidator validator = new ArticleValidator();
    public CommandLineUI (CommandReader scanner) {
        
        this.scanner = scanner;
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
        System.out.print(prompt+" ");
        String input = scanner.nextLine();
        return input.split(" ");
    }
    
    @Override
    public int getInt(String prompt) {
        System.out.print(prompt+" ");
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
        System.out.println("Please select citation type: \n");
        //System.out.print("Supported types: ");
        for (int i = 0; i < ArticleValidator.citation_types.length; i++) {
            System.out.print("(" + i + ")" + " " + ArticleValidator.citation_types[i]
                + "\n");
        }
        int citation_type = getInt(">");
        article.setCitationType(ArticleValidator.citation_types[citation_type]);
        
        // Getting all mandatory fields
        for (String mandatory_field : ArticleValidator.mandatory_fields[citation_type]) {
            System.out.println("Please enter " + mandatory_field + ": ");
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
        
        
        System.out.println("Add: Enter [field type] [field value] or done.");
        while(true) {
            String[] input = getCommand(">");
            if (input[0].toLowerCase().equals("done")) {
                break;
            }
           
            String type = input[0];
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
            //System.out.print(article.toString());
        }
        //System.out.print(article.toString());
        return article;
    }
    
    /**
     * Display available commands.
     */
    @Override
    public void displayMenu() {
        System.out.println("Available commands:");
        System.out.println("add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("save");
        System.out.println("quit");
        System.out.println("help");
    }
}
