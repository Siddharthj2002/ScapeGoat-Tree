# ScapeGoat-Tree

● Overview:

The project focuses on the implementation of simpler, but still
reasonably effective form of self-balancing tree: the scapegoat tree.
The project consists of a codebase for binary search trees along with the implementation of several 
additional methods for binary search trees. 

  _________________________________


● Project Goals:


  -> Demonstrate an understanding of binary trees, sufficient to implement several non-trivial
     methods.
  
  -> Apply forethought to determine if iterative or recursive methods are more straightforward
     in various cases.
  
  -> Learn and implement a simple extension to binary search trees based on a textual
     description of the extension.
  
  -> Experience debugging and testing code.
  
  _________________________________
  
  
● Project Background:

  Just obeying the BST rule is not sufficient to achieve O(log n) performance when accessing a
  binary search tree. The tree must be balanced, or close to it. But, the basic implementation of
  a balance method is likely at least O(n). Therefore it does not make sense to call balance before or after
  each method, as it will make all methods asymptotically slower than O(log n), negating the
  performance advantage that motivated binary search trees in the first place!
  The solution to this paradox is to build a self-balancing binary tree, that is, a tree that maintains
  some invariant across calls to add or remove. This invariant enforces that the tree remains
  approximately balanced, and does so at an amortized low cost.
  
  _________________________________
  
  
  ● Code Structure:
  
  This project contains the following important folders:
  
  -> src: This is the source folder which consists of all the important files and code related to this project.
  
  -> src/test: This is the package folder containing all of the public unit tests.
  
  -> lib: This is where you can find libraries that are included with the project. At the very
          least you will find two jar files that are used to run the JUnit test framework.
          
   _________________________________
  
  
● Contributing:

  If you want to contribute to this project and make it better with new ideas, your pull request is very welcomed. 
  If you find any issue just put it in the repository issue section, thank you.
