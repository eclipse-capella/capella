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
package org.polarsys.capella.core.ui.toolkit.decomposition;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import org.polarsys.capella.core.ui.toolkit.Activator;
import org.polarsys.capella.core.ui.toolkit.viewers.CapellaElementLabelProvider;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LogicalComponent;
//import org.polarsys.capella.core.projection.preferences.ProjectionPreferences;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;

/**
 * Class <code>DecompositionLabelProvider</code> provides the label for
 * <code>DecompositionGeneralViewer</code>
 * 
 */
public class DecompositionLabelProvider  extends CapellaElementLabelProvider implements IBaseLabelProvider, IColorProvider , IFontProvider{//extends ColumnLabelProvider
	private boolean _sourceViewer;
	private Font boldFont = new Font(Display.getCurrent(),
			"Verdana", 8, SWT.BOLD); //$NON-NLS-1$;
	private Font normalFont = new Font(Display.getCurrent(),
			"Verdana", 8, SWT.NONE); //$NON-NLS-1$;
	
	ImageDescriptor implImageDescriptorFromPlugin = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, Messages.getString("LCDecomp.interface.icon.LCInterfaceImpl")); //$NON-NLS-1$
	Image implImage = implImageDescriptorFromPlugin.createImage();
	ImageDescriptor useImageDescriptorFromPlugin = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, Messages.getString("LCDecomp.interface.icon.LCInterfaceUse")); //$NON-NLS-1$
	Image useImage = useImageDescriptorFromPlugin.createImage();
	
	
	/**
	 * @param flag
	 */
	public DecompositionLabelProvider(boolean flag, ImageRegistry imgRegistry_p) {
		setSourceViewer(flag);
	}

	@Override
	public String getText(Object element_p) {
		if (element_p instanceof DecompositionComponent) {
			DecompositionComponent comp = (DecompositionComponent) element_p;
			Object value = comp.getValue();
			if (null != value && value instanceof LogicalComponent) {
				LogicalComponent lc = (LogicalComponent) value;
				String lc_name = lc.getName();
				EList<AbstractTypedElement> abstractTypedElements = lc.getAbstractTypedElements();
				if (abstractTypedElements.isEmpty()) {
					return element_p.toString();
				}
				AbstractTypedElement abstractTypedElement = abstractTypedElements.get(0);
				String part_name = abstractTypedElement.getName();

				if (getSynchronizationModeSelected()) { 
				  if (comp.isComposite()) {
						if (comp.isTrigger()) {
							comp.setName(element_p.toString());
							return element_p.toString() + " : " + element_p.toString() + Messages.getString("LCDecomp.component.compositelabel"); //$NON-NLS-1$ //$NON-NLS-2$
						} 
						//return part_name + " : " + element_p.toString() + Messages.getString("LCDecomp.component.compositelabel"); //$NON-NLS-1$ //$NON-NLS-2$
						return  element_p.toString()+ Messages.getString("LCDecomp.component.compositelabel"); //$NON-NLS-1$ //$NON-NLS-2$
					}
				  //if not composite
					if (comp.isTrigger()) {
						comp.setName(element_p.toString());
						return element_p.toString() + " : " + element_p.toString(); //$NON-NLS-1$
					}
					//return part_name + " : " + element_p.toString(); //$NON-NLS-1$
					return element_p.toString(); //$NON-NLS-1$
				}
				
				// if not Synchronization Mode Selected
				if (comp.isComposite()) {
					if (comp.isTrigger()) {
						comp.setName(element_p.toString());
						return element_p.toString() + " : " + lc_name + Messages.getString("LCDecomp.component.compositelabel"); //$NON-NLS-1$ //$NON-NLS-2$
					} 
					return part_name + " : " + element_p.toString()+ Messages.getString("LCDecomp.component.compositelabel"); //$NON-NLS-1$ //$NON-NLS-2$
				}
				//if not composite
				if (comp.isTrigger()) {
					comp.setName(element_p.toString());
					return element_p.toString() + " : " + lc_name; //$NON-NLS-1$
				}
				//return part_name + " : " + element_p.toString();	 //$NON-NLS-1$
				return element_p.toString();	 //$NON-NLS-1$
				
			}
			//return element_p.toString() + " : " + element_p.toString();	 //$NON-NLS-1$
			return element_p.toString() ;	 //$NON-NLS-1$
		} 
		else if (element_p instanceof DecompositionItem) {
			DecompositionItem itemItf = (DecompositionItem) element_p;
			if (itemItf.isInternal()) {
				String label = new String(itemItf.getName());
				label = label.concat(" [Refined]"); //$NON-NLS-1$
				return label;
			}
		}
		
		return element_p.toString();
	}

	/**
	 * @return the sourceViewer
	 */
	public boolean isSourceViewer() {
		return _sourceViewer;
	}

	/**
	 * @param sourceViewer_p
	 *            the sourceViewer to set
	 */
	public void setSourceViewer(boolean sourceViewer_p) {
		_sourceViewer = sourceViewer_p;
	}

	/**
	 * @see org.eclipse.jface.viewers.ColumnLabelProvider#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(Object element_p) {
		if (element_p instanceof DecompositionComponent) {
			DecompositionComponent dComp = (DecompositionComponent) element_p;
			Object value = dComp.getValue();
			if(null != value){
				return super.getImage(value);
			}
      return super.getImage(LaFactory.eINSTANCE.createLogicalComponent());
		}

		if (element_p instanceof DecompositionItem) {
			DecompositionItem item = (DecompositionItem) element_p;
			Object value = item.getValue();
			if (item.isInterfaceUsage() && (null != value)) {
				return useImage;
			}
			return implImage;
		}

		if (element_p instanceof DecompositionItemService) {
			DecompositionItemService item = (DecompositionItemService) element_p;
			Object value = item.getValue();
			if (null != value) {
				return  super.getImage(value);
			}
			return null;
		}

		return null;
	}

	public Color getBackground(Object element) {
		return null;
	}

	public Color getForeground(Object element_p) {
		Color color = Display.getCurrent().getSystemColor(SWT.COLOR_BLACK);
		int status = -1;
		if (element_p instanceof DecompositionItem) {
			DecompositionItem item = (DecompositionItem) element_p;
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

	public Font getFont(Object element_p) {
		if (element_p instanceof DecompositionItem) {
		if (!isSourceViewer()) {
			if (((DecompositionItem) element_p).isAlreadyDecomposed()) {
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
