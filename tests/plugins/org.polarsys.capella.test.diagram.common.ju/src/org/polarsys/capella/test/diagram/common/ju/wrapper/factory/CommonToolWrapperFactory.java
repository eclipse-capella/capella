/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.diagram.tools.api.command.DiagramCommandFactoryService;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.OperationAction;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.test.diagram.common.ju.wrapper.AbstractToolWrapper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.OperationActionToolDescriptionWrapper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.ToolDescriptionWrapper;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 */
public class CommonToolWrapperFactory extends AbstractToolWrapperFactory {

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

    if (ToolPackage.Literals.TOOL_DESCRIPTION.equals(eclass)) {
      result = new ToolDescriptionWrapper(tool_p, commandFactory);

    } else if (ToolPackage.Literals.POPUP_MENU.equals(eclass)) {
      result = new OperationActionToolDescriptionWrapper(findOperationAction(tool_p), commandFactory);

    } else if (tool_p instanceof OperationAction) {
      result = new OperationActionToolDescriptionWrapper((OperationAction)tool_p, commandFactory);

    }

    return result;
  }
  
  /**
   * Find an OperationAction in a tool eContents
   * @param eContents
   * @return the f OperationAction encounter in a tool eContents
   */
  private OperationAction findOperationAction(AbstractToolDescription tool) {
    if (tool instanceof OperationAction) {
      return (OperationAction)tool;
    }
    boolean found = false;
    OperationAction operationActionResult = null;
    EList<EObject> eContents = tool.eContents();
    for (int i = 0; i < eContents.size() && !found; i++) {
      EObject content = eContents.get(i);
      if (content instanceof OperationAction) {
        operationActionResult = (OperationAction) content;
      }
    }
    return operationActionResult;
  }


}
