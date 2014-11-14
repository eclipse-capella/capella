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
package org.polarsys.capella.core.model.handler.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;

/**
 * A model command that deletes given elements, along with their references.<br>
 * This code is taken from {@link org.eclipse.emf.edit.command.DeleteCommand}, but differs at cross referencing time, and allow for sub-classing deletion
 * behavior.
 */
public class DeleteCommand extends CompoundCommand {
  /**
   * Editing domain holding semantic elements, in which the command operates.
   */
  protected EditingDomain _editingDomain;
  /**
   * Collection of elements to be deleted.
   */
  protected Collection<?> _elementsToDelete;

  /**
   * Constructor.
   * @param editingDomain_p
   * @param elements_p
   */
  public DeleteCommand(EditingDomain editingDomain_p, Collection<?> elements_p) {
    Assert.isTrue(editingDomain_p instanceof SemanticEditingDomain);
    _editingDomain = editingDomain_p;
    // Avoid conflicting use of specified collection by cloning it.
    _elementsToDelete = new ArrayList<Object>(elements_p);
  }

  /**
   * Delete specified pointing reference.
   * @param referencingEObject_p The object referencing the deleted element.
   * @param feature_p The feature hosting the reference.
   * @param referenceToDelete_p The deleted element to remove.
   */
  protected void deletePointingReference(EObject referencingEObject_p, EStructuralFeature feature_p, EObject referenceToDelete_p) {
    if (feature_p.isMany()) {
      appendAndExecute(RemoveCommand.create(_editingDomain, referencingEObject_p, feature_p, referenceToDelete_p));
    } else {
      appendAndExecute(SetCommand.create(_editingDomain, referencingEObject_p, feature_p, null));
    }
  }

  /**
   * Do add an initial command to this compound one.<br>
   * Default implementation adds an EMF {@link RemoveCommand}.
   */
  protected void doPrepare() {
    append(RemoveCommand.create(_editingDomain, _elementsToDelete));
  }

  /**
   * @see org.eclipse.emf.common.command.CompoundCommand#execute()
   */
  @Override
  public void execute() {
    // Unwrap elements to be deleted.
    Collection<EObject> eObjects = new LinkedHashSet<EObject>();
    for (Object wrappedObject : _elementsToDelete) {
      Object object = AdapterFactoryEditingDomain.unwrap(wrappedObject);
      if (object instanceof EObject) {
        eObjects.add((EObject) object);
        for (Iterator<EObject> j = ((EObject) object).eAllContents(); j.hasNext();) {
          eObjects.add(j.next());
        }
      } else if (object instanceof Resource) {
        for (Iterator<EObject> j = ((Resource) object).getAllContents(); j.hasNext();) {
          eObjects.add(j.next());
        }
      }
    }

    // Use permanent cross referencer for better performances.
    // Note that this one does only take into account semantic resources.
    ECrossReferenceAdapter crossReferencer = ((SemanticEditingDomain) _editingDomain).getCrossReferencer();
    Map<EObject, Collection<EStructuralFeature.Setting>> usages = new HashMap<EObject, Collection<Setting>>(0);
    for (EObject eObject : eObjects) {
      Collection<Setting> inverseReferences = crossReferencer.getInverseReferences(eObject, true);
      // Remove containment references out of inverse references, there are handled natively by the command.
      for (Iterator<Setting> settings = inverseReferences.iterator(); settings.hasNext();) {
        Setting setting = settings.next();
        try {
          // Test feature against reference, and containment.
          if (EReference.class.cast(setting.getEStructuralFeature()).isContainment()) {
            // Remove this feature from inverse references.
            settings.remove();
          }
        } catch (ClassCastException cce_p) {
          // Not a reference, skip it.
        }
      }
      usages.put(eObject, inverseReferences);
    }

    // Execute composing commands.
    super.execute();

    // Delete references.
    for (Map.Entry<EObject, Collection<EStructuralFeature.Setting>> entry : usages.entrySet()) {
      EObject eObject = entry.getKey();
      Collection<EStructuralFeature.Setting> settings = entry.getValue();
      for (EStructuralFeature.Setting setting : settings) {
        EObject referencingEObject = setting.getEObject();
        if (!eObjects.contains(referencingEObject)) {
          EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
          if (eStructuralFeature.isChangeable()) {
            deletePointingReference(referencingEObject, eStructuralFeature, eObject);
          }
        }
      }
    }
  }

  /**
   * @see org.eclipse.emf.common.command.CompoundCommand#prepare()
   */
  @Override
  protected boolean prepare() {
    doPrepare();
    return super.prepare();
  }
}