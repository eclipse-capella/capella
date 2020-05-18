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
package org.polarsys.capella.core.ui.metric.dialog;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.ui.providers.MDEAdapterFactoryLabelProvider;
import org.polarsys.capella.common.ui.toolkit.viewers.data.TreeData;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.diagram.helpers.RepresentationAnnotationHelper;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;

/**
 * @see Progress Monitoring label provider.
 */
public class ProgressMonitoringLabelProvider extends MDEAdapterFactoryLabelProvider implements IColorProvider {
  /**
   * Foreground color for referencing elements.
   */
  private int _foregroundColor;
  /**
   * Viewer that uses this label provider.
   */
  private TreeViewer _viewer;

  /**
   * Constructor.
   * 
   * @param adapterFactory
   * @param foregroundColorForReferencingElements
   *          must be a {@link SWT#COLOR} constant.
   */
  public ProgressMonitoringLabelProvider(TreeViewer viewer, int foregroundColorForReferencingElements) {
    super();
    _foregroundColor = foregroundColorForReferencingElements;
    _viewer = viewer;
  }

  /**
   * @see org.eclipse.jface.viewers.IColorProvider#getBackground(java.lang.Object)
   */
  @Override
  public Color getBackground(Object element) {
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
   */
  @Override
  public Color getForeground(Object element) {
    // Select the foreground color for elements that reference the selected
    // one.
    Object input = _viewer.getInput();
    if ((input instanceof TreeData) && (((TreeData) input).isValid(element))) {
      Display display = PlatformUI.getWorkbench().getDisplay();
      return display.getSystemColor(_foregroundColor);
    }
    return null;
  }

  /**
   * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getText(java.lang.Object)
   */
  @Override
  public String getText(Object object) {
    String text = super.getText(object);
    return text.replace("%20", ICommonConstants.EMPTY_STRING + ICommonConstants.WHITE_SPACE_CHARACTER); //$NON-NLS-1$
  }

  @Override
  public String getColumnText(Object element, int columnIndex) {

    String text = null;

    if (0 == columnIndex) {
      text = getText(element);
      
    } else if (1 == columnIndex) {
      if (element instanceof CapellaElement) {
        EnumerationPropertyLiteral status = ((CapellaElement) element).getStatus();
        if (null != status) {
          text = status.getLabel();
        }
      }
      if (element instanceof DRepresentationDescriptor) {
        DRepresentationDescriptor representationDesc = (DRepresentationDescriptor) element;
        EnumerationPropertyLiteral status = RepresentationAnnotationHelper.getProgressStatus(representationDesc);
        if (null != status) {
          text = status.getLabel();
        }
      }
      
    } else if (2 == columnIndex) {
      if (element instanceof CapellaElement) {
        String review = ((CapellaElement) element).getReview();
        if (null != review) {
          text = review;
        }
      }
      if (element instanceof DRepresentationDescriptor) {
        DRepresentationDescriptor representationDesc = (DRepresentationDescriptor) element;
        String review = RepresentationAnnotationHelper.getStatusReview(representationDesc);
        if (review != null) {
          text = review;
        }
      }
    }
    return text;
  }

  @Override
  public Image getColumnImage(Object element, int columnIndex) {

    Image image = null;

    if (0 == columnIndex) {
      image = getImage(element);
    }

    return image;
  }

}
