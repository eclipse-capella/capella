/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.fields;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.core.ui.properties.controllers.ConstraintController;

/**
 * A helper that creates a group for a number of constraint references, intended
 * for use in capella property sections. Not intended to be subclassed.
 */
public class ConstraintReferenceGroup {

  private final Map<String, EReference> refs;
  private final Map<EReference, AbstractSemanticField> fields;
  boolean displayOpenButton;
  
  /**
   * Creates a group for the given references. The reference must be able to hold
   * a constraint and must be single valued. The keys in the argument map
   * are used as the field labels.
   * 
   * @param refs a map of field labels to constraint references.
   */
  public ConstraintReferenceGroup(Map<String, EReference> refs){
    this(refs, true);
  }
  
  public ConstraintReferenceGroup(Map<String, EReference> refs, boolean displayOpenButton){
    this.displayOpenButton = displayOpenButton;
    this.refs = refs;
    fields = new HashMap<EReference, AbstractSemanticField>();
  }
  
  /**
   * Create the controls for the given parent. The parent is expected to use a grid layout.
   * The created controls are put inside a group that spans the entire column count of the parent
   * layout.
   * 
   * @param parent
   * @param factory
   * @param isDisplayedInWizard
   */
  public void createControls(Composite parent, TabbedPropertySheetWidgetFactory factory, boolean isDisplayedInWizard){
    Group referenceGroup = factory.createGroup(parent, ""); //$NON-NLS-1$
    
    GridData gd = new GridData(SWT.FILL, SWT.TOP, true, false);
    gd.horizontalSpan = ((GridLayout)(parent.getLayout())).numColumns;
    referenceGroup.setLayoutData(gd);
    referenceGroup.setLayout(new GridLayout(4 + (displayOpenButton ? 1 : 0), false));
    
    // label, text, edit, browse, delete. SimpleEditableSemanticField uses 2 columns for the text field, so we need to add one extra column.
    for (final Map.Entry<String, EReference> entry : refs.entrySet()){
      ConstraintReferenceField field = new ConstraintReferenceField(referenceGroup, entry.getKey(), factory, displayOpenButton, new ConstraintController());
      field.setDisplayedInWizard(isDisplayedInWizard);
      fields.put(entry.getValue(), field);
    }
  }
  
  public void loadData(EObject element){
    for (Map.Entry<EReference, AbstractSemanticField> e : fields.entrySet()){
      e.getValue().loadData(element, e.getKey());
    }
  }

  /**
   * Returns the semantic fields in this group.
   */
  public Collection<? extends AbstractSemanticField> getFields() {
    return fields.values();
  }
}
