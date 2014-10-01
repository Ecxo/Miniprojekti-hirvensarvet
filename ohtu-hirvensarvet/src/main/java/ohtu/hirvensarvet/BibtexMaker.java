/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.hirvensarvet;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author lasse
 */
public class BibtexMaker {

    private UI ui;
    private ArrayList<Article> articles;

    public BibtexMaker(UI ui) {
        this.ui = ui;
        this.articles = new ArrayList<Article>();
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
                        System.out.println("Please enter article id (add somearticle)");
                        break;
                    }
                    articles.add(ui.addArticle(command[1]));
                case "list":
                    for (Article a : this.articles) {
                        //System.out.println(a.toString());
                        // Changed to pretty printing with article formatter
                        ArticleFormatter formatter = new ArticleFormatter(a);
                        System.out.print(formatter.exportArticle());
                    }
                default:
                    break;
            }
        }
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public static void main(String[] args) {
        UI ui = new CommandLineUI(new CommandReader(new Scanner(System.in)));

        new BibtexMaker(ui).run();
    }
}
