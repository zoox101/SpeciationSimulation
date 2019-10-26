#Standard imports
import os, re, sys, pickle
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

import matplotlib as mpl

FILE_NAME = 'saves/Output3.csv'

#Setting font sizes
mpl.rcParams['font.size'] = 20.0

#Loading the file
df = pd.read_csv(FILE_NAME)

#Initializing the plot
plt.ylabel('Beak Size'); plt.xlabel('Generation')
plt.ylim(0, 10); plt.xlim(0, 1000)

#Plotting the scatter plot
plt.scatter(df['Generation'], df['BeakSize'], color='RoyalBlue', s=1)
plt.show()
