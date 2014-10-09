package ohtu.hirvensarvet

description "Editing an edited article"

scenario "Editing article", {

    given "user creates article with valid fields", {
        printer = new Printer()
        cmd = new CommandReader(new Scanner(System.in))
        ui = new CommandLineUI(cmd,printer)
        cmd.setNextLine("add test");  
        cmd.setNextLine("0")
        cmd.setNextLine("Walt Disney")
        cmd.setNextLine("Aku ankka ostaa jotain")
        cmd.setNextLine("Aku Ankka")
        cmd.setNextLine("1999")
        cmd.setNextLine("done")     
        }

    when "users wants to edit a field", {
        cmd.setNextLine("edit test");
        cmd.setNextLine("author");
        cmd.setNextLine("heikki");
        cmd.setNextLine("done");
        cmd.setNextLine("list");

        }


    then "the field should be changed", {
        cmd.setNextLine("quit")
        bibmaker = new BibtexMaker(ui,printer)
        bibmaker.run()
        bibmaker.getArticles().size().shouldBeGreaterThan 0
        ensure(printer.historyContainsLine("author  = \"heikki \"")) {   
        isTrue
        }

        }

    
    }

scenario "Deleting article", {

    given "user creates article with valid fields", {
        printer = new Printer()
        cmd = new CommandReader(new Scanner(System.in))
        ui = new CommandLineUI(cmd,printer)
        cmd.setNextLine("add test");  
        cmd.setNextLine("0")
        cmd.setNextLine("Walt Disney")
        cmd.setNextLine("Aku ankka ostaa jotain")
        cmd.setNextLine("Aku Ankka")
        cmd.setNextLine("1999")
        cmd.setNextLine("done")     
        }

    when "users wants to delete the reference", {
        cmd.setNextLine("remove test");
        cmd.setNextLine("edit");
        cmd.setNextLine("edit test");
        cmd.setNextLine("quit");
        }

    then "the field should be changed", {
        BibtexMaker testUi = new BibtexMaker(ui, printer);
        testUi.run();
        ensure(printer.historyContainsLine("No article found")) {   
        isTrue
        }

        }

    
    }

