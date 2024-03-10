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

Text…

# 2 Manual data-flow coverage calculations for X and Y methods

DataUtilities.calculateColumnTotal:



Range.contains:
1 public boolean contains(double value) {
2       if (value < this.lower) {
3           return false;
4       }
5       if (value  > this.upper) {
6           return false;
7       }
8       return (value >= this.lower && value <= this.upper);
9   }
Data Flow Chart:

Here are the def-use sets per statement:
	line:statement
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
Variable
Def in node
DCU
DPU
value
1
{1}
{(2,3),(2,4),(3,5),(3,6)}

CU = 1, PU = 4

Test Cases and Covered DU-Pairs:

TC1:value<lower

TC2:value>upper

TC3:lower<value<upper

Other infeasible as impossible to have Range with lower > upper. Making line 8 the same as: return true; 
Good defensive programming to write line 8 the way it is though.

As such it's impossible to improve coverage.


# 3 A detailed description of the testing strategy for the new unit test

Text…

# 4 A high level description of five selected test cases you have designed using coverage information, and how they have increased code coverage

Text…

# 5 A detailed report of the coverage achieved of each class and method (a screen shot from the code cover results in green and red color would suffice)

![instruciton coverage](InstructionCoverage.png)

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

In summary the only reason the tests don't have 100% coverage is because the constructor prevents lower > upper and there is no way to access the private members to create such conditions.

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