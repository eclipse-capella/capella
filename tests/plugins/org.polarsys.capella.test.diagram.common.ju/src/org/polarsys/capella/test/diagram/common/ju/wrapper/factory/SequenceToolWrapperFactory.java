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
import org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.tools.api.command.DiagramCommandFactoryService;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.test.diagram.common.ju.wrapper.AbstractToolWrapper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.sequence.CombinedFragmentCreationDescriptionWrapper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.sequence.InstanceRoleCreationDescriptionWrapper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.sequence.InteractionUseCreationDescriptionWrapper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.sequence.MessageCreationDescriptionWrapper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.sequence.OperandCreationDescriptionWrapper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.sequence.StateCreationDescriptionWrapper;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 */
public class SequenceToolWrapperFactory extends AbstractToolWrapperFactory {

  /**
   * @see org.polarsys.capella.test.diagram.common.ju.wrapper.factory.AbstractToolWrapperFactory#getTargetEPackage()
   */
  @Override
  public EPackage getTargetEPackage() {
    return org.eclipse.sirius.diagram.sequence.description.tool.ToolPackage.eINSTANCE;
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

    switch (eclass.getClassifierID()) {
      case ToolPackage.MESSAGE_CREATION_TOOL:
        result = new MessageCreationDescriptionWrapper(tool_p, commandFactory);
      break;
      case ToolPackage.INSTANCE_ROLE_CREATION_TOOL:
        result = new InstanceRoleCreationDescriptionWrapper(tool_p, commandFactory);
      break;
      case ToolPackage.COMBINED_FRAGMENT_CREATION_TOOL:
        result = new CombinedFragmentCreationDescriptionWrapper(tool_p, commandFactory);
      break;
      case ToolPackage.OPERAND_CREATION_TOOL:
        result = new OperandCreationDescriptionWrapper(tool_p, commandFactory);
      break;
      case ToolPackage.STATE_CREATION_TOOL:
        result = new StateCreationDescriptionWrapper(tool_p, commandFactory);
      break;
      case ToolPackage.INTERACTION_USE_CREATION_TOOL:
        result = new InteractionUseCreationDescriptionWrapper(tool_p, commandFactory);
      break;
      default:
        result = null;
    }

    return result;
  }

}
