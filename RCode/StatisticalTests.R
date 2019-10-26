
#cinterval = function(hits, total=48, alpha=0.05) {
#  p = hits/total
#  q = 1 - p
#  mean = p * total
#  sd = sqrt(p*q*total)
#  ndev = qnorm(1-(alpha/2))
#  return(c(mean-sd*ndev, mean+sd*ndev))
#}

#Two Sample Binomial Test
tsbt = function(s1, s2, n1=48, n2=48) {
  p1 = s1/n1
  p2 = s2/n2
  p = (p1*n1 + p2*n2) / (n1+n2)
  z = (p1-p2) / sqrt(p * (1-p) * (1/n1 + 1/n2))
  x = min(pnorm(z), 1-pnorm(z))
  return(x)
}

#TAB 1 -- BSAM
tsbt(31,30); tsbt(30,25); tsbt(30,23)
tsbt(17,15); tsbt(15,23); tsbt(15,21)
tsbt(00,03); tsbt(03,00); tsbt(03,04)

#TAB 1 -- BSRM
tsbt(41,38); tsbt(38,35); tsbt(38,35)
tsbt(07,10); tsbt(10,13); tsbt(10,13)

#TAB 1 -- USAM
tsbt(35,37); tsbt(37,41); tsbt(37,41)
tsbt(13,11); tsbt(11,07); tsbt(11,07)

#TAB 1 -- USRM
tsbt(35,23); tsbt(23,24); tsbt(23,34)
tsbt(13,25); tsbt(25,24); tsbt(25,14)

#TAB 2 -- BSAM
tsbt(25,31); tsbt(23,20)
tsbt(23,15); tsbt(21,27)
tsbt(00,02); tsbt(04,01)

#TAB 2 -- BSRM
tsbt(00,01); tsbt(00,33)
tsbt(35,30); tsbt(35,14)
tsbt(13,17); tsbt(13,01)

#TAB 2 -- USAM
tsbt(41,37); tsbt(41,41)
tsbt(07,11); tsbt(07,07)

tsbt(24,44); tsbt(34,40)
tsbt(24,04); tsbt(14,08)
