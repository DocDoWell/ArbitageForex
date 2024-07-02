# ArbitageForex

Using Bellman ford to look for negative cycles in a graph and predict forex gains (WIP)
Implementation of theory based here - https://www.ijisrt.com/assets/upload/files/IJISRT20MAY047.pdf

There is no unit testing in this PoC. There are 2 inputs. For the input "arbitage.csv", we expect to see:

USD ---> EUR ---> JPY ---> USD 

with a gain of 1.5131039999998848 from 1000 units.

For the input "no_arbitage.csv" there should be no arbitage found.

