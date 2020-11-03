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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.providers;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;

import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;

/**
 *
 */
public class TraceStore implements IPreferenceStore {
  // The Capella element.
  protected CapellaElement _element;

  /**
   * @param element_p
   */
  public TraceStore(CapellaElement element_p) {
    _element = element_p;
  }
  
  /**
   * @see IPreferenceStore#setValue(String, String)
   */
  public void setValue(String name_p, String value_p) {
    if (null == _element) {
      return;
    }

    if (name_p.equalsIgnoreCase(CapellacorePackage.Literals.CAPELLA_ELEMENT__DESCRIPTION.getName())) {
      _element.setDescription(value_p);
    }
    else if (name_p.equalsIgnoreCase(CapellacorePackage.Literals.CAPELLA_ELEMENT__SUMMARY.getName())) {
      _element.setSummary(value_p);
    }
  }

  /**
   * @see IPreferenceStore#getString(String)
   */
  public String getString(String name_p) {
    String value = ""; //$NON-NLS-1$
    if (null == _element) {
      return value;
    }

    if (name_p.equalsIgnoreCase(CapellacorePackage.Literals.CAPELLA_ELEMENT__DESCRIPTION.getName())) {
      value = _element.getDescription();
    }
    else if (name_p.equalsIgnoreCase(CapellacorePackage.Literals.CAPELLA_ELEMENT__SUMMARY.getName())) {
      value = _element.getSummary();
    }

    return value;
  }

  /**
   * @see IPreferenceStore#addPropertyChangeListener(IPropertyChangeListener)
   */
  public void addPropertyChangeListener(IPropertyChangeListener listener_p) {
    // Do nothing.
  }

  /**
   * @see IPreferenceStore#contains(String)
   */
  public boolean contains(String name_p) {
    // Do nothing.
    return false;
  }

  /**
   * @see IPreferenceStore#firePropertyChangeEvent(String, Object, Object)
   */
  public void firePropertyChangeEvent(String name_p, Object oldValue_p, Object newValue_p) {
    // Do nothing.
  }

  /**
   * @see IPreferenceStore#getBoolean(String)
   */
  public boolean getBoolean(String name_p) {
    // Do nothing.
    return false;
  }

  /**
   * @see IPreferenceStore#getDefaultBoolean(String)
   */
  public boolean getDefaultBoolean(String name_p) {
    // Do nothing.
    return false;
  }

  /**
   * @see IPreferenceStore#getDefaultDouble(String)
   */
  public double getDefaultDouble(String name_p) {
    // Do nothing.
    return 0;
  }

  /**
   * @see IPreferenceStore#getDefaultFloat(String)
   */
  public float getDefaultFloat(String name_p) {
    // Do nothing.
    return 0;
  }

  /**
   * @see IPreferenceStore#getDefaultInt(String)
   */
  public int getDefaultInt(String name_p) {
    // Do nothing.
    return 0;
  }

  /**
   * @see IPreferenceStore#getDefaultLong(String)
   */
  public long getDefaultLong(String name_p) {
    // Do nothing.
    return 0;
  }

  /**
   * @see IPreferenceStore#getDefaultString(String)
   */
  public String getDefaultString(String name_p) {
    // Do nothing.
    return null;
  }

  /**
   * @see IPreferenceStore#getDouble(String)
   */
  public double getDouble(String name_p) {
    // Do nothing.
    return 0;
  }

  /**
   * @see IPreferenceStore#getFloat(String)
   */
  public float getFloat(String name_p) {
    // Do nothing.
    return 0;
  }

  /**
   * @see IPreferenceStore#getInt(String)
   */
  public int getInt(String name_p) {
    // Do nothing.
    return 0;
  }

  /**
   * @see IPreferenceStore#getLong(String)
   */
  public long getLong(String name_p) {
    // Do nothing.
    return 0;
  }

  /**
   * @see IPreferenceStore#isDefault(String)
   */
  public boolean isDefault(String name_p) {
    // Do nothing.
    return false;
  }

  /**
   * @see IPreferenceStore#needsSaving()
   */
  public boolean needsSaving() {
    // Do nothing.
    return false;
  }

  /**
   * @see IPreferenceStore#putValue(String, String)
   */
  public void putValue(String name_p, String value_p) {
    // Do nothing.
  }

  /**
   * @see IPreferenceStore#removePropertyChangeListener(IPropertyChangeListener)
   */
  public void removePropertyChangeListener(IPropertyChangeListener listener_p) {
    // Do nothing.
  }

  /**
   * @see IPreferenceStore#setDefault(String, double)
   */
  public void setDefault(String name_p, double value_p) {
    // Do nothing.
  }

  /**
   * @see IPreferenceStore#setDefault(String, float)
   */
  public void setDefault(String name_p, float value_p) {
    // Do nothing.
  }

  /**
   * @see IPreferenceStore#setDefault(String, int)
   */
  public void setDefault(String name_p, int value_p) {
    // Do nothing.
  }

  /**
   * @see IPreferenceStore#setDefault(String, long)
   */
  public void setDefault(String name_p, long value_p) {
    // Do nothing.
  }

  /**
   * @see IPreferenceStore#setDefault(String, String)
   */
  public void setDefault(String name_p, String defaultObject_p) {
    // Do nothing.
  }

  /**
   * @see IPreferenceStore#setDefault(String, boolean)
   */
  public void setDefault(String name_p, boolean value_p) {
    // Do nothing.
  }

  /**
   * @see IPreferenceStore#setToDefault(String)
   */
  public void setToDefault(String name_p) {
    // Do nothing.
  }

  /**
   * @see IPreferenceStore#setValue(String, double)
   */
  public void setValue(String name_p, double value_p) {
    // Do nothing.
  }

  /**
   * @see IPreferenceStore#setValue(String, float)
   */
  public void setValue(String name_p, float value_p) {
    // Do nothing.
  }

  /**
   * @see IPreferenceStore#setValue(String, int)
   */
  public void setValue(String name_p, int value_p) {
    // Do nothing.
  }

  /**
   * @see IPreferenceStore#setValue(String, long)
   */
  public void setValue(String name_p, long value_p) {
    // Do nothing.
  }

  /**
   * @see IPreferenceStore#setValue(String, boolean)
   */
  public void setValue(String name_p, boolean value_p) {
    // Do nothing.
  }
}
