/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search.searchfor;

import org.eclipse.swt.widgets.Group;
public class CapellaRightSearchForContainerArea extends AbstractCapellaSearchForContainerArea {
  public CapellaRightSearchForContainerArea(Group parent, AbstractCapellaSearchForContainerArea leftArea) {
    super(parent, leftArea);
  }

  @Override
  protected AbstractMetaModelParticipantsItemProvider getPartictipantsItemProvider() {
    return new AttributesParticipantsItemProvider(oterSideArea);
  }
}
