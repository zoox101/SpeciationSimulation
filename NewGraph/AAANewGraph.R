library(ggplot2)
library(grid)
library(gridExtra)
library(lattice)

rmx = theme(axis.title.x=element_blank(),
            axis.text.x=element_blank(),
            axis.ticks.x=element_blank())

rmy = theme(axis.title.y=element_blank(),
            axis.text.y=element_blank(),
            axis.ticks.y=element_blank())

rma = rmx + rmy

myTheme = function(side) {
  return(ggplot(NULL, aes(x=Generation, y=BeakSize)) +
  theme(legend.position="none") + theme_bw() +
  scale_y_continuous(breaks = 2*(0:5), limits = c(0,11), position=side[2]) + 
  scale_x_continuous(position=side[1]))
}

plot = function(data, axis, arg=NULL, x=NULL, y=NULL) {
  myplot = myTheme(axis) + geom_point(data=data, color="DarkBlue", size=0.01) 
  if(!is.null(x)) {myplot = myplot + xlab(x)}
  if(!is.null(y)) {myplot = myplot + ylab(y)}
  if(is.vector(arg)) {for(i in arg) {myplot = myplot + i}}
  else if(!is.null(arg)) {myplot = myplot + arg}
  return(ggplotGrob(myplot))
}

LB = c("bottom", "left")
RB = c("bottom", "right")
LT = c("top", "left")
RT = c("top", "right")
