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
package org.polarsys.capella.core.tiger.extension;

import org.polarsys.capella.core.tiger.ITransfo;

/**
 * Interface to be implemented by class that want to enhance the default behavior of the TransfoEngine.
 */
public interface ITransfoEngineExecuteExt {

  /**
   * Method that is supposed to be launched before the "execute" method TransfoEngine.
   * @param transfo the transformation
   * @throws Exception
   */
  public void preExecute(ITransfo transfo) throws Exception;

  /**
   * Method that is supposed to be launched after the "execute" method TransfoEngine if it succeed.
   * @param transfo the transformation
   * @throws Exception
   */
  public void postExecute(ITransfo transfo) throws Exception;

}
