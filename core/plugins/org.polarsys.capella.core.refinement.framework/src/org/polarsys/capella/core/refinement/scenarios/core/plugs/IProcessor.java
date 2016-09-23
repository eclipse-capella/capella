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
package org.polarsys.capella.core.refinement.scenarios.core.plugs;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.ProcessorException;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 */
public interface IProcessor extends IRefinementPlug {

  /**
   * 
   */
  enum ProcessingType { PREPROCESSING, POSTPROCESSING }

  /**
   * Sets the element on which the processing will be applied
   * 
   * @param context
   */
  public void setContext(ModelElement context);

  /**
   * Sets the elements on which the processing will be applied
   * 
   * @param context
   */
  public void setContext(List<ModelElement> context);

  /**
   * Sets the target element of the processing execution
   * 
   * @param target
   */
  public void setTarget(NamedElement target);

  /**
   * @return Retrieves the result of the processing execution
   */
  public Object getResult();

	/**
	 * launch the processing execution
	 * 
	 * @param progressMonitor
	 *
	 * @throws ProcessorException
	 */
	public void execute(IProgressMonitor progressMonitor) throws ProcessorException;
}
