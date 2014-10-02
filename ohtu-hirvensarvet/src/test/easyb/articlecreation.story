package ohtu.hirvensarvet

description "Creating references"

scenario "creating article reference", {

    given "user creates article", {
        cmd = new CommandReader(new Scanner(System.in))
        ui = new CommandLineUI(cmd);
        cmd.setNextLine("add test");        
        }

    when "required fiels for article are given", {
        cmd.setNextLine("0");
        cmd.setNextLine("Walt Disney");
        cmd.setNextLine("Aku ankka ostaa jotain");
        cmd.setNextLine("Aku Ankka");
        cmd.setNextLine("1999");
cmd.setNextLine("done");
        }


    then "the article list should contain 1 article", {
        cmd.setNextLine("quit")
        bibmaker = new BibtexMaker(ui)
        bibmaker.run()
        bibmaker.getArticles().size().shouldBeGreaterThan 0
        }

    
}

scenario "creating article with incorrect field", {

    given "user creates article"
    when "incorrect fields are given"
    then "warning should be given"

}

scenario "creating article reference with field promts", {

    given "user creates article"
    when "program should ask for mandatory fields"
    then "article is created"

    
}
