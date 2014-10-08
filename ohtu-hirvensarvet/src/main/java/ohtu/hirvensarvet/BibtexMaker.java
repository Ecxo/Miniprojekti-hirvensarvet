/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.hirvensarvet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

/**
 *
 * @author lasse
 */
public class BibtexMaker {

    private UI ui;
    private ArrayList<Article> articles;
    private Printer printer;
    
    public BibtexMaker(UI ui, Printer print) {
        this.ui = ui;
        this.articles = new ArrayList<Article>();
        this.printer = print;
    }
    
    public BibtexMaker(UI ui, Printer print, ArrayList<Article> articles) {
        this.ui = ui;
        this.articles = articles;
        this.printer = print;
    }

    public void run() {

        while (true) {

            String[] command = ui.getCommand("Enter bibtexmaker command:\n>");
            if (command[0].toLowerCase().equals("quit")) {
                break;
            }
            switch (command[0].toLowerCase()) {
                case "help":
                    ui.displayMenu();
                    break;

                case "add":
                    if (command.length != 2) {
                        printer.println("Please enter article id (add somearticle)");
                        break;
                    }
                    articles.add(ui.addArticle(command[1]));
                    break;

                case "list":
                    printer.print(catArticles(articles));
                    break;

                case "save":
                    ui.saveEntries(catArticles(articles));
                    break;

                case "remove":
                    if (command.length != 2) {
                        printer.println("Please enter article id (remove somearticle)");
                        break;
                    }
                    articles.remove(new Article(command[1]));
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Concatenates string representations of a collection of articles, uses
     * newline to separate individual entries
     *
     * @param articles collection of articles
     * @return Entries separated with newlines
     */
    public String catArticles(Collection<Article> articles) {

        String result = "";

        for (Article a : articles) {
            result += ArticleFormatter.exportArticle(a) + "\n\n";
        }

        return result;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public static void main(String[] args) {
        Printer printer = new Printer();
        UI ui = new CommandLineUI(new CommandReader(new Scanner(System.in)), printer);
        String bibtexFile = "";
        ArrayList<Article> readArticles = new ArrayList<Article>();
        if (args.length > 0) {
            try {
            // Open the file that is the first 
                // command line parameter
                FileInputStream fstream = new FileInputStream(args[0]);
                BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                String strLine;
                //Read File Line By Line
                while ((strLine = br.readLine()) != null) {
                    // Print the content on the console
                    bibtexFile += strLine;
                }
                //Close the input stream
                fstream.close();
                readArticles = (ArrayList) CitationParser.parseBibtexFile(bibtexFile);
            } catch (Exception e) {//Catch exception if any
                System.err.println("Error: " + e.getMessage());
                System.exit(0);
            }
        }
        new BibtexMaker(ui,printer,readArticles).run();
    }
}
