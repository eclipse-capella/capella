/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.common.handlers.merge;

import org.eclipse.swt.graphics.Image;

public interface ICategorySet {

  /**
   * Return the identifier of the set
   */
  String getId();

  /**
   * Return an optional image for this set of category
   */
  Image getImage();

  /**
   * Return a user-friendly name for this category in the context of the given diff node
   */
  String getText();

  /**
   * Return a description
   */
  String getDescription();

}
