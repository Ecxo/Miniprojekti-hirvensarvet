package ohtu.hirvensarvet

description "Creating references"

scenario "creating article reference", {

    given "user creates article", {
        cmd = new CommandReader(new Scanner(System.in))
        ui = new CommandLineUI(cmd);
        cmd.setNextLine("add article");        
        }

    when "required fiels for article are given", {
        cmd.setNextLine("journal Aku Ankka");
        cmd.setNextLine("author Walt Disney");
        cmd.setNextLine("title Aku Ankka ostaa makkaraa");
        cmd.setNextLine("year 1999");
        cmd.setNextLine("done");
        }


    then "the article list should contain 1 article", {
        cmd.setNextLine("quit")
        bibmaker = new BibtexMaker(ui)
        bibmaker.run()
        bibmaker.getArticles().size().shouldBeGreaterThan 0
        }

    
}
