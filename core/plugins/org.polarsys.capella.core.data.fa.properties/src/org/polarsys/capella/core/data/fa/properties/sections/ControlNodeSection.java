/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.fa.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.core.properties.sections.CapellaElementSection;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.properties.fields.ControlNodeKindGroup;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;

/**
 * The ControlNode section.
 */
public class ControlNodeSection extends CapellaElementSection {

  private ControlNodeKindGroup controlNodeKindGroup;
  private boolean showControlNodeKind;

  /**
   * Default constructor.
   */
  public ControlNodeSection() {
    this(true);
  }

  /**
   * Constructor.
   * 
   * @param showFunctionalChainRealizations
   */
  public ControlNodeSection(boolean showControlNodeKind) {
    this.showControlNodeKind = showControlNodeKind;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    if (showControlNodeKind) {
      controlNodeKindGroup = new ControlNodeKindGroup(parent, getWidgetFactory(), true) {
        /**
         * {@inheritDoc}
         */
        @Override
        protected void selectButton(Button button, Enumerator enumerated) {
          super.selectButton(button, enumerated);
        }
      };
      controlNodeKindGroup.setDisplayedInWizard(displayedInWizard);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    if (null != controlNodeKindGroup) {
      controlNodeKindGroup.loadData(capellaElement, FaPackage.Literals.CONTROL_NODE__KIND);
    }
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == FaPackage.eINSTANCE.getControlNode()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(controlNodeKindGroup);

    return fields;
  }
}
