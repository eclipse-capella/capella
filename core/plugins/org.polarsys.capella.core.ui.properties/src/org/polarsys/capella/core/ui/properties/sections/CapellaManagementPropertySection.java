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
package org.polarsys.capella.core.ui.properties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.BooleanValueGroup;
import org.polarsys.capella.core.ui.properties.fields.EnumerationValueGroup;
import org.polarsys.capella.core.ui.properties.fields.TextAreaValueGroup;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

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
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    _rootParentComposite.setLayout(new GridLayout());
    _rootParentComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

    boolean displayedInWizard = isDisplayedInWizard();

    _visibleInDocGroup = new BooleanValueGroup(_rootParentComposite, Messages.VisibleInDocGroup_Label, getWidgetFactory());
    _visibleInDocGroup.setDisplayedInWizard(displayedInWizard);

    _visibleInLMGroup = new BooleanValueGroup(_rootParentComposite, Messages.VisibleForTraceabilityGroup_Label, getWidgetFactory());
    _visibleInLMGroup.setDisplayedInWizard(displayedInWizard);

    _status = new EnumerationValueGroup(_rootParentComposite, Messages.ProgressStatus_Label, getWidgetFactory());
    _status.setDisplayedInWizard(displayedInWizard);

    _review = new TextAreaValueGroup(_status.getParent(), Messages.ReviewGroup_Label, getWidgetFactory(), true);
    _review.setDisplayedInWizard(displayedInWizard);
  }

  /**
   * load the form data from given capella element.<br>
   * Default implementation registers an EMF adapter to listen to model changes if displayed in a wizard.
   */
  @Override
  public void loadData(CapellaElement capellaElement) {
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
      EObject selectedElt = CapellaAdapterHelper.resolveSemanticObject(((StructuredSelection) selection).getFirstElement());
      if (selectedElt instanceof CapellaElement) {
        if (selectedElt.eClass().equals(CsPackage.eINSTANCE.getPart())) {
          boolean allowMultiplePart = TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven((Part) selectedElt));
          if (!allowMultiplePart) {
            AbstractType type = ((Part) selectedElt).getAbstractType();
            if ((type != null) && !(type instanceof ConfigurationItem)) {
              super.setInput(part, new StructuredSelection(type));
              loadData((CapellaElement) type);
              return;
            }
          }
        }
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
    EObject eObj = CapellaAdapterHelper.resolveSemanticObject(toTest);
    if (eObj instanceof CapellaElement) {
      return true;
    }
    return false;
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
