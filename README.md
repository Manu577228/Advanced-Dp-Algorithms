<div align="center">
  <img src="https://avatars.githubusercontent.com/u/102406248?v=4" width="120" height="120" style="border-radius: 50%;" alt="code-with-Bharadwaj"/>
  
  <h1>⚡ Advanced DP Optimization Algorithms</h1>

  <p><strong>A deep-dive into the most powerful Dynamic Programming optimization techniques</strong><br/>
  As explored on <a href="https://www.youtube.com/@code-with-Bharadwaj">@code-with-Bharadwaj</a></p>

  <p>
    <img src="https://img.shields.io/badge/Language-Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
    <img src="https://img.shields.io/badge/Topic-Dynamic%20Programming-6C63FF?style=for-the-badge"/>
    <img src="https://img.shields.io/badge/Level-Advanced-FF4C4C?style=for-the-badge"/>
    <img src="https://img.shields.io/badge/YouTube-@code--with--Bharadwaj-FF0000?style=for-the-badge&logo=youtube&logoColor=white"/>
  </p>
</div>

---

## 🎯 What Is This Repository?

This repository is the **official code companion** to the YouTube series by [@code-with-Bharadwaj](https://www.youtube.com/@code-with-Bharadwaj), covering **advanced Dynamic Programming optimization algorithms** that are widely used in competitive programming and system design.

These are not your everyday DP problems — these are the **techniques that separate good programmers from great ones.** Each algorithm here is a tool to reduce time complexity from `O(n³)` to `O(n²)` or even `O(n log n)` when the right conditions are met.

---

## 📁 Repository Structure

```
📦 DP-Optimization-Algorithms
 ┣ 📂 BitsetDPAlgorithm
 ┣ 📂 BrokenProfileDPAlgorithm
 ┣ 📂 DPSMAWKAlgorithm
 ┣ 📂 DivideAndConquerDPAlgorithm
 ┣ 📂 KnuthOptimizationAlgorithm
 ┣ 📂 LagrangianRelaxationDPAlgorithm
 ┣ 📂 MongeDPOptimizationAlgorithm
 ┣ 📂 QuadrangleInequalityDPOptimizationAlgorithm
 ┣ 📂 SubsetConvolutionAlgorithm
 ┣ 📂 SumOverSubsetsDPAlgorithm
 ┗ 📄 Main.java
```

---

## 🧠 Algorithms Covered

### 1. 🔢 Bitset DP Algorithm
Leverages **bitwise operations** to speed up DP transitions. By representing states as bits, operations that normally cost `O(n)` can run in `O(n/64)` using 64-bit integers.

- **Use case:** Subset problems, reachability, counting paths
- **Complexity gain:** `O(n²/w)` where `w = 64`

---

### 2. 🧩 Broken Profile DP Algorithm
A **profile dynamic programming** technique used for tiling problems on grids. The "broken profile" moves column-by-column (or row-by-row), encoding the current partial state.

- **Use case:** Grid tiling, domino problems, Hamiltonian paths on grids
- **Complexity:** `O(n × 2^m)` where `m` is the smaller grid dimension

---

### 3. ⚡ SMAWK Algorithm (DP Variant)
The **SMAWK algorithm** efficiently finds row minima in **totally monotone matrices**, enabling faster DP transitions.

- **Use case:** Optimal BST, matrix chain multiplication variants
- **Complexity gain:** Reduces `O(n²)` to `O(n)` for eligible problems

---

### 4. ➗ Divide and Conquer DP
Applicable when the **optimal split point** of a DP is **monotone**. Splits the problem recursively, exploiting the ordering of opt values.

- **Condition:** `opt(i, j) ≤ opt(i, j+1)`
- **Complexity gain:** `O(n²)` → `O(n log n)`

---

### 5. 🪢 Knuth's Optimization
A refinement for **interval DP** problems. When the cost function satisfies both the **quadrangle inequality** and **monotonicity**, the optimal split point is bounded.

- **Use case:** Optimal BST, Matrix Chain Multiplication
- **Complexity gain:** `O(n³)` → `O(n²)`

---

### 6. 🔁 Lagrangian Relaxation (Aliens Trick)
Also called the **"WQS Binary Search"** or **Aliens trick**. Converts a DP with a constraint "use exactly k items" into a penalized version, binary searching on the penalty.

- **Use case:** When a constraint like "exactly k operations" makes DP slow
- **Complexity gain:** `O(n × k)` → `O(n log n)`

---

### 7. 📐 Monge DP Optimization
Based on the **Monge matrix** property (a generalization of concavity for 2D cost arrays). Allows efficient computation of DP transitions.

- **Condition:** `C[a][c] + C[b][d] ≤ C[a][d] + C[b][c]` for `a ≤ b ≤ c ≤ d`
- **Complexity gain:** Used with SMAWK or Divide & Conquer for `O(n log n)`

---

### 8. 🔷 Quadrangle Inequality DP Optimization
The **quadrangle inequality** (or concave/convex SMAWK condition) enables monotone optimization of the optimal decision in interval DP.

- **Condition:** `w(a,c) + w(b,d) ≤ w(a,d) + w(b,c)` for `a ≤ b ≤ c ≤ d`
- **Complexity gain:** `O(n³)` → `O(n²)`

---

### 9. 🌀 Subset Convolution Algorithm
Computes the **convolution over subsets** — i.e., `h[S] = Σ f[T] × g[S\T]` for all `T ⊆ S`. Used in counting problems on subsets.

- **Use case:** Counting colorings, matchings, covers over subsets
- **Complexity:** `O(2ⁿ × n²)`

---

### 10. ➕ Sum Over Subsets (SOS) DP
Efficiently computes **sum of values over all subsets** of every mask. A fundamental building block for bitmask DP.

- **Formula:** `dp[mask] = Σ a[submask]` for all submasks of `mask`
- **Complexity:** `O(2ⁿ × n)` — far better than the naive `O(3ⁿ)`

---

## 🚀 Getting Started

### Prerequisites
- Java 11 or higher
- Any IDE (IntelliJ IDEA recommended) or terminal

### Clone & Run

```bash
# Clone the repository
git clone https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git

# Navigate to the project
cd YOUR_REPO_NAME

# Compile
javac Main.java

# Run
java Main
```

---

## 📊 Complexity Cheat Sheet

| Algorithm | Before Optimization | After Optimization | Key Condition |
|---|---|---|---|
| Divide & Conquer DP | O(n²) | O(n log n) | Monotone opt |
| Knuth's Optimization | O(n³) | O(n²) | Quadrangle inequality |
| Lagrangian Relaxation | O(n·k) | O(n log n) | Convexity in k |
| SMAWK | O(n²) | O(n) | Totally monotone matrix |
| SOS DP | O(3ⁿ) | O(2ⁿ · n) | All subsets |
| Subset Convolution | O(4ⁿ) | O(2ⁿ · n²) | Ranked zeta/Möbius |
| Bitset DP | O(n²) | O(n²/64) | Bitwise parallelism |

---

## 🎓 Who Is This For?

- 🏆 **Competitive programmers** aiming for Codeforces Div. 1 / ICPC level
- 💼 **Interview preppers** targeting FAANG+ companies
- 🎯 **CS students** studying algorithm design
- 📺 **Viewers** of [@code-with-Bharadwaj](https://www.youtube.com/@code-with-Bharadwaj) who want the code alongside the videos

---

## 📺 YouTube Channel

All these algorithms are explained visually with examples on the channel:

<div align="center">

[![YouTube](https://img.shields.io/badge/Watch%20on-YouTube-FF0000?style=for-the-badge&logo=youtube&logoColor=white)](https://www.youtube.com/@code-with-Bharadwaj)

**@code-with-Bharadwaj**  
*Making hard algorithms easy to understand* 🔥

</div>

---

## 🤝 Contributing

Contributions are welcome! If you find a bug, have a cleaner implementation, or want to add test cases:

1. Fork this repository
2. Create your feature branch: `git checkout -b feature/improve-knuth`
3. Commit your changes: `git commit -m 'Improve Knuth optimization with comments'`
4. Push to the branch: `git push origin feature/improve-knuth`
5. Open a Pull Request

---

## ⭐ Support

If this repository helped you — **star it** and **share it** with your fellow competitive programmers!

And don't forget to **subscribe** to [@code-with-Bharadwaj](https://www.youtube.com/@code-with-Bharadwaj) for more in-depth algorithm content. 🚀

---

<div align="center">
  <sub>Built with ❤️ by <a href="https://www.youtube.com/@code-with-Bharadwaj">@code-with-Bharadwaj</a> · Happy Coding! 💻</sub>
</div>
