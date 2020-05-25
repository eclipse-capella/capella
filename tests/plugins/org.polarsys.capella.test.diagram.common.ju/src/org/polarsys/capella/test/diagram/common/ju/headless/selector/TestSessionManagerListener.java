/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.common.ju.headless.selector;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManagerListener;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.ui.PlatformUI;

public class TestSessionManagerListener extends SessionManagerListener.Stub {

  @Override
  public void notify(Session updated, int notification) {
    super.notify(updated, notification);
    boolean enableHeadless = (PlatformUI.getTestableObject().getTestHarness() != null);
    if (notification == SessionListener.OPENED && enableHeadless) {
      ((DAnalysisSession) updated).setAnalysisSelector(HeadlessCapellaAnalysisSelector.INSTANCE);
    }
  }
}
