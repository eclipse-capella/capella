/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.handlers.level;

import java.util.Collection;
import java.util.LinkedList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.selection.CompoundSelectionContextHandler;
import org.polarsys.capella.core.transition.common.handlers.selection.ISelectionContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public interface ILevelHandler extends IHandler {

  public enum Level {
    PREVIOUS_N2, PREVIOUS_N1, SOURCE, TARGET
  };

  /**
   * Return eClass of the source architecture
   */
  public EClass getSourceLevel(IContext context_p);

  /**
   * Returns architecture EClass for the given level
   * @param context_p
   * @param level_p
   * @return
   */
  public EClass getLevel(IContext context_p, Level level_p);

  /**
   * Returns an ordered list of available contexts. (from bottom to upper levels)
   */
  public LinkedList<Level> getLevels(IContext context_p);

  @Deprecated
  public Collection<Level> getLevels(IContext context_p, EObject element_p);

  /**
   * Returns selection context for the given level
   */
  public ISelectionContext getSelectionContext(Level index_p, IContext context_p);

  /**
   * Returns selection context for the given traced element
   */
  @Deprecated
  public ISelectionContext getSelectionContext(EObject element_p, IContext context_p);

  /**
   * @param context_p
   * @param handler_p
   */
  public void initializeSelectionContexts(IContext context_p, CompoundSelectionContextHandler handler_p);

  /**
   * @param level_p
   * @param result_p
   * @param context_p
   */
  public void addScope(Level level_p, EObject result_p, IContext context_p);

}
