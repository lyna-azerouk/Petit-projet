import csv
f = open('Histogramme.txt', 'r')
resultat_welzl = f.readline()
resultat_naif=f.readline()
f.close()
with open('stat.csv', 'w') as file:
    writer = csv.writer(file)
    writer.writerow(["Algo","temps_calcule"])
    writer.writerow(["welzl",int (resultat_welzl)])
    writer.writerow(["naif",int (resultat_naif)]) 
 