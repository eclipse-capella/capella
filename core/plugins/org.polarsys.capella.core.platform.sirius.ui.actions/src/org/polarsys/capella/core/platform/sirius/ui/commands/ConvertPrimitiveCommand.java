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

package org.polarsys.capella.core.platform.sirius.ui.commands;
import java.text.MessageFormat;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.information.AggregationKind;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.datavalue.DatavalueFactory;
import org.polarsys.capella.core.data.information.datavalue.LiteralNumericValue;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;


public class ConvertPrimitiveCommand extends AbstractReadWriteCommand {

	/**
	 * Model element on which the command is launched
	 */
	private ModelElement _modelElement;
	
	/**
	 * Capella Logger
	 */
	private static final Logger _logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.DEFAULT);
	
	/**
	 * Default constructor
	 */
	public ConvertPrimitiveCommand(ModelElement modelElement) {
		_modelElement = modelElement;
	}
	
	public void run() {
		if (_modelElement instanceof org.polarsys.capella.core.data.information.Class) {
			org.polarsys.capella.core.data.information.Class myClass = (org.polarsys.capella.core.data.information.Class) _modelElement;
			
			/**
			 * Set variables
			 */
			//final ModelElement owner
			DataPkg myClassPkg = null;
			if (myClass.eContainer() instanceof DataPkg) {
				myClassPkg = (DataPkg) myClass.eContainer();
			} else {
				_logger.error(new EmbeddedMessage(MessageFormat.format("myClassPkg is null", myClass.getId()), IReportManagerDefaultComponents.MODEL, myClass)); //$NON-NLS-1$
			}
			
			
			/**
			 * Change Class primitive state
			 */
			myClass.setIsPrimitive(false);
			
			
			// Transfer Attributes into Compositions
			for (AbstractTypedElement currentAbstractTypedElement : myClass.getAbstractTypedElements()) {
				if (currentAbstractTypedElement instanceof Property && myClassPkg != null) {
					Property currentProperty = (Property) currentAbstractTypedElement;
					
					// Update Property to new Class
					currentProperty.setAggregationKind(AggregationKind.COMPOSITION);
					
					// Create Association
					Association assoc = InformationFactory.eINSTANCE.createAssociation(currentProperty.getName());
					myClassPkg.getOwnedAssociations().add(assoc);
					assoc.setDescription(currentProperty.getDescription());
					assoc.setSummary(currentProperty.getSummary());
					assoc.getNavigableMembers().add(currentProperty);
					
					// Create second Property
					Property secondProperty = InformationFactory.eINSTANCE.createProperty();
					EObject currentPropertyContainer = currentProperty.eContainer();
					if (currentPropertyContainer instanceof org.polarsys.capella.core.data.information.Class) {
						org.polarsys.capella.core.data.information.Class srcClass = (org.polarsys.capella.core.data.information.Class) currentPropertyContainer;
						
						secondProperty.setAbstractType(srcClass);
						assoc.getOwnedMembers().add(secondProperty);
						secondProperty.setName(srcClass.getName().substring(0, 1).toLowerCase() + srcClass.getName().substring(1));
					} else {
						_logger.error(new EmbeddedMessage(MessageFormat.format("Container of "+ currentProperty.getFullLabel() +" is not a Class !", currentProperty.getId()), IReportManagerDefaultComponents.MODEL, currentProperty)); //$NON-NLS-1$ //$NON-NLS-2$
					}
					secondProperty.setAggregationKind(AggregationKind.ASSOCIATION);
					
					// create secondProperty min/max Cards
					LiteralNumericValue minCard = DatavalueFactory.eINSTANCE.createLiteralNumericValue();
					LiteralNumericValue maxCard = DatavalueFactory.eINSTANCE.createLiteralNumericValue();
					minCard.setValue("1"); //$NON-NLS-1$
					maxCard.setValue("1"); //$NON-NLS-1$
					secondProperty.setOwnedMinCard(minCard);
					secondProperty.setOwnedMaxCard(maxCard);
				} else {
					_logger.error(new EmbeddedMessage(MessageFormat.format("Typed Element is not a property", currentAbstractTypedElement.getId()), IReportManagerDefaultComponents.MODEL, currentAbstractTypedElement)); //$NON-NLS-1$
				}
			}
			
			
		} else {
			_logger.error(new EmbeddedMessage(MessageFormat.format("Selected Element is not correct : "+ _modelElement.getFullLabel(), _modelElement.getId()), IReportManagerDefaultComponents.MODEL, _modelElement)); //$NON-NLS-1$
		}
	}
}
