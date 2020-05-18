/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.helpers.listeners;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.NotificationFilter;
import org.polarsys.capella.common.helpers.transaction.ContainmentTreeListener;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 * A ContainmentTreeListener that is only interested in containment changes for
 * <i>semantic</i> elements.
 */
public abstract class CapellaContainmentTreeListener extends ContainmentTreeListener {

  public static class SemanticNotifierFilter extends NotificationFilter.Custom {

    @Override
    public boolean matches(Notification notification) {
      return CapellaResourceHelper.isSemanticElement(notification.getNotifier());
    }

  }

  CapellaContainmentTreeListener(){
    super(new SemanticNotifierFilter().and(DEFAULT_FILTER));
  }

}
