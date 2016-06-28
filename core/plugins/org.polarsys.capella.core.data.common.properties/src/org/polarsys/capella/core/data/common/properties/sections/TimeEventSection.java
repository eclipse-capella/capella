/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.common.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.common.properties.fields.TimeEventKindGroup;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;

public class TimeEventSection extends StateEventSection {

  private TimeEventKindGroup _kindGroup;

  /**
   * {@inheritDoc}
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    boolean displayedInWizard = isDisplayedInWizard();
    super.createControls(parent, aTabbedPropertySheetPage);

    _kindGroup = new TimeEventKindGroup(_rootParentComposite, getWidgetFactory(), true);
    _kindGroup.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {

    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();
    fields.addAll(super.getSemanticFields());

    fields.add(_kindGroup);

    return fields;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);
    _kindGroup.loadData(capellaElement, CapellacommonPackage.Literals.TIME_EVENT__KIND);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == CapellacommonPackage.eINSTANCE.getTimeEvent()));
  }
}
