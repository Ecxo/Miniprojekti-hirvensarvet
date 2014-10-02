package ohtu.hirvensarvet

description "Creating references"

scenario "creating article reference", {

    given "user creates article", {
        printer = new Printer()
        cmd = new CommandReader(new Scanner(System.in))
        ui = new CommandLineUI(cmd,printer)
        cmd.setNextLine("add test");       
        }

    when "required fiels for article are given", {
        cmd.setNextLine("0")
        cmd.setNextLine("Walt Disney")
        cmd.setNextLine("Aku ankka ostaa jotain")
        cmd.setNextLine("Aku Ankka")
        cmd.setNextLine("1999")
        cmd.setNextLine("done")
        }


    then "the article list should contain 1 article", {
        cmd.setNextLine("quit")
        bibmaker = new BibtexMaker(ui,printer)
        bibmaker.run()
        bibmaker.getArticles().size().shouldBeGreaterThan 0
        }

    
}

scenario "creating article with incorrect field", {

    given "user creates article", {
        printer = new Printer()
        cmd = new CommandReader(new Scanner(System.in))
        ui = new CommandLineUI(cmd,printer)
        cmd.setNextLine("add test");       
        }

    when "incorrectly named fields for article are given", {
        cmd.setNextLine("0")
        cmd.setNextLine("Walt Disney")
        cmd.setNextLine("Aku ankka ostaa jotain")
        cmd.setNextLine("Aku Ankka")
        cmd.setNextLine("1999")
        cmd.setNextLine("lazerbeamgun turbocharger")
        cmd.setNextLine("done")
        }


    then "warning should be given", {
        cmd.setNextLine("quit")
        bibmaker = new BibtexMaker(ui,printer)
        bibmaker.run()
        
        ensure(printer.historyContainsLine("is not a valid BibTex field.")) {   
        isTrue
        }


        }

}

scenario "creating article reference with field promts", {

    given "user is creating an article article", {
        printer = new Printer()
        cmd = new CommandReader(new Scanner(System.in))
        ui = new CommandLineUI(cmd,printer);
        cmd.setNextLine("add test");        
        }

    when "article has been created", {
        cmd.setNextLine("0");
        cmd.setNextLine("Walt Disney");
        cmd.setNextLine("Aku ankka ostaa jotain");
        cmd.setNextLine("Aku Ankka");
        cmd.setNextLine("1999");
        cmd.setNextLine("done");
        cmd.setNextLine("quit")
        bibmaker = new BibtexMaker(ui,printer)
        bibmaker.run()
        }

    then "mandatory field were asked", {
        ensure(printer.historyContainsLine("Please enter author:")) {   
        isTrue
        }
        ensure(printer.historyContainsLine("Please enter author:")) {   
        isTrue
        }
        ensure(printer.historyContainsLine("Please enter title:")) {   
        isTrue
        }
        ensure(printer.historyContainsLine("Please enter journal:")) {   
        isTrue
        }
        ensure(printer.historyContainsLine("Please enter year:")) {   
        isTrue
        }

        }   
}
