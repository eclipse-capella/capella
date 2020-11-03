/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.BooleanValueGroup;
import org.polarsys.capella.core.ui.properties.fields.EnumerationValueGroup;
import org.polarsys.capella.core.ui.properties.fields.TextAreaValueGroup;

/**
 *
 */
public class CapellaManagementPropertySection extends AbstractSection implements IFilter {

  private BooleanValueGroup _visibleInDocGroup;
  private BooleanValueGroup _visibleInLMGroup;
  private EnumerationValueGroup _status;
  private TextAreaValueGroup _review;

  /**
   * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
   *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {

    boolean displayedInWizard = isDisplayedInWizard();

    _visibleInDocGroup = new BooleanValueGroup(parent, Messages.VisibleInDocGroup_Label, getWidgetFactory());
    _visibleInDocGroup.setDisplayedInWizard(displayedInWizard);

    _visibleInLMGroup = new BooleanValueGroup(parent, Messages.VisibleForTraceabilityGroup_Label, getWidgetFactory());
    _visibleInLMGroup.setDisplayedInWizard(displayedInWizard);

    _status = new EnumerationValueGroup(parent, Messages.ProgressStatus_Label, getWidgetFactory());
    _status.setDisplayedInWizard(displayedInWizard);

    _review = new TextAreaValueGroup(_status.getParent(), Messages.ReviewGroup_Label, getWidgetFactory(), true);
    _review.setDisplayedInWizard(displayedInWizard);
  }

  @Override
  protected int getColumnCount() {
    return 1;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    _visibleInDocGroup.loadData(capellaElement, ModellingcorePackage.eINSTANCE.getPublishableElement_VisibleInDoc());
    _visibleInLMGroup.loadData(capellaElement, ModellingcorePackage.eINSTANCE.getPublishableElement_VisibleInLM());
    _status.loadData(capellaElement, CapellacorePackage.eINSTANCE.getCapellaElement_Status());
    _review.loadData(capellaElement, CapellacorePackage.eINSTANCE.getCapellaElement_Review());
  }

  /**
   * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
   */
  @Override
  public void setInput(IWorkbenchPart part, ISelection selection) {
    if (selection instanceof StructuredSelection) {
      EObject selectedElt = CapellaAdapterHelper.resolveBusinessObject(((StructuredSelection) selection).getFirstElement());
      if (selectedElt instanceof CapellaElement) {
        loadData((CapellaElement) selectedElt);
      }
    }
    super.setInput(part, selection);
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObj = CapellaAdapterHelper.resolveDescriptorOrBusinessObject(toTest);
    return (eObj instanceof CapellaElement);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.add(_visibleInDocGroup);
    fields.add(_visibleInLMGroup);
    fields.add(_status);
    fields.add(_review);

    return fields;
  }
}
