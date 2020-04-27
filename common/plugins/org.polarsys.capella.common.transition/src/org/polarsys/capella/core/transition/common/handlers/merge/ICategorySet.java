/*******************************************************************************
 * Copyright (c) 2016, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.common.handlers.merge;

public interface ICategorySet {

  /**
   * Return the identifier of the set
   */
  String getId();

  /**
   * Return an optional image for this set of category
   */
  Object getImage();

  /**
   * Return a user-friendly name for this category in the context of the given diff node
   */
  String getText();

  /**
   * Return a description
   */
  String getDescription();

}
