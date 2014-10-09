/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ohtu.hirvensarvet;

/**
 *
 * @author lasse
 */
public interface UI {
    void displayMenu();
    String[] getCommand(String prompt);
    int getInt(String prompt);
    Article addArticle(String key);
    void saveEntries(String formattedEntries);
    Article editArticle(Article toEdit);
}
