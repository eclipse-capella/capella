package org.polarsys.capella.test.diagram.tools.ju.sequence;

import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.diagram.common.ju.context.SequenceDiagram;

public class InsertRemoveActors extends SequenceTest {

  @Override
  public void test() throws Exception {
    BlockArchitectureExt.Type [] types = { BlockArchitectureExt.Type.SA,
        BlockArchitectureExt.Type.LA, BlockArchitectureExt.Type.PA };
    BlockArchitectureExt.Type [] typesIS = { BlockArchitectureExt.Type.SA,
        BlockArchitectureExt.Type.LA, BlockArchitectureExt.Type.PA };
    
    testOnAllLevels(types, SequenceType.ES);
    testOnAllLevels(typesIS, SequenceType.IS);
  }

  @Override
  public void test(SequenceDiagram diagram) {
    testInsertRemove(diagram);
  }
  
  public void testInsertRemove(SequenceDiagram diagram) {
    actor1 = diagram.createActor();
    actor2 = diagram.createActor();
    diagram.removeActors(new String[]{actor1, actor2});
    diagram.insertActors(new String[]{actor1, actor2});
  }
}
