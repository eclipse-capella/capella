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
package org.polarsys.capella.core.dashboard.actions.sa;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.polarsys.capella.core.dashboard.actions.AbstractCapellaAction;
import org.polarsys.capella.core.dashboard.hyperlinkadapter.sa.PerformOpCapabilityToSystemCapabilityTransitionAdapter;
import org.polarsys.capella.core.data.capellamodeller.Project;

/**
 * Perform an automated transition of Operational Capability to a System Capability.
 */
public class PerformOpCapabilityToSystemCapabilityAction extends AbstractCapellaAction {
  /**
   * Constructor.
   * @param text_p
   * @param image_p
   * @param capellaProject_p
   * @param session_p
   */
  public PerformOpCapabilityToSystemCapabilityAction(Project capellaProject_p, Session session_p) {
    super(Messages.PerformOpCapabilityToSystemCapabilityAction_Title, AbstractUIPlugin.imageDescriptorFromPlugin("org.polarsys.capella.core.data.gen.edit", //$NON-NLS-1$
        "icons/full/obj16/Capability.gif"), capellaProject_p, session_p); //$NON-NLS-1$
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void doRun(Project capellaProject_p, Session session_p) {
    new PerformOpCapabilityToSystemCapabilityTransitionAdapter(session_p).linkActivated(null);
  }
}
