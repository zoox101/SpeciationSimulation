#Will Booker

df = c(
  "data/idom/pseudo/Output1.csv",
  "data/idom/pseudo/Output49.csv",
  "data/idom/pseudo/Output97.csv",
  "data/idom/pseudo/Output146.csv",
  "data/idom/meiosis/Output3.csv",
  "data/idom/meiosis/Output49.csv",
  "data/idom/meiosis/Output98.csv",
  "data/idom/meiosis/Output147.csv",
  "data/cdom/meiosis/Output1.csv",
  "data/cdom/meiosis/Output50.csv",
  "data/cdom/meiosis/Output98.csv",
  "data/cdom/meiosis/Output145.csv"
)

x = rbind(plot(read.table(df[1], header=TRUE, sep=","), LT, y="BSAM", x="Non-Meiotic - Incomplete Dominance"), 
          plot(read.table(df[2], header=TRUE, sep=","), LT, rmx, y="BSRM"),
          plot(read.table(df[3], header=TRUE, sep=","), LT, rmx, y="USAM"),
          plot(read.table(df[4], header=TRUE, sep=","), LB, y="USRM", x="Generation"))

y = rbind(plot(read.table(df[5], header=TRUE, sep=","), LT, rmy, x="Meiotic - Incomplete Dominance"), 
          plot(read.table(df[6], header=TRUE, sep=","), LT, rma),
          plot(read.table(df[7], header=TRUE, sep=","), LT, rma),
          plot(read.table(df[8], header=TRUE, sep=","), LB, rmy, x="Generation"))

z = rbind(plot(read.table(df[9], header=TRUE, sep=","), RT, x="Meiotic - Complete Dominance", y="Beak Size"), 
          plot(read.table(df[10], header=TRUE, sep=","), RT, rmx, y="Beak Size"),
          plot(read.table(df[11], header=TRUE, sep=","), RT, rmx, y="Beak Size"),
          plot(read.table(df[12], header=TRUE, sep=","), RB, x="Generation", y="Beak Size"))

full = cbind(x, y, z)
grid.newpage(); grid.draw(rbind(full, size = "last"))
