// Each Periodic[K] consists of log2(K) Block[K]
// networks connected sequentially.
class PeriodicNetwork implements CountingNetwork {
  CountingNetwork[] blocks;
  final int width;
  // blocks: sequential log2 Block[2K]s
  // width: number of inputs/outputs

  public PeriodicNetwork(int w) {
    int B = Integer.numberOfLeadingZeros(w);
    blocks = new CountingNetwork[B];
    for (int i=0; i<B; i++)
      blocks[i] = new BlockNetwork(w);
    width = w;
  }

  // 1. Traverse all Block[2K]s sequentially.
  @Override
  public int traverse(int x) {
    for (CountingNetwork b : blocks) // 1
      x = b.traverse(x);             // 1
    return x;
  }
}
