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
package org.polarsys.capella.test.framework.helpers;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.core.libraries.model.AbstractCapellaModel;

/**
 *
 */
public class SessionHelper {

  public static void saveSession(EObject context) {
    saveSession(context, new NullProgressMonitor());
  }

  public static void saveSession(AbstractCapellaModel context) {
    saveSession(context, new NullProgressMonitor());
  }

  public static void saveSession(AbstractCapellaModel model, IProgressMonitor monitor) {
    saveSession(model.getProject(model.getEditingDomain()), monitor);
  }

	public static void saveSession(EObject context, IProgressMonitor monitor) {
	  SessionManager.INSTANCE.getSession(context).save(monitor);
	}
}
