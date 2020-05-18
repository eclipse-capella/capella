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
package org.polarsys.capella.core.data.core.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.core.properties.Messages;
import org.polarsys.capella.core.ui.properties.controllers.SimpleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.SimpleSemanticField;

/**
 * The AbstractTrace section.
 */
public abstract class AbstractTraceSection extends CapellaElementSection {

  private SimpleSemanticField _sourceElementField;
  private SimpleSemanticField _targetElementField;

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    _sourceElementField = new SimpleSemanticField(getReferencesGroup(), Messages.getString("AbstractTrace.SourceElement.Label"), getWidgetFactory(), new SimpleSemanticFieldController()); //$NON-NLS-1$
    _sourceElementField.setDisplayedInWizard(displayedInWizard);
    _sourceElementField.setEnabled(false);

    _targetElementField = new SimpleSemanticField(getReferencesGroup(), Messages.getString("AbstractTrace.TargetElement.Label"), getWidgetFactory(), new SimpleSemanticFieldController()); //$NON-NLS-1$
    _targetElementField.setDisplayedInWizard(displayedInWizard);
    _targetElementField.setEnabled(false);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    _sourceElementField.loadData(capellaElement, ModellingcorePackage.eINSTANCE.getAbstractTrace_SourceElement());
    _targetElementField.loadData(capellaElement, ModellingcorePackage.eINSTANCE.getAbstractTrace_TargetElement());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.add(_sourceElementField);
    fields.add(_targetElementField);

    return fields;
  }
}
