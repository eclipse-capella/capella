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
package org.polarsys.capella.common.platform.sirius.tig.ef;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.domain.EditingDomain;

public interface PreCommitValidationChecker {
	public boolean validCommit (Object obj, Notification notif);

	public String getPluginId();
	
	public String getMessage ();

	public Object getErrorElement();
	
	public Command triggerObject (Object obj, Notification notif);

	public boolean validDeletion(Object elementToDelete);
	
	public void setDomain (EditingDomain domain_p);

  /**
   * initialize the checker at the begin of a precommit
   */
  public void init();
}
