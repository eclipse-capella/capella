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
package org.polarsys.capella.core.ui.properties.fields;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.common.data.modellingcore.ValueSpecification;
import org.polarsys.capella.common.menu.dynamic.CreationHelper;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacoreFactory;
import org.polarsys.capella.core.data.capellacore.Constraint;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.ui.properties.controllers.AbstractSimpleEditableSemanticFieldController;

/**
 * A helper that creates a group for a number of constraint references, intended
 * for use in capella property sections. Not intended to be subclassed.
 */
public class ConstraintReferenceGroup {

  private final Map<String, EReference> refs;
  private final Map<EReference, AbstractSemanticField> fields;

  /**
   * Creates a group for the given references. The reference must be able to hold
   * a constraint and must be single valued. The keys in the argument map
   * are used as the field labels.
   * 
   * @param refs_p a map of field labels to constraint references.
   */
  public ConstraintReferenceGroup(Map<String, EReference> refs_p){
    refs = refs_p;
    fields = new HashMap<EReference, AbstractSemanticField>();
  }

  /**
   * Create the controls for the given parent. The parent is expected to use a grid layout.
   * The created controls are put inside a group that spans the entire column count of the parent
   * layout.
   * 
   * @param parent_p
   * @param factory_p
   * @param isDisplayedInWizard_p
   */
  public void createControls(Composite parent_p, TabbedPropertySheetWidgetFactory factory_p, boolean isDisplayedInWizard_p){
    Group referenceGroup = factory_p.createGroup(parent_p, ""); //$NON-NLS-1$
    
    GridData gd = new GridData(SWT.FILL, SWT.TOP, true, false);
    gd.horizontalSpan = ((GridLayout)(parent_p.getLayout())).numColumns;
    
    referenceGroup.setLayoutData(gd);
    
    // label, text, edit, browse, delete. SimpleEditableSemanticField uses 2 columns for the text field, so we need to add one extra column.
    referenceGroup.setLayout(new GridLayout(6, false));
    for (final Map.Entry<String, EReference> entry : refs.entrySet()){

      SimpleEditableSemanticField field = new SimpleEditableSemanticField(referenceGroup, entry.getKey(), factory_p, "", new AbstractSimpleEditableSemanticFieldController() { //$NON-NLS-1$

        @Override
        public EObject writeOpenValue(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p, String defaultName_p, EObject value_p) {
          semanticElement_p.eSet(semanticFeature_p, value_p);
          return value_p;
        }

        @Override
        public EObject editValue(CapellaElement semanticElement_p, EStructuralFeature semanticFeature_p, String defaultName_p) {
	    	boolean wasCreatedSpec = false;
	    	boolean wasCreatedCons = false;
        	
	    	// Get constraint on semantic element
        	Constraint constraint = ((Constraint) semanticElement_p.eGet(semanticFeature_p));
        	
        	// If element has no constraint, create one
	    	if (null == constraint) {
	    		constraint = CapellacoreFactory.eINSTANCE.createConstraint();
	    		semanticElement_p.getOwnedConstraints().add(constraint);
	            CreationHelper.performContributionCommands(constraint, semanticElement_p);
	            semanticElement_p.eSet(semanticFeature_p, constraint);
	            wasCreatedCons = true;
	    	}
	    	
	    	ValueSpecification specification = constraint.getOwnedSpecification();
	    	
	    	// In case constraint was created, need to create associated specification
	    	if (null == specification) {
	    		specification = DatavalueFactory.eINSTANCE.createOpaqueExpression();
	    		constraint.setOwnedSpecification(specification);
	    		CreationHelper.performContributionCommands(specification, constraint);
	    		wasCreatedSpec = true;
	    	}
	    	
	    	// Tell if user has clicked on finish or cancel
	    	boolean hasPerformedFinish = editValueWizard(specification);
	    	
	    	// In case of cancel, must remove the constraint and specification in case it has been created
	    	if (!hasPerformedFinish) {
	    		if (wasCreatedCons) {
	    			SimpleEditableSemanticField.deleteContainmentValue(constraint);
	    		} else if (wasCreatedSpec) {
	    			SimpleEditableSemanticField.deleteContainmentValue(specification);
	    		}
	    	}
	    	return (EObject) semanticElement_p.eGet(semanticFeature_p);
        }
      });
      field.setDisplayedInWizard(isDisplayedInWizard_p);
      fields.put(entry.getValue(), field);
    }
  }
  
  public void loadData(CapellaElement element_p){
    for (Map.Entry<EReference, AbstractSemanticField> e : fields.entrySet()){
      e.getValue().loadData(element_p, e.getKey());
    }
  }

  /**
   * Returns the semantic fields in this group.
   */
  public Collection<? extends AbstractSemanticField> getFields() {
    return fields.values();
  }
}
