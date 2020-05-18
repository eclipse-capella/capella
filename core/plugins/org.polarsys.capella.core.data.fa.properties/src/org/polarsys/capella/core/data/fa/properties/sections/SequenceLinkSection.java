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
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.core.properties.sections.CapellaElementSection;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.properties.controllers.SequenceLinkLinksController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.ConstraintReferenceGroup;
import org.polarsys.capella.core.ui.properties.fields.MultipleSemanticField;

/**
 * The SequenceLink section.
 */
public class SequenceLinkSection extends CapellaElementSection {

  protected ConstraintReferenceGroup conditionField;
  protected MultipleSemanticField linksField;

  /**
   * Default constructor.
   */
  public SequenceLinkSection() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    conditionField = new ConstraintReferenceGroup(Collections.singletonMap(Messages.SequenceLinkSection_Condition_Label,
        FaPackage.Literals.SEQUENCE_LINK__CONDITION));
    conditionField.createControls(parent, getWidgetFactory(), displayedInWizard);

    linksField = new MultipleSemanticField(getReferencesGroup(), Messages.SequenceLinkSection_Links_Label,
        getWidgetFactory(), new SequenceLinkLinksController());
    linksField.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    conditionField.loadData(capellaElement);
    linksField.loadData(capellaElement, FaPackage.eINSTANCE.getSequenceLink_Links());
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == FaPackage.eINSTANCE.getSequenceLink()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.addAll(conditionField.getFields());
    fields.add(linksField);

    return fields;
  }
}
