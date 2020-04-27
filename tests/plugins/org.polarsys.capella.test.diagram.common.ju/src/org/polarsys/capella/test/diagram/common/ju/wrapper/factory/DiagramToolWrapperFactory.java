/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.common.ju.wrapper.factory;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.tools.api.command.DiagramCommandFactoryService;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.test.diagram.common.ju.wrapper.AbstractToolWrapper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.ContainerCreationDescriptionWrapper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.ContainerDropDescriptionWrapper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.DeleteCreationDescriptionWrapper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.EdgeCreationDescriptionWrapper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.NodeCreationDescriptionWrapper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.ReconnectEdgeDescriptionWrapper;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 */
public class DiagramToolWrapperFactory extends AbstractToolWrapperFactory {

  /**
   * @see org.polarsys.capella.test.diagram.common.ju.wrapper.factory.AbstractToolWrapperFactory#getTargetEPackage()
   */
  @Override
  public EPackage getTargetEPackage() {
    return ToolPackage.eINSTANCE;
  }

  /**
   * @see org.polarsys.capella.test.diagram.common.ju.wrapper.factory.AbstractToolWrapperFactory#getDefaultCommandFactory()
   */
  @Override
  public ICommandFactory getDefaultCommandFactory() {
	  TransactionalEditingDomain editingDomain = TestHelper.getEditingDomain();
	    if (null == editingDomain) {
		    final ExecutionManager manager = ExecutionManagerRegistry.getInstance().addNewManager();
		    editingDomain = manager.getEditingDomain();
	    }
	    return DiagramCommandFactoryService.getInstance().getNewProvider().getCommandFactory(editingDomain);
  }

  /**
   * @see org.polarsys.capella.test.diagram.common.ju.wrapper.factory.AbstractToolWrapperFactory#createToolCommandWrapper(org.eclipse.sirius.description.tool.AbstractToolDescription,
   *      org.eclipse.sirius.tools.api.command.ICommandFactory)
   */
  @Override
  public AbstractToolWrapper createToolCommandWrapper(AbstractToolDescription tool_p, ICommandFactory cmdFactory_p) {
    Assert.isNotNull(tool_p);
    Assert.isTrue(cmdFactory_p instanceof IDiagramCommandFactory);

    IDiagramCommandFactory commandFactory = (IDiagramCommandFactory) cmdFactory_p;

    AbstractToolWrapper result = null;

    EClass eclass = tool_p.eClass();

    if (ToolPackage.Literals.CONTAINER_CREATION_DESCRIPTION.equals(eclass)) {
      result = new ContainerCreationDescriptionWrapper(tool_p, commandFactory);

    } else if (ToolPackage.Literals.NODE_CREATION_DESCRIPTION.equals(eclass)) {
      result = new NodeCreationDescriptionWrapper(tool_p, commandFactory);

    } else if (ToolPackage.Literals.EDGE_CREATION_DESCRIPTION.equals(eclass)) {
      result = new EdgeCreationDescriptionWrapper(tool_p, commandFactory);

    } else if (ToolPackage.Literals.DELETE_ELEMENT_DESCRIPTION.equals(eclass)) {
      result = new DeleteCreationDescriptionWrapper(tool_p, commandFactory);

    } else if (ToolPackage.Literals.RECONNECT_EDGE_DESCRIPTION.equals(eclass)) {
      result = new ReconnectEdgeDescriptionWrapper(tool_p, commandFactory);

    } else if (ToolPackage.Literals.CONTAINER_DROP_DESCRIPTION.equals(eclass)) {
      result = new ContainerDropDescriptionWrapper(tool_p, commandFactory);

    }

    return result;
  }

}
