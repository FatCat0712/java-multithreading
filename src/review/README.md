### When to Use sleep(), join() and interrupt() ?
<str>sleep()</str> is useful for introducing delays, but be careful not to overuse it
as it can make your threads unresponsive

<str>join()</str> is essential when one thread needs to wait for another to complete,
but it should be used only when necessary to avoid blocking the thread unnecessarily

<str>interrupt</str> is a powerful method for signaling threads to stop, but always
make sure to handle InterruptedException properly, and avoid swallowing the exception
silently. 