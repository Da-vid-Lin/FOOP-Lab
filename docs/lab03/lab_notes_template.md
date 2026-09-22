# Lab 3 Notes – Performance Evaluation and Stat Summary

Use and vary this template as required. Your notes are formative working
material rather than a separately marked report, but selected evidence may
feed into later coursework and its mini-viva.

**Name:**  
**Student ID:**  
**Date:**

---

## 1. Arithmetic Speed Test

### 1.1 Rough Estimate
- **Estimated time per increment:**  
  (Based on CPU FLOPS / clock speed)

### 1.2 Measured Results
| Run | Elapsed Time (ns) |
|-----|--------------------|
| 1   |                    |
| 2   |                    |
| 3   |                    |

- **Variation observed:**  
  ...

- **Comparison with estimate:**  
  ...

---

## 2. Average Speed Test

**nReps used:**  
...

| Experiment variant | Prediction | Observed time | Explanation |
|---|---|---:|---|
| Baseline (`x++`) | | | |
| Repetitions divided by 10 | | | |
| Empty loop body | | | |
| Random-number operation | | | |

- **Observations / explanations:**  
  ...

---

## 3. List Append Performance

### 3.1 Experimental Setup
- **Lists tested:**
  - [ ] IntArrayList
  - [ ] IntLinkedList
  - [ ] GenericArrayList
  - [ ] GenericLinkedList
  - [ ] GenericLinkedListRecord

- **Range of n:** ...
- **Warm-up count:** ...
- **Trials per n:** ...
- **Quantity measured by one trial:** ...
- **Summary statistic:** ...

### 3.2 Results

Attach your raw data or summarised tables here.

## 4. Kplotlib output

- Plot file (SVG or PNG):
- Axis labels and units:
- Warm-up count:
- Trials per point:
- Summary statistic and uncertainty shown:
- Command used to regenerate the plot:

## 5. RunningSummary

- State retained by the implementation:
- Why each single-value update is O(1):
- Numerically stable variance method used:
- Behaviour with insufficient data:

## 6. Conclusions

- Which observations support or contradict your complexity predictions?
- What are the most important limitations of this experiment?
