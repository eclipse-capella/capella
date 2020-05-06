/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.properties;

import java.net.URL;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.NoteAttachmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.TextEditPart;
import org.eclipse.gmf.runtime.notation.Connector;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.common.ui.business.api.views.properties.tabbed.ILabelProviderProvider;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.common.ui.providers.MDEAdapterFactoryLabelProvider;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.model.helpers.TitleBlockExt;
import org.polarsys.capella.core.model.utils.NamingHelper;
import org.polarsys.capella.core.ui.resources.CapellaUIResourcesPlugin;

/**
 * This class handles title label for tabbed properties.
 */
public class TabbedPropertiesLabelProvider extends MDEAdapterFactoryLabelProvider implements ILabelProviderProvider {

  /**
   * This method overrides the AdapterFactoryLabelProvider getText method in order to obtain the desired label values. The returned string will have the
   * StructuralFeature with a description of its EcoreClass and its model.
   * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getText(Object)
   */
  @Override
  public String getText(Object element) {
    String title = "<unknown>"; //$NON-NLS-1$
    EObject modelElement = getModel(element);
    if (null != modelElement) {
      title = NamingHelper.getDefaultTitle(modelElement);
    } else if (element instanceof StructuredSelection) {
      Object selection = ((IStructuredSelection) element).getFirstElement();
      if (selection instanceof AbstractGraphicalEditPart) {
        return getTextForEditPart((AbstractGraphicalEditPart) selection);
      }
      if (selection instanceof IResource) {
        return NamingHelper.getTextForResource((IResource) selection);
      }
    }
    return encode(title);
  }
  
  
  @SuppressWarnings("restriction")
  private String getTextForEditPart(AbstractGraphicalEditPart editPart) {
    StringBuilder sb = new StringBuilder(""); //$NON-NLS-1$
    Object model = editPart.getModel();
    if (editPart instanceof NoteEditPart) {
      sb.append(((Shape) model).getType());
      return sb.toString();
    }
    if (editPart instanceof TextEditPart) {
      sb.append(((Shape) model).getType());
      return sb.toString();
    }
    if (editPart instanceof NoteAttachmentEditPart) {
      sb.append(((Connector) model).getType());
      return sb.toString();
    }
    return sb.toString();
  }

  /**
   * @param str
   * @return encoded string
   */
  protected String encode(String str) {
    return str.replaceAll("&", "&&"); //$NON-NLS-1$ //$NON-NLS-2$
  }

  /**
   * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getImage(Object)
   */
  @Override
  public Image getImage(Object element) {
    EObject model = getModel(element);
    if (TitleBlockExt.isTitleBlockAnnotation(model)) {
      DAnnotation annotation = (DAnnotation) model;
      if (annotation.getSource().contains("TitleBlock")) {
        String imagePath = "/icons/full/obj16/TitleBlock_16.gif";
        URL url = FileLocator.find(CapellaUIResourcesPlugin.getDefault().getBundle(), new Path(imagePath), null);
        return ImageDescriptor.createFromURL(url).createImage();
      }
    }
    else if (model != null) {
      return super.getImage(model);
    }
    return null;
  }

  /**
   *
   */
  private EObject getModel(Object element) {
    if (element instanceof StructuredSelection) {
      StructuredSelection selection = (StructuredSelection) element;
      return CapellaAdapterHelper.resolveDescriptorOrBusinessObject(selection.getFirstElement());
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ILabelProvider getLabelProvider() {
    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean provides(Object selection) {
    return true;
  }
}
