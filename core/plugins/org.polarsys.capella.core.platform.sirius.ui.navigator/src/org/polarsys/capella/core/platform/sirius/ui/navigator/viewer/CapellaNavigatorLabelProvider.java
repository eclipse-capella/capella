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
package org.polarsys.capella.core.platform.sirius.ui.navigator.viewer;

import java.util.Arrays;
import java.util.Optional;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.common.tools.api.query.IllegalStateExceptionQuery;
import org.eclipse.sirius.common.ui.tools.api.view.common.item.ItemDecorator;
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;
import org.eclipse.sirius.ui.tools.api.color.VisualBindingManager;
import org.eclipse.sirius.ui.tools.api.views.common.item.ItemWrapper;
import org.eclipse.sirius.ui.tools.api.views.common.item.RepresentationDescriptionItem;
import org.eclipse.sirius.ui.tools.api.views.common.item.ViewpointItem;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.SystemColors;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.navigator.IDescriptionProvider;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.model.copypaste.SharedCutPasteClipboard;
import org.polarsys.capella.common.ui.providers.MDEAdapterFactoryLabelProvider;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;
import org.polarsys.capella.core.platform.sirius.ui.navigator.IImageKeys;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

public class CapellaNavigatorLabelProvider extends MDEAdapterFactoryLabelProvider
    implements ILabelProvider, IDescriptionProvider, IFontProvider, IColorProvider {

  private static final String STATUS_LINE_PATH_SEPARATOR = "::"; //$NON-NLS-1$

  private Font italicFont;

  private static final String DISABLED_REPRESENTATION_SUFFIX = "_disabled"; //$NON-NLS-1$
  
  public CapellaNavigatorLabelProvider() {
    super();
    Font currentFont = Display.getCurrent().getSystemFont();
    FontData[] datas = currentFont.getFontData();
    datas[0].setStyle(SWT.ITALIC);
    italicFont = new Font(currentFont.getDevice(), datas);
  }

  /**
   * Part related to DRepresentationDescriptor case is copied from
   * org.eclipse.sirius.ui.tools.internal.views.common.navigator.SiriusCommonLabelProvider.getImage(Object)
   * 
   * @see org.eclipse.emf.transaction.ui.provider.TransactionalAdapterFactoryLabelProvider#getImage(java.lang.Object)
   */
  @Override
  public Image getImage(Object object) {
    Image image = null;
    try {
      Optional<DRepresentationDescriptor> repDesc = getRepresentationDescriptor(object);
      if (repDesc.isPresent()) {
        image = super.getImage(repDesc.get());
        if (!RepresentationHelper.isValid(repDesc.get())) {
          image = getDisabledImage(repDesc.get(), image);
        }
      } else if (object instanceof Session) {
        image = SessionLabelProviderHelper.getInstance().getSessionLabelProvider().getImage(object);
      } else if (object instanceof ItemDecorator) {
        image = ((ItemDecorator) object).getImage();
      } else if (object instanceof ViewpointItem) {
        image = CapellaNavigatorPlugin.getDefault().getImage(IImageKeys.IMG_VIEWPOINT);
      } else if (object instanceof RepresentationDescriptionItem) {
        RepresentationDescriptionItem descriptionItem = (RepresentationDescriptionItem) object;
        // Filter out scenario diagram to keep the nice image.
        if (!(descriptionItem.getWrappedObject() instanceof SequenceDiagramDescription)) {
          image = CapellaNavigatorPlugin.getDefault().getImage(IImageKeys.IMG_DIAGRAM_TYPE);
        } else {
          image = super.getImage(((ItemWrapper) object).getWrappedObject());
        }
      } else if (object instanceof ItemWrapper) {
        image = super.getImage(((ItemWrapper) object).getWrappedObject());
      } else if (object instanceof RepresentationPackage) {
        image = PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_FOLDER);
      } else if (!(object instanceof IResource)) {
        image = super.getImage(object);
      }
    } catch (IllegalStateException e) {
      if (new IllegalStateExceptionQuery(e).isAConnectionLostException()) {
        // Do nothing.
      } else {
        throw e;
      }
  }
    return image;
  }

  /**
   * Returns a gray image for the given representation descriptor
   */
  private Image getDisabledImage(DRepresentationDescriptor representationDescriptor, Image image) {
    StringBuilder sB = new StringBuilder();
    sB.append(DRepresentationDescriptor.class.getName());
    RepresentationDescription description = representationDescriptor.getDescription();
    if (description != null) {
      sB.append(ICommonConstants.UNDERSCORE_CHARACTER);
      sB.append(description.getClass().getName());
    }
    sB.append(DISABLED_REPRESENTATION_SUFFIX);

    String key = sB.toString();
    Image disabledImage = CapellaNavigatorPlugin.getDefault().getImageRegistry().get(key);
    if (disabledImage == null && image != null) {
      ImageDescriptor desc = ImageDescriptor.createFromImage(image);
      ImageDescriptor disabledDesc = ImageDescriptor.createWithFlags(desc, SWT.IMAGE_DISABLE);
      CapellaNavigatorPlugin.getDefault().getImageRegistry().put(key, disabledDesc);
      disabledImage = CapellaNavigatorPlugin.getDefault().getImageRegistry().get(key);
    }
    return disabledImage;
  }

  /**
   * Return the representation descriptor of the given element
   */
  private Optional<DRepresentationDescriptor> getRepresentationDescriptor(Object element) {
    return RepresentationHelper.getSelectedDescriptors(Arrays.asList(element)).stream().findFirst();
  }

  /**
   * @see org.eclipse.emf.transaction.ui.provider.TransactionalAdapterFactoryLabelProvider#getText(java.lang.Object)
   */
  @Override
  public String getText(Object object) {
    String text = null;

    if (object instanceof Session) {
      text = SessionLabelProviderHelper.getInstance().getSessionLabelProvider().getText(object);
    } else if (object instanceof ItemDecorator) {
      text = ((ItemDecorator) object).getText();
    } else if (object instanceof ItemWrapper) {
      text = super.getText(((ItemWrapper) object).getWrappedObject());
    } else if (object instanceof RepresentationPackage) {
      return (((RepresentationPackage) object).getName());
    } else {
      // Fix due to 3.5 & 3.6 that have changed the implementation of IResource.toString().
      IWorkbenchAdapter workbenchAdapter = Platform.getAdapterManager().getAdapter(object, IWorkbenchAdapter.class);
      text = (null != workbenchAdapter) ? workbenchAdapter.getLabel(object) : super.getText(object);

      if (object instanceof IFile) {
        if (CapellaResourceHelper.isAirdResource((IFile) object, true)) {
          Session session = SessionHelper.getSession(((IFile) object));
          if ((session != null) && (session.getStatus() == SessionStatus.DIRTY)) {
            text = "*" + text; //$NON-NLS-1$
          }
        }
      }
    }

    return text;
  }

  /**
   * This method is mainly copied from
   * org.eclipse.sirius.ui.tools.internal.views.common.navigator.SiriusCommonLabelProvider.getForeground(Object)
   * 
   * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#getForeground(java.lang.Object)
   */
  @Override
  public Color getForeground(final Object element) {
    Optional<DRepresentationDescriptor> repDesc = getRepresentationDescriptor(element);
    if (repDesc.isPresent()) {
      try {
        if (!RepresentationHelper.isValid(repDesc.get())) {
          return VisualBindingManager.getDefault().getColorFromName(SystemColors.LIGHT_GRAY_LITERAL.getName());
        }
      } catch (IllegalStateException | NullPointerException e) {
        // This can happen when trying to get the label of a CDOObject
        // which transaction has just been closed
        // Nothing to do, null will returned
      }
    }
    if (SharedCutPasteClipboard.getCutClipboard().isObjectCut(element)) {
      return VisualBindingManager.getDefault().getColorFromName(SystemColors.GRAY_LITERAL.getName());
    }
    return super.getForeground(element);
  }

  @Override
  public String getDescription(Object element) {
    try {
      if (element instanceof ModelElement) {
        String slash = String.valueOf(ICommonConstants.SLASH_CHARACTER);
        ModelElement modelElement = (ModelElement) element;
        String path = modelElement.getFullLabel();
        if (path.startsWith(slash)) {
          path = path.substring(1);
        }
        return path.replaceAll(slash, STATUS_LINE_PATH_SEPARATOR);
      }
      
      // Handle firstly for representation
      EObject eObject = CapellaAdapterHelper.resolveEObject(element);
      if (eObject instanceof DRepresentation) {
        DRepresentationDescriptor descriptor = RepresentationHelper
            .getRepresentationDescriptor((DRepresentation) eObject);
        return RepresentationHelper.getRepresentationFullPathText(descriptor);
      }
      
      if (eObject instanceof DRepresentationDescriptor) {
        return RepresentationHelper.getRepresentationFullPathText((DRepresentationDescriptor) eObject);
      }
      
      // Handle then for semantic element
      EObject semanticElement = CapellaAdapterHelper.resolveBusinessObject(element);
      if (semanticElement != null) {
        return EObjectLabelProviderHelper.getFullPathText(semanticElement);
      }
    } catch (IllegalStateException e) {
      if (new IllegalStateExceptionQuery(e).isAConnectionLostException()) {
        // Do nothing.
      } else {
        throw e;
      }
    }
    return "";
  }

  @Override
  public void dispose() {
    if ((null != italicFont) && !italicFont.isDisposed()) {
      italicFont.dispose();
      italicFont = null;
    }
    super.dispose();
  }

  @Override
  public Font getFont(Object element) {
    if (element instanceof Part || SharedCutPasteClipboard.getCutClipboard().isObjectCut(element)) {
      return italicFont;
    }
    return super.getFont(element);
  }
}
