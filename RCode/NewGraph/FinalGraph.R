library(ggplot2)

#Plotting function
plot = function(filename, xlim=1000) {
  data = read.table(filename, header=TRUE, sep=",")
  g = ggplot(data, aes(x=data$Generation, y=data$BeakSize))
  g = g + geom_point(data=data, color="DarkBlue", size=0.01)
  g = g + scale_y_continuous(breaks = 2*(0:5), limits = c(0,11))
  g = g + scale_x_continuous(limits = c(0,xlim))
  g = g + xlab("Generation") + ylab("Beak Size")
  g = g + theme_light()
  g = g + theme(text = element_text(size=42))
  return(g)
}

#Plots and saves all graphs
plot_all = function(vector, name) {
  for(i in 1:length(vector)) {
    filename = vector[i]
    g = plot(filename)
    savename = paste("graphs", .Platform$file.sep, name, "_", i, ".eps", sep="")
    ggsave(savename, width=16, height=12, device="eps")
  }
}

#Graphs to plot

#Comp blending and meiosis
graph1 = 
  c("data/avg/Output2.csv",
    "data/avg/Output55.csv",
    "data/avg/Output97.csv",
    "data/avg/Output145.csv",
    "data/idom/pseudo/Output1.csv",
    "data/idom/pseudo/Output49.csv",
    "data/idom/pseudo/Output97.csv",
    "data/idom/pseudo/Output146.csv",
    "data/cdom/pseudo/Output3.csv",
    "data/cdom/pseudo/Output55.csv",
    "data/cdom/pseudo/Output97.csv",
    "data/cdom/pseudo/Output146.csv")

#No mutation
graph2 = 
  c("data/nomutation/Output1.csv",
    "data/nomutation/Output53.csv")

#Comp wide and narrow
graph3 = 
  c("data/wide/Output4.csv",
    "data/wide/Output50.csv",
    "data/wide/Output97.csv",
    "data/narrow/Output1.csv",
    "data/narrow/Output49.csv",
    "data/narrow/Output119.csv")

#Control
graph4 = 
  c("data/idom/pseudo/Output1.csv",
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

plot_all(graph1, "graph1")
plot_all(graph3, "graph3")
plot_all(graph4, "graph4")

#Plotting graph 2 with the altered limits
name = "graph2"
g = plot(graph2[1], xlim=120)
savename1 = paste("graphs", .Platform$file.sep, name, "_", 1, ".eps", sep="")
ggsave(savename1, width=16, height=12, device="eps")
g = plot(graph2[2], xlim=120)
savename2 = paste("graphs", .Platform$file.sep, name, "_", 2, ".eps", sep="")
ggsave(savename2, width=16, height=12, device="eps")

plot("data/cdom/meiosis/Output145.csv")

