# Read PRN/CSV files and Write to HTML/JSON
Reads in printer (PRN) and excel (CSV) files and writes to HTML or JSON, based on user input.

## Instructions
Java JDK 1.8 must be installed, and set on the PATH.

To build the program, run "$buildProgram.bat" in the root directory.
This will create a manifest, as well as the executable (jar) in the root directory.

To run the program: "$cat ./Workbook2.csv | java -jar ReadWriteProgram.jar csv html > csv.html.txt", for example from root directory

Same thing can be done for prn, but on Windows, prn is a reserved name. So instead I used printer.html.txt as the output
file. So something like this:
"$cat ./Workbook2.prn | java -jar ReadWriteProgram.jar prn html > printer.html.txt"

Alternatively, you can run the testProgram.bat script to test the program in all scenarios, as well as compare the diff.


## Nice to haves for the future:
Include Apache Commons to force delete output files before writing to them, in case they are open by the user
Find third party library for parsing .prn files
