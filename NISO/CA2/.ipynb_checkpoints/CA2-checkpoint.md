# NISO CA 2: Genetic Programming



## Data

In this competition, the fifth iteration, you will use hierarchical sales data from Walmart, the world’s largest company by revenue, to forecast daily sales for the next 28 days. The data, covers stores in three US States (California, Texas, and Wisconsin) and includes item level, department, product categories, and store details. In addition, it has explanatory variables such as price, promotions, day of the week, and special events. Together, this robust dataset can be used to improve forecasting accuracy.

- `calendar.csv` - Contains information about the **dates** on which the products are sold.
- `sales_train_validation.csv` - Contains the historical daily unit **sales data** per product and store `[d_1 - d_1913]`
- `sell_prices.csv` - Contains information about the **price** of the products sold per store and date.

For the CA2, data is expected to combine historical price with a label of current price. 
- The 'calendar.csv' file includes all information about the dates. The extra data are indexed by the date, including discount info.
- The 'sales_train_validation.csv' file contains the main time series data of product sales. The columns include the amount of sales of each item throught out 1913 days. The id is concatenated by the info of item, department, category, store and city. As described, all the data come from 10 Walmart stores in California, Texas and Wisconsin. 
- The 'sell_prices.csv' file consists of 3049 items and their daily historical prices in different places. The time span is 520 days. 

After investigation, the sales data are eligible for the prediction task.


### Exercise 1

​	Implement a routine to parse and evaluate expressions. 

- Input: -n 1 -x "1.0" -expr "(mul (add 1 2) (log 8))"
- Output: 9.0

1. The expression was parsed in as a nested list..
2. First place all the elements in a binary expression tree.
3. Then use post-fix traverse to evaluate the expression.
4. Fix the issue of input $\neq$ 3.
5. Fix the issue of multi-dimensional input.

