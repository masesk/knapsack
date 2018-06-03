# [Knapsack/Knapsack Solver](http://www.masesk.com)

This program was created to solve knapsack problems.<br>
## Features

* The executable solver a knapsack problem based on a text file
* The implementation of the solver is available in the Java files.
* The Java implementation uses terminal input for knapsack size to also solve the problem.



## Getting Started


#### Using Knapsack Solver

(1) Create a file of format ***txt*** and in it, type desired weight, a space, and desired profit of that weight<br>
Drop new line and type other weights and profits. Use only ***integers*** Example:

```bash
2 3
1 4
4 8
3 5
```
(2) To run on the implementation Java on a user prompt IDE, make sure to save the text file as ***input.txt*** otherwise,

Run ***Knapsack Solver.exe*** and open desired text file in the correct format described in section ***(1)***.

Enter knapsack capacity in textbox at the top, and press ***Find Knapsack***. Results for list
```bash
2 3
1 4
4 8
3 5
```
and Knapsack capacity of 6 yield the following answer:


```bash
0/1 Knapsack Maximum Profit: 12

Object #1 with weight 2 and profit 3
Object #2 with weight 1 and profit 4
Object #3 with weight 4 and profit 8
Object #4 with weight 3 and profit 5

Objects used are: 
Object #3
Object #2


Fractional Knapsack Maximum Profit: 13.666666666666666

Object #1 with weight 2 and profit 3
Object #2 with weight 3 and profit 5
Object #3 with weight 4 and profit 8
Object #4 with weight 1 and profit 4

Objects used are: 
Object #4
Object #3
Object #2
```

## Licence

Source code can be found on [github](https://github.com/masesk/lafoodie). <br>
[![Screen Shot](https://licensebuttons.net/l/by/4.0/88x31.png)This work is licensed under a Creative Commons Attribution 4.0 International License.](https://creativecommons.org/licenses/by/4.0/)

Developed by [Mases Krikorian](http://masesk.com)

    
