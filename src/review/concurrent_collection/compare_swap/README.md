### What is Compare and Swap (CAS) ?
Compare and Swap, commonly referred to as CAS, is an atomic operation supported at
the hardware level. It ensures that a shared variable is updated only if its current value
matches an expected value.

The key idea is to compare the value of a variable with an expected value. If they match
the variable is updated to a new value in atomic step.

#### CAS can be visualized as a simple formula
if(currentValue == expectedValue) {
updateValue(newValue);
}

#### Advantages of CAS
<b>Non-blocking:</b> Unlike traditional locks, CAS doesn't lock threads, instead, threads keep
retrying until the operation succeeds.

<b>Improved performance:</b> No context switching or thread suspension, making CAS ideal
for high-performance applications.

<b>Scalability:</b> CAS allows multiple threads to progress independently, avoiding bottlenecks

#### Challenges of CAS
* While CAS eliminates locking overhead, it has its own challenges, such as spinning under high
  contention, leading to CPU resource consumption

#### Real-World Applications of CAS
* ConcurrentHashMap
* ConcurrentLinkedQueue
* AtomicStampedReference
* Spin locks use CAS to implement lightweight locking. Unlike traditional locks, threads repeatedly
attempt to acquire the lock

#### ABA Problem in CAS
The ABA problem occurs when a variable's value changes from A to B the back to A. CAS operations
can be tricked into thinking that no change has occurred, leading to incorrect updates.

#### What is AtomicStampedReference ?
The AtomicStampedReference class in Java pairs a value with a "stamp" (a version number) to tracking changes
accurately. This helps in addressing the ABA problem by ensuring that both the value and the stamp must match
for a CAS operation to succeed

##### How It Works
* <b>Value and Stamp Pair</b>: Each value is associated with a stamp (version number). When the value changes, the stamp
is also updated.
* <b>CAS Operation</b>: The CAS Operation now checks both the value and the stamp. This way, even if the value
reverts to its original state, the stamp will differ, indicating that a change occurred