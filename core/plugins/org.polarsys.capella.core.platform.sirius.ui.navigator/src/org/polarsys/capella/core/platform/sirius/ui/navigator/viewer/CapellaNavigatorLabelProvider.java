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
package org.polarsys.capella.core.platform.sirius.ui.navigator.viewer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.common.ui.tools.api.view.common.item.ItemDecorator;
import org.eclipse.sirius.diagram.sequence.description.SequenceDiagramDescription;
import org.eclipse.sirius.ui.tools.api.views.common.item.ItemWrapper;
import org.eclipse.sirius.ui.tools.api.views.common.item.RepresentationDescriptionItem;
import org.eclipse.sirius.ui.tools.api.views.common.item.ViewpointItem;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.navigator.IDescriptionProvider;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.model.copypaste.SharedCutPasteClipboard;
import org.polarsys.capella.common.ui.providers.MDEAdapterFactoryLabelProvider;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.provider.CapellaAdapterFactoryProvider;
import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;
import org.polarsys.capella.core.platform.sirius.ui.navigator.IImageKeys;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

/**
 * The Capella navigator label provider.
 */
public class CapellaNavigatorLabelProvider extends MDEAdapterFactoryLabelProvider implements IDescriptionProvider,
    IFontProvider {
  private static final String STATUS_LINE_PATH_SEPARATOR = "::"; //$NON-NLS-1$
  private Font _italicFont;

  /**
   * Constructs the Capella navigator label provider.
   */
  public CapellaNavigatorLabelProvider() {
    super(CapellaAdapterFactoryProvider.getInstance().getAdapterFactory());
  }

  public CapellaNavigatorLabelProvider(AdapterFactory adapterFact) {
    super(adapterFact);
  }

  public CapellaNavigatorLabelProvider(TransactionalEditingDomain editingDomain, AdapterFactory adapterFact) {
    super(editingDomain, adapterFact);
  }

  /**
   * @see org.eclipse.emf.transaction.ui.provider.TransactionalAdapterFactoryLabelProvider#getImage(java.lang.Object)
   */
  @Override
  public Image getImage(Object object) {
    Image image = null;
    if (object instanceof Session) {
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
    } else if (!(object instanceof IResource)) {
      image = super.getImage(object);
    }
    return image;
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
    } else {
      // Fix due to 3.5 & 3.6 that have changed the implementation of IResource.toString().
      IWorkbenchAdapter workbenchAdapter = (IWorkbenchAdapter) Platform.getAdapterManager().getAdapter(object,
          IWorkbenchAdapter.class);
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
   * @see org.eclipse.ui.navigator.IDescriptionProvider#getDescription(java.lang.Object)
   */
  @Override
  public String getDescription(Object element) {
    String result = ICommonConstants.EMPTY_STRING;
    String slash = String.valueOf(ICommonConstants.SLASH_CHARACTER);
    if (element instanceof ModelElement) {
      ModelElement modelElement = (ModelElement) element;
      String path = modelElement.getFullLabel();
      if (path.startsWith(slash)) {
        path = path.substring(1);
      }
      result = path.replaceAll(slash, STATUS_LINE_PATH_SEPARATOR);
    } else if (element instanceof DRepresentation || element instanceof DRepresentationDescriptor) {
      // Adapts the representation into a Capella element (it returns its Capella container).
      DRepresentation representation = (element instanceof DRepresentationDescriptor) ? ((DRepresentationDescriptor) element)
          .getRepresentation() : (DRepresentation) element;
      Object modelElement = Platform.getAdapterManager().getAdapter(representation, ModelElement.class);
      if (null == modelElement) {
        modelElement = Platform.getAdapterManager().loadAdapter(representation, ModelElement.class.getName());
      }
      if (null != modelElement) {
        // Builds and formats the DRepresentation path.
        String containerPath = getDescription(modelElement);
        String path = containerPath.concat(STATUS_LINE_PATH_SEPARATOR).concat(representation.getName());
        if (path.startsWith(slash)) {
          path = path.substring(1);
        }
        result = path.replaceAll(slash, STATUS_LINE_PATH_SEPARATOR);
      }
    } else if (element instanceof ItemWrapper) {
      // Adapts the representation into a Capella element (it returns its
      // Capella container).
      ItemWrapper item = (ItemWrapper) element;
      Object wrappedObject = item.getWrappedObject();
      String description = getDescription(wrappedObject);
      result = description;
    } else if (element instanceof Viewpoint) {
      Viewpoint viewpoint = (Viewpoint) element;
      result = viewpoint.getName();
    } else if (element instanceof RepresentationDescription) {
      RepresentationDescription description = (RepresentationDescription) element;
      String representationDescPath = description.getName();
      EObject container = description.eContainer();
      while (null != container) {
        String containerPath = getDescription(container);
        if (null != containerPath) {
          representationDescPath = containerPath.concat(STATUS_LINE_PATH_SEPARATOR).concat(representationDescPath);
        }
        container = container.eContainer();
      }
      result = representationDescPath;
    } else if (element instanceof EObject) {

      String path = getText(element);
      EObject container = ((EObject) element).eContainer();

      while (null != container) {
        String containerPath = getDescription(container);
        if (null != containerPath) {
          path = containerPath.concat(STATUS_LINE_PATH_SEPARATOR).concat(path);
        }
        if (containerPath.contains(STATUS_LINE_PATH_SEPARATOR)) {
          container = null;
        } else {
          container = container.eContainer();
        }
      }
      result = path;
    }
    return result;
  }

  /**
   * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#dispose()
   */
  @Override
  public void dispose() {
    if ((null != _italicFont) && !_italicFont.isDisposed()) {
      _italicFont.dispose();
      _italicFont = null;
    }
    super.dispose();
  }

  /**
   * @see org.eclipse.jface.viewers.IFontProvider#getFont(java.lang.Object)
   */
  @Override
  public Font getFont(Object element) {
    Font currentFont = Display.getCurrent().getSystemFont();
    if (_italicFont == null) {
      FontData[] datas = currentFont.getFontData();
      datas[0].setStyle(SWT.ITALIC);
      _italicFont = new Font(currentFont.getDevice(), datas);
    }
    if (SharedCutPasteClipboard.getCutClipboard().isObjectCut(element)) {
      return _italicFont;
    }
    return currentFont;
  }
}
