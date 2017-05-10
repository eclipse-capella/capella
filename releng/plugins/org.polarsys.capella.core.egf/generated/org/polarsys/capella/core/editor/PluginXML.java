//Generated with EGF 1.4.1.v20161010-1511
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
  protected final String TEXT_6 = "\"" + NL + "      version=\"1.1.0.qualifier\"" + NL
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
  protected final String TEXT_17 = NL + NL
      + "   <extension point=\"org.eclipse.emf.edit.itemProviderAdapterFactories\">" + NL + "      <factory" + NL
      + "            uri=\"";
  protected final String TEXT_18 = "\"" + NL + "            class=\"";
  protected final String TEXT_19 = "\"" + NL + "            supportedTypes=";
  protected final String TEXT_20 = NL + "              ";
  protected final String TEXT_21 = "\"/>";
  protected final String TEXT_22 = NL + "   </extension>";
  protected final String TEXT_23 = NL + NL + "   <extension point=\"org.eclipse.emf.edit.childCreationExtenders\">";
  protected final String TEXT_24 = NL + "      <extender" + NL + "            uri=\"";
  protected final String TEXT_25 = "\"" + NL + "            class=\"";
  protected final String TEXT_26 = "$";
  protected final String TEXT_27 = "\"/>";
  protected final String TEXT_28 = NL + "   </extension>";
  protected final String TEXT_29 = NL + NL + "   <extension point=\"org.eclipse.emf.ecore.generated_package\">" + NL
      + "      <package" + NL + "            uri=\"";
  protected final String TEXT_30 = "\"";
  protected final String TEXT_31 = NL + "            class=\"";
  protected final String TEXT_32 = "\"" + NL + "            genModel=\"";
  protected final String TEXT_33 = "\"/>";
  protected final String TEXT_34 = NL + "            class=\"";
  protected final String TEXT_35 = "\"/>";
  protected final String TEXT_36 = NL + "   </extension>";
  protected final String TEXT_37 = NL + NL + "   <extension point=\"org.eclipse.emf.ecore.content_parser\">" + NL
      + "      <parser" + NL + "            contentTypeIdentifier=\"";
  protected final String TEXT_38 = "\"" + NL + "            class=\"";
  protected final String TEXT_39 = "\"/>" + NL + "   </extension>" + NL + "" + NL
      + "   <extension point=\"org.eclipse.core.contenttype.contentTypes\">" + NL + "      <content-type" + NL
      + "            base-type=\"";
  protected final String TEXT_40 = "\"" + NL + "            file-extensions=\"";
  protected final String TEXT_41 = "\"" + NL + "            id=\"";
  protected final String TEXT_42 = "\"" + NL + "            name=\"%_UI_";
  protected final String TEXT_43 = "_content_type\"" + NL + "            priority=\"normal\">" + NL
      + "         <describer class=\"org.eclipse.emf.ecore.xmi.impl.RootXMLContentHandlerImpl$Describer\">";
  protected final String TEXT_44 = NL + "            <parameter name=\"namespace\" value=\"";
  protected final String TEXT_45 = "\"/>";
  protected final String TEXT_46 = NL + "            <parameter name=\"kind\" value=\"xmi\"/>";
  protected final String TEXT_47 = NL + "         </describer>" + NL + "      </content-type>" + NL + "   </extension>";
  protected final String TEXT_48 = NL + NL + "   <extension point=\"org.eclipse.emf.ecore.extension_parser\">" + NL
      + "      <parser" + NL + "            type=\"";
  protected final String TEXT_49 = "\"" + NL + "            class=\"";
  protected final String TEXT_50 = "\"/>" + NL + "   </extension>";
  protected final String TEXT_51 = NL + NL + "   <extension" + NL
      + "         point=\"org.eclipse.core.runtime.applications\"" + NL + "         id=\"";
  protected final String TEXT_52 = "Application\">" + NL + "      <application>" + NL + "         <run class=\"";
  protected final String TEXT_53 = "$Application\"/>" + NL + "      </application>" + NL + "   </extension>" + NL + ""
      + NL + "   <extension point=\"org.eclipse.ui.perspectives\">" + NL + "      <perspective" + NL
      + "            name=\"%_UI_Perspective_label\"" + NL + "            class=\"";
  protected final String TEXT_54 = "$Perspective\"" + NL + "            id=\"";
  protected final String TEXT_55 = "Perspective\">" + NL + "      </perspective>" + NL + "   </extension>" + NL + ""
      + NL + "   <extension point=\"org.eclipse.ui.commands\">" + NL + "      <command" + NL
      + "            name=\"%_UI_Menu_OpenURI_label\"" + NL
      + "            description=\"%_UI_Menu_OpenURI_description\"" + NL
      + "            categoryId=\"org.eclipse.ui.category.file\"" + NL + "            id=\"";
  protected final String TEXT_56 = "OpenURICommand\"/>";
  protected final String TEXT_57 = NL + "      <command" + NL + "            name=\"%_UI_Menu_Open_label\"" + NL
      + "            description=\"%_UI_Menu_Open_description\"" + NL
      + "            categoryId=\"org.eclipse.ui.category.file\"" + NL + "            id=\"";
  protected final String TEXT_58 = "OpenCommand\"/>";
  protected final String TEXT_59 = NL + "   </extension>" + NL;
  protected final String TEXT_60 = NL + "   <extension point=\"org.eclipse.ui.bindings\">" + NL + "      <key" + NL
      + "            commandId=\"";
  protected final String TEXT_61 = "OpenURICommand\"" + NL + "            sequence=\"M1+U\"" + NL
      + "            schemeId=\"org.eclipse.ui.defaultAcceleratorConfiguration\"/>" + NL + "      <key" + NL
      + "            commandId=\"";
  protected final String TEXT_62 = "OpenCommand\"" + NL + "            sequence=\"M1+O\"" + NL
      + "            schemeId=\"org.eclipse.ui.defaultAcceleratorConfiguration\"/>" + NL + "   </extension>";
  protected final String TEXT_63 = NL + NL + "   <extension point=\"org.eclipse.ui.actionSets\">" + NL
      + "      <actionSet" + NL + "            label=\"%_UI_";
  protected final String TEXT_64 = "_ActionSet_label\"" + NL + "            visible=\"true\"" + NL
      + "            id=\"";
  protected final String TEXT_65 = "ActionSet\">" + NL + "         <action" + NL
      + "               label=\"%_UI_Menu_About_label\"" + NL + "               class=\"";
  protected final String TEXT_66 = "$AboutAction\"" + NL + "               menubarPath=\"help/additions\"" + NL
      + "               id=\"";
  protected final String TEXT_67 = "AboutAction\"/>" + NL + "         <action" + NL
      + "               label=\"%_UI_Menu_OpenURI_label\"" + NL + "               definitionId=\"";
  protected final String TEXT_68 = "OpenURICommand\"" + NL + "               class=\"";
  protected final String TEXT_69 = "$OpenURIAction\"" + NL + "               menubarPath=\"file/additions\"" + NL
      + "               id=\"";
  protected final String TEXT_70 = "OpenURIAction\"/>";
  protected final String TEXT_71 = NL + "         <action" + NL + "               label=\"%_UI_Menu_Open_label\"" + NL
      + "               definitionId=\"";
  protected final String TEXT_72 = "OpenCommand\"" + NL + "               class=\"";
  protected final String TEXT_73 = "$OpenAction\"" + NL + "               menubarPath=\"file/additions\"" + NL
      + "               id=\"";
  protected final String TEXT_74 = "OpenAction\"/>";
  protected final String TEXT_75 = NL + "      </actionSet>" + NL + "   </extension>";
  protected final String TEXT_76 = NL + NL + "   <extension point=\"org.eclipse.ui.actionSets\">" + NL
      + "      <actionSet" + NL + "            label=\"%_UI_";
  protected final String TEXT_77 = "_ActionSet_label\"" + NL + "            visible=\"true\"" + NL
      + "            id=\"";
  protected final String TEXT_78 = "ActionSet\">" + NL + "         <action" + NL + "               label=\"%_UI_";
  protected final String TEXT_79 = "_label\"" + NL + "               class=\"";
  protected final String TEXT_80 = "$NewAction\"" + NL + "               menubarPath=\"file/new/additions\"" + NL
      + "               id=\"";
  protected final String TEXT_81 = "NewAction\"/>" + NL + "      </actionSet>" + NL + "   </extension>";
  protected final String TEXT_82 = NL + NL + "   <extension point=\"org.eclipse.ui.newWizards\">" + NL + "      <wizard"
      + NL + "            id=\"";
  protected final String TEXT_83 = "ID\"" + NL + "            name=\"%_UI_";
  protected final String TEXT_84 = "_label\"" + NL + "            class=\"";
  protected final String TEXT_85 = "\"" + NL
      + "            category=\"org.polarsys.kitalpha.wizard.category/org.polarsys.kitalpha.mdeCoreTechnology.wizard.category\""
      + NL + "            icon=\"icons/full/obj16/";
  protected final String TEXT_86 = "ModelFile.gif\">" + NL + "         <description>%_UI_";
  protected final String TEXT_87 = "_description</description>" + NL
      + "         <selection class=\"org.eclipse.core.resources.IResource\"/>" + NL + "      </wizard>" + NL
      + "   </extension>";
  protected final String TEXT_88 = NL + NL + "   <extension point=\"org.eclipse.ui.editors\">" + NL + "      <editor"
      + NL + "            id=\"";
  protected final String TEXT_89 = "ID\"" + NL + "            name=\"%_UI_";
  protected final String TEXT_90 = "_label\"" + NL + "            default=\"true\"" + NL
      + "            icon=\"icons/full/obj16/";
  protected final String TEXT_91 = "ModelFile.gif\"";
  protected final String TEXT_92 = NL + "            extensions=\"";
  protected final String TEXT_93 = "\"";
  protected final String TEXT_94 = NL + "            class=\"";
  protected final String TEXT_95 = "\"" + NL + "            contributorClass=\"";
  protected final String TEXT_96 = "\">";
  protected final String TEXT_97 = NL + "         <contentTypeBinding contentTypeId=\"";
  protected final String TEXT_98 = "\"/>";
  protected final String TEXT_99 = NL + "      </editor>" + NL + "   </extension>";
  protected final String TEXT_100 = NL + NL + "</plugin>";
  protected final String TEXT_101 = NL;
  protected final String TEXT_102 = NL;

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

    stringBuffer.append(TEXT_101);
    stringBuffer.append(TEXT_102);
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
     * <copyright>
     *
     * Copyright (c) 2002-2010 IBM Corporation and others.
     * All rights reserved.   This program and the accompanying materials
     * are made available under the terms of the Eclipse Public License v1.0
     * which accompanies this distribution, and is available at
     * http://www.eclipse.org/legal/epl-v10.html
     * 
     * Contributors: 
     *   IBM - Initial API and implementation
     *
     * </copyright>
     */

    GenModel genModel = (GenModel) argument;
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
    if (genModel.sameEditEditorProject()) {
      for (GenPackage genPackage : genModel.getAllGenPackagesWithClassifiers()) {
        if (!genPackage.getGenClasses().isEmpty()) {
          stringBuffer.append(TEXT_17);
          stringBuffer.append(genPackage.getNSURI());
          stringBuffer.append(TEXT_18);
          stringBuffer.append(genPackage.getQualifiedItemProviderAdapterFactoryClassName());
          stringBuffer.append(TEXT_19);
          for (ListIterator<?> j = genPackage.getProviderSupportedTypes().listIterator(); j.hasNext();) {
            stringBuffer.append(TEXT_20);
            stringBuffer.append(j.hasPrevious() ? " " : "\"");
            stringBuffer.append(j.next());
            if (!j.hasNext()) {
              stringBuffer.append(TEXT_21);
            }
          }
          stringBuffer.append(TEXT_22);
          if (genPackage.isChildCreationExtenders()) {
            Map<GenPackage, Map<GenClass, List<GenClass.ChildCreationData>>> extendedChildCreationData = ExtensionHelper
                .getExtendedChildCreationData(genPackage);
            if (!extendedChildCreationData.isEmpty()) {
              stringBuffer.append(TEXT_23);
              for (Map.Entry<GenPackage, Map<GenClass, List<GenClass.ChildCreationData>>> entry : extendedChildCreationData
                  .entrySet()) {
                stringBuffer.append(TEXT_24);
                stringBuffer.append(entry.getKey().getNSURI());
                stringBuffer.append(TEXT_25);
                stringBuffer.append(genPackage.getQualifiedItemProviderAdapterFactoryClassName());
                stringBuffer.append(TEXT_26);
                stringBuffer.append(genPackage.getChildCreationExtenderName(entry.getKey()));
                stringBuffer.append(TEXT_27);
              }
              stringBuffer.append(TEXT_28);
            }
          }
        }
      }
    }
    if (genModel.sameModelEditorProject()) {
      for (GenPackage genPackage : genModel.getAllGenPackagesWithClassifiers()) {
        stringBuffer.append(TEXT_29);
        stringBuffer.append(genPackage.getNSURI());
        stringBuffer.append(TEXT_30);
        if (genModel.hasLocalGenModel()) {
          stringBuffer.append(TEXT_31);
          stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
          stringBuffer.append(TEXT_32);
          stringBuffer.append(genModel.getRelativeGenModelLocation());
          stringBuffer.append(TEXT_33);
        } else {
          stringBuffer.append(TEXT_34);
          stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
          stringBuffer.append(TEXT_35);
        }
        stringBuffer.append(TEXT_36);
        if (genPackage.isContentType()) {
          stringBuffer.append(TEXT_37);
          stringBuffer.append(genPackage.getContentTypeIdentifier());
          stringBuffer.append(TEXT_38);
          stringBuffer.append(genPackage.getQualifiedEffectiveResourceFactoryClassName());
          stringBuffer.append(TEXT_39);
          stringBuffer
              .append(genPackage.isXMIResource() ? "org.eclipse.emf.ecore.xmi" : "org.eclipse.core.runtime.xml");
          stringBuffer.append(TEXT_40);
          stringBuffer.append(genPackage.getFileExtensions());
          stringBuffer.append(TEXT_41);
          stringBuffer.append(genPackage.getContentTypeIdentifier());
          stringBuffer.append(TEXT_42);
          stringBuffer.append(genPackage.getPrefix());
          stringBuffer.append(TEXT_43);
          if (genPackage.hasTargetNamespace()) {
            stringBuffer.append(TEXT_44);
            stringBuffer.append(genPackage.getNSURI());
            stringBuffer.append(TEXT_45);
          }
          if (genPackage.isXMIResource()) {
            stringBuffer.append(TEXT_46);
          }
          stringBuffer.append(TEXT_47);
        } else if (genPackage.getResource() != GenResourceKind.NONE_LITERAL) {
          stringBuffer.append(TEXT_48);
          stringBuffer.append(genPackage.getFileExtension());
          stringBuffer.append(TEXT_49);
          stringBuffer.append(genPackage.getQualifiedResourceFactoryClassName());
          stringBuffer.append(TEXT_50);
        }
      }
    }
    if (genModel.isRichClientPlatform()) {
      stringBuffer.append(TEXT_51);
      stringBuffer.append(genModel.getEditorAdvisorClassName());
      stringBuffer.append(TEXT_52);
      stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
      stringBuffer.append(TEXT_53);
      stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
      stringBuffer.append(TEXT_54);
      stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
      stringBuffer.append(TEXT_55);
      stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
      stringBuffer.append(TEXT_56);
      if (!genModel.isRichAjaxPlatform()) {
        stringBuffer.append(TEXT_57);
        stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
        stringBuffer.append(TEXT_58);
      }
      stringBuffer.append(TEXT_59);
      if (!genModel.isRichAjaxPlatform()) {
        stringBuffer.append(TEXT_60);
        stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
        stringBuffer.append(TEXT_61);
        stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
        stringBuffer.append(TEXT_62);
      }
      stringBuffer.append(TEXT_63);
      stringBuffer.append(genModel.getEditorAdvisorClassName());
      stringBuffer.append(TEXT_64);
      stringBuffer.append(genModel.getEditorAdvisorClassName());
      stringBuffer.append(TEXT_65);
      stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
      stringBuffer.append(TEXT_66);
      stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
      stringBuffer.append(TEXT_67);
      stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
      stringBuffer.append(TEXT_68);
      stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
      stringBuffer.append(TEXT_69);
      stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
      stringBuffer.append(TEXT_70);
      if (!genModel.isRichAjaxPlatform()) {
        stringBuffer.append(TEXT_71);
        stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
        stringBuffer.append(TEXT_72);
        stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
        stringBuffer.append(TEXT_73);
        stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
        stringBuffer.append(TEXT_74);
      }
      stringBuffer.append(TEXT_75);
    }
    for (GenPackage genPackage : genModel.getAllGenPackagesWithClassifiers()) {
      if (genPackage.hasConcreteClasses()) {
        if (genPackage.isGenerateModelWizard()) {
          if (genModel.isRichClientPlatform()) {
            stringBuffer.append(TEXT_76);
            stringBuffer.append(genPackage.getModelWizardClassName());
            stringBuffer.append(TEXT_77);
            stringBuffer.append(genPackage.getQualifiedActionBarContributorClassName());
            stringBuffer.append(TEXT_78);
            stringBuffer.append(genPackage.getModelWizardClassName());
            stringBuffer.append(TEXT_79);
            stringBuffer.append(genPackage.getQualifiedActionBarContributorClassName());
            stringBuffer.append(TEXT_80);
            stringBuffer.append(genPackage.getQualifiedActionBarContributorClassName());
            stringBuffer.append(TEXT_81);
          } else {
            stringBuffer.append(TEXT_82);
            stringBuffer.append(genPackage.getQualifiedModelWizardClassName());
            stringBuffer.append(TEXT_83);
            stringBuffer.append(genPackage.getModelWizardClassName());
            stringBuffer.append(TEXT_84);
            stringBuffer.append(genPackage.getQualifiedModelWizardClassName());
            stringBuffer.append(TEXT_85);
            stringBuffer.append(genPackage.getPrefix());
            stringBuffer.append(TEXT_86);
            stringBuffer.append(genPackage.getModelWizardClassName());
            stringBuffer.append(TEXT_87);
          }
        }
        stringBuffer.append(TEXT_88);
        stringBuffer.append(genPackage.getQualifiedEditorClassName());
        stringBuffer.append(TEXT_89);
        stringBuffer.append(genPackage.getEditorClassName());
        stringBuffer.append(TEXT_90);
        stringBuffer.append(genPackage.getPrefix());
        stringBuffer.append(TEXT_91);
        if (!genPackage.isContentType()) {
          stringBuffer.append(TEXT_92);
          stringBuffer.append(genPackage.getFileExtension());
          stringBuffer.append(TEXT_93);
        }
        stringBuffer.append(TEXT_94);
        stringBuffer.append(genPackage.getQualifiedEditorClassName());
        stringBuffer.append(TEXT_95);
        stringBuffer.append(genPackage.getQualifiedActionBarContributorClassName());
        stringBuffer.append(TEXT_96);
        if (genPackage.isContentType()) {
          stringBuffer.append(TEXT_97);
          stringBuffer.append(genPackage.getQualifiedContentTypeIdentifier());
          stringBuffer.append(TEXT_98);
        }
        stringBuffer.append(TEXT_99);
      }
    }
    stringBuffer.append(TEXT_100);
    InternalPatternContext ictx = (InternalPatternContext) ctx;
    new Node.DataLeaf(ictx.getNode(), getClass(), "doGenerate", stringBuffer.toString());
  }
}