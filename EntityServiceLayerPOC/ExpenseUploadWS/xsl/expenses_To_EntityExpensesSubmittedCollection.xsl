<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="../BPELProcess1.wsdl"/>
      <rootElement name="expenses" namespace="http://xmlns.oracle.com/EntityServiceLayerPOC/ExpenseService/submitExpense"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="../writeExpenseToDB.wsdl"/>
      <rootElement name="EntityExpensesSubmittedCollection" namespace="http://xmlns.oracle.com/pcbpel/adapter/db/top/writeExpenseToDB"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 11.1.1.5.0(build 110418.1550.0174) AT [FRI MAY 24 16:36:33 EST 2013]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:xp20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:bpel="http://docs.oasis-open.org/wsbpel/2.0/process/executable"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:bpm="http://xmlns.oracle.com/bpmn20/extensions"
                xmlns:plnk="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:tns="http://xmlns.oracle.com/pcbpel/adapter/db/EntityServiceLayerPOC/ExpenseUploadWS/writeExpenseToDB"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:top="http://xmlns.oracle.com/pcbpel/adapter/db/top/writeExpenseToDB"
                xmlns:med="http://schemas.oracle.com/mediator/xpath"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:client="http://xmlns.oracle.com/EntityServiceLayerPOC/ExpenseService/BPELProcess1"
                xmlns:xdk="http://schemas.oracle.com/bpel/extension/xpath/function/xdk"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:ns1="http://xmlns.oracle.com/EntityServiceLayerPOC/ExpenseService/submitExpense"
                xmlns:bpmn="http://schemas.oracle.com/bpm/xpath"
                xmlns:ldap="http://schemas.oracle.com/xpath/extension/ldap"
                exclude-result-prefixes="xsi xsl plnk wsdl client xsd ns1 tns top xp20 bpws bpel bpm ora socket mhdr oraext dvm hwf med ids xdk xref bpmn ldap">
  <xsl:template match="/">
    <top:EntityExpensesSubmittedCollection>
      <top:EntityExpensesSubmitted>
        <top:userId>
          <xsl:text disable-output-escaping="no">test user id</xsl:text>
        </top:userId>
        <top:amount>
          <xsl:value-of select="/ns1:expenses/ns1:amount"/>
        </top:amount>
        <top:incuredDate>
          <xsl:value-of select="/ns1:expenses/ns1:date"/>
        </top:incuredDate>
        <top:description>
          <xsl:value-of select="/ns1:expenses/ns1:description"/>
        </top:description>
        <top:receiptId>
          <xsl:text disable-output-escaping="no">test receipt id</xsl:text>
        </top:receiptId>
        <top:entryId>
          <xsl:value-of select="xp20:current-dateTime()"/>
        </top:entryId>
      </top:EntityExpensesSubmitted>
    </top:EntityExpensesSubmittedCollection>
  </xsl:template>
</xsl:stylesheet>
