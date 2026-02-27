### What Are Race Conditions ?
A race condition occurs when multiple threads access and modify shared state 
concurrently, and the outcomes depends on the timing or interleaving of their execution.
This often leads to inconsistent or incorrect results, which can be hard to reproduce
and debug.

### Key Characteristics
* <b>Time Dependency</b>
The issue occurs only under certain execution orders

* <b>Shared Resources</b>
The problem arises due to asynchronized access to shared variables or objects 

### How to Identify Race Conditions ?
* <b>Code Review</b>
Carefully review your code for shared variables and identify sections where multiple threads
might read or write simultaneously

* <b>Using Logs</b>
Insert logging statements in critical sections to track thread execution
identify overlaps in shared data access

### Fixing Race Conditions
* Using Synchronization
* Using Lock from java.util.concurrent
* Using Atomic Variables