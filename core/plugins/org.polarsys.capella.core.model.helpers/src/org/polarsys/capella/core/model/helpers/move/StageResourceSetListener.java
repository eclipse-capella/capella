/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.helpers.move;

import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;

public class StageResourceSetListener extends ResourceSetListenerImpl {

  private final Stage stage;

  public StageResourceSetListener(Stage s) {
    super(NotificationFilter.READ.negated());
    this.stage = s;
  }

  @Override
  public void resourceSetChanged(ResourceSetChangeEvent event) {
    stage.refresh();
  }

  @Override
  public boolean isPostcommitOnly() {
    return true;
  }

}
