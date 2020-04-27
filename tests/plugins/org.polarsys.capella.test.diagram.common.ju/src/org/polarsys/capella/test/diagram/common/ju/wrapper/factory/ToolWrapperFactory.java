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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.polarsys.capella.test.diagram.common.ju.wrapper.AbstractToolWrapper;

/**
 * a factory  for AbstractCmdToolWrapper specialization.
 */
public class ToolWrapperFactory {

  /** Map with registered factory */
  Map<EPackage, AbstractToolWrapperFactory> _factories = null;

  /** The factory singleton */
  public static ToolWrapperFactory INSTANCE = init();

  /** The singleton initialization */
  private static ToolWrapperFactory init() {
    if (null == INSTANCE) {
      INSTANCE = new ToolWrapperFactory();
    }

    return INSTANCE;
  }

  /** constructor */
  private ToolWrapperFactory() {

    // Let's register available factories

    _factories = new HashMap<EPackage, AbstractToolWrapperFactory>();

    Set<AbstractToolWrapperFactory> list = new HashSet<AbstractToolWrapperFactory>(3);
    list.add(new CommonToolWrapperFactory());
    list.add(new SequenceToolWrapperFactory());
    list.add(new TableToolWrapperFactory());
    list.add(new DiagramToolWrapperFactory());

    for (AbstractToolWrapperFactory factory : list) {
      _factories.put(factory.getTargetEPackage(), factory);
    }

  }

  /**
   * Return an instance of the ToolCommandWrapper class matching with the
   * given tool.
   * @param tool that we 
   * @return null whether no wrapper was found.
   * @see AbstractToolDescription
   */
  public AbstractToolWrapper createToolCommandWrapper(AbstractToolDescription tool_p) {
    Assert.isNotNull(tool_p);

    //TODO add support for hierarchical ePackage?
    EPackage ePackage = tool_p.eClass().getEPackage();

    AbstractToolWrapperFactory factory = _factories.get(ePackage);

    return (factory != null ? factory.createToolCommandWrapper(tool_p) : null);

  }

}
