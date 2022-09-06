/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.titleblock;

import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManagerListener;

/**
 * This {@link SessionManagerListener} is used to add a {@link ResourceSetListener} intended to update the titleBLock
 * when the representation timeStamp is modified.
 * 
 * @author lfasani
 *
 */
public class TitleBlockSessionManagerListener extends SessionManagerListener.Stub {

  @Override
  public void notifyAddSession(Session newSession) {
    TitleBlockUpdaterListener titleBlockUpdaterListener = new TitleBlockUpdaterListener(newSession);
    newSession.getTransactionalEditingDomain().addResourceSetListener(titleBlockUpdaterListener);
  }
}
