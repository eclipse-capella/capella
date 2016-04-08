/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.detachment.propertyvalues.ui.page;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.regex.PatternSyntaxException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.polarsys.capella.detachment.propertyvalue.Activator;
import org.polarsys.capella.detachment.propertyvalues.scrutinizers.PropertyGroupScrutinizer;
import org.polarsys.capella.detachment.propertyvalues.scrutinizers.PropertyValuePackageScrutinizer;
import org.polarsys.capella.detachment.propertyvalues.scrutinizers.PropertyValuesScrutinizer;
import org.polarsys.kitalpha.model.common.scrutiny.analyzer.ModelScrutinyException;
import org.polarsys.kitalpha.model.common.scrutiny.analyzer.Scrutineer;
import org.polarsys.kitalpha.model.common.scrutiny.interfaces.IScrutinize;
import org.polarsys.kitalpha.model.common.scrutiny.registry.ModelScrutinyRegistry.RegistryElement;

public class PropertyValueHelper {
	
	public static String getEObjectName(EObject eObject) {
		try {
			String name = (String) eObject.getClass().getMethod("getName").invoke(eObject); //$NON-NLS-1$
			EObject eContainer = eObject.eContainer();
			if (eContainer != null){
				name += " (" + (String) eContainer.getClass().getMethod("getName").invoke(eContainer) + ")";
			}
			
			return name == null || name.isEmpty()? "empty name":name; //$NON-NLS-1$
		} catch (Exception e) {
			throw new IllegalStateException("Unable to invoke getName() method on object " + eObject.toString(), e);
		}
	}
	
	/**
	 * @param tk
	 * @param parent
	 * @param sectionTitle
	 * @param description
	 * @return
	 */
	public static CheckboxTableViewer createSectionFilteredCheckboxTableViewer(
			final FormToolkit tk, 
			final Composite parent, 
			final String sectionTitle, 
			final String description,
			final Collection<EObject> input,
			final PropertyValueType type){
		
		
		final PropertyValueFilterViewer filter = new PropertyValueFilterViewer();
		
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		
		//Create filtering field
		Composite superComposite = tk.createComposite(parent);
		superComposite.setLayout(new GridLayout(1, true));
		superComposite.setLayoutData(gridData);
		
		final StyledText inputFilter = new StyledText(superComposite, SWT.SINGLE | SWT.CENTER | SWT.SEARCH);
		inputFilter.setText("");
		inputFilter.setToolTipText(getToolTipTextFor(type));
		inputFilter.setMargins(5, 1, 5, 1);
		inputFilter.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		
		Composite innerComposite = new Composite(superComposite, SWT.NONE);

		GridLayout innerGridData = new GridLayout(2, false);
		innerComposite.setLayout(innerGridData);

		innerComposite.setLayoutData(gridData);

		//Create a section
		Section section = tk.createSection(innerComposite, Section.TITLE_BAR | Section.DESCRIPTION | Section.EXPANDED);
		section.setDescription(description);

		GridData sectionGridData = new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL);
		section.setLayoutData(sectionGridData);

		tk.paintBordersFor(section);
		section.setText(sectionTitle);
		
		final CheckboxTableViewer tableViewer = CheckboxTableViewer.newCheckList(section, SWT.BORDER);
		tableViewer.getTable().setLayoutData(gridData);

		tk.paintBordersFor(tableViewer.getTable());
		section.setClient(tableViewer.getTable());

		//Add paint Listener to input filter
		inputFilter.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				e.gc.setAntialias(SWT.ON);
				Color blue = Display.getCurrent().getSystemColor(SWT.COLOR_BLUE);
				e.gc.setAlpha(140);
				e.gc.setForeground(blue);
				Rectangle rec = new Rectangle(0, 0, inputFilter.getClientArea().width - 1, inputFilter.getClientArea().height - 1);
				e.gc.drawRoundRectangle(0, 0, rec.width, rec.height, 3, 3);
			}
		});
		
		//Add Key Listener
		inputFilter.addKeyListener(new KeyAdapter() {
			
			ErrorRegExpMessageToolTip errorToolTip;

			@Override
			public void keyReleased(KeyEvent e) {
				try {
					
					if (errorToolTip != null){
						errorToolTip.hide();
						errorToolTip.deactivate();
					}
					inputFilter.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
					filter.setPattern(inputFilter.getText());
					tableViewer.refresh();
				} catch (PatternSyntaxException ex){
					inputFilter.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
					if (errorToolTip == null){
						errorToolTip = new ErrorRegExpMessageToolTip(inputFilter, "");
					}
					
					Point location = inputFilter.getLocation();
					errorToolTip.setRegExp(inputFilter.getText());
					errorToolTip.activate();
					errorToolTip.show(new Point(location.x * 2, location.y + 15));
				}
			}
		});
		
		
		
		

		Composite buttonsComposite = tk.createComposite(innerComposite, SWT.NONE);
		GridData gd = new GridData(GridData.FILL_VERTICAL);
		buttonsComposite.setLayoutData(gd);
		
		GridLayout buttonsLayout = new GridLayout();
		buttonsLayout.marginWidth = buttonsLayout.marginHeight = 0;

		buttonsComposite.setLayout(buttonsLayout);
		Button selectAll = tk.createButton(buttonsComposite, "Select All", SWT.BUTTON1); //$NON-NLS-1$
		Button deselectAll = tk.createButton(buttonsComposite, "Deselect All", SWT.BUTTON1); ////$NON-NLS-1$

		selectAll.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		deselectAll.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		
		//Add Listener selection on buttons
		selectAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tableViewer.setAllChecked(true);
			}
		});

		deselectAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tableViewer.setAllChecked(false);
			}
		});
		
		
		//Bind a table viewer to filter input
		tableViewer.addFilter(filter);
		
		tableViewer.setContentProvider(new PropertyValueContentProvider());
		tableViewer.setLabelProvider(new PropertyValueLabelProvider());
		
		tableViewer.setInput(input);
		return tableViewer;
	}

	private static String getToolTipTextFor(PropertyValueType type) {
		String res = "Filter ";
		switch (type){
		case PROPERTY_VALUE:
			return res + "Property Values"; //$NON-NLS-1$
		case PROPERTY_GROUP:
			return res + "Property Group"; //$NON-NLS-1$
		case PROPERTY_PACKAGE:
			return res + "Property Package"; //$NON-NLS-1$
		default:
			return "Unknown Property value kind"; //$NON-NLS-1$
		}
	}
	
	
	public static Collection<IScrutinize> getScrutinizers(String id){
		try {
			RegistryElement regElt = Scrutineer.getRegistryElement(id);	
			return regElt.getFinders();
		} catch (ModelScrutinyException e) {
			Status status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e);
			Activator.getDefault().getLog().log(status);
		}
		return Collections.emptySet();
	}
	
	
	@SuppressWarnings("unchecked")
	public static int getNbScrutinizerWithResult(Collection<IScrutinize> scrutinizers){
		int result = 0;
		
		for (IScrutinize iScrutinize : scrutinizers) {
				if (!((IScrutinize<Map<EObject, Boolean>, Object>)iScrutinize).getAnalysisResult().isEmpty()){
					result++;
				}
		}
		
		return result;
	}
	
	public static Collection<IScrutinize> getScrutinizersWithResult(Collection<IScrutinize> scrutinizers){
		Collection<IScrutinize> result = new LinkedHashSet<>();
		
		for (IScrutinize iScrutinizer : scrutinizers) {
			if (!((IScrutinize<Map<EObject, Boolean>, Object>)iScrutinizer).getAnalysisResult().isEmpty()){
				result.add(iScrutinizer);
			}
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static IScrutinize<Map<EObject, Boolean>, Object> getScrutinizerFor(PropertyValueType type, Collection<IScrutinize> scrutinizers){
		
		for (IScrutinize s : scrutinizers) {
			if (type.equals(PropertyValueType.PROPERTY_VALUE) && (s instanceof PropertyValuesScrutinizer)){
				return (IScrutinize<Map<EObject, Boolean>, Object>)s;
			}
			
			if (type.equals(PropertyValueType.PROPERTY_GROUP) && (s instanceof PropertyGroupScrutinizer)){
				return (IScrutinize<Map<EObject, Boolean>, Object>)s;
			}
			
			if (type.equals(PropertyValueType.PROPERTY_PACKAGE) && (s instanceof PropertyValuePackageScrutinizer)){
				return (IScrutinize<Map<EObject, Boolean>, Object>)s;
			}
		}
		
		return null;
	}

}
