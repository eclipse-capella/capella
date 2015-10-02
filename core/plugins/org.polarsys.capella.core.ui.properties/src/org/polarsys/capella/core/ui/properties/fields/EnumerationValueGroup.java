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
package org.polarsys.capella.core.ui.properties.fields;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.model.utils.CollectionExt;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;

/**
 */
public class EnumerationValueGroup extends AbstractSemanticGroup {

  protected CCombo _valueField;
  private Map<String, EObject> _items;

  /**
   * @param parent
   * @param label
   * @param widgetFactory
   */
  public EnumerationValueGroup(Composite parent, String label, TabbedPropertySheetWidgetFactory widgetFactory) {
    super(parent, widgetFactory, false);

    _widgetFactory.createCLabel(_parent, label);
    _valueField = createValueComboField();
    _valueField.setEditable(false);
  }

  /**
   * @param label
   */
  protected CCombo createValueComboField() {
    CCombo valueField = _widgetFactory.createCCombo(_parent, SWT.BORDER);
    valueField.addSelectionListener(this);
    valueField.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    valueField.setEditable(false);
    return valueField;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(CapellaElement semanticElement, EStructuralFeature semanticFeature) {
    super.loadData(semanticElement, semanticFeature);

    loadComboValue();
  }

  /**
   * @see org.polarsys.capella.core.data.core.custom.properties.fields.AbstractSemanticField#loadData(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public void loadData(CapellaElement semanticElement) {
    loadComboValue();
  }

  /**
   *
   */
  public void loadComboValue() {
    if (null != _valueField) {
      if (null == _items) {
        _items = new HashMap<String, EObject>();
      }
      _items.clear();

      _items.put(ICommonConstants.EMPTY_STRING, null);
      for (CapellaElement element : getAvailableValues()) {
        if (element instanceof AbstractNamedElement) {
          _items.put(((AbstractNamedElement) element).getName(), element);
        }
      }

      _valueField.setItems(CollectionExt.getArray(_items.keySet()));

      int index = getSelection();
      if (index == -1) {
        _valueField.deselectAll();
      } else {
        _valueField.select(index);
      }
    }
  }

  /**
   *
   */
  protected int getSelection() {
    for (CapellaElement element : getCurrentValues()) {
      if (element instanceof AbstractNamedElement) {
        String selection = ((AbstractNamedElement) element).getName();
        for (int i = 0; i < _valueField.getItemCount(); i++) {
          if (selection.equals(_valueField.getItem(i))) {
            return i;
          }
        }
      }
    }
    return -1;
  }

  /**
   *
   */
  protected List<CapellaElement> getAvailableValues() {
    List<CapellaElement> result = new ArrayList<CapellaElement>(0);
    IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(CapellacorePackage.Literals.CAPELLA_ELEMENT, _semanticFeature);
    if (null != query) {
      result.addAll(query.getAvailableElements(_semanticElement));
    }
    return result;
  }

  /**
   *
   */
  protected List<CapellaElement> getCurrentValues() {
    List<CapellaElement> result = new ArrayList<CapellaElement>(0);
    IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(CapellacorePackage.Literals.CAPELLA_ELEMENT, _semanticFeature);
    if (null != query) {
      result.addAll(query.getCurrentElements(_semanticElement, false));
    }
    return result;
  }

  /**
   * @param comboField combo field to be filled
   */
  @Override
  protected void fillComboField(CCombo comboField) {
    if (comboField.equals(_valueField)) {
      String selecteditem = _valueField.getItem(_valueField.getSelectionIndex());
      setDataValue(_semanticElement, _semanticFeature, _items.get(selecteditem));
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled) {
    if (null != _valueField && !_valueField.isDisposed()) {
      _valueField.setEnabled(enabled);
    }
  }
}
