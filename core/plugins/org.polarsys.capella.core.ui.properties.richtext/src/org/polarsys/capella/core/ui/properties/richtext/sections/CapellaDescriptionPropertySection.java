/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.properties.richtext.sections;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.richtext.fields.CapellaElementDescriptionGroup;
import org.polarsys.capella.core.ui.properties.sections.AbstractSection;

/**
 * @author Joao Barata
 */
public class CapellaDescriptionPropertySection extends AbstractSection implements IFilter {

  /**
   *
   */
  protected CapellaElementDescriptionGroup _descriptionGroup;

  /**
   * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
   *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    rootParentComposite.setLayout(new GridLayout());
    rootParentComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

    _descriptionGroup = new CapellaElementDescriptionGroup(rootParentComposite, (aTabbedPropertySheetPage != null) ? getWidgetFactory() : null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose() {
    super.dispose();

    if (null != _descriptionGroup) {
      _descriptionGroup.dispose();
      _descriptionGroup = null;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void handleParentBackground(Color color, Composite parent) {
    // Do nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    if (null != _descriptionGroup) {
      _descriptionGroup.loadData(capellaElement);
    }
  }

  /**
   * @see org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#setInput(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
   */
  @Override
  public void setInput(IWorkbenchPart part, ISelection selection) {
    if (selection instanceof StructuredSelection) {
      EObject elt = CapellaAdapterHelper.resolveSemanticObject(((StructuredSelection) selection).getFirstElement());
      if (elt instanceof CapellaElement) {
        if (elt.eClass().equals(CsPackage.eINSTANCE.getPart())) {
          boolean allowMultiplePart = TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven((Part) elt));
          if (!allowMultiplePart) {
            AbstractType type = ((Part) elt).getAbstractType();
            if ((type != null) && !(type instanceof ConfigurationItem)) {
              super.setInput(part, new StructuredSelection(type));
              loadData((CapellaElement) type);
              return;
            }
          }
        }
        loadData((CapellaElement) elt);
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
  public void setEnabled(boolean enabled) {
    super.setEnabled(enabled);

    if (null != _descriptionGroup) {
      _descriptionGroup.setEnabled(enabled);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    return Collections.emptyList();
  }
}
