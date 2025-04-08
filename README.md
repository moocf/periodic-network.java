Periodic network is a balanced counting network
that allows processes to decompose operations, like
counting, and reduce memory contention.

> **Course**: [Concurrent Data Structures], Monsoon 2020\
> **Taught by**: Prof. Govindarajulu Regeti

[Concurrent Data Structures]: https://github.com/iiithf/concurrent-data-structures

```java
Balancer:
A Balancer is a simple switch with two input
and two output wires. Tokens arrive on the
Balancer’s input wires at arbitrary times,
and emerge on their output wires, at some later
Given a stream of input tokens, it sends one
token to the top output wire, and the next to
the bottom, and so on, effectively balancing the
number of tokens between the two wires.

Balancer.traverse():
1. Flip balancer state.
2. Return the updated state.
```

```java
LayerNetwork:
Each Layer[2K] consists K balancers connecting
inputs i and W-1-i for i=[0, W/2). When K is 1
Layer[2K] is a Balancer.

LayerNetwork.traverse():
1. Find connected Balancer.
2. Traverse connected Balancer.
```

```java
BlockNetwork:
Each Block[2K] consists of Layer[2K] network
followed by top and bottom Block[K] networks.
Top Block[K] is connected to top-half of
outputs of Layer[2K]. Bottom Block[K] is
connected to the bottom-half. When K is 1,
Block[2K] is a Balancer.

BlockNetwork.traverse():
1. Traverse Layer[2K].
2. Find connected Block[K].
3. Traverse connected Block[K].
```

```java
PeriodicNetwork:
Each Periodic[K] consists of log2(K) Block[K]
networks connected sequentially.

PeriodicNetwork.traverse():
1. Traverse all Block[2K]s sequentially.
```

See [Balancer.java], [LayerNetwork.java],
[BlockNetwork.java], [PeriodicNetwork.java] for
code, [Main.java] for
test, and [repl.it] for output.

[Balancer.java]: https://repl.it/@wolfram77/periodic-network#Balancer.java
[LayerNetwork.java]: https://repl.it/@wolfram77/periodic-network#LayerNetwork.java
[BlockNetwork.java]: https://repl.it/@wolfram77/periodic-network#BlockNetwork.java
[PeriodicNetwork.java]: https://repl.it/@wolfram77/periodic-network#PeriodicNetwork.java
[Main.java]: https://repl.it/@wolfram77/periodic-network#Main.java
[repl.it]: https://periodic-network.wolfram77.repl.run


### references

- [The Art of Multiprocessor Programming :: Maurice Herlihy, Nir Shavit](https://dl.acm.org/doi/book/10.5555/2385452)

![](https://ga-beacon.deno.dev/G-G1E8HNDZYY:v51jklKGTLmC3LAZ4rJbIQ/github.com/javaf/periodic-network)
