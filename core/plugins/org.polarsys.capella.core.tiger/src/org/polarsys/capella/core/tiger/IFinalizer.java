/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.tiger;

/**
 * defines an interface for a special rule called after all 
 * other attachments. This is a system to normalize the resulting 
 * model.
 *
 */
public interface IFinalizer {
  /**
   * finalize a transformation
   * @param transfo the transformation to finalize
   */
  void finalize (ITransfo transfo);
}
