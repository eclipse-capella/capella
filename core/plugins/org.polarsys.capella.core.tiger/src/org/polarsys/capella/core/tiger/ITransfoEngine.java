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
package org.polarsys.capella.core.tiger;

import java.util.Observable;
import java.util.Observer;

/**
 */
public abstract class ITransfoEngine
  implements Runnable, Observer { // FIXME Observer is implemented to satisfy an unclear(unused?) dependency on "InfoProgressShell"

  public abstract String getName();
  
  public abstract void execute(ITransfo transfo) throws TransfoException;

  /**
   * @see java.lang.Runnable#run()
   */
  public abstract void run();

  /**
   * @param transfo the transfo to set
   */
  public abstract void setTransfo(ITransfo transfo);

  protected abstract void generateUid();
  
  /**
   * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
   */
  public abstract void update(Observable o, Object arg);  
  
  public abstract void initialize(ITransfo transfo) throws TransfoException; 
  
  public abstract String generateReport();
}
