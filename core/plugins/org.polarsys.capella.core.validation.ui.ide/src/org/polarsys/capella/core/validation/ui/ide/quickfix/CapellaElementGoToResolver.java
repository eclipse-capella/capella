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
package org.polarsys.capella.core.validation.ui.ide.quickfix;

import java.text.MessageFormat;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMarkerResolution2;

import org.polarsys.capella.common.tools.report.appenders.reportlogview.actions.SelectElementAction;
import org.polarsys.capella.common.ui.services.helper.EObjectLabelProviderHelper;

/**
 * This class can be used to create "Go to" Quick Fixes.
 */
public class CapellaElementGoToResolver implements IMarkerResolution2 {
  /**
   * QF's label pattern.
   */
  private static final String QUICK_FIX_LABEL_PATTERN = "Show {0} {1} ({2})"; //$NON-NLS-1$
  /**
   * QF's image.
   */
  private final Image _image;
  /**
   * QF's label.
   */
  private final String _label;
  /**
   * Model element to go to.
   */
  private final EObject _modelElement;

  /**
   * @param elementToGoToDescription_p
   * @param modelElement_p
   */
  public CapellaElementGoToResolver(String elementToGoToDescription_p, EObject modelElement_p) {
    // Generate label.
    String modelElementClassName =
        EObjectLabelProviderHelper.getMetaclassLabel(modelElement_p, false);
    String modelElementName = EObjectLabelProviderHelper.getText(modelElement_p);
    _label =
        MessageFormat.format(QUICK_FIX_LABEL_PATTERN, elementToGoToDescription_p, modelElementName,
            modelElementClassName);
    // Image.
    _image = EObjectLabelProviderHelper.getImage(modelElement_p);

    _modelElement = modelElement_p;
  }

  /**
   * {@inheritDoc}
   */
  public Image getImage() {
    return _image;
  }

  /**
   * {@inheritDoc}
   */
  public String getLabel() {
    return _label;
  }

  /**
   * {@inheritDoc}
   */
  public void run(IMarker marker_p) {
    SelectElementAction selectElementAction = new SelectElementAction(_modelElement);
    selectElementAction.run();
  }

  /**
   * {@inheritDoc}
   */
  public String getDescription() {
    return null;
  }
}
