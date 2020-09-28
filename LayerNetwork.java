// Each Layer[2K] consists K balancers connecting
// inputs i and W-1-i for i=[0, W/2). When K is 1
// Layer[2K] is a Balancer.
class LayerNetwork implements CountingNetwork {
  CountingNetwork[] balancers;
  final int width;
  // balancers: set of Balancers
  // width: number of inputs/outputs

  public LayerNetwork(int w) {
    balancers = new CountingNetwork[w/2];
    for (int i=0; i<w/2; i++)
      balancers[i] = new Balancer();
    width = w;
  }

  // 1. Find connected Balancer.
  // 2. Traverse connected Balancer.
  @Override
  public int traverse(int x) {
    int w = width;
    int b = x < w/2? x : w-1-x; // 1
    int y = balancers[b].traverse(0); // 2
    return y == 0? b : w-1-b;         // 2
  }
}
