#Will Booker

df = c(
  "wide/Output4.csv",
  "narrow/Output1.csv",
  "wide/Output50.csv",
  "narrow/Output49.csv",
  "wide/Output97.csv",
  "narrow/Output119.csv"
)

x = rbind(plot(read.table(df[1], header=TRUE, sep=","), LT, y="Wide", x="Incomplete Dominance Structure"), 
          plot(read.table(df[2], header=TRUE, sep=","), LB, y="Narrow", x="Generation"))

y = rbind(plot(read.table(df[3], header=TRUE, sep=","), LT, rmy, x="Meiosis - Incomplete Dominance"), 
          plot(read.table(df[4], header=TRUE, sep=","), LB, rmy, x="Generation"))

z = rbind(plot(read.table(df[5], header=TRUE, sep=","), RT, x="Meiosis - Complete Dominance", y="Beak Size"), 
          plot(read.table(df[6], header=TRUE, sep=","), RB, x="Generation", y="Beak Size"))

full = cbind(x, y, z)
grid.newpage(); grid.draw(rbind(full, size = "last"))
