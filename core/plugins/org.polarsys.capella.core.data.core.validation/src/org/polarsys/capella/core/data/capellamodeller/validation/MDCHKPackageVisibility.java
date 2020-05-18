/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.capellamodeller.validation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.Generalization;
import org.polarsys.capella.core.data.capellacore.Relationship;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
/**
 * 
 * 
 * This rule detects that it is not possible to create an association from Class1 to Class2 if Class2 is in package0, and Class1 in packag1 within package0.
 *
 */
public class MDCHKPackageVisibility extends AbstractValidationRule{
	
	/**
	 * 
	 * @param modelElement
	 * @return
	 */
	private boolean isImpactedElement(ModelElement modelElement) {
		return ((modelElement!=null) && modelElement instanceof  Class) || ((modelElement!=null) && modelElement instanceof  Collection) || ((modelElement!=null) && modelElement instanceof  DataType);
	}
	
		/**
		 * 
		 */
	  private Set<IStatus> statuses ;

	@Override
	public IStatus validate(IValidationContext context) {
		EObject eObj = context.getTarget();
	    EMFEventType eType = context.getEventType();
	    if (eType == EMFEventType.NULL) {
	      if (eObj instanceof SystemEngineering) {
	    	  statuses = new HashSet<IStatus>();
	    	  SystemEngineering system = (SystemEngineering)eObj;
	    	  List<DataPkg> allPackages = SystemEngineeringExt.getAllDataPkgs(system);
			    for (DataPkg aPackage : allPackages) {
			    	validateAssociation(context, aPackage);
			    	validateGeneralization(context, aPackage);
			     }
			    if (!statuses.isEmpty()) {
		            // There are conflicts returns them as a multi-statuses status
		            return ConstraintStatus.createMultiStatus(context, statuses);
		        }
	      }
	
	    }
		return context.createSuccessStatus();
	}

	
	
	/**
	 * @param context
	 * @param aPackage
	 */
	private void validateAssociation(IValidationContext context, DataPkg aPackage) {
		EList<Association> associations = aPackage.getOwnedAssociations() ;
		for (Association association : associations) {
			validatePackageVisibility(context,association, aPackage);
		}
	}
	/**
	 * @param context
	 * @param aPackage
	 */
	private void validateGeneralization(IValidationContext context, DataPkg aPackage) {
		Set<Generalization> generalizations = DataPkgExt.getAllGeneralization(aPackage);
		for (Generalization generalization : generalizations) {
			validatePackageVisibility(context,generalization, aPackage);
		}
		
	}

	
	
	
	/**
	 * @param association
	 * @param aPackage
	 */
	private void validatePackageVisibility(IValidationContext context, Relationship relation,	DataPkg aPackage) {
		
		GeneralizableElement sourceElement = null ;
		GeneralizableElement targetElement = null ;
		
		 DataPkg sourcePackage = null;
		 DataPkg targetPackage = null;
		
		if (relation instanceof Association) {
			Association association = (Association) relation;
			if (!association.getNavigableMembers().isEmpty() && !association.getOwnedMembers().isEmpty()){
			Property navigableMember = association.getNavigableMembers().get(0);
			Property ownedMemmber = association.getOwnedMembers().get(0);
			
			if (isImpactedElement(ownedMemmber.getAbstractType())
					&& isImpactedElement(navigableMember.getAbstractType())) 
				{
					sourceElement = (GeneralizableElement) ownedMemmber.getAbstractType();
				 	targetElement = (GeneralizableElement) navigableMember.getAbstractType();
				 	sourcePackage = (DataPkg) sourceElement.eContainer();
				 	targetPackage = (DataPkg) targetElement.eContainer();
				}
			}
			
		}else if (relation instanceof Generalization) {
			Generalization generalization = (Generalization) relation;
			targetElement = generalization.getSuper();
			sourceElement = generalization.getSub();
			
			if (isImpactedElement(sourceElement)
					&& isImpactedElement(targetElement)) 
			{
				 sourcePackage = (DataPkg) sourceElement.eContainer();
				 targetPackage = (DataPkg) targetElement.eContainer();
			}
		}
		
		 boolean isAncestorNavigablePackage = targetPackage!=null && sourcePackage!=null && DataPkgExt.isAncestorPackage(targetPackage,sourcePackage);
			if (isAncestorNavigablePackage) {
				String SOURCE_PREFIXE = "\"" + sourceElement.getName()  +"\" ( "+ sourceElement.eClass().getName() + " ) ";
				String TARGET_PREFIXE = "\"" + targetElement.getName()  +"\" ( "+ targetElement.eClass().getName() + " ) ";
				this.statuses.add(createFailureStatus(context, new Object[] { SOURCE_PREFIXE,TARGET_PREFIXE })) ;
				
			}
		
		
	}
}
