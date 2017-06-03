#Will Booker

df = c(
  "avg/Output2.csv",
  "avg/Output55.csv",
  "avg/Output97.csv",
  "avg/Output145.csv",
  "idom/pseudo/Output1.csv",
  "idom/pseudo/Output49.csv",
  "idom/pseudo/Output97.csv",
  "idom/pseudo/Output146.csv",
  "cdom/pseudo/Output3.csv",
  "cdom/pseudo/Output55.csv",
  "cdom/pseudo/Output97.csv",
  "cdom/pseudo/Output146.csv"
)

x = rbind(plot(read.table(df[1], header=TRUE, sep=","), LT, y="BSAM", x="No Gene Structure"), 
          plot(read.table(df[2], header=TRUE, sep=","), LT, rmx, y="BSRM"),
          plot(read.table(df[3], header=TRUE, sep=","), LT, rmx, y="USAM"),
          plot(read.table(df[4], header=TRUE, sep=","), LB, y="USRM", x="Generation"))

y = rbind(plot(read.table(df[5], header=TRUE, sep=","), LT, rmy, x="Incomplete Dominance Structure"), 
          plot(read.table(df[6], header=TRUE, sep=","), LT, rma),
          plot(read.table(df[7], header=TRUE, sep=","), LT, rma),
          plot(read.table(df[8], header=TRUE, sep=","), LB, rmy, x="Generation"))

z = rbind(plot(read.table(df[9], header=TRUE, sep=","), RT, x="Complete Dominance Structure", y="Beak Size"), 
          plot(read.table(df[10], header=TRUE, sep=","), RT, rmx, y="Beak Size"),
          plot(read.table(df[11], header=TRUE, sep=","), RT, rmx, y="Beak Size"),
          plot(read.table(df[12], header=TRUE, sep=","), RB, x="Generation", y="Beak Size"))

full = cbind(x, y, z)
grid.newpage(); grid.draw(rbind(full, size = "last"))
