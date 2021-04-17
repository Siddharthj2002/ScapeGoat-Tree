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

  Essentially, a medical triage is a system in which patients come and go and its behavior is similar to a
  processor executing tasks following a priority schedule. Patients will arrive one at a time with
  varying severity of conditions. The system will also provide the possibility of ordering by patient
  age instead of their condition. With a finite number of doctors and ER beds, the program must decide which 
  patients get treated in which order automatically. Naturally, we want the system to have the patients with 
  the more severe conditions be treated first. 

  To accomplish this, we will implement a priority queue using an array-based heap.
  The main interface and data structure in this program is a priority queue. Patients are enqueued
  when they arrive and dequeued as they are treated and released. The priority is on the patient’s
  condition, which has these levels in increasing order of priority: LIGHT, MILD, SEVERE,
  CRITICAL. Thus, all CRITICAL patients would be treated first, SEVERE patients second, etc. To
  offer more flexibility, this application can also prioritize patients by age.
  The priority queue is implemented with a heap in this application. 

  Each element in the tree will represent a specific patient
  with priority commensurate with the severity of the injury (or their age if that is the chosen
  priority). We want the patients with worse conditions to be treated first, so our heap invariant is:
  every child node will have less than or equal severity (priority) than its parent. Therefore, it can
  be inferred that the root node will always have the highest priority of any element in the heap.
  This describes a max heap. We must maintain the heap-invariant as patients arrive, enqueued
  onto the priority queue (added to the heap) and are treated, dequeued from the priority queue
  (removed from the heap). 

  Note :  In this program, the heap can also be configured to function
  as a min heap. In that case, the root has the lowest priority, and child nodes have priority less
  than or equal to their parent.

  While there are many ways to store a binary tree in memory, we opt for the space efficient
  array-based allocation. The array implementation also provides a simple way to navigate the
  heap by calculating parent and child indexes.
  
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
