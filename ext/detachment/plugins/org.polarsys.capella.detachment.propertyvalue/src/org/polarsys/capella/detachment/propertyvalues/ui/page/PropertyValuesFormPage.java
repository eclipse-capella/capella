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
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.polarsys.kitalpha.model.common.scrutiny.interfaces.IScrutinize;

public class PropertyValuesFormPage extends org.polarsys.kitalpha.model.detachment.ui.page.AbstractDetachmentFormPage {

	private Map<EObject, Boolean> propertyValues;
	private Map<EObject, Boolean> propertyGroups;
	private Map<EObject, Boolean> propertyPkgs;

	public PropertyValuesFormPage(FormEditor editor, String id, String title) {
		super(editor, id, title);
	}


	@SuppressWarnings("rawtypes")
	@Override
	protected void createFormContent(IManagedForm managedForm) {

		final FormToolkit tk = managedForm.getToolkit();
		final ScrolledForm scrolledForm = managedForm.getForm();
		scrolledForm.setText("Property Values");	
		scrolledForm.setExpandHorizontal(true);

		
		
		Collection<IScrutinize> scrutinizers = PropertyValueHelper.getScrutinizers(getFinderID());
		
		int nbScrutinizersWithResult = PropertyValueHelper.getNbScrutinizerWithResult(scrutinizers);
		
		if (nbScrutinizersWithResult > 0){
			Composite composite = initializeFormLayout(scrolledForm, nbScrutinizersWithResult);
			
			Collection<IScrutinize> scrutinizersWithResult = PropertyValueHelper.getScrutinizersWithResult(scrutinizers);
			
			IScrutinize pvScrutinizer = PropertyValueHelper.getScrutinizerFor(PropertyValueType.PROPERTY_VALUE, scrutinizersWithResult);
			if (pvScrutinizer != null)
				initializePropertyValuesViewer(tk, composite, pvScrutinizer);
			
			
			IScrutinize pgScrutinizer = PropertyValueHelper.getScrutinizerFor(PropertyValueType.PROPERTY_GROUP, scrutinizersWithResult);
			if (pgScrutinizer != null)
				initializePropertyGroupViewer(tk, composite, pgScrutinizer);
			
			IScrutinize pkScrutinizer = PropertyValueHelper.getScrutinizerFor(PropertyValueType.PROPERTY_PACKAGE, scrutinizersWithResult);
			
			if (pkScrutinizer != null)
				initializePropertyPackageViewer(tk, composite, pkScrutinizer);
			
		} else {
			Composite composite = initializeFormLayout(scrolledForm, 1);
			tk.createLabel(composite, "No Property Values found in the model");
		}
	}


	private Composite initializeFormLayout(final ScrolledForm scrolledForm, int nbScrutinizersWithResult) {
		Composite composite = scrolledForm.getBody();
		composite.setLayout(new GridLayout(nbScrutinizersWithResult, true));
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		composite.setLayoutData(gridData);
		return composite;
	}


	private void initializePropertyPackageViewer(final FormToolkit tk, Composite composite, IScrutinize iScrutinize) {
		IScrutinize<Map<EObject, Boolean>, Object> pkScrutiny = (IScrutinize<Map<EObject, Boolean>, Object>)iScrutinize;
		propertyPkgs = pkScrutiny.getAnalysisResult();
		
		CheckboxTableViewer tableViewer = PropertyValueHelper.createSectionFilteredCheckboxTableViewer(tk, composite, "Property Packages", "List of Property Packages", ((IScrutinize<Map<EObject, Boolean>, Object>)iScrutinize).getAnalysisResult().keySet(), PropertyValueType.PROPERTY_PACKAGE);
		
		tableViewer.addCheckStateListener(new ICheckStateListener() {

			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				Object element = event.getElement();

				if (element instanceof EObject){
					EObject eObject = (EObject)element;
					if (propertyPkgs.containsKey(eObject)){
						propertyPkgs.put(eObject, event.getChecked());
					}
				}
			}
		});
	}


	private void initializePropertyGroupViewer(final FormToolkit tk, Composite composite, IScrutinize iScrutinize) {
		IScrutinize<Map<EObject, Boolean>, Object> pgScrutiny = (IScrutinize<Map<EObject, Boolean>, Object>)iScrutinize;
		propertyGroups = pgScrutiny.getAnalysisResult();
		
		CheckboxTableViewer tableViewer = PropertyValueHelper.createSectionFilteredCheckboxTableViewer(tk, composite, "Property Groups", "List of Property Groups", ((IScrutinize<Map<EObject, Boolean>, Object>)iScrutinize).getAnalysisResult().keySet(), PropertyValueType.PROPERTY_GROUP);
		
		tableViewer.addCheckStateListener(new ICheckStateListener() {

			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				Object element = event.getElement();

				if (element instanceof EObject){
					EObject eObject = (EObject)element;
					if (propertyGroups.containsKey(eObject)){
						propertyGroups.put(eObject, event.getChecked());
					}
				}
			}
		});
	}


	private void initializePropertyValuesViewer(final FormToolkit tk, Composite composite, IScrutinize iScrutinize) {
		IScrutinize<Map<EObject, Boolean>, Object> pvScrutiny = (IScrutinize<Map<EObject, Boolean>, Object>)iScrutinize;
		propertyValues = pvScrutiny.getAnalysisResult();
		final CheckboxTableViewer tableViewer = PropertyValueHelper.createSectionFilteredCheckboxTableViewer(tk, composite, "Property Values", "List of Property Values", propertyValues.keySet(), PropertyValueType.PROPERTY_VALUE);
		
		tableViewer.addCheckStateListener(new ICheckStateListener() {

			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				Object element = event.getElement();
				if (element instanceof EObject){
					EObject eObject = (EObject)element;
					if (propertyValues.containsKey(eObject)){
						propertyValues.put(eObject, event.getChecked());
					}
				}
			}
		});
	}
}