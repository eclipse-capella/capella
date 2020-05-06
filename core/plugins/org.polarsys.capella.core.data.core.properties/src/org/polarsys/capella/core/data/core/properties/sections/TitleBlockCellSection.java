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
package org.polarsys.capella.core.data.core.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.core.properties.fields.TitleBlockBasicElementGroup;
import org.polarsys.capella.core.model.helpers.TitleBlockExt;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.sections.AbstractSection;

/**
 * The PropertyValueGroup section.
 */
public class TitleBlockCellSection extends AbstractSection {
  private static final String CELL_NAME = "Name:";
  private static final String CELL_CONTENT = "Content:";
  protected TitleBlockBasicElementGroup titleBlockBasicElementGroup;

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);

    return (eObjectToTest instanceof DAnnotation
        && TitleBlockExt.isCellTitleBlock((DAnnotation) eObjectToTest));
  }

  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();
    fields.add(titleBlockBasicElementGroup);
    return fields;
  }

  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    titleBlockBasicElementGroup = new TitleBlockBasicElementGroup(parent, getWidgetFactory());
    titleBlockBasicElementGroup.setDisplayedInWizard(isDisplayedInWizard());
  }

  @Override
  public void loadData(EObject capellaElement) {
    if (capellaElement instanceof DAnnotation) {
      super.loadData(capellaElement);
      DAnnotation titleBlockCell = (DAnnotation) capellaElement;
      
      // if the cell belongs to a Diagram Title Block, disable the edit
      DAnnotation titleBlockContainer = TitleBlockExt.getParentTitleBlock(titleBlockCell);
      if(TitleBlockExt.isDiagramTitleBlock(titleBlockContainer)) {
        super.setEnabled(false);
      }
      
      String name = titleBlockCell.getDetails().get(CELL_NAME);
      String content = titleBlockCell.getDetails().get(CELL_CONTENT);
      titleBlockBasicElementGroup.loadData(capellaElement, name, content);
    }
  }

  @Override
  public void setInput(IWorkbenchPart part, ISelection selection) {
    EObject newEObject = super.setInputSelection(part, selection);
    if (newEObject instanceof DAnnotation
        && TitleBlockExt.isCellTitleBlock((DAnnotation) newEObject)) {
      loadData(newEObject);
    }
  }
}
