/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.model.helpers.move;

import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;

public class StageResourceSetListener extends ResourceSetListenerImpl {

  private final Stage stage;

  public StageResourceSetListener(Stage s) {
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
