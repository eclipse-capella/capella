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
package org.polarsys.capella.core.refinement.command;

import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.refinement.processor.CommunicationLinkProcessor;
import org.polarsys.capella.core.refinement.processor.InterfaceImplementationAndProvideProcessor;
import org.polarsys.capella.core.refinement.processor.InterfaceUsageAndRequireProcessor;
import org.polarsys.capella.core.refinement.scenarios.core.StaticRefinement;
import org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * This static refinement will update interface's implementation/usage.
 * It can be applied on any Component or ComponentArchitecture.
 * 
 */
public class UpdateLinks extends StaticRefinement {

	/**
	 * Default constructor
	 */
	public UpdateLinks() {
		super();
	}

	/**
	 * Constructor
	 *
	 * @param context
	 */
	public UpdateLinks(Component context) {
		this((ModelElement) context);
	}

	/**
	 * Constructor
	 * 
	 * @param context
	 */
	public UpdateLinks(ComponentArchitecture context) {
		this((ModelElement) context);
	}

	/**
	 * Constructor
	 * 
	 * @param context
	 */
	private UpdateLinks(ModelElement context) {
		super();

		setContext(context);
	}

	/**
	 * Add processors
	 * 
	 * @param context
	 */
	@Override
	public void setContext(ModelElement context) {
		super.setContext(context);

	  if ((context instanceof Component) || (context instanceof ComponentArchitecture)) {
			addPlug(new InterfaceUsageAndRequireProcessor((CapellaElement) context));
			addPlug(new InterfaceImplementationAndProvideProcessor((CapellaElement) context));
      addPlug(new CommunicationLinkProcessor((CapellaElement) context));
	  }
	}

  /**
   * Add processors
   * 
   * @param context
   */
  @Override
  public void setTarget(NamedElement target) {
    super.setTarget(target);

    if ((target instanceof Component) || (target instanceof ComponentArchitecture)) {
      IProcessor processor = new InterfaceUsageAndRequireProcessor();
      processor.setTarget(target);
      addPlug(processor);
      processor = new InterfaceImplementationAndProvideProcessor();
      processor.setTarget(target);
      addPlug(processor);
      processor = new CommunicationLinkProcessor();
      processor.setTarget(target);
      addPlug(processor);
    }
  }

	/**
	 * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#getName()
	 */
	public Object getName() {
		return null;
	}
}
