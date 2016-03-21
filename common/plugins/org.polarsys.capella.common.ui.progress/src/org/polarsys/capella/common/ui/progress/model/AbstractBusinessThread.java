/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.ui.progress.model;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 */
public abstract class AbstractBusinessThread 
  extends Observable 
  implements Runnable, 
    Observer {

  private boolean _isRunning;

  /**
   * Default constructor
   */
  public AbstractBusinessThread() {
    _isRunning = false;
  }
  
  /**
   * @see java.lang.Runnable#run()
   */
  public void run() {
    _isRunning = true;
    
    int min = getMinimum();    
    int max = getMaximum();
    int step = getStep();
    
    HashMap<String, Object> info = new HashMap<String, Object>();
    
    for (int i = min; i < max; i+=step) {
      info.put("Progress", new Integer(i)); //$NON-NLS-1$
      info.put("Min", new Integer(min)); //$NON-NLS-1$      
      info.put("Max", new Integer(max)); //$NON-NLS-1$         
      info.put("Step", new Integer(step)); //$NON-NLS-1$      
      info.put("Finished", Boolean.FALSE); //$NON-NLS-1$   
            
      doRun(info);

      setChanged();
      notifyObservers(info);

      while(!_isRunning) { 
        try {
          Thread.sleep(10);
        } catch (InterruptedException exception_p) {
          // 
        } 
      }
    }
    
    info.put("Finished", Boolean.TRUE); //$NON-NLS-1$
    setChanged();
    notifyObservers(info);
    
  }
  
  /**
   * Runs a step
   * @param progressInfo_p
   */
  public abstract void doRun(HashMap<String, Object> info);

  /**
   * @return The started step
   */
  public abstract int getMinimum();

  /**
   * @return The ended step
   */
  public abstract int getMaximum();

  /**
   * @return Step length
   */
  public abstract int getStep();

  /**
   * Specifies wether this thread is running or not
   * @param running
   */
  public synchronized void setRunning(boolean running) {
    _isRunning = running;
  }

  /**
   * @return the isRunning
   */
  public boolean isRunning() {
    return _isRunning;
  }
  
  /**
   * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
   */
  public void update(Observable o, Object arg) {
    setRunning(!isRunning());
  }  
}
