/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage;
import org.eclipse.sirius.table.metamodel.table.description.LabelEditTool;
import org.eclipse.sirius.table.metamodel.table.description.TableTool;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.table.tools.api.command.TableCommandFactoryService;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.test.diagram.common.ju.wrapper.AbstractToolWrapper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.table.CreateCellToolWrapper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.table.CreateLineToolWrapper;
import org.polarsys.capella.test.diagram.common.ju.wrapper.table.DeleteTableElementToolWrapper;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * FIXME it should be checked whether {@link LabelEditTool} has to come under {@link AbstractToolDescription}.
 * <P>
 * Otherwise we have to refactor this class with {@link TableTool} instead of AbstractToolDescription
 */
public class TableToolWrapperFactory extends AbstractToolWrapperFactory {

  /**
   * @see org.polarsys.capella.test.diagram.common.ju.wrapper.factory.AbstractToolWrapperFactory#getTargetEPackage()
   */
  @Override
  public EPackage getTargetEPackage() {
    return org.eclipse.sirius.table.metamodel.table.description.DescriptionPackage.eINSTANCE;
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
	    return TableCommandFactoryService.getInstance().getNewProvider().getCommandFactory(editingDomain);
  }

  /**
   * @see org.polarsys.capella.test.diagram.common.ju.wrapper.factory.AbstractToolWrapperFactory#createToolCommandWrapper(org.eclipse.sirius.description.tool.AbstractToolDescription,
   *      org.eclipse.sirius.tools.api.command.ICommandFactory)
   */
  @Override
  public AbstractToolWrapper createToolCommandWrapper(AbstractToolDescription tool_p, ICommandFactory cmdFactory_p) {
    Assert.isNotNull(tool_p);
    Assert.isTrue(cmdFactory_p instanceof ITableCommandFactory);

    ITableCommandFactory commandFactory = (ITableCommandFactory) cmdFactory_p;
    // This needs to be explicitly set as "TableCommandFactory" class is not initializing the commandTaskHelper in constructor
    
    ResourceSet rs ;
    if (null == TestHelper.getEditingDomain()) {
	    final ExecutionManager manager = ExecutionManagerRegistry.getInstance().addNewManager();
	    rs = manager.getEditingDomain().getResourceSet();
    }
    else
    	rs = TestHelper.getEditingDomain().getResourceSet();

    commandFactory.setModelAccessor(SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(rs));

    AbstractToolWrapper result = null;

    EClass eclass = tool_p.eClass();

    switch (eclass.getClassifierID()) {
      case DescriptionPackage.CREATE_CELL_TOOL:
        result = new CreateCellToolWrapper(tool_p, commandFactory);
      break;
      case DescriptionPackage.CREATE_LINE_TOOL:
        result = new CreateLineToolWrapper(tool_p, commandFactory);
      break;
      case DescriptionPackage.CREATE_COLUMN_TOOL:
      break;
      case DescriptionPackage.DELETE_LINE_TOOL:
      case DescriptionPackage.DELETE_COLUMN_TOOL:
        result = new DeleteTableElementToolWrapper(tool_p, commandFactory);
      break;
      default:
        result = null;
    }

    return result;
  }

}
