/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.viewers;

import java.util.ArrayList;

import org.eclipse.emf.common.ui.celleditor.ExtendedComboBoxCellEditor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.business.queries.capellacore.BusinessQueriesProvider;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.BooleanPropertyValue;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyValue;
import org.polarsys.capella.core.data.capellacore.FloatPropertyValue;
import org.polarsys.capella.core.data.capellacore.IntegerPropertyValue;
import org.polarsys.capella.core.data.capellacore.StringPropertyValue;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;

/**
 */
public class AbstractPropertyValueCellEditorProvider implements ICellEditorProvider {
  //
  private static final String VALUE = "value"; //$NON-NLS-1$

  /**
   * @param element
   * @param column
   * @return
   */
  @SuppressWarnings("boxing")
  public Object getElementValue(EObject element, int column) {
    switch (column) {
      case 0:
        if (element instanceof AbstractPropertyValue) {
          String name = ((AbstractPropertyValue) element).getName();
          if (null == name) {
            name = ICommonConstants.EMPTY_STRING;
          }
          return name;
        }
        break;
      case 1:
        if (element instanceof BooleanPropertyValue)
          return ((BooleanPropertyValue) element).isValue();
        if (element instanceof IntegerPropertyValue)
          return ((IntegerPropertyValue) element).getValue();
        if (element instanceof FloatPropertyValue)
          return ((FloatPropertyValue) element).getValue();
        if (element instanceof StringPropertyValue) {
          String value = ((StringPropertyValue) element).getValue();
          if (null == value) {
            value = ICommonConstants.EMPTY_STRING;
          }
          return value;
        }
        if (element instanceof EnumerationPropertyValue)
          return ((EnumerationPropertyValue) element).getValue();
        break;
      case 2:
        if (element instanceof AbstractPropertyValue) {
          String summary = ((AbstractPropertyValue) element).getSummary();
          if (null == summary) {
            summary = ICommonConstants.EMPTY_STRING;
          }
          return summary;
        }
    }
    return null;
  }

  /**
   * @param element
   * @param value
   */
  @SuppressWarnings("boxing")
  public void modifyElement(EObject element, int column, Object value) {
    switch (column) {
      case 0:
        if ((element instanceof AbstractPropertyValue) && (null != value)) {
          ((AbstractPropertyValue) element).setName((String) value);
        }
        break;
      case 1:
        if ((element instanceof BooleanPropertyValue) && (null != value)) {
          ((BooleanPropertyValue) element).setValue((Boolean) value);
        } else if ((element instanceof IntegerPropertyValue) && (null != value)) {
          ((IntegerPropertyValue) element).setValue((Integer) value);
        } else if ((element instanceof FloatPropertyValue) && (null != value)) {
          ((FloatPropertyValue) element).setValue((Float) value);
        } else if ((element instanceof StringPropertyValue) && (null != value)) {
          ((StringPropertyValue) element).setValue((String) value);
        } else if (element instanceof EnumerationPropertyValue) {
          // this value can be null (unset)
          ((EnumerationPropertyValue) element).setValue((EnumerationPropertyLiteral) value);
        }
        break;
      case 2:
        if ((element instanceof AbstractPropertyValue) && (null != value)) {
          ((AbstractPropertyValue) element).setSummary((String) value);
        }
    }
  }

  /**
   * Gets a cell editor for the given element.
   * 
   * @param composite
   * @param column
   * @param element
   * @return cell editor for the given element
   */
  public CellEditor getCellEditor(Composite composite, int column, Object element) {
    switch (column) {
      case 0:
        // Gets a simple text cell editor.
        return createCellEditor(composite, null);
      case 1:
        if (element instanceof BooleanPropertyValue) {
          return createCellEditor(composite, element);
        }
        if (element instanceof IntegerPropertyValue) {
          return createCellEditor(composite, element);
        }
        if (element instanceof StringPropertyValue) {
          return createCellEditor(composite, element);
        }
        if (element instanceof FloatPropertyValue) {
          return createCellEditor(composite, element);
        }
        if (element instanceof EnumerationPropertyValue) {
          return createCellEditor(composite, element);
        }
        // Gets a dummy cell editor.
        return createCellEditor(composite, null);
      case 2:
        // Gets a simple text cell editor.
        return createCellEditor(composite, null);
    }
    return null;
  }

  /**
   * Creates a cell editor for the given element.
   * 
   * @param composite
   * @param element
   * @return cell editor for the given element
   */
  private CellEditor createCellEditor(Composite composite, Object element) {
    if (null == element) {
      return new TextCellEditor(composite);
    }

    IItemPropertySource provider = (IItemPropertySource) CapellaAdapterFactoryProvider.getInstance().getAdapterFactory().adapt(element, IItemPropertySource.class);
    IItemPropertyDescriptor itemDescriptor = provider.getPropertyDescriptor(element, VALUE);
    PropertyDescriptor descriptor = new PropertyDescriptor(element, itemDescriptor);

    if (element instanceof EnumerationPropertyValue) {
      ArrayList<EObject> list = new ArrayList<EObject>();
      IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(
        CapellacorePackage.Literals.ENUMERATION_PROPERTY_VALUE,
        CapellacorePackage.Literals.ENUMERATION_PROPERTY_VALUE__VALUE);
      if (null != query) {
        list.addAll(query.getCurrentElements((CapellaElement) element, false));
        list.addAll(query.getAvailableElements((CapellaElement) element));
      }
      // this null value an expected value
      if (!list.contains(null)) {
        list.add(0, null);
      }

      return new ExtendedComboBoxCellEditor(composite, list, descriptor.getLabelProvider(), itemDescriptor.isSortChoices(element));
    }
    return descriptor.createPropertyEditor(composite);
  }
}
