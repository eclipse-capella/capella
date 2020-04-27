/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.eclipse.emf.diffmerge.patterns.capella.ext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.diffmerge.patterns.templates.ocl.IOclEnvironmentCustomizer;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;

import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.capellacore.ModellingArchitecture;
import org.polarsys.capella.core.data.capellamodeller.ModelRoot;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;


/**
 * An customization for Capella of the OCL environment for Template Patterns
 */
public class CapellaOclEnvironmentCustomizer implements IOclEnvironmentCustomizer {
  
  /**
   * Add a variable specification for each Capella architecture to the given collection
   * @param se_p a non-null Capella system engineering
   * @param specs_p a non-null, modifiable collection
   */
  private void addArchitectureVariables(SystemEngineering se_p,
      Collection<VariableSpecification> specs_p) {
    for (ModellingArchitecture archi : se_p.getOwnedArchitectures()) {
      String name = getArchitectureName(archi);
      VariableSpecification spec = createVariableSpecification(archi, name);
      if (spec != null)
        specs_p.add(spec);
    }
  }
  
  /**
   * Return a new variable specification specifically tailored for the given element
   * @param element_p a potentially null element
   * @param name_p a potentially null string
   * @return a potentially null variable specification
   */
  private VariableSpecification createVariableSpecification(EObject element_p, String name_p) {
    VariableSpecification result = null;
    if (name_p != null) {
      EClassifier type = element_p != null? element_p.eClass(): EcorePackage.eINSTANCE.getEObject();
      result = new VariableSpecification(name_p, type, element_p);
    }
    return result;
  }
  
  /**
   * Return a name for the variable which represents the given Capella architecture
   * @param architecture_p a potentially null architecture
   * @return a potentially null string
   */
  private String getArchitectureName(ModellingArchitecture architecture_p) {
    String result = null;
    if (architecture_p instanceof OperationalAnalysis)
      result = "oa"; //$NON-NLS-1$
    else if (architecture_p instanceof SystemAnalysis)
      result = "sa"; //$NON-NLS-1$
    else if (architecture_p instanceof LogicalArchitecture)
      result = "la"; //$NON-NLS-1$
    else if (architecture_p instanceof PhysicalArchitecture)
      result = "pa"; //$NON-NLS-1$
    else if (architecture_p instanceof EPBSArchitecture)
      result = "epbs"; //$NON-NLS-1$
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.patterns.templates.ocl.IOclEnvironmentCustomizer#getVariables(org.eclipse.emf.ecore.EObject)
   */
  public Collection<VariableSpecification> getVariables(EObject context_p) {
    Collection<VariableSpecification> result = new ArrayList<VariableSpecification>();
    SystemEngineering se = getSystemEngineering(context_p);
    if (se != null)
      addArchitectureVariables(se, result);
    return Collections.unmodifiableCollection(result);
  }
  
  /**
   * Return the Capella system engineering related to the given contextual element
   * @param context_p a potentially null element
   * @return a potentially null system engineering
   */
  private SystemEngineering getSystemEngineering(EObject context_p) {
    SystemEngineering result = null;
    if (context_p instanceof Project) {
      for (ModelRoot root : ((Project)context_p).getOwnedModelRoots()) {
        if (root instanceof SystemEngineering) {
          result = (SystemEngineering)root;
          break;
        }
      }
    } else {
      EObject current = context_p;
      while (current != null && result == null) {
        if (current instanceof SystemEngineering)
          result = (SystemEngineering)current;
        current = current.eContainer();
      }
    }
    return result;
  }
  
}
