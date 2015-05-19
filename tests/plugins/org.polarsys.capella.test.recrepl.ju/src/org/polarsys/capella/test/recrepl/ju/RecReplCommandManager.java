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
package org.polarsys.capella.test.recrepl.ju;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.constants.ITransitionSteps;
import org.polarsys.capella.core.transition.common.handlers.activity.IActivityExtender;
import org.polarsys.capella.core.transition.common.handlers.options.DefaultOptionsHandler;
import org.polarsys.capella.core.transition.common.handlers.options.OptionsHandlerHelper;
import org.polarsys.kitalpha.cadence.core.api.parameter.ActivityParameters;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * An activiry extender to run a test before the end of the transition (and so on before disposing all handlers)
 */
public class RecReplCommandManager implements IActivityExtender {

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus init(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus dispose(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus preActivity(IContext context_p, String activityIdentifier_p, ActivityParameters activityParams_p) {
    if ("DifferencesFilteringActivity".equals(activityIdentifier_p)) {
    	//throw new UnsupportedOperationException();
//      if (_currentTest != null) {
//        _currentTest.filteringTest(context_p);
//      }
    }
    return Status.OK_STATUS;
  } 
  
  protected static String propertyName = null;
  protected static Object value = null;
  protected static IContext context;
  
  public static void push(String propertyName_p, Object value_p) {
  	propertyName = propertyName_p;
  	value = value_p;  	
  }
  
  public static IContext getContext() {
  	return context;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus postActivity(IContext context_p, String activityIdentifier_p, ActivityParameters activityParams_p) {  	
  	if (ITransitionSteps.INITIALIZE_TRANSITION.equals(activityIdentifier_p) && propertyName != null) {
      IPropertyContext propertyContext =
          ((DefaultOptionsHandler) OptionsHandlerHelper.getInstance(context_p)).getPropertyContext(context_p,
              (String) context_p.get(ITransitionConstants.OPTIONS_SCOPE));
      propertyContext.setCurrentValue(propertyContext.getProperties().getProperty(propertyName), value);
    } 
  	if ("FinalizeTransitionActivity".equals(activityIdentifier_p)) {
  		context = context_p;
  		System.out.println(ReplicableElementHandlerHelper.getInstance(getContext()));  		
  	}
    return Status.OK_STATUS;
  }

	public static void clear() {
		propertyName = null;
		value = null;
	}
}
