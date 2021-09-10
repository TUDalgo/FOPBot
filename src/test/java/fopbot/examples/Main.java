package fopbot.examples;

import fopbot.anim.AnimatedWorldFrame;
import fopbot.anim.resources.Resources;
import fopbot.trace.TracingWorld;

import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {
    Resources.loadAll();
    var example = new BasicExample();
    var frame = new AnimatedWorldFrame(example.getGrid());

    var world = new TracingWorld(frame.getWorld());

    example.init(world);
    world.start();

    frame.startUpdateThread();
    example.run(world);

    for (int i = 0; i < world.getTraces().size(); i++) {
      var trace = world.getTraces().get(i);
      for (var state : trace) {
        switch (state.getLastAction()) {
          case SPAWNED:
            System.out.printf("Robot #%d spawned at (%d,%d)%n", i, state.getX(), state.getY());
            break;
          case MOVED:
            System.out.printf("Robot #%d moved to (%d,%d)%n", i, state.getX(), state.getY());
            break;
          default:
            break;
        }
      }
    }
  }
}
