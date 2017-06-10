
data = read.table(file.choose(), header=TRUE, sep=",")

max = c(); min = c()
for(i in 1:nrow(data)) {
  gen = data['Generation'][i,] + 1
  max[gen] = 0; min[gen] = 100
}

for(i in 1:nrow(data)) {
  gen = data['Generation'][i,] + 1
  val = data['BeakSize'][i,]
  if(val > max[gen]) {max[gen] = val}
  if(val < min[gen]) {min[gen] = val}
}

Generation = 0:999
for(i in Generation) {
  max[i] = max[i] + 1
  min[i] = min[i] - 1
}

comb_limit = c(max, rev(min))
comb_gens = c(0:999, rev(0:999))
BeakSize = comb_limit
Generation = comb_gens
beak_limits = data.frame(Generation, BeakSize)

BeakSize = c(1,1,10,10)
Generation = c(0,999,999,0)
seed_limits = data.frame(Generation, BeakSize)

#colors = c('#000000', '#737373', '#f0f0f0')
#colors = c('#081d58', '#225ea8', '#ffffd9')
#colors = c('#08306b', '#2171b5', '#f7fbff')
#colors = c('#084081', '#4eb3d3', '#e0f3db')

#colors = c('#08306b', '#6baed6', '#deebf7')
#colors = c('#004529', '#238443', '#f0f0f0')
#colors = c('#004529', '#238443', '#f7fcb9')
#colors = c('#542788', '#f46d43', '#f0f0f0')

#colors = c('#08306b', '#6baed6', '#f0f0f0')
#colors = c('DarkBlue', '#6baed6', '#d9d9d9')
colors = c('Population'='DarkBlue', 'Effective Range'='#6baed6', 'Wasted Resources'='#f0f0f0')
colors = c('DarkBlue', '#6baed6', '#f0f0f0')



ggplot(data=data, aes(x=Generation)) + 
  geom_point(aes(y=BeakSize, color=colors[1]), size=0.01) +
  scale_colour_manual(name="Error Bars", values=colors)
p = ggplotGrob(plot)
grid.newpage(); grid.draw(p)

ggplot(data=data, aes(x=Generation)) +
  theme_bw() + 
  scale_y_continuous(breaks = 2*(0:5), limits = c(1,10)) + 
  scale_x_continuous(limits = c(0, 1000)) + 
  ggtitle("Wasted Resources") + 
  theme(plot.title=element_text(hjust=0.5)) + 
  geom_polygon(data=seed_limits, aes(y=BeakSize, fill='Wasted Resources')) + 
  geom_polygon(data=beak_limits, aes(y=BeakSize, fill='Effective Range')) + 
  geom_point(aes(y=BeakSize, color='Population'), size=0.01) + 
  scale_colour_manual(name="Key", values=colors)



#Stable

plot = ggplot(NULL, aes(x=Generation, y=BeakSize))
plot = plot + theme_bw()
plot = plot + scale_y_continuous(breaks = 2*(0:5), limits = c(1,10))
plot = plot + scale_x_continuous(limits = c(0, 1000))
plot = plot + geom_polygon(data=seed_limits, fill=colors[3])
plot = plot + geom_polygon(data=beak_limits, fill=colors[2])
plot = plot + geom_point(data=data, color=colors[1], size=0.01)
plot = plot + ggtitle("Wasted Resources")
plot = plot + theme(plot.title=element_text(hjust=0.5))
p = ggplotGrob(plot)

grid.newpage(); grid.draw(p)
