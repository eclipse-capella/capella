/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
	 * @param context_p
	 */
	public UpdateLinks(Component context_p) {
		this((ModelElement) context_p);
	}

	/**
	 * Constructor
	 * 
	 * @param context_p
	 */
	public UpdateLinks(ComponentArchitecture context_p) {
		this((ModelElement) context_p);
	}

	/**
	 * Constructor
	 * 
	 * @param context_p
	 */
	private UpdateLinks(ModelElement context_p) {
		super();

		setContext(context_p);
	}

	/**
	 * Add processors
	 * 
	 * @param context_p
	 */
	@Override
	public void setContext(ModelElement context_p) {
		super.setContext(context_p);

	  if ((context_p instanceof Component) || (context_p instanceof ComponentArchitecture)) {
			addPlug(new InterfaceUsageAndRequireProcessor((CapellaElement) context_p));
			addPlug(new InterfaceImplementationAndProvideProcessor((CapellaElement) context_p));
      addPlug(new CommunicationLinkProcessor((CapellaElement) context_p));
	  }
	}

  /**
   * Add processors
   * 
   * @param context_p
   */
  @Override
  public void setTarget(NamedElement target_p) {
    super.setTarget(target_p);

    if ((target_p instanceof Component) || (target_p instanceof ComponentArchitecture)) {
      IProcessor processor = new InterfaceUsageAndRequireProcessor();
      processor.setTarget(target_p);
      addPlug(processor);
      processor = new InterfaceImplementationAndProvideProcessor();
      processor.setTarget(target_p);
      addPlug(processor);
      processor = new CommunicationLinkProcessor();
      processor.setTarget(target_p);
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
