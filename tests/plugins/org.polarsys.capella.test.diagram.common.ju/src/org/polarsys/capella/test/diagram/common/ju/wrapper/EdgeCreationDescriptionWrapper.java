/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.wrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.business.api.query.DMappingBasedQuery;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.ArgumentType;

/**
 */
public class EdgeCreationDescriptionWrapper extends AbstractCommonToolWrapper {

  public EdgeCreationDescriptionWrapper(AbstractToolDescription tool, IDiagramCommandFactory commandFactory) {
    super(tool, commandFactory);
  }

  /**
   * @see org.polarsys.capella.test.common.ju.tool.AbstractCmdToolWrapper #createCommand()
   */
  @Override
  public Command createCommand() {

    Command cmd = UnexecutableCommand.INSTANCE;

    if (isContextOk()) {
      EdgeCreationDescription tool = (EdgeCreationDescription) _tool;

      EdgeTarget source = (EdgeTarget) _arguments.get(ArgumentType.SOURCE);
      EdgeTarget target = (EdgeTarget) _arguments.get(ArgumentType.TARGET);

      cmd = _diagramCommandFactory.buildCreateEdgeCommandFromTool(source, target, tool);
    }

    return cmd;
  }

  /**
   * @see org.polarsys.capella.test.common.ju.tool.AbstractCmdToolWrapper #getArgumentTypes()
   */
  @Override
  public List<ArgumentData> getArgumentTypes() {

    List<ArgumentData> ret = null;

    if (null == _argumentTypes) {
      List<ArgumentData> list = new ArrayList<ArgumentData>();
      Collections.addAll(list, new AbstractToolWrapper.ArgumentData(ArgumentType.SOURCE, DiagramPackage.Literals.EDGE_TARGET),
          new AbstractToolWrapper.ArgumentData(ArgumentType.TARGET, DiagramPackage.Literals.EDGE_TARGET));
      ret = Collections.unmodifiableList(list);
    } else {
      ret = _argumentTypes;
    }

    return ret;
  }

  /**
   * @see org.polarsys.capella.test.common.ju.tool.AbstractCmdToolWrapper #isContextOk()
   */
  @Override
  public boolean isContextOk() {

    boolean ret = true;

    // First of all Let's check is argument types are ok.
    if (!isArgumentsAreSet() && (_arguments.get(ArgumentType.SOURCE) instanceof DMappingBased)
        && (_arguments.get(ArgumentType.TARGET) instanceof DMappingBased)

    ) {
      ret = false;
    }

    // Now, let's perform job
    if (ret) {

      EdgeCreationDescription tool = (EdgeCreationDescription) _tool;
      DMappingBased source = (DMappingBased) _arguments.get(ArgumentType.SOURCE);
      DMappingBased target = (DMappingBased) _arguments.get(ArgumentType.TARGET);

      DMappingBasedQuery sourceQuery = new DMappingBasedQuery(source);
      DMappingBasedQuery targetQuery = new DMappingBasedQuery(target);

      boolean sourceOk = false;
      boolean targetOk = false;
      DiagramElementMapping sourceMapping = (DiagramElementMapping) source.getMapping();
      if (sourceMapping != null) {
        Iterator<EdgeMapping> iterMappings = tool.getEdgeMappings().iterator();
        while (iterMappings.hasNext()) {
          EdgeMapping edgeMapping = iterMappings.next();
          if (sourceQuery.isFromAnyMapping(edgeMapping.getSourceMapping())) {
            if (target == null) {
              return true;
            }
            sourceOk = true;
            if (targetQuery.isFromAnyMapping(edgeMapping.getTargetMapping())) {
              return true;
            }
          } else if ((target != null) && targetQuery.isFromAnyMapping(edgeMapping.getTargetMapping())) {
            targetOk = true;
          }
        }
        if (!sourceOk) {
          DMappingBased toCompare = source;
          Iterator<DiagramElementMapping> iterExtraMappings = tool.getExtraSourceMappings().iterator();
          while (iterExtraMappings.hasNext()) {
            DiagramElementMapping next = iterExtraMappings.next();
            if (next.isFrom(toCompare)) {
              sourceOk = true;
            }
          }
        }
        if (!targetOk) {
          if (target == null) {
            targetOk = true;
          } else {
            DMappingBased toCompare = target;
            Iterator<DiagramElementMapping> iterExtraMappings = tool.getExtraTargetMappings().iterator();
            while (iterExtraMappings.hasNext()) {
              DiagramElementMapping next = iterExtraMappings.next();
              if (next.isFrom(toCompare)) {
                targetOk = true;
              }
            }
          }
        }
      }
      ret = sourceOk && targetOk;
    }

    return ret;
  }

}
