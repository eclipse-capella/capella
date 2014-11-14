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

import org.polarsys.capella.core.ui.toolkit.viewers.CapellaElementLabelProvider;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.information.Operation;
import org.polarsys.capella.core.data.information.Parameter;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 * The named element label provider.
 */
public class CapellaShadedNonValidElementLabelProvider extends CapellaElementLabelProvider implements IColorProvider, IFontProvider {

  protected Font _font;
  protected Color _invalidColor;
  protected Color _validColor;
  protected boolean _isValidElementsSet = false;
  protected List<EObject> _validElements = new ArrayList<EObject>(0);

  /**
   * Constructs the named element label provider.
   */
  public CapellaShadedNonValidElementLabelProvider() {
    // Do nothing.
  }

  @Override
  public void dispose() {
    if (null != _font) {
      _font.dispose();
    }
    super.dispose();
  }

  /**
   * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
   */
  @SuppressWarnings("unchecked")
  @Override
  public String getText(Object element_p) {
    if (element_p instanceof AbstractNamedElement) {
      String eleMentName = ((AbstractNamedElement) element_p).getName();

      if (element_p instanceof Part) {
        Part partEle = (Part) element_p;
        AbstractType abstractType = partEle.getAbstractType();
        if (abstractType != null) {
          String typeName = abstractType.getName();
          if (typeName != null) {
            eleMentName = eleMentName + ": " + typeName; //$NON-NLS-1$
          } else {
            eleMentName = eleMentName + ": <undefined>"; //$NON-NLS-1$
          }
        } else {
          eleMentName = eleMentName + ": <undefined>"; //$NON-NLS-1$
        }
      } else if (element_p instanceof Operation) {
        Operation operation = (Operation) element_p;
        EList<Parameter> parameters = operation.getOwnedParameters();
        if (!parameters.isEmpty()) {
          eleMentName = eleMentName + "("; //$NON-NLS-1$
          for (Iterator iter = parameters.iterator(); iter.hasNext();) {
            Parameter parameter = (Parameter) iter.next();
            eleMentName = eleMentName + " " + parameter.getName(); //$NON-NLS-1$
            if (iter.hasNext()) {
              eleMentName = eleMentName + ","; //$NON-NLS-1$
            }
          }
          eleMentName = eleMentName + " )";//$NON-NLS-1$
        } else {
          eleMentName = eleMentName + " ()";//$NON-NLS-1$
        }

      }
      return eleMentName;
    } else if (element_p instanceof Entry) {
      Entry<Integer, EObject> entry = (Entry<Integer, EObject>) element_p;
      EObject key = entry.getValue();
      if (key != null && key instanceof AbstractNamedElement) {
        String name = ((AbstractNamedElement) key).getName();
        return name;
      }
    }
    return super.getText(element_p);
  }

  @SuppressWarnings("unchecked")
  public Font getFont(Object element_p) {
    if (element_p instanceof AbstractNamedElement) {
      if (_isValidElementsSet && !_validElements.contains(element_p)) {
        if (null == _font) {
          _font = createInvalidElementFont();
        }
        return _font;
      }
    } else if (element_p instanceof Entry) {
      Entry<Integer, EObject> entry = (Entry<Integer, EObject>) element_p;
      EObject key = entry.getValue();
      if (key != null && key instanceof AbstractNamedElement) {
        if (_isValidElementsSet && !_validElements.contains(key)) {
          if (null == _font) {
            _font = createInvalidElementFont();
          }
          return _font;
        }
      }
    }

    return null;
  }

  /**
   * Create invalid element font.
   */
  protected Font createInvalidElementFont() {
    return new Font(Display.getDefault(), "Arial", 9, SWT.NORMAL | SWT.ITALIC); //$NON-NLS-1$
  }

  /**
   * @see org.eclipse.jface.viewers.IColorProvider#getBackground(java.lang.Object)
   */
  public Color getBackground(Object element_p) {
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
   */
  @SuppressWarnings("unchecked")
  public Color getForeground(Object element_p) {
    if (element_p instanceof AbstractNamedElement) {
      return getValidForeGround(element_p);
    } else if (element_p instanceof Entry) {
      Entry<Integer, EObject> entry = (Entry<Integer, EObject>) element_p;
      EObject key = entry.getValue();
      if (key != null && key instanceof AbstractNamedElement) {
        return getValidForeGround(key);
      }
    }
    return _validColor;
  }

  /**
   * @param element_p
   * @return
   */
  protected Color getValidForeGround(Object element_p) {
    if (_isValidElementsSet && !_validElements.contains(element_p)) {
      if (null == _invalidColor) {
        _invalidColor = Display.getDefault().getSystemColor(SWT.COLOR_DARK_GRAY);
      }
      return _invalidColor;
    }
    if (null == _validColor) {
      _validColor = Display.getDefault().getSystemColor(SWT.COLOR_BLACK);
    }
    return _validColor;
  }

  public List<EObject> get_notValidList() {
    return _validElements;
  }

  public void set_ValidList(List<EObject> validList) {
    _isValidElementsSet = true;
    _validElements = validList;
  }

  @SuppressWarnings("unchecked")
  @Override
  /**
   * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getImage(java.lang.Object)
   */
  public Image getImage(Object element_p) {
    if (element_p instanceof AbstractNamedElement) {
      return super.getImage(element_p);
    } else if (element_p instanceof Entry) {
      Entry<Integer, EObject> entry = (Entry<Integer, EObject>) element_p;
      EObject key = entry.getValue();
      if (key != null && key instanceof AbstractNamedElement) {
        return super.getImage(key);
      }
    }
    return super.getImage(element_p);
  }
}
