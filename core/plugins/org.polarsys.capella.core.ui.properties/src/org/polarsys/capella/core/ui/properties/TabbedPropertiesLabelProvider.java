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
package org.polarsys.capella.core.ui.properties;

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
import org.eclipse.sirius.common.tools.api.query.IllegalStateExceptionQuery;
import org.eclipse.sirius.common.ui.business.api.views.properties.tabbed.ILabelProviderProvider;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.common.ui.providers.MDEAdapterFactoryLabelProvider;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.model.utils.NamingHelper;

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
    Image result = null;
    EObject model = getModel(element);
    if (model != null) {
      try {
        result = super.getImage(model);
      } catch (IllegalStateException e) {
        if (new IllegalStateExceptionQuery(e).isAConnectionLostException()) {
          // Do nothing just return null
        } else {
          throw e;
        }
      }
    }
    return result;
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
