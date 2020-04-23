@echo off
cat ./Workbook2.csv | java -jar KaijieSolution.jar csv html > csv.html.txt
cat ./Workbook2.prn | java -jar KaijieSolution.jar prn html > printer.html.txt
diff csv.html.txt printer.html.txt

cat ./Workbook2.csv | java -jar KaijieSolution.jar csv json > csv.json.txt
cat ./Workbook2.prn | java -jar KaijieSolution.jar prn json > printer.json.txt
diff csv.json.txt printer.json.txt