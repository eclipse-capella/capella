/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.extension.migration.egf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.egf.core.producer.InvocationException;
import org.eclipse.egf.ftask.producer.context.ITaskProductionContext;
import org.eclipse.egf.ftask.producer.invocation.ITaskProduction;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenResourceKind;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;

/**
 * 
 */
public class GenerateGenModelsTask implements ITaskProduction {

	public void doExecute(ITaskProductionContext productionContext, IProgressMonitor monitor)
			throws InvocationException {
		List<GenModel> genModels = new ArrayList<GenModel>();
		generateGenModel(genModels, "/org.polarsys.capella.common.data.def/model/ModellingCore.ecore",
				"org.polarsys.capella.common.data.core.gen", "org.polarsys.capella.common.data");
		generateGenModel(genModels, "/org.polarsys.capella.common.data.def/model/Behavior.ecore",
				"org.polarsys.capella.common.data.behavior.gen", "org.polarsys.capella.common.data");
		generateGenModel(genModels, "/org.polarsys.capella.common.data.def/model/Activity.ecore",
				"org.polarsys.capella.common.data.activity.gen", "org.polarsys.capella.common.data");
		generateGenModel(genModels, "/org.polarsys.capella.core.data.def/model/CapellaModeller.ecore",
				"org.polarsys.capella.core.data.gen", "org.polarsys.capella.core.data");
		generateGenModel(genModels, "/org.polarsys.capella.common.re.gen/model/re.ecore",
				"org.polarsys.capella.common.re.gen", "org.polarsys.capella.common");
		generateGenModel(genModels, "/org.polarsys.capella.common.libraries.gen/model/libraries.ecore",
				"org.polarsys.capella.common.libraries.gen", "org.polarsys.capella.common");

	}

	private void generateGenModel(final List<GenModel> genModels_p, String ecorePath_p, String pluginId_p,
			String basePackage_p) {
		EcoreGenModelGenerator genModelGenerator = new EcoreGenModelGenerator() {
			@Override
			protected void addExternalGenModels(List<GenModel> genModelsP) {
				super.addExternalGenModels(genModelsP);
				genModelsP.addAll(genModels_p);
			}

			@Override
      public void setGenModelParameters(GenModel genModel_p) {
				super.setGenModelParameters(genModel_p);
				int year = Calendar.getInstance().get(Calendar.YEAR);
				genModel_p.setCopyrightText(" Copyright (c) 2006, " + year
						+ " THALES GLOBAL SERVICES.\n This program and the accompanying materials are made available under the\n terms of the Eclipse Public License 2.0 which is available at\n http://www.eclipse.org/legal/epl-2.0\n\n SPDX-License-Identifier: EPL-2.0\n\n Contributors:\n    Thales - initial API and implementation");

				// Add a customization for set melody extension (to be discussed)
				if ("CapellaModeller".equals(genModel_p.getModelName())) {
					for (GenPackage pack : genModel_p.getGenPackages()) {
						String packPrefix = pack.getPrefix();

						if ("Capellamodeller".equals(packPrefix)) {
							pack.setFileExtensions(CapellaResourceHelper.CAPELLA_MODEL_FILE_EXTENSION);
						}

						// Add a customization to set "Create Child" attribute of the feature
						
						else if ("Oa".equals(packPrefix)) {
							for (GenClass genClass : pack.getGenClasses()) {
								String className = genClass.getEcoreClass().getName();

								if ("Entity".equals(className) || "EntityPkg".equals(className)) {
									for (GenFeature genFeature : genClass.getGenFeatures()) {
										if ("ownedEntities".equals(genFeature.getEcoreFeature().getName())) {
											genFeature.setCreateChild(false);
										}
									}
								}
							}
						}

						else if ("Ctx".equals(packPrefix)) {
							for (GenClass genClass : pack.getGenClasses()) {
								String className = genClass.getEcoreClass().getName();

								if ("SystemComponentPkg".equals(className)) {
									for (GenFeature genFeature : genClass.getGenFeatures()) {
										if ("ownedSystemComponents".equals(genFeature.getEcoreFeature().getName())) {
											genFeature.setCreateChild(false);
										}
									}
								} else if ("SystemComponent".equals(className)) {
									for (GenFeature genFeature : genClass.getGenFeatures()) {
										if ("ownedSystemComponents".equals(genFeature.getEcoreFeature().getName())) {
											genFeature.setCreateChild(false);
										} else if ("ownedSystemComponentPkgs".equals(genFeature.getEcoreFeature().getName())) {
											genFeature.setCreateChild(false);
										}
									}
								}
							}
						}

						else if ("La".equals(packPrefix)) {
							for (GenClass genClass : pack.getGenClasses()) {
								String className = genClass.getEcoreClass().getName();

								if ("LogicalComponent".equals(className) || "LogicalComponentPkg".equals(className)) {
									for (GenFeature genFeature : genClass.getGenFeatures()) {
										if ("ownedLogicalComponents".equals(genFeature.getEcoreFeature().getName())) {
											genFeature.setCreateChild(false);
										}
									}
								}
							}
						}

						else if ("Pa".equals(packPrefix)) {
							for (GenClass genClass : pack.getGenClasses()) {
								String className = genClass.getEcoreClass().getName();

								if ("PhysicalComponent".equals(className) || "PhysicalComponentPkg".equals(className)) {
									for (GenFeature genFeature : genClass.getGenFeatures()) {
										if ("ownedPhysicalComponents".equals(genFeature.getEcoreFeature().getName())) {
											genFeature.setCreateChild(false);
										}
									}
								}

							}
						}
						
						else if ("Epbs".equals(packPrefix)) {
							for (GenClass genClass : pack.getGenClasses()) {
								String className = genClass.getEcoreClass().getName();

								if ("ConfigurationItemPkg".equals(className)) {
									for (GenFeature genFeature : genClass.getGenFeatures()) {
										if ("ownedConfigurationItems".equals(genFeature.getEcoreFeature().getName())) {
											genFeature.setCreateChild(false);
										} 
										else if ("ownedConfigurationItemPkgs"
												.equals(genFeature.getEcoreFeature().getName())) {
											genFeature.setCreateChild(false);
										}
									}
								}

							}
						}

					}
				}

				// Add a customization to set "Create Child" attribute of the feature
				// ownedMigratedElements to false
				if ("ModellingCore".equals(genModel_p.getModelName())) {
					for (GenPackage pack : genModel_p.getGenPackages()) {
						if ("Modellingcore".equals(pack.getPrefix())) {
							for (GenClass genClass : pack.getGenClasses()) {
								if ("ModelElement".equals(genClass.getEcoreClass().getName())) {
									for (GenFeature genFeature : genClass.getGenFeatures()) {
										if ("ownedMigratedElements".equals(genFeature.getEcoreFeature().getName())) {
											genFeature.setCreateChild(false);
										}
									}
								}
							}
						}
					}
				}

			}
		};

		genModelGenerator.setInputPath(new Path(ecorePath_p));
		genModelGenerator.setPluginId(pluginId_p);
		genModelGenerator.setBasePackagePrefix(basePackage_p);
		genModelGenerator.setModelDirectory("generated");

		genModelGenerator.setJdkComplianceLevel(GenJDKLevel.JDK60_LITERAL);
		genModelGenerator.setResourceType(GenResourceKind.XMI_LITERAL);
		genModelGenerator.setRootExtendsClass("org.eclipse.emf.ecore.impl.EObjectImpl");
		genModelGenerator.setRootExtendsInterface("org.eclipse.emf.ecore.EObject");
		genModelGenerator.setOperationReflection(false);

		GenModel model = genModelGenerator.execute(new BasicMonitor());
		adaptGenPackagesToEcoreAnnotations(model);

		genModels_p.add(model);
	}

	private void adaptGenPackagesToEcoreAnnotations(GenModel model) {
		for (GenPackage genPackage : model.getAllGenPackagesWithClassifiers()) {
			String annotation = null;
			EPackage ecorePackage = genPackage.getEcorePackage();
			annotation = EcoreUtil.getAnnotation(ecorePackage, "http://www.polarsys.org/kitalpha/emde/1.0.0/extension",
					"childCreationExtenders");
			if (annotation != null) {
				genPackage.setChildCreationExtenders(Boolean.parseBoolean(annotation));
			}

			annotation = EcoreUtil.getAnnotation(ecorePackage, "http://www.polarsys.org/kitalpha/emde/1.0.0/extension",
					"extensibleProviderFactory");
			if (annotation != null) {
				genPackage.setExtensibleProviderFactory(Boolean.parseBoolean(annotation));
			}

			try {
				genPackage.eResource().save(Collections.EMPTY_MAP);
				genPackage.getEcorePackage().eResource().save(Collections.EMPTY_MAP);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void postExecute(ITaskProductionContext productionContext, IProgressMonitor monitor)
			throws InvocationException {
	}

	public void preExecute(ITaskProductionContext productionContext, IProgressMonitor monitor)
			throws InvocationException {
	}
}
