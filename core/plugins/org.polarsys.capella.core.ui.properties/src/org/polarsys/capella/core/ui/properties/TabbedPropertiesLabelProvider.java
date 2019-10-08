/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.properties;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.NoteAttachmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.editparts.TextEditPart;
import org.eclipse.gmf.runtime.notation.Connector;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.common.ui.business.api.views.properties.tabbed.ILabelProviderProvider;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.ui.toolkit.viewers.CapellaElementLabelProvider;

/**
 * This class handles title label for tabbed properties.
 */
public class TabbedPropertiesLabelProvider extends CapellaElementLabelProvider implements ILabelProviderProvider {

  /**
   * Constructor
   */
  public TabbedPropertiesLabelProvider() {
    super();
  }

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
      title = super.getText(modelElement);
      if (null != title) {
        String metaclassLabel = EObjectLabelProviderHelper.getMetaclassLabel(modelElement, true);
        if (null != metaclassLabel) {
          if (!title.startsWith(metaclassLabel)) {
            title = metaclassLabel + title;
          }
        }
      } else {
        title = EObjectLabelProviderHelper.getText(modelElement);
      }
    } else if (element instanceof StructuredSelection) {
      Object selection = ((IStructuredSelection) element).getFirstElement();
      if (selection instanceof AbstractGraphicalEditPart) {
        return getTextForEditPart((AbstractGraphicalEditPart) selection);
      }
      if(selection instanceof IResource) {
        return getTextForResource((IResource)selection);
      }

    }
    return encode(title);
  }
  
  private String getTextForResource(IResource resource) {
    IContainer parent = resource.getParent();
    if (parent != null && parent.getType() != IResource.ROOT) {
      return resource.getName() + " - " + parent.getFullPath();

    }
    return resource.getName();
  }

  /**
   * @param editPart
   * @return
   */
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
    if (model != null) {
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
      return CapellaAdapterHelper.resolveSemanticObject(selection.getFirstElement());
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
