CommandLineUI design idea
=========================
####Juha Kivekäs
####22.9.2014

The commandline interface could work by the user executing commands to preform simple tasks. Possible commans for the application could be:

-	add
-	remove
-	list
-	save
-	quit
-	help

In order for the user not to have to memorize the commands and how the arguments are ordered it is best to use commands that require only one or no arguments. For example the save command could work like this:

	Enter bibtex maker command:
	>save citations.bib
	Citations saved into file citations.bib.
	Enter bibtex maker command:
	>

For commands that require more than one argument there could be a sub-shell in which the user could name the argument name and value. For example the add command doesn't need the same arguments for a book and an article. The sub-shell could look like this:

	Enter bibtex maker command:
	>add HM06
	Add: Enter [field type] [filed value] or done.
	>type inproceedings
	>author Hassinen, Marko and Mäyrä, Hannui
	>title Learning programming by programming: a case study
	.
	.
	>publisher ACM
	>done
	Citation MH06 saved.
	Enter bibtex maker command:
	>

Implementing a shell for simple one-word commands like these is easily done with a case-switch inside a loop. It would be preferred if all the shell and sub-shell commands followed the same model:

	[command] [argument string]

The example .bib object used in this document:

	@inproceedings{HM06,
		author = {Hassinen, Marko and M\"{a}yr\"{a}, Hannu},
		title = {Learning programming by programming: a case study},
		booktitle = {Baltic Sea '06: Proceedings of the 6th Baltic Sea conference on Computing education research: Koli Calling 2006},
		year = {2006},
		pages = {117--119},
		publisher = {ACM},
	}
