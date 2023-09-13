//Generated with EGF 1.6.4.202309111303
package org.polarsys.capella.core.edit;

import org.eclipse.egf.emf.pattern.base.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.emf.codegen.ecore.genmodel.impl.*;
import org.eclipse.emf.codegen.ecore.genmodel.generator.*;
import org.eclipse.emf.codegen.util.*;
import org.eclipse.emf.ecore.util.*;
import org.eclipse.emf.common.util.*;
import org.eclipse.egf.common.helper.*;
import java.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.egf.model.pattern.*;
import org.eclipse.egf.pattern.execution.*;
import org.eclipse.egf.pattern.query.*;

public class Plugin extends org.eclipse.egf.emf.pattern.edit.Plugin {
  protected static String nl;

  public static synchronized Plugin create(String lineSeparator) {
    nl = lineSeparator;
    Plugin result = new Plugin();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "package ";
  protected final String TEXT_3 = ";" + NL;
  protected final String TEXT_4 = NL + NL + "/**" + NL + " * This is the central singleton for the ";
  protected final String TEXT_5 = " edit plugin." + NL + " * <!-- begin-user-doc -->" + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_6 = NL + " * ";
  protected final String TEXT_7 = NL + " * @generated" + NL + " */";
  protected final String TEXT_8 = NL + "@Deprecated";
  protected final String TEXT_9 = NL + "public final class ";
  protected final String TEXT_10 = " extends EMFPlugin" + NL + "{";
  protected final String TEXT_11 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
      + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_12 = " copyright = ";
  protected final String TEXT_13 = ";";
  protected final String TEXT_14 = NL;
  protected final String TEXT_15 = NL + "\t/**" + NL + "\t * Keep track of the singleton." + NL
      + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL
      + "\tpublic static final ";
  protected final String TEXT_16 = " INSTANCE = new ";
  protected final String TEXT_17 = "();" + NL;
  protected final String TEXT_18 = NL + "\t/**" + NL + "\t * Keep track of the singleton." + NL
      + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL
      + "\tprivate static Implementation plugin;" + NL;
  protected final String TEXT_19 = NL + "\t/**" + NL + "\t * Create the instance." + NL + "\t * <!-- begin-user-doc -->"
      + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_20 = "()" + NL + "\t{" + NL + "\t\tsuper" + NL + "\t\t  (new ResourceLocator [] " + NL
      + "\t\t   {";
  protected final String TEXT_21 = NL + "\t\t     ";
  protected final String TEXT_22 = ".INSTANCE,";
  protected final String TEXT_23 = NL + "\t\t   });" + NL + "\t}" + NL + "" + NL + "\t/**" + NL
      + "\t * Returns the singleton instance of the Eclipse plugin." + NL + "\t * <!-- begin-user-doc -->" + NL
      + "\t * <!-- end-user-doc -->" + NL + "\t * @return the singleton instance." + NL + "\t * @generated" + NL
      + "\t */";
  protected final String TEXT_24 = NL + "\t@Override";
  protected final String TEXT_25 = NL + "\tpublic ResourceLocator getPluginResourceLocator()" + NL + "\t{";
  protected final String TEXT_26 = NL + "\t\treturn null;";
  protected final String TEXT_27 = NL + "\t\treturn plugin;";
  protected final String TEXT_28 = NL + "\t}" + NL;
  protected final String TEXT_29 = NL + "\t/**" + NL + "\t * Returns the singleton instance of the Eclipse plugin." + NL
      + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the singleton instance."
      + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static Implementation getPlugin()" + NL + "\t{" + NL
      + "\t\treturn plugin;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL
      + "\t * The actual implementation of the Eclipse <b>Plugin</b>." + NL + "\t * <!-- begin-user-doc -->" + NL
      + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL
      + "\tpublic static class Implementation extends EclipsePlugin" + NL + "\t{" + NL + "\t\t/**" + NL
      + "\t\t * Creates an instance." + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->";
  protected final String TEXT_30 = NL + "\t\t * @param descriptor the description of the plugin.";
  protected final String TEXT_31 = NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tpublic Implementation(";
  protected final String TEXT_32 = " descriptor";
  protected final String TEXT_33 = ")" + NL + "\t\t{" + NL + "\t\t\tsuper(";
  protected final String TEXT_34 = "descriptor";
  protected final String TEXT_35 = ");" + NL + "" + NL + "\t\t\t// Remember the static instance." + NL + "\t\t\t//" + NL
      + "\t\t\tplugin = this;" + NL + "\t\t}";
  protected final String TEXT_36 = NL + "\t" + NL + "\t\t/**" + NL
      + "\t\t * The actual implementation of the purely OSGi-compatible <b>Bundle Activator</b>." + NL
      + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL
      + "\t\t */" + NL + "\t\tpublic static final class Activator extends ";
  protected final String TEXT_37 = ".OSGiDelegatingBundleActivator" + NL + "\t\t{";
  protected final String TEXT_38 = NL + "\t\t\t@Override";
  protected final String TEXT_39 = NL + "\t\t\tprotected ";
  protected final String TEXT_40 = " createBundle()" + NL + "\t\t\t{" + NL + "\t\t\t\treturn new Implementation();" + NL
      + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_41 = NL + "\t\t" + NL + "\t\t//begin-capella-code" + NL + "\t\t/**" + NL
      + "\t\t * Returns the object's graphical representation." + NL
      + "\t\t * (This method comes from a customization of the standard EMF generator)" + NL + "\t\t *" + NL
      + "\t\t * @param key the identifier of the object." + NL + "\t\t * @generated" + NL + "\t\t */" + NL
      + "\t\t@Override" + NL + "\t\tpublic Object getImage(String key) {" + NL + "\t\t\treturn ";
  protected final String TEXT_42 = ".find(this.getBundle(), new ";
  protected final String TEXT_43 = "(\"icons/\" + key + \".gif\"), null);" + NL + "\t\t}" + NL
      + "\t\t//end-capella-code" + NL + "\t}" + NL;
  protected final String TEXT_44 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
      + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_45 = " PROPERTIES = ";
  protected final String TEXT_46 = ".create(";
  protected final String TEXT_47 = ".class);" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
      + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Override" + NL
      + "\tpublic String getString(String key, boolean translate)" + NL + "\t{";
  protected final String TEXT_48 = NL + "\t\t";
  protected final String TEXT_49 = "else ";
  protected final String TEXT_50 = "if (\"_UI_";
  protected final String TEXT_51 = "_type\".equals(key)) return PROPERTIES.";
  protected final String TEXT_52 = "Type();";
  protected final String TEXT_53 = " if (\"_UI_Unknown_type\".equals(key)) return PROPERTIES.unknownType();" + NL
      + "\t\telse if (\"_UI_Unknown_datatype\".equals(key)) return PROPERTIES.unknownDatatype();";
  protected final String TEXT_54 = NL + "\t\telse if (\"_UI_";
  protected final String TEXT_55 = "_";
  protected final String TEXT_56 = "_feature\".equals(key)) return PROPERTIES.";
  protected final String TEXT_57 = "Feature();";
  protected final String TEXT_58 = "_description\".equals(key)) return PROPERTIES.";
  protected final String TEXT_59 = "Description();";
  protected final String TEXT_60 = NL
      + "\t\telse if (\"_UI_Unknown_feature\".equals(key)) return PROPERTIES.unknownFeature();";
  protected final String TEXT_61 = "_literal\".equals(key)) return PROPERTIES.";
  protected final String TEXT_62 = "Literal();";
  protected final String TEXT_63 = " = ";
  protected final String TEXT_64 = NL + "\t\telse if (\"";
  protected final String TEXT_65 = "\".equals(key)) return PROPERTIES.";
  protected final String TEXT_66 = "();";
  protected final String TEXT_67 = NL + "\t\telse return key;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL
      + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL
      + "\t@Override" + NL + "\tpublic String getString(String key, Object [] substitutions, boolean translate)" + NL
      + "\t{";
  protected final String TEXT_68 = NL
      + "\t\tif (\"_UI_CreateChild_text\".equals(key)) return PROPERTIES.createChildText(substitutions[0]);" + NL
      + "\t\telse if (\"_UI_CreateChild_text2\".equals(key)) return PROPERTIES.createChildText2(substitutions[0], substitutions[1]);"
      + NL + "\t\telse if (\"_UI_CreateChild_text3\".equals(key)) return PROPERTIES.createChildText3(substitutions[1]);"
      + NL
      + "\t\telse if (\"_UI_CreateChild_tooltip\".equals(key)) return PROPERTIES.createChildTooltip(substitutions[0], substitutions[1]);"
      + NL
      + "\t\telse if (\"_UI_CreateChild_description\".equals(key)) return PROPERTIES.createChildDescripition(substitutions[0], substitutions[1], substitutions[2]);"
      + NL
      + "\t\telse if (\"_UI_CreateSibling_description\".equals(key)) return PROPERTIES.createSiblingDescription(substitutions[0], substitutions[1], substitutions[2]);";
  protected final String TEXT_69 = "if (\"_UI_PropertyDescriptor_description\".equals(key)) return PROPERTIES.propertyDescriptorDescription(substitutions[0], substitutions[1]);"
      + NL + "\t\telse return key;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
      + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_70 = " IMAGES = ";
  protected final String TEXT_71 = ".class);" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
      + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Override" + NL
      + "\tpublic Object getImage(String key)" + NL + "\t{";
  protected final String TEXT_72 = "if (\"";
  protected final String TEXT_73 = "\".equals(key)) return IMAGES.";
  protected final String TEXT_74 = "return key;" + NL + "\t}" + NL;
  protected final String TEXT_75 = NL + "}";

  public Plugin() {
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

    stringBuffer.append(TEXT_14);
    stringBuffer.append(TEXT_14);
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
     * are made available under the terms of the Eclipse Public License v2.0
     * which accompanies this distribution, and is available at
     * http://www.eclipse.org/legal/epl-v20.html
     *
     * Contributors:
     *   IBM - Initial API and implementation
     */

    GenModel genModel = (GenModel) argument;
    /* Trick to import java.util.* without warnings */Iterator.class.getName();
    final boolean isJDK50 = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50;
    stringBuffer.append(TEXT_1);
    {
      //<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern.base/egf/EMF_Pattern_Base.fcore#LogicalName=org.eclipse.egf.emf.pattern.base.HeaderJava" args="parameter:argument"%>

      InternalPatternContext ictx = (InternalPatternContext) ctx;
      new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
      stringBuffer.setLength(0);

      final Map<String, Object> callParameters = new HashMap<String, Object>();
      callParameters.put("argument", parameter);
      CallHelper.executeWithParameterInjection(
          "platform:/plugin/org.eclipse.egf.emf.pattern.base/egf/EMF_Pattern_Base.fcore#_XHLrsCwtEd-jc5T-XaRJlg",
          new ExecutionContext((InternalPatternContext) ctx), callParameters);
      stringBuffer.setLength(0);
    }

    stringBuffer.append(TEXT_2);
    stringBuffer.append(genModel.getEditPluginPackageName());
    stringBuffer.append(TEXT_3);
    genModel.addImport("org.eclipse.emf.common.EMFPlugin");
    genModel.addImport("org.eclipse.emf.common.util.ResourceLocator");
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genModel.getModelName());
    stringBuffer.append(TEXT_5);
    if (genModel.hasAPITags()) {
      stringBuffer.append(TEXT_6);
      stringBuffer.append(genModel.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_7);
    if (isJDK50 && genModel.hasAPIDeprecatedTag()) {
      stringBuffer.append(TEXT_8);
    }
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genModel.getEditPluginClassName());
    stringBuffer.append(TEXT_10);
    if (genModel.hasCopyrightField()) {
      stringBuffer.append(TEXT_11);
      stringBuffer.append(genModel.getImportedName("java.lang.String"));
      stringBuffer.append(TEXT_12);
      stringBuffer.append(genModel.getCopyrightFieldLiteral());
      stringBuffer.append(TEXT_13);
      stringBuffer.append(genModel.getNonNLS());
      stringBuffer.append(TEXT_14);
    }
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genModel.getEditPluginClassName());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genModel.getEditPluginClassName());
    stringBuffer.append(TEXT_17);
    if (genModel.getRuntimePlatform() != GenRuntimePlatform.GWT) {
      stringBuffer.append(TEXT_18);
    }
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genModel.getEditPluginClassName());
    stringBuffer.append(TEXT_20);
    for (String pluginClassName : genModel.getEditResourceDelegateImportedPluginClassNames()) {
      stringBuffer.append(TEXT_21);
      stringBuffer.append(pluginClassName);
      stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_23);
    if (genModel.useClassOverrideAnnotation()) {
      stringBuffer.append(TEXT_24);
    }
    stringBuffer.append(TEXT_25);
    if (genModel.getRuntimePlatform() == GenRuntimePlatform.GWT) {
      stringBuffer.append(TEXT_26);
    } else {
      stringBuffer.append(TEXT_27);
    }
    stringBuffer.append(TEXT_28);
    if (genModel.getRuntimePlatform() != GenRuntimePlatform.GWT) {
      stringBuffer.append(TEXT_29);
      if (genModel.needsRuntimeCompatibility()) {
        stringBuffer.append(TEXT_30);
      }
      stringBuffer.append(TEXT_31);
      if (genModel.needsRuntimeCompatibility()) {
        stringBuffer.append(genModel.getImportedName("org.eclipse.core.runtime.IPluginDescriptor"));
        stringBuffer.append(TEXT_32);
      }
      stringBuffer.append(TEXT_33);
      if (genModel.needsRuntimeCompatibility()) {
        stringBuffer.append(TEXT_34);
      }
      stringBuffer.append(TEXT_35);
      if (genModel.isOSGiCompatible()) {
        stringBuffer.append(TEXT_36);
        stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.EMFPlugin"));
        stringBuffer.append(TEXT_37);
        if (genModel.useClassOverrideAnnotation()) {
          stringBuffer.append(TEXT_38);
        }
        stringBuffer.append(TEXT_39);
        stringBuffer.append(genModel.getImportedName("org.osgi.framework.BundleActivator"));
        stringBuffer.append(TEXT_40);
      }
      stringBuffer.append(TEXT_41);
      stringBuffer.append(genModel.getImportedName("org.eclipse.core.runtime.FileLocator"));
      stringBuffer.append(TEXT_42);
      stringBuffer.append(genModel.getImportedName("org.eclipse.core.runtime.Path"));
      stringBuffer.append(TEXT_43);
    } else {
      stringBuffer.append(TEXT_44);
      stringBuffer.append(genModel.getImportedName(genModel.getQualifiedEditPluginClassName() + "Properties"));
      stringBuffer.append(TEXT_45);
      stringBuffer.append(genModel.getImportedName("com.google.gwt.core.client.GWT"));
      stringBuffer.append(TEXT_46);
      stringBuffer.append(genModel.getImportedName(genModel.getQualifiedEditPluginClassName() + "Properties"));
      stringBuffer.append(TEXT_47);
      boolean first = true;
      for (GenPackage genPackage : genModel.getAllGenAndUsedGenPackagesWithClassifiers()) {
        if (genPackage.getGenModel() == genModel || !genPackage.getGenModel().hasEditSupport()) {
          for (GenClass genClass : genPackage.getGenClasses()) {
            stringBuffer.append(TEXT_48);
            if (first) {
              first = false;
            } else {
              stringBuffer.append(TEXT_49);
            }
            stringBuffer.append(TEXT_50);
            stringBuffer.append(genClass.getName());
            stringBuffer.append(TEXT_51);
            stringBuffer.append(genClass.getUncapName());
            stringBuffer.append(TEXT_52);
          }
        }
      }
      stringBuffer.append(TEXT_48);
      if (first) {
        first = false;
      } else {
        stringBuffer.append(TEXT_49);
      }
      stringBuffer.append(TEXT_53);
      for (GenFeature genFeature : genModel.getFilteredAllGenFeatures()) {
        String description = genFeature.getPropertyDescription();
        stringBuffer.append(TEXT_54);
        stringBuffer.append(genFeature.getGenClass().getName());
        stringBuffer.append(TEXT_55);
        stringBuffer.append(genFeature.getName());
        stringBuffer.append(TEXT_56);
        stringBuffer.append(genFeature.getGenClass().getUncapName());
        stringBuffer.append(TEXT_55);
        stringBuffer.append(genFeature.getCapName());
        stringBuffer.append(TEXT_57);
        if (description != null && description.length() > 0) {
          stringBuffer.append(TEXT_54);
          stringBuffer.append(genFeature.getGenClass().getName());
          stringBuffer.append(TEXT_55);
          stringBuffer.append(genFeature.getName());
          stringBuffer.append(TEXT_58);
          stringBuffer.append(genFeature.getGenClass().getUncapName());
          stringBuffer.append(TEXT_55);
          stringBuffer.append(genFeature.getCapName());
          stringBuffer.append(TEXT_59);
        }
      }
      stringBuffer.append(TEXT_60);
      for (GenPackage genPackage : genModel.getAllGenAndUsedGenPackagesWithClassifiers()) {
        if (genPackage.getGenModel() == genModel || !genPackage.getGenModel().hasEditSupport()) {
          for (GenEnum genEnum : genPackage.getGenEnums()) {
            for (GenEnumLiteral genEnumLiteral : genEnum.getGenEnumLiterals()) {
              stringBuffer.append(TEXT_54);
              stringBuffer.append(genEnum.getName());
              stringBuffer.append(TEXT_55);
              stringBuffer.append(genEnumLiteral.getName());
              stringBuffer.append(TEXT_61);
              stringBuffer.append(genEnum.getSafeUncapName());
              stringBuffer.append(TEXT_55);
              stringBuffer.append(genEnumLiteral.getName());
              stringBuffer.append(TEXT_62);
            }
          }
        }
      }
      for (String category : genModel.getPropertyCategories()) {
        stringBuffer.append(TEXT_14);
        stringBuffer.append(genModel.getPropertyCategoryKey(category));
        stringBuffer.append(TEXT_63);
        stringBuffer.append(category);
        stringBuffer.append(TEXT_64);
        stringBuffer.append(genModel.getPropertyCategoryKey(category));
        stringBuffer.append(TEXT_65);
        stringBuffer.append(genModel.getPropertyCategoryKey(category));
        stringBuffer.append(TEXT_66);
      }
      stringBuffer.append(TEXT_67);
      if (genModel.isCreationCommands()) {
        stringBuffer.append(TEXT_68);
      }
      stringBuffer.append(TEXT_48);
      if (!genModel.isCreationCommands()) {
        stringBuffer.append(TEXT_49);
      }
      stringBuffer.append(TEXT_69);
      stringBuffer.append(genModel.getImportedName(genModel.getQualifiedEditPluginClassName() + "Images"));
      stringBuffer.append(TEXT_70);
      stringBuffer.append(genModel.getImportedName("com.google.gwt.core.client.GWT"));
      stringBuffer.append(TEXT_46);
      stringBuffer.append(genModel.getImportedName(genModel.getQualifiedEditPluginClassName() + "Images"));
      stringBuffer.append(TEXT_71);
      first = true;
      for (GenPackage genPackage : genModel.getAllGenAndUsedGenPackagesWithClassifiers()) {
        for (GenClass genClass : genPackage.getGenClasses()) {
          if (genClass.isImage()) {
            String image = genClass.getItemIconFileName();
            image = image.substring(image.lastIndexOf("/icons/") + 7, image.length() - 4);
            stringBuffer.append(TEXT_48);
            if (first) {
              first = false;
            } else {
              stringBuffer.append(TEXT_49);
            }
            stringBuffer.append(TEXT_72);
            stringBuffer.append(image);
            stringBuffer.append(TEXT_73);
            stringBuffer.append(genClass.getItemIconAccessorName());
            stringBuffer.append(TEXT_66);
          }
        }
      }
      stringBuffer.append(TEXT_48);
      if (first) {
        first = false;
      } else {
        stringBuffer.append(TEXT_49);
      }
      stringBuffer.append(TEXT_74);
    }
    stringBuffer.append(TEXT_75);
    genModel.emitSortedImports();
    InternalPatternContext ictx = (InternalPatternContext) ctx;
    new Node.DataLeaf(ictx.getNode(), getClass(), "doGenerate", stringBuffer.toString());
  }

  public boolean preCondition(PatternContext ctx) throws Exception {
    GenModel genModel = parameter;
    genModel = parameter.getGenModel();
    boolean canGenerate = new CodegenGeneratorAdapter(parameter)
        .canGenerate("org.eclipse.emf.codegen.ecore.genmodel.generator.EditProject");
    canGenerate = canGenerate && (!genModel.sameEditEditorProject());
    return canGenerate;
  }
}