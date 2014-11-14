/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
   * @param element_p
   * @param column
   * @return
   */
  @SuppressWarnings("boxing")
  public Object getElementValue(EObject element_p, int column_p) {
    switch (column_p) {
      case 0:
        if (element_p instanceof AbstractPropertyValue) {
          String name = ((AbstractPropertyValue) element_p).getName();
          if (null == name) {
            name = ICommonConstants.EMPTY_STRING;
          }
          return name;
        }
        break;
      case 1:
        if (element_p instanceof BooleanPropertyValue)
          return ((BooleanPropertyValue) element_p).isValue();
        if (element_p instanceof IntegerPropertyValue)
          return ((IntegerPropertyValue) element_p).getValue();
        if (element_p instanceof FloatPropertyValue)
          return ((FloatPropertyValue) element_p).getValue();
        if (element_p instanceof StringPropertyValue) {
          String value = ((StringPropertyValue) element_p).getValue();
          if (null == value) {
            value = ICommonConstants.EMPTY_STRING;
          }
          return value;
        }
        if (element_p instanceof EnumerationPropertyValue)
          return ((EnumerationPropertyValue) element_p).getValue();
        break;
      case 2:
        if (element_p instanceof AbstractPropertyValue) {
          String summary = ((AbstractPropertyValue) element_p).getSummary();
          if (null == summary) {
            summary = ICommonConstants.EMPTY_STRING;
          }
          return summary;
        }
    }
    return null;
  }

  /**
   * @param element_p
   * @param value_p
   */
  @SuppressWarnings("boxing")
  public void modifyElement(EObject element_p, int column_p, Object value_p) {
    switch (column_p) {
      case 0:
        if ((element_p instanceof AbstractPropertyValue) && (null != value_p)) {
          ((AbstractPropertyValue) element_p).setName((String) value_p);
        }
        break;
      case 1:
        if ((element_p instanceof BooleanPropertyValue) && (null != value_p)) {
          ((BooleanPropertyValue) element_p).setValue((Boolean) value_p);
        } else if ((element_p instanceof IntegerPropertyValue) && (null != value_p)) {
          ((IntegerPropertyValue) element_p).setValue((Integer) value_p);
        } else if ((element_p instanceof FloatPropertyValue) && (null != value_p)) {
          ((FloatPropertyValue) element_p).setValue((Float) value_p);
        } else if ((element_p instanceof StringPropertyValue) && (null != value_p)) {
          ((StringPropertyValue) element_p).setValue((String) value_p);
        } else if (element_p instanceof EnumerationPropertyValue) {
          // this value can be null (unset)
          ((EnumerationPropertyValue) element_p).setValue((EnumerationPropertyLiteral) value_p);
        }
        break;
      case 2:
        if ((element_p instanceof AbstractPropertyValue) && (null != value_p)) {
          ((AbstractPropertyValue) element_p).setSummary((String) value_p);
        }
    }
  }

  /**
   * Gets a cell editor for the given element.
   * 
   * @param composite_p
   * @param column_p
   * @param element_p
   * @return cell editor for the given element
   */
  public CellEditor getCellEditor(Composite composite_p, int column_p, Object element_p) {
    switch (column_p) {
      case 0:
        // Gets a simple text cell editor.
        return createCellEditor(composite_p, null);
      case 1:
        if (element_p instanceof BooleanPropertyValue) {
          return createCellEditor(composite_p, element_p);
        }
        if (element_p instanceof IntegerPropertyValue) {
          return createCellEditor(composite_p, element_p);
        }
        if (element_p instanceof StringPropertyValue) {
          return createCellEditor(composite_p, element_p);
        }
        if (element_p instanceof FloatPropertyValue) {
          return createCellEditor(composite_p, element_p);
        }
        if (element_p instanceof EnumerationPropertyValue) {
          return createCellEditor(composite_p, element_p);
        }
        // Gets a dummy cell editor.
        return createCellEditor(composite_p, null);
      case 2:
        // Gets a simple text cell editor.
        return createCellEditor(composite_p, null);
    }
    return null;
  }

  /**
   * Creates a cell editor for the given element.
   * 
   * @param composite_p
   * @param element_p
   * @return cell editor for the given element
   */
  private CellEditor createCellEditor(Composite composite_p, Object element_p) {
    if (null == element_p) {
      return new TextCellEditor(composite_p);
    }

    IItemPropertySource provider = (IItemPropertySource) CapellaAdapterFactoryProvider.getInstance().getAdapterFactory().adapt(element_p, IItemPropertySource.class);
    IItemPropertyDescriptor itemDescriptor = provider.getPropertyDescriptor(element_p, VALUE);
    PropertyDescriptor descriptor = new PropertyDescriptor(element_p, itemDescriptor);

    if (element_p instanceof EnumerationPropertyValue) {
      ArrayList<EObject> list = new ArrayList<EObject>();
      IBusinessQuery query = BusinessQueriesProvider.getInstance().getContribution(
        CapellacorePackage.Literals.ENUMERATION_PROPERTY_VALUE,
        CapellacorePackage.Literals.ENUMERATION_PROPERTY_VALUE__VALUE);
      if (null != query) {
        list.addAll(query.getCurrentElements((CapellaElement) element_p, false));
        list.addAll(query.getAvailableElements((CapellaElement) element_p));
      }
      // this null value an expected value
      if (!list.contains(null)) {
        list.add(0, null);
      }

      return new ExtendedComboBoxCellEditor(composite_p, list, descriptor.getLabelProvider(), itemDescriptor.isSortChoices(element_p));
    }
    return descriptor.createPropertyEditor(composite_p);
  }
}
