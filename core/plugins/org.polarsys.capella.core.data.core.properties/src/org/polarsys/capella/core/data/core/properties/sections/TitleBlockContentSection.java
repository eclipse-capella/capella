/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.core.properties.sections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.core.properties.Messages;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;

/**
 * The TitleBlockContentSection section.
 */
public class TitleBlockContentSection extends TitleBlockContainerSection {
  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return (eObjectToTest instanceof DAnnotation &&
        TitleBlockHelper.isContentTitleBlock((DAnnotation)eObjectToTest));
  }


  @Override
  public void setInput(IWorkbenchPart part, ISelection selection) {
    EObject newEObject = super.setInputSelection(part, selection);
    if (newEObject instanceof DAnnotation &&
        TitleBlockHelper.isContentTitleBlock((DAnnotation)newEObject)) {
      loadData(newEObject);
    }
  }
  
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage, Messages.getString("TitleBlock.Content.Label"));
  }
  
  @Override
  protected String getTextGroup(EObject capellaElement) {
      return ((DAnnotation)capellaElement).getDetails().get(TitleBlockHelper.CONTENT);
  }
}
