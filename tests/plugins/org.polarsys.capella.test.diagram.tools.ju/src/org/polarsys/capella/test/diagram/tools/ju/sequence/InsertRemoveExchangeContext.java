package org.polarsys.capella.test.diagram.tools.ju.sequence;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.ef.command.AbstractCommand;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.test.diagram.common.ju.context.FSDiagram;
import org.polarsys.capella.test.diagram.common.ju.context.SequenceDiagram;
import org.polarsys.capella.test.framework.helpers.SkeletonHelper;
import org.polarsys.capella.test.framework.model.GenericModel;

public class InsertRemoveExchangeContext extends SequenceTest {
  String seqMsg = "SequenceMessage";
  String constraint = GenericModel.CONSTRAINT_1;

  @Override
  public void test() throws Exception {
    BlockArchitectureExt.Type[] types = { BlockArchitectureExt.Type.OA, BlockArchitectureExt.Type.SA,
        BlockArchitectureExt.Type.LA, BlockArchitectureExt.Type.PA };
    BlockArchitectureExt.Type[] typesIS = { BlockArchitectureExt.Type.SA, BlockArchitectureExt.Type.LA,
        BlockArchitectureExt.Type.PA };

    testOnAllLevels(types, SequenceType.ES);
    testOnAllLevels(typesIS, SequenceType.IS);
    testOnAllLevels(types, SequenceType.FS);
  }

  @Override
  public void test(SequenceDiagram diagram) {
    setUpDiagram(diagram);
    diagram.insertExchangeContext(constraint);
  }

  @Override
  public void setUpDiagram(SequenceDiagram diagram) {
    String source = "";
    String target = "";
    if (diagram instanceof FSDiagram) {
      source = ((FSDiagram) diagram).createFunction();
      target = ((FSDiagram) diagram).createFunction();
    } else {
      source = diagram.createActor();
      target = diagram.createActor();
    }
    SkeletonHelper.createSequenceMessage(((Scenario) diagram.getDiagramDescriptor().getTarget()).getId(), seqMsg,
        source, target, context);
    diagram.createConstraint(constraint);
    addConstraint(seqMsg, constraint);
    diagram.removeConstraint(constraint, diagram.getDiagramId());
  }

  private void addConstraint(String elementId, String constraintId) {
    EObject obj = context.getSemanticElement(elementId);
    if (obj instanceof SequenceMessage) {
      SequenceMessage msg = (SequenceMessage) obj;
      final AbstractCommand cmd = new AbstractReadWriteCommand() {
        public void run() {
          Constraint c = (Constraint) context.getSemanticElement(constraintId);
          msg.setExchangeContext(c);
        }
      };
      context.getExecutionManager().execute(cmd);
    }
  }
}
