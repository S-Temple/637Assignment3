**SENG 637 - Dependability and Reliability of Software Systems**

**Lab. Report #3 – Code Coverage, Adequacy Criteria and Test Case Correlation**

| Group \#:6     |     |
| -------------- | --- |
| Student Names: |     |
| Sean Temple    |     |
|                |     |
|                |     |

(Note that some labs require individual reports while others require one report
for each group. Please see each lab document for details.)

# 1 Introduction

This assignment is similar to assignment 2 but with changed expectations and access to the source code allowing white box testing. After copying the tests from assignment 2 to the new codebase we tested for code coverage metrics before and after attempting to increase our code coverage. For one method from DataUtilities and one from Range we manually analyzed each generating data flow graphs and def-use information to determine what tests would be required for full coverage. Using mainly EclEmma we gathered reports on testing coverage for all tested methods in each class.

# 2 Manual data-flow coverage calculations for X and Y methods

## DataUtilities.calculateColumnTotal:

Using EclEmma. Complexity here refers to cyclomatic complexity paths covered.

Range

Constructor:
8/8 lines - 100% statement coverage
2/2 branches - 100% branch coverage
2/2 complexity - 100% complexity coverage

Note both upper/lower/length methods use bound checks that lower is not > upper. We tried to create conditions to run that code and see no way to update or change lower/upper without memory editing. None of the other methods edit upper or lower; they just instantiate a new Range so with the constructor stopping any lower > upper its impossible to create such conditions.
getLowerBound:
2/5 Lines- 40% statement coverage
1/2  branches - 50% branch coverage
1/2 complexity - 50% complexity coverage
	
getUpperBound:
2/5 Lines- 40% statement coverage
1/2 branches - 50% branch coverage
1/2 complexity - 50% complexity coverage

getLength:
2/5 Lines- 40% statement coverage
1/2  branches - 50% branch coverage
1/2 complexity - 50% complexity coverage

getCentralValue:
1/1 line- 100% statement coverage
No branches to cover - 100% branch coverage
1/1 complexity - 100% complexity coverage

Contains:
5/5 Lines- 100% statement coverage
6/8 branches, - 75% branch coverage
3/5 complexity - 60% complexity coverage

Constrain:
8/8 Lines- 100% statement coverage
5/6 branches - 83.33% branch coverage 
another weird case will have to look into.
3/4 complexity - 75% complexity coverage
	
Equals:
7/8 Lines- 87.5% statement coverage
5/6  branches - 83.33% branch coverage
3/4 complexity - 75% complexity coverage

Range Totals for intentionally covered methods:
30/40 Instructions = 75% Instruction Coverage
21/28 branch paths = 75% branch coverage
12/17 complexity = 70.59% complexity coverage

Figure1: Original Range statement(line) coverage


Figure2: Original Range branch Coverage


Figure 3: Original Range Complexity Coverage


Junit in combination with EclEmma provided all the information we needed.

DataUtilities
calculateColumnTotal 
9/12 Lines= 75% statement Coverage
5/8  branches = 62.5% branch coverage
3/5 complexity - 60% complexity coverage

calculateRowTotal
	9/12 Lines= 75% Instruction Coverage
	5/8  branches = 62.5% branch coverage
	3/5 complexity - 60% complexity coverage

getCumulativePercentages
	15/18 Lines= 83.3% Instruction Coverage
	7/12 branches = 58.3% branch coverage
	3/7 complexity - 42.9% complexity coverage

createNumberArray
	5/5 Lines= 100% Instruction Coverage
	2/2 branches fully covered = 100% branch coverage
	2/2 complexity - 100% complexity coverage

createNumberArray2D
	6/6 Lines= 100% Instruction Coverage
2/2 branches fully covered = 100% branch coverage
2/2 complexity - 100% complexity coverage

DataUtilities Totals for intentionally covered methods:
44/53 Instructions = 83.02% Instruction Coverage
21/32 branch paths = 65.63% branch coverage
13/21 complexity = 61.90% complexity coverage

	


Figure 4: Original Data Utilities Statement (Line) Coverage


Figure 5: Original Data Utilities Branch Coverage


Figure 6: Original Data Utilities Complexity Coverage

DataUtilities.calculateColumnTotal:

   public static double calculateColumnTotal(Values2D data, int column,
            int[] validRows) {
       ParamChecks.nullNotPermitted(data, "data");
       double total = 0.0;
       if (total > 0){
           total = 100;
       }
       int rowCount = data.getRowCount();
       for (int v = 0; v < validRows.length; v++) {
           int row = validRows[v];
           if (row < rowCount) {
               Number n = data.getValue(row, column);
               if (n != null) {
                   total += n.doubleValue();
               }
           }
       }
       return total;
   }


Data Flow Chart:



Def-use sets per statement:

ParamChecks.nullNotPermitted(data, "data");
Def: None (method call, no variables defined)
Use: data
double total = 0.0;
Def: total
Use: None
if (total > 0){ total = 100; }
Def: total (inside the block)
Use: total (condition)
int rowCount = data.getRowCount();
Def: rowCount
Use: data
for (int v = 0; v < validRows.length; v++) { ... }
Def: v (initialization)
Use: validRows (condition), v (condition, increment)
Inside the loop:
int row = validRows[v];
Def: row
Use: validRows, v
if (row < rowCount) { ... }
Def: None
Use: row, rowCount
Number n = data.getValue(row, column);
Def: n
Use: data, row, column
if (n != null) { total += n.doubleValue(); }
Def: total
Use: n, total

List All DU-Pairs Per Variable
data: Used in ParamChecks, getRowCount(), getValue()
total: Defined initially and possibly modified in the loop. Used in if condition and for accumulation.
rowCount: Defined before the loop, used within the loop condition.
v: Defined and used in the loop.
validRows: Used in loop condition and to get row.
row: Defined in the loop, used to get values.
n: Defined in the loop, used in the null check.
Coverage per Test Case
Each test case covers:
The definition and use of data through mock interactions.
The initialization and conditional modification of total.
The definition and use of rowCount through data.getRowCount().
The loop execution, affecting v, validRows, row, and the internal logic based on n.
Specific Coverages:
With Positive Values: Covers the definition and use of all variables due to loop execution and conditions being met for positive value accumulation.
With Null Input: Directly covers the null check on data.
With Negative Values, Mixed Values, Zero Values, Large Dataset, No Rows: Each of these tests covers various aspects of the loop and conditionals, specifically the handling of different value types and quantities.


## Range.contains:

    1 public boolean contains(double value) {
    2       if (value < this.lower) {
    3           return false;
    4       }
    5       if (value  > this.upper) {
    6           return false;
    7       }
    8       return (value >= this.lower && value <= this.upper);
    9 }

### Data Flow Graph:

![DFG](media/RangeDataFlowDiagram.png)

### def-use sets per statement/line:

line: statement

1: public boolean contains(double value) {

    Def: value
    Use: None

2: if (value < this.lower) {

    Def: None
    Use: value
3: return false;

    Def: None
    Use: value
5:if (value  > this.upper) {

    Def: None
    Use: value, this.upper
6: return false;

    Def: None
    Use: value
8: return (value >= this.lower && value <= this.upper);

    Def: None
    Use: value, this.lower

list all DU-pairs per variable:

| Variable | Def in node | DCU | DPU |
|----------|-------------|-----|-----|
| value    |1| {1} |{(2,3),(2,4),(3,5),(3,6)}|


CU = 1, PU = 4

Test Cases and Covered DU-Pairs:

TC1:value<lower

TC2:value>upper

TC3:lower<value<upper

Other infeasible as it is impossible to have Range with lower > upper. Making line 8 the same as: return true; 
Good defensive programming to write line 8 the way it is though.

As such it's impossible to improve coverage.


# 3 A detailed description of the testing strategy for the new unit test

Text…

# 4 A high level description of five selected test cases you have designed using coverage information, and how they have increased code coverage

Text…

# 5 A detailed report of the coverage achieved of each class and method (a screen shot from the code cover results in green and red color would suffice)


![Line Coverage](/media/RangeLineCoverage.png)

### Instruction Coverage
![Instruction coverage](/media/RangeInstrucitonCoverage.png)
Details of the tested methods with less than 100% coverage:

92.9% coverage of contains method:

    public boolean contains (double value) {
        if (value < this.lower) {
            return false;
        }
        if (value  > this.upper) {
            return false;
        }
        return (value >= this.lower && value <= this.upper);
    }

The last 7.1% is from the last return statement only ever being evaluated to true as false can only occur if the lower bound is greater then the upper. lower will never be higher then upper as there are no setter methods and the constructor prevents a Range object from being created with lower higher then upper.

52.2% coverange of getLength

45% of getUpperBound and getLowerBound

all three of these methods have the same inacessable code for the same reason as the contains method:

    if (lower > upper) {
        String msg = "Range(double, double): require lower (" + lower + ") <= upper (" + upper + ").";
            throw new IllegalArgumentException(msg);
    }

In summary the only reason the tests don't have 100% coverage is because the constructor prevents lower > upper and there is no way to access the private members to create such conditions without editing values in memory or some other kind of memory corruption.

![Branch Coverage](/media/RangeBranchCoverage.png)

# 6 Pros and Cons of coverage tools used and Metrics you report

Text…

# 7 A comparison on the advantages and disadvantages of requirements-based test generation and coverage-based test generation.

Text…

# 8 A discussion on how the team work/effort was divided and managed

Text…

# 9 Any difficulties encountered, challenges overcome, and lessons learned from performing the lab

Importing libraries cause a varity of errors for team members. Hours of troubleshooting were required to get eclipse, imported librarys, imported tests and EclEmma to work. 

# 10 Comments/feedback on the lab itself

Text…


## 5 Evaluation Criteria
5.1 JUnit Test Suite (50%)
The test suite will be required to be submitted along with the lab report. Students will be graded on their unit tests. The grading criteria are as follows.

Marking Scheme	
Code coverage: lesser coverage than coverage target specified in lab instructions above, would decrement your mark proportionally, unless you explain it by a valid the reason (e.g., infeasible path)	20%
Clarity (are they easy to follow, through commenting or style, etc.?)	15%
Correctness (do the tests actually test what they are intended to test?)	15%

5.2 Lab Report (50%)
Students will be required to submit a report on their work in the assignment as a group. To be consistent, please use the template markdown file (seng637-a3-team_number.md) provided online under the Assignment 3 folder. If desired, feel free to rename the sections, as long as the headings are still descriptive and accurate. In the report should be included.

Marking Scheme	
Manual data-flow coverage calculations the two mentioned methods	15%
A detailed description of the testing strategy for the new unit tests	5%
A high level description of five selected test cases you have designed using coverage information, and how they have increased code coverage	10%
A detailed report of the coverage achieved of each class and method (a screen shot from the code cover results in green and red color would suffice)	5%
Pros and cons of the coverage tools tried by your group in this assignment, in terms of reported measures, integration with the IDE and other plug-ins, user friendliness, crashes, etc.	5%
A comparison on the advantages and disadvantages of requirements-based test generation and coverage-based test generation.	5%
A discussion on how the team work/effort was divided and managed	2%
Any difficulties encountered, challenges overcome, and lessons learned from performing the assignment	2%
Comments/feedback on the assignment itself	1%
A portion of the grade for the lab report will be allocated to organization and clarity.
