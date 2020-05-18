//Generated with EGF 1.5.0.v20170706-0846
package org.polarsys.capella.core.editor;

import java.util.*;
import org.polarsys.kitalpha.emde.egf.helper.ExtensionHelper;
import org.eclipse.egf.model.pattern.*;
import org.eclipse.egf.pattern.execution.*;
import org.eclipse.egf.pattern.query.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class PluginXML extends org.polarsys.kitalpha.emde.egf.editor.PluginXML {
	protected static String nl;

	public static synchronized PluginXML create(String lineSeparator) {
		nl = lineSeparator;
		PluginXML result = new PluginXML();
		nl = null;
		return result;
	}

	public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
	protected final String TEXT_1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + NL + "<?eclipse version=\"3.0\"?>"
			+ NL;
	protected final String TEXT_2 = NL;
	protected final String TEXT_3 = NL;
	protected final String TEXT_4 = NL + "<plugin>";
	protected final String TEXT_5 = NL + "<plugin" + NL + "      name=\"%pluginName\"" + NL + "      id=\"";
	protected final String TEXT_6 = "\"" + NL + "      version=\"1.0.0.qualifier\"" + NL
			+ "      provider-name=\"%providerName\"" + NL + "      class=\"";
	protected final String TEXT_7 = "$Implementation\">" + NL + "" + NL + "   <requires>";
	protected final String TEXT_8 = NL + "      <import plugin=\"";
	protected final String TEXT_9 = "\"";
	protected final String TEXT_10 = " export=\"true\"";
	protected final String TEXT_11 = "/>";
	protected final String TEXT_12 = NL + "   </requires>" + NL + "" + NL + "   <runtime>";
	protected final String TEXT_13 = NL + "      <library name=\"";
	protected final String TEXT_14 = ".jar\">";
	protected final String TEXT_15 = NL + "      <library name=\".\">";
	protected final String TEXT_16 = NL + "         <export name=\"*\"/>" + NL + "      </library>" + NL
			+ "   </runtime>";
	protected final String TEXT_17 = NL + NL + "   <extension point=\"org.eclipse.emf.ecore.generated_package\">";
	protected final String TEXT_18 = NL + "      <!-- @generated ";
	protected final String TEXT_19 = " -->";
	protected final String TEXT_20 = NL + "      <package" + NL + "            uri=\"";
	protected final String TEXT_21 = "\"";
	protected final String TEXT_22 = NL + "            class=\"";
	protected final String TEXT_23 = "\"" + NL + "            genModel=\"";
	protected final String TEXT_24 = "\"/>";
	protected final String TEXT_25 = NL + "            class=\"";
	protected final String TEXT_26 = "\"/>";
	protected final String TEXT_27 = NL + "   </extension>";
	protected final String TEXT_28 = NL + NL + "   <extension point=\"org.eclipse.emf.ecore.content_parser\">";
	protected final String TEXT_29 = NL + "      <!-- @generated ";
	protected final String TEXT_30 = " -->";
	protected final String TEXT_31 = NL + "      <parser" + NL + "            contentTypeIdentifier=\"";
	protected final String TEXT_32 = "\"" + NL + "            class=\"";
	protected final String TEXT_33 = "\"/>" + NL + "   </extension>" + NL + "" + NL
			+ "   <extension point=\"org.eclipse.core.contenttype.contentTypes\">";
	protected final String TEXT_34 = NL + "      <!-- @generated ";
	protected final String TEXT_35 = " -->";
	protected final String TEXT_36 = NL + "      <content-type" + NL + "            base-type=\"";
	protected final String TEXT_37 = "\"" + NL + "            file-extensions=\"";
	protected final String TEXT_38 = "\"" + NL + "            id=\"";
	protected final String TEXT_39 = "\"" + NL + "            name=\"%_UI_";
	protected final String TEXT_40 = "_content_type\"" + NL + "            priority=\"normal\">" + NL
			+ "         <describer class=\"org.eclipse.emf.ecore.xmi.impl.RootXMLContentHandlerImpl$Describer\">";
	protected final String TEXT_41 = NL + "            <parameter name=\"namespace\" value=\"";
	protected final String TEXT_42 = "\"/>";
	protected final String TEXT_43 = NL + "            <parameter name=\"kind\" value=\"xmi\"/>";
	protected final String TEXT_44 = NL + "         </describer>" + NL + "      </content-type>" + NL
			+ "   </extension>";
	protected final String TEXT_45 = NL + NL + "   <extension point=\"org.eclipse.emf.ecore.extension_parser\">";
	protected final String TEXT_46 = NL + "      <!-- @generated ";
	protected final String TEXT_47 = " -->";
	protected final String TEXT_48 = NL + "      <parser" + NL + "            type=\"";
	protected final String TEXT_49 = "\"" + NL + "            class=\"";
	protected final String TEXT_50 = "\"/>";
	protected final String TEXT_51 = NL + "   </extension>";
	protected final String TEXT_52 = NL + NL
			+ "   <extension point=\"org.eclipse.emf.edit.itemProviderAdapterFactories\">";
	protected final String TEXT_53 = NL + "      <!-- @generated ";
	protected final String TEXT_54 = " -->";
	protected final String TEXT_55 = NL + "      <factory" + NL + "            uri=\"";
	protected final String TEXT_56 = "\"" + NL + "            class=\"";
	protected final String TEXT_57 = "\"" + NL + "            supportedTypes=";
	protected final String TEXT_58 = NL + "              ";
	protected final String TEXT_59 = "\"/>";
	protected final String TEXT_60 = NL + "   </extension>";
	protected final String TEXT_61 = NL + NL + "   <extension point=\"org.eclipse.emf.edit.childCreationExtenders\">";
	protected final String TEXT_62 = NL + "      <!-- @generated ";
	protected final String TEXT_63 = " -->";
	protected final String TEXT_64 = NL + "      <extender" + NL + "            uri=\"";
	protected final String TEXT_65 = "\"" + NL + "            class=\"";
	protected final String TEXT_66 = "$";
	protected final String TEXT_67 = "\"/>";
	protected final String TEXT_68 = NL + "   </extension>";
	protected final String TEXT_69 = NL + NL + "   <extension" + NL
			+ "         point=\"org.eclipse.core.runtime.applications\"" + NL + "         id=\"";
	protected final String TEXT_70 = "Application\">";
	protected final String TEXT_71 = NL + "      <!-- @generated ";
	protected final String TEXT_72 = " -->";
	protected final String TEXT_73 = NL + "      <application>" + NL + "         <run class=\"";
	protected final String TEXT_74 = "$Application\"/>" + NL + "      </application>" + NL + "   </extension>" + NL + ""
			+ NL + "   <extension point=\"org.eclipse.ui.perspectives\">";
	protected final String TEXT_75 = NL + "      <!-- @generated ";
	protected final String TEXT_76 = " -->";
	protected final String TEXT_77 = NL + "      <perspective" + NL + "            name=\"%_UI_Perspective_label\"" + NL
			+ "            class=\"";
	protected final String TEXT_78 = "$Perspective\"" + NL + "            id=\"";
	protected final String TEXT_79 = "Perspective\">" + NL + "      </perspective>" + NL + "   </extension>" + NL + ""
			+ NL + "   <extension point=\"org.eclipse.ui.commands\">";
	protected final String TEXT_80 = NL + "      <!-- @generated ";
	protected final String TEXT_81 = " -->";
	protected final String TEXT_82 = NL + "      <command" + NL + "            name=\"%_UI_Menu_OpenURI_label\"" + NL
			+ "            description=\"%_UI_Menu_OpenURI_description\"" + NL
			+ "            categoryId=\"org.eclipse.ui.category.file\"" + NL + "            id=\"";
	protected final String TEXT_83 = "OpenURICommand\"/>";
	protected final String TEXT_84 = NL + "      <command" + NL + "            name=\"%_UI_Menu_Open_label\"" + NL
			+ "            description=\"%_UI_Menu_Open_description\"" + NL
			+ "            categoryId=\"org.eclipse.ui.category.file\"" + NL + "            id=\"";
	protected final String TEXT_85 = "OpenCommand\"/>";
	protected final String TEXT_86 = NL + "   </extension>" + NL;
	protected final String TEXT_87 = NL + "   <extension point=\"org.eclipse.ui.bindings\">";
	protected final String TEXT_88 = NL + "      <!-- @generated ";
	protected final String TEXT_89 = " -->";
	protected final String TEXT_90 = NL + "      <key" + NL + "            commandId=\"";
	protected final String TEXT_91 = "OpenURICommand\"" + NL + "            sequence=\"M1+U\"" + NL
			+ "            schemeId=\"org.eclipse.ui.defaultAcceleratorConfiguration\"/>" + NL + "      <key" + NL
			+ "            commandId=\"";
	protected final String TEXT_92 = "OpenCommand\"" + NL + "            sequence=\"M1+O\"" + NL
			+ "            schemeId=\"org.eclipse.ui.defaultAcceleratorConfiguration\"/>" + NL + "   </extension>";
	protected final String TEXT_93 = NL + NL + "   <extension point=\"org.eclipse.ui.actionSets\">";
	protected final String TEXT_94 = NL + "      <!-- @generated ";
	protected final String TEXT_95 = " -->";
	protected final String TEXT_96 = NL + "      <actionSet" + NL + "            label=\"%_UI_";
	protected final String TEXT_97 = "_ActionSet_label\"" + NL + "            visible=\"true\"" + NL
			+ "            id=\"";
	protected final String TEXT_98 = "ActionSet\">" + NL + "         <action" + NL
			+ "               label=\"%_UI_Menu_About_label\"" + NL + "               class=\"";
	protected final String TEXT_99 = "$AboutAction\"" + NL + "               menubarPath=\"help/additions\"" + NL
			+ "               id=\"";
	protected final String TEXT_100 = "AboutAction\"/>" + NL + "         <action" + NL
			+ "               label=\"%_UI_Menu_OpenURI_label\"" + NL + "               definitionId=\"";
	protected final String TEXT_101 = "OpenURICommand\"" + NL + "               class=\"";
	protected final String TEXT_102 = "$OpenURIAction\"" + NL + "               menubarPath=\"file/additions\"" + NL
			+ "               id=\"";
	protected final String TEXT_103 = "OpenURIAction\"/>";
	protected final String TEXT_104 = NL + "         <action" + NL + "               label=\"%_UI_Menu_Open_label\""
			+ NL + "               definitionId=\"";
	protected final String TEXT_105 = "OpenCommand\"" + NL + "               class=\"";
	protected final String TEXT_106 = "$OpenAction\"" + NL + "               menubarPath=\"file/additions\"" + NL
			+ "               id=\"";
	protected final String TEXT_107 = "OpenAction\"/>";
	protected final String TEXT_108 = NL + "      </actionSet>" + NL + "   </extension>";
	protected final String TEXT_109 = NL + NL + "   <extension point=\"org.eclipse.ui.actionSets\">";
	protected final String TEXT_110 = NL + "      <!-- @generated ";
	protected final String TEXT_111 = " -->";
	protected final String TEXT_112 = NL + "      <actionSet" + NL + "            label=\"%_UI_";
	protected final String TEXT_113 = "_ActionSet_label\"" + NL + "            visible=\"true\"" + NL
			+ "            id=\"";
	protected final String TEXT_114 = "ActionSet\">" + NL + "         <action" + NL + "               label=\"%_UI_";
	protected final String TEXT_115 = "_label\"" + NL + "               class=\"";
	protected final String TEXT_116 = "$NewAction\"" + NL + "               menubarPath=\"file/new/additions\"" + NL
			+ "               id=\"";
	protected final String TEXT_117 = "NewAction\"/>" + NL + "      </actionSet>" + NL + "   </extension>";
	protected final String TEXT_118 = NL + NL + "   <extension point=\"org.eclipse.ui.newWizards\">";
	protected final String TEXT_119 = NL + "      <!-- @generated ";
	protected final String TEXT_120 = " -->";
	protected final String TEXT_121 = NL + "      <wizard" + NL + "            id=\"";
	protected final String TEXT_122 = "ID\"" + NL + "            name=\"%_UI_";
	protected final String TEXT_123 = "_label\"" + NL + "            class=\"";
	protected final String TEXT_124 = "\"" + NL
			+ "            category=\"org.polarsys.kitalpha.wizard.category/org.polarsys.kitalpha.mdeCoreTechnology.wizard.category\""
			+ NL + "            icon=\"icons/full/obj16/";
	protected final String TEXT_125 = "ModelFile.gif\">" + NL + "         <description>%_UI_";
	protected final String TEXT_126 = "_description</description>" + NL
			+ "         <selection class=\"org.eclipse.core.resources.IResource\"/>" + NL + "      </wizard>" + NL
			+ "   </extension>";
	protected final String TEXT_127 = NL + NL + "   <extension point=\"org.eclipse.ui.editors\">";
	protected final String TEXT_128 = NL + "      <!-- @generated ";
	protected final String TEXT_129 = " -->";
	protected final String TEXT_130 = NL + "      <editor" + NL + "            id=\"";
	protected final String TEXT_131 = "ID\"" + NL + "            name=\"%_UI_";
	protected final String TEXT_132 = "_label\"" + NL + "            icon=\"icons/full/obj16/";
	protected final String TEXT_133 = "ModelFile.gif\"";
	protected final String TEXT_134 = NL + "            extensions=\"";
	protected final String TEXT_135 = "\"";
	protected final String TEXT_136 = NL + "            class=\"";
	protected final String TEXT_137 = "\"" + NL + "            contributorClass=\"";
	protected final String TEXT_138 = "\">";
	protected final String TEXT_139 = NL + "         <contentTypeBinding contentTypeId=\"";
	protected final String TEXT_140 = "\"/>";
	protected final String TEXT_141 = NL + "      </editor>" + NL + "   </extension>";
	protected final String TEXT_142 = NL + NL + "</plugin>";
	protected final String TEXT_143 = NL;
	protected final String TEXT_144 = NL;

	public PluginXML() {
		//Here is the constructor
		StringBuffer stringBuffer = new StringBuffer();

		// add initialisation of the pattern variables (declaration has been already done).

	}

	public String generate(Object argument) throws Exception {
		final StringBuffer stringBuffer = new StringBuffer();

		InternalPatternContext ctx = (InternalPatternContext) argument;
		Map<String, String> queryCtx = null;
		IQuery.ParameterDescription paramDesc = null;
		Node.Container currentNode = ctx.getNode();

		List<Object> parameterList = null;
		//this pattern can only be called by another (i.e. it's not an entry point in execution)

		for (Object parameterParameter : parameterList) {

			this.parameter = (org.eclipse.emf.codegen.ecore.genmodel.GenModel) parameterParameter;

			if (preCondition(ctx)) {
				ctx.setNode(new Node.Container(currentNode, getClass()));
				orchestration(ctx);
			}

		}
		ctx.setNode(currentNode);
		if (ctx.useReporter()) {
			ctx.getReporter().executionFinished(OutputManager.computeExecutionOutput(ctx), ctx);
		}

		stringBuffer.append(TEXT_143);
		stringBuffer.append(TEXT_144);
		return stringBuffer.toString();
	}

	public String orchestration(PatternContext ctx) throws Exception {
		InternalPatternContext ictx = (InternalPatternContext) ctx;

		super.orchestration(new SuperOrchestrationContext(ictx));

		if (ictx.useReporter()) {
			Map<String, Object> parameterValues = new HashMap<String, Object>();
			parameterValues.put("parameter", this.parameter);
			String outputWithCallBack = OutputManager.computeLoopOutput(ictx);
			String loop = OutputManager.computeLoopOutputWithoutCallback(ictx);
			ictx.getReporter().loopFinished(loop, outputWithCallBack, ictx, parameterValues);
		}
		return null;
	}

	public Map<String, Object> getParameters() {
		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("parameter", this.parameter);
		return parameters;
	}

	protected void method_doGenerate(final StringBuffer stringBuffer, final PatternContext ctx) throws Exception {

		/**
		 * Copyright (c) 2002-2010 IBM Corporation and others.
		 * All rights reserved.   This program and the accompanying materials
		 * terms of the Eclipse Public License 2.0 which is available at
		 * http://www.eclipse.org/legal/epl-2.0
		 * 
		 * SPDX-License-Identifier: EPL-2.0
		 * 
		 * Contributors: 
		 *   IBM - Initial API and implementation
		 */

		GenModel genModel = (GenModel) argument;
		String key = genModel.getPluginKey();
		boolean hasKey = key != null && !key.equals("");
		stringBuffer.append(TEXT_1);
		stringBuffer.append(TEXT_2);
		{
			//<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern.base/egf/EMF_Pattern_Base.fcore#LogicalName=org.eclipse.egf.emf.pattern.base.HeaderXml" args="parameter:argument"%>

			InternalPatternContext ictx = (InternalPatternContext) ctx;
			new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
			stringBuffer.setLength(0);

			final Map<String, Object> callParameters = new HashMap<String, Object>();
			callParameters.put("argument", parameter);
			CallHelper.executeWithParameterInjection(
					"platform:/plugin/org.eclipse.egf.emf.pattern.base/egf/EMF_Pattern_Base.fcore#__h1VkCwtEd-jc5T-XaRJlg",
					new ExecutionContext((InternalPatternContext) ctx), callParameters);
			stringBuffer.setLength(0);
		}

		stringBuffer.append(TEXT_3);
		if (genModel.isBundleManifest()) {
			stringBuffer.append(TEXT_4);
		} else {
			stringBuffer.append(TEXT_5);
			stringBuffer.append(genModel.getEditorPluginID());
			stringBuffer.append(TEXT_6);
			stringBuffer.append(genModel.getQualifiedEditorPluginClassName());
			stringBuffer.append(TEXT_7);
			for (String pluginID : genModel.getEditorRequiredPlugins()) {
				stringBuffer.append(TEXT_8);
				stringBuffer.append(pluginID);
				stringBuffer.append(TEXT_9);
				if (!pluginID.startsWith("org.eclipse.core.runtime")) {
					stringBuffer.append(TEXT_10);
				}
				stringBuffer.append(TEXT_11);
			}
			stringBuffer.append(TEXT_12);
			if (genModel.isRuntimeJar()) {
				stringBuffer.append(TEXT_13);
				stringBuffer.append(genModel.getEditorPluginID());
				stringBuffer.append(TEXT_14);
			} else {
				stringBuffer.append(TEXT_15);
			}
			stringBuffer.append(TEXT_16);
		}
		if (genModel.sameModelEditorProject()) {
			for (GenPackage genPackage : genModel.getAllGenPackagesWithClassifiers()) {
				stringBuffer.append(TEXT_17);
				if (hasKey) {
					stringBuffer.append(TEXT_18);
					stringBuffer.append(key);
					stringBuffer.append(TEXT_19);
				}
				stringBuffer.append(TEXT_20);
				stringBuffer.append(genPackage.getNSURI());
				stringBuffer.append(TEXT_21);
				if (genModel.hasLocalGenModel()) {
					stringBuffer.append(TEXT_22);
					stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
					stringBuffer.append(TEXT_23);
					stringBuffer.append(genModel.getRelativeGenModelLocation());
					stringBuffer.append(TEXT_24);
				} else {
					stringBuffer.append(TEXT_25);
					stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
					stringBuffer.append(TEXT_26);
				}
				stringBuffer.append(TEXT_27);
				if (genPackage.isContentType()) {
					stringBuffer.append(TEXT_28);
					if (hasKey) {
						stringBuffer.append(TEXT_29);
						stringBuffer.append(key);
						stringBuffer.append(TEXT_30);
					}
					stringBuffer.append(TEXT_31);
					stringBuffer.append(genPackage.getContentTypeIdentifier());
					stringBuffer.append(TEXT_32);
					stringBuffer.append(genPackage.getQualifiedEffectiveResourceFactoryClassName());
					stringBuffer.append(TEXT_33);
					if (hasKey) {
						stringBuffer.append(TEXT_34);
						stringBuffer.append(key);
						stringBuffer.append(TEXT_35);
					}
					stringBuffer.append(TEXT_36);
					stringBuffer.append(
							genPackage.isXMIResource() ? "org.eclipse.emf.ecore.xmi" : "org.eclipse.core.runtime.xml");
					stringBuffer.append(TEXT_37);
					stringBuffer.append(genPackage.getFileExtensions());
					stringBuffer.append(TEXT_38);
					stringBuffer.append(genPackage.getContentTypeIdentifier());
					stringBuffer.append(TEXT_39);
					stringBuffer.append(genPackage.getPrefix());
					stringBuffer.append(TEXT_40);
					if (genPackage.hasTargetNamespace()) {
						stringBuffer.append(TEXT_41);
						stringBuffer.append(genPackage.getNSURI());
						stringBuffer.append(TEXT_42);
					}
					if (genPackage.isXMIResource()) {
						stringBuffer.append(TEXT_43);
					}
					stringBuffer.append(TEXT_44);
				} else if (genPackage.getResource() != GenResourceKind.NONE_LITERAL) {
					stringBuffer.append(TEXT_45);
					if (hasKey) {
						stringBuffer.append(TEXT_46);
						stringBuffer.append(key);
						stringBuffer.append(TEXT_47);
					}
					for (String fileExtension : genPackage.getFileExtensionList()) {
						stringBuffer.append(TEXT_48);
						stringBuffer.append(fileExtension);
						stringBuffer.append(TEXT_49);
						stringBuffer.append(genPackage.getQualifiedResourceFactoryClassName());
						stringBuffer.append(TEXT_50);
					}
					stringBuffer.append(TEXT_51);
				}
			}
		}
		if (genModel.sameEditEditorProject()) {
			for (GenPackage genPackage : genModel.getAllGenPackagesWithClassifiers()) {
				if (!genPackage.getGenClasses().isEmpty()) {
					stringBuffer.append(TEXT_52);
					if (hasKey) {
						stringBuffer.append(TEXT_53);
						stringBuffer.append(key);
						stringBuffer.append(TEXT_54);
					}
					stringBuffer.append(TEXT_55);
					stringBuffer.append(genPackage.getNSURI());
					stringBuffer.append(TEXT_56);
					stringBuffer.append(genPackage.getQualifiedItemProviderAdapterFactoryClassName());
					stringBuffer.append(TEXT_57);
					for (ListIterator<?> j = genPackage.getProviderSupportedTypes().listIterator(); j.hasNext();) {
						stringBuffer.append(TEXT_58);
						stringBuffer.append(j.hasPrevious() ? " " : "\"");
						stringBuffer.append(j.next());
						if (!j.hasNext()) {
							stringBuffer.append(TEXT_59);
						}
					}
					stringBuffer.append(TEXT_60);
					if (genPackage.isChildCreationExtenders()) {
						Map<GenPackage, Map<GenClass, List<GenClass.ChildCreationData>>> extendedChildCreationData = ExtensionHelper
								.getExtendedChildCreationData(genPackage);
						if (!extendedChildCreationData.isEmpty()) {
							stringBuffer.append(TEXT_61);
							for (Map.Entry<GenPackage, Map<GenClass, List<GenClass.ChildCreationData>>> entry : extendedChildCreationData
									.entrySet()) {
								if (hasKey) {
									stringBuffer.append(TEXT_62);
									stringBuffer.append(key);
									stringBuffer.append(TEXT_63);
								}
								stringBuffer.append(TEXT_64);
								stringBuffer.append(entry.getKey().getNSURI());
								stringBuffer.append(TEXT_65);
								stringBuffer.append(genPackage.getQualifiedItemProviderAdapterFactoryClassName());
								stringBuffer.append(TEXT_66);
								stringBuffer.append(genPackage.getChildCreationExtenderName(entry.getKey()));
								stringBuffer.append(TEXT_67);
							}
							stringBuffer.append(TEXT_68);
						}
					}
				}
			}
		}
		if (genModel.isRichClientPlatform()) {
			stringBuffer.append(TEXT_69);
			stringBuffer.append(genModel.getEditorAdvisorClassName());
			stringBuffer.append(TEXT_70);
			if (hasKey) {
				stringBuffer.append(TEXT_71);
				stringBuffer.append(key);
				stringBuffer.append(TEXT_72);
			}
			stringBuffer.append(TEXT_73);
			stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
			stringBuffer.append(TEXT_74);
			if (hasKey) {
				stringBuffer.append(TEXT_75);
				stringBuffer.append(key);
				stringBuffer.append(TEXT_76);
			}
			stringBuffer.append(TEXT_77);
			stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
			stringBuffer.append(TEXT_78);
			stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
			stringBuffer.append(TEXT_79);
			if (hasKey) {
				stringBuffer.append(TEXT_80);
				stringBuffer.append(key);
				stringBuffer.append(TEXT_81);
			}
			stringBuffer.append(TEXT_82);
			stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
			stringBuffer.append(TEXT_83);
			if (!genModel.isRichAjaxPlatform()) {
				stringBuffer.append(TEXT_84);
				stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
				stringBuffer.append(TEXT_85);
			}
			stringBuffer.append(TEXT_86);
			if (!genModel.isRichAjaxPlatform()) {
				stringBuffer.append(TEXT_87);
				if (hasKey) {
					stringBuffer.append(TEXT_88);
					stringBuffer.append(key);
					stringBuffer.append(TEXT_89);
				}
				stringBuffer.append(TEXT_90);
				stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
				stringBuffer.append(TEXT_91);
				stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
				stringBuffer.append(TEXT_92);
			}
			stringBuffer.append(TEXT_93);
			if (hasKey) {
				stringBuffer.append(TEXT_94);
				stringBuffer.append(key);
				stringBuffer.append(TEXT_95);
			}
			stringBuffer.append(TEXT_96);
			stringBuffer.append(genModel.getEditorAdvisorClassName());
			stringBuffer.append(TEXT_97);
			stringBuffer.append(genModel.getEditorAdvisorClassName());
			stringBuffer.append(TEXT_98);
			stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
			stringBuffer.append(TEXT_99);
			stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
			stringBuffer.append(TEXT_100);
			stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
			stringBuffer.append(TEXT_101);
			stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
			stringBuffer.append(TEXT_102);
			stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
			stringBuffer.append(TEXT_103);
			if (!genModel.isRichAjaxPlatform()) {
				stringBuffer.append(TEXT_104);
				stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
				stringBuffer.append(TEXT_105);
				stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
				stringBuffer.append(TEXT_106);
				stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
				stringBuffer.append(TEXT_107);
			}
			stringBuffer.append(TEXT_108);
		}
		for (GenPackage genPackage : genModel.getAllGenPackagesWithClassifiers()) {
			if (genPackage.hasConcreteClasses()) {
				if (genPackage.isGenerateModelWizard()) {
					if (genModel.isRichClientPlatform()) {
						stringBuffer.append(TEXT_109);
						if (hasKey) {
							stringBuffer.append(TEXT_110);
							stringBuffer.append(key);
							stringBuffer.append(TEXT_111);
						}
						stringBuffer.append(TEXT_112);
						stringBuffer.append(genPackage.getModelWizardClassName());
						stringBuffer.append(TEXT_113);
						stringBuffer.append(genPackage.getQualifiedActionBarContributorClassName());
						stringBuffer.append(TEXT_114);
						stringBuffer.append(genPackage.getModelWizardClassName());
						stringBuffer.append(TEXT_115);
						stringBuffer.append(genPackage.getQualifiedActionBarContributorClassName());
						stringBuffer.append(TEXT_116);
						stringBuffer.append(genPackage.getQualifiedActionBarContributorClassName());
						stringBuffer.append(TEXT_117);
					} else {
						stringBuffer.append(TEXT_118);
						if (hasKey) {
							stringBuffer.append(TEXT_119);
							stringBuffer.append(key);
							stringBuffer.append(TEXT_120);
						}
						stringBuffer.append(TEXT_121);
						stringBuffer.append(genPackage.getQualifiedModelWizardClassName());
						stringBuffer.append(TEXT_122);
						stringBuffer.append(genPackage.getModelWizardClassName());
						stringBuffer.append(TEXT_123);
						stringBuffer.append(genPackage.getQualifiedModelWizardClassName());
						stringBuffer.append(TEXT_124);
						stringBuffer.append(genPackage.getPrefix());
						stringBuffer.append(TEXT_125);
						stringBuffer.append(genPackage.getModelWizardClassName());
						stringBuffer.append(TEXT_126);
					}
				}
				stringBuffer.append(TEXT_127);
				if (hasKey) {
					stringBuffer.append(TEXT_128);
					stringBuffer.append(key);
					stringBuffer.append(TEXT_129);
				}
				stringBuffer.append(TEXT_130);
				stringBuffer.append(genPackage.getQualifiedEditorClassName());
				stringBuffer.append(TEXT_131);
				stringBuffer.append(genPackage.getEditorClassName());
				stringBuffer.append(TEXT_132);
				stringBuffer.append(genPackage.getPrefix());
				stringBuffer.append(TEXT_133);
				if (!genPackage.isContentType()) {
					stringBuffer.append(TEXT_134);
					stringBuffer.append(genPackage.getFileExtensions());
					stringBuffer.append(TEXT_135);
				}
				stringBuffer.append(TEXT_136);
				stringBuffer.append(genPackage.getQualifiedEditorClassName());
				stringBuffer.append(TEXT_137);
				stringBuffer.append(genPackage.getQualifiedActionBarContributorClassName());
				stringBuffer.append(TEXT_138);
				if (genPackage.isContentType()) {
					stringBuffer.append(TEXT_139);
					stringBuffer.append(genPackage.getQualifiedContentTypeIdentifier());
					stringBuffer.append(TEXT_140);
				}
				stringBuffer.append(TEXT_141);
			}
		}
		stringBuffer.append(TEXT_142);
		InternalPatternContext ictx = (InternalPatternContext) ctx;
		new Node.DataLeaf(ictx.getNode(), getClass(), "doGenerate", stringBuffer.toString());
	}
}