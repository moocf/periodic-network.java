// Each Block[2K] consists of Layer[2K] network
// followed by top and bottom Block[K] networks.
// Top Block[K] is connected to top-half of
// outputs of Layer[2K]. Bottom Block[K] is
// connected to the bottom-half. When K is 1,
// Block[2K] is a Balancer.
class BlockNetwork implements CountingNetwork {
  CountingNetwork layer;
  CountingNetwork[] halves;
  final int width;
  // layer: Layer[2K] network
  // halves: top, bottom Block[K]
  // width: number of inputs/outputs

  public BlockNetwork(int w) {
    layer = new LayerNetwork(w);
    if (w > 2) halves = new BlockNetwork[] {
      new BlockNetwork(w/2),
      new BlockNetwork(w/2)
    };
    width = w;
  }

  // 1. Traverse Layer[2K].
  // 2. Find connected Block[K].
  // 3. Traverse connected Block[K].
  @Override
  public int traverse(int x) {
    int w = width;
    x = layer.traverse(x); // 1
    if (w <= 2) return x;  // 2
    int h = x / (w/2);     // 2
    x = x % (w/2);         // 2
    return h*(w/2) + halves[h].traverse(x); // 3
  }
}
