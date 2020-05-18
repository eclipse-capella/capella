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
package org.polarsys.capella.core.ui.toolkit.dialogs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.ui.providers.MDEAdapterFactoryLabelProvider;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.information.Operation;
import org.polarsys.capella.core.data.information.Parameter;

public class CapellaShadedNonValidElementLabelProvider extends MDEAdapterFactoryLabelProvider implements IColorProvider, IFontProvider {

  protected Font font = new Font(Display.getDefault(), "Arial", 9, SWT.NORMAL | SWT.ITALIC);
  protected Color invalidColor = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY);
  protected Color validColor = Display.getDefault().getSystemColor(SWT.COLOR_BLACK);
  protected boolean isValidElementsSet = false;
  protected List<EObject> validElements = new ArrayList<>(0);


  @Override
  public void dispose() {
    if (null != font) {
      font.dispose();
    }
    super.dispose();
  }

  @SuppressWarnings("unchecked")
  @Override
  public String getText(Object element) {
    if (element instanceof AbstractNamedElement) {
      String elementName = ((AbstractNamedElement) element).getName();

      if (element instanceof Part) {
        Part partEle = (Part) element;
        AbstractType abstractType = partEle.getAbstractType();
        if (abstractType != null) {
          String typeName = abstractType.getName();
          if (typeName != null) {
            elementName = elementName + ": " + typeName; //$NON-NLS-1$
          } else {
            elementName = elementName + ": <undefined>"; //$NON-NLS-1$
          }
        } else {
          elementName = elementName + ": <undefined>"; //$NON-NLS-1$
        }
      } else if (element instanceof Operation) {
        Operation operation = (Operation) element;
        EList<Parameter> parameters = operation.getOwnedParameters();
        if (!parameters.isEmpty()) {
          elementName = elementName + "("; //$NON-NLS-1$
          for (Iterator iter = parameters.iterator(); iter.hasNext();) {
            Parameter parameter = (Parameter) iter.next();
            elementName = elementName + " " + parameter.getName(); //$NON-NLS-1$
            if (iter.hasNext()) {
              elementName = elementName + ","; //$NON-NLS-1$
            }
          }
          elementName = elementName + " )";//$NON-NLS-1$
        } else {
          elementName = elementName + " ()";//$NON-NLS-1$
        }

      }
      return elementName;
    } else if (element instanceof Entry) {
      Entry<Integer, EObject> entry = (Entry<Integer, EObject>) element;
      EObject key = entry.getValue();
      if (key != null && key instanceof AbstractNamedElement) {
        return ((AbstractNamedElement) key).getName();
      }
    }
    return super.getText(element);
  }

  @SuppressWarnings("unchecked")
  public Font getFont(Object element) {
    if (element instanceof AbstractNamedElement) {
      if (isValidElementsSet && !validElements.contains(element)) {
        return font;
      }
    } else if (element instanceof Entry) {
      Entry<Integer, EObject> entry = (Entry<Integer, EObject>) element;
      EObject key = entry.getValue();
      if (key instanceof AbstractNamedElement) {
        if (isValidElementsSet && !validElements.contains(key)) {
          return font;
        }
      }
    }

    return null;
  }

  public Color getBackground(Object element) {
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
   */
  @SuppressWarnings("unchecked")
  public Color getForeground(Object element) {
    if (element instanceof AbstractNamedElement) {
      return getValidForeGround(element);
    } else if (element instanceof Entry) {
      Entry<Integer, EObject> entry = (Entry<Integer, EObject>) element;
      EObject key = entry.getValue();
      if (key != null && key instanceof AbstractNamedElement) {
        return getValidForeGround(key);
      }
    }
    return validColor;
  }

  protected Color getValidForeGround(Object element) {
    if (isValidElementsSet && !validElements.contains(element)) {
      return invalidColor;
    }
    return validColor;
  }

  public List<EObject> get_notValidList() {
    return validElements;
  }

  public void set_ValidList(List<EObject> validList) {
    isValidElementsSet = true;
    validElements = validList;
  }

  @SuppressWarnings("unchecked")
  @Override
  /**
   * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getImage(java.lang.Object)
   */
  public Image getImage(Object element) {
    if (element instanceof AbstractNamedElement) {
      return super.getImage(element);
    } else if (element instanceof Entry) {
      Entry<Integer, EObject> entry = (Entry<Integer, EObject>) element;
      EObject key = entry.getValue();
      if (key != null && key instanceof AbstractNamedElement) {
        return super.getImage(key);
      }
    }
    return super.getImage(element);
  }
}
