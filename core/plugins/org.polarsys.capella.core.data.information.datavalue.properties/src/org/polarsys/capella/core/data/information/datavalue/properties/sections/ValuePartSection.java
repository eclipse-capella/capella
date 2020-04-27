/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.information.datavalue.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.core.properties.sections.CapellaElementSection;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.datavalue.ComplexValue;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.information.datavalue.ValuePart;
import org.polarsys.capella.core.data.information.datavalue.properties.Messages;
import org.polarsys.capella.core.data.information.datavalue.properties.controllers.ValuePartController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleSemanticField;

/**
 * The ValuePart section.
 */
public class ValuePartSection extends CapellaElementSection {

  private SimpleSemanticField referencedPropertyField;

  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    Group main = getWidgetFactory().createGroup(parent, ICommonConstants.EMPTY_STRING);
    main.setLayout(new GridLayout(6, false));
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    gd.horizontalSpan = 2;
    main.setLayoutData(gd);

    boolean displayedInWizard = isDisplayedInWizard();

    referencedPropertyField =
        new SimpleSemanticField(
            main,
            Messages.getString("ValuePart.ReferencedProperty"), getWidgetFactory(), new ValuePartController()); //$NON-NLS-1$
    referencedPropertyField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    referencedPropertyField.loadData(capellaElement, DatavaluePackage.eINSTANCE.getValuePart_ReferencedProperty());

    evaluateButtonStatus((ValuePart) capellaElement);
  }
  
  /**
   * If the type of the owning ComplexValue is unset, then the referencedProperty buttons (ADD and BROWSE) shall be disabled<br>
   * If the type of the owning ComplexValue is Collection, then the referencedProperty button ADD shall be disabled<br>
   * 
   * @param unionProperty
   */
  private void evaluateButtonStatus(ValuePart valuePart) {
    if (valuePart.eContainer() instanceof ComplexValue) {
      AbstractType type = ((ComplexValue) valuePart.eContainer()).getAbstractType();
      if (type != null) {
        boolean enable = !(type instanceof Collection);
        referencedPropertyField.enableOpenButton(enable);
      } else {
        referencedPropertyField.enableOpenButton(false);
      }
    } else {
      referencedPropertyField.enableOpenButton(false);
    }
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == DatavaluePackage.eINSTANCE.getValuePart()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(referencedPropertyField);

    return fields;
  }
}
