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
package org.polarsys.capella.core.platform.sirius.ui.navigator.listeners;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.session.Session;

import org.polarsys.capella.core.platform.sirius.ui.navigator.viewer.ISessionListener;
import org.polarsys.capella.core.platform.sirius.ui.session.CapellaSessionHelper;

/**
 *
 */
public class SessionCheckingListener implements ISessionListener {
  /**
   * Constructor
   */
	public SessionCheckingListener() {
	  //
	}

  /**
   * {@inheritDoc}
   */
	public void sessionOpening(final Session session_p) {
	  //
	}

  /**
   * {@inheritDoc}
   */
	public void sessionOpened(final Session session_p) {
		if (!CapellaSessionHelper.checkModelsCompliancy(session_p)) {
		  CapellaSessionHelper.cleanResourceSet(session_p);
		  session_p.close(new NullProgressMonitor());
		}
	}

  /**
   * {@inheritDoc}
   */
	public void sessionClosing(Session session_p) {
	  //
	}

	/**
	 * {@inheritDoc}
	 */
	public void sessionClosed(Session session_p) {
	  //
	}
}
