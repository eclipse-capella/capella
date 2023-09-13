//Generated with EGF 1.6.4.202309111303
package org.polarsys.capella.common.tig.model;

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

public class ClassgetGenFeatureoverride
    extends org.eclipse.egf.emf.pattern.model.call.Class.ClassgetGenFeatureoverride {
  protected static String nl;

  public static synchronized ClassgetGenFeatureoverride create(String lineSeparator) {
    nl = lineSeparator;
    ClassgetGenFeatureoverride result = new ClassgetGenFeatureoverride();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
      + "\t * <!-- end-user-doc -->";
  protected final String TEXT_3 = NL + "\t * ";
  protected final String TEXT_4 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_5 = NL;
  protected final String TEXT_6 = NL + "\t@Deprecated";
  protected final String TEXT_7 = NL + "\t";
  protected final String TEXT_8 = " ";
  protected final String TEXT_9 = "();" + NL;
  protected final String TEXT_10 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_11 = NL + "\tpublic ";
  protected final String TEXT_12 = "_";
  protected final String TEXT_13 = "()" + NL + "\t{";
  protected final String TEXT_14 = NL + "\t\treturn ";
  protected final String TEXT_15 = "(";
  protected final String TEXT_16 = ")eDynamicGet(";
  protected final String TEXT_17 = ", ";
  protected final String TEXT_18 = ", true, ";
  protected final String TEXT_19 = ")";
  protected final String TEXT_20 = ").";
  protected final String TEXT_21 = "()";
  protected final String TEXT_22 = ";";
  protected final String TEXT_23 = ")eGet(";
  protected final String TEXT_24 = ", true)";
  protected final String TEXT_25 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, true, false)";
  protected final String TEXT_26 = NL + "\t\t";
  protected final String TEXT_27 = " = (";
  protected final String TEXT_28 = ")eVirtualGet(";
  protected final String TEXT_29 = ");";
  protected final String TEXT_30 = NL + "\t\tif (";
  protected final String TEXT_31 = " == null)" + NL + "\t\t{";
  protected final String TEXT_32 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_33 = " = new ";
  protected final String TEXT_34 = NL + "\t\t\t";
  protected final String TEXT_35 = NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_36 = NL + "\t\tif (eContainerFeatureID() != ";
  protected final String TEXT_37 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_38 = "eContainer";
  protected final String TEXT_39 = "eInternalContainer";
  protected final String TEXT_40 = "();";
  protected final String TEXT_41 = " != null && ";
  protected final String TEXT_42 = ".eIsProxy())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_43 = " old";
  protected final String TEXT_44 = " = ";
  protected final String TEXT_45 = ";" + NL + "\t\t\t";
  protected final String TEXT_46 = "eResolveProxy(old";
  protected final String TEXT_47 = ");" + NL + "\t\t\tif (";
  protected final String TEXT_48 = " != old";
  protected final String TEXT_49 = ")" + NL + "\t\t\t{";
  protected final String TEXT_50 = NL + "\t\t\t\t";
  protected final String TEXT_51 = " new";
  protected final String TEXT_52 = " msgs = old";
  protected final String TEXT_53 = ".eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_54 = ", null, null);";
  protected final String TEXT_55 = " msgs =  old";
  protected final String TEXT_56 = ".eInverseRemove(this, ";
  protected final String TEXT_57 = ".class, null);";
  protected final String TEXT_58 = NL + "\t\t\t\tif (new";
  protected final String TEXT_59 = ".eInternalContainer() == null)" + NL + "\t\t\t\t{";
  protected final String TEXT_60 = NL + "\t\t\t\t\tmsgs = new";
  protected final String TEXT_61 = ".eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_62 = ", null, msgs);";
  protected final String TEXT_63 = NL + "\t\t\t\t\tmsgs =  new";
  protected final String TEXT_64 = ".eInverseAdd(this, ";
  protected final String TEXT_65 = ".class, msgs);";
  protected final String TEXT_66 = NL + "\t\t\t\t}" + NL + "\t\t\t\tif (msgs != null) msgs.dispatch();";
  protected final String TEXT_67 = NL + "\t\t\t\teVirtualSet(";
  protected final String TEXT_68 = NL + "\t\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\t\teNotify(new ";
  protected final String TEXT_69 = "(this, ";
  protected final String TEXT_70 = ".RESOLVE, ";
  protected final String TEXT_71 = ", old";
  protected final String TEXT_72 = "));";
  protected final String TEXT_73 = NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_74 = NL + "\t\treturn (";
  protected final String TEXT_75 = " & ";
  protected final String TEXT_76 = "_EFLAG) != 0;";
  protected final String TEXT_77 = "_EFLAG_VALUES[(";
  protected final String TEXT_78 = "_EFLAG) >>> ";
  protected final String TEXT_79 = "_EFLAG_OFFSET];";
  protected final String TEXT_80 = " = basicGet";
  protected final String TEXT_81 = "();" + NL + "\t\treturn ";
  protected final String TEXT_82 = ".eIsProxy() ? ";
  protected final String TEXT_83 = "eResolveProxy(";
  protected final String TEXT_84 = ") : ";
  protected final String TEXT_85 = NL + "\t\treturn new ";
  protected final String TEXT_86 = "((";
  protected final String TEXT_87 = ".Internal)((";
  protected final String TEXT_88 = ".Internal.Wrapper)get";
  protected final String TEXT_89 = "()).featureMap().";
  protected final String TEXT_90 = "list(";
  protected final String TEXT_91 = ")get";
  protected final String TEXT_92 = "().";
  protected final String TEXT_93 = NL + "\t\treturn ((";
  protected final String TEXT_94 = "()).featureMap().list(";
  protected final String TEXT_95 = NL + "\t\treturn get";
  protected final String TEXT_96 = "().list(";
  protected final String TEXT_97 = "()).featureMap().get(";
  protected final String TEXT_98 = "get";
  protected final String TEXT_99 = "().get(";
  protected final String TEXT_100 = NL + "\t}" + NL;

  public ClassgetGenFeatureoverride() {
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

    List<Object> genFeatureList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)
    List<Object> genClassList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)
    List<Object> genPackageList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)
    List<Object> genModelList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)
    List<Object> isJDK50List = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)
    List<Object> isInterfaceList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)
    List<Object> isImplementationList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)
    List<Object> useInterfaceOverrideAnnotationList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)
    List<Object> isGWTList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)
    List<Object> forceDefaultCaseList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)
    List<Object> indentDefaultCaseList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)
    List<Object> publicStaticFinalFlagList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)
    List<Object> singleWildcardList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)
    List<Object> negativeOffsetCorrectionList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)
    List<Object> positiveOffsetCorrectionList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)
    List<Object> negativeOperationOffsetCorrectionList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)
    List<Object> positiveOperationOffsetCorrectionList = null;
    //this pattern can only be called by another (i.e. it's not an entry point in execution)

    for (Object genFeatureParameter : genFeatureList) {
      for (Object genClassParameter : genClassList) {
        for (Object genPackageParameter : genPackageList) {
          for (Object genModelParameter : genModelList) {
            for (Object isJDK50Parameter : isJDK50List) {
              for (Object isInterfaceParameter : isInterfaceList) {
                for (Object isImplementationParameter : isImplementationList) {
                  for (Object useInterfaceOverrideAnnotationParameter : useInterfaceOverrideAnnotationList) {
                    for (Object isGWTParameter : isGWTList) {
                      for (Object forceDefaultCaseParameter : forceDefaultCaseList) {
                        for (Object indentDefaultCaseParameter : indentDefaultCaseList) {
                          for (Object publicStaticFinalFlagParameter : publicStaticFinalFlagList) {
                            for (Object singleWildcardParameter : singleWildcardList) {
                              for (Object negativeOffsetCorrectionParameter : negativeOffsetCorrectionList) {
                                for (Object positiveOffsetCorrectionParameter : positiveOffsetCorrectionList) {
                                  for (Object negativeOperationOffsetCorrectionParameter : negativeOperationOffsetCorrectionList) {
                                    for (Object positiveOperationOffsetCorrectionParameter : positiveOperationOffsetCorrectionList) {

                                      this.genFeature = (org.eclipse.emf.codegen.ecore.genmodel.GenFeature) genFeatureParameter;
                                      this.genClass = (org.eclipse.emf.codegen.ecore.genmodel.GenClass) genClassParameter;
                                      this.genPackage = (org.eclipse.emf.codegen.ecore.genmodel.GenPackage) genPackageParameter;
                                      this.genModel = (org.eclipse.emf.codegen.ecore.genmodel.GenModel) genModelParameter;
                                      this.isJDK50 = (java.lang.Boolean) isJDK50Parameter;
                                      this.isInterface = (java.lang.Boolean) isInterfaceParameter;
                                      this.isImplementation = (java.lang.Boolean) isImplementationParameter;
                                      this.useInterfaceOverrideAnnotation = (java.lang.Boolean) useInterfaceOverrideAnnotationParameter;
                                      this.isGWT = (java.lang.Boolean) isGWTParameter;
                                      this.forceDefaultCase = (java.lang.Boolean) forceDefaultCaseParameter;
                                      this.indentDefaultCase = (java.lang.String) indentDefaultCaseParameter;
                                      this.publicStaticFinalFlag = (java.lang.String) publicStaticFinalFlagParameter;
                                      this.singleWildcard = (java.lang.String) singleWildcardParameter;
                                      this.negativeOffsetCorrection = (java.lang.String) negativeOffsetCorrectionParameter;
                                      this.positiveOffsetCorrection = (java.lang.String) positiveOffsetCorrectionParameter;
                                      this.negativeOperationOffsetCorrection = (java.lang.String) negativeOperationOffsetCorrectionParameter;
                                      this.positiveOperationOffsetCorrection = (java.lang.String) positiveOperationOffsetCorrectionParameter;

                                      if (preCondition(ctx)) {
                                        ctx.setNode(new Node.Container(currentNode, getClass()));
                                        orchestration(ctx);
                                      }

                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    ctx.setNode(currentNode);
    if (ctx.useReporter()) {
      ctx.getReporter().executionFinished(OutputManager.computeExecutionOutput(ctx), ctx);
    }

    stringBuffer.append(TEXT_5);
    stringBuffer.append(TEXT_5);
    return stringBuffer.toString();
  }

  public String orchestration(PatternContext ctx) throws Exception {
    InternalPatternContext ictx = (InternalPatternContext) ctx;

    super.orchestration(new SuperOrchestrationContext(ictx));

    if (ictx.useReporter()) {
      Map<String, Object> parameterValues = new HashMap<String, Object>();
      parameterValues.put("genFeature", this.genFeature);
      parameterValues.put("genClass", this.genClass);
      parameterValues.put("genPackage", this.genPackage);
      parameterValues.put("genModel", this.genModel);
      parameterValues.put("isJDK50", this.isJDK50);
      parameterValues.put("isInterface", this.isInterface);
      parameterValues.put("isImplementation", this.isImplementation);
      parameterValues.put("useInterfaceOverrideAnnotation", this.useInterfaceOverrideAnnotation);
      parameterValues.put("isGWT", this.isGWT);
      parameterValues.put("forceDefaultCase", this.forceDefaultCase);
      parameterValues.put("indentDefaultCase", this.indentDefaultCase);
      parameterValues.put("publicStaticFinalFlag", this.publicStaticFinalFlag);
      parameterValues.put("singleWildcard", this.singleWildcard);
      parameterValues.put("negativeOffsetCorrection", this.negativeOffsetCorrection);
      parameterValues.put("positiveOffsetCorrection", this.positiveOffsetCorrection);
      parameterValues.put("negativeOperationOffsetCorrection", this.negativeOperationOffsetCorrection);
      parameterValues.put("positiveOperationOffsetCorrection", this.positiveOperationOffsetCorrection);
      String outputWithCallBack = OutputManager.computeLoopOutput(ictx);
      String loop = OutputManager.computeLoopOutputWithoutCallback(ictx);
      ictx.getReporter().loopFinished(loop, outputWithCallBack, ictx, parameterValues);
    }
    return null;
  }

  public Map<String, Object> getParameters() {
    final Map<String, Object> parameters = new HashMap<String, Object>();
    parameters.put("genFeature", this.genFeature);
    parameters.put("genClass", this.genClass);
    parameters.put("genPackage", this.genPackage);
    parameters.put("genModel", this.genModel);
    parameters.put("isJDK50", this.isJDK50);
    parameters.put("isInterface", this.isInterface);
    parameters.put("isImplementation", this.isImplementation);
    parameters.put("useInterfaceOverrideAnnotation", this.useInterfaceOverrideAnnotation);
    parameters.put("isGWT", this.isGWT);
    parameters.put("forceDefaultCase", this.forceDefaultCase);
    parameters.put("indentDefaultCase", this.indentDefaultCase);
    parameters.put("publicStaticFinalFlag", this.publicStaticFinalFlag);
    parameters.put("singleWildcard", this.singleWildcard);
    parameters.put("negativeOffsetCorrection", this.negativeOffsetCorrection);
    parameters.put("positiveOffsetCorrection", this.positiveOffsetCorrection);
    parameters.put("negativeOperationOffsetCorrection", this.negativeOperationOffsetCorrection);
    parameters.put("positiveOperationOffsetCorrection", this.positiveOperationOffsetCorrection);
    return parameters;
  }

  protected void method_doGenerate(final StringBuffer stringBuffer, final PatternContext ctx) throws Exception {

    if (isInterface) {
      stringBuffer.append(TEXT_1);
      {
        //<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#LogicalName=org.eclipse.egf.emf.pattern.model.call.Class.Class.getGenFeature.javadoc.override" args="genFeature:genFeature,genClass:genClass,genPackage:genPackage,genModel:genModel,isJDK50:isJDK50,isInterface:isInterface,isImplementation:isImplementation,useInterfaceOverrideAnnotation:useInterfaceOverrideAnnotation,isGWT:isGWT,forceDefaultCase:forceDefaultCase,indentDefaultCase:indentDefaultCase,publicStaticFinalFlag:publicStaticFinalFlag,singleWildcard:singleWildcard,negativeOffsetCorrection:negativeOffsetCorrection,positiveOffsetCorrection:positiveOffsetCorrection,negativeOperationOffsetCorrection:negativeOperationOffsetCorrection,positiveOperationOffsetCorrection:positiveOperationOffsetCorrection"%>

        InternalPatternContext ictx = (InternalPatternContext) ctx;
        new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
        stringBuffer.setLength(0);

        final Map<String, Object> callParameters = new HashMap<String, Object>();
        callParameters.put("genFeature", genFeature);
        callParameters.put("genClass", genClass);
        callParameters.put("genPackage", genPackage);
        callParameters.put("genModel", genModel);
        callParameters.put("isJDK50", isJDK50);
        callParameters.put("isInterface", isInterface);
        callParameters.put("isImplementation", isImplementation);
        callParameters.put("useInterfaceOverrideAnnotation", useInterfaceOverrideAnnotation);
        callParameters.put("isGWT", isGWT);
        callParameters.put("forceDefaultCase", forceDefaultCase);
        callParameters.put("indentDefaultCase", indentDefaultCase);
        callParameters.put("publicStaticFinalFlag", publicStaticFinalFlag);
        callParameters.put("singleWildcard", singleWildcard);
        callParameters.put("negativeOffsetCorrection", negativeOffsetCorrection);
        callParameters.put("positiveOffsetCorrection", positiveOffsetCorrection);
        callParameters.put("negativeOperationOffsetCorrection", negativeOperationOffsetCorrection);
        callParameters.put("positiveOperationOffsetCorrection", positiveOperationOffsetCorrection);
        CallHelper.executeWithParameterInjection(
            "platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#_0kroEGJ-Ed-FqczH3ESmRw",
            new ExecutionContext((InternalPatternContext) ctx), callParameters);
        stringBuffer.setLength(0);
      }

      //Class/getGenFeature.javadoc.override.javajetinc
    } else {
      stringBuffer.append(TEXT_2);
      if (genFeature.hasAPITags()) {
        stringBuffer.append(TEXT_3);
        stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
      }
      stringBuffer.append(TEXT_4);
      if (isJDK50) { //Class/getGenFeature.annotations.insert.javajetinc
        stringBuffer.append(TEXT_5);
        {
          //<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#LogicalName=org.eclipse.egf.emf.pattern.model.call.Class.Class.getGenFeature.annotations.insert" args="genFeature:genFeature,genClass:genClass,genPackage:genPackage,genModel:genModel,isJDK50:isJDK50,isInterface:isInterface,isImplementation:isImplementation,useInterfaceOverrideAnnotation:useInterfaceOverrideAnnotation,isGWT:isGWT,forceDefaultCase:forceDefaultCase,indentDefaultCase:indentDefaultCase,publicStaticFinalFlag:publicStaticFinalFlag,singleWildcard:singleWildcard,negativeOffsetCorrection:negativeOffsetCorrection,positiveOffsetCorrection:positiveOffsetCorrection,negativeOperationOffsetCorrection:negativeOperationOffsetCorrection,positiveOperationOffsetCorrection:positiveOperationOffsetCorrection"%>

          InternalPatternContext ictx = (InternalPatternContext) ctx;
          new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
          stringBuffer.setLength(0);

          final Map<String, Object> callParameters = new HashMap<String, Object>();
          callParameters.put("genFeature", genFeature);
          callParameters.put("genClass", genClass);
          callParameters.put("genPackage", genPackage);
          callParameters.put("genModel", genModel);
          callParameters.put("isJDK50", isJDK50);
          callParameters.put("isInterface", isInterface);
          callParameters.put("isImplementation", isImplementation);
          callParameters.put("useInterfaceOverrideAnnotation", useInterfaceOverrideAnnotation);
          callParameters.put("isGWT", isGWT);
          callParameters.put("forceDefaultCase", forceDefaultCase);
          callParameters.put("indentDefaultCase", indentDefaultCase);
          callParameters.put("publicStaticFinalFlag", publicStaticFinalFlag);
          callParameters.put("singleWildcard", singleWildcard);
          callParameters.put("negativeOffsetCorrection", negativeOffsetCorrection);
          callParameters.put("positiveOffsetCorrection", positiveOffsetCorrection);
          callParameters.put("negativeOperationOffsetCorrection", negativeOperationOffsetCorrection);
          callParameters.put("positiveOperationOffsetCorrection", positiveOperationOffsetCorrection);
          CallHelper.executeWithParameterInjection(
              "platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#_0lIUFGJ-Ed-FqczH3ESmRw",
              new ExecutionContext((InternalPatternContext) ctx), callParameters);
          stringBuffer.setLength(0);
        }

      }
    }
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
      stringBuffer.append(TEXT_6);
    }
    if (!isImplementation) {
      stringBuffer.append(TEXT_7);
      stringBuffer.append(genFeature.getImportedType(genClass));
      stringBuffer.append(TEXT_8);
      stringBuffer.append(genFeature.getGetAccessor());
      stringBuffer.append(TEXT_9);
    } else {
      if (genModel.useGenerics() && ((genFeature.isContainer() || genFeature.isResolveProxies())
          && !genFeature.isListType() && !(genModel.isReflectiveDelegation() && genModel.isDynamicDelegation())
          && genFeature.isUncheckedCast(genClass)
          || genFeature.isListType() && !genFeature.isFeatureMapType()
              && (genModel.isReflectiveDelegation() || genModel.isVirtualDelegation() || genModel.isDynamicDelegation())
          || genFeature.isListDataType() && genFeature.hasDelegateFeature()
          || genFeature.isListType() && genFeature.hasSettingDelegate())) {
        stringBuffer.append(TEXT_10);
      }
      stringBuffer.append(TEXT_11);
      stringBuffer.append(genFeature.getImportedType(genClass));
      stringBuffer.append(TEXT_8);
      stringBuffer.append(genFeature.getGetAccessor());
      if (genClass.hasCollidingGetAccessorOperation(genFeature)) {
        stringBuffer.append(TEXT_12);
      }
      stringBuffer.append(TEXT_13);
      stringBuffer.append(TEXT_5);
      {
        //<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#LogicalName=org.eclipse.egf.emf.pattern.model.call.Class.Class.getGenFeature.pre.insert" args="genFeature:genFeature,genClass:genClass,genPackage:genPackage,genModel:genModel,isJDK50:isJDK50,isInterface:isInterface,isImplementation:isImplementation,isGWT:isGWT,publicStaticFinalFlag:publicStaticFinalFlag,singleWildcard:singleWildcard,negativeOffsetCorrection:negativeOffsetCorrection,positiveOffsetCorrection:positiveOffsetCorrection,negativeOperationOffsetCorrection:negativeOperationOffsetCorrection,positiveOperationOffsetCorrection:positiveOperationOffsetCorrection"%>

        InternalPatternContext ictx = (InternalPatternContext) ctx;
        new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
        stringBuffer.setLength(0);

        final Map<String, Object> callParameters = new HashMap<String, Object>();
        callParameters.put("genFeature", genFeature);
        callParameters.put("genClass", genClass);
        callParameters.put("genPackage", genPackage);
        callParameters.put("genModel", genModel);
        callParameters.put("isJDK50", isJDK50);
        callParameters.put("isInterface", isInterface);
        callParameters.put("isImplementation", isImplementation);
        callParameters.put("isGWT", isGWT);
        callParameters.put("publicStaticFinalFlag", publicStaticFinalFlag);
        callParameters.put("singleWildcard", singleWildcard);
        callParameters.put("negativeOffsetCorrection", negativeOffsetCorrection);
        callParameters.put("positiveOffsetCorrection", positiveOffsetCorrection);
        callParameters.put("negativeOperationOffsetCorrection", negativeOperationOffsetCorrection);
        callParameters.put("positiveOperationOffsetCorrection", positiveOperationOffsetCorrection);
        CallHelper.executeWithParameterInjection(
            "platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#_0lSFDGJ-Ed-FqczH3ESmRw",
            new ExecutionContext((InternalPatternContext) ctx), callParameters);
        stringBuffer.setLength(0);
      }

      if (genModel.isDynamicDelegation()) {
        if (genFeature.isDerived() && genFeature.isVolatile() && !genFeature.isChangeable()
            && genFeature.getEcoreFeature().isTransient()) { // Check if current feature is a derived property.
          // begin-CDO
          stringBuffer.append(TEXT_5);
          {
            //<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#LogicalName=org.eclipse.egf.emf.pattern.model.call.Class.Class.getGenFeature.TODO.override" args="genFeature:genFeature,genClass:genClass,genPackage:genPackage,genModel:genModel,isJDK50:isJDK50,isInterface:isInterface,isImplementation:isImplementation,useInterfaceOverrideAnnotation:useInterfaceOverrideAnnotation,isGWT:isGWT,forceDefaultCase:forceDefaultCase,indentDefaultCase:indentDefaultCase,publicStaticFinalFlag:publicStaticFinalFlag,singleWildcard:singleWildcard,negativeOffsetCorrection:negativeOffsetCorrection,positiveOffsetCorrection:positiveOffsetCorrection,negativeOperationOffsetCorrection:negativeOperationOffsetCorrection,positiveOperationOffsetCorrection:positiveOperationOffsetCorrection"%>

            InternalPatternContext ictx = (InternalPatternContext) ctx;
            new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
            stringBuffer.setLength(0);

            final Map<String, Object> callParameters = new HashMap<String, Object>();
            callParameters.put("genFeature", genFeature);
            callParameters.put("genClass", genClass);
            callParameters.put("genPackage", genPackage);
            callParameters.put("genModel", genModel);
            callParameters.put("isJDK50", isJDK50);
            callParameters.put("isInterface", isInterface);
            callParameters.put("isImplementation", isImplementation);
            callParameters.put("useInterfaceOverrideAnnotation", useInterfaceOverrideAnnotation);
            callParameters.put("isGWT", isGWT);
            callParameters.put("forceDefaultCase", forceDefaultCase);
            callParameters.put("indentDefaultCase", indentDefaultCase);
            callParameters.put("publicStaticFinalFlag", publicStaticFinalFlag);
            callParameters.put("singleWildcard", singleWildcard);
            callParameters.put("negativeOffsetCorrection", negativeOffsetCorrection);
            callParameters.put("positiveOffsetCorrection", positiveOffsetCorrection);
            callParameters.put("negativeOperationOffsetCorrection", negativeOperationOffsetCorrection);
            callParameters.put("positiveOperationOffsetCorrection", positiveOperationOffsetCorrection);
            CallHelper.executeWithParameterInjection(
                "platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#_0lbPAWJ-Ed-FqczH3ESmRw",
                new ExecutionContext((InternalPatternContext) ctx), callParameters);
            stringBuffer.setLength(0);
          }

          // end-CDO
        } else {
          stringBuffer.append(TEXT_14);
          if (!isJDK50 && genFeature.isPrimitiveType()) {
            stringBuffer.append(TEXT_15);
          }
          stringBuffer.append(TEXT_15);
          stringBuffer.append(genFeature.getObjectType(genClass));
          stringBuffer.append(TEXT_16);
          stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
          stringBuffer.append(TEXT_17);
          stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
          stringBuffer.append(TEXT_18);
          stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
          stringBuffer.append(TEXT_19);
          if (!isJDK50 && genFeature.isPrimitiveType()) {
            stringBuffer.append(TEXT_20);
            stringBuffer.append(genFeature.getPrimitiveValueFunction());
            stringBuffer.append(TEXT_21);
          }
          stringBuffer.append(TEXT_22);
        }
      } else if (genModel.isReflectiveDelegation()) {
        if (genFeature.isDerived() && genFeature.isVolatile() && !genFeature.isChangeable()
            && genFeature.getEcoreFeature().isTransient()) { // Check if current feature is a derived property.
          // begin-CDO
          stringBuffer.append(TEXT_5);
          {
            //<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#LogicalName=org.eclipse.egf.emf.pattern.model.call.Class.Class.getGenFeature.TODO.override" args="genFeature:genFeature,genClass:genClass,genPackage:genPackage,genModel:genModel,isJDK50:isJDK50,isInterface:isInterface,isImplementation:isImplementation,useInterfaceOverrideAnnotation:useInterfaceOverrideAnnotation,isGWT:isGWT,forceDefaultCase:forceDefaultCase,indentDefaultCase:indentDefaultCase,publicStaticFinalFlag:publicStaticFinalFlag,singleWildcard:singleWildcard,negativeOffsetCorrection:negativeOffsetCorrection,positiveOffsetCorrection:positiveOffsetCorrection,negativeOperationOffsetCorrection:negativeOperationOffsetCorrection,positiveOperationOffsetCorrection:positiveOperationOffsetCorrection"%>

            InternalPatternContext ictx = (InternalPatternContext) ctx;
            new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
            stringBuffer.setLength(0);

            final Map<String, Object> callParameters = new HashMap<String, Object>();
            callParameters.put("genFeature", genFeature);
            callParameters.put("genClass", genClass);
            callParameters.put("genPackage", genPackage);
            callParameters.put("genModel", genModel);
            callParameters.put("isJDK50", isJDK50);
            callParameters.put("isInterface", isInterface);
            callParameters.put("isImplementation", isImplementation);
            callParameters.put("useInterfaceOverrideAnnotation", useInterfaceOverrideAnnotation);
            callParameters.put("isGWT", isGWT);
            callParameters.put("forceDefaultCase", forceDefaultCase);
            callParameters.put("indentDefaultCase", indentDefaultCase);
            callParameters.put("publicStaticFinalFlag", publicStaticFinalFlag);
            callParameters.put("singleWildcard", singleWildcard);
            callParameters.put("negativeOffsetCorrection", negativeOffsetCorrection);
            callParameters.put("positiveOffsetCorrection", positiveOffsetCorrection);
            callParameters.put("negativeOperationOffsetCorrection", negativeOperationOffsetCorrection);
            callParameters.put("positiveOperationOffsetCorrection", positiveOperationOffsetCorrection);
            CallHelper.executeWithParameterInjection(
                "platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#_0lbPAWJ-Ed-FqczH3ESmRw",
                new ExecutionContext((InternalPatternContext) ctx), callParameters);
            stringBuffer.setLength(0);
          }

          // end-CDO
        } else {
          stringBuffer.append(TEXT_14);
          if (!isJDK50 && genFeature.isPrimitiveType()) {
            stringBuffer.append(TEXT_15);
          }
          stringBuffer.append(TEXT_15);
          stringBuffer.append(genFeature.getObjectType(genClass));
          stringBuffer.append(TEXT_23);
          stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
          stringBuffer.append(TEXT_24);
          if (!isJDK50 && genFeature.isPrimitiveType()) {
            stringBuffer.append(TEXT_20);
            stringBuffer.append(genFeature.getPrimitiveValueFunction());
            stringBuffer.append(TEXT_21);
          }
          stringBuffer.append(TEXT_22);
        }
      } else if (genFeature.hasSettingDelegate()) {
        stringBuffer.append(TEXT_14);
        if (!isJDK50 && genFeature.isPrimitiveType()) {
          stringBuffer.append(TEXT_15);
        }
        stringBuffer.append(TEXT_15);
        stringBuffer.append(genFeature.getObjectType(genClass));
        stringBuffer.append(TEXT_19);
        stringBuffer.append(genFeature.getUpperName());
        stringBuffer.append(TEXT_25);
        if (!isJDK50 && genFeature.isPrimitiveType()) {
          stringBuffer.append(TEXT_20);
          stringBuffer.append(genFeature.getPrimitiveValueFunction());
          stringBuffer.append(TEXT_21);
        }
        stringBuffer.append(TEXT_22);
      } else if (!genFeature.isVolatile()) {
        if (genFeature.isListType()) {
          if (genModel.isVirtualDelegation()) {
            stringBuffer.append(TEXT_26);
            stringBuffer.append(genFeature.getImportedType(genClass));
            stringBuffer.append(TEXT_8);
            stringBuffer.append(genFeature.getSafeName());
            stringBuffer.append(TEXT_27);
            stringBuffer.append(genFeature.getImportedType(genClass));
            stringBuffer.append(TEXT_28);
            stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
            stringBuffer.append(positiveOffsetCorrection);
            stringBuffer.append(TEXT_29);
          }
          stringBuffer.append(TEXT_30);
          stringBuffer.append(genFeature.getSafeName());
          stringBuffer.append(TEXT_31);
          if (genModel.isVirtualDelegation()) {
            stringBuffer.append(TEXT_32);
            stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
            stringBuffer.append(positiveOffsetCorrection);
            stringBuffer.append(TEXT_17);
            stringBuffer.append(genFeature.getSafeName());
            stringBuffer.append(TEXT_33);
            stringBuffer.append(genClass.getListConstructor(genFeature));
            stringBuffer.append(TEXT_29);
          } else {
            stringBuffer.append(TEXT_34);
            stringBuffer.append(genFeature.getSafeName());
            stringBuffer.append(TEXT_33);
            stringBuffer.append(genClass.getListConstructor(genFeature));
            stringBuffer.append(TEXT_22);
          }
          stringBuffer.append(TEXT_35);
          stringBuffer.append(genFeature.getSafeName());
          stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
          stringBuffer.append(TEXT_22);
        } else if (genFeature.isContainer()) {
          stringBuffer.append(TEXT_36);
          stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
          stringBuffer.append(positiveOffsetCorrection);
          stringBuffer.append(TEXT_37);
          stringBuffer.append(genFeature.getImportedType(genClass));
          stringBuffer.append(TEXT_19);
          if (genFeature.isResolveProxies()) {
            stringBuffer.append(TEXT_38);
          } else {
            stringBuffer.append(TEXT_39);
          }
          stringBuffer.append(TEXT_40);
        } else {
          if (genFeature.isResolveProxies()) {
            if (genModel.isVirtualDelegation()) {
              stringBuffer.append(TEXT_26);
              stringBuffer.append(genFeature.getImportedType(genClass));
              stringBuffer.append(TEXT_8);
              stringBuffer.append(genFeature.getSafeName());
              stringBuffer.append(TEXT_27);
              stringBuffer.append(genFeature.getImportedType(genClass));
              stringBuffer.append(TEXT_28);
              stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
              stringBuffer.append(positiveOffsetCorrection);
              if (genFeature.hasEDefault()) {
                stringBuffer.append(TEXT_17);
                stringBuffer.append(genFeature.getEDefault());
              }
              stringBuffer.append(TEXT_29);
            }
            stringBuffer.append(TEXT_30);
            stringBuffer.append(genFeature.getSafeName());
            stringBuffer.append(TEXT_41);
            stringBuffer.append(genFeature.getSafeNameAsEObject());
            stringBuffer.append(TEXT_42);
            stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
            stringBuffer.append(TEXT_43);
            stringBuffer.append(genFeature.getCapName());
            stringBuffer.append(TEXT_44);
            stringBuffer.append(genFeature.getAsInternalEObject(genFeature.getSafeName(), false));
            stringBuffer.append(TEXT_45);
            stringBuffer.append(genFeature.getSafeName());
            stringBuffer.append(TEXT_44);
            stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
            stringBuffer.append(TEXT_46);
            stringBuffer.append(genFeature.getCapName());
            stringBuffer.append(TEXT_47);
            stringBuffer.append(genFeature.getSafeName());
            stringBuffer.append(TEXT_48);
            stringBuffer.append(genFeature.getCapName());
            stringBuffer.append(TEXT_49);
            if (genFeature.isEffectiveContains()) {
              stringBuffer.append(TEXT_50);
              stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
              stringBuffer.append(TEXT_51);
              stringBuffer.append(genFeature.getCapName());
              stringBuffer.append(TEXT_44);
              stringBuffer.append(genFeature.getAsInternalEObject(genFeature.getSafeName(), false));
              stringBuffer.append(TEXT_22);
              if (!genFeature.isBidirectional()) {
                stringBuffer.append(TEXT_50);
                stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
                stringBuffer.append(TEXT_52);
                stringBuffer.append(genFeature.getCapName());
                stringBuffer.append(TEXT_53);
                stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
                stringBuffer.append(negativeOffsetCorrection);
                stringBuffer.append(TEXT_54);
              } else {
                GenFeature reverseFeature = genFeature.getReverse();
                GenClass targetClass = reverseFeature.getGenClass();
                String reverseOffsetCorrection = targetClass.hasOffsetCorrection()
                    ? " + " + genClass.getOffsetCorrectionField(genFeature)
                    : "";
                stringBuffer.append(TEXT_50);
                stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
                stringBuffer.append(TEXT_55);
                stringBuffer.append(genFeature.getCapName());
                stringBuffer.append(TEXT_56);
                stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
                stringBuffer.append(reverseOffsetCorrection);
                stringBuffer.append(TEXT_17);
                stringBuffer.append(targetClass.getRawImportedInterfaceName());
                stringBuffer.append(TEXT_57);
              }
              stringBuffer.append(TEXT_58);
              stringBuffer.append(genFeature.getCapName());
              stringBuffer.append(TEXT_59);
              if (!genFeature.isBidirectional()) {
                stringBuffer.append(TEXT_60);
                stringBuffer.append(genFeature.getCapName());
                stringBuffer.append(TEXT_61);
                stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
                stringBuffer.append(negativeOffsetCorrection);
                stringBuffer.append(TEXT_62);
              } else {
                GenFeature reverseFeature = genFeature.getReverse();
                GenClass targetClass = reverseFeature.getGenClass();
                String reverseOffsetCorrection = targetClass.hasOffsetCorrection()
                    ? " + " + genClass.getOffsetCorrectionField(genFeature)
                    : "";
                stringBuffer.append(TEXT_63);
                stringBuffer.append(genFeature.getCapName());
                stringBuffer.append(TEXT_64);
                stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
                stringBuffer.append(reverseOffsetCorrection);
                stringBuffer.append(TEXT_17);
                stringBuffer.append(targetClass.getRawImportedInterfaceName());
                stringBuffer.append(TEXT_65);
              }
              stringBuffer.append(TEXT_66);
            } else if (genModel.isVirtualDelegation()) {
              stringBuffer.append(TEXT_67);
              stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
              stringBuffer.append(positiveOffsetCorrection);
              stringBuffer.append(TEXT_17);
              stringBuffer.append(genFeature.getSafeName());
              stringBuffer.append(TEXT_29);
            }
            if (!genModel.isSuppressNotification()) {
              stringBuffer.append(TEXT_68);
              stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
              stringBuffer.append(TEXT_69);
              stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
              stringBuffer.append(TEXT_70);
              stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
              stringBuffer.append(positiveOffsetCorrection);
              stringBuffer.append(TEXT_71);
              stringBuffer.append(genFeature.getCapName());
              stringBuffer.append(TEXT_17);
              stringBuffer.append(genFeature.getSafeName());
              stringBuffer.append(TEXT_72);
            }
            stringBuffer.append(TEXT_73);
          }
          if (!genFeature.isResolveProxies() && genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
            stringBuffer.append(TEXT_74);
            stringBuffer.append(genFeature.getImportedType(genClass));
            stringBuffer.append(TEXT_28);
            stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
            stringBuffer.append(positiveOffsetCorrection);
            if (genFeature.hasEDefault()) {
              stringBuffer.append(TEXT_17);
              stringBuffer.append(genFeature.getEDefault());
            }
            stringBuffer.append(TEXT_29);
          } else if (genClass.isFlag(genFeature)) {
            if (genFeature.isBooleanType()) {
              stringBuffer.append(TEXT_74);
              stringBuffer.append(genClass.getFlagsField(genFeature));
              stringBuffer.append(TEXT_75);
              stringBuffer.append(genFeature.getUpperName());
              stringBuffer.append(TEXT_76);
            } else {
              stringBuffer.append(TEXT_14);
              stringBuffer.append(genFeature.getUpperName());
              stringBuffer.append(TEXT_77);
              stringBuffer.append(genClass.getFlagsField(genFeature));
              stringBuffer.append(TEXT_75);
              stringBuffer.append(genFeature.getUpperName());
              stringBuffer.append(TEXT_78);
              stringBuffer.append(genFeature.getUpperName());
              stringBuffer.append(TEXT_79);
            }
          } else {
            stringBuffer.append(TEXT_14);
            stringBuffer.append(genFeature.getSafeName());
            stringBuffer.append(TEXT_22);
          }
        }
      } else {//volatile
        if (genFeature.isResolveProxies() && !genFeature.isListType()) {
          stringBuffer.append(TEXT_26);
          stringBuffer.append(genFeature.getImportedType(genClass));
          stringBuffer.append(TEXT_8);
          stringBuffer.append(genFeature.getSafeName());
          stringBuffer.append(TEXT_80);
          stringBuffer.append(genFeature.getAccessorName());
          stringBuffer.append(TEXT_81);
          stringBuffer.append(genFeature.getSafeName());
          stringBuffer.append(TEXT_41);
          stringBuffer.append(genFeature.getSafeNameAsEObject());
          stringBuffer.append(TEXT_82);
          stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
          stringBuffer.append(TEXT_83);
          stringBuffer.append(genFeature.getAsInternalEObject(genFeature.getSafeName(), false));
          stringBuffer.append(TEXT_84);
          stringBuffer.append(genFeature.getSafeName());
          stringBuffer.append(TEXT_22);
        } else if (genFeature.hasDelegateFeature()) {
          GenFeature delegateFeature = genFeature.getDelegateFeature();
          if (genFeature.isFeatureMapType()) {
            String featureMapEntryTemplateArgument = isJDK50
                ? "<" + genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap") + ".Entry>"
                : "";
            if (delegateFeature.isWrappedFeatureMapType()) {
              stringBuffer.append(TEXT_85);
              stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
              stringBuffer.append(TEXT_86);
              stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
              stringBuffer.append(TEXT_87);
              stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
              stringBuffer.append(TEXT_88);
              stringBuffer.append(delegateFeature.getAccessorName());
              stringBuffer.append(TEXT_89);
              stringBuffer.append(featureMapEntryTemplateArgument);
              stringBuffer.append(TEXT_90);
              stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
              stringBuffer.append(TEXT_72);
            } else {
              stringBuffer.append(TEXT_74);
              stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
              stringBuffer.append(TEXT_91);
              stringBuffer.append(delegateFeature.getAccessorName());
              stringBuffer.append(TEXT_92);
              stringBuffer.append(featureMapEntryTemplateArgument);
              stringBuffer.append(TEXT_90);
              stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
              stringBuffer.append(TEXT_29);
            }
          } else if (genFeature.isListType()) {
            if (delegateFeature.isWrappedFeatureMapType()) {
              stringBuffer.append(TEXT_93);
              stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
              stringBuffer.append(TEXT_88);
              stringBuffer.append(delegateFeature.getAccessorName());
              stringBuffer.append(TEXT_94);
              stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
              stringBuffer.append(TEXT_29);
            } else {
              stringBuffer.append(TEXT_95);
              stringBuffer.append(delegateFeature.getAccessorName());
              stringBuffer.append(TEXT_96);
              stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
              stringBuffer.append(TEXT_29);
            }
          } else {
            if (delegateFeature.isWrappedFeatureMapType()) {
              stringBuffer.append(TEXT_14);
              if (!isJDK50 && genFeature.isPrimitiveType()) {
                stringBuffer.append(TEXT_15);
              }
              if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
                stringBuffer.append(TEXT_15);
                stringBuffer.append(genFeature.getObjectType(genClass));
                stringBuffer.append(TEXT_19);
              }
              stringBuffer.append(TEXT_86);
              stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
              stringBuffer.append(TEXT_88);
              stringBuffer.append(delegateFeature.getAccessorName());
              stringBuffer.append(TEXT_97);
              stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
              stringBuffer.append(TEXT_24);
              if (!isJDK50 && genFeature.isPrimitiveType()) {
                stringBuffer.append(TEXT_20);
                stringBuffer.append(genFeature.getPrimitiveValueFunction());
                stringBuffer.append(TEXT_21);
              }
              stringBuffer.append(TEXT_22);
            } else {
              stringBuffer.append(TEXT_14);
              if (!isJDK50 && genFeature.isPrimitiveType()) {
                stringBuffer.append(TEXT_15);
              }
              if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
                stringBuffer.append(TEXT_15);
                stringBuffer.append(genFeature.getObjectType(genClass));
                stringBuffer.append(TEXT_19);
              }
              stringBuffer.append(TEXT_98);
              stringBuffer.append(delegateFeature.getAccessorName());
              stringBuffer.append(TEXT_99);
              stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
              stringBuffer.append(TEXT_24);
              if (!isJDK50 && genFeature.isPrimitiveType()) {
                stringBuffer.append(TEXT_20);
                stringBuffer.append(genFeature.getPrimitiveValueFunction());
                stringBuffer.append(TEXT_21);
              }
              stringBuffer.append(TEXT_22);
            }
          }
        } else if (genClass.getGetAccessorOperation(genFeature) != null) {
          stringBuffer.append(TEXT_26);
          stringBuffer
              .append(genClass.getGetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
        } else if (genFeature.hasGetterBody()) {
          stringBuffer.append(TEXT_26);
          stringBuffer.append(genFeature.getGetterBody(genModel.getIndentation(stringBuffer)));
        } else {
          stringBuffer.append(TEXT_5);
          {
            //<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#LogicalName=org.eclipse.egf.emf.pattern.model.call.Class.Class.getGenFeature.TODO.override" args="genFeature:genFeature,genClass:genClass,genPackage:genPackage,genModel:genModel,isJDK50:isJDK50,isInterface:isInterface,isImplementation:isImplementation,useInterfaceOverrideAnnotation:useInterfaceOverrideAnnotation,isGWT:isGWT,forceDefaultCase:forceDefaultCase,indentDefaultCase:indentDefaultCase,publicStaticFinalFlag:publicStaticFinalFlag,singleWildcard:singleWildcard,negativeOffsetCorrection:negativeOffsetCorrection,positiveOffsetCorrection:positiveOffsetCorrection,negativeOperationOffsetCorrection:negativeOperationOffsetCorrection,positiveOperationOffsetCorrection:positiveOperationOffsetCorrection"%>

            InternalPatternContext ictx = (InternalPatternContext) ctx;
            new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
            stringBuffer.setLength(0);

            final Map<String, Object> callParameters = new HashMap<String, Object>();
            callParameters.put("genFeature", genFeature);
            callParameters.put("genClass", genClass);
            callParameters.put("genPackage", genPackage);
            callParameters.put("genModel", genModel);
            callParameters.put("isJDK50", isJDK50);
            callParameters.put("isInterface", isInterface);
            callParameters.put("isImplementation", isImplementation);
            callParameters.put("useInterfaceOverrideAnnotation", useInterfaceOverrideAnnotation);
            callParameters.put("isGWT", isGWT);
            callParameters.put("forceDefaultCase", forceDefaultCase);
            callParameters.put("indentDefaultCase", indentDefaultCase);
            callParameters.put("publicStaticFinalFlag", publicStaticFinalFlag);
            callParameters.put("singleWildcard", singleWildcard);
            callParameters.put("negativeOffsetCorrection", negativeOffsetCorrection);
            callParameters.put("positiveOffsetCorrection", positiveOffsetCorrection);
            callParameters.put("negativeOperationOffsetCorrection", negativeOperationOffsetCorrection);
            callParameters.put("positiveOperationOffsetCorrection", positiveOperationOffsetCorrection);
            CallHelper.executeWithParameterInjection(
                "platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#_0lbPAWJ-Ed-FqczH3ESmRw",
                new ExecutionContext((InternalPatternContext) ctx), callParameters);
            stringBuffer.setLength(0);
          }

          //Class/getGenFeature.todo.override.javajetinc
        }
      }
      stringBuffer.append(TEXT_100);
    }
    InternalPatternContext ictx = (InternalPatternContext) ctx;
    new Node.DataLeaf(ictx.getNode(), getClass(), "doGenerate", stringBuffer.toString());
  }
}