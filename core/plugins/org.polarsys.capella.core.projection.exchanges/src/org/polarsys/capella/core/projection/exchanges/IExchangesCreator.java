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
package org.polarsys.capella.core.projection.exchanges;


/**
 * This interface is the base interface for automated exchanges creators.<br>
 * The purpose of the sub-classes of this class is to automatically create the exchanges starting from a <code>Component</code> instance on which some "higher level" with exchanges are allocated.<br>
 * For example, for an Entity on which an Operational Activity with some exchanges is allocated, the purpose of such a class would be to create the related communication means starting from the Entity and to allocate the functional exchange to the newly created communication means, if they are not already created.
 * 
 */
public interface IExchangesCreator {
  /**
   * Create the exchanges which are needed to be created automatically.<br>
   * The type of exchange may be different between 2 implementations. It can be a component exchange, a physical link,... 
   */
  public void createExchanges();
}
