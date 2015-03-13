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
package org.polarsys.capella.core.ui.metric.dialog;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
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
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;
import org.polarsys.capella.core.ui.properties.annotations.IRepresentationAnnotationConstants;

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
   * @param adapterFactory_p
   * @param foregroundColorForReferencingElements_p must be a {@link SWT#COLOR} constant.
   */
  public ProgressMonitoringLabelProvider(TreeViewer viewer_p, int foregroundColorForReferencingElements_p) {
    super(CapellaAdapterFactoryProvider.getInstance().getAdapterFactory());
    _foregroundColor = foregroundColorForReferencingElements_p;
    _viewer = viewer_p;
  }

  /**
   * @see org.eclipse.jface.viewers.IColorProvider#getBackground(java.lang.Object)
   */
  @Override
  public Color getBackground(Object element_p) {
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.IColorProvider#getForeground(java.lang.Object)
   */
  @Override
  public Color getForeground(Object element_p) {
    // Select the foreground color for elements that reference the selected one.
    Object input = _viewer.getInput();
    if ((input instanceof TreeData) && (((TreeData) input).isValid(element_p))) {
      Display display = PlatformUI.getWorkbench().getDisplay();
      return display.getSystemColor(_foregroundColor);
    }
    return null;
  }

  /**
   * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getText(java.lang.Object)
   */
  @Override
  public String getText(Object object_p) {
    String text = super.getText(object_p);
    return text.replace("%20", ICommonConstants.EMPTY_STRING + ICommonConstants.WHITE_SPACE_CHARACTER); //$NON-NLS-1$
  }

  @Override
  public String getColumnText(Object element_p, int columnIndex_p) {

    String text = null;

    if (0 == columnIndex_p) {
      text = getText(element_p);
    } else if (1 == columnIndex_p) {
      if (element_p instanceof CapellaElement) {
        EnumerationPropertyLiteral status = ((CapellaElement) element_p).getStatus();
        if (null != status) {
          text = status.getLabel();
        }
      }
      if (element_p instanceof DRepresentation) {
            String eAnnot= IRepresentationAnnotationConstants.ProgressStatus;
    		DAnnotation dAnnotation= RepresentationHelper.getAnnotation(eAnnot, (DRepresentation) element_p);
    		 if (dAnnotation!=null) {
    	    text= dAnnotation.getDetails().get("value");
    		 }
      }
    }
    return text;
  }


  @Override
  public Image getColumnImage(Object element_p, int columnIndex_p) {

    Image image = null;

    if (0 == columnIndex_p) {
      image = getImage(element_p);
    }

    return image;
  }

}
