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
package org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.oa;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.AbstractCapellaNewDiagramHyperlinkAdapter;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ModelQueryHelper;
import org.polarsys.capella.core.model.helpers.ProjectExt;
import org.polarsys.capella.core.sirius.analysis.IDiagramNameConstants;

/**
 * Create a new Operational Entity Blank diagram.
 */
public class NewOperationalEntityBlankDiagramAdapter extends AbstractCapellaNewDiagramHyperlinkAdapter {


  public NewOperationalEntityBlankDiagramAdapter() {
    super();
  }

  @Override
  public String getRepresentationName() {
    return IDiagramNameConstants.OPERATIONAL_ENTITY_BLANK_DIAGRAM_NAME;
  }

  @Override
  protected EObject getModelElement(EObject rootSemanticModel) {
    return BlockArchitectureExt.getComponentPkg(ModelQueryHelper.getOperationalAnalysis(ProjectExt.getProject(rootSemanticModel)), true);
  }

}
