/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.model.ju.activityExplorer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.explorer.activity.ui.hyperlinkadapter.pa.NewClassDiagramAdapter;

public class CDBPhysicalActivityExplorerTestCase extends CDBActivityExplorerTestCase {

  @Override
  public void initLink() {
    link = new MyNewClassDiagramAdapter();
  }

  @Override
  public boolean getResultOfCreateDiagram() {
    return ((MyNewClassDiagramAdapter) link).myCreateDiagram(structure, session);
  }

  @Override
  public ModelElement getTestModelElement() {
    MyNewClassDiagramAdapter cdbLink = new MyNewClassDiagramAdapter();
    return cdbLink.getMyModelElement(project);
  }

  @Override
  public DataPkg getStructure() {
    return context.getSemanticElement(PA__DATA);
  }

  class MyNewClassDiagramAdapter extends NewClassDiagramAdapter {

    @Override
    protected boolean useDefaultName() {
      return true;
    }

    public ModelElement getMyModelElement(EObject rootSemanticModel) {
      return getModelElement(rootSemanticModel);
    }

    public boolean myCreateDiagram(final EObject project, final Session session) {
      return createDiagram(project, session);
    }
  }

}
