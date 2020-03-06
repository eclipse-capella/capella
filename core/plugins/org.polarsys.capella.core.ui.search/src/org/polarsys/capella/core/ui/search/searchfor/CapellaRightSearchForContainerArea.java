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

import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.dialogs.PatternFilter;
import org.polarsys.capella.core.ui.search.CapellaSearchPage;

public class CapellaRightSearchForContainerArea extends AbstractCapellaSearchForContainerArea {
  protected AbstractMetaModelParticipantsItemProvider partictipantsItemProvider;
  
  public CapellaRightSearchForContainerArea(Group parent, AbstractCapellaSearchForContainerArea leftArea,
       CapellaSearchPage searchPage) {
    super(parent, leftArea, searchPage);
  }

  @Override
  protected AbstractMetaModelParticipantsItemProvider getPartictipantsItemProvider() {
    if (partictipantsItemProvider == null) {
      partictipantsItemProvider = new AttributesParticipantsItemProvider(otherSideArea);
    }
    return partictipantsItemProvider;
  }

  protected PatternFilter createPatternFilter() {
    return new PatternFilter();
  }
  
  public void updateSearchSettings() {
    searchPage.getCapellaSearchSettings().setSearchAttributes(getCheckedElements());
  }
}
