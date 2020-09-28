// Each Merger[2K] consists of 2 Merger[K] networks
// followed by a layer of Balancers. The top
// Merger[K] takes even inputs from top-half and
// odd inputs from bottom-half. The bottom
// Merger[K] takes odd inputs from top-half and
// even inputs form bottom half. The respective
// output of each Merger[K] is then combined with
// a Balancer. When K is 1, Merger[2K] is a single
// Balancer.
class LayerNetwork implements CountingNetwork {
  CountingNetwork[] balancers;
  final int width;
  // balancers: layer of Balancers
  // width: number of inputs/outputs

  public LayerNetwork(int w) {
    balancers = new CountingNetwork[w/2];
    for (int i=0; i<w/2; i++)
      balancers[i] = new Balancer();
    width = w;
  }

  // 1. Find connected Merger[K].
  // 2. Traverse connected Merger[K].
  // 3. Traverse connected balancer.
  @Override
  public int traverse(int x) {
    int w = width;
    int b = x < w/2? x : w-1-x;
    int y = balancers[b].traverse(0);
    return y == 0? b : w-1-b;
  }
}
