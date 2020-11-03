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
package org.polarsys.capella.core.ui.toolkit.decomposition;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.ui.providers.MDEAdapterFactoryLabelProvider;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.communication.CommunicationLink;
import org.polarsys.capella.core.data.information.communication.provider.CommunicationItemProviderAdapterFactory;
import org.polarsys.capella.core.data.information.communication.provider.CommunicationLinkItemProvider;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.ui.toolkit.Activator;

public class DecompositionLabelProvider extends MDEAdapterFactoryLabelProvider implements IBaseLabelProvider,
    IColorProvider, IFontProvider {// extends ColumnLabelProvider
  private boolean _sourceViewer;
  private Font boldFont = new Font(Display.getCurrent(), "Verdana", 8, SWT.BOLD); //$NON-NLS-1$;
  private Font normalFont = new Font(Display.getCurrent(), "Verdana", 8, SWT.NONE); //$NON-NLS-1$;

  ImageDescriptor implImageDescriptorFromPlugin = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID,
      Messages.getString("LCDecomp.interface.icon.LCInterfaceImpl")); //$NON-NLS-1$
  Image implImage = implImageDescriptorFromPlugin.createImage();
  ImageDescriptor useImageDescriptorFromPlugin = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID,
      Messages.getString("LCDecomp.interface.icon.LCInterfaceUse")); //$NON-NLS-1$
  Image useImage = useImageDescriptorFromPlugin.createImage();

  public DecompositionLabelProvider(boolean flag) {
    super();
    setSourceViewer(flag);
  }

  @Override
  public String getText(Object element) {
    if (element instanceof DecompositionComponent) {
      DecompositionComponent comp = (DecompositionComponent) element;
      Object value = comp.getValue();
      if (null != value && value instanceof LogicalComponent) {
        LogicalComponent lc = (LogicalComponent) value;
        String lc_name = lc.getName();
        EList<AbstractTypedElement> abstractTypedElements = lc.getAbstractTypedElements();
        if (abstractTypedElements.isEmpty()) {
          return element.toString();
        }
        AbstractTypedElement abstractTypedElement = abstractTypedElements.get(0);
        String part_name = abstractTypedElement.getName();

        if (getSynchronizationModeSelected()) {
          if (comp.isComposite()) {
            if (comp.isTrigger()) {
              comp.setName(element.toString());
              return element.toString()
                  + " : " + element.toString() + Messages.getString("LCDecomp.component.compositelabel"); //$NON-NLS-1$ //$NON-NLS-2$
            }
            //return part_name + " : " + element.toString() + Messages.getString("LCDecomp.component.compositelabel"); //$NON-NLS-1$ //$NON-NLS-2$
            return element.toString() + Messages.getString("LCDecomp.component.compositelabel"); //$NON-NLS-1$ //$NON-NLS-2$
          }
          // if not composite
          if (comp.isTrigger()) {
            comp.setName(element.toString());
            return element.toString() + " : " + element.toString(); //$NON-NLS-1$
          }
          //return part_name + " : " + element.toString(); //$NON-NLS-1$
          return element.toString(); //$NON-NLS-1$
        }

        // if not Synchronization Mode Selected
        if (comp.isComposite()) {
          if (comp.isTrigger()) {
            comp.setName(element.toString());
            return element.toString() + " : " + lc_name + Messages.getString("LCDecomp.component.compositelabel"); //$NON-NLS-1$ //$NON-NLS-2$
          }
          return part_name + " : " + element.toString() + Messages.getString("LCDecomp.component.compositelabel"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        // if not composite
        if (comp.isTrigger()) {
          comp.setName(element.toString());
          return element.toString() + " : " + lc_name; //$NON-NLS-1$
        }
        //return part_name + " : " + element.toString();	 //$NON-NLS-1$
        return element.toString(); //$NON-NLS-1$

      }
      //return element.toString() + " : " + element.toString();	 //$NON-NLS-1$
      return element.toString(); //$NON-NLS-1$
    } else if (element instanceof DecompositionItem) {
      DecompositionItem itemItf = (DecompositionItem) element;
      if (itemItf.isInternal()) {
        String label = new String(itemItf.getName());
        label = label.concat(" [Refined]"); //$NON-NLS-1$
        return label;
      }
    }

    return element.toString();
  }

  /**
   * @return the sourceViewer
   */
  public boolean isSourceViewer() {
    return _sourceViewer;
  }

  /**
   * @param sourceViewer
   *          the sourceViewer to set
   */
  public void setSourceViewer(boolean sourceViewer) {
    _sourceViewer = sourceViewer;
  }

  /**
   * @see org.eclipse.jface.viewers.ColumnLabelProvider#getImage(java.lang.Object)
   */
  @Override
  public Image getImage(Object element) {
    if (element instanceof DecompositionComponent) {
      DecompositionComponent dComp = (DecompositionComponent) element;
      Object value = dComp.getValue();
      if (null != value) {
        return super.getImage(value);
      }
      return super.getImage(LaFactory.eINSTANCE.createLogicalComponent());
    }

    if (element instanceof DecompositionItem) {
      DecompositionItem item = (DecompositionItem) element;
      Object value = item.getValue();
      if (value != null) {
        if (value instanceof Interface) {
          if (item.isInterfaceUsage()) {
            return useImage;
          }
        } else if (value instanceof CommunicationLink) {
          CommunicationItemProviderAdapterFactory cipaf = new CommunicationItemProviderAdapterFactory();
          CommunicationLinkItemProvider clip = new CommunicationLinkItemProvider(cipaf);
          Object image = clip.getImage(value);
          return ExtendedImageRegistry.getInstance().getImage(image);
        }
      }
      return implImage;
    }

    if (element instanceof DecompositionItemService) {
      DecompositionItemService item = (DecompositionItemService) element;
      Object value = item.getValue();
      if (null != value) {
        return super.getImage(value);
      }
      return null;
    }

    return null;
  }

  public Color getBackground(Object element) {
    return null;
  }

  public Color getForeground(Object element) {
    Color color = Display.getCurrent().getSystemColor(SWT.COLOR_BLACK);
    int status = -1;
    if (element instanceof DecompositionItem) {
      DecompositionItem item = (DecompositionItem) element;
      if (isSourceViewer()) {
        status = item.getStatus();
      } else {
        if (item.getParentComponent().isReusedComponent()) {
          color = Display.getCurrent().getSystemColor(SWT.COLOR_GRAY);
        }
      }
    }
    switch (status) {
    case DecompositionItem.AMBIGUOUS:
      color = Display.getCurrent().getSystemColor(SWT.COLOR_DARK_MAGENTA);
      break;
    case DecompositionItem.ASSIGNED:
      color = Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GREEN);
      break;
    case DecompositionItem.UNASSIGNED:
      color = Display.getCurrent().getSystemColor(SWT.COLOR_RED);
      break;
    default:
      break;
    }
    return color;
  }

  public Font getFont(Object element) {
    if (element instanceof DecompositionItem) {
      if (!isSourceViewer()) {
        if (((DecompositionItem) element).isAlreadyDecomposed()) {
          return boldFont;
        }
      }
    }
    return normalFont;
  }

  private boolean getSynchronizationModeSelected() {
    return DecompositionPreferenceOption.synchronizeName;
  }

  @Override
  public void dispose() {
    super.dispose();
    implImage.dispose();
    useImage.dispose();
  }

}
