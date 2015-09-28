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
package org.polarsys.capella.test.refinement.ju.headless;

import java.util.List;

import org.polarsys.capella.core.data.information.AbstractInstance;

/**
 * Interface which have to be implemented by the classes with 
 * are used in order to short-cut GUI call to {@link UISelectionResolver}
 */
public interface IResolverResult extends IHeadlessResult {

	/** 
	 * the simulated result. 
	 * @return the selected element list.
	 */
	public List<AbstractInstance> getSelectedAbstractInstances();

	/** 
   * the attempted proposals. 
   * @return the proposed element list.
   */
  public List<AbstractInstance> getAttemptedAbstractInstances();
}
