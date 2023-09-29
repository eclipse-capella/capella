/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.extension;

import org.polarsys.capella.core.data.capellacore.Trace;

/**
 * This interface allows to contribute an extension to Capella Trace Manager
 */
public interface ITraceExtension {
  /**
   * true if the Trace can be deleted
   * 
   * @param element
   * @return boolean
   */
  public boolean canDelete(Trace element);

  /**
   * true if the trace's source can be removed
   * 
   * @param element
   * @return boolean
   */
  public boolean canRemoveSource(Trace element);

  /**
   * true if the trace target can be removed
   * 
   * @param element
   * @return boolean
   */
  public boolean canRemoveTarget(Trace element);

  /**
   * Whether the trace is assignable from the class given in parameter
   * 
   * @param clazz
   * @return boolean
   */
  public boolean isAssignableFrom(Class<?> clazz);

  /**
   * The trace readable name
   */
  public String getTraceName();

  /**
   * a new trace instance This method should call the proper factory
   */
  public Trace getNewTraceInstance();

  /**
   * true if the trace can be created through the trace wizard
   */
  public boolean isManualTrace();

  /**
   * true if the element can be removed through the trace wizard
   */
  public boolean canAddRemoveItemsToTrace(Object element);

  /**
   * true if the trace can be edited through the trace wizard
   */
  public boolean canEdit(Object element);

  /**
   * The viewpoint identifier for the given extension. Allows to be disabled when the viewpoint is not active
   */
  public String getViewpointID();
}
