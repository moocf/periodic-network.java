// Each Merger[2K] consists of 2 Merger[K] networks
// followed by a layer of Balancers. The top
// Merger[K] takes even inputs from top-half and
// odd inputs from bottom-half. The bottom
// Merger[K] takes odd inputs from top-half and
// even inputs form bottom half. The respective
// output of each Merger[K] is then combined with
// a Balancer. When K is 1, Merger[2K] is a single
// Balancer.
class BlockNetwork implements CountingNetwork {
  CountingNetwork layer;
  CountingNetwork[] halves;
  final int width;
  // halves: top, bottom Merger[K]
  // balancers: layers of Balancers
  // width: number of inputs/outputs

  public BlockNetwork(int w) {
    layer = new LayerNetwork(w);
    if (w > 2) halves = new BlockNetwork[] {
      new BlockNetwork(w/2),
      new BlockNetwork(w/2)
    };
    width = w;
  }

  // 1. Find connected Merger[K].
  // 2. Traverse connected Merger[K].
  // 3. Traverse connected balancer.
  @Override
  public int traverse(int x) {
    int w = width;
    x = layer.traverse(x);
    if (w <= 2) return x;
    int h = x / (w/2);
    return h*(w/2) + halves[h].traverse(x);
  }
}
