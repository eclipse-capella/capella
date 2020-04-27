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

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.polarsys.capella.test.diagram.common.ju.wrapper.AbstractToolWrapper;

/**
 * Abstract class to extend for any ToolWrapperFactory
 *
 */
public abstract class AbstractToolWrapperFactory {

  /**
   * the EPackage contained the tools for this factory
   * @return
   */
  public abstract EPackage getTargetEPackage();
  
  /**
   * Return the default command factory to use with for a tool wrapper factory
   */
  public abstract ICommandFactory getDefaultCommandFactory();
  
  /**
   * Return an instance of the ToolCommandWrapper class matching with the
   * given tool.
   * @param tool_p tool to wrap 
   * @return null whether no wrapper was found.
   * @see AbstractToolDescription
   */
  public AbstractToolWrapper createToolCommandWrapper(AbstractToolDescription tool_p) {
    
    return createToolCommandWrapper(tool_p, getDefaultCommandFactory());
  }
  
  /**
   * Return an instance of the ToolCommandWrapper class matching with the
   * given tool.
   * @param tool_p tool to wrap
   * @param cmdFactory_p the command factory to use  
   * @return null whether no wrapper was found.
   * @see AbstractToolDescription
   * @see ICommandFactory
   */  
  public abstract AbstractToolWrapper createToolCommandWrapper(AbstractToolDescription tool_p, ICommandFactory cmdFactory_p);
  
}
