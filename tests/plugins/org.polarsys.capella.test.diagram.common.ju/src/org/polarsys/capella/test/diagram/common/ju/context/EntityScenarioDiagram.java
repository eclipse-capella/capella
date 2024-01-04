/*******************************************************************************
 * Copyright (c) 2024 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.context;

import org.eclipse.sirius.diagram.DDiagram;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt.Type;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;
import org.polarsys.capella.test.diagram.common.ju.step.crud.CreateDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.framework.context.SessionContext;

public class EntityScenarioDiagram extends SequenceDiagram {
  public EntityScenarioDiagram(BlockArchitectureExt.Type type, SessionContext context, DDiagram diagram) {
    super(type, context, diagram);
  }

  public static EntityScenarioDiagram createDiagram(SessionContext executionContext, String targetIdentifier) {
    BlockArchitecture architecture = BlockArchitectureExt
        .getRootBlockArchitecture(executionContext.getSemanticElement(targetIdentifier));
      final BlockArchitectureExt.Type type = BlockArchitectureExt.getBlockArchitectureType(architecture);
      if(type == Type.OA) {
        return (EntityScenarioDiagram) new CreateDiagramStep(executionContext, targetIdentifier, IDiagramNameConstants.OPERATIONAL_INTERACTION_SCENARIO_DIAGRAM_NAME) {
          @Override
          public DiagramContext getResult() {
            return new EntityScenarioDiagram(type, getExecutionContext(), diagram);
          }
        }.run().open();
      }
      
      return null;   
  }

  public static EntityScenarioDiagram openDiagram(SessionContext executionContext, String name,
      final BlockArchitectureExt.Type type) {
    return (EntityScenarioDiagram) new OpenDiagramStep(executionContext, name) {
      @Override
      public DiagramContext getResult() {
        return new EntityScenarioDiagram(type, getExecutionContext(), diagram);
      }
    }.run().open();
  }
}
