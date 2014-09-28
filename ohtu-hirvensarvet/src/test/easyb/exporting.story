package ohtu.hirvensarvet

description "Exporting reference"

scenario "Exporting an article reference", {

    given "user creates a valid article"
    when "user exports article"
    then "a reference in bibtext format is created"

}
