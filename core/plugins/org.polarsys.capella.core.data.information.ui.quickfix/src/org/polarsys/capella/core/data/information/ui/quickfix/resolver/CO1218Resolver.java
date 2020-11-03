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
package org.polarsys.capella.core.data.information.ui.quickfix.resolver;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.information.AggregationKind;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.information.ui.quickfix.messages.InformationQuickFixMessages;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

/**
 * Consider 'Class_A' with 'property' which is typed by non-Primitive type
 * 'Class_B' The Quick fix action on the warning will create an association
 * between Class_A and Class_B
 * 
 * 
 */
public class CO1218Resolver extends AbstractCapellaMarkerResolution {

	protected Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.VALIDATION);

	/**
	 * {@inheritDoc}
	 */
	public void run(IMarker marker) {
		List<EObject> modelElements = getModelElements(marker);
		if (modelElements.isEmpty())
			return;
		if (!(modelElements.get(0) instanceof Property))
			return;
		// Property
		final Property property = (Property) getModelElements(marker).get(0);

		if (null == property.getAssociation()) {
			final String errorMessage = InformationQuickFixMessages.cO1218_exception_message;
			final EObject propertyContianer = property.eContainer();
			if (propertyContianer instanceof Collection
					|| propertyContianer instanceof Class) {
				// considering the propertyContianer is non primitive (which is
				// implied in the validation rule itself)
				AbstractType abstractType = property.getAbstractType();
				if (null != abstractType
						&& (abstractType instanceof Collection || abstractType instanceof Class)) {
					final DataPkg parentDataPkg = DataPkgExt
							.getParentDataPkg((Classifier) abstractType);
					if (null != parentDataPkg) {
						// read write command
						AbstractReadWriteCommand collectElementsCommand = new AbstractReadWriteCommand() {
							// association to create
							Association association = null;
							// property to create under association
							Property prop = null;

							/**
							 * @see java.lang.Runnable#run()
							 */
							public void run() {
								try {
									// create association
									association = InformationFactory.eINSTANCE
											.createAssociation();
									parentDataPkg.getOwnedAssociations().add(
											association);
									CapellaElementExt.creationService(
											association, association.eClass()
													.getName());
									// create property
									prop = InformationFactory.eINSTANCE
											.createProperty();
									// add owned members to an association
									association.getOwnedMembers().add(prop);
									CapellaElementExt.creationService(prop, prop
											.eClass().getName());
									// set type
									prop.setAbstractType((Classifier) propertyContianer);
									// set aggregation kind
									prop.setAggregationKind(AggregationKind.ASSOCIATION);
									// add navigation members to an association
									association.getNavigableMembers().add(
											property);
								} catch (Exception exception) {
									// log err message
									commandRolledBack();
									logger.error(new EmbeddedMessage(
											errorMessage,
											IReportManagerDefaultComponents.VALIDATION));
								}
							}

							/**
							 * {@inheritDoc}
							 */
							@Override
							public void commandRolledBack() {
								super.commandRolledBack();
								if (null != association) {
									association.destroy();
								}
								if (null != prop) {
									prop.destroy();
								}
							}
						};
						// execute the command
						TransactionHelper.getExecutionManager(modelElements).execute(
								collectElementsCommand);
					}
				}
			}
		}
	}

	@Override
	public void run(IMarker[] markers, IProgressMonitor monitor) {

		for (IMarker marker : markers) {

			List<EObject> modelElements = getModelElements(marker);
			if (modelElements.isEmpty())
				return;

			if (!(modelElements.get(0) instanceof Property))
				return;

			// Property
			final Property property = (Property) modelElements.get(0);

			if (null == property.getAssociation()) {
				final String errorMessage = InformationQuickFixMessages.cO1218_exception_message;
				final EObject propertyContianer = property.eContainer();
				if (propertyContianer instanceof Collection
						|| propertyContianer instanceof Class) {
					// considering the propertyContianer is non primitive (which
					// is implied in the validation rule itself)
					AbstractType abstractType = property.getAbstractType();
					if (null != abstractType
							&& (abstractType instanceof Collection || abstractType instanceof Class)) {
						final DataPkg parentDataPkg = DataPkgExt
								.getParentDataPkg((Classifier) abstractType);
						if (null != parentDataPkg) {
							// read write command
							AbstractReadWriteCommand collectElementsCommand = new AbstractReadWriteCommand() {
								// association to create
								Association association = null;
								// property to create under association
								Property prop = null;

								/**
								 * @see java.lang.Runnable#run()
								 */
								public void run() {
									try {
										// create association
										association = InformationFactory.eINSTANCE
												.createAssociation();
										parentDataPkg.getOwnedAssociations()
												.add(association);
										CapellaElementExt.creationService(
												association, association
														.eClass().getName());
										// create property
										prop = InformationFactory.eINSTANCE
												.createProperty();
										// add owned members to an association
										association.getOwnedMembers().add(prop);
										CapellaElementExt.creationService(prop,
												prop.eClass().getName());
										// set type
										prop.setAbstractType((Classifier) propertyContianer);
										// set aggregation kind
										prop.setAggregationKind(AggregationKind.ASSOCIATION);
										// add navigation members to an
										// association
										association.getNavigableMembers().add(
												property);
									} catch (Exception exception) {
										// log err message
										commandRolledBack();
										logger.error(new EmbeddedMessage(
												errorMessage,
												IReportManagerDefaultComponents.VALIDATION));
									}
								}

								/**
								 * {@inheritDoc}
								 */
								@Override
								public void commandRolledBack() {
									super.commandRolledBack();
									if (null != association) {
										association.destroy();
									}
									if (null != prop) {
										prop.destroy();
									}
								}
							};
							// execute the command
							TransactionHelper.getExecutionManager(property).execute(collectElementsCommand);
						}
					}
				}
			}

		}

	}
}
