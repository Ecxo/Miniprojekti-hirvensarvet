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
    
    private Scanner scanner;
    
    public CommandLineUI (Scanner scanner) {
        
        this.scanner = scanner;
    }
    
    @Override
    public String[] getCommand(String prompt) {
        System.out.print(prompt+" ");
        String input = scanner.nextLine();
        return input.split(" ");
    }
    
    @Override
    public Article addArticle(String key) {
        Article article = new Article(key);
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
