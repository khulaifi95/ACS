# Evaluation Methods & Statistics



## Lecture 2: Experiment Design



### 1. Stroop experiment

- Task: spit out the colour of words.
  1. Congruent: word name $=$ ink colour
  2. Incongruent: word name $\neq$ ink colour

- Theory/ Assumptions:

  1. Top-down control of human information processing is limited.

  2. Humans do not seem to capable of *switching off* word reading.

  3. Words can be read **more** **quickly** than colours can be named.

- Features of the frequency plots:

  1. There are more values in the middle than at the extremes.
  2. The data are **noisy**. The curves are not perfectly smooth.
  3. The plots are **skewed**. The distributions have a long-tail to the right.
  4. Plots for some participants have **outliers**.

  - Need **more data**.



| <img src="/Users/kevinxu95/ACS/EMS/Notes/EMS_Lecture 2.assets/Screenshot 2020-05-30 at 14.48.48.png" style="zoom:33%;" /> |
| :----------------------------------------------------------: |
|     **Fig 1.** Frequency plots of the Stroop experiment      |



### 2. Hypothesis definition

- Statistical hypothesis is **testable/falsifiable** on the basis of observing a process. 

- Commonly, **two** statistical data sets are compared, or a data set obtained by sampling is compared against a synthetic data set from an idealized model.

- We need to test the hypothesis and propose new theory:

  - **Null** hypothesis: 

    There's no relationship between these two data-sets.

  - **Alternative** hypothesis: 

    Propose there's a statistical-relationship between the two data-sets.



### 3. Hypothesis testing

A statistical hypothesis test is a method of **statistical inference**.

- Absence of evidence is not evidence of absence.
- We can only reject a hypothesis that states a theory about $\forall$ not $\exist$.
- **Statistical testing** can help us reject the null hypothesis.



**Example:**

The results indicated significant correlation between Facebook use and social capital, r(267) = .29, **p<0.001**.

- It states that the likelihood of accepting the null hypothesis is less that 1 in 1000, so we can reject it and accept the alternative hypothesis.



### 3. False claims and errors

- Type I error: *false positive*
  - We accepted the alternative hypothesis when it is false.
  - Many statistics tests are designed to minimise this error.
  - It defines the **significance level** $\alpha$ ($5\%$) that the experimenter will accept.
- Type II error: *false negative*
  - We accepted the null hypothesis when it is false (fail to reject).
  - The probability of a Type II error is defined as $\beta$.
  - The probability of **correctly rejecting** a false null hypothesis is defined as $1-\beta$, which called **Power**.



[^significance level]: A result has statistical **significance** when it is very unlikely to have occurred given the **null** hypothesis. More precisely, a study's defined **significance level**, denoted by $\alpha$, is the probability of the study **rejecting** the null hypothesis, given that the null hypothesis were assumed to be true.



### 4. Experiment design

- **Independent** variables define the **conditions** of the experiment.
  - In Stroop task, congruent and *incongruent* colour & word.
- **Dependent** variables defines what is **measured**.
  - In Stroop task, the dependent variable is *reaction time*.
- **Confounding** variables define what *might* affect the results of the experiment is not taken into account.
  - Including the *order* of tests or characteristics of people in the experiment.
- **Variation** in dependent variables:
  - Systematic: due to change in Independent variables.
  - Unsystematic: due to confounding variables.



| Experiment design for Stroop task                            |
| :----------------------------------------------------------- |
| **Hypothesis:** Reaction time to congruent words will be faster than reaction time to incongruent words. |
| **Independent Variable:**  Congruent Words (colour of ink = name of word), Incongruent Words (colour of ink $\neq$ name of word). |
| **Control Condition: **Congruent Words  \|  **Experimental Condition:** Incongruent Words |
| **Dependent Variable(s):** Reaction Time                     |
| **Task:** Participants will be asked to read, as quickly as possible, single words on a display. The words will be the names of colours and will be presented either in the same colour as the word’s name or in a different colour. |
| **Confounding Variables:** Performance could be affected by ability to perceive colour (‘colour-blindedness’) and knowledge of the names of colour (‘language skills’). |



### 5. Types of study design

1. **Post-test**

Control versus experimental group complete task. Outcome is measured and compared.

2. **Pretest-Post-test**

Apart from the priliminary test, experimental group treatment and both groups tested again.

3. **Solomon Four Group**

2 control groups and 2 experimental groups; pretest-post-test and post-test only.

4. **Factorial Design**

2 or more independent variables manipulated on all possible combinations.

5. **Crossover Design**

Participants randomly allocated to perform both control and experimental conditions complete task. Outcome is measured and compared



### 6. Participants differences

Sometimes unsystematic variation of dependent variables come from the difference in participants. 

1. Between subjects:

- The measurement of DV can be affected by individual differences.
  - Match participants on key characteristics.
  - Random allocation to condition.

2. Within subjects:

- Performance of one participant can be affected by repeated practice or order of stimuli.
  - Modify order of stimuli.
- Participants may grow boredom or fatigue durint the task.
  - Design in-between breaks.
- Participants can suffer from asymmetric transfers.
  - Counter-balance conditions.



### 7. Balanced latin squares

We want to reduce the **confound of order** effects, by assigning participants to conditions. 

- But if every participant does the **same** tasks in the **same** order, how do we know that they are not simply getting better with practice?
- We randomise the order in which people complete the tasks.

For multiple conditions, we need to ensure that:

1. Each condition appears once in each row and column.
2. Each condition **follows** each other condition.



| Group | Round 1 | Round 2 | Round 3 | Round 4 |
| ----- | ------- | ------- | ------- | ------- |
| 1     | A       | B       | C       | D       |
| 2     | B       | C       | D       | A       |
| 3     | C       | D       | A       | B       |
| 4     | D       | A       | B       | C       |

**Note:** For four conditions, we need multiples of 4 groups.

